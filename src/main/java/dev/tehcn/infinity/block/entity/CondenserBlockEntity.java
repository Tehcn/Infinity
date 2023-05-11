package dev.tehcn.infinity.block.entity;

import dev.tehcn.infinity.recipe.CondensingRecipe;
import dev.tehcn.infinity.screen.CondenserScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.Optional;

public class CondenserBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(3, ItemStack.EMPTY);

    public static final long ENERGY_COST_PER_TICK = 10;

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(10000, 20, 0) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private final int maxProgress = 72;

    public CondenserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CONDENSER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch(index) {
                    case 0 -> CondenserBlockEntity.this.progress;
                    case 1 -> CondenserBlockEntity.this.maxProgress;
                    case 2 -> (int)CondenserBlockEntity.this.energyStorage.amount;
                    case 3 -> (int)CondenserBlockEntity.this.energyStorage.capacity;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> CondenserBlockEntity.this.progress = value;
                    case 1 -> throw new RuntimeException("Error trying to access CondenserBlockEntity propdel idx 1");
                    case 2 -> CondenserBlockEntity.this.energyStorage.amount = value;
                    case 3 -> throw new RuntimeException("Error trying to access CondenserBlockEntity propdel idx 3");
                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.infinity.condenser");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CondenserScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("condenser.progress", progress);
        nbt.putInt("condenser.energy", (int)energyStorage.amount);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("condenser.progress");
        energyStorage.amount = nbt.getInt("condenser.energy");
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, CondenserBlockEntity entity) {
        if(world.isClient) return;

        if(hasRecipe(entity) && hasEnergy(entity)) {
            entity.progress++;
            markDirty(world, blockPos, state);
            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    private static boolean hasEnergy(CondenserBlockEntity entity) {
        if(entity.energyStorage.amount > ENERGY_COST_PER_TICK) {
            return true;
        } else return false;
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(CondenserBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for(int i=0;i<entity.size();i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<CondensingRecipe> recipe = entity.getWorld().getRecipeManager()
                .getFirstMatch(CondensingRecipe.Type.INSTANCE, inventory, entity.getWorld());

        if(hasRecipe(entity)) {
            entity.removeStack(1, 1);
            entity.setStack(2, new ItemStack(recipe.get().getOutput().getItem(),
                    entity.getStack(2).getCount() + 1));
        }

        entity.energyStorage.amount -= ENERGY_COST_PER_TICK;
        entity.resetProgress();
    }

    private static boolean hasRecipe(CondenserBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for(int i=0;i<entity.size();i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<CondensingRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(CondensingRecipe.Type.INSTANCE, inventory, entity.getWorld());

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput().getItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(2).getItem() == output || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
    }

}

package dev.tehcn.infinity.item;

import dev.tehcn.infinity.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup INFINITY = FabricItemGroup.builder(new Identifier("infinity", "infinity"))
            .icon(() -> new ItemStack(ModItems.RAW_INFINITY))
            .build();

    public static final ItemGroup INFINITY_MACHINES = FabricItemGroup.builder(new Identifier("infinity", "infinity_machines"))
            .icon(() -> new ItemStack(ModBlocks.CONDENSER.asItem()))
            .build();
}

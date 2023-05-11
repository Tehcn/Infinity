package dev.tehcn.infinity.block.entity;

import dev.tehcn.infinity.Infinity;
import dev.tehcn.infinity.block.ModBlocks;
import dev.tehcn.infinity.util.registry.RegistryHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<CondenserBlockEntity> CONDENSER;

    public static void registerBlockEntities() {
        Infinity.LOGGER.debug("Registering Mod BlockEntities for " + Infinity.MOD_ID);
        CONDENSER = Registry.register(Registries.BLOCK_ENTITY_TYPE, RegistryHelper.id("condenser"),
                FabricBlockEntityTypeBuilder.create(CondenserBlockEntity::new, ModBlocks.CONDENSER).build(null));
    }
}

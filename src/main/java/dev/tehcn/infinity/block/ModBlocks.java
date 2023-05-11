package dev.tehcn.infinity.block;

import dev.tehcn.infinity.Infinity;
import dev.tehcn.infinity.block.custom.CondenserBlock;
import dev.tehcn.infinity.item.ModItemGroup;
import dev.tehcn.infinity.util.registry.RegistryHelper;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Rarity;

import java.util.List;

public class ModBlocks {
    public static final Block INFINITY_BLOCK = registerBlock("infinity_block",
            new Block(FabricBlockSettings.of(Material.METAL).luminance(15).requiresTool()),
            Rarity.EPIC,
            List.of(ItemGroups.BUILDING_BLOCKS, ModItemGroup.INFINITY));

    public static final Block CONDENSER = registerBlock("condenser",
            new CondenserBlock(FabricBlockSettings.of(Material.METAL).nonOpaque().requiresTool()),
            Rarity.COMMON,
            List.of(ItemGroups.FUNCTIONAL, ModItemGroup.INFINITY, ModItemGroup.INFINITY_MACHINES));

    private static Block registerBlock(String name, Block block, Rarity rarity, List<ItemGroup> groups) {
        groups.forEach(group -> ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.add(block)));
        return RegistryHelper.registerBlock(name, block, rarity);
    }

    public static void registerModBlocks() {
        Infinity.LOGGER.debug("Registering Mod Blocks for " + Infinity.MOD_ID);
    }
}

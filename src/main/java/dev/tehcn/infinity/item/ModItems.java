package dev.tehcn.infinity.item;

import dev.tehcn.infinity.Infinity;
import dev.tehcn.infinity.util.registry.RegistryHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final Item RAW_INFINITY = registerItem("raw_infinity",
            new Item(new FabricItemSettings().rarity(Rarity.RARE).fireproof()),
            List.of(ModItemGroup.INFINITY, ItemGroups.INGREDIENTS));
    public static final Item INFINITY = registerItem("infinity",
            new Item(new FabricItemSettings().rarity(Rarity.EPIC).fireproof()),
            List.of(ModItemGroup.INFINITY, ItemGroups.INGREDIENTS));

//    public static final Item INFINITY_HELMET = registerItem("INFINITY_HELMET".toLowerCase(),
//            new ArmorItem(ModArmorMaterials.INFINITY, EquipmentSlot.HEAD,
//                    new FabricItemSettings().group(ModItemGroup.INFINITY).rarity(Rarity.EPIC).fireproof()));
//    public static final Item INFINITY_CHESTPLATE = registerItem("INFINITY_CHESTPLATE".toLowerCase(),
//            new ArmorItem(ModArmorMaterials.INFINITY, EquipmentSlot.CHEST,
//                    new FabricItemSettings().group(ModItemGroup.INFINITY).rarity(Rarity.EPIC)));
//    public static final Item INFINITY_LEGGINGS = registerItem("INFINITY_LEGGINGS".toLowerCase(),
//            new ArmorItem(ModArmorMaterials.INFINITY, EquipmentSlot.LEGS,
//                    new FabricItemSettings().group(ModItemGroup.INFINITY).rarity(Rarity.EPIC)));
//    public static final Item INFINITY_BOOTS = registerItem("INFINITY_BOOTS".toLowerCase(),
//            new ArmorItem(ModArmorMaterials.INFINITY, EquipmentSlot.FEET,
//                    new FabricItemSettings().group(ModItemGroup.INFINITY).rarity(Rarity.EPIC)));

    private static Item registerItem(String name, Item item, List<ItemGroup> groups) {
        groups.forEach(group -> ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.add(item)));
        return RegistryHelper.registerItem(name, item);
    }

    public static void registerModItems() {
        Infinity.LOGGER.debug("Registering Mod Items for " + Infinity.MOD_ID);
    }

}

package dev.tehcn.infinity.armor;

import dev.tehcn.infinity.item.ModArmorMaterials;
import dev.tehcn.infinity.item.ModItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Rarity;

import java.util.List;

public class ArmorSets {
    public static final ArmorSet INFINITY = new ArmorSet(ModArmorMaterials.INFINITY,
            settings -> settings.rarity(Rarity.EPIC).fireproof());

    public static void register() {
        INFINITY.register("infinity", List.of(ItemGroups.COMBAT,  ModItemGroup.INFINITY));
    }
}

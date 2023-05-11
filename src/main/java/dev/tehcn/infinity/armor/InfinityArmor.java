package dev.tehcn.infinity.armor;

import dev.tehcn.infinity.item.ModArmorMaterials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public class InfinityArmor extends ArmorItem {
    public final EquipmentSlot slot;

    public InfinityArmor(EquipmentSlot slot, Settings settings) {
        super(ModArmorMaterials.INFINITY, slot, settings);
        this.slot = slot;
    }

    public InfinityArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
        this.slot = slot;
    }
}

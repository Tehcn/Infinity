package dev.tehcn.infinity.armor;

import dev.tehcn.infinity.item.ModItemGroup;
import dev.tehcn.infinity.util.registry.RegistryHelper;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArmorSet {
    private final ArmorItem helmet;
    private final ArmorItem chestplate;
    private final ArmorItem leggings;
    private final ArmorItem boots;

    private final List<Item> armorSet;

    public ArmorItem baseArmorItem(ArmorMaterial material, EquipmentSlot slot, Consumer<Item.Settings> settingsProcessor) {
        final FabricItemSettings settings = (FabricItemSettings) new FabricItemSettings();
        settingsProcessor.accept(settings);
        return this.makeItem(material, slot, settings);
    }

    public ArmorSet(ArmorMaterial material) {
        this(material, settings -> { });
    }

    public ArmorSet(ArmorMaterial material, Consumer<Item.Settings> settingsProcessor) {
        this.helmet = baseArmorItem(material, EquipmentSlot.HEAD, settingsProcessor);
        this.chestplate = baseArmorItem(material, EquipmentSlot.CHEST, settingsProcessor);
        this.leggings = baseArmorItem(material, EquipmentSlot.LEGS, settingsProcessor);
        this.boots = baseArmorItem(material, EquipmentSlot.FEET, settingsProcessor);
        this.armorSet = List.of(helmet, chestplate, leggings, boots);
    }

    public void register(String name, List<ItemGroup> groups) {
        ArrayList<Item> items = new ArrayList<>();
        items.add(RegistryHelper.registerItem(name + "_helmet", helmet));
        items.add(RegistryHelper.registerItem(name + "_chestplate", chestplate));
        items.add(RegistryHelper.registerItem(name + "_leggings", leggings));
        items.add(RegistryHelper.registerItem(name + "_boots", boots));
        groups.forEach(group -> ItemGroupEvents.modifyEntriesEvent(group)
                .register(content -> items.forEach(item -> content.add(item))));
    }

    protected ArmorItem makeItem(ArmorMaterial material, EquipmentSlot slot, Item.Settings settings) {
        return new ArmorItem(material, slot, settings);
    }

    public ArmorItem getHelmet() {
        return helmet;
    }

    public ArmorItem getChestplate() {
        return chestplate;
    }

    public ArmorItem getLeggings() {
        return leggings;
    }

    public ArmorItem getBoots() {
        return boots;
    }

    public List<Item> getArmorSet() {
        return armorSet;
    }

    public boolean isInArmorSet(ItemStack stack) {
        return this.getArmorSet().contains(stack.getItem());
    }

}

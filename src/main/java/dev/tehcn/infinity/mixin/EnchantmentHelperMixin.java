package dev.tehcn.infinity.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import dev.tehcn.infinity.abilities.Abilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "getProtectionAmount", at = @At("TAIL"), cancellable = true)
    private static void infinity$damageReduction(Iterable<ItemStack> equipment, DamageSource source, CallbackInfoReturnable<Integer> cir) {
        // Make sure that there is any gear to check
        if (!equipment.iterator().hasNext()) return;

        // no fall
        Item gear = equipment.iterator().next().getItem();
        if (Abilities.NO_FALL.getItems().contains(gear) && source.isFromFalling()) cir.setReturnValue(Integer.MAX_VALUE - 1);
    }
}

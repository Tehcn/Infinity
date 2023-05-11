package dev.tehcn.infinity.mixin;

import dev.tehcn.infinity.armor.ArmorSets;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.ServerMetadata;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Shadow @Final private Thread serverThread;

    @Shadow @Final private ServerMetadata metadata;

    @Shadow private PlayerManager playerManager;

    private static int timeoutTicks = 4;

    @Inject(method = "tick", at = @At("HEAD"))
    public void infinity$applyAbilityEffects(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        timeoutTicks += 1;

        if(timeoutTicks == 5) {
            timeoutTicks = 0;
            List<ServerPlayerEntity> players = this.playerManager.getPlayerList();

            players.forEach(entity -> {
                Iterable<ItemStack> armor = entity.getArmorItems();

                armor.forEach(itemStack -> {
                    if(ArmorSets.INFINITY.isInArmorSet(itemStack)) {
                        ArmorItem item = (ArmorItem) itemStack.getItem();

                        if(item == ArmorSets.INFINITY.getHelmet()) {
                            StatusEffect effect1 = StatusEffects.WATER_BREATHING;
                            entity.addStatusEffect(new StatusEffectInstance(effect1, 400, 1,
                                    false, false, true));
                            StatusEffect effect2 = StatusEffects.NIGHT_VISION;
                            entity.addStatusEffect(new StatusEffectInstance(effect2, 400, 1,
                                    false, false, true));
                        }

                        if(item == ArmorSets.INFINITY.getChestplate()) {
                            StatusEffect effect1 = StatusEffects.ABSORPTION;
                            entity.addStatusEffect(new StatusEffectInstance(effect1, 400, 3,
                                    false, false, true));
                            StatusEffect effect2 = StatusEffects.RESISTANCE;
                            entity.addStatusEffect(new StatusEffectInstance(effect2, 400, 2,
                                    false, false, true));
                            StatusEffect effect3 = StatusEffects.REGENERATION;
                            entity.addStatusEffect(new StatusEffectInstance(effect3, 400, 2,
                                    false, false, true));
                            StatusEffect effect4 = StatusEffects.SATURATION;
                            entity.addStatusEffect(new StatusEffectInstance(effect4, 400, 3,
                                    false, false, true));
                        }

                        if(item == ArmorSets.INFINITY.getLeggings()) {
                            StatusEffect effect1 = StatusEffects.SPEED;
                            entity.addStatusEffect(new StatusEffectInstance(effect1, 400, 4,
                                    false, false, true));
                        }

                        if(item == ArmorSets.INFINITY.getBoots()) {
                            StatusEffect effect1 = StatusEffects.INVISIBILITY;
                            entity.addStatusEffect(new StatusEffectInstance(effect1, 400, 2,
                                    false, false, true));
                        }
                    }
                });
            });
        }
    }
}

package dev.tehcn.infinity.datagen.advancements;

import dev.tehcn.infinity.block.ModBlocks;
import dev.tehcn.infinity.item.ModItems;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.ChangedDimensionCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class Advancements implements Consumer<Consumer<Advancement>> {
    @Override
    public void accept(Consumer<Advancement> consumer) {
        Advancement obtainRawInfinity = Advancement.Builder.create()
                .display(
                        ModItems.RAW_INFINITY,
                        Text.literal("Infinity... almost"),
                        Text.literal("Obtain raw infinity"),
                        new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .criterion("obtain_raw_infinity", InventoryChangedCriterion.Conditions.items(ModItems.RAW_INFINITY))
                .build(consumer, "infinity" + "/root");

        Advancement obtainInfinity = Advancement.Builder.create().parent(obtainRawInfinity)
                .display(
                        ModItems.INFINITY,
                        Text.literal("Infinity in the Palm of Your Hand"),
                        Text.literal("Obtain infinity"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .criterion("obtain_infinity", InventoryChangedCriterion.Conditions.items(ModItems.INFINITY))
                .build(consumer, "infinity" + "/obtain_infinity");

        Advancement visitDarkness = Advancement.Builder.create().parent(obtainInfinity)
                .display(
                    ModBlocks.INFINITY_BLOCK.asItem(),
                    Text.literal("The Darkness Awaits"),
                    Text.literal("Visit the Darkness"),
                    null,
                    AdvancementFrame.CHALLENGE,
                    true,
                    true,
                    false
                )
                .criterion("visit_darkness", ChangedDimensionCriterion.Conditions.to(World.END))
                .build(consumer, "infinity" + "/visit_darkness");
    }

}

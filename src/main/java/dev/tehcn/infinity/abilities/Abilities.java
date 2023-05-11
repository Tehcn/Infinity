package dev.tehcn.infinity.abilities;

import dev.tehcn.infinity.armor.ArmorSets;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;

public class Abilities {
    public static final Ability NO_FALL = new Ability("ability.infinity.no_fall", 1, false);

    public static void init() {
        NO_FALL.addItem(ArmorSets.INFINITY.getBoots().asItem(), Style.EMPTY.withColor(Formatting.AQUA));
    }
}

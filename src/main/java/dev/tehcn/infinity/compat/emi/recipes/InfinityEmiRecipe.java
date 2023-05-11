package dev.tehcn.infinity.compat.emi.recipes;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.tehcn.infinity.compat.emi.InfinityEmiPlugin;
import dev.tehcn.infinity.item.ModItems;
import dev.tehcn.infinity.util.registry.RegistryHelper;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InfinityEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final List<EmiIngredient> input;
    private final List<EmiStack> output;

    public InfinityEmiRecipe() {
        this.id = RegistryHelper.id("infinity");
        this.input = List.of(EmiIngredient.of(TagKey.of(Registries.ITEM.getKey(), id)));
        this.output = List.of(EmiStack.of(ModItems.INFINITY));
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return InfinityEmiPlugin.CONDENSER_CATEGORY;
    }

    @Nullable
    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return input;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return output;
    }

    @Override
    public int getDisplayWidth() {
        return 76;
    }

    @Override
    public int getDisplayHeight() {
        return 18;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 26, 1);
        widgets.addSlot(input.get(0), 0, 0);
        widgets.addSlot(output.get(0), 58, 0).recipeContext(this);
    }
}

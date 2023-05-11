package dev.tehcn.infinity.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import dev.tehcn.infinity.block.ModBlocks;
import dev.tehcn.infinity.compat.emi.recipes.InfinityEmiRecipe;
import dev.tehcn.infinity.recipe.CondensingRecipe;
import dev.tehcn.infinity.recipe.ModRecipes;
import dev.tehcn.infinity.util.registry.RegistryHelper;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;

import java.util.List;

public class InfinityEmiPlugin implements EmiPlugin {
    public static final Identifier CONDENSER_SHEET = RegistryHelper.id("textures/gui/condenser_gui.png");
    public static final EmiStack CONDENSER = EmiStack.of(ModBlocks.CONDENSER);
    public static final EmiRecipeCategory CONDENSER_CATEGORY =
            new EmiRecipeCategory(RegistryHelper.id("condenser"), CONDENSER, new EmiTexture(CONDENSER_SHEET, 0, 0, 175, 167));

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(CONDENSER_CATEGORY);
        registry.addWorkstation(CONDENSER_CATEGORY, CONDENSER);

        RecipeManager manager = registry.getRecipeManager();
    }
}

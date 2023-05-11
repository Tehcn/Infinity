package dev.tehcn.infinity.recipe;

import dev.tehcn.infinity.Infinity;
import dev.tehcn.infinity.util.registry.RegistryHelper;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRecipes {
    public static void registerRecipes() {
        Infinity.LOGGER.debug("Registering Mod Recipes for " + Infinity.MOD_ID);

        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id(CondensingRecipe.Serializer.ID),
                CondensingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, RegistryHelper.id(CondensingRecipe.Type.ID),
                CondensingRecipe.Type.INSTANCE);
    }
}

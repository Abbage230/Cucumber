package com.blakebr0.cucumber.compat.kubejs;

import com.blakebr0.cucumber.helper.RecipeHelper;
import dev.latvian.mods.kubejs.core.RecipeManagerKJS;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RecipesKubeEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.Map;

public class CucumberKubeJSPlugin implements KubeJSPlugin {
    @Override
    public void injectRuntimeRecipes(RecipesKubeEvent event, RecipeManagerKJS manager, Map<ResourceLocation, RecipeHolder<?>> recipesByName) {
        if (manager instanceof RecipeManager recipeManager) {
            RecipeHelper.fireRecipeManagerLoadedEventKubeJSEdition(recipeManager, recipesByName);
        }
    }
}

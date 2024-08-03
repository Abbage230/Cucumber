package com.blakebr0.cucumber.compat.kubejs;

import dev.latvian.mods.kubejs.core.RecipeManagerKJS;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RecipesKubeEvent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Map;

public class CucumberKubeJSPlugin implements KubeJSPlugin {
    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry registry) {
        // TODO: Implement custom schemas for these

        registry.namespace("cucumber")
            .shaped("shaped_no_mirror")
            .shaped("shaped_transfer_damage")
        ;

        //.shaped("shaped_tag")
        //.shapeless("shapeless_tag")
    }

    @Override
    public void injectRuntimeRecipes(RecipesKubeEvent event, RecipeManagerKJS manager, Map<ResourceLocation, RecipeHolder<?>> recipesByName) {
        // TODO: 1.21 kubejs recipe manager thingy?
//        RecipeHelper.fireRecipeManagerLoadedEventKubeJSEdition(manager, recipesByName);
    }
}

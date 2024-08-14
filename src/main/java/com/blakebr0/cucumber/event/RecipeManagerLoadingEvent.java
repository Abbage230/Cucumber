package com.blakebr0.cucumber.event;

import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.bus.api.Event;

import java.util.List;

public class RecipeManagerLoadingEvent extends Event {
    private final RecipeManager manager;
    private final List<RecipeHolder<?>> recipes;

    public RecipeManagerLoadingEvent(RecipeManager manager, List<RecipeHolder<?>> recipes) {
        this.manager = manager;
        this.recipes = recipes;
    }

    public RecipeManager getRecipeManager() {
        return this.manager;
    }

    public void addRecipe(RecipeHolder<?> recipe) {
        this.recipes.add(recipe);
    }
}

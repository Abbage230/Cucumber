package com.blakebr0.cucumber.helper;

import com.blakebr0.cucumber.Cucumber;
import com.blakebr0.cucumber.event.RecipeManagerLoadingEvent;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RecipesUpdatedEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// TODO: 1.21 recipe stuff
public final class RecipeHelper {
    private static RecipeManager recipeManager;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onAddReloadListeners(AddReloadListenerEvent event) {
        recipeManager = event.getServerResources().getRecipeManager();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRecipesUpdated(RecipesUpdatedEvent event) {
        recipeManager = event.getRecipeManager();
    }

    public static RecipeManager getRecipeManager() {
        return recipeManager;
    }

    public static Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> getRecipes() {
        throw new NotImplementedException();
//        return getRecipeManager().recipes;
    }

    // TODO: 1.21 try to remove this
    public static <C extends RecipeInput, T extends Recipe<C>> Collection<RecipeHolder<T>> getRecipes(RecipeType<T> type) {
        return (Collection<RecipeHolder<T>>) (Object) getRecipeManager().byType.get(type);
    }

    @Deprecated(forRemoval = true)
    public static void addRecipe(Recipe<?> recipe) {
//        if (recipeManager.recipes instanceof ImmutableMap) {
//            recipeManager.recipes = new HashMap<>(recipeManager.recipes);
//            recipeManager.recipes.replaceAll((t, v) -> new HashMap<>(recipeManager.recipes.get(t)));
//        }
//
//        if (recipeManager.byName instanceof ImmutableMap) {
//            recipeManager.byName = new HashMap<>(recipeManager.byName);
//        }
//
//        getRecipeManager().recipes.computeIfAbsent(recipe.getType(), t -> new HashMap<>()).put(recipe.getId(), recipe);
//        getRecipeManager().byName.put(recipe.getId(), recipe);
    }

    public static void fireRecipeManagerLoadingEvent(RecipeManager manager, ImmutableMultimap.Builder<RecipeType<?>, RecipeHolder<?>> map,
                                                     ImmutableMap.Builder<ResourceLocation, Recipe<?>> builder) {
        var stopwatch = Stopwatch.createStarted();
        var recipes = new ArrayList<RecipeHolder<?>>();

        try {
            NeoForge.EVENT_BUS.post(new RecipeManagerLoadingEvent(manager, recipes));
        } catch (Exception e) {
            Cucumber.LOGGER.error("An error occurred while firing RecipeManagerLoadingEvent", e);
        }

        for (var recipe : recipes) {
            map.put(recipe.value().getType(), recipe);
            builder.put(recipe.id(), recipe.value());
        }

        Cucumber.LOGGER.info("Registered {} recipes in {} ms", recipes.size(), stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
    }

    public static void fireRecipeManagerLoadedEventKubeJSEdition(RecipeManager manager, Map<ResourceLocation, Recipe<?>> recipesByName) {
        var stopwatch = Stopwatch.createStarted();
        var recipes = new ArrayList<Recipe<?>>();

        try {
//            NeoForge.EVENT_BUS.post(new RecipeManagerLoadingEvent(manager, recipes));
        } catch (Exception e) {
            Cucumber.LOGGER.error("An error occurred while firing RecipeManagerLoadingEvent", e);
        }

        for (var recipe : recipes) {
//            recipesByName.put(recipe.getId(), recipe);
        }

        Cucumber.LOGGER.info("Registered {} recipes in {} ms (KubeJS mode)", recipes.size(), stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
    }
}

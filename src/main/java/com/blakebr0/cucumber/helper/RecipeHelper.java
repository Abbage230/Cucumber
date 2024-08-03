package com.blakebr0.cucumber.helper;

import com.blakebr0.cucumber.Cucumber;
import com.blakebr0.cucumber.event.RecipeManagerLoadingEvent;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
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

    public static <C extends RecipeInput, T extends Recipe<C>> Map<ResourceLocation, T> getRecipes(RecipeType<T> type) {
        throw new NotImplementedException();
//        return getRecipeManager().byType(type);
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

    // map parameter uses Object because custom servers replace the ImmutableMap.Builder with a different map type
    public static void fireRecipeManagerLoadedEvent(RecipeManager manager, Map<RecipeType<?>, Object> map, ImmutableMap.Builder<ResourceLocation, Recipe<?>> builder) {
        var stopwatch = Stopwatch.createStarted();
        var recipes = new ArrayList<Recipe<?>>();

        try {
            NeoForge.EVENT_BUS.post(new RecipeManagerLoadingEvent(manager, recipes));
        } catch (Exception e) {
            Cucumber.LOGGER.error("An error occurred while firing RecipeManagerLoadingEvent", e);
        }

        for (var recipe : recipes) {
            var recipeType = recipe.getType();
//            var recipeId = recipe.getId();
            var recipeMap = map.get(recipeType);

            // Mohist (and I think another custom server) change it to this because annoying hacky hybrid server reasons
            if (recipeMap instanceof Object2ObjectLinkedOpenHashMap<?, ?>) {
                var o2oRecipeMap = (Object2ObjectLinkedOpenHashMap<Object, Object>) recipeMap;
//                o2oRecipeMap.put(recipeId, recipe);
            } else if (recipeMap instanceof ImmutableMap.Builder<?, ?>) {
                var recipeMapBuilder = (ImmutableMap.Builder<Object, Object>) recipeMap;
//                recipeMapBuilder.put(recipeId, recipe);
            } else if (recipeMap == null) {
                var recipeMapBuilder = ImmutableMap.builder();
//                recipeMapBuilder.put(recipeId, recipe);
                map.put(recipeType, recipeMapBuilder);
            } else {
//                Cucumber.LOGGER.error("Failed to register recipe {} to map of type {}", recipeId, recipeMap.getClass());
            }

//            builder.put(recipeId, recipe);
        }

        Cucumber.LOGGER.info("Registered {} recipes in {} ms", recipes.size(), stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
    }

    public static void fireRecipeManagerLoadedEventKubeJSEdition(RecipeManager manager, Map<ResourceLocation, Recipe<?>> recipesByName) {
        var stopwatch = Stopwatch.createStarted();
        var recipes = new ArrayList<Recipe<?>>();

        try {
            NeoForge.EVENT_BUS.post(new RecipeManagerLoadingEvent(manager, recipes));
        } catch (Exception e) {
            Cucumber.LOGGER.error("An error occurred while firing RecipeManagerLoadingEvent", e);
        }

        for (var recipe : recipes) {
//            recipesByName.put(recipe.getId(), recipe);
        }

        Cucumber.LOGGER.info("Registered {} recipes in {} ms (KubeJS mode)", recipes.size(), stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
    }
}

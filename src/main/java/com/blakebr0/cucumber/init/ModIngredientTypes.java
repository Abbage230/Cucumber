package com.blakebr0.cucumber.init;

import com.blakebr0.cucumber.Cucumber;
import com.blakebr0.cucumber.crafting.ingredient.IngredientWithCount;
import net.neoforged.neoforge.common.crafting.IngredientType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class ModIngredientTypes {
    public static final DeferredRegister<IngredientType<?>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.INGREDIENT_TYPES, Cucumber.MOD_ID);

    public static final DeferredHolder<IngredientType<?>, IngredientType<IngredientWithCount>> WITH_COUNT = REGISTRY.register("with_count", () -> new IngredientType<>(IngredientWithCount.MAP_CODEC));
}

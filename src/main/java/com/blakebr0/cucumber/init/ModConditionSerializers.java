package com.blakebr0.cucumber.init;

import com.blakebr0.cucumber.Cucumber;
import com.blakebr0.cucumber.crafting.conditions.FeatureFlagCondition;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class ModConditionSerializers {
    public static final DeferredRegister<MapCodec<? extends ICondition>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, Cucumber.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends ICondition>, MapCodec<? extends ICondition>> FEATURE_FLAG = REGISTRY.register("feature_flag", () -> FeatureFlagCondition.CODEC);
}

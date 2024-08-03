package com.blakebr0.cucumber.init;

import com.blakebr0.cucumber.Cucumber;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Cucumber.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> WATERING_CAN = REGISTRY.register("watering_can", () -> SoundEvent.createVariableRangeEvent(Cucumber.resource("watering_can")));
}

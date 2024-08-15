package com.blakebr0.cucumber.mixin;

import com.blakebr0.cucumber.helper.RecipeHelper;
import net.minecraft.commands.Commands;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ReloadableServerResources.class)
public class ReloadableServerResourcesMixin {
    @Shadow @Final private RecipeManager recipes;

    @Inject(at = @At(value = "RETURN"), method = "<init>")
    public void cucumber$constructor(
            RegistryAccess.Frozen registryAccess,
            FeatureFlagSet enabledFeatures,
            Commands.CommandSelection commandSelection,
            int functionCompilationLevel,
            CallbackInfo ci
    ) {
        RecipeHelper.setRecipeManager(this.recipes);
    }
}

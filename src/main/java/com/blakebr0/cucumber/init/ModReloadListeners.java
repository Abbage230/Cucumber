package com.blakebr0.cucumber.init;

import com.blakebr0.cucumber.event.RegisterRecipesEvent;
import com.blakebr0.cucumber.helper.RecipeHelper;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

public class ModReloadListeners {
    @SubscribeEvent
    public void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new RegisterRecipesReloadListener());
    }

    private static class RegisterRecipesReloadListener implements ResourceManagerReloadListener {
        @Override
        public void onResourceManagerReload(ResourceManager manager) {
            NeoForge.EVENT_BUS.post(new RegisterRecipesEvent(RecipeHelper.getRecipeManager()));
        }
    }
}
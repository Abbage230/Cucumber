package com.blakebr0.cucumber.client.handler;

import com.blakebr0.cucumber.config.ModConfigs;
import com.blakebr0.cucumber.lib.Tooltips;
import com.blakebr0.cucumber.util.Localizable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

public final class TagTooltipHandler {
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onItemTooltip(ItemTooltipEvent event) {
        if (!ModConfigs.ENABLE_TAG_TOOLTIPS.get())
            return;

        if (Minecraft.getInstance().options.advancedItemTooltips) {
            var stack = event.getItemStack();
            var block = Block.byItem(stack.getItem());

            var blockTags = block == Blocks.AIR ? List.of() : block.defaultBlockState().getTags()
                    .map(TagKey::location)
                    .toList();
            var itemTags = stack.getTags()
                    .map(TagKey::location)
                    .toList();

            if (!blockTags.isEmpty() || !itemTags.isEmpty()) {
                var tooltip = event.getToolTip();

                if (Screen.hasControlDown()) {
                    if (!blockTags.isEmpty()) {
                        tooltip.add(Tooltips.BLOCK_TAGS.build());
                        blockTags.stream()
                                .map(Object::toString)
                                .map(s -> "  " + s)
                                .map(t -> Localizable.of(t).color(ChatFormatting.DARK_GRAY).build())
                                .forEach(tooltip::add);
                    }

                    if (!itemTags.isEmpty()) {
                        tooltip.add(Tooltips.ITEM_TAGS.build());
                        itemTags.stream()
                                .map(Object::toString)
                                .map(s -> "  " + s)
                                .map(t -> Localizable.of(t).color(ChatFormatting.DARK_GRAY).build())
                                .forEach(tooltip::add);
                    }
                } else {
                    tooltip.add(Tooltips.HOLD_CTRL_FOR_TAGS.build());
                }
            }
        }
    }
}

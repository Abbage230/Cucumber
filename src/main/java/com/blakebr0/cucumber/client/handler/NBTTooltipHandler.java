package com.blakebr0.cucumber.client.handler;

import com.blakebr0.cucumber.config.ModConfigs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

// TODO: 1.21 convert to item component tooltip
public final class NBTTooltipHandler {
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onItemTooltip(ItemTooltipEvent event) {
        if (!ModConfigs.ENABLE_NBT_TOOLTIPS.get())
            return;

        if (Minecraft.getInstance().options.advancedItemTooltips) {
            var stack = event.getItemStack();
//            var tag = stack.getTag();
//
//            if (tag != null) {
//                var tooltip = event.getToolTip();
//
//                if (Screen.hasAltDown()) {
//                    try {
//                        var text = JsonParser.parseString(tag.getAsString());
//                        var json = GSON.toJson(text);
//
//                        for (var line : json.split("\n")) {
//                            tooltip.add(Component.literal(Colors.DARK_GRAY + line));
//                        }
//                    } catch (JsonParseException e) {
//                        tooltip.add(Tooltips.FAILED_TO_LOAD_NBT.build());
//                    }
//                } else {
//                    tooltip.add(Tooltips.HOLD_ALT_FOR_NBT.build());
//                }
//            }
        }
    }
}

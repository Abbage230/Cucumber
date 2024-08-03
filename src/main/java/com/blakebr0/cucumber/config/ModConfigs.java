package com.blakebr0.cucumber.config;

import com.google.common.collect.Lists;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class ModConfigs {
    public static final ModConfigSpec CLIENT;
    public static final ModConfigSpec COMMON;

    public static final ModConfigSpec.BooleanValue ENABLE_TAG_TOOLTIPS;
    public static final ModConfigSpec.BooleanValue ENABLE_NBT_TOOLTIPS;

    // Client
    static {
        final var client = new ModConfigSpec.Builder();

        client.comment("General configuration options.").push("General");
        ENABLE_TAG_TOOLTIPS = client
                .comment("Enable tag list tooltips for blocks/items?")
                .define("tagTooltips", true);
        ENABLE_NBT_TOOLTIPS = client
                .comment("Enable NBT tooltips for items?")
                .define("nbtTooltips", false);
        client.pop();

        CLIENT = client.build();
    }

    public static final ModConfigSpec.ConfigValue<List<String>> MOD_TAG_PRIORITIES;
    public static final ModConfigSpec.BooleanValue AUTO_REFRESH_TAG_ENTRIES;

    // Common
    static {
        final var common = new ModConfigSpec.Builder();

        common.comment("General configuration options.").push("General");
        MOD_TAG_PRIORITIES = common
                .comment("Mod ids (in order) to prioritize using items for when generating the cucumber-tags.json file.")
                .define("modTagPriorities", Lists.newArrayList("thermal", "mekanism", "tconstruct", "immersiveengineering", "appliedenergistics2"));
        AUTO_REFRESH_TAG_ENTRIES = common
                .comment("If enabled, any tags in the cucumber-tags.json file set to items that don't exist will be refreshed if possible.")
                .define("autoRefreshTagOptions", true);
        common.pop();

        COMMON = common.build();
    }
}

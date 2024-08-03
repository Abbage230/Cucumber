package com.blakebr0.cucumber.config;

import com.blakebr0.cucumber.Cucumber;
import com.blakebr0.cucumber.util.FeatureFlag;
import com.blakebr0.cucumber.util.FeatureFlags;

@FeatureFlags
public final class ModFeatureFlags {
    public static final FeatureFlag NBT_TOOLTIPS = FeatureFlag.create(Cucumber.resource("nbt_tooltips"), ModConfigs.ENABLE_NBT_TOOLTIPS);
    public static final FeatureFlag TAG_TOOLTIPS = FeatureFlag.create(Cucumber.resource("tag_tooltips"), ModConfigs.ENABLE_TAG_TOOLTIPS);
}

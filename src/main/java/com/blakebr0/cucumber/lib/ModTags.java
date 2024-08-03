package com.blakebr0.cucumber.lib;

import com.blakebr0.cucumber.Cucumber;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class ModTags {
    public static final TagKey<Block> MINEABLE_WITH_PAXEL = BlockTags.create(Cucumber.resource("mineable/paxel"));
    public static final TagKey<Block> MINEABLE_WITH_SICKLE = BlockTags.create(Cucumber.resource("mineable/sickle"));
}

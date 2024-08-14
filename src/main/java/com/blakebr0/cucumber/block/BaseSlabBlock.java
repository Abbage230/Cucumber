package com.blakebr0.cucumber.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;

import java.util.function.Supplier;

public class BaseSlabBlock extends SlabBlock {
    public BaseSlabBlock(Supplier<Block> block) {
        this(Properties.ofFullCopy(block.get()));
    }

    public BaseSlabBlock(Properties properties) {
        super(properties);
    }

    public BaseSlabBlock(SoundType sound, float hardness, float resistance) {
        this(Properties.of()
                .sound(sound)
                .strength(hardness, resistance)
        );
    }

    public BaseSlabBlock(SoundType sound, float hardness, float resistance, boolean tool) {
        this(Properties.of()
                .sound(sound)
                .strength(hardness, resistance)
                .requiresCorrectToolForDrops()
        );
    }
}

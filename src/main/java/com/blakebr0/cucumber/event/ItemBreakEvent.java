package com.blakebr0.cucumber.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import org.jetbrains.annotations.Nullable;

public class ItemBreakEvent extends Event {
    private final ItemStack stack;
    private final int amount;
    private final ServerLevel level;
    private final LivingEntity entity;

    public ItemBreakEvent(ItemStack stack, int amount, ServerLevel level, @Nullable LivingEntity entity) {
        this.stack = stack;
        this.amount = amount;
        this.level = level;
        this.entity = entity;
    }

    /**
     * @return a COPY of the ItemStack before it is destroyed
     */
    public ItemStack getItemStack() {
        return this.stack;
    }

    public Item getItem() {
        return this.stack.getItem();
    }

    public int getAmount() {
        return this.amount;
    }

    public ServerLevel getLevel() {
        return this.level;
    }

    public LivingEntity getEntity() {
        return this.entity;
    }
}

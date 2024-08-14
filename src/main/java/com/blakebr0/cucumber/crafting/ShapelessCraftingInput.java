package com.blakebr0.cucumber.crafting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;

import java.util.List;

public class ShapelessCraftingInput extends CraftingInput {
    public ShapelessCraftingInput(List<ItemStack> items) {
        super(items.size(), 1, items);
    }
}

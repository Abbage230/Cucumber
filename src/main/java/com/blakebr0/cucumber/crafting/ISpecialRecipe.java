package com.blakebr0.cucumber.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public interface ISpecialRecipe extends Recipe<RecipeInput> {
    default ItemStack assemble(IItemHandler inventory, HolderLookup.Provider lookup) {
        return this.assemble(new RecipeWrapper(inventory), lookup);
    }

    default boolean matches(IItemHandler inventory) {
        return this.matches(inventory, 0, inventory.getSlots());
    }

    default boolean matches(IItemHandler inventory, int startIndex, int endIndex) {
        NonNullList<ItemStack> inputs = NonNullList.create();

        for (var i = startIndex; i < endIndex; i++) {
            inputs.add(inventory.getStackInSlot(i));
        }

        return RecipeMatcher.findMatches(inputs, this.getIngredients()) != null;
    }

    default NonNullList<ItemStack> getRemainingItems(IItemHandler inventory) {
        return this.getRemainingItems(new RecipeWrapper(inventory));
    }
}

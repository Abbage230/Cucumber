package com.blakebr0.cucumber.inventory.slot;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class SingleSlot extends SlotItemHandler {
	public SingleSlot(IItemHandler inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}

	@Override
	public int getMaxStackSize(ItemStack stack) {
		return 1;
	}
}

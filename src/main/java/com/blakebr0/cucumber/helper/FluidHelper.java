package com.blakebr0.cucumber.helper;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;

public final class FluidHelper {
	public static FluidStack getFluidFromStack(ItemStack stack) {
		var handler = stack.getCapability(Capabilities.FluidHandler.ITEM);
		return handler == null ? FluidStack.EMPTY : handler.getFluidInTank(0);
	}

	public static Rarity getFluidRarity(FluidStack fluid) {
		return fluid.getFluid().getFluidType().getRarity();
	}

	public static int getFluidAmount(ItemStack stack) {
		var fluid = getFluidFromStack(stack);
		return fluid == null ? 0 : fluid.getAmount();
	}

	public static ItemStack getFilledBucket(FluidStack fluid, Item bucket, int capacity) {
		if (BuiltInRegistries.FLUID.containsValue(fluid.getFluid())) {
			var filledBucket = new ItemStack(bucket);
			var fluidContents = fluid.copyWithAmount(capacity);

			var tag = new CompoundTag();

			// TODO: 1.21 how do I do this now?
//			fluidContents.writeToNBT(tag);
//			filledBucket.setTag(tag);

			return filledBucket;
		}

		return ItemStack.EMPTY;
	}

	public static int toBuckets(int i) {
		return i - (i % 1000);
	}
}

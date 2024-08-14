package com.blakebr0.cucumber.helper;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public final class FluidHelper {
	public static FluidStack getFluidFromStack(ItemStack stack) {
		var handler = stack.getCapability(Capabilities.FluidHandler.ITEM);
		return handler == null ? FluidStack.EMPTY : handler.getFluidInTank(0);
	}

	public static int getFluidAmount(ItemStack stack) {
		var fluid = getFluidFromStack(stack);
		return fluid == null ? 0 : fluid.getAmount();
	}

	public static ItemStack getFilledBucket(FluidStack fluid, Item bucket, int capacity) {
		if (BuiltInRegistries.FLUID.containsValue(fluid.getFluid())) {
			var filledBucket = new ItemStack(bucket);
			var fluidContents = fluid.copyWithAmount(capacity);

			var tank = filledBucket.getCapability(Capabilities.FluidHandler.ITEM);
			if (tank != null) {
				tank.fill(fluidContents, IFluidHandler.FluidAction.EXECUTE);
			}

			return filledBucket;
		}

		return ItemStack.EMPTY;
	}

	public static int toBuckets(int i) {
		return i - (i % 1000);
	}
}

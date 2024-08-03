package com.blakebr0.cucumber.item;

import com.blakebr0.cucumber.lib.Tooltips;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.function.Function;

public class BaseReusableItem extends BaseItem {
	private final boolean damage;
	private final boolean tooltip;

	public BaseReusableItem(int uses) {
		this(uses, p -> p);
	}

	public BaseReusableItem(Function<Properties, Properties> properties) {
		this(true, properties);
	}

	public BaseReusableItem(boolean tooltip, Function<Properties, Properties> properties) {
		this(0, tooltip, properties);
	}

	public BaseReusableItem(int uses, Function<Properties, Properties> properties) {
		this(uses, true, properties);
	}

	public BaseReusableItem(int uses, boolean tooltip, Function<Properties, Properties> properties) {
		super(properties.compose(p -> p.durability(Math.max(uses - 1, 0)).setNoRepair()));
		this.damage = uses > 0;
		this.tooltip = tooltip;
	}
	
	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return true;
	}
	
	@Override
	public ItemStack getCraftingRemainingItem(ItemStack stack) {
		var copy = stack.copy();

		copy.setCount(1);

		if (!this.damage)
			return copy;

		// TODO: 1.21 figure out how to handle unbreaking
//		var unbreaking = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.UNBREAKING, stack);
//
//		for (var i = 0; i < unbreaking; i++) {
//			if (Enchantments.shouldIgnoreDurabilityDrop(stack, unbreaking, Utils.RANDOM))
//				return copy;
//		}

		copy.setDamageValue(stack.getDamageValue() + 1);

		if (copy.getDamageValue() > stack.getMaxDamage())
			return ItemStack.EMPTY;

		return copy;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		if (this.tooltip) {
			if (this.damage) {
				var damage = stack.getMaxDamage() - stack.getDamageValue() + 1;

				if (damage == 1) {
					tooltip.add(Tooltips.ONE_USE_LEFT.build());
				} else {
					tooltip.add(Tooltips.USES_LEFT.args(damage).build());
				}
			} else {
				tooltip.add(Tooltips.UNLIMITED_USES.build());
			}
		}
	}
}

package com.blakebr0.cucumber.item.tool;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

import java.util.function.Function;

public class BaseAxeItem extends AxeItem {
    private final float attackDamage;
    private final float attackSpeed;

    public BaseAxeItem(Tier tier) {
        this(tier, 6.0F, -3.0F, p -> p);
    }

    public BaseAxeItem(Tier tier, Function<Properties, Properties> properties) {
        this(tier, 6.0F, -3.0F, properties);
    }

    public BaseAxeItem(Tier tier, float attackDamage, float attackSpeed, Function<Properties, Properties> properties) {
        super(tier, properties.apply(new Properties().attributes(createAttributes(tier, attackDamage, attackSpeed))));
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage + this.getTier().getAttackDamageBonus();
    }

    public float getAttackSpeed() {
        return this.attackSpeed;
    }
}

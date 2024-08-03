package com.blakebr0.cucumber.item.tool;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import java.util.function.Function;

public class BaseSwordItem extends SwordItem {
    private final float attackDamage;
    private final float attackSpeed;

    public BaseSwordItem(Tier tier) {
        this(tier, 3, -2.4F, p -> p);
    }

    public BaseSwordItem(Tier tier, Function<Properties, Properties> properties) {
        this(tier, 3, -2.4F, properties);
    }

    public BaseSwordItem(Tier tier, int attackDamage, float attackSpeed, Function<Properties, Properties> properties) {
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

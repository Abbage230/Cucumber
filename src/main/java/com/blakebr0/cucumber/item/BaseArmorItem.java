package com.blakebr0.cucumber.item;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

import java.util.function.Function;

public class BaseArmorItem extends ArmorItem {
    @Deprecated(forRemoval = true)
    public BaseArmorItem(Holder<ArmorMaterial> material, Type type) {
        this(material, type, 0);
    }

    public BaseArmorItem(Holder<ArmorMaterial> material, Type type, int maxDamageFactor) {
        super(material, type, new Properties().durability(type.getDurability(maxDamageFactor)));
    }

    public BaseArmorItem(Holder<ArmorMaterial> material, Type type, Function<Properties, Properties> properties) {
        this(material, type, 0, properties);
    }

    public BaseArmorItem(Holder<ArmorMaterial> material, Type type, int maxDamageFactor, Function<Properties, Properties> properties) {
        super(material, type, properties.apply(new Properties().durability(type.getDurability(maxDamageFactor))));
    }
}

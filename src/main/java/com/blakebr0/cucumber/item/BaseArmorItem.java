package com.blakebr0.cucumber.item;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

import java.util.function.Function;

public class BaseArmorItem extends ArmorItem {
    public BaseArmorItem(Holder<ArmorMaterial> material, Type type) {
        super(material, type, new Properties());
    }

    public BaseArmorItem(Holder<ArmorMaterial> material, Type type, Function<Properties, Properties> properties) {
        super(material, type, properties.apply(new Properties()));
    }
}

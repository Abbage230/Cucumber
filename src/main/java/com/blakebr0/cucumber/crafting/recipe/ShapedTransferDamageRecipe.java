package com.blakebr0.cucumber.crafting.recipe;

import com.blakebr0.cucumber.init.ModRecipeSerializers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;

public class ShapedTransferDamageRecipe extends ShapedRecipe {
    private final ItemStack result;
    private final boolean transferComponents;

    public ShapedTransferDamageRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result, boolean showNotification, boolean transferComponents) {
        super(group, category, pattern, result, showNotification);
        this.result = result;
        this.transferComponents = transferComponents;
    }

    @Override
    public ItemStack assemble(CraftingInput inventory, HolderLookup.Provider lookup) {
        var damageable = ItemStack.EMPTY;

        for (var i = 0; i < inventory.size(); i++) {
            var slotStack = inventory.getItem(i);

            if (slotStack.isDamageableItem()) {
                damageable = slotStack;
                break;
            }
        }

        if (damageable.isEmpty())
            return super.assemble(inventory, lookup);

        var result = this.getResultItem(lookup).copy();

        if (this.transferComponents) {
            result.applyComponents(damageable.getComponentsPatch());
        } else {
            result.setDamageValue(damageable.getDamageValue());
        }

        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.CRAFTING_SHAPED_TRANSFER_DAMAGE.get();
    }

    public static class Serializer implements RecipeSerializer<ShapedTransferDamageRecipe> {
        public static final MapCodec<ShapedTransferDamageRecipe> CODEC = RecordCodecBuilder.mapCodec(builder ->
                builder.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(ShapedRecipe::getGroup),
                        CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(ShapedRecipe::category),
                        ShapedRecipePattern.MAP_CODEC.forGetter(recipe -> recipe.pattern),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                        Codec.BOOL.optionalFieldOf("show_notification", Boolean.TRUE).forGetter(ShapedRecipe::showNotification),
                        Codec.BOOL.optionalFieldOf("transfer_nbt", Boolean.FALSE).forGetter(recipe -> recipe.transferComponents)
                ).apply(builder, ShapedTransferDamageRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, ShapedTransferDamageRecipe> STREAM_CODEC = StreamCodec.of(
                ShapedTransferDamageRecipe.Serializer::toNetwork, ShapedTransferDamageRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<ShapedTransferDamageRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ShapedTransferDamageRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static ShapedTransferDamageRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            var group = buffer.readUtf();
            var category = buffer.readEnum(CraftingBookCategory.class);
            var pattern = ShapedRecipePattern.STREAM_CODEC.decode(buffer);
            var result = ItemStack.STREAM_CODEC.decode(buffer);
            var showNotification = buffer.readBoolean();
            var transferComponents = buffer.readBoolean();

            return new ShapedTransferDamageRecipe(group, category, pattern, result, showNotification, transferComponents);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, ShapedTransferDamageRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeEnum(recipe.category());
            ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeBoolean(recipe.showNotification());
            buffer.writeBoolean(recipe.transferComponents);
        }
    }
}

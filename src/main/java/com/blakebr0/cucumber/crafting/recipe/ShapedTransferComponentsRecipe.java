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

public class ShapedTransferComponentsRecipe extends ShapedRecipe {
    private final ItemStack result;
    private final int transferSlot;

    public ShapedTransferComponentsRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result, boolean showNotification, int transferSlot) {
        super(group, category, pattern, result, showNotification);
        this.result = result;
        this.transferSlot = transferSlot;
    }

    @Override
    public ItemStack assemble(CraftingInput inventory, HolderLookup.Provider lookup) {
        var stack = inventory.getItem(this.transferSlot);
        var result = this.getResultItem(lookup).copy();

        result.applyComponents(stack.getComponents());

        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.CRAFTING_SHAPED_TRANSFER_COMPONENTS.get();
    }

    public static class Serializer implements RecipeSerializer<ShapedTransferComponentsRecipe> {
        public static final MapCodec<ShapedTransferComponentsRecipe> CODEC = RecordCodecBuilder.mapCodec(builder ->
                builder.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(ShapedRecipe::getGroup),
                        CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(ShapedRecipe::category),
                        ShapedRecipePattern.MAP_CODEC.forGetter(recipe -> recipe.pattern),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                        Codec.BOOL.optionalFieldOf("show_notification", Boolean.TRUE).forGetter(ShapedRecipe::showNotification),
                        Codec.INT.fieldOf("transfer_slot").forGetter(recipe -> recipe.transferSlot)
                ).apply(builder, ShapedTransferComponentsRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, ShapedTransferComponentsRecipe> STREAM_CODEC = StreamCodec.of(
                ShapedTransferComponentsRecipe.Serializer::toNetwork, ShapedTransferComponentsRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<ShapedTransferComponentsRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ShapedTransferComponentsRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static ShapedTransferComponentsRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            var group = buffer.readUtf();
            var category = buffer.readEnum(CraftingBookCategory.class);
            var pattern = ShapedRecipePattern.STREAM_CODEC.decode(buffer);
            var result = ItemStack.STREAM_CODEC.decode(buffer);
            var showNotification = buffer.readBoolean();
            var transferSlot = buffer.readVarInt();

            return new ShapedTransferComponentsRecipe(group, category, pattern, result, showNotification, transferSlot);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, ShapedTransferComponentsRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeEnum(recipe.category());
            ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeBoolean(recipe.showNotification());
            buffer.writeVarInt(recipe.transferSlot);
        }
    }
}

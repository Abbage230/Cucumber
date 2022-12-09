package com.blakebr0.cucumber.item.tool;

import com.blakebr0.cucumber.helper.BlockHelper;
import com.blakebr0.cucumber.lib.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeHooks;

import java.util.function.Function;

public class BaseSickleItem extends DiggerItem {
    private final float attackDamage;
    private final float attackSpeed;
    private final int range;

    public BaseSickleItem(Tier tier, int range) {
        this(tier, range, p -> p);
    }

    public BaseSickleItem(Tier tier, int range, Function<Properties, Properties> properties) {
        super(4.0F, -3.0F, tier, ModTags.MINEABLE_WITH_SICKLE, properties.apply(new Properties()));
        this.attackDamage = 4.0F;
        this.attackSpeed = -3.0F;
        this.range = range;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return isValidMaterial(state) ? this.getTier().getSpeed() / 2 : super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return isValidMaterial(state);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, Player player) {
        return !this.harvest(stack, player.level, pos, player);
    }

    public float getAttackDamage() {
        return this.attackDamage + this.getTier().getAttackDamageBonus();
    }

    public float getAttackSpeed() {
        return this.attackSpeed;
    }

    private boolean harvest(ItemStack stack, Level level, BlockPos pos, Player player) {
        var state = level.getBlockState(pos);
        var hardness = state.getDestroySpeed(level, pos);

        if (!this.tryHarvest(level, pos, false, stack, player) || !isValidMaterial(state))
            return false;

        if (this.range > 0) {
            BlockPos.betweenClosed(pos.offset(-this.range, -this.range, -this.range), pos.offset(this.range, this.range, this.range)).forEach(aoePos -> {
                if (stack.isEmpty())
                    return;

                if (aoePos != pos) {
                    var aoeState = level.getBlockState(aoePos);

                    if (!isValidMaterial(aoeState))
                        return;

                    var aoeHardness = aoeState.getDestroySpeed(level, aoePos);

                    if (aoeHardness > hardness + 5.0F)
                        return;

                    var harvested = this.tryHarvest(level, aoePos, true, stack, player);

                    if (harvested && !player.getAbilities().instabuild && aoeHardness <= 0.0F && Math.random() < 0.33) {
                        stack.hurtAndBreak(1, player, entity -> {
                            entity.broadcastBreakEvent(player.getUsedItemHand());
                        });
                    }
                }
            });
        }

        return true;
    }

    private boolean tryHarvest(Level level, BlockPos pos, boolean extra, ItemStack stack, Player player) {
        var state = level.getBlockState(pos);
        var hardness = state.getDestroySpeed(level, pos);
        var harvest = !extra || (ForgeHooks.isCorrectToolForDrops(state, player) || this.isCorrectToolForDrops(stack, state));

        if (hardness >= 0.0F && harvest)
            return BlockHelper.breakBlocksAOE(stack, level, player, pos, !extra);

        return false;
    }

    private static boolean isValidMaterial(BlockState state) {
        var material = state.getMaterial();
        return state.is(ModTags.MINEABLE_WITH_SICKLE) || material == Material.LEAVES || material == Material.PLANT || material == Material.REPLACEABLE_PLANT;
    }
}

package net.jirniy.jirbiomes.item.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;

public class PlaceWaterloggedBucketItem extends SolidBucketItem {
    final Block block;

    public PlaceWaterloggedBucketItem(Block content, SoundEvent placeSound, Properties properties) {
        super(content, placeSound, properties);
        this.block = content;
    }

    @Override
    public InteractionResult useOn(final UseOnContext context) {
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult use(final Level level, final Player player, final InteractionHand hand) {
        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        BlockHitResult blockResult = hitResult.withPosition(hitResult.getBlockPos());
        if (level.getBlockState(blockResult.getBlockPos()).is(this.block) || level.getBlockState(blockResult.getBlockPos()).isAir()) {
            return InteractionResult.FAIL;
        }
        return super.useOn(new UseOnContext(player, hand, blockResult));
    }
}

package net.jirniy.jirbiomes.mixin;

import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.SpeleothemBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpeleothemBlock.class)
public class SpeleothemMixin {
    @Shadow protected static boolean isStalactite(BlockState fallState) {return true;}
    @Shadow private static boolean isTip(BlockState fallState, boolean b) {return true;}

    @Inject(method = "spawnFallingStalactite", at = @At(value = "HEAD"), cancellable = true)
    private static void fallingStalactite(BlockState state, ServerLevel level, BlockPos pos, CallbackInfo ci) {
        if (state.is(ModTags.Blocks.FALLING_STALACTITE_NO_DROP)) {
            BlockPos.MutableBlockPos fallPos = pos.mutable();

            for(BlockState fallState = state; isStalactite(fallState); fallState = level.getBlockState(fallPos)) {
                FallingBlockEntity entity = FallingBlockEntity.fall(level, fallPos, fallState);
                if (isTip(fallState, true)) {
                    int size = Math.max(1 + pos.getY() - fallPos.getY(), 6);
                    float damagePerFallDistance = 1.0F * (float)size;
                    entity.setHurtsEntities(damagePerFallDistance, 40);
                    entity.disableDrop();
                    break;
                }
                entity.disableDrop();
                fallPos.move(Direction.DOWN);
            }

            ci.cancel();
        }
    }
}

package net.jirniy.jirbiomes.mixin;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowLayerBlock.class)
public class SnowLayerMixin {
    @Inject(method = "canBeReplaced", at = @At(value = "HEAD"), cancellable = true)
    private static void canBeReplaced(BlockState state, BlockPlaceContext context, CallbackInfoReturnable<Boolean> cir) {
        if (context.getItemInHand().is(ModBlocks.SNOWY_BUSH.asItem()) && state.getValue(SnowLayerBlock.LAYERS) < 8) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}

package net.jirniy.jirbiomes.mixin;

import net.jirniy.jirbiomes.block.custom.SnowloggableVegetationBlock;
import net.minecraft.world.level.block.SnowyBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowyBlock .class)
public class SnowyBlockMixin {
    @Inject(method = "isSnowySetting", at = @At(value = "HEAD"), cancellable = true)
    private static void isSnowySetting(BlockState aboveState, CallbackInfoReturnable<Boolean> cir) {
        if (aboveState.getValueOrElse(SnowloggableVegetationBlock.SNOWLOGGED_LAYERS, 0) > 0) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}

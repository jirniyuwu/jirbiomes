package net.jirniy.jirbiomes.mixin;

import net.jirniy.jirbiomes.block.fluid.TarrFluid;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FogRenderer.class)
public class FluidFogRendererMixin {
    @Shadow @Final private static List<FogEnvironment> FOG_ENVIRONMENTS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void registerCustomFogEnvironment(CallbackInfo ci) {
        FOG_ENVIRONMENTS.addFirst(new TarrFluid.TarrFogEnvironment());
    }
}

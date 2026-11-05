package net.jirniy.jirbiomes.mixin;

import net.jirniy.jirbiomes.misc.ModTags;
import net.jirniy.jirbiomes.particle.ModParticles;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityFluidInteraction;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Entity.class)
public abstract class EntityFluidInteractionMixin {
    @Final @Shadow private EntityFluidInteraction fluidInteraction;
    @Shadow private EntityDimensions dimensions;
    @Final @Shadow protected RandomSource random;
    @Shadow protected boolean firstTick;

    @Unique protected boolean wasTouchingTarr = false;

    @Inject(method = "updateFluidInteraction", at = @At("TAIL"), cancellable = true)
    private void fluidInteraction(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity) (Object) this;
        boolean inTarr = this.fluidInteraction.isInFluid(ModTags.Fluids.TARR);

        if (inTarr && !entity.isSpectator()) {
            entity.resetFallDistance();
            if (!this.wasTouchingTarr && !this.firstTick) {
                this.doTarrSplashEffect(entity);
            }
        }

        this.wasTouchingTarr = inTarr;
        cir.setReturnValue(cir.getReturnValue() || inTarr);
    }

    @Unique
    protected void doTarrSplashEffect(Entity thisEntity) {
        Entity entity = Objects.requireNonNullElse(thisEntity.getControllingPassenger(), thisEntity);
        float volumeModifier = entity == thisEntity ? 0.2F : 0.9F;
        Vec3 movement = entity.getDeltaMovement();
        float speed = Math.min(1.0F, (float)Math.sqrt(movement.x * movement.x * 0.2F + movement.y * movement.y + movement.z * movement.z * 0.2F) * volumeModifier);
        if (speed < 0.25F) {
            thisEntity.playSound(SoundEvents.GENERIC_SPLASH, speed, 0.6F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
        } else {
            thisEntity.playSound(SoundEvents.GENERIC_SPLASH, speed, 0.6F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
        }

        float yt = Mth.floor(thisEntity.getY());

        for (int i = 0; i < 1.0F + this.dimensions.width() * 10.0F; i++) {
            double xo = (this.random.nextDouble() * 2.0 - 1.0) * this.dimensions.width();
            double zo = (this.random.nextDouble() * 2.0 - 1.0) * this.dimensions.width();
            thisEntity.level()
                    .addParticle(ModParticles.TARR_BUBBLE, thisEntity.getX() + xo, yt + this.random.nextDouble(), thisEntity.getZ() + zo,
                            movement.x, movement.y - this.random.nextDouble() * 0.4F, movement.z);
        }

        for (int i = 0; i < 1.0F + this.dimensions.width() * 2.0F; i++) {
            double xo = (this.random.nextDouble() * 2.0 - 1.0) * this.dimensions.width();
            double zo = (this.random.nextDouble() * 2.0 - 1.0) * this.dimensions.width();
            thisEntity.level().addParticle(ModParticles.TARR_BUBBLE_POP, thisEntity.getX() + xo, yt + 1.0F, thisEntity.getZ() + zo,
                    movement.x * 0.4, movement.y * 0.02, movement.z * 0.4);
        }

        thisEntity.gameEvent(GameEvent.SPLASH);
    }
}

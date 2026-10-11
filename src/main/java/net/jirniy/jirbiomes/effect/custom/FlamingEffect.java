package net.jirniy.jirbiomes.effect.custom;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FlamingEffect extends MobEffect {
    public FlamingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    public FlamingEffect(MobEffectCategory category, int color, ParticleOptions particleOptions) {
        super(category, color, particleOptions);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        if (!mob.fireImmune()) {
            if (mob.getRemainingFireTicks() < 40 * amplification) {
                mob.setRemainingFireTicks(40 * amplification);
            }
            mob.setSharedFlagOnFire(true);
        }
        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }
}

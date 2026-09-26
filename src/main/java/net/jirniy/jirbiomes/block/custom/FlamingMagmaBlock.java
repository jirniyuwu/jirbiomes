package net.jirniy.jirbiomes.block.custom;

import net.jirniy.jirbiomes.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class FlamingMagmaBlock extends MagmaLikeBlock {
    public FlamingMagmaBlock(Block onContactWater, Properties properties) {
        super(onContactWater, properties);
    }

    @Override
    public void stepOn(final Level level, final BlockPos pos, final BlockState onState, final Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && !entity.fireImmune()) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(ModEffects.FLAMING, 20, 0));
        }

        super.stepOn(level, pos, onState, entity);
    }
}

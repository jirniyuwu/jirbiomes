package net.jirniy.jirbiomes.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpeleothemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SpeleothemThickness;
import net.minecraft.world.phys.Vec3;

public class IcicleBlock extends SpeleothemBlock {
    public static final MapCodec<IcicleBlock> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(BlockState.CODEC.fieldOf("block_to_grow_on").forGetter((b) -> b.blockToGrowOn), propertiesCodec()).apply(i, IcicleBlock::new));

    public IcicleBlock(BlockState blockToGrowOn, Properties properties) {
        super(blockToGrowOn, properties);
    }

    @Override
    public void animateTick(final BlockState state, final Level level, final BlockPos pos, final RandomSource random) {
        if (isFreeHangingStalactite(state)) {
            if (random.nextFloat() < 0.02F) {
                Vec3 offset = state.getOffset(pos);
                level.addParticle(ParticleTypes.DRIPPING_DRIPSTONE_WATER,
                        pos.getX() + 0.5f + offset.x, pos.getY() + SHAPE_TIP_DOWN.min(Direction.Axis.Y) - 1/16f, pos.getZ() + 0.5f + offset.z,
                        0.0F, 0.0F, 0.0F);
            }
        }
    }

    @Override
    public void fallOn(final Level level, final BlockState state, final BlockPos pos, final Entity entity, final double fallDistance) {
        if (state.getValue(TIP_DIRECTION) == Direction.UP && state.getValue(THICKNESS) == SpeleothemThickness.TIP) {
            entity.causeFallDamage(fallDistance + (double)1.5F, 1.5F, level.damageSources().stalagmite());
        } else {
            super.fallOn(level, state, pos, entity, fallDistance);
        }
    }

    @Override
    public MapCodec<? extends SpeleothemBlock> codec() {
        return CODEC;
    }

    @Override
    protected int getStalactiteLandingSound() {
        return 1045;
    }
}

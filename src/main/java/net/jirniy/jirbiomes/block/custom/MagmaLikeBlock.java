package net.jirniy.jirbiomes.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jirniy.jirbiomes.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.Nullable;

public class MagmaLikeBlock extends Block {
    public static final MapCodec<MagmaLikeBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance
                    .group(Block.CODEC.fieldOf("on_contact_water").forGetter(block -> block.waterTurningInto), propertiesCodec())
                    .apply(instance, MagmaLikeBlock::new)
    );
    @Override
    public MapCodec<MagmaLikeBlock> codec() {
        return CODEC;
    }
    private final Block waterTurningInto;

    public MagmaLikeBlock(Block onContactWater, Properties properties) {
        super(properties);
        this.waterTurningInto = onContactWater;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        extinguish(level, pos);
        super.onPlace(state, level, pos, oldState, movedByPiston);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        extinguish(level, pos);
        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
    }

    @Override
    public void stepOn(final Level level, final BlockPos pos, final BlockState onState, final Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity) {
            entity.hurt(level.damageSources().hotFloor(), 1.5F);
            if (!entity.fireImmune()) {
                ((LivingEntity) entity).addEffect(new MobEffectInstance(ModEffects.FLAMING, 20, 0));
            }
        }

        super.stepOn(level, pos, onState, entity);
    }

    protected void extinguish(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (level instanceof ServerLevel serverLevel && level.getFluidState(pos.relative(direction)).is(FluidTags.WATER)) {
                serverLevel.setBlockAndUpdate(pos, waterTurningInto.defaultBlockState());
                serverLevel.sendParticles(ParticleTypes.CLOUD, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f,
                        5, 0.2f, 0.2f, 0.2f, 0.02f);
                serverLevel.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS);
            }
        }
    }
}

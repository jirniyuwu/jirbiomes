package net.jirniy.jirbiomes.block.fluid;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.block.ModFluids;
import net.jirniy.jirbiomes.item.ModItems;
import net.jirniy.jirbiomes.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Optional;

public abstract class TarrFluid extends FlowingFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_TARR;
    }

    @Override
    public Fluid getSource() {
        return ModFluids.TARR;
    }

    @Override
    public Item getBucket() {
        return ModItems.TARR_BUCKET;
    }

    @Override
    protected void randomTick(ServerLevel level, BlockPos pos, FluidState fluidState, RandomSource random) {
        if (fluidState.isSource() && level.getBlockState(pos.above()).is(BlockTags.AIR)) {
            ArrayList<Direction> neighbors = new ArrayList<>();
            for (Direction direction : Direction.values()) {
                if (!level.getFluidState(pos.relative(direction)).is(this)) {
                    neighbors.add(direction);
                }
            }

            if (random.nextFloat() < 0.02f * (neighbors.size() - 1)) {
                level.setBlockAndUpdate(pos, ModBlocks.SOLID_TARR.defaultBlockState());
            }
        }
        super.randomTick(level, pos, fluidState, random);
    }

    @Override
    protected void entityInside(final Level level, final BlockPos pos, final Entity entity, final InsideBlockEffectApplier effectApplier) {
        effectApplier.apply(InsideBlockEffectType.EXTINGUISH);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 10));
        }
    }

    @Override
    public @Nullable ParticleOptions getDripParticle() {
        return ModParticles.DRIPPING_TARR;
    }

    @Override
    protected void beforeDestroyingBlock(final LevelAccessor level, final BlockPos pos, final BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, blockEntity);
    }

    @Override
    public int getSlopeFindDistance(final LevelReader level) {
        return 2;
    }

    @Override
    public BlockState createLegacyBlock(final FluidState fluidState) {
        return ModBlocks.TARR.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(fluidState));
    }

    @Override
    public boolean isSame(final Fluid other) {
        return other == ModFluids.TARR || other == ModFluids.FLOWING_TARR;
    }

    @Override
    public int getDropOff(final LevelReader level) {
        return 2;
    }

    @Override
    public boolean canBeReplacedWith(final FluidState state, final BlockGetter level, final BlockPos pos, final Fluid other, final Direction direction) {
        return state.getHeight(level, pos) >= 0.44444445F && other.is(FluidTags.WATER);
    }

    @Override
    public int getTickDelay(final LevelReader level) {
        return 30;
    }

    @Override
    public int getSpreadDelay(final Level level, final BlockPos pos, final FluidState oldFluidState, final FluidState newFluidState) {
        int result = this.getTickDelay(level);
        if (!oldFluidState.isEmpty()
                && !newFluidState.isEmpty()
                && !oldFluidState.getValue(FALLING)
                && !newFluidState.getValue(FALLING)
                && newFluidState.getHeight(level, pos) > oldFluidState.getHeight(level, pos)
                && level.getRandom().nextInt(4) != 0) {
            result *= 4;
        }

        return result;
    }

    @Override
    protected boolean canConvertToSource(final ServerLevel level) {
        return false;
    }

    @Override
    protected void spreadTo(final LevelAccessor level, final BlockPos pos, final BlockState state, final Direction direction, final FluidState target) {
        if (direction == Direction.DOWN) {
            FluidState fluidState = level.getFluidState(pos);
            if (fluidState.is(FluidTags.LAVA) || fluidState.is(FluidTags.WATER)) {
                if (state.getBlock() instanceof LiquidBlock) {
                    level.setBlock(pos, ModBlocks.SOLID_TARR.defaultBlockState(), 3);
                }

                return;
            }
        }

        super.spreadTo(level, pos, state, direction, target);
    }

    @Override
    protected boolean isRandomlyTicking() {
        return true;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL_SULFUR_CUBE);
    }

    public static class Flowing extends TarrFluid {
        @Override
        protected void createFluidStateDefinition(final StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(final FluidState fluidState) {
            return fluidState.getValue(LEVEL);
        }

        @Override
        public boolean isSource(final FluidState fluidState) {
            return false;
        }
    }

    public static class Source extends TarrFluid {
        @Override
        public int getAmount(final FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isSource(final FluidState fluidState) {
            return true;
        }
    }
}

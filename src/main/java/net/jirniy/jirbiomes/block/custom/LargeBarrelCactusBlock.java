package net.jirniy.jirbiomes.block.custom;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.item.ModItems;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class LargeBarrelCactusBlock extends Block implements BonemealableBlock {
    private static final VoxelShape SHAPE = Block.column(14, 0, 16);

    public LargeBarrelCactusBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < getGrowthChance(0.08f, 3, level, pos)
                && level.getRawBrightness(pos, 0) >= 9) {
            if (!level.getBlockState(pos.below().below()).is(ModBlocks.LARGE_BARREL_CACTUS)) {
                grow(state, level, pos, random);
            }
        }
        super.randomTick(state, level, pos, random);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!canSurvive(state, level, pos)) {
            level.destroyBlock(pos, true, null, 16);
        }
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (!level.getFluidState(pos.relative(direction)).isEmpty()) {
                return false;
            }
        }

        return level.getBlockState(pos.below()).is(ModTags.Blocks.SUPPORTS_BARREL_CACTUS)
                && level.getFluidState(pos.above()).isEmpty();
    }

    @Override
    public Item asItem() {
        return ModItems.BARREL_CACTUS;
    }

    @Override
    protected void entityInside(final BlockState state, final Level level, final BlockPos pos, final Entity entity, final InsideBlockEffectApplier effectApplier, final boolean isPrecise) {
        if (!(entity instanceof ItemEntity)) {
            entity.hurt(level.damageSources().cactus(), 1.0F);
        }
        Vec3 speedMultiplier = new Vec3(0.9, 0.9, 0.9);
        entity.makeStuckInBlock(state, speedMultiplier);
    }

    @Override
    protected boolean isPathfindable(final BlockState state, final PathComputationType type) {
        return false;
    }

    private void grow(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getBlockState(pos.above()).isAir()) {
            level.setBlockAndUpdate(pos.above(), ModBlocks.PRICKLY_PEAR_SEED.defaultBlockState());
        }
    }

    public static float getGrowthChance(float baseChance, int maxSteps, ServerLevel level, BlockPos pos) {
        for (int i = 0; i < maxSteps; i++) {
            if (level.getBlockState(pos.below(i + 1)).is(ModTags.Blocks.BARREL_CACTUS_FAST_GROWTH)) {
                return baseChance*2;
            } else if (level.getBlockState(pos.below(i + 1)).is(ModBlocks.LARGE_BARREL_CACTUS)) {
                continue;
            } else {
                return baseChance;
            }
        }
        return baseChance;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.above()).isAir() && level.isInsideBuildHeight(pos.above());
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        grow(state, level, pos, random);
    }
}

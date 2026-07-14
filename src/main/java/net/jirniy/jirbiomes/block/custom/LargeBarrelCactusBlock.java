package net.jirniy.jirbiomes.block.custom;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.item.ModItems;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SoulSandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
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
        if (random.nextFloat() < 0.05f && level.getRawBrightness(pos, 0) >= 9) {
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
        return level.getBlockState(pos.below()).is(ModTags.Blocks.SUPPORTS_LARGE_BARREL_CACTUS)
                || level.isWaterAt(pos.east()) || level.isWaterAt(pos.west()) || level.isWaterAt(pos.north()) || level.isWaterAt(pos.south());
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
            if (random.nextFloat() < 0.8f) {
                level.setBlockAndUpdate(pos.above(), ModBlocks.SMALL_BARREL_CACTUS.defaultBlockState());
            } else {
                level.setBlockAndUpdate(pos.above(), Blocks.CACTUS_FLOWER.defaultBlockState());
            }
        }
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

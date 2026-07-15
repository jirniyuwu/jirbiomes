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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class BarrelCactusSeedBlock extends Block implements BonemealableBlock {
    private static final VoxelShape SHAPE = Block.column(6, 0, 7);

    public BarrelCactusSeedBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < LargeBarrelCactusBlock.getGrowthChance(0.05f, 3, level, pos)
                && level.getRawBrightness(pos, 0) >= 9) {
            grow(state, level, pos, random, 1);
        }
        super.randomTick(state, level, pos, random);
    }

    @Override
    public Item asItem() {
        return ModItems.PRICKLY_PEAR;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!canSurvive(state, level, pos)) {
            level.destroyBlock(pos, true, null, 16);
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (!level.getFluidState(pos.relative(direction)).isEmpty()) {
                return false;
            }
        }

        return (level.getBlockState(pos.below()).is(ModTags.Blocks.SUPPORTS_BARREL_CACTUS) ||
                level.getBlockState(pos.below()).is(ModTags.Blocks.CACTUS_SEED_FLOWER_OVERRIDE))
                && level.getFluidState(pos.above()).isEmpty();
    }

    private void grow(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, int amount) {
        if (level.getBlockState(pos.below()).is(ModTags.Blocks.CACTUS_SEED_FLOWER_OVERRIDE) ||
                (random.nextFloat() < 0.2f && level.getBlockState(pos.below()).is(ModBlocks.LARGE_BARREL_CACTUS))) {
            level.setBlockAndUpdate(pos, Blocks.CACTUS_FLOWER.defaultBlockState());
        } else {
            level.setBlockAndUpdate(pos, ModBlocks.SMALL_BARREL_CACTUS.defaultBlockState()
                    .setValue(SmallBarrelCactusBlock.AGE, amount - 1));
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        grow(state, level, pos, random, random.nextIntBetweenInclusive(1, 2));
    }
}

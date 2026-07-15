package net.jirniy.jirbiomes.block.custom;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.item.ModItems;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
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
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class SmallBarrelCactusBlock extends Block implements BonemealableBlock {
    public static final int MAX_AGE = 2;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);
    private static final VoxelShape[] SHAPES = {
            Block.column(8, 0, 9),
            Block.column(10, 0, 12),
            Block.column(12, 0, 14),
    };

    public SmallBarrelCactusBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 1));
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < LargeBarrelCactusBlock.getGrowthChance(0.2f, 3, level, pos)
                && level.getRawBrightness(pos, 0) >= 9) {
            grow(state, level, pos, 1);
        }
        super.randomTick(state, level, pos, random);
    }

    @Override
    public Item asItem() {
        return ModItems.BARREL_CACTUS;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[state.getValue(AGE)];
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
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

        return level.getBlockState(pos.below()).is(ModTags.Blocks.SUPPORTS_BARREL_CACTUS)
                && level.getFluidState(pos.above()).isEmpty();
    }

    @Override
    protected void entityInside(final BlockState state, final Level level, final BlockPos pos, final Entity entity, final InsideBlockEffectApplier effectApplier, final boolean isPrecise) {
        if (state.getValue(AGE) == 2) {
            if (!(entity instanceof ItemEntity)) {
                entity.hurt(level.damageSources().cactus(), 1.0F);
            }
        }
    }

    @Override
    protected boolean isPathfindable(final BlockState state, final PathComputationType type) {
        return state.getValue(AGE) < 1;
    }

    private void grow(BlockState state, ServerLevel level, BlockPos pos, int amount) {
        int currentAge = state.getValue(AGE);
        if (currentAge <= MAX_AGE - amount) {
            level.setBlockAndUpdate(pos, state.setValue(AGE, currentAge + amount));
        } else {
            level.setBlockAndUpdate(pos, ModBlocks.LARGE_BARREL_CACTUS.defaultBlockState());
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
        grow(state, level, pos, random.nextIntBetweenInclusive(1, 2));
    }
}

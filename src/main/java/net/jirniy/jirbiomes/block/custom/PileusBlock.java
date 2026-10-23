package net.jirniy.jirbiomes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class PileusBlock extends Block implements BonemealableBlock {
    public static final EnumProperty<PileusShapes> SHAPE = EnumProperty.create("shape", PileusShapes.class);
    public static final VoxelShape HANGING_SHAPE = Block.column(16, 8, 16);
    public static final VoxelShape GROUND_SHAPE = Block.column(16, 0, 8);
    private final BlockState bonemealState;

    public PileusBlock(BlockState bonemealState, Properties properties) {
        super(properties);
        this.bonemealState = bonemealState;
        registerDefaultState(defaultBlockState().setValue(SHAPE, PileusShapes.FULL));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(SHAPE)) {
            case PileusShapes.HANGING -> HANGING_SHAPE;
            case PileusShapes.GROUNDED -> GROUND_SHAPE;
            default -> Shapes.block();
        };
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        BlockState belowState = level.getBlockState(pos.below());
        if (belowState.is(this) || belowState.isFaceSturdy(level, pos.below(), Direction.UP)) {
            return state.getValue(SHAPE) == PileusShapes.GROUNDED && level.getBlockState(pos.above()).isAir()
                    ? state.setValue(SHAPE, PileusShapes.GROUNDED)
                    : state.setValue(SHAPE, PileusShapes.FULL);
        } else if (belowState.isAir()) {
            return state.setValue(SHAPE, PileusShapes.HANGING);
        } else {
            return state.setValue(SHAPE, PileusShapes.SUPPORTING);
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = defaultBlockState();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState belowState = level.getBlockState(pos.below());
        if (belowState.is(this) || belowState.isFaceSturdy(level, pos.below(), Direction.UP)) {
            if ((context.getPlayer() != null && !context.getPlayer().isShiftKeyDown()) && level.getBlockState(pos.above()).isAir()) {
                return state.setValue(SHAPE, PileusShapes.GROUNDED);
            } else {
                return state.setValue(SHAPE, PileusShapes.FULL);
            }
        } else if (belowState.isAir()) {
            return state.setValue(SHAPE, PileusShapes.HANGING);
        } else {
            return state.setValue(SHAPE, PileusShapes.SUPPORTING);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SHAPE);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.below()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlockAndUpdate(pos.below(), bonemealState);
    }
}

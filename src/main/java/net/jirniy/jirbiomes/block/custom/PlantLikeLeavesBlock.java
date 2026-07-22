package net.jirniy.jirbiomes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class PlantLikeLeavesBlock extends Block {
    public static final EnumProperty<AttachFace> BLOCKSHAPE = EnumProperty.create("shape", AttachFace.class);
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    public PlantLikeLeavesBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState().setValue(BLOCKSHAPE, AttachFace.FLOOR));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BLOCKSHAPE, FACING);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = getDirectionPlacedOn(state);
        BlockPos relative = pos.relative(direction);
        return level.getBlockState(relative).isFaceSturdy(level, relative, direction.getOpposite());
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!canSurvive(state, level, pos)) {
            level.destroyBlock(pos, true, null, 2);
        }
        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
    }

    public @Nullable BlockState getStateForPlacement(final BlockPlaceContext context) {
        for(Direction direction : context.getNearestLookingDirections()) {
            BlockState state;
            if (direction.getAxis() == Direction.Axis.Z || direction.getAxis() == Direction.Axis.X) {
                state = this.defaultBlockState().setValue(BLOCKSHAPE, AttachFace.WALL).setValue(FACING, direction.getOpposite());
                } else {
                state = this.defaultBlockState().setValue(BLOCKSHAPE, direction == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR).setValue(FACING, context.getHorizontalDirection());
            }

            if (state.canSurvive(context.getLevel(), context.getClickedPos())) {
                return state;
            }
        }

        return null;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (getDirectionPlacedOn(state)) {
            case DOWN -> Block.box(4, 0, 4, 12, 12, 12);
            case UP -> Block.box(4, 4, 4, 12, 16, 12);
            case NORTH, SOUTH, WEST, EAST -> Block.box(0, 4, 0, 16, 12, 16);
            default -> super.getShape(state, level, pos, context);
        };
    }

    private Direction getDirectionPlacedOn(BlockState state) {
        return switch (state.getValue(BLOCKSHAPE)) {
            case AttachFace.WALL -> state.getValue(FACING);
            case AttachFace.CEILING -> Direction.UP;
            case AttachFace.FLOOR -> Direction.DOWN;
            default -> Direction.DOWN;
        };
    }
}

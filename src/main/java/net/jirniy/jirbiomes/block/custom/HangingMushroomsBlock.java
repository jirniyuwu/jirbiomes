package net.jirniy.jirbiomes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HangingMushroomsBlock extends Block implements BonemealableBlock {
    public static final BooleanProperty TIP = BlockStateProperties.TIP;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    private static final VoxelShape SHAPE_BASE = Block.column(14.0, 0.0, 16.0);
    private static final VoxelShape SHAPE_TIP = Block.column(14.0, 3.0, 16.0);

    public HangingMushroomsBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(defaultBlockState().setValue(TIP, true).setValue(LIT, false));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (itemStack.is(Items.SHEARS) && state.getValue(LIT)) {
            level.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.SHEARS_SNIP, SoundSource.BLOCKS);
            level.setBlock(pos, state.setValue(LIT, false), 2);

            int dropAttempts = level.getRandom().nextIntBetweenInclusive(1, 3);
            for (int i = 0; i < dropAttempts; i++) {
                if (level.getRandom().nextBoolean()) {
                    popResource(level, pos, new ItemStack(Items.BROWN_MUSHROOM, 1));
                } else {
                    popResource(level, pos, new ItemStack(Items.RED_MUSHROOM, 1));
                }
            };
            itemStack.hurtAndBreak(1, player, hand);
            return InteractionResult.SUCCESS;
        }
        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    public static int getLightLevel(BlockState state) {
        return state.getValue(LIT) ? 1 : 0;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(TIP) || !state.getValue(LIT);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < 0.02f) {
            if (!state.getValue(LIT)) {
                level.setBlockAndUpdate(pos, state.setValue(LIT, true));
            }
            if (state.getValue(TIP) && random.nextFloat() < 0.5f) {
                if (level.getBlockState(pos.below()).isAir() && level.isInsideBuildHeight(pos.below())) {
                    level.setBlockAndUpdate(pos, state.setValue(TIP, false));
                    level.setBlockAndUpdate(pos.below(), state.setValue(TIP, true).setValue(LIT, false));
                }
            }
        }
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return state.getValue(TIP) ? SHAPE_TIP : SHAPE_BASE;
    }

    @Override
    protected boolean propagatesSkylightDown(final BlockState state) {
        return true;
    }

    @Override
    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        return this.canStayAtPosition(level, pos);
    }

    private boolean canStayAtPosition(final BlockGetter level, final BlockPos pos) {
        BlockPos neighbourPos = pos.relative(Direction.UP);
        BlockState blockState = level.getBlockState(neighbourPos);
        return MultifaceBlock.canAttachTo(level, Direction.UP, neighbourPos, blockState) || blockState.is(this);
    }

    @Override
    protected BlockState updateShape(final BlockState state, final LevelReader level, final ScheduledTickAccess ticks, final BlockPos pos, final Direction directionToNeighbour, final BlockPos neighbourPos, final BlockState neighbourState, final RandomSource random) {
        if (!this.canStayAtPosition(level, pos)) {
            ticks.scheduleTick(pos, this, 1);
        }

        return state.setValue(TIP, !level.getBlockState(pos.below()).is(this));
    }

    @Override
    protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        if (!this.canStayAtPosition(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TIP, LIT);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return !state.getValue(LIT) ||
                (state.getValue(TIP) && level.getBlockState(pos.below()).isAir() && level.isInsideBuildHeight(pos.below()));
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        if (!state.getValue(LIT)) {
            level.setBlockAndUpdate(pos, state.setValue(LIT, true));
        } else if (state.getValue(TIP)) {
            if (level.getBlockState(pos.below()).isAir() && level.isInsideBuildHeight(pos.below())) {
                level.setBlockAndUpdate(pos.below(), state.setValue(TIP, true).setValue(LIT, false));
                level.setBlockAndUpdate(pos, state.setValue(TIP, false));
            }
        }
    }
}

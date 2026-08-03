package net.jirniy.jirbiomes.block.custom;

import net.jirniy.jirbiomes.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;

public class AlgaeBlock extends Block implements SimpleWaterloggedBlock, BonemealableBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty DENSE = BooleanProperty.create("dense");
    private static final VoxelShape SHAPE = Block.column(16.0F, 14.5F, 15.5F);
    private static final VoxelShape DENSE_SHAPE = Block.column(16.0F, 13.5F, 15.5F);

    public AlgaeBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, true).setValue(DENSE, false));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return state.getValue(WATERLOGGED) && (level.getBlockState(pos.above()).is(BlockTags.AIR) || level.getBlockState(pos.above()).is(Blocks.LILY_PAD));
    }

    @Override
    public Item asItem() {
        return ModItems.ALGAE_BUCKET;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < 0.3f) {
            level.addParticle(ParticleTypes.FALLING_SPORE_BLOSSOM,
                    pos.getX() + random.nextFloat(), pos.getY() + 0.5f + random.nextFloat()*0.4f, pos.getZ() + random.nextFloat(),
                    0.5, 0.2, 0.5);
        }
        super.animateTick(state, level, pos, random);
    }

    protected boolean skipRendering(final BlockState state, final BlockState neighborState, final Direction direction) {
        if (direction.getAxis().isHorizontal() && state.getValue(DENSE) && neighborState.is(this) && neighborState.getValue(DENSE)) {
            return true;
        }

        return super.skipRendering(state, neighborState, direction);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return canSurvive(state, level, pos)
                ? super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random)
                : Blocks.WATER.defaultBlockState();
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        super.onPlace(state, level, pos, oldState, movedByPiston);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!canSurvive(state, level, pos)) {
            level.destroyBlock(pos, false);
        }
        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (itemStack.is(Items.WATER_BUCKET)) {
            giveItem(Blocks.WATER, level, pos, player, itemStack);
            return InteractionResult.SUCCESS;
        } else if (itemStack.is(Items.BUCKET)) {
            giveItem(Blocks.AIR, level, pos, player, itemStack);
            return InteractionResult.SUCCESS;
        }

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    protected void giveItem(Block replaceWith, Level level, BlockPos pos, Player player, ItemStack heldItem) {
        ItemStack giveStack = new ItemStack(ModItems.ALGAE_BUCKET, 1);
        Inventory inventory = player.getInventory();
        if (!player.isCreative()) {
            if (heldItem.getCount() == 1) {
                inventory.removeItem(inventory.getSelectedSlot(), 1);
                inventory.setItem(inventory.getSelectedSlot(), giveStack);
            } else {
                heldItem.setCount(heldItem.count() - 1);
                player.addItem(giveStack);
            }
        } else if (!inventory.contains(giveStack)) {
            player.addItem(giveStack);
        }
        level.playSound(player, pos, SoundEvents.BUCKET_FILL, SoundSource.PLAYERS);
        level.setBlockAndUpdate(pos, replaceWith.defaultBlockState());
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getRawBrightness(pos, 0) >= 11 && random.nextFloat() < 0.03f) {
            grow(level, pos, state, random);
        }
        super.randomTick(state, level, pos, random);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, DENSE);
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return state.getValue(DENSE) ? DENSE_SHAPE : SHAPE;
    }

    private void grow(Level level, BlockPos pos, BlockState state, RandomSource random) {
        if (state.getValue(DENSE)) {
            ArrayList<Direction> spreadDirections = new ArrayList<>();
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockPos dirPos = pos.relative(direction);
                if (level.getBlockState(dirPos).is(Blocks.WATER) && level.getFluidState(dirPos).isSource()) {
                    spreadDirections.add(direction);
                }
            }

            if (!spreadDirections.isEmpty()) {
                BlockPos randomPos = pos.relative(spreadDirections.get(random.nextIntBetweenInclusive(0, spreadDirections.size()-1)));
                BlockState neighborState = level.getBlockState(randomPos);
                if (neighborState.is(Blocks.WATER) && neighborState.getFluidState().isSourceOfType(Fluids.WATER)) {
                    level.setBlockAndUpdate(randomPos, this.defaultBlockState().setValue(DENSE, false));
                }
            }
        } else {
            level.setBlockAndUpdate(pos, state.setValue(DENSE, true));
        }
    }

    @Override
    protected FluidState getFluidState(final BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos dirPos = pos.relative(direction);
            if (level.getBlockState(dirPos).is(Blocks.WATER) && level.getFluidState(dirPos).isSource()) {
                return true;
            }
        }
        return !state.getValue(DENSE);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        grow(level, pos, state, random);
    }
}

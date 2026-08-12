package net.jirniy.jirbiomes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class SeaUrchinBlock extends Block implements SimpleWaterloggedBlock, BonemealableBlock {
    public static final BooleanProperty FLOATING = BooleanProperty.create("floating");
    public static final BooleanProperty NATURAL = BooleanProperty.create("natural");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape SHAPE = Block.column(12.0, 0.0, 13.0);
    private static final VoxelShape FLOATING_SHAPE = Block.column(12.0, 2.0, 14.0);

    public SeaUrchinBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(WATERLOGGED, false)
                .setValue(FLOATING, false)
                .setValue(NATURAL, true));
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if (state.getValue(NATURAL) && entity instanceof LivingEntity livingEntity) {
            if (livingEntity instanceof Player player && player.isCreative()) {
                return;
            }
            livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 160, 0));
            livingEntity.makeStuckInBlock(state, new Vec3(0.85, 0.85, 0.85));
            if (!level.isClientSide()) {
                level.scheduleTick(pos, this, 6);
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.destroyBlock(pos, false);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (state.getValue(NATURAL) && itemStack.is(Items.SHEARS)) {
            level.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.SHEARS_SNIP, SoundSource.BLOCKS);
            level.setBlock(pos, state.setValue(NATURAL, false), 1);
            itemStack.hurtAndBreak(1, player, hand);
            return InteractionResult.SUCCESS;
        }
        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = defaultBlockState();
        if (level.getFluidState(pos).is(Fluids.WATER)) {
            state = state.setValue(WATERLOGGED, true);
        }
        if (!level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) {
            state = state.setValue(FLOATING, true);
        }
        return canSurvive(state, level, pos) ? state : null;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!canSurvive(level.getBlockState(pos), level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return state.getValue(WATERLOGGED) ||
                (!state.getValue(FLOATING) && level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(FLOATING) ? FLOATING_SHAPE : SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FLOATING, WATERLOGGED, NATURAL);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return !state.getValue(NATURAL);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return state.setValue(FLOATING, !level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP));
    }

    @Override
    protected FluidState getFluidState(final BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
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
        popResource(level, pos, new ItemStack(this));
    }
}

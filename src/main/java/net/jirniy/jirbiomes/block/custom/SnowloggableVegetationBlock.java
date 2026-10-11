package net.jirniy.jirbiomes.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class SnowloggableVegetationBlock extends VegetationPlaceableOnBlock {
    public static final IntegerProperty SNOWLOGGABLE_LAYERS = IntegerProperty.create("snowloggable", 0, 7);
    private static final VoxelShape SHAPE = Block.column(16.0, 0.0, 14.0);
    private static final VoxelShape[] LAYER_SHAPES = Block.boxes(8, height -> Block.column(16.0, 0.0, height * 2));

    public SnowloggableVegetationBlock(TagKey<Block> placeableOn, Properties properties) {
        super(placeableOn, properties);
        registerDefaultState(defaultBlockState().setValue(SNOWLOGGABLE_LAYERS, 0));
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
        if (state.getValue(SNOWLOGGABLE_LAYERS) > 0 && canSnowSurvive(state, level, pos)) {
            level.setBlockAndUpdate(pos, Blocks.SNOW.defaultBlockState().setValue(SnowLayerBlock.LAYERS, state.getValue(SNOWLOGGABLE_LAYERS)));
            if (player.isCreative() || destroyedWith.isCorrectToolForDrops(Blocks.SNOW.defaultBlockState())) {
                level.destroyBlock(pos, true, player);
            }
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if (!canSnowSurvive(state, level, pos)) {
            return this.defaultBlockState().setValue(SNOWLOGGABLE_LAYERS, 0);
        }
        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
//        if (state.getValue(SNOWLOGGABLE_LAYERS) < level.getGameRules().get(GameRules.MAX_SNOW_ACCUMULATION_HEIGHT)
//                && level.isRainingAt(pos) && level.canSeeSky(pos.above())
//                && level.getBiome(pos).value().getPrecipitationAt(pos, level.getSeaLevel()) == Biome.Precipitation.SNOW
//                && level.isInsideBuildHeight(pos) && level.getBrightness(LightLayer.BLOCK, pos) < 10) {
//            incrementLayer(state, level, pos);
//        }
        // cant figure it out
        super.randomTick(state, level, pos, random);
    }

    protected void incrementLayer(BlockState state, Level level, BlockPos pos) {
        if (canSnowSurvive(state, level, pos)) {
            if (state.getValue(SNOWLOGGABLE_LAYERS) < 7) {
                level.setBlockAndUpdate(pos, state.setValue(SNOWLOGGABLE_LAYERS, state.getValue(SNOWLOGGABLE_LAYERS) + 1));
            } else {
                level.setBlockAndUpdate(pos, Blocks.SNOW.defaultBlockState().setValue(SnowLayerBlock.LAYERS, 8));
            }
        }
    }

    protected boolean canSnowSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState belowState = level.getBlockState(pos.below());
        return !belowState.is(BlockTags.CANNOT_SUPPORT_SNOW_LAYER)
                && (belowState.is(BlockTags.SUPPORT_OVERRIDE_SNOW_LAYER)
                || Block.isFaceFull(belowState.getCollisionShape(level, pos.below()), Direction.UP))
                && !belowState.is(Blocks.SNOW);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(SNOWLOGGABLE_LAYERS) > 0 ? Shapes.or(SHAPE, LAYER_SHAPES[state.getValue(SNOWLOGGABLE_LAYERS)]) : SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return state.getValue(SNOWLOGGABLE_LAYERS) > 0 ? LAYER_SHAPES[state.getValue(SNOWLOGGABLE_LAYERS) - 1] : Shapes.empty();
    }

    @Override
    protected VoxelShape getBlockSupportShape(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return state.getValue(SNOWLOGGABLE_LAYERS) > 0 ? LAYER_SHAPES[state.getValue(SNOWLOGGABLE_LAYERS) - 1] : Shapes.empty();
    }

    @Override
    protected VoxelShape getVisualShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return state.getValue(SNOWLOGGABLE_LAYERS) > 0 ? Shapes.or(SHAPE, LAYER_SHAPES[state.getValue(SNOWLOGGABLE_LAYERS)]) : SHAPE;
    }

    @Override
    protected boolean canBeReplaced(final BlockState state, final BlockPlaceContext context) {
        return !context.getItemInHand().is(this.asItem()) && state.getValue(SNOWLOGGABLE_LAYERS) <= 1;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (itemStack.is(Items.SNOW) && canSnowSurvive(state, level, pos)) {
            incrementLayer(state, level, pos);
            level.playSound(player, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.SNOW_PLACE, SoundSource.BLOCKS);
            itemStack.consume(1, player);
            return InteractionResult.SUCCESS;
        }
        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        if (state.is(Blocks.SNOW) && state.getValue(SnowLayerBlock.LAYERS) < 8) {
            return this.defaultBlockState().setValue(SNOWLOGGABLE_LAYERS, state.getValue(SnowLayerBlock.LAYERS));
        } else {
            return super.getStateForPlacement(context);
        }
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SNOWLOGGABLE_LAYERS);
    }
}

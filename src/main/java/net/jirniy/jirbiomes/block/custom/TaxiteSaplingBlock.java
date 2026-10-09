package net.jirniy.jirbiomes.block.custom;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class TaxiteSaplingBlock extends VegetationPlaceableOnBlock implements BonemealableBlock {
    private static final VoxelShape SHAPE = Block.column(10.0, 0.0, 14.0);
    public static Map<Block, Block> ROOT_CONVERSION = Map.ofEntries(
            Map.entry(Blocks.SAND, ModBlocks.ROOTED_SAND),
            Map.entry(Blocks.RED_SAND, ModBlocks.ROOTED_RED_SAND),
            Map.entry(ModBlocks.DRIED_DIRT, ModBlocks.ROOTED_DRIED_DIRT),
            Map.entry(ModBlocks.DRIED_GRASS_BLOCK, ModBlocks.ROOTED_DRIED_DIRT),
            Map.entry(Blocks.DIRT, Blocks.ROOTED_DIRT),
            Map.entry(Blocks.GRASS_BLOCK, Blocks.ROOTED_DIRT),
            Map.entry(Blocks.PODZOL, Blocks.ROOTED_DIRT),
            Map.entry(Blocks.MYCELIUM, Blocks.ROOTED_DIRT),
            Map.entry(ModBlocks.WETLAND, ModBlocks.ROOTED_WETLAND),
            Map.entry(ModBlocks.WET_GRASS_BLOCK, ModBlocks.ROOTED_WETLAND),
            Map.entry(Blocks.MUD, ModBlocks.ROOTED_MUD)
    );

    public TaxiteSaplingBlock(Properties properties) {
        super(ModTags.Blocks.PROTOTAXITE_PLACEABLE, properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        if (level.getBlockState(pos.below()).is(ModTags.Blocks.ROOTED_BLOCKS)
                && level.isInsideBuildHeight(pos.above())
                && level.getBlockState(pos.above()).isAir()
                && level.getRawBrightness(pos, 0) >= 13
                && random.nextFloat() < 0.01f) {
            level.setBlockAndUpdate(pos, ModBlocks.PROTOTAXITE_LOG.defaultBlockState());
            level.setBlockAndUpdate(pos.above(), ModBlocks.PROTOTAXITE_SAPLING.defaultBlockState());
        }
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.above()).isAir()
                && level.isInsideBuildHeight(pos.above())
                && level.getBlockState(pos.below()).is(ModTags.Blocks.PROTOTAXITE_PLACEABLE);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return random.nextBoolean();
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlockAndUpdate(pos, ModBlocks.PROTOTAXITE_LOG.defaultBlockState());
        level.setBlockAndUpdate(pos.above(), ModBlocks.PROTOTAXITE_SAPLING.defaultBlockState());
        if (level.getBlockState(pos.below()).is(ModTags.Blocks.PROTOTAXITE_ROOTS_REPLACEABLE)) {
            Block rootBlock = ROOT_CONVERSION.get(level.getBlockState(pos.below()).getBlock());
            if (rootBlock != null) {
                level.setBlockAndUpdate(pos.below(), rootBlock.defaultBlockState());
            }
        }
    }
}

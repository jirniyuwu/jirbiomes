package net.jirniy.jirbiomes.block.custom;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TaxiteLogBlock extends RotatedPillarBlock implements BonemealableBlock {
    public TaxiteLogBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(AXIS) == Direction.Axis.Y
                && level.getBlockState(pos.above()).isAir()
                && level.isInsideBuildHeight(pos.above())
                && random.nextFloat() < 0.005f) {
            level.setBlockAndUpdate(pos.above(), ModBlocks.PROTOTAXITE_SAPLING.defaultBlockState());
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AXIS) == Direction.Axis.Y;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AXIS) == Direction.Axis.Y
                && level.getBlockState(pos.above()).isAir()
                && level.isInsideBuildHeight(pos.above());
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return random.nextBoolean();
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlockAndUpdate(pos.above(), ModBlocks.PROTOTAXITE_SAPLING.defaultBlockState());
    }
}

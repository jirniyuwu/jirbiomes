package net.jirniy.jirbiomes.block.custom;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class PalmLeavesBlock extends UntintedParticleLeavesBlock implements BonemealableBlock {
    public PalmLeavesBlock(Properties properties) {
        super(0.0f, ParticleTypes.CHERRY_LEAVES, properties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.below()).isAir() && !directionsToGrow(level, pos).isEmpty();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockState coconutState = ModBlocks.COCONUT_PLANT.defaultBlockState();
        List<Direction> directionsToGrow = directionsToGrow(level, pos);
        level.setBlockAndUpdate(pos.below(), coconutState
                .setValue(CocoaBlock.FACING, directionsToGrow.get(random.nextInt(0, directionsToGrow.size()))));
    }

    protected List<Direction> directionsToGrow(LevelReader level, BlockPos pos) {
        ArrayList<Direction> list = new ArrayList<>();
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos offsetPos = pos.below().relative(direction);
            if (level.getBlockState(offsetPos).is(BlockTags.SUPPORTS_COCOA)) {
                list.add(direction);
            }
        }
        return list;
    }
}

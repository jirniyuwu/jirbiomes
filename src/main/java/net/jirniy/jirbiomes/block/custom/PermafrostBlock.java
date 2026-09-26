package net.jirniy.jirbiomes.block.custom;

import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.Arrays;

public class PermafrostBlock extends Block {
    public PermafrostBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        Direction[] waterAdjacent = getAdjacentToBlockDirections(Blocks.WATER, Arrays.stream(Direction.values()).toList(), level, pos);
        Direction[] lavaAdjacent = getAdjacentToBlockDirections(Blocks.LAVA,  Arrays.stream(Direction.values()).toList(), level, pos);
        for (Direction dir : waterAdjacent) {
            if (level.getFluidState(pos.relative(dir)).isSourceOfType(Fluids.WATER) && random.nextFloat() < 0.1f) {
                level.setBlockAndUpdate(pos.relative(dir), Blocks.ICE.defaultBlockState());
            }
        }
        for (Direction dir : lavaAdjacent) {
            if (level.getFluidState(pos.relative(dir)).isSourceOfType(Fluids.LAVA) && random.nextFloat() < 0.4f) {
                level.setBlockAndUpdate(pos.relative(dir), ModBlocks.POTENT_MAGMA_BLOCK.defaultBlockState());
            }
        }
    }

    public Direction[] getAdjacentToBlockDirections(Block block, Iterable<Direction> directionsToCheck, Level level, BlockPos pos) {
        ArrayList<Direction> directions = new ArrayList<>();
        for (Direction dir : directionsToCheck) {
            BlockPos dirPos = pos.relative(dir);
            if (level.getBlockState(dirPos).is(block)) {
                directions.add(dir);
            }
        }
        return directions.toArray(Direction[]::new);
    }

    public Direction[] getAdjacentToBlockDirections(Block[] block, Iterable<Direction> directionsToCheck, Level level, BlockPos pos) {
        ArrayList<Direction> directions = new ArrayList<>();
        for (Direction dir : directionsToCheck) {
            BlockPos dirPos = pos.relative(dir);
            if (Arrays.stream(block).anyMatch(level.getBlockState(dirPos)::is)) {
                directions.add(dir);
            }
        }
        return directions.toArray(Direction[]::new);
    }
}

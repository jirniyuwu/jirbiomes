package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.block.custom.PileusBlock;
import net.jirniy.jirbiomes.block.custom.PileusShapes;
import net.jirniy.jirbiomes.block.custom.PlantLikeLeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;

public class LargeTenebrisFeature extends Feature<NoneFeatureConfiguration> {
    public LargeTenebrisFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        for (int i = 0; i <= 9; i++) {
            if (!level.getBlockState(pos.above(i)).canBeReplaced()) {
                return false;
            }
        }

        ArrayList<BlockPos> trunkPoses = new ArrayList<>();
        ArrayList<BlockPos> capPoses = new ArrayList<>();
        int height = random.nextIntBetweenInclusive(5, 8);
        int radius = 2;
        Pair<Float, Float> hangingBlockProbability = Pair.of(0.7f, 0.7f);
        float hangingDecorProbability = 0.4f;

        for (int i = 0; i < height; i++) {
            BlockPos newPos = pos.above(i);
            trySetBlock(trunkPoses, ModBlocks.TENEBRIS_LOG.defaultBlockState(), newPos, level);

            if (i == height - 1) {
                placeCap(capPoses, radius, hangingBlockProbability, ModBlocks.TENEBRIS_PILEUS.defaultBlockState(), newPos, level, random);
            }
        }

        for (BlockPos capPos : capPoses) {
            if (level.getBlockState(capPos.below()).isAir()) {
                if (random.nextFloat() < hangingDecorProbability) {
                    if (random.nextFloat() < 0.2f) {
                        trySetBlock(Blocks.SHROOMLIGHT.defaultBlockState(),
                                capPos.below(), level);
                    } else {
                        trySetBlock(ModBlocks.TENEBRIS_LEAVES.defaultBlockState()
                                        .setValue(PlantLikeLeavesBlock.BLOCKSHAPE, AttachFace.CEILING)
                                        .setValue(PlantLikeLeavesBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random)),
                                capPos.below(), level);
                    }
                }
            }
        }

        return true;
    }

    private ArrayList<BlockPos> placeCap(ArrayList<BlockPos> list, int radius, Pair<Float, Float> probability, BlockState state, BlockPos pos, WorldGenLevel level, RandomSource random) {
        ArrayList<Vec3i> offsets = new ArrayList<>();
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                offsets.add(new Vec3i(i, 0, j));
            }
        }

        ArrayList<Vec3i> tempList = new ArrayList<>();
        tempList.add(new Vec3i(0, 1, 0));
        ArrayList<Vec3i> topList = new ArrayList<>();
        for (Vec3i offset : offsets) {
            if (isWithinRange(offset.getX(), -1, 1) && isWithinRange(offset.getZ(), -1, 1)) {
                if (isAtCorner(offset, 1)) {
                    if (random.nextFloat() < 0.4f) {
                        topList.add(offset.above());
                    }
                } else {
                    topList.add(offset.above());
                }
            }
            if (isAtEdge(offset, radius)) {
                if (random.nextFloat() < probability.getFirst()) {
                    tempList.add(offset.below());
                    if (random.nextFloat() < probability.getSecond()) {
                        tempList.add(offset.below(2));
                    }
                }
            }
        }
        offsets.addAll(topList);
        offsets.addAll(tempList);

        for (Vec3i offset : offsets) {
            if (!offset.equals(new Vec3i(0, 0, 0))) {
                if (offset.getY() == 0
                        && (((!isAtCorner(offset, radius) && random.nextFloat() > probability.getSecond())
                        || isAtCorner(offset, 1)) && !topList.contains(offset.above()))) {
                    trySetBlock(list, state.setValue(PileusBlock.SHAPE, PileusShapes.GROUNDED),
                            pos.offset(offset.above()), level);
                }
                trySetBlock(list, state, pos.offset(offset), level);
            }
        }

        return list;
    }

    private ArrayList<BlockPos> trySetBlock(ArrayList<BlockPos> list, BlockState state, BlockPos pos, WorldGenLevel level) {
        if (level.getBlockState(pos).canBeReplaced()) {
            if (level.setBlock(pos, state, 3)) {
                level.scheduleTick(pos, state.getBlock(), 1);
                list.add(pos);
            }
        }
        return list;
    }

    private void trySetBlock(BlockState state, BlockPos pos, WorldGenLevel level) {
        if (level.getBlockState(pos).canBeReplaced()) {
            level.scheduleTick(pos, state.getBlock(), 1);
            level.setBlock(pos, state, 3);
        }
    }

    private static boolean isWithinRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
    private static boolean isAtCorner(Vec3i value, int radius) {
        return (value.getX() == radius || value.getX() == -radius)
                && (value.getZ() == radius || value.getZ() == -radius);
    }
    private static boolean isAtEdge(Vec3i value, int radius) {
        return (value.getX() == radius || value.getX() == -radius)
                || (value.getZ() == radius || value.getZ() == -radius);
    }
}

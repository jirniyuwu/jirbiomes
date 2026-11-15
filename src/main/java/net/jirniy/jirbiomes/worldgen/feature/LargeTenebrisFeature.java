package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jirniy.jirbiomes.block.custom.PileusBlock;
import net.jirniy.jirbiomes.block.custom.PileusShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.ArrayList;

public record LargeTenebrisFeature(
        Holder<BlockStateProvider> trunk,
        IntProvider trunkHeight,
        Holder<BlockStateProvider> leaves,
        IntProvider leavesRadius,
        int leavesMaxHanging,
        float leavesHangingProbability,
        float topBlockProbability1,
        float topBlockProbability2,
        Holder<BlockStateProvider> decorHanging,
        float decorProbability
) implements Feature {
    public static final MapCodec<LargeTenebrisFeature> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            BlockStateProvider.CODEC.fieldOf("trunk").forGetter(LargeTenebrisFeature::trunk),
            IntProviders.codec(1, 32).fieldOf("height").forGetter(LargeTenebrisFeature::trunkHeight),
            BlockStateProvider.CODEC.fieldOf("leaves").forGetter(LargeTenebrisFeature::leaves),
            IntProviders.codec(1, 5).fieldOf("leaves_radius").forGetter(LargeTenebrisFeature::leavesRadius),
            Codec.intRange(0, 4).fieldOf("leaves_max_hanging").forGetter(LargeTenebrisFeature::leavesMaxHanging),
            Codec.floatRange(0.0F, 1.0F).fieldOf("leaves_hanging_probability").forGetter(LargeTenebrisFeature::leavesHangingProbability),
            Codec.floatRange(0.0F, 1.0F).fieldOf("top_block_probability_1").forGetter(LargeTenebrisFeature::topBlockProbability1),
            Codec.floatRange(0.0F, 1.0F).fieldOf("top_block_probability_2").forGetter(LargeTenebrisFeature::topBlockProbability2),
            BlockStateProvider.CODEC.fieldOf("decor").forGetter(LargeTenebrisFeature::decorHanging),
            Codec.floatRange(0.0F, 1.0F).fieldOf("decor_probability").forGetter(LargeTenebrisFeature::decorProbability)
    ).apply(i, LargeTenebrisFeature::new));

    @Override
    public MapCodec<LargeTenebrisFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        int height = trunkHeight.sample(random);
        int radius = leavesRadius.sample(random);

        for (int i = 0; i <= height + 1; i++) {
            if (!level.getBlockState(origin.above(i)).canBeReplaced()) {
                return false;
            }
        }

        ArrayList<BlockPos> trunkPoses = new ArrayList<>();
        ArrayList<BlockPos> capPoses = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            BlockPos newPos = origin.above(i);
            trySetBlock(trunkPoses, trunk.value().getState(level, random, newPos), newPos, level);

            if (i == height - 1) {
                placeCap(capPoses, radius, newPos, level, random);
            }
        }

        for (BlockPos capPos : capPoses) {
            if (level.getBlockState(capPos.below()).isAir()) {
                if (random.nextFloat() < decorProbability) {
                    trySetBlock(decorHanging.value().getState(level, random, capPos.below()), capPos.below(), level);
                }
            }
        }

        return true;
    }

    private ArrayList<BlockPos> placeCap(ArrayList<BlockPos> list, int radius, BlockPos pos, WorldGenLevel level, RandomSource random) {
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
                    if (random.nextFloat() < topBlockProbability1) {
                        topList.add(offset.above());
                    }
                } else {
                    topList.add(offset.above());
                }
            }
            if (leavesMaxHanging > 0 && isAtEdge(offset, radius)) {
                if (random.nextFloat() < leavesHangingProbability) {
                    tempList.add(offset.below(1));
                    if (leavesMaxHanging > 1 && random.nextFloat() < leavesHangingProbability) {
                        tempList.add(offset.below(2));
                        if (leavesMaxHanging > 2 && random.nextFloat() < leavesHangingProbability) {
                            tempList.add(offset.below(3));
                            if (leavesMaxHanging > 3 && random.nextFloat() < leavesHangingProbability) {
                                tempList.add(offset.below(4));
                            }
                        }
                    }
                }
            }
        }
        offsets.addAll(topList);
        offsets.addAll(tempList);

        for (Vec3i offset : offsets) {
            if (!offset.equals(new Vec3i(0, 0, 0))) {
                if (offset.getY() == 0
                        && (((!isAtCorner(offset, radius) && random.nextFloat() > topBlockProbability2)
                        || isAtCorner(offset, 1)) && !topList.contains(offset.above()))) {
                    trySetBlock(list, leaves.value().getState(level, random, pos.offset(offset.above()))
                                    .trySetValue(PileusBlock.SHAPE, PileusShapes.GROUNDED),
                            pos.offset(offset.above()), level);
                }
                trySetBlock(list, leaves.value().getState(level, random, pos.offset(offset)), pos.offset(offset), level);
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

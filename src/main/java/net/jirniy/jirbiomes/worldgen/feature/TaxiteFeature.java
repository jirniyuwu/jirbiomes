package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record TaxiteFeature(
        Holder<BlockStateProvider> trunkProvider,
        IntProvider radius,
        IntProvider topHeight,
        IntProvider bottomHeight,
        float cornerProbability,
        float topBlockProbability,
        boolean alwaysPlace
) implements Feature {
    private static final Direction[] HORIZONTAL_DIRECTIONS = new Direction[]{Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST};
    public static final MapCodec<TaxiteFeature> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
                    BlockStateProvider.CODEC.fieldOf("trunk").forGetter(TaxiteFeature::trunkProvider),
                    IntProviders.codec(1, 4).fieldOf("radius").forGetter(TaxiteFeature::radius),
                    IntProviders.codec(1, 60).fieldOf("top_height").forGetter(TaxiteFeature::topHeight),
                    IntProviders.codec(1, 60).fieldOf("bottom_height").forGetter(TaxiteFeature::bottomHeight),
                    Codec.floatRange(0.0f, 1.0f).fieldOf("corner_probability").forGetter(TaxiteFeature::cornerProbability),
                    Codec.floatRange(0.0f, 1.0f).fieldOf("top_block_probability").forGetter(TaxiteFeature::topBlockProbability),
                    Codec.BOOL.optionalFieldOf("always_place", false).forGetter(TaxiteFeature::alwaysPlace)
            ).apply(i, TaxiteFeature::new)
    );
    @Override
    public MapCodec<TaxiteFeature> codec() {
        return CODEC;
    }

    // this is a mess
    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        int radius = this.radius.sample(random);
        int topHeight = this.topHeight.sample(random);
        int bottomHeight = this.bottomHeight.sample(random);
        BlockStateProvider trunk = trunkProvider.value();

        if (alwaysPlace && !canPlace(level, topHeight, radius, origin)) {
            return false;
        }

        try {
            for (int i = 0; i < topHeight; i++) {
                int currentRadius;
                float currentProbability = cornerProbability;
                if (i == topHeight - 1) {
                    currentRadius = 1;
                    currentProbability = 0;
                    if (random.nextFloat() < topBlockProbability) {
                        trySetBlock(trunk.getState(level, random, origin.above(i + 1)), origin.above(i + 1), level);
                    }
                } else if (i >= topHeight * 0.8) {
                    if (i >= topHeight - 2) {
                        currentProbability = 0;
                    }
                    currentRadius = (int) Math.floor(radius * 0.35);
                } else if (i >= topHeight * 0.6) {
                    if (i >= topHeight * 0.8 - 1 && (int)Math.floor(radius * 0.35) != radius) {
                        currentProbability = 0;
                    }
                    currentRadius = (int) Math.floor(radius * 0.75);
                } else {
                    if (i >= topHeight * 0.6 - 1 && (int)Math.floor(radius * 0.75) != radius) {
                        currentProbability = 0;
                    }
                    currentRadius = radius;
                }

                placeRadius(trunk, Math.max(currentRadius, 1), currentProbability, level, origin.above(i), random);
            }
            for (int i = 0; i < bottomHeight; i++) {
                int currentRadius;
                if (i == bottomHeight - 1) {
                    currentRadius = 1;
                } else if (i >= bottomHeight * 0.75) {
                    currentRadius = (int) Math.floor(radius * 0.35);
                } else if (i >= bottomHeight * 0.45) {
                    currentRadius = (int) Math.floor(radius * 0.75);
                } else {
                    currentRadius = radius;
                }

                placeRadius(trunk, Math.max(currentRadius, 1), cornerProbability, level, origin.below(i), random);
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    protected boolean canPlace(WorldGenLevel level, int height, int radius, BlockPos pos) {
        for (int i = 1; i < height * 0.7; i++) {
            if (!level.getBlockState(pos.above(i)).canBeReplaced()) {
                return false;
            }
        }
        for (Direction dir : HORIZONTAL_DIRECTIONS) {
            for (int i = 1; i <= radius; i++) {
                BlockPos dirPos = pos.relative(dir, i).above((int)Math.floor(height * 0.15));
                if (!level.getBlockState(dirPos).canBeReplaced()) {
                    return false;
                }
            }
            if (!level.getBlockState(pos.above((int)Math.floor(height * 0.15)).relative(dir).relative(dir.getClockWise())).canBeReplaced()) {
                return false;
            }
        }
        return true;
    }

    private void placeRadius(BlockStateProvider block, int radius, float cornerProbability, WorldGenLevel level, BlockPos pos, RandomSource random) {
        switch (radius) {
            case 1 -> {
                trySetBlock(block.getState(level, random, pos), pos, level);
                for (Direction dir : HORIZONTAL_DIRECTIONS) {
                    BlockPos dirPos = pos.relative(dir);
                    trySetBlock(block.getState(level, random, dirPos), dirPos, level);
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.relative(dir.getCounterClockWise())),
                                dirPos.relative(dir.getCounterClockWise()), level);
                    }
                }
            }
            case 2 -> {
                for (Direction dir : HORIZONTAL_DIRECTIONS) {
                    BlockPos dirPos = pos.relative(dir, 2);
                    trySetBlock(block.getState(level, random, dirPos), dirPos, level);
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.relative(dir.getCounterClockWise())),
                                dirPos.relative(dir.getCounterClockWise()), level);
                    }
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.relative(dir.getClockWise())),
                                dirPos.relative(dir.getClockWise()), level);
                    }
                }
                placeRadius(block, 1, 1f, level, pos, random);
            }
            case 3 -> {
                for (Direction dir : HORIZONTAL_DIRECTIONS) {
                    BlockPos dirPos = pos.relative(dir, 3);
                    trySetBlock(block.getState(level, random, dirPos), dirPos, level);
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.relative(dir.getCounterClockWise())),
                                dirPos.relative(dir.getCounterClockWise()), level);
                    }
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.relative(dir.getClockWise())),
                                dirPos.relative(dir.getClockWise()), level);
                    }

                    BlockPos diagPos = pos.relative(dir, 2)
                            .relative(dir.getClockWise(), 2);
                    trySetBlock(block.getState(level, random, diagPos), diagPos, level);
                }
                placeRadius(block, 2, 1f, level, pos, random);
            }
            case 4 -> {
                for (Direction dir : HORIZONTAL_DIRECTIONS) {
                    BlockPos dirPos = pos.relative(dir, 4);
                    trySetBlock(block.getState(level, random, dirPos), dirPos, level);
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.relative(dir.getCounterClockWise())),
                                dirPos.relative(dir.getCounterClockWise()), level);
                    }
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.relative(dir.getClockWise())),
                                dirPos.relative(dir.getClockWise()), level);
                    }

                    BlockPos diagPos1 = pos.relative(dir, 3)
                            .relative(dir.getClockWise(), 2);
                    trySetBlock(block.getState(level, random, diagPos1), diagPos1, level);
                    BlockPos diagPos2 = pos.relative(dir, 2)
                            .relative(dir.getClockWise(), 3);
                    trySetBlock(block.getState(level, random, diagPos2), diagPos2, level);
                }
                placeRadius(block, 3, 1f, level, pos, random);
            }
            default -> placeRadius(block, 1, 0f, level, pos, random);
        }
    }

    private void trySetBlock(BlockState state, BlockPos pos, WorldGenLevel level) {
        if (!level.getBlockState(pos).is(BlockTags.FEATURES_CANNOT_REPLACE)) {
            level.setBlock(pos, state, 3);
        }
    }
}

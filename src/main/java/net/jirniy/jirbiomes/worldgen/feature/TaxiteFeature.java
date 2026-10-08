package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class TaxiteFeature extends Feature<TaxiteConfiguration> {
    private static final Direction[] HORIZONTAL_DIRECTIONS = new Direction[]{Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST};

    public TaxiteFeature(Codec<TaxiteConfiguration> codec) {
        super(codec);
    }

    // this is a mess
    @Override
    public boolean place(FeaturePlaceContext<TaxiteConfiguration> context) {
        RandomSource random = context.random();
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();

        BlockStateProvider trunkProvider = context.config().trunk();
        int radius = context.config().radius().sample(random);
        int topHeight = context.config().topHeight().sample(random);
        int bottomHeight = context.config().bottomHeight().sample(random);
        float cornerProbability = context.config().cornerProbability();

        for (int i = 0; i < topHeight; i++) {
            int currentRadius;
            float currentProbability = cornerProbability;
            if (i == topHeight - 1) {
                currentRadius = 1;
                currentProbability = 0;
            } else if (i >= topHeight * 0.8) {
                currentRadius = (int) Math.floor(radius * 0.35);
            } else if (i >= topHeight * 0.6) {
                if (i >= topHeight * 0.8 - 1) {
                    currentProbability = 0;
                }
                currentRadius = (int) Math.floor(radius * 0.75);
            } else {
                if (i >= topHeight * 0.6 - 1) {
                    currentProbability = 0;
                }
                currentRadius = radius;
            }

            placeRadius(trunkProvider, currentRadius, currentProbability, level, pos.above(i), random);
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

            placeRadius(trunkProvider, currentRadius, cornerProbability, level, pos.below(i), random);
        }

        return true;
    }

    private void placeRadius(BlockStateProvider block, int radius, float cornerProbability, WorldGenLevel level, BlockPos pos, RandomSource random) {
        switch (radius) {
            case 1 -> {trySetBlock(block.getState(level, random, pos), pos, level);
                for (Direction dir : HORIZONTAL_DIRECTIONS) {
                    BlockPos dirPos = pos.offset(dir.getUnitVec3i());
                    trySetBlock(block.getState(level, random, dirPos), dirPos, level);
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.offset(dir.getCounterClockWise().getUnitVec3i())),
                                dirPos.offset(dir.getCounterClockWise().getUnitVec3i()), level);
                    }
                }
            }
            case 2 -> {
                for (Direction dir : HORIZONTAL_DIRECTIONS) {
                    BlockPos dirPos = pos.offset(dir.getUnitVec3i().multiply(2));
                    trySetBlock(block.getState(level, random, dirPos), dirPos, level);
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.offset(dir.getCounterClockWise().getUnitVec3i())),
                                dirPos.offset(dir.getCounterClockWise().getUnitVec3i()), level);
                    }
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.offset(dir.getClockWise().getUnitVec3i())),
                                dirPos.offset(dir.getClockWise().getUnitVec3i()), level);
                    }
                }
                placeRadius(block, 1, 1f, level, pos, random);
            }
            case 3 -> {
                for (Direction dir : HORIZONTAL_DIRECTIONS) {
                    BlockPos dirPos = pos.offset(dir.getUnitVec3i().multiply(3));
                    trySetBlock(block.getState(level, random, dirPos), dirPos, level);
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.offset(dir.getCounterClockWise().getUnitVec3i())),
                                dirPos.offset(dir.getCounterClockWise().getUnitVec3i()), level);
                    }
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.offset(dir.getClockWise().getUnitVec3i())),
                                dirPos.offset(dir.getClockWise().getUnitVec3i()), level);
                    }

                    BlockPos diagPos = pos.offset(dir.getUnitVec3i().multiply(2))
                            .offset(dir.getClockWise().getUnitVec3i().multiply(2));
                    trySetBlock(block.getState(level, random, diagPos), diagPos, level);
                }
                placeRadius(block, 2, 1f, level, pos, random);
            }
            case 4 -> {
                for (Direction dir : HORIZONTAL_DIRECTIONS) {
                    BlockPos dirPos = pos.offset(dir.getUnitVec3i().multiply(4));
                    trySetBlock(block.getState(level, random, dirPos), dirPos, level);
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.offset(dir.getCounterClockWise().getUnitVec3i())),
                                dirPos.offset(dir.getCounterClockWise().getUnitVec3i()), level);
                    }
                    if (random.nextFloat() < cornerProbability) {
                        trySetBlock(block.getState(level, random,
                                        dirPos.offset(dir.getClockWise().getUnitVec3i())),
                                dirPos.offset(dir.getClockWise().getUnitVec3i()), level);
                    }

                    BlockPos diagPos1 = pos.offset(dir.getUnitVec3i().multiply(3))
                            .offset(dir.getClockWise().getUnitVec3i().multiply(2));
                    trySetBlock(block.getState(level, random, diagPos1), diagPos1, level);
                    BlockPos diagPos2 = pos.offset(dir.getUnitVec3i().multiply(2))
                            .offset(dir.getClockWise().getUnitVec3i().multiply(3));
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

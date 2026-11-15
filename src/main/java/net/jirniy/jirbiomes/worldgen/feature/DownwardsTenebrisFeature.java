package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.MapCodec;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.block.custom.PlantLikeLeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.material.Fluids;

public record DownwardsTenebrisFeature() implements Feature {
    public static final MapCodec<DownwardsTenebrisFeature> CODEC = MapCodec.unit(DownwardsTenebrisFeature::new);

    @Override
    public MapCodec<DownwardsTenebrisFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        for (int i = 0; i <= 9; i++) {
            if (!level.getBlockState(origin.below(i)).canBeReplaced() || level.getFluidState(origin.below(i)).is(Fluids.LAVA)) {
                return false;
            }
        }

        int height = random.nextIntBetweenInclusive(2, 7);
        int leavesHeight = random.nextIntBetweenInclusive(1, 2);

        for (int i = 0; i <= height + 1; i++) {
            if (level.getBlockState(origin.below(i)).is(BlockTags.FEATURES_CANNOT_REPLACE)) {
                return false;
            }
            if (i < height) {
                level.setBlock(origin.below(i), ModBlocks.TENEBRIS_LOG.defaultBlockState(), 3);
            } else if (i == height) {
                level.setBlock(origin.below(i), Blocks.SHROOMLIGHT.defaultBlockState(), 3);
            } else {
                BlockState leafState = ModBlocks.TENEBRIS_LEAVES.defaultBlockState()
                        .setValue(PlantLikeLeavesBlock.BLOCKSHAPE, AttachFace.CEILING);
                level.setBlock(origin.below(i), leafState, 3);
            }
            if (i > leavesHeight && i < height + 1) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockPos dirPos = origin.below(i).relative(direction);
                    if (level.getBlockState(dirPos).is(BlockTags.FEATURES_CANNOT_REPLACE)) {
                        return false;
                    }
                    if (level.getBlockState(dirPos).canBeReplaced()) {
                        BlockState leafState = ModBlocks.TENEBRIS_LEAVES.defaultBlockState()
                                .setValue(PlantLikeLeavesBlock.BLOCKSHAPE, AttachFace.WALL)
                                .setValue(PlantLikeLeavesBlock.FACING, direction.getOpposite());
                        level.setBlock(origin.below(i).relative(direction), leafState, 3);
                    }
                }
            }
        }
        return true;
    }
}

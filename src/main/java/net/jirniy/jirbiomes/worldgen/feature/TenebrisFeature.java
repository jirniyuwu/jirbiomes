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

public record TenebrisFeature() implements Feature {
    public static final MapCodec<TenebrisFeature> CODEC = MapCodec.unit(TenebrisFeature::new);

    @Override
    public MapCodec<TenebrisFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        for (int i = 0; i <= 5; i++) {
            if (!level.getBlockState(origin.above(i)).canBeReplaced()) {
                return false;
            }
        }

        int height = random.nextIntBetweenInclusive(3, 4);
        int leavesHeight = random.nextIntBetweenInclusive(0, 1);

        for (int i = 0; i <= height + 1; i++) {
            if (level.getBlockState(origin.above(i)).is(BlockTags.FEATURES_CANNOT_REPLACE)) {
                return false;
            }
            if (i < height) {
                level.setBlock(origin.above(i), ModBlocks.TENEBRIS_LOG.defaultBlockState(), 3);
            } else if (i == height) {
                level.setBlock(origin.above(i), Blocks.SHROOMLIGHT.defaultBlockState(), 3);
            } else {
                BlockState leafState = ModBlocks.TENEBRIS_LEAVES.defaultBlockState()
                        .setValue(PlantLikeLeavesBlock.BLOCKSHAPE, AttachFace.FLOOR);
                level.setBlock(origin.above(i), leafState, 3);
            }
            if (i > leavesHeight && i < height + 1) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockPos dirPos = origin.above(i).relative(direction);
                    if (level.getBlockState(dirPos).is(BlockTags.FEATURES_CANNOT_REPLACE)) {
                        return false;
                    }
                    if (level.getBlockState(dirPos).canBeReplaced()) {
                        BlockState leafState = ModBlocks.TENEBRIS_LEAVES.defaultBlockState()
                                .setValue(PlantLikeLeavesBlock.BLOCKSHAPE, AttachFace.WALL)
                                .setValue(PlantLikeLeavesBlock.FACING, direction.getOpposite());
                        level.setBlock(origin.above(i).relative(direction), leafState, 3);
                    }
                }
            }
        }
        return true;
    }
}

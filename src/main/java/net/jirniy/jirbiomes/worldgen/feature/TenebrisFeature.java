package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.Codec;
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
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class TenebrisFeature extends Feature<NoneFeatureConfiguration> {
    public TenebrisFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        for (int i = 0; i <= 5; i++) {
            if (!level.getBlockState(pos.above(i)).canBeReplaced()) {
                return false;
            }
        }

        int height = random.nextIntBetweenInclusive(3, 4);
        int leavesHeight = random.nextIntBetweenInclusive(0, 1);

        for (int i = 0; i <= height + 1; i++) {
            if (level.getBlockState(pos.above(i)).is(BlockTags.FEATURES_CANNOT_REPLACE)) {
                return false;
            }
            if (i < height) {
                level.setBlock(pos.above(i), ModBlocks.TENEBRIS_LOG.defaultBlockState(), 3);
            } else if (i == height) {
                level.setBlock(pos.above(i), Blocks.SHROOMLIGHT.defaultBlockState(), 3);
            } else {
                BlockState leafState = ModBlocks.TENEBRIS_LEAVES.defaultBlockState()
                        .setValue(PlantLikeLeavesBlock.BLOCKSHAPE, AttachFace.FLOOR);
                level.setBlock(pos.above(i), leafState, 3);
            }
            if (i > leavesHeight && i < height + 1) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockPos dirPos = pos.above(i).relative(direction);
                    if (level.getBlockState(dirPos).is(BlockTags.FEATURES_CANNOT_REPLACE)) {
                        return false;
                    }
                    if (level.getBlockState(dirPos).canBeReplaced()) {
                        BlockState leafState = ModBlocks.TENEBRIS_LEAVES.defaultBlockState()
                                .setValue(PlantLikeLeavesBlock.BLOCKSHAPE, AttachFace.WALL)
                                .setValue(PlantLikeLeavesBlock.FACING, direction.getOpposite());
                        level.setBlock(pos.above(i).relative(direction), leafState, 3); 
                    }
                }
            }
        }
        return true;
    }
}

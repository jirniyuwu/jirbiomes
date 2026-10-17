package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jirniy.jirbiomes.block.custom.PlantLikeLeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record TenebrisFeature(
        BlockStateProvider trunk,
        IntProvider height,
        Direction direction,
        BlockStateProvider leaves,
        IntProvider leavesHeight,
        BlockStateProvider tipBlock
) implements Feature {
    public static final MapCodec<TenebrisFeature> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
                    BlockStateProvider.DIRECT_CODEC.fieldOf("trunk").forGetter(TenebrisFeature::trunk),
                    IntProviders.codec(1, 32).fieldOf("height").forGetter(TenebrisFeature::height),
                    Direction.VERTICAL_CODEC.fieldOf("direction").forGetter(TenebrisFeature::direction),
                    BlockStateProvider.DIRECT_CODEC.fieldOf("leaves").forGetter(TenebrisFeature::leaves),
                    IntProviders.codec(0, 4).fieldOf("leaves_height").forGetter(TenebrisFeature::leavesHeight),
                    BlockStateProvider.DIRECT_CODEC.fieldOf("tip_block").forGetter(TenebrisFeature::tipBlock)
            ).apply(i, TenebrisFeature::new)
    );

    @Override
    public MapCodec<? extends Feature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        int height = this.height.sample(random);
        int leavesHeight = this.leavesHeight.sample(random);

        for (int i = 0; i < height; i++) {
            if (!level.getBlockState(origin.above(i)).canBeReplaced()) {
                return false;
            }
        }

//        int height = random.nextIntBetweenInclusive(3, 4);
//        int leavesHeight = random.nextIntBetweenInclusive(0, 1);

        for (int i = 0; i <= height + 1; i++) {
            BlockPos pos = origin.relative(direction, i);
            if (level.getBlockState(pos).is(BlockTags.FEATURES_CANNOT_REPLACE)) {
                return false;
            }
            if (i < height) {
                level.setBlock(pos, trunk.getState(level, random, pos), 3);
            } else if (i == height) {
                level.setBlock(pos, tipBlock.getState(level, random, pos), 3);
            } else {
                BlockState leafState = leaves.getState(level, random, pos)
                        .trySetValue(PlantLikeLeavesBlock.BLOCKSHAPE,
                                direction == Direction.UP ? AttachFace.FLOOR : AttachFace.CEILING);
                level.setBlock(pos, leafState, 3);
            }
            if (i > leavesHeight && i < height + 1) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockPos dirPos = pos.relative(direction);
                    if (level.getBlockState(dirPos).is(BlockTags.FEATURES_CANNOT_REPLACE)) {
                        return false;
                    }
                    if (level.getBlockState(dirPos).canBeReplaced()) {
                        BlockState leafState = trunk.getState(level, random, pos)
                                .trySetValue(PlantLikeLeavesBlock.BLOCKSHAPE, AttachFace.WALL)
                                .trySetValue(PlantLikeLeavesBlock.FACING, direction.getOpposite());
                        level.setBlock(pos.relative(direction), leafState, 3);
                    }
                }
            }
        }
        return true;
    }
}

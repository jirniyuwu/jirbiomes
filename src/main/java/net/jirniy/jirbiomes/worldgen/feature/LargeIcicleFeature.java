package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.codec.RegistryCodecs;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.FloatProviders;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SpeleothemUtils;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

// copied from large dripstone feature and just replaced dripstone with mod blocks
public record LargeIcicleFeature(
        BlockStateProvider blockProvider,
        HolderSet<Block> replaceableBlocks,
        int floorToCeilingSearchRange,
        IntProvider columnRadius,
        FloatProvider heightScale,
        float maxColumnRadiusToCaveHeightRatio,
        FloatProvider stalactiteBluntness,
        FloatProvider stalagmiteBluntness,
        FloatProvider windSpeed,
        int minRadiusForWind,
        float minBluntnessForWind
) implements Feature {
    public static final MapCodec<LargeIcicleFeature> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                            BlockStateProvider.DIRECT_CODEC.fieldOf("block_provider").forGetter(LargeIcicleFeature::blockProvider),
                            RegistryCodecs.holderSet(Registries.BLOCK).fieldOf("replaceable_blocks").forGetter(LargeIcicleFeature::replaceableBlocks),
                            Codec.intRange(1, 512).optionalFieldOf("floor_to_ceiling_search_range", 30).forGetter(LargeIcicleFeature::floorToCeilingSearchRange),
                            IntProviders.codec(1, 16).fieldOf("column_radius").forGetter(LargeIcicleFeature::columnRadius),
                            FloatProviders.codec(0.0F, 20.0F).fieldOf("height_scale").forGetter(LargeIcicleFeature::heightScale),
                            Codec.floatRange(0.1F, 1.0F).fieldOf("max_column_radius_to_cave_height_ratio").forGetter(LargeIcicleFeature::maxColumnRadiusToCaveHeightRatio),
                            FloatProviders.codec(0.1F, 10.0F).fieldOf("stalactite_bluntness").forGetter(LargeIcicleFeature::stalactiteBluntness),
                            FloatProviders.codec(0.1F, 10.0F).fieldOf("stalagmite_bluntness").forGetter(LargeIcicleFeature::stalagmiteBluntness),
                            FloatProviders.codec(0.0F, 2.0F).fieldOf("wind_speed").forGetter(LargeIcicleFeature::windSpeed),
                            Codec.intRange(0, 100).fieldOf("min_radius_for_wind").forGetter(LargeIcicleFeature::minRadiusForWind),
                            Codec.floatRange(0.0F, 5.0F).fieldOf("min_bluntness_for_wind").forGetter(LargeIcicleFeature::minBluntnessForWind)
                    )
                    .apply(i, LargeIcicleFeature::new)
    );

    @Override
    public MapCodec<LargeIcicleFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        if (!isEmptyOrWater(level, origin)) {
            return false;
        }

        Optional<Column> column = Column.scan(
                level,
                origin,
                this.floorToCeilingSearchRange,
                SpeleothemUtils::isEmptyOrWater,
                state -> SpeleothemUtils.isBaseOrLava(state, blockProvider.getState(level, random, origin).getBlock(), this.replaceableBlocks)
        );
        if (!(!column.isEmpty() && column.get() instanceof Column.Range columnRange)) {
            return false;
        }

        if (columnRange.height() < 4) {
            return false;
        }

        int maxColumnRadiusBasedOnColumnHeight = (int)(columnRange.height() * this.maxColumnRadiusToCaveHeightRatio);
        int maxColumnRadius = Mth.clamp(maxColumnRadiusBasedOnColumnHeight, this.columnRadius.minInclusive(), this.columnRadius.maxInclusive());
        int radius = Mth.randomBetweenInclusive(random, this.columnRadius.minInclusive(), maxColumnRadius);
        LargeIcicleFeature.LargeDripstone stalactite = makeDripstone(
                origin.atY(columnRange.ceiling() - 1), false, random, radius, this.stalactiteBluntness, this.heightScale
        );
        LargeIcicleFeature.LargeDripstone stalagmite = makeDripstone(
                origin.atY(columnRange.floor() + 1), true, random, radius, this.stalagmiteBluntness, this.heightScale
        );
        LargeIcicleFeature.WindOffsetter wind;
        if (stalactite.isSuitableForWind(this.minRadiusForWind, this.minBluntnessForWind)
                && stalagmite.isSuitableForWind(this.minRadiusForWind, this.minBluntnessForWind)) {
            wind = new LargeIcicleFeature.WindOffsetter(origin.getY(), random, this.windSpeed, 16 - radius);
        } else {
            wind = LargeIcicleFeature.WindOffsetter.noWind();
        }

        boolean stalactiteBaseEmbeddedInStone = stalactite.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(level, wind);
        boolean stalagmiteBaseEmbeddedInStone = stalagmite.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(level, wind);
        if (stalactiteBaseEmbeddedInStone) {
            stalactite.placeBlocks(level, random, wind, blockProvider);
        }

        if (stalagmiteBaseEmbeddedInStone) {
            stalagmite.placeBlocks(level, random, wind, blockProvider);
        }

        if (SharedConstants.DEBUG_LARGE_DRIPSTONE) {
            this.placeDebugMarkers(level, origin, columnRange, wind, random);
        }

        return true;
    }

    private static LargeIcicleFeature.LargeDripstone makeDripstone(
            final BlockPos root, final boolean pointingUp, final RandomSource random, final int radius, final FloatProvider bluntness, final FloatProvider heightScale
    ) {
        return new LargeIcicleFeature.LargeDripstone(root, pointingUp, radius, bluntness.sample(random), heightScale.sample(random));
    }

    private void placeDebugMarkers(final WorldGenLevel level, final BlockPos origin, final Column.Range range, final LargeIcicleFeature.WindOffsetter wind, final RandomSource random) {
        level.setBlock(wind.offset(origin.atY(range.ceiling() - 1)), Blocks.DIAMOND_BLOCK.defaultBlockState(), 2);
        level.setBlock(wind.offset(origin.atY(range.floor() + 1)), Blocks.GOLD_BLOCK.defaultBlockState(), 2);

        for (BlockPos.MutableBlockPos pos = origin.atY(range.floor() + 2).mutable(); pos.getY() < range.ceiling() - 1; pos.move(Direction.UP)) {
            BlockPos windAdjustedPos = wind.offset(pos);
            if (isEmptyOrWater(level, windAdjustedPos) || level.getBlockState(windAdjustedPos).is(blockProvider.getState(level, random, windAdjustedPos).getBlock())) {
                level.setBlock(windAdjustedPos, Blocks.CREEPER_HEAD.defaultBlockState(), 2);
            }
        }
    }

    private static final class LargeDripstone {
        private BlockPos root;
        private final boolean pointingUp;
        private int radius;
        private final double bluntness;
        private final double scale;

        private LargeDripstone(final BlockPos root, final boolean pointingUp, final int radius, final double bluntness, final double scale) {
            this.root = root;
            this.pointingUp = pointingUp;
            this.radius = radius;
            this.bluntness = bluntness;
            this.scale = scale;
        }

        private int getHeight() {
            return this.getHeightAtRadius(0.0F);
        }

        private boolean moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(final WorldGenLevel level, final LargeIcicleFeature.WindOffsetter wind) {
            while (this.radius > 1) {
                BlockPos.MutableBlockPos newRoot = this.root.mutable();
                int maxTries = Math.min(10, this.getHeight());

                for (int i = 0; i < maxTries; i++) {
                    if (level.getBlockState(newRoot).is(Blocks.LAVA)) {
                        return false;
                    }

                    if (isCircleMostlyEmbeddedInStone(level, wind.offset(newRoot), this.radius)) {
                        this.root = newRoot;
                        return true;
                    }

                    newRoot.move(this.pointingUp ? Direction.DOWN : Direction.UP);
                }

                this.radius /= 2;
            }

            return false;
        }

        private int getHeightAtRadius(final float checkRadius) {
            return (int)getSpeleothemHeight(checkRadius, this.radius, this.scale, this.bluntness);
        }

        private void placeBlocks(final WorldGenLevel level, final RandomSource random, final LargeIcicleFeature.WindOffsetter wind, final BlockStateProvider blockProvider) {
            for (int dx = -this.radius; dx <= this.radius; dx++) {
                for (int dz = -this.radius; dz <= this.radius; dz++) {
                    float currentRadius = Mth.sqrt(dx * dx + dz * dz);
                    if (!(currentRadius > this.radius)) {
                        int height = this.getHeightAtRadius(currentRadius);
                        if (height > 0) {
                            if (random.nextFloat() < 0.2) {
                                height = (int)(height * Mth.randomBetween(random, 0.8F, 1.0F));
                            }

                            BlockPos.MutableBlockPos pos = this.root.offset(dx, 0, dz).mutable();
                            boolean hasBeenOutOfStone = false;
                            int maxY = this.pointingUp ? level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ()) : Integer.MAX_VALUE;

                            for (int i = 0; i < height && pos.getY() < maxY; i++) {
                                BlockPos windAdjustedPos = wind.offset(pos);
                                if (isEmptyOrWaterOrLava(level, windAdjustedPos)) {
                                    hasBeenOutOfStone = true;
                                    BlockState block = SharedConstants.DEBUG_LARGE_DRIPSTONE ? Blocks.GLASS.defaultBlockState() : blockProvider.getState(level, random, windAdjustedPos);
                                    level.setBlock(windAdjustedPos, block, 2);
                                } else if (hasBeenOutOfStone && level.getBlockState(windAdjustedPos).is(BlockTags.BASE_STONE_OVERWORLD)) {
                                    break;
                                }

                                pos.move(this.pointingUp ? Direction.UP : Direction.DOWN);
                            }
                        }
                    }
                }
            }
        }

        private boolean isSuitableForWind(final int minRadiusForWind, final float minBluntnessForWind) {
            return this.radius >= minRadiusForWind && this.bluntness >= minBluntnessForWind;
        }
    }

    private static final class WindOffsetter {
        private final int originY;
        private final @Nullable Vec3 windSpeed;
        private final int maxOffset;

        private WindOffsetter(final int originY, final RandomSource random, final FloatProvider windSpeedRange, final int maxOffset) {
            this.originY = originY;
            this.maxOffset = maxOffset;
            float speed = windSpeedRange.sample(random);
            float direction = Mth.randomBetween(random, 0.0F, (float) Math.PI);
            this.windSpeed = new Vec3(Mth.cos(direction) * speed, 0.0, Mth.sin(direction) * speed);
        }

        private WindOffsetter() {
            this.originY = 0;
            this.windSpeed = null;
            this.maxOffset = 0;
        }

        private static LargeIcicleFeature.WindOffsetter noWind() {
            return new LargeIcicleFeature.WindOffsetter();
        }

        private BlockPos offset(final BlockPos pos) {
            if (this.windSpeed == null) {
                return pos;
            }

            int dy = this.originY - pos.getY();
            Vec3 totalWindAdjust = this.windSpeed.scale(dy);
            int dx = Mth.clamp(Mth.floor(totalWindAdjust.x), -this.maxOffset, this.maxOffset);
            int dz = Mth.clamp(Mth.floor(totalWindAdjust.z), -this.maxOffset, this.maxOffset);
            return pos.offset(dx, 0, dz);
        }
    }

    private static boolean isEmptyOrWaterOrLava(final LevelAccessor level, final BlockPos pos) {
        return level.isStateAtPosition(pos, SpeleothemUtils::isEmptyOrWaterOrLava);
    }
    private static double getSpeleothemHeight(double xzDistanceFromCenter, final double speleothemRadius, final double scale, final double bluntness) {
        if (xzDistanceFromCenter < bluntness) {
            xzDistanceFromCenter = bluntness;
        }

        double cutoff = 0.384;
        double r = xzDistanceFromCenter / speleothemRadius * 0.384;
        double part1 = 0.75 * Math.pow(r, 1.3333333333333333);
        double part2 = Math.pow(r, 0.6666666666666666);
        double part3 = 0.3333333333333333 * Math.log(r);
        double heightRelativeToMaxRadius = scale * (part1 - part2 - part3);
        heightRelativeToMaxRadius = Math.max(heightRelativeToMaxRadius, 0.0);
        return heightRelativeToMaxRadius / 0.384 * speleothemRadius;
    }
    private static boolean isCircleMostlyEmbeddedInStone(final WorldGenLevel level, final BlockPos center, final int xzRadius) {
        if (isEmptyOrWaterOrLava(level, center)) {
            return false;
        }

        float arcLength = 6.0F;
        float angleIncrement = 6.0F / xzRadius;

        for (float angle = 0.0F; angle < (float) (Math.PI * 2); angle += angleIncrement) {
            int dx = (int)(Mth.cos(angle) * xzRadius);
            int dz = (int)(Mth.sin(angle) * xzRadius);
            if (isEmptyOrWaterOrLava(level, center.offset(dx, 0, dz))) {
                return false;
            }
        }

        return true;
    }
    private static boolean isEmptyOrWater(final LevelAccessor level, final BlockPos pos) {
        return level.isStateAtPosition(pos, SpeleothemUtils::isEmptyOrWater);
    }
}

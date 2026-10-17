package net.jirniy.jirbiomes.block.custom;

import net.jirniy.jirbiomes.worldgen.ModPlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public class CustomGrassBlock extends SpreadingSnowyBlock implements BonemealableBlock {
    protected final ResourceKey<Block> replaceable;
    protected final ResourceKey<PlacedFeature> placement;
    protected final boolean canBonemeal;

    public CustomGrassBlock(ResourceKey<Block> replaceable, ResourceKey<PlacedFeature> placedFeature, boolean bonemealable, final BlockBehaviour.Properties properties) {
        super(properties, replaceable);
        this.replaceable = replaceable;
        this.placement = placedFeature;
        this.canBonemeal = bonemealable;
    }

    public CustomGrassBlock(ResourceKey<Block> replaceable, final BlockBehaviour.Properties properties) {
        super(properties, replaceable);
        this.replaceable = replaceable;
        this.placement = ModPlacedFeatures.NOOP;
        this.canBonemeal = false;
    }

    public CustomGrassBlock(ResourceKey<Block> replaceable, ResourceKey<PlacedFeature> placedFeature, final BlockBehaviour.Properties properties) {
        super(properties, replaceable);
        this.replaceable = replaceable;
        this.placement = placedFeature;
        this.canBonemeal = true;
    }

    @Override
    public boolean isValidBonemealTarget(final LevelReader level, final BlockPos pos, final BlockState state, BonemealSource source) {
        return this.canBonemeal && level.getBlockState(pos.above()).isAir() && level.isInsideBuildHeight(pos.above());
    }

    @Override
    public boolean isBonemealSuccess(final Level level, final RandomSource random, final BlockPos pos, final BlockState state, BonemealSource source) {
        return this.canBonemeal;
    }

    @Override
    public void performBonemeal(final ServerLevel level, final RandomSource random, final BlockPos pos, final BlockState state, BonemealSource source) {
        BlockPos above = pos.above();
        BlockState grass = Blocks.SHORT_GRASS.defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> grassFeature = level.registryAccess()
                .lookupOrThrow(Registries.PLACED_FEATURE)
                .get(this.placement);

        label48:
        for (int j = 0; j < 128; j++) {
            BlockPos testPos = above;

            for (int i = 0; i < j / 16; i++) {
                testPos = testPos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!level.getBlockState(testPos.below()).is(this) || level.getBlockState(testPos).isCollisionShapeFullBlock(level, testPos)) {
                    continue label48;
                }
            }

            BlockState testState = level.getBlockState(testPos);
            if (testState.is(grass.getBlock()) && random.nextInt(10) == 0) {
                BonemealableBlock bonemealableBlock = (BonemealableBlock)grass.getBlock();
                if (bonemealableBlock.isValidBonemealTarget(level, testPos, testState, source)) {
                    bonemealableBlock.performBonemeal(level, random, testPos, testState, source);
                }
            }

            if (testState.isAir() && !level.isOutsideBuildHeight(testPos)) {
                if (random.nextFloat() < 0.125F) {
                    List<Feature> features = level.getBiome(testPos).value().getGenerationSettings().getBoneMealFeatures();
                    if (features.isEmpty()) {
                        return;
                    }

                    Feature placementFeature = Util.getRandom(features, random);
                    placementFeature.place(level, level.getChunkSource().getGenerator(), random, testPos);
                } else if (grassFeature.isPresent()) {
                    grassFeature.get().value().place(level, level.getChunkSource().getGenerator(), random, testPos);
                }
            }
        }
    }

    @Override
    public BonemealableBlock.Type getType() {
        return BonemealableBlock.Type.NEIGHBOR_SPREADER;
    }
}

package net.jirniy.jirbiomes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Optional;

public class BonemealableFeatureBlock extends VegetationBlock implements BonemealableBlock {
    private final ResourceKey<PlacedFeature> featurePlacement;
    private final float boneMealChance;

    public BonemealableFeatureBlock(ResourceKey<PlacedFeature> featurePlacement, Properties properties) {
        super(properties);
        this.boneMealChance = 1f;
        this.featurePlacement = featurePlacement;
    }

    public BonemealableFeatureBlock(ResourceKey<PlacedFeature> featurePlacement, float probability, Properties properties) {
        super(properties);
        this.boneMealChance = probability;
        this.featurePlacement = featurePlacement;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, BonemealSource source) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
        return random.nextFloat() < this.boneMealChance &&  level.registryAccess()
                .lookupOrThrow(Registries.PLACED_FEATURE)
                .get(this.featurePlacement).isPresent();
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
        Optional<Holder.Reference<PlacedFeature>> feature = level.registryAccess()
                .lookupOrThrow(Registries.PLACED_FEATURE)
                .get(this.featurePlacement);
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 1);
        if (!feature.get().value().place(level, level.getChunkSource().getGenerator(), random, pos)) {
            level.setBlock(pos, state, 1);
        };
    }
}

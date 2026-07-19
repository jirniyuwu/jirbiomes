package net.jirniy.jirbiomes.block.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Optional;

public class BonemealableFeatureBlock extends VegetationBlock implements BonemealableBlock {
    public static final MapCodec<BonemealableFeatureBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ResourceKey.codec(Registries.PLACED_FEATURE).fieldOf("featurePlacement").forGetter(b -> b.featurePlacement),
                    Codec.floatRange(0, 1).optionalFieldOf("bone_meal_chance", 1f).forGetter(block -> block.boneMealChance),
                    propertiesCodec())
                    .apply(instance, BonemealableFeatureBlock::new)
    );
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
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return random.nextFloat() < this.boneMealChance &&  level.registryAccess()
                .lookupOrThrow(Registries.PLACED_FEATURE)
                .get(this.featurePlacement).isPresent();
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        Optional<Holder.Reference<PlacedFeature>> feature = level.registryAccess()
                .lookupOrThrow(Registries.PLACED_FEATURE)
                .get(this.featurePlacement);
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 1);
        if (!feature.get().value().place(level, level.getChunkSource().getGenerator(), random, pos)) {
            level.setBlock(pos, state, 1);
        };
    }
}

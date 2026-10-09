package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record TaxiteConfiguration(
        BlockStateProvider trunk,
        IntProvider radius,
        IntProvider topHeight,
        IntProvider bottomHeight,
        float cornerProbability,
        float topBlockProbability
) implements FeatureConfiguration {
    public static final Codec<Float> CHANCE_RANGE = Codec.floatRange(0.0f, 1.0f);
    public static final Codec<TaxiteConfiguration> CODEC = RecordCodecBuilder.create(i -> i.group(
            BlockStateProvider.CODEC.fieldOf("trunk").forGetter(TaxiteConfiguration::trunk),
            IntProviders.codec(1, 4).fieldOf("radius").forGetter(TaxiteConfiguration::radius),
            IntProviders.codec(1, 60).fieldOf("top_height").forGetter(TaxiteConfiguration::topHeight),
            IntProviders.codec(1, 60).fieldOf("bottom_height").forGetter(TaxiteConfiguration::bottomHeight),
            CHANCE_RANGE.fieldOf("corner_probability").forGetter(TaxiteConfiguration::cornerProbability),
            CHANCE_RANGE.fieldOf("top_block_probability").forGetter(TaxiteConfiguration::topBlockProbability)
        ).apply(i, TaxiteConfiguration::new)
    );
}

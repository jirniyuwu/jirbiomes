package net.jirniy.jirbiomes.worldgen.feature;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.LargeDripstoneConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ModFeatures {
    public static final Feature<NoneFeatureConfiguration> TENEBRIS = create("tenebris", new TenebrisFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> TENEBRIS_DOWNWARDS = create("tenebris_downwards", new DownwardsTenebrisFeature(NoneFeatureConfiguration.CODEC));

    public static final Feature<LargeDripstoneConfiguration> LARGE_ICICLE = create("large_icicle", new LargeIcicleFeature(LargeDripstoneConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F create(final String name, final F feature) {
        return (F)(Registry.register(BuiltInRegistries.FEATURE, JirniyBiomes.id(name), feature));
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering features for " + JirniyBiomes.MOD_ID);
    }
}

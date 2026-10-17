package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.MapCodec;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;

public class ModFeatureTypes {
    public static final MapCodec<LargeIcicleFeature> LARGE_ICICLE = register("large_icicle", LargeIcicleFeature.CODEC);
    public static final MapCodec<TaxiteFeature> TAXITE = register("taxite", TaxiteFeature.CODEC);
    public static final MapCodec<TenebrisFeature> TENEBRIS = register("tenebris", TenebrisFeature.CODEC);

    private static <T extends Feature> MapCodec<T> register(String name, MapCodec<T> codec) {
        return (MapCodec<T>) Registry.register(BuiltInRegistries.FEATURE_TYPE, JirniyBiomes.id(name), codec);
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering feature types for " + JirniyBiomes.MOD_ID);
    }
}

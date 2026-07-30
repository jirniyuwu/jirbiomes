package net.jirniy.jirbiomes.worldgen.feature;

import com.mojang.serialization.MapCodec;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class ModFoliagePlacerTypes<P extends FoliagePlacer> {
    public static final FoliagePlacerType<PalmFoliagePlacer> PALM_FOLIAGE_PLACER = create("palm_foliage_placer", PalmFoliagePlacer.CODEC);
    private final MapCodec<P> codec;

    private static <P extends FoliagePlacer> FoliagePlacerType<P> create(final String name, final MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, JirniyBiomes.id(name), new FoliagePlacerType(codec));
    }
    public ModFoliagePlacerTypes(final MapCodec<P> codec) {
        this.codec = codec;
    }
    public MapCodec<P> codec() {
        return this.codec;
    }

    public static void register() {
        JirniyBiomes.LOGGER.info("registering foliage placers for " + JirniyBiomes.MOD_ID);
    }
}

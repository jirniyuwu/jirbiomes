package net.jirniy.jirbiomes.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.worldgen.biome.ModBiomes;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomeModifications {
    public static void register() {
        JirniyBiomes.LOGGER.info("registering biome modifications for " + JirniyBiomes.MOD_ID);

        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_SAVANNA),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.GINKGO_TREE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SAVANNA_PLATEAU),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.GINKGO_TREE_BONUS_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_SAVANNA),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DRIED_GRASS_PATCH_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.HAS_DESERT_PYRAMID),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DRIED_GRASS_PATCH_DESERT_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BADLANDS, Biomes.WOODED_BADLANDS),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DRIED_GRASS_PATCH_DESERT_PLACED);

        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.HAS_DESERT_PYRAMID),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BARREL_CACTUS_PATCH);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_BADLANDS),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BARREL_CACTUS_PATCH);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SWAMP, Biomes.MANGROVE_SWAMP),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.WET_GRASS_PATCH_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.WET_GRASS_PATCH_PLACED);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.NETHERSTONE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BRIMSTONE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.IGNITED_BRIMSTONE_PLACED);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.BRIMSTONE_CRAGS),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BRIMGRASS_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.BRIMSTONE_CRAGS),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.TENEBRIS_BUD_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.BRIMSTONE_CRAGS),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.TENEBRIS_PLACED);
    }
}

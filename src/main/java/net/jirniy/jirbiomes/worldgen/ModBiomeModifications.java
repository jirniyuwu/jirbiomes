package net.jirniy.jirbiomes.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.misc.CommonTag;
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
        BiomeModifications.addFeature(BiomeSelectors.tag(CommonTag.ofBiome("is_desert")),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PALM_TREE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BEACH, Biomes.SNOWY_BEACH),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PALM_TREE_BEACH_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PALM_TREE_OCEAN_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_DEEP_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PALM_TREE_OCEAN_PLACED);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DESERT),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DRIED_DIRT_PATCH_DESERT_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DESERT),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DRIED_GRASS_PATCH_DESERT_PLACED);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SNOWY_PLAINS, Biomes.SNOWY_SLOPES),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FROZEN_GRASS_PLACED);

        BiomeModifications.addFeature(BiomeSelectors.tag(CommonTag.ofBiome("is_desert")),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BARREL_CACTUS_PATCH);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_BADLANDS),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BARREL_CACTUS_PATCH);

        BiomeModifications.addFeature(BiomeSelectors.tag(CommonTag.ofBiome("is_swamp")),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.WET_GRASS_PATCH_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.WET_GRASS_PATCH_PLACED);

        BiomeModifications.addFeature(BiomeSelectors.tag(CommonTag.ofBiome("is_swamp")),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.LOTUS_RARE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.LOTUS_RARE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.CHERRY_GROVE, Biomes.MANGROVE_SWAMP),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.LOTUS_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(CommonTag.ofBiome("is_swamp")),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CATTAIL_PATCH_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(CommonTag.ofBiome("is_swamp")),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.ALGAE_PATCH_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.ALGAE_PATCH_PLACED);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SEA_URCHIN_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.WARM_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SEA_URCHIN_BONUS_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SEA_URCHIN_SHIPWRECK_PLACED);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BEACH),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SAND_SHELLS_BEACH);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SNOWY_BEACH, Biomes.BEACH),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SAND_SHELLS_SMALL_BEACH);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SAND_SHELLS);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.WARM_OCEAN),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SAND_SHELLS_BONUS);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.NETHERSTONE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BRIMSTONE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.IGNITED_BRIMSTONE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SHARP_RIBS_SOUL_SAND_PLACED);
    }
}

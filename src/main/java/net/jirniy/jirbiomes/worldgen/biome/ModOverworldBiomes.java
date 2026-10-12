package net.jirniy.jirbiomes.worldgen.biome;

import net.jirniy.jirbiomes.particle.ModParticles;
import net.jirniy.jirbiomes.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModOverworldBiomes {
    public static Biome saltDeposit(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        Biome.BiomeBuilder biome = OverworldBiomes.baseBiome(0.9f, 0.5f);
        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(mobs);
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(placedFeatures, carvers)
                .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModPlacedFeatures.SALT_PLACED);
        OverworldBiomes.globalOverworldGeneration(generation);
        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        BiomeDefaultFeatures.addDefaultFlowers(generation);
        BiomeDefaultFeatures.addDefaultGrass(generation);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, true);

        return biome
                .specialEffects((new BiomeSpecialEffects.Builder().waterColor(0x91c2ed)).build())
                .mobSpawnSettings(mobs.build()).generationSettings(generation.build())
                .build();
    }

    public static Biome permafrostCaves(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        Biome.BiomeBuilder biome = OverworldBiomes.baseBiome(0.0f, 0.5f);
        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.dripstoneCavesSpawns(mobs);
        BiomeDefaultFeatures.snowySpawns(mobs, false);
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(placedFeatures, carvers);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generation);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generation);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generation);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIRT);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GRAVEL);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIORITE_UPPER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIORITE_LOWER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_ANDESITE_UPPER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_ANDESITE_LOWER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_TUFF);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.GLOW_LICHEN);
        BiomeDefaultFeatures.addDefaultSprings(generation);
        BiomeDefaultFeatures.addSurfaceFreezing(generation);
        BiomeDefaultFeatures.addPlainGrass(generation);
        BiomeDefaultFeatures.addFrozenSprings(generation);
        BiomeDefaultFeatures.addDefaultOres(generation, false);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        BiomeDefaultFeatures.addPlainVegetation(generation);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, false);
        generation
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, ModPlacedFeatures.LARGE_ICICLE_PLACED)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLUE_ICE_ORE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.ICICLE_CLUSTER_PLACED)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.POINTED_ICICLE_PLACED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FROZEN_GRASS_PLACED)
                .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModPlacedFeatures.ICE_TOP_LAYER);

        return biome
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_SNOWY_SLOPES))
                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ModParticles.SNOWFLAKE, 0.0005f))
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x95d3e6)
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xa2d5f5)
                .specialEffects((new BiomeSpecialEffects.Builder().waterColor(0x8fabf2)).build())
                .mobSpawnSettings(mobs.build()).generationSettings(generation.build())
                .build();
    }

    public static Biome polarDesert(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        Biome.BiomeBuilder biome = OverworldBiomes.baseBiome(0.0f, 0.1f);
        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.snowySpawns(mobs, false);
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(placedFeatures, carvers);
        OverworldBiomes.globalOverworldGeneration(generation);
        BiomeDefaultFeatures.addFrozenSprings(generation);
        BiomeDefaultFeatures.addDefaultOres(generation, true);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLUE_ICE_ORE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FROZEN_GRASS_PLACED)
                .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModPlacedFeatures.ICE_TOP_LAYER);

        return biome
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_FROZEN_PEAKS))
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x95d3e6)
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xa2d5f5)
                .specialEffects((new BiomeSpecialEffects.Builder().waterColor(0x8fabf2)).build())
                .mobSpawnSettings(mobs.build()).generationSettings(generation.build())
                .build();
    }

    public static Biome prototaxiteForest(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        Biome.BiomeBuilder biome = OverworldBiomes.baseBiome(2.0f, 2.0f);
        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.swampSpawns(mobs, 40);
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(placedFeatures, carvers);
        OverworldBiomes.globalOverworldGeneration(generation);
        generation
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.LARGE_PROTOTAXITE_PLACED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SMALL_PROTOTAXITE_PLACED)
                .addFeature(GenerationStep.Decoration.LAKES, ModPlacedFeatures.MUD_POOL_PLACED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PROTOTAXITE_SEPTA_GROWTH_PLACED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PROTOTAXITE_SEPTA_PATCH_PLACED);
        generation
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.LOTUS_PROTOTAXITE_FOREST_PLACED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CATTAIL_PATCH_RARE_PLACED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.ALGAE_SPARSE_PLACED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SMALL_MUSHROOMS_PROTOTAXITE_FOREST_PLACED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.HANGING_MUSHROOMS_PROTOTAXITE_FOREST_PLACED);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
        BiomeDefaultFeatures.addPlainGrass(generation);
        BiomeDefaultFeatures.addBushes(generation);
        BiomeDefaultFeatures.addFerns(generation);
        BiomeDefaultFeatures.addDefaultOres(generation, false);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_PLAINS);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addSwampExtraVegetation(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);

        return biome
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_SWAMP))
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x7ff5e9)
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x325747)
                .specialEffects((new BiomeSpecialEffects.Builder().waterColor(0x49918b)
                        .grassColorOverride(0x46bd4c).foliageColorOverride(0x64bd1c)).build())
                .mobSpawnSettings(mobs.build()).generationSettings(generation.build())
                .build();
    }
}

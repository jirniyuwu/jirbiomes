package net.jirniy.jirbiomes.worldgen.biome;

import net.jirniy.jirbiomes.particle.ModParticles;
import net.jirniy.jirbiomes.worldgen.ModPlacements;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.*;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.joml.Vector3f;

import java.util.List;
import java.util.Optional;

public class ModNetherBiomes {
    public static Biome brimstoneCrags(HolderGetter<PlacedFeature> placedFeatures, final HolderGetter<WorldCarver> carvers) {
        MobSpawnSettings mobSpawnSettings = (new MobSpawnSettings.Builder())
                .addSpawn(EntityTypes.GHAST, 50, 4, 4)
                .addSpawn(EntityTypes.ZOMBIFIED_PIGLIN, 100, 4, 4)
                .addSpawn(EntityTypes.MAGMA_CUBE, 2, 4, 4)
                .addSpawn(EntityTypes.STRIDER, 60, 1, 2).build();
        BiomeGenerationSettings.Builder biomeBuilder = (new BiomeGenerationSettings.Builder(placedFeatures, carvers))
                .addCarver(Carvers.NETHER_CAVE)
                .addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, ModPlacements.BRIMSTONE_RUINS)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacements.TENEBRIS_PLACED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacements.TENEBRIS_BUD_PLACED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacements.BRIMGRASS_PLACED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacements.TENEBRIS_CEILING_PLACED)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacements.GOLD_BRIMSTONE_PLACED)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_OPEN)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_FIRE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacements.BRIMSTONE_GLOWSTONE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacements.BRIMSTONE_GLOWSTONE_EXTRA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.BROWN_MUSHROOM_NETHER)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.RED_MUSHROOM_NETHER)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addNetherDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacements.POTENT_MAGMA_LAYER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false).temperature(4.0F).downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder().waterColor(0x9c3669)).build())
                .mobSpawnSettings(mobSpawnSettings).generationSettings(biomeBuilder.build())
                .setAttribute(EnvironmentAttributes.FOG_COLOR, new Vector3f())
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.AMBIENT_NETHER_WASTES_LOOP))
                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ModParticles.BRIMGRASS_SPORES, 0.0025f))
                .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                        Optional.of(SoundEvents.AMBIENT_NETHER_WASTES_LOOP),
                        Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 75000, 7, 2.0D)),
                        List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP, 0.015))))
                .build();
    }
}

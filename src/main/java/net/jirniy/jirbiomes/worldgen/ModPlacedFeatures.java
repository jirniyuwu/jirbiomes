package net.jirniy.jirbiomes.worldgen;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> GINKGO_TREE_PLACED = registryKey("ginkgo_tree");
    public static final ResourceKey<PlacedFeature> GINKGO_TREE_BONUS_PLACED = registryKey("ginkgo_tree_bonus");

    public static final ResourceKey<PlacedFeature> DRIED_GRASS_PATCH_PLACED = registryKey("dried_grass_patch");
    public static final ResourceKey<PlacedFeature> DRIED_GRASS_PATCH_DESERT_PLACED = registryKey("dried_grass_desert_patch");
    public static final ResourceKey<PlacedFeature> WET_GRASS_PATCH_PLACED = registryKey("wet_grass_patch");
    public static final ResourceKey<PlacedFeature> BARREL_CACTUS_PATCH = registryKey("barrel_cactus_patch");

    public static final ResourceKey<PlacedFeature> NETHERSTONE_PLACED = registryKey("netherstone");
    public static final ResourceKey<PlacedFeature> BRIMSTONE_PLACED = registryKey("brimstone");
    public static final ResourceKey<PlacedFeature> IGNITED_BRIMSTONE_PLACED = registryKey("ignited_brimstone");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, GINKGO_TREE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLD_GROWTH_GINKGO_TREE_BEES_005),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), BiomeFilter.biome(), InSquarePlacement.spread(),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.GINKGO_SAPLING), RarityFilter.onAverageOnceEvery(12),
                NoiseThresholdCountPlacement.of(0.6, 1, 2));
        register(context, GINKGO_TREE_BONUS_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLD_GROWTH_GINKGO_TREE_BEES_005),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.GINKGO_SAPLING), BiomeFilter.biome(), InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), RarityFilter.onAverageOnceEvery(6),
                NoiseThresholdCountPlacement.of(0.8, 1, 3));

        register(context, DRIED_GRASS_PATCH_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.DRIED_GRASS_PATCH),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), BiomeFilter.biome(),
                RandomOffsetPlacement.horizontal(UniformInt.of(-3, 1)), CountPlacement.of(2),
                InSquarePlacement.spread(), NoiseThresholdCountPlacement.of(0.4, 2, 14));
        register(context, DRIED_GRASS_PATCH_DESERT_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.DRIED_GRASS_PATCH_DESERT),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), BiomeFilter.biome(),
                RandomOffsetPlacement.horizontal(UniformInt.of(-3, 1)),
                InSquarePlacement.spread(), NoiseThresholdCountPlacement.of(0.2, 0, 2));
        register(context, WET_GRASS_PATCH_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.WET_GRASS_PATCH),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG), BiomeFilter.biome(),
                RandomOffsetPlacement.horizontal(UniformInt.of(-3, 1)), CountPlacement.of(3),
                InSquarePlacement.spread(), NoiseThresholdCountPlacement.of(0.2, 6, 1));

        register(context, BARREL_CACTUS_PATCH, configuredFeatures.getOrThrow(ModConfiguredFeatures.BARREL_CACTUS_PATCH),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(4),
                RandomOffsetPlacement.of(UniformInt.of(-2, 3), ConstantInt.of(0)), CountPlacement.of(UniformInt.of(1, 4)),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE),
                InSquarePlacement.spread(), NoiseThresholdCountPlacement.of(0.3, 1, 2));

        register(context, NETHERSTONE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHERSTONE),
                BiomeFilter.biome(), HeightRangePlacement.triangle(VerticalAnchor.BOTTOM, VerticalAnchor.aboveBottom(160)),
                CountPlacement.of(16), InSquarePlacement.spread());
        register(context, BRIMSTONE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.BRIMSTONE_PATCH),
                HeightRangePlacement.of(BiasedToBottomHeight.of(
                        VerticalAnchor.aboveBottom(20), VerticalAnchor.aboveBottom(40), 2)),
                BiomeFilter.biome(), CountPlacement.of(30), InSquarePlacement.spread());
        register(context, IGNITED_BRIMSTONE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.IGNITED_BRIMSTONE_PATCH),
                HeightRangePlacement.of(BiasedToBottomHeight.of(
                        VerticalAnchor.aboveBottom(15), VerticalAnchor.aboveBottom(30), 2)),
                BiomeFilter.biome(), NoiseThresholdCountPlacement.of(0.5f, 1, 3), InSquarePlacement.spread());
    }

    public static ResourceKey<PlacedFeature> registryKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, JirniyBiomes.id(name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                                                                          Holder<ConfiguredFeature<?, ?>> configuration,
                                                                                          PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}

package net.jirniy.jirbiomes.worldgen;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> GINKGO_TREE_PLACED = registryKey("ginkgo_tree");
    public static final ResourceKey<PlacedFeature> GINKGO_TREE_BONUS_PLACED = registryKey("ginkgo_tree_bonus");
    public static final ResourceKey<PlacedFeature> PALM_TREE_PLACED = registryKey("palm_tree");
    public static final ResourceKey<PlacedFeature> PALM_TREE_BEACH_PLACED = registryKey("palm_tree_beach");
    public static final ResourceKey<PlacedFeature> PALM_TREE_OCEAN_PLACED = registryKey("palm_tree_ocean");

    public static final ResourceKey<PlacedFeature> DRIED_GRASS_PATCH_PLACED = registryKey("dried_grass_patch");
    public static final ResourceKey<PlacedFeature> DRIED_GRASS_PATCH_DESERT_PLACED = registryKey("dried_grass_desert_patch");
    public static final ResourceKey<PlacedFeature> WET_GRASS_PATCH_PLACED = registryKey("wet_grass_patch");
    public static final ResourceKey<PlacedFeature> BARREL_CACTUS_PATCH = registryKey("barrel_cactus_patch");

    public static final ResourceKey<PlacedFeature> CATTAIL_PATCH_PLACED = registryKey("cattail_patch");
    public static final ResourceKey<PlacedFeature> ALGAE_PATCH_PLACED = registryKey("algae_patch");

    public static final ResourceKey<PlacedFeature> BRIMGRASS_PLACED = registryKey("brimgrass_patch");
    public static final ResourceKey<PlacedFeature> BRIMSTONE_RUINS = registryKey("brimstone_ruins");
    public static final ResourceKey<PlacedFeature> TENEBRIS_PLACED = registryKey("tenebris_tree");
    public static final ResourceKey<PlacedFeature> TENEBRIS_BUD_PLACED = registryKey("tenebris_bud");
    public static final ResourceKey<PlacedFeature> TENEBRIS_CEILING_PLACED = registryKey("tenebris_ceiling");

    public static final ResourceKey<PlacedFeature> SALT_PLACED = registryKey("sea_salt");

    public static final ResourceKey<PlacedFeature> NETHERSTONE_PLACED = registryKey("netherstone");
    public static final ResourceKey<PlacedFeature> BRIMSTONE_PLACED = registryKey("brimstone");
    public static final ResourceKey<PlacedFeature> IGNITED_BRIMSTONE_PLACED = registryKey("ignited_brimstone");
    public static final ResourceKey<PlacedFeature> GOLD_BRIMSTONE_PLACED = registryKey("brimstone_gold");

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

        register(context, PALM_TREE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.PALM_COCONUT_TREE),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), NoiseThresholdCountPlacement.of(0.6, 1, 3), InSquarePlacement.spread(),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.PALM_SAPLING), VegetationFeatures.nearWaterPredicate(ModBlocks.PALM_SAPLING), BiomeFilter.biome());
        register(context, PALM_TREE_BEACH_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.PALM_COCONUT_TREE),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), NoiseThresholdCountPlacement.of(0.4, 1, 3), InSquarePlacement.spread(),
                RarityFilter.onAverageOnceEvery(12), BiomeFilter.biome(), PlacementUtils.filteredByBlockSurvival(ModBlocks.PALM_SAPLING));
        register(context, PALM_TREE_OCEAN_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.PALM_COCONUT_TREE),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), CountPlacement.of(UniformInt.of(1, 3)), InSquarePlacement.spread(),
                BiomeFilter.biome(), PlacementUtils.filteredByBlockSurvival(ModBlocks.PALM_SAPLING));

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

        register(context, CATTAIL_PATCH_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.CATTAIL),
                RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BiomeFilter.biome(), CountPlacement.of(80), RandomOffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesFluids(Fluids.WATER),
                        BlockPredicate.matchesTag(Direction.UP.getUnitVec3i(), BlockTags.AIR))));
        register(context, ALGAE_PATCH_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALGAE_PATCH),
                RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(), CountPlacement.of(20), RandomOffsetPlacement.ofTriangle(7, 0),
                RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesFluids(Fluids.WATER),
                        BlockPredicate.matchesTag(Direction.UP.getUnitVec3i(), BlockTags.AIR))));

        register(context, BRIMGRASS_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.BRIMGRASS),
                CountPlacement.of(200), RandomOffsetPlacement.ofTriangle(7, 3),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(32), PlacementUtils.FULL_RANGE,
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                ModBlocks.BRIMGRASS_BLOCK))));
        register(context, TENEBRIS_BUD_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.TENEBRIS_BUD),
                CountPlacement.of(14), RandomOffsetPlacement.ofTriangle(7, 3),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(4), PlacementUtils.FULL_RANGE,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                ModBlocks.BRIMGRASS_BLOCK)), 6),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                ModBlocks.BRIMGRASS_BLOCK))));
        register(context, BRIMSTONE_RUINS, configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLD_BRIMSTONE_RUINS),
                CountPlacement.of(UniformInt.of(3, 5)), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(50), VerticalAnchor.aboveBottom(180)),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(),
                        BlockPredicate.anyOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesFluids(Fluids.LAVA, Fluids.FLOWING_LAVA)), 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

        register(context, TENEBRIS_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.TENEBRIS),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(32), PlacementUtils.FULL_RANGE,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                ModBlocks.BRIMGRASS_BLOCK, ModBlocks.BRIMSTONE)), 9));
        register(context, TENEBRIS_CEILING_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.CEILING_TENEBRIS_PATCH),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(UniformInt.of(2, 5)), PlacementUtils.FULL_RANGE,
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.UP.getUnitVec3i(),
                                ModBlocks.IGNITED_BRIMSTONE, ModBlocks.BRIMSTONE)), 9));

        register(context, SALT_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.SALT),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                NoiseThresholdCountPlacement.of(0.4f, 40, 200),
                RandomOffsetPlacement.of(ConstantInt.of(0), UniformInt.of(-2, -1)), InSquarePlacement.spread(), BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER, Fluids.FLOWING_WATER)));

        register(context, GOLD_BRIMSTONE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLD_BRIMSTONE),
                HeightRangePlacement.of(BiasedToBottomHeight.of(
                        VerticalAnchor.aboveBottom(20), VerticalAnchor.aboveBottom(200), 1)),
                BiomeFilter.biome(), CountPlacement.of(12), InSquarePlacement.spread());
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

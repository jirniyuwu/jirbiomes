package net.jirniy.jirbiomes.worldgen;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ClampedNormalInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
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
    public static final ResourceKey<PlacedFeature> NOOP = registryKey("noop");

    public static final ResourceKey<PlacedFeature> GINKGO_TREE_PLACED = registryKey("ginkgo_tree");
    public static final ResourceKey<PlacedFeature> GINKGO_TREE_BONUS_PLACED = registryKey("ginkgo_tree_bonus");
    public static final ResourceKey<PlacedFeature> PALM_TREE_PLACED = registryKey("palm_tree");
    public static final ResourceKey<PlacedFeature> PALM_TREE_BEACH_PLACED = registryKey("palm_tree_beach");
    public static final ResourceKey<PlacedFeature> PALM_TREE_OCEAN_PLACED = registryKey("palm_tree_ocean");

    public static final ResourceKey<PlacedFeature> DRIED_GRASS_PATCH_PLACED = registryKey("dried_grass_patch");
    public static final ResourceKey<PlacedFeature> DRIED_GRASS_PATCH_DESERT_PLACED = registryKey("dried_grass_desert_patch");
    public static final ResourceKey<PlacedFeature> DRIED_DIRT_PATCH_DESERT_PLACED = registryKey("dried_dirt_desert_patch");
    public static final ResourceKey<PlacedFeature> WET_GRASS_PATCH_PLACED = registryKey("wet_grass_patch");
    public static final ResourceKey<PlacedFeature> BARREL_CACTUS_PATCH = registryKey("barrel_cactus_patch");

    public static final ResourceKey<PlacedFeature> CATTAIL_PATCH_PLACED = registryKey("cattail_patch");
    public static final ResourceKey<PlacedFeature> ALGAE_PATCH_PLACED = registryKey("algae_patch");
    public static final ResourceKey<PlacedFeature> LOTUS_PLACED = registryKey("lotus_placed");
    public static final ResourceKey<PlacedFeature> LOTUS_RARE_PLACED = registryKey("lotus_rare_placed");
    public static final ResourceKey<PlacedFeature> FROZEN_GRASS_PLACED = registryKey("frozen_grass");
    public static final ResourceKey<PlacedFeature> SHARP_RIBS_SOUL_SAND_PLACED = registryKey("sharp_ribs_soul_sand");
    public static final ResourceKey<PlacedFeature> SEA_URCHIN_PLACED = registryKey("sea_urchin");
    public static final ResourceKey<PlacedFeature> SEA_URCHIN_BONUS_PLACED = registryKey("sea_urchin_bonus");
    public static final ResourceKey<PlacedFeature> SEA_URCHIN_SHIPWRECK_PLACED = registryKey("sea_urchin_shipwreck");
    public static final ResourceKey<PlacedFeature> SAND_SHELLS_BEACH = registryKey("sand_shells_beach");
    public static final ResourceKey<PlacedFeature> SAND_SHELLS_SMALL_BEACH = registryKey("sand_shells_small_beach");
    public static final ResourceKey<PlacedFeature> SAND_SHELLS = registryKey("sand_shells_ocean");
    public static final ResourceKey<PlacedFeature> SAND_SHELLS_BONUS = registryKey("sand_shells_bonus_ocean");

    public static final ResourceKey<PlacedFeature> ICE_TOP_LAYER = registryKey("ice_top_layer");
    public static final ResourceKey<PlacedFeature> BLUE_ICE_ORE = registryKey("blue_ice_ore");
    public static final ResourceKey<PlacedFeature> ICICLE_CLUSTER_PLACED = registryKey("icicle_cluster");
    public static final ResourceKey<PlacedFeature> LARGE_ICICLE_PLACED = registryKey("large_icicle");
    public static final ResourceKey<PlacedFeature> POINTED_ICICLE_PLACED = registryKey("pointed_icicle");

    public static final ResourceKey<PlacedFeature> BRIMGRASS_PLACED = registryKey("brimgrass_patch");
    public static final ResourceKey<PlacedFeature> BRIMGRASS_BONEMEAL = registryKey("brimgrass_bonemeal");
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

        register(context, NOOP, configuredFeatures.getOrThrow(ModConfiguredFeatures.NOOP));

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

        register(context, ICE_TOP_LAYER, configuredFeatures.getOrThrow(ModConfiguredFeatures.ICE_TOP_LAYER),
                CountPlacement.of(UniformInt.of(1024, 1546)), InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(20), VerticalAnchor.absolute(100)), EnvironmentScanPlacement.scanningFor(
                        Direction.DOWN, BlockPredicate.matchesBlocks(Blocks.WATER, Blocks.LAVA), 9),
                CountPlacement.of(UniformInt.of(8, 18)), RandomOffsetPlacement.ofTriangle(6, 0),
                BiomeFilter.biome());
        register(context, BLUE_ICE_ORE, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLUE_ICE_ORE),
                NoiseThresholdCountPlacement.of(0.5f, 5, 15), InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(15), VerticalAnchor.absolute(120)),
                BiomeFilter.biome());
        register(context, ICICLE_CLUSTER_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.ICICLE_CLUSTER),
                CountPlacement.of(UniformInt.of(48, 96)), InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        register(context, LARGE_ICICLE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.LARGE_ICICLE),
                CountPlacement.of(UniformInt.of(10, 48)), InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        register(context, POINTED_ICICLE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.POINTED_ICICLE),
                CountPlacement.of(UniformInt.of(192, 256)), InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, CountPlacement.of(UniformInt.of(1, 5)),
                RandomOffsetPlacement.of(ClampedNormalInt.of(0.0F, 3.0F, -10, 10),
                        ClampedNormalInt.of(0.0F, 0.6F, -2, 2)), BiomeFilter.biome());

        register(context, DRIED_GRASS_PATCH_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.DRIED_GRASS_PATCH),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), CountPlacement.of(2),
                NoiseThresholdCountPlacement.of(0.4, 2, 14), InSquarePlacement.spread(),
                RandomOffsetPlacement.horizontal(UniformInt.of(-3, 1)), BiomeFilter.biome());
        register(context, DRIED_DIRT_PATCH_DESERT_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.DRIED_DIRT_PATCH_DESERT),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), BiomeFilter.biome(),
                RandomOffsetPlacement.horizontal(UniformInt.of(-3, 1)),
                NoiseThresholdCountPlacement.of(0.2, 0, 2),
                InSquarePlacement.spread());
        register(context, DRIED_GRASS_PATCH_DESERT_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.DRIED_GRASS_PATCH_DESERT),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), BiomeFilter.biome(),
                NoiseThresholdCountPlacement.of(0.2, 3, 16), InSquarePlacement.spread(),
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                        BlockPredicate.matchesFluids(new BlockPos(1, -1, 0), Fluids.WATER, Fluids.FLOWING_WATER),
                        BlockPredicate.matchesFluids(new BlockPos(-1, -1, 0), Fluids.WATER, Fluids.FLOWING_WATER),
                        BlockPredicate.matchesFluids(new BlockPos(0, -1, 1), Fluids.WATER, Fluids.FLOWING_WATER),
                        BlockPredicate.matchesFluids(new BlockPos(0, -1, -1), Fluids.WATER, Fluids.FLOWING_WATER),
                        BlockPredicate.matchesFluids(new BlockPos(0, -1, 0), Fluids.WATER, Fluids.FLOWING_WATER),
                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.GRASS_BLOCK)
                )));
        register(context, WET_GRASS_PATCH_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.WET_GRASS_PATCH),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG), CountPlacement.of(3),
                NoiseThresholdCountPlacement.of(0.2, 6, 1), CountPlacement.of(3),
                InSquarePlacement.spread(), RandomOffsetPlacement.horizontal(UniformInt.of(-3, 1)), BiomeFilter.biome());

        register(context, BARREL_CACTUS_PATCH, configuredFeatures.getOrThrow(ModConfiguredFeatures.BARREL_CACTUS_PATCH),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), InSquarePlacement.spread(), BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(6),
                RandomOffsetPlacement.of(UniformInt.of(-2, 3), ConstantInt.of(0)), CountPlacement.of(UniformInt.of(1, 4)),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), NoiseThresholdCountPlacement.of(0.3, 1, 2));

        register(context, CATTAIL_PATCH_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.CATTAIL),
                RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BiomeFilter.biome(), CountPlacement.of(80), RandomOffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesFluids(Fluids.WATER),
                        BlockPredicate.matchesTag(Direction.UP.getUnitVec3i(), BlockTags.AIR))), BiomeFilter.biome());
        register(context, LOTUS_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.LOTUS_FLOWER),
                NoiseThresholdCountPlacement.of(0.4, 1, 4),
                RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesFluids(Direction.DOWN.getUnitVec3i(), Fluids.WATER))), BiomeFilter.biome());
        register(context, LOTUS_RARE_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.LOTUS_FLOWER),
                NoiseThresholdCountPlacement.of(0.5, 0, 2),
                RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesFluids(Direction.DOWN.getUnitVec3i(), Fluids.WATER))), BiomeFilter.biome());
        register(context, ALGAE_PATCH_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALGAE_PATCH),
                RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                CountPlacement.of(20), RandomOffsetPlacement.ofTriangle(7, 0),
                RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesFluids(Fluids.WATER),
                        BlockPredicate.matchesTag(Direction.UP.getUnitVec3i(), BlockTags.AIR))));
        register(context, FROZEN_GRASS_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_GRASS),
                CountPlacement.of(40), RandomOffsetPlacement.ofTriangle(7, 3),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(14), PlacementUtils.FULL_RANGE,
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesTag(Direction.DOWN.getUnitVec3i(),
                                ModTags.Blocks.SUPPORTS_ICE_VEGETATION))));
        register(context, SHARP_RIBS_SOUL_SAND_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.SHARP_RIBS),
                CountPlacement.of(6), RandomOffsetPlacement.ofTriangle(4, 3),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(32), PlacementUtils.FULL_RANGE,
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                Blocks.SOUL_SAND, Blocks.SOUL_SOIL))));
        register(context, SEA_URCHIN_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.SEA_URCHIN),
                RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(),
                RandomOffsetPlacement.ofTriangle(4, 3), CountPlacement.of(24), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome());
        register(context, SEA_URCHIN_BONUS_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.SEA_URCHIN),
                NoiseBasedCountPlacement.of(10, 400.0, 0.0),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome());
        register(context, SEA_URCHIN_SHIPWRECK_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.SEA_URCHIN_SHIPWRECK),
                CountPlacement.of(24), RandomOffsetPlacement.ofTriangle(7, 3), InSquarePlacement.spread(),
                CountPlacement.of(12), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesFluids(Fluids.WATER),
                        BlockPredicate.matchesTag(Direction.DOWN.getUnitVec3i(),
                                ModTags.Blocks.SEA_URCHIN_SHIPWRECK_PLACEMENT))));

        register(context, SAND_SHELLS, configuredFeatures.getOrThrow(ModConfiguredFeatures.SAND_SHELLS),
                RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(),
                RandomOffsetPlacement.ofTriangle(4, 1), CountPlacement.of(4), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                        BlockPredicate.matchesFluids(Fluids.WATER), BlockPredicate.matchesFluids(Direction.UP.getUnitVec3i(), Fluids.WATER))),
                BiomeFilter.biome());
        register(context, SAND_SHELLS_BONUS, configuredFeatures.getOrThrow(ModConfiguredFeatures.SAND_SHELLS_SMALL),
                CountPlacement.of(UniformInt.of(1, 2)), InSquarePlacement.spread(), CountPlacement.of(7),
                RandomOffsetPlacement.ofTriangle(5, 2), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                        BlockPredicate.matchesFluids(Fluids.WATER), BlockPredicate.matchesFluids(Direction.UP.getUnitVec3i(), Fluids.WATER))),
                BiomeFilter.biome());

        register(context, SAND_SHELLS_BEACH, configuredFeatures.getOrThrow(ModConfiguredFeatures.SAND_SHELLS),
                InSquarePlacement.spread(), CountPlacement.of(2), RandomOffsetPlacement.ofTriangle(4, 1),
                RarityFilter.onAverageOnceEvery(3), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                        BlockPredicate.matchesFluids(Fluids.WATER), BlockPredicate.matchesFluids(Direction.UP.getUnitVec3i(), Fluids.WATER))),
                BiomeFilter.biome());
        register(context, SAND_SHELLS_SMALL_BEACH, configuredFeatures.getOrThrow(ModConfiguredFeatures.SAND_SHELLS_SMALL),
                CountPlacement.of(8), InSquarePlacement.spread(),
                CountPlacement.of(6), RandomOffsetPlacement.ofTriangle(5, 0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                            BlockPredicate.matchesFluids(new BlockPos(1, -1, 0), Fluids.WATER, Fluids.FLOWING_WATER),
                            BlockPredicate.matchesFluids(new BlockPos(-1, -1, 0), Fluids.WATER, Fluids.FLOWING_WATER),
                            BlockPredicate.matchesFluids(new BlockPos(0, -1, 1), Fluids.WATER, Fluids.FLOWING_WATER),
                            BlockPredicate.matchesFluids(new BlockPos(0, -1, -1), Fluids.WATER, Fluids.FLOWING_WATER),
                            BlockPredicate.matchesBlocks(new BlockPos(1, -1, 0), Blocks.ICE),
                            BlockPredicate.matchesBlocks(new BlockPos(-1, -1, 0), Blocks.ICE),
                            BlockPredicate.matchesBlocks(new BlockPos(0, -1, 1), Blocks.ICE),
                            BlockPredicate.matchesBlocks(new BlockPos(0, -1, -1), Blocks.ICE)
                    )),
                BiomeFilter.biome());

        register(context, BRIMGRASS_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.BRIMGRASS),
                CountPlacement.of(200), RandomOffsetPlacement.ofTriangle(7, 3),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(32), PlacementUtils.FULL_RANGE,
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                ModBlocks.BRIMGRASS_BLOCK))));
        register(context, BRIMGRASS_BONEMEAL, configuredFeatures.getOrThrow(ModConfiguredFeatures.BRIMGRASS), PlacementUtils.isEmpty());
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

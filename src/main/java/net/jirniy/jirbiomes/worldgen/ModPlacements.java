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
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ClampedNormalInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class ModPlacements {
    public static final ResourceKey<PlacedFeature> NOOP = registryKey("noop");

    public static final ResourceKey<PlacedFeature> GINKGO_TREE_PLACED = registryKey("ginkgo_tree");
    public static final ResourceKey<PlacedFeature> GINKGO_TREE_BONUS_PLACED = registryKey("ginkgo_tree_bonus");
    public static final ResourceKey<PlacedFeature> PALM_TREE_PLACED = registryKey("palm_tree");
    public static final ResourceKey<PlacedFeature> PALM_TREE_BEACH_PLACED = registryKey("palm_tree_beach");
    public static final ResourceKey<PlacedFeature> PALM_TREE_OCEAN_PLACED = registryKey("palm_tree_ocean");
    public static final ResourceKey<PlacedFeature> LARGE_PROTOTAXITE_PLACED = registryKey("large_prototaxite");
    public static final ResourceKey<PlacedFeature> SMALL_PROTOTAXITE_PLACED = registryKey("small_prototaxite");

    public static final ResourceKey<PlacedFeature> DRIED_GRASS_PATCH_PLACED = registryKey("dried_grass_patch");
    public static final ResourceKey<PlacedFeature> DRIED_GRASS_PATCH_DESERT_PLACED = registryKey("dried_grass_desert_patch");
    public static final ResourceKey<PlacedFeature> DRIED_DIRT_PATCH_DESERT_PLACED = registryKey("dried_dirt_desert_patch");
    public static final ResourceKey<PlacedFeature> WET_GRASS_PATCH_PLACED = registryKey("wet_grass_patch");
    public static final ResourceKey<PlacedFeature> BARREL_CACTUS_PATCH = registryKey("barrel_cactus_patch");

    public static final ResourceKey<PlacedFeature> CATTAIL_PATCH_PLACED = registryKey("cattail_patch");
    public static final ResourceKey<PlacedFeature> CATTAIL_PATCH_RARE_PLACED = registryKey("cattail_patch_rare");
    public static final ResourceKey<PlacedFeature> ALGAE_SPARSE_PLACED = registryKey("algae_sparse");
    public static final ResourceKey<PlacedFeature> ALGAE_PATCH_PLACED = registryKey("algae_patch");
    public static final ResourceKey<PlacedFeature> LOTUS_PLACED = registryKey("lotus_placed");
    public static final ResourceKey<PlacedFeature> LOTUS_RARE_PLACED = registryKey("lotus_rare_placed");
    public static final ResourceKey<PlacedFeature> LOTUS_PROTOTAXITE_FOREST_PLACED = registryKey("lotus_prototaxite_forest");
    public static final ResourceKey<PlacedFeature> FROZEN_GRASS_PLACED = registryKey("frozen_grass");
    public static final ResourceKey<PlacedFeature> SNOWY_BUSH_PLACED = registryKey("snowy_bush");
    public static final ResourceKey<PlacedFeature> SHARP_RIBS_SOUL_SAND_PLACED = registryKey("sharp_ribs_soul_sand");
    public static final ResourceKey<PlacedFeature> SEA_URCHIN_PLACED = registryKey("sea_urchin");
    public static final ResourceKey<PlacedFeature> SEA_URCHIN_BONUS_PLACED = registryKey("sea_urchin_bonus");
    public static final ResourceKey<PlacedFeature> SEA_URCHIN_SHIPWRECK_PLACED = registryKey("sea_urchin_shipwreck");
    public static final ResourceKey<PlacedFeature> SAND_SHELLS_BEACH = registryKey("sand_shells_beach");
    public static final ResourceKey<PlacedFeature> SAND_SHELLS_SMALL_BEACH = registryKey("sand_shells_small_beach");
    public static final ResourceKey<PlacedFeature> SAND_SHELLS = registryKey("sand_shells_ocean");
    public static final ResourceKey<PlacedFeature> SAND_SHELLS_BONUS = registryKey("sand_shells_bonus_ocean");
    public static final ResourceKey<PlacedFeature> SMALL_MUSHROOMS_PATCH_PLACED = registryKey("small_mushrooms_patch");
    public static final ResourceKey<PlacedFeature> SMALL_MUSHROOMS_RARE_PLACED = registryKey("small_mushrooms_rare");
    public static final ResourceKey<PlacedFeature> SMALL_MUSHROOMS_CAVE_PLACED = registryKey("small_mushrooms_cave");
    public static final ResourceKey<PlacedFeature> SMALL_MUSHROOMS_PROTOTAXITE_FOREST_PLACED = registryKey("small_mushrooms_prototaxite_forest");
    public static final ResourceKey<PlacedFeature> HANGING_MUSHROOMS_PLACED = registryKey("hanging_mushrooms");
    public static final ResourceKey<PlacedFeature> HANGING_MUSHROOMS_PROTOTAXITE_FOREST_PLACED = registryKey("hanging_mushrooms_prototaxite_forest");
    public static final ResourceKey<PlacedFeature> HANGING_MUSHROOMS_DEEP_PLACED = registryKey("hanging_mushrooms_deep");
    public static final ResourceKey<PlacedFeature> PROTOTAXITE_SEPTA_PATCH_PLACED = registryKey("prototaxite_septa_patch");
    public static final ResourceKey<PlacedFeature> PROTOTAXITE_SEPTA_GROWTH_PLACED = registryKey("prototaxite_septa_growth");
    public static final ResourceKey<PlacedFeature> MUD_POOL_PLACED = registryKey("mud_pool");

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

    public static final ResourceKey<PlacedFeature> POTENT_MAGMA_LAYER = registryKey("potent_magma_layer");
    public static final ResourceKey<PlacedFeature> POTENT_MAGMA_ORE = registryKey("potent_magma_ore");
    public static final ResourceKey<PlacedFeature> NETHERSTONE_PLACED = registryKey("netherstone");
    public static final ResourceKey<PlacedFeature> BRIMSTONE_PLACED = registryKey("brimstone");
    public static final ResourceKey<PlacedFeature> IGNITED_BRIMSTONE_PLACED = registryKey("ignited_brimstone");
    public static final ResourceKey<PlacedFeature> GOLD_BRIMSTONE_PLACED = registryKey("brimstone_gold");
    public static final ResourceKey<PlacedFeature> BRIMSTONE_GLOWSTONE = registryKey("glowstone_brimstone");
    public static final ResourceKey<PlacedFeature> BRIMSTONE_GLOWSTONE_EXTRA = registryKey("glowstone_extra_brimstone");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<Feature> configuredFeatures = context.lookup(Registries.FEATURE);

        register(context, NOOP, configuredFeatures.getOrThrow(ModFeatures.NOOP));

        register(context, GINKGO_TREE_PLACED, configuredFeatures.getOrThrow(ModFeatures.OLD_GROWTH_GINKGO_TREE_BEES_005),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), BiomeFilter.biome(), InSquarePlacement.spread(),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.GINKGO_SAPLING), RarityFilter.onAverageOnceEvery(12),
                NoiseThresholdCountPlacement.of(0.6, 1, 2));
        register(context, GINKGO_TREE_BONUS_PLACED, configuredFeatures.getOrThrow(ModFeatures.OLD_GROWTH_GINKGO_TREE_BEES_005),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.GINKGO_SAPLING), BiomeFilter.biome(), InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), RarityFilter.onAverageOnceEvery(6),
                NoiseThresholdCountPlacement.of(0.8, 1, 3));

        register(context, PALM_TREE_PLACED, configuredFeatures.getOrThrow(ModFeatures.PALM_COCONUT_TREE),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), NoiseThresholdCountPlacement.of(0.6, 1, 3), InSquarePlacement.spread(),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.PALM_SAPLING), VegetationFeatures.nearWaterPredicate(ModBlocks.PALM_SAPLING), BiomeFilter.biome());
        register(context, PALM_TREE_BEACH_PLACED, configuredFeatures.getOrThrow(ModFeatures.PALM_COCONUT_TREE),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), NoiseThresholdCountPlacement.of(0.4, 1, 3), InSquarePlacement.spread(),
                RarityFilter.onAverageOnceEvery(12), BiomeFilter.biome(), PlacementUtils.filteredByBlockSurvival(ModBlocks.PALM_SAPLING));
        register(context, PALM_TREE_OCEAN_PLACED, configuredFeatures.getOrThrow(ModFeatures.PALM_COCONUT_TREE),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), CountPlacement.of(UniformInt.of(1, 3)), InSquarePlacement.spread(),
                BiomeFilter.biome(), PlacementUtils.filteredByBlockSurvival(ModBlocks.PALM_SAPLING));

        register(context, LARGE_PROTOTAXITE_PLACED, configuredFeatures.getOrThrow(ModFeatures.PROTOTAXITE_WITH_SEPTAS),
                NoiseThresholdCountPlacement.of(0.2, 1, 3),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), InSquarePlacement.spread(), BiomeFilter.biome(),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.PROTOTAXITE_SAPLING), RarityFilter.onAverageOnceEvery(3),
                BlockPredicateFilter.forPredicate(BlockPredicate.not(
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), List.of(ModBlocks.PROTOTAXITE_LOG)))
                ));
        register(context, SMALL_PROTOTAXITE_PLACED, configuredFeatures.getOrThrow(ModFeatures.PROTOTAXITE_SMALL_WITH_SEPTAS),
                NoiseThresholdCountPlacement.of(0.2, 3, 1),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), InSquarePlacement.spread(), BiomeFilter.biome(),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.PROTOTAXITE_SAPLING), RarityFilter.onAverageOnceEvery(2),
                BlockPredicateFilter.forPredicate(BlockPredicate.not(
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), List.of(ModBlocks.PROTOTAXITE_LOG)))
                ));

        register(context, ICE_TOP_LAYER, configuredFeatures.getOrThrow(ModFeatures.ICE_TOP_LAYER),
                CountPlacement.of(UniformInt.of(1124, 1646)), InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(100)), EnvironmentScanPlacement.scanningFor(
                        Direction.DOWN, BlockPredicate.matchesBlocks(Blocks.WATER, Blocks.LAVA), 9),
                CountPlacement.of(UniformInt.of(8, 18)), OffsetPlacement.ofTriangle(6, 0),
                BiomeFilter.biome());
        register(context, BLUE_ICE_ORE, configuredFeatures.getOrThrow(ModFeatures.BLUE_ICE_ORE),
                NoiseThresholdCountPlacement.of(0.5f, 5, 15), InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(15), VerticalAnchor.absolute(120)),
                BiomeFilter.biome());
        register(context, ICICLE_CLUSTER_PLACED, configuredFeatures.getOrThrow(ModFeatures.ICICLE_CLUSTER),
                CountPlacement.of(UniformInt.of(48, 96)), InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        register(context, LARGE_ICICLE_PLACED, configuredFeatures.getOrThrow(ModFeatures.LARGE_ICICLE),
                CountPlacement.of(UniformInt.of(10, 48)), InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        register(context, POINTED_ICICLE_PLACED, configuredFeatures.getOrThrow(ModFeatures.POINTED_ICICLE),
                CountPlacement.of(UniformInt.of(192, 256)), InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, CountPlacement.of(UniformInt.of(1, 5)),
                OffsetPlacement.of(ClampedNormalInt.of(0.0F, 3.0F, -10, 10),
                        ClampedNormalInt.of(0.0F, 0.6F, -2, 2)), BiomeFilter.biome());

        register(context, DRIED_GRASS_PATCH_PLACED, configuredFeatures.getOrThrow(ModFeatures.DRIED_GRASS_PATCH),
                PlacementUtils.HEIGHTMAP_NO_LEAVES, CountPlacement.of(2),
                NoiseThresholdCountPlacement.of(0.4, 2, 14), InSquarePlacement.spread(),
                OffsetPlacement.horizontal(UniformInt.of(-3, 1)), BiomeFilter.biome());
        register(context, DRIED_DIRT_PATCH_DESERT_PLACED, configuredFeatures.getOrThrow(ModFeatures.DRIED_DIRT_PATCH_DESERT),
                PlacementUtils.HEIGHTMAP_NO_LEAVES, BiomeFilter.biome(),
                OffsetPlacement.horizontal(UniformInt.of(-3, 1)),
                NoiseThresholdCountPlacement.of(0.2, 0, 2),
                InSquarePlacement.spread());
        register(context, DRIED_GRASS_PATCH_DESERT_PLACED, configuredFeatures.getOrThrow(ModFeatures.DRIED_GRASS_PATCH_DESERT),
                PlacementUtils.HEIGHTMAP_NO_LEAVES, BiomeFilter.biome(),
                NoiseThresholdCountPlacement.of(0.2, 3, 16), InSquarePlacement.spread(),
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                        besideFluidPredicate(Fluids.WATER, Fluids.FLOWING_WATER),
                        BlockPredicate.matchesFluids(new BlockPos(0, -1, 0), List.of(Fluids.WATER, Fluids.FLOWING_WATER)),
                        besideBlockPredicate(Blocks.GRASS_BLOCK, ModBlocks.WET_GRASS_BLOCK),
                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), List.of(Blocks.GRASS_BLOCK, ModBlocks.WET_GRASS_BLOCK))
                )));
        register(context, WET_GRASS_PATCH_PLACED, configuredFeatures.getOrThrow(ModFeatures.WET_GRASS_PATCH),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG), CountPlacement.of(3),
                NoiseThresholdCountPlacement.of(0.2, 6, 1), CountPlacement.of(3),
                InSquarePlacement.spread(), OffsetPlacement.horizontal(UniformInt.of(-3, 1)), BiomeFilter.biome());

        register(context, BARREL_CACTUS_PATCH, configuredFeatures.getOrThrow(ModFeatures.BARREL_CACTUS_PATCH),
                PlacementUtils.HEIGHTMAP_NO_LEAVES, InSquarePlacement.spread(), BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(6),
                OffsetPlacement.of(UniformInt.of(-2, 3), ConstantInt.of(0)), CountPlacement.of(UniformInt.of(1, 4)),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), NoiseThresholdCountPlacement.of(0.3, 1, 2));

        register(context, CATTAIL_PATCH_PLACED, configuredFeatures.getOrThrow(ModFeatures.CATTAIL),
                RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BiomeFilter.biome(), CountPlacement.of(80), OffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesFluids(Fluids.WATER),
                        BlockPredicate.matchesTag(Direction.UP.getUnitVec3i(), BlockTags.AIR))), BiomeFilter.biome());
        register(context, CATTAIL_PATCH_RARE_PLACED, configuredFeatures.getOrThrow(ModFeatures.CATTAIL),
                RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BiomeFilter.biome(), CountPlacement.of(140), OffsetPlacement.ofTriangle(9, 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesFluids(Fluids.WATER),
                        BlockPredicate.matchesTag(Direction.UP.getUnitVec3i(), BlockTags.AIR))), BiomeFilter.biome());
        register(context, LOTUS_PLACED, configuredFeatures.getOrThrow(ModFeatures.LOTUS_FLOWER),
                NoiseThresholdCountPlacement.of(0.4, 1, 4),
                RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_NO_LEAVES,
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesFluids(Direction.DOWN.getUnitVec3i(), List.of(Fluids.WATER)))), BiomeFilter.biome());
        register(context, LOTUS_RARE_PLACED, configuredFeatures.getOrThrow(ModFeatures.LOTUS_FLOWER),
                NoiseThresholdCountPlacement.of(0.5, 0, 2),
                RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_NO_LEAVES,
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesFluids(Direction.DOWN.getUnitVec3i(), List.of(Fluids.WATER)))), BiomeFilter.biome());
        register(context, LOTUS_PROTOTAXITE_FOREST_PLACED, configuredFeatures.getOrThrow(ModFeatures.LOTUS_FLOWER),
                NoiseThresholdCountPlacement.of(-0.2, 2, 0),
                RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_NO_LEAVES,
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.anyOf(
                                BlockPredicate.matchesFluids(Direction.DOWN.getUnitVec3i(), List.of(Fluids.WATER)),
                                BlockPredicate.matchesTag(Direction.DOWN.getUnitVec3i(), BlockTags.SUPPORTS_VEGETATION)))),
                BiomeFilter.biome());
        register(context, ALGAE_SPARSE_PLACED, configuredFeatures.getOrThrow(ModFeatures.ALGAE_PATCH),
                CountPlacement.of(3), NoiseThresholdCountPlacement.of(0.3f, 1, 2),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_NO_LEAVES,
                OffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesFluids(Fluids.WATER),
                        BlockPredicate.matchesTag(Direction.UP.getUnitVec3i(), BlockTags.AIR))));
        register(context, ALGAE_PATCH_PLACED, configuredFeatures.getOrThrow(ModFeatures.ALGAE_PATCH),
                RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_NO_LEAVES,
                CountPlacement.of(20), OffsetPlacement.ofTriangle(7, 0),
                OffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesFluids(Fluids.WATER),
                        BlockPredicate.matchesTag(Direction.UP.getUnitVec3i(), BlockTags.AIR))));
        register(context, FROZEN_GRASS_PLACED, configuredFeatures.getOrThrow(ModFeatures.FROZEN_GRASS),
                CountPlacement.of(40), OffsetPlacement.ofTriangle(7, 3),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(14), PlacementUtils.FULL_RANGE,
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesTag(Direction.DOWN.getUnitVec3i(),
                                ModTags.Blocks.SUPPORTS_ICE_VEGETATION))));
        register(context, SNOWY_BUSH_PLACED, configuredFeatures.getOrThrow(ModFeatures.SNOWY_BUSH),
                RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_NO_LEAVES,
                BiomeFilter.biome(), CountPlacement.of(24), OffsetPlacement.ofTriangle(5, 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Blocks.SNOW)
                )));
        register(context, SHARP_RIBS_SOUL_SAND_PLACED, configuredFeatures.getOrThrow(ModFeatures.SHARP_RIBS),
                CountPlacement.of(6), OffsetPlacement.ofTriangle(4, 3),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(32), PlacementUtils.FULL_RANGE,
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                List.of(Blocks.SOUL_SAND, Blocks.SOUL_SOIL)))));
        register(context, SEA_URCHIN_PLACED, configuredFeatures.getOrThrow(ModFeatures.SEA_URCHIN),
                RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(),
                OffsetPlacement.ofTriangle(4, 3), CountPlacement.of(24), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome());
        register(context, SEA_URCHIN_BONUS_PLACED, configuredFeatures.getOrThrow(ModFeatures.SEA_URCHIN),
                NoiseBasedCountPlacement.of(10, 400.0, 0.0),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome());
        register(context, SEA_URCHIN_SHIPWRECK_PLACED, configuredFeatures.getOrThrow(ModFeatures.SEA_URCHIN_SHIPWRECK),
                CountPlacement.of(24), OffsetPlacement.ofTriangle(7, 3), InSquarePlacement.spread(),
                CountPlacement.of(12), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesFluids(Fluids.WATER),
                        BlockPredicate.matchesTag(Direction.DOWN.getUnitVec3i(),
                                ModTags.Blocks.SEA_URCHIN_SHIPWRECK_PLACEMENT))));
        register(context, PROTOTAXITE_SEPTA_PATCH_PLACED, configuredFeatures.getOrThrow(ModFeatures.PROTOTAXITE_SEPTA),
                NoiseThresholdCountPlacement.of(0.4f, 1, 2), RarityFilter.onAverageOnceEvery(4),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(8),
                OffsetPlacement.ofTriangle(7, 2), PlacementUtils.HEIGHTMAP_NO_LEAVES,
                PlacementUtils.filteredByBlockSurvival(ModBlocks.PROTOTAXITE_SAPLING), BlockPredicateFilter.forPredicate(
                        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), List.of(ModBlocks.PROTOTAXITE_LOG)))
                ));
        register(context, PROTOTAXITE_SEPTA_GROWTH_PLACED, configuredFeatures.getOrThrow(ModFeatures.PROTOTAXITE_SEPTA_GROWTH),
                NoiseThresholdCountPlacement.of(0.1f, 3, 2), RarityFilter.onAverageOnceEvery(6),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(3),
                OffsetPlacement.ofTriangle(6, 2), PlacementUtils.HEIGHTMAP_NO_LEAVES,
                PlacementUtils.filteredByBlockSurvival(ModBlocks.PROTOTAXITE_SAPLING), BlockPredicateFilter.forPredicate(
                        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), List.of(ModBlocks.PROTOTAXITE_LOG)))
                ));
        register(context, MUD_POOL_PLACED, configuredFeatures.getOrThrow(ModFeatures.MUD_POOL),
                NoiseThresholdCountPlacement.of(0.2f, 2, 1), RarityFilter.onAverageOnceEvery(12),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(UniformInt.of(2, 5)),
                OffsetPlacement.ofTriangle(5, 2), PlacementUtils.HEIGHTMAP_NO_LEAVES,
                PlacementUtils.filteredByBlockSurvival(ModBlocks.PROTOTAXITE_SAPLING), BlockPredicateFilter.forPredicate(
                        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), List.of(ModBlocks.PROTOTAXITE_LOG)))
                ));

        register(context, SMALL_MUSHROOMS_PATCH_PLACED, configuredFeatures.getOrThrow(ModFeatures.SMALL_MUSHROOMS),
                NoiseThresholdCountPlacement.of(0.6, 1, 3),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), CountPlacement.of(48),
                OffsetPlacement.ofTriangle(6, 2),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), List.of(Blocks.MYCELIUM))
                )));
        register(context, SMALL_MUSHROOMS_RARE_PLACED, configuredFeatures.getOrThrow(ModFeatures.SMALL_MUSHROOMS),
                NoiseThresholdCountPlacement.of(0.4, 0, 1),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_NO_LEAVES, BiomeFilter.biome(), CountPlacement.of(28),
                OffsetPlacement.ofTriangle(4, 2),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.solid(Direction.DOWN)
                )));
        register(context, SMALL_MUSHROOMS_CAVE_PLACED, configuredFeatures.getOrThrow(ModFeatures.SMALL_MUSHROOMS),
                NoiseThresholdCountPlacement.of(0.2, 1, 3), InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(30), VerticalAnchor.absolute(70)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(Direction.DOWN), 8),
                BiomeFilter.biome(), CountPlacement.of(32), OffsetPlacement.ofTriangle(4, 2),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(Direction.DOWN), 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.solid(Direction.DOWN)
                )));
        register(context, SMALL_MUSHROOMS_PROTOTAXITE_FOREST_PLACED, configuredFeatures.getOrThrow(ModFeatures.SMALL_MUSHROOMS),
                NoiseThresholdCountPlacement.of(0.3, 2, 6), InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(30), VerticalAnchor.absolute(120)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(Direction.DOWN), 12),
                BiomeFilter.biome(), CountPlacement.of(32), OffsetPlacement.ofTriangle(4, 2),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(Direction.DOWN), 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.solid(Direction.DOWN)
                )));
        register(context, HANGING_MUSHROOMS_PLACED, configuredFeatures.getOrThrow(ModFeatures.HANGING_MUSHROOMS),
                NoiseThresholdCountPlacement.of(0.2, 1, 3), InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(90), VerticalAnchor.absolute(120)),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(Direction.UP), 8),
                BiomeFilter.biome(), CountPlacement.of(32), OffsetPlacement.ofTriangle(4, 2),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(Direction.UP), 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.solid(Direction.UP),
                        BlockPredicate.not(BlockPredicate.matchesTag(Direction.UP.getUnitVec3i(), ModTags.Blocks.HANGING_MUSHROOMS_CANNOT_GENERATE_UNDER))
                )));
        register(context, HANGING_MUSHROOMS_PROTOTAXITE_FOREST_PLACED, configuredFeatures.getOrThrow(ModFeatures.HANGING_MUSHROOMS),
                NoiseThresholdCountPlacement.of(0.2, 1, 3), InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(100), VerticalAnchor.absolute(130)),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(Direction.UP), 8),
                BiomeFilter.biome(), CountPlacement.of(32), OffsetPlacement.ofTriangle(4, 2),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(Direction.UP), 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.not(BlockPredicate.matchesTag(Direction.UP.getUnitVec3i(), ModTags.Blocks.HANGING_MUSHROOMS_CANNOT_GENERATE_UNDER)),
                        BlockPredicate.solid(Direction.UP)
                )));
        register(context, HANGING_MUSHROOMS_DEEP_PLACED, configuredFeatures.getOrThrow(ModFeatures.HANGING_MUSHROOMS),
                NoiseThresholdCountPlacement.of(0.3, 1, 4), InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(20), VerticalAnchor.absolute(70)),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(Direction.UP), 9),
                BiomeFilter.biome(), CountPlacement.of(48), OffsetPlacement.ofTriangle(6, 2),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(Direction.UP), 4),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.solid(Direction.UP),
                        BlockPredicate.not(BlockPredicate.matchesTag(Direction.UP.getUnitVec3i(), ModTags.Blocks.HANGING_MUSHROOMS_CANNOT_GENERATE_UNDER))
                )));

        register(context, SAND_SHELLS, configuredFeatures.getOrThrow(ModFeatures.SAND_SHELLS),
                RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(),
                OffsetPlacement.ofTriangle(4, 1), CountPlacement.of(4), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                        BlockPredicate.matchesFluids(Fluids.WATER), BlockPredicate.matchesFluids(Direction.UP.getUnitVec3i(), List.of(Fluids.WATER)))),
                BiomeFilter.biome());
        register(context, SAND_SHELLS_BONUS, configuredFeatures.getOrThrow(ModFeatures.SAND_SHELLS_SMALL),
                CountPlacement.of(UniformInt.of(1, 2)), InSquarePlacement.spread(), CountPlacement.of(7),
                OffsetPlacement.ofTriangle(5, 2), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                        BlockPredicate.matchesFluids(Fluids.WATER), BlockPredicate.matchesFluids(Direction.UP.getUnitVec3i(), List.of(Fluids.WATER)))),
                BiomeFilter.biome());

        register(context, SAND_SHELLS_BEACH, configuredFeatures.getOrThrow(ModFeatures.SAND_SHELLS),
                InSquarePlacement.spread(), CountPlacement.of(2), OffsetPlacement.ofTriangle(4, 1),
                RarityFilter.onAverageOnceEvery(3), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                        BlockPredicate.matchesFluids(Fluids.WATER), BlockPredicate.matchesFluids(Direction.UP.getUnitVec3i(), List.of(Fluids.WATER)))),
                BiomeFilter.biome());
        register(context, SAND_SHELLS_SMALL_BEACH, configuredFeatures.getOrThrow(ModFeatures.SAND_SHELLS_SMALL),
                CountPlacement.of(8), InSquarePlacement.spread(),
                CountPlacement.of(6), OffsetPlacement.ofTriangle(5, 0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                        besideFluidPredicate(Fluids.WATER, Fluids.FLOWING_WATER),
                        besideBlockPredicate(Blocks.ICE)
                    )),
                BiomeFilter.biome());

        register(context, BRIMGRASS_PLACED, configuredFeatures.getOrThrow(ModFeatures.BRIMGRASS),
                CountPlacement.of(200), OffsetPlacement.ofTriangle(7, 3),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(32), PlacementUtils.FULL_RANGE,
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                List.of(ModBlocks.BRIMGRASS_BLOCK)))));
        register(context, BRIMGRASS_BONEMEAL, configuredFeatures.getOrThrow(ModFeatures.BRIMGRASS), PlacementUtils.isEmpty());
        register(context, TENEBRIS_BUD_PLACED, configuredFeatures.getOrThrow(ModFeatures.TENEBRIS_BUD),
                CountPlacement.of(14), OffsetPlacement.ofTriangle(7, 3),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(4), PlacementUtils.FULL_RANGE,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                List.of(ModBlocks.BRIMGRASS_BLOCK))), 6),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                List.of(ModBlocks.BRIMGRASS_BLOCK)))));
        register(context, BRIMSTONE_RUINS, configuredFeatures.getOrThrow(ModFeatures.GOLD_BRIMSTONE_RUINS),
                CountPlacement.of(UniformInt.of(3, 5)), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(50), VerticalAnchor.aboveBottom(180)),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(),
                        BlockPredicate.anyOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesFluids(Fluids.LAVA, Fluids.FLOWING_LAVA)), 12),
                OffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());

        register(context, TENEBRIS_PLACED, configuredFeatures.getOrThrow(ModFeatures.TENEBRIS),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(32), PlacementUtils.FULL_RANGE,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(),
                                List.of(ModBlocks.BRIMGRASS_BLOCK, ModBlocks.BRIMSTONE))), 9));
        register(context, TENEBRIS_CEILING_PLACED, configuredFeatures.getOrThrow(ModFeatures.CEILING_TENEBRIS_PATCH),
                InSquarePlacement.spread(), BiomeFilter.biome(), CountPlacement.of(UniformInt.of(2, 5)), PlacementUtils.FULL_RANGE,
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.matchesBlocks(Direction.UP.getUnitVec3i(),
                                List.of(ModBlocks.IGNITED_BRIMSTONE, ModBlocks.BRIMSTONE))), 9));

        register(context, SALT_PLACED, configuredFeatures.getOrThrow(ModFeatures.SALT),
                PlacementUtils.HEIGHTMAP_NO_LEAVES,
                NoiseThresholdCountPlacement.of(0.4f, 40, 200),
                OffsetPlacement.of(ConstantInt.of(0), UniformInt.of(-2, -1)), InSquarePlacement.spread(), BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER, Fluids.FLOWING_WATER)));

        register(context, POTENT_MAGMA_ORE, configuredFeatures.getOrThrow(ModFeatures.POTENT_MAGMA_ORE),
                CountPlacement.of(6), HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.aboveBottom(60), 2)),
                InSquarePlacement.spread(), BiomeFilter.biome());
        register(context, POTENT_MAGMA_LAYER, configuredFeatures.getOrThrow(ModFeatures.POTENT_MAGMA_LAYER),
                InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(30), VerticalAnchor.aboveBottom(80)),
                RarityFilter.onAverageOnceEvery(2), CountPlacement.of(UniformInt.of(3, 9)),
                OffsetPlacement.ofTriangle(8, 1),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(
                        BlockPredicate.matchesFluids(Fluids.LAVA),
                        BlockPredicate.matchesBlocks(Direction.UP.getUnitVec3i(), List.of(Blocks.AIR))
                ), 24), BiomeFilter.biome());
        register(context, GOLD_BRIMSTONE_PLACED, configuredFeatures.getOrThrow(ModFeatures.GOLD_BRIMSTONE),
                HeightRangePlacement.of(BiasedToBottomHeight.of(
                        VerticalAnchor.aboveBottom(20), VerticalAnchor.aboveBottom(200), 1)),
                BiomeFilter.biome(), CountPlacement.of(12), InSquarePlacement.spread());
        register(context, NETHERSTONE_PLACED, configuredFeatures.getOrThrow(ModFeatures.NETHERSTONE),
                BiomeFilter.biome(), HeightRangePlacement.triangle(VerticalAnchor.BOTTOM, VerticalAnchor.aboveBottom(160)),
                CountPlacement.of(16), InSquarePlacement.spread());
        register(context, BRIMSTONE_PLACED, configuredFeatures.getOrThrow(ModFeatures.BRIMSTONE_PATCH),
                HeightRangePlacement.of(BiasedToBottomHeight.of(
                        VerticalAnchor.aboveBottom(20), VerticalAnchor.aboveBottom(40), 2)),
                BiomeFilter.biome(), CountPlacement.of(30), InSquarePlacement.spread());
        register(context, IGNITED_BRIMSTONE_PLACED, configuredFeatures.getOrThrow(ModFeatures.IGNITED_BRIMSTONE_PATCH),
                HeightRangePlacement.of(BiasedToBottomHeight.of(
                        VerticalAnchor.aboveBottom(15), VerticalAnchor.aboveBottom(30), 2)),
                BiomeFilter.biome(), NoiseThresholdCountPlacement.of(0.5f, 1, 3), InSquarePlacement.spread());

        register(context, BRIMSTONE_GLOWSTONE, configuredFeatures.getOrThrow(NetherFeatures.GLOWSTONE_EXTRA),
                CountPlacement.of(BiasedToBottomInt.of(0, 9)), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.UP, ModBlocks.BRIMSTONE, ModBlocks.NETHERSTONE))
                ), BiomeFilter.biome()
        );
        register(context, BRIMSTONE_GLOWSTONE_EXTRA, configuredFeatures.getOrThrow(NetherFeatures.GLOWSTONE_EXTRA),
                CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.UP, ModBlocks.BRIMSTONE, ModBlocks.NETHERSTONE))
                ), BiomeFilter.biome()
        );
    }

    public static ResourceKey<PlacedFeature> registryKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, JirniyBiomes.id(name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<Feature> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                                                                          Holder<Feature> configuration,
                                                                                          PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

    public static BlockPredicate besideFluidPredicate(Fluid... fluids) {
        return BlockPredicate.anyOf(
                BlockPredicate.matchesFluids(new BlockPos(1, -1, 0), List.of(fluids)),
                BlockPredicate.matchesFluids(new BlockPos(-1, -1, 0), List.of(fluids)),
                BlockPredicate.matchesFluids(new BlockPos(0, -1, 1), List.of(fluids)),
                BlockPredicate.matchesFluids(new BlockPos(0, -1, -1), List.of(fluids))
        );
    }

    public static BlockPredicate besideBlockPredicate(Block... blocks) {
        return BlockPredicate.anyOf(
                BlockPredicate.matchesBlocks(new BlockPos(1, -1, 0), List.of(blocks)),
                BlockPredicate.matchesBlocks(new BlockPos(-1, -1, 0), List.of(blocks)),
                BlockPredicate.matchesBlocks(new BlockPos(0, -1, 1), List.of(blocks)),
                BlockPredicate.matchesBlocks(new BlockPos(0, -1, -1), List.of(blocks))
        );
    }
}

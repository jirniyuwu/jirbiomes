package net.jirniy.jirbiomes.worldgen;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.block.custom.*;
import net.jirniy.jirbiomes.misc.ModTags;
import net.jirniy.jirbiomes.worldgen.blockstate.MapStateProvider;
import net.jirniy.jirbiomes.worldgen.blockstate.WeightedStateProviderProvider;
import net.jirniy.jirbiomes.worldgen.feature.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BlockStateProviders;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.AquaticFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBedBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.PlaceOnGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;

import java.util.List;
import java.util.OptionalInt;

public class ModFeatures {
    public static final ResourceKey<Feature> NOOP = registryKey("noop");

    public static final ResourceKey<Feature> NETHERSTONE = registryKey("netherstone");
    public static final ResourceKey<Feature> BRIMSTONE_PATCH = registryKey("brimstone_patch");
    public static final ResourceKey<Feature> IGNITED_BRIMSTONE_PATCH = registryKey("ignited_brimstone_patch");
    public static final ResourceKey<Feature> GOLD_BRIMSTONE = registryKey("brimstone_gold");

    public static final ResourceKey<Feature> ICE_TOP_LAYER = registryKey("ice_top_layer");
    public static final ResourceKey<Feature> ICICLE_CLUSTER = registryKey("icicle_cluster");
    public static final ResourceKey<Feature> LARGE_ICICLE = registryKey("large_icicle");
    public static final ResourceKey<Feature> POINTED_ICICLE = registryKey("pointed_icicle");
    public static final ResourceKey<Feature> BLUE_ICE_ORE = registryKey("blue_ice_ore");

    public static final ResourceKey<Feature> GINKGO_TREE = registryKey("ginkgo_tree");
    public static final ResourceKey<Feature> GINKGO_TREE_BEES_005 = registryKey("ginkgo_tree_bees_005");
    public static final ResourceKey<Feature> OLD_GROWTH_GINKGO_TREE = registryKey("old_growth_ginkgo_tree");
    public static final ResourceKey<Feature> OLD_GROWTH_GINKGO_TREE_BEES_005 = registryKey("old_growth_ginkgo_tree_bees_005");

    public static final ResourceKey<Feature> PALM_TREE = registryKey("palm_tree");
    public static final ResourceKey<Feature> PALM_TREE_BEES_005 = registryKey("palm_tree_bees_005");
    public static final ResourceKey<Feature> PALM_COCONUT_TREE = registryKey("palm_tree_coconut");

    public static final ResourceKey<Feature> PROTOTAXITE = registryKey("prototaxite");
    public static final ResourceKey<Feature> PROTOTAXITE_SMALL = registryKey("prototaxite_small");
    public static final ResourceKey<Feature> PROTOTAXITE_SEPTA = registryKey("prototaxite_septa");
    public static final ResourceKey<Feature> PROTOTAXITE_SEPTA_GROWTH = registryKey("prototaxite_septa_growth");
    public static final ResourceKey<Feature> PROTOTAXITE_WITH_SEPTAS = registryKey("prototaxite_septas");
    public static final ResourceKey<Feature> PROTOTAXITE_SMALL_WITH_SEPTAS = registryKey("prototaxite_small_septas");

    public static final ResourceKey<Feature> TENEBRIS = registryKey("tenebris");
    public static final ResourceKey<Feature> DOWNWARDS_TENEBRIS = registryKey("downwards_tenebris");
    public static final ResourceKey<Feature> BRIMSTONE_RUINS = registryKey("brimstone_ruins");
    public static final ResourceKey<Feature> GOLD_BRIMSTONE_RUINS = registryKey("gold_brimstone_ruins");

    public static final ResourceKey<Feature> APPLE_OAK_TREE = registryKey("apple_oak_tree");
    public static final ResourceKey<Feature> APPLE_OAK_TREE_BEES_005 = registryKey("apple_oak_tree_bees_005");
    public static final ResourceKey<Feature> FANCY_APPLE_OAK_TREE = registryKey("fancy_apple_oak_tree");
    public static final ResourceKey<Feature> FANCY_APPLE_OAK_TREE_BEES_005 = registryKey("fancy_apple_oak_tree_bees_005");

    public static final ResourceKey<Feature> DRIED_GRASS_PATCH = registryKey("dried_grass_patch");
    public static final ResourceKey<Feature> DRIED_DIRT_PATCH_DESERT = registryKey("dried_dirt_patch_desert");
    public static final ResourceKey<Feature> DRIED_GRASS_PATCH_DESERT = registryKey("dried_grass_patch_desert");
    public static final ResourceKey<Feature> WET_GRASS_PATCH = registryKey("wet_grass_patch");

    public static final ResourceKey<Feature> BARREL_CACTUS = registryKey("barrel_cactus");
    public static final ResourceKey<Feature> BARREL_CACTUS_PATCH = registryKey("barrel_cactus_patch");

    public static final ResourceKey<Feature> CATTAIL = registryKey("cattail");
    public static final ResourceKey<Feature> LOTUS_FLOWER = registryKey("lotus_flower");
    public static final ResourceKey<Feature> ALGAE = registryKey("algae");
    public static final ResourceKey<Feature> ALGAE_PATCH = registryKey("algae_patch");
    public static final ResourceKey<Feature> FROZEN_GRASS = registryKey("frozen_grass");
    public static final ResourceKey<Feature> SNOWY_BUSH = registryKey("snowy_bush");
    public static final ResourceKey<Feature> SHARP_RIBS = registryKey("sharp_ribs");
    public static final ResourceKey<Feature> SEA_URCHIN = registryKey("sea_urchin");
    public static final ResourceKey<Feature> SEA_URCHIN_SHIPWRECK = registryKey("sea_urchin_shipwreck");
    public static final ResourceKey<Feature> SAND_SHELLS = registryKey("sand_shells");
    public static final ResourceKey<Feature> SAND_SHELLS_SMALL = registryKey("sand_shells_small");
    public static final ResourceKey<Feature> SMALL_MUSHROOMS = registryKey("small_mushrooms");
    public static final ResourceKey<Feature> HANGING_MUSHROOMS = registryKey("hanging_mushrooms");
    public static final ResourceKey<Feature> MUD_POOL = registryKey("mud_pool");

    public static final ResourceKey<Feature> POTENT_MAGMA_LAYER = registryKey("potent_magma_layer");
    public static final ResourceKey<Feature> POTENT_MAGMA_ORE = registryKey("potent_magma_ore");
    public static final ResourceKey<Feature> BRIMGRASS = registryKey("brimgrass");
    public static final ResourceKey<Feature> TENEBRIS_BUD = registryKey("tenebris_bud");
    public static final ResourceKey<Feature> CEILING_TENEBRIS_PATCH = registryKey("ceiling_tenebris_patch");
    public static final ResourceKey<Feature> SALT = registryKey("salt");

    public static void bootstrap(BootstrapContext<Feature> context) {
        HolderGetter<Feature> configuredFeatures = context.lookup(Registries.FEATURE);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        HolderGetter<BlockStateProvider> blockStateProviders = context.lookup(Registries.BLOCK_STATE_PROVIDER);

        context.register(NOOP, new NoOpFeature());

        Holder<BlockStateProvider> belowTrunkProvider = blockStateProviders.getOrThrow(BlockStateProviders.SOIL_BENEATH_TREE);
        Holder<BlockStateProvider> palmTreeBelowTrunkProvider = Holder.<BlockStateProvider>direct(new MapStateProvider(
                new Block[]{Blocks.SAND, Blocks.GRASS_BLOCK, Blocks.DIRT, ModBlocks.DRIED_GRASS_BLOCK, ModBlocks.DRIED_DIRT, Blocks.WATER, Blocks.AIR},
                new Block[]{Blocks.SAND, Blocks.DIRT, Blocks.DIRT, ModBlocks.ROOTED_DRIED_DIRT, ModBlocks.ROOTED_DRIED_DIRT, ModBlocks.PALM_LOG, ModBlocks.PALM_LOG}
        ));

        Holder<BlockStateProvider> driedDirtProvider = Holder.<BlockStateProvider>direct(new MapStateProvider(
                new Block[]{Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.ROOTED_DIRT, Blocks.COARSE_DIRT, Blocks.DIRT_PATH, Blocks.FARMLAND},
                new Block[]{ModBlocks.DRIED_GRASS_BLOCK, ModBlocks.DRIED_DIRT, ModBlocks.ROOTED_DRIED_DIRT, ModBlocks.COARSE_DRIED_DIRT, ModBlocks.DRIED_DIRT_PATH, ModBlocks.DRY_FARMLAND}));
        Holder<BlockStateProvider> wetlandProvider = Holder.<BlockStateProvider>direct(new MapStateProvider(
                new Block[]{Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.ROOTED_DIRT, Blocks.COARSE_DIRT, Blocks.DIRT_PATH, Blocks.FARMLAND},
                new Block[]{ModBlocks.WET_GRASS_BLOCK, ModBlocks.WETLAND, ModBlocks.ROOTED_WETLAND, ModBlocks.COARSE_WETLAND, ModBlocks.WETLAND_PATH, ModBlocks.WET_FARMLAND}));
        Holder<BlockStateProvider> rootsProvider = Holder.<BlockStateProvider>direct(new MapStateProvider(
                new Block[]{Blocks.SAND, Blocks.RED_SAND, Blocks.DIRT, Blocks.GRASS_BLOCK, ModBlocks.DRIED_DIRT, ModBlocks.DRIED_GRASS_BLOCK, ModBlocks.WETLAND, ModBlocks.WET_GRASS_BLOCK, Blocks.MUD},
                new Block[]{ModBlocks.ROOTED_SAND, ModBlocks.ROOTED_RED_SAND, Blocks.ROOTED_DIRT, Blocks.ROOTED_DIRT, ModBlocks.ROOTED_DRIED_DIRT, ModBlocks.ROOTED_DRIED_DIRT, ModBlocks.ROOTED_WETLAND, ModBlocks.ROOTED_WETLAND, ModBlocks.ROOTED_MUD}));

        PlaceOnGroundDecorator sparseLeafLitter = new PlaceOnGroundDecorator(
                96, 4, 2, Holder.<BlockStateProvider>direct(new WeightedStateProvider(VegetationFeatures.leafLitterPatchBuilder(1, 3)))
        );
        PlaceOnGroundDecorator thickLeafLitter = new PlaceOnGroundDecorator(
                150, 2, 2, Holder.<BlockStateProvider>direct(new WeightedStateProvider(VegetationFeatures.leafLitterPatchBuilder(1, 4)))
        );

        BeehiveDecorator beehive0002 = new BeehiveDecorator(0.002F);
        BeehiveDecorator beehive001 = new BeehiveDecorator(0.01F);
        BeehiveDecorator beehive002 = new BeehiveDecorator(0.02F);
        BeehiveDecorator beehive005 = new BeehiveDecorator(0.05F);
        BeehiveDecorator beehive = new BeehiveDecorator(1.0F);

        context.register(TENEBRIS, new TenebrisFeature(
                simpleStateHolder(ModBlocks.TENEBRIS_LOG), UniformInt.of(3, 4), Direction.UP,
                simpleStateHolder(ModBlocks.TENEBRIS_LEAVES), UniformInt.of(0, 1), simpleStateHolder(Blocks.SHROOMLIGHT)));
        context.register(DOWNWARDS_TENEBRIS, new TenebrisFeature(
                simpleStateHolder(ModBlocks.TENEBRIS_LOG), UniformInt.of(2, 7), Direction.DOWN,
                simpleStateHolder(ModBlocks.TENEBRIS_LEAVES), UniformInt.of(1, 2), simpleStateHolder(Blocks.SHROOMLIGHT)));

        TreeFeature.Builder ginkgoTreeConfig = new TreeFeature.Builder(
                simpleState(ModBlocks.GINKGO_LOG),
                new FancyTrunkPlacer(9, 4, 1),
                simpleState(ModBlocks.GINKGO_LEAVES),
                new AcaciaFoliagePlacer(UniformInt.of(2, 3), ConstantInt.of(0)),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)),
                belowTrunkProvider
        );
        TreeFeature.Builder oldGrowthGinkgoTreeConfig = new TreeFeature.Builder(
                simpleState(ModBlocks.GINKGO_LOG),
                new FancyTrunkPlacer(11, 6, 1),
                simpleState(ModBlocks.GINKGO_LEAVES),
                new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)),
                belowTrunkProvider
        );

        TreeFeature.Builder palmTreeConfig = new TreeFeature.Builder(
                simpleState(ModBlocks.PALM_LOG),
                new StraightTrunkPlacer(6, 1, 0),
                simpleState(ModBlocks.PALM_LEAVES),
                new PalmFoliagePlacer(UniformInt.of(4, 6), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1),
                palmTreeBelowTrunkProvider);

        context.register(PALM_TREE, palmTreeConfig.ignoreVines().build());
        context.register(PALM_TREE_BEES_005, palmTreeConfig.ignoreVines().decorators(List.of(beehive005)).build());
        context.register(PALM_COCONUT_TREE, palmTreeConfig.ignoreVines()
                .decorators(List.of(new CoconutDecorator(0.3f, UniformInt.of(1, 2)))).build());

        context.register(PROTOTAXITE, new TaxiteFeature(
                simpleStateHolder(ModBlocks.PROTOTAXITE_LOG), ConstantInt.of(2),
                UniformInt.of(18, 25), UniformInt.of(3, 4),
                0.7f, 0.6f, false
        ));
        context.register(PROTOTAXITE_SMALL, new TaxiteFeature(
                simpleStateHolder(ModBlocks.PROTOTAXITE_LOG), ConstantInt.of(1),
                UniformInt.of(14, 20), UniformInt.of(2, 4), 0.6f, 0.8f, false
        ));
        context.register(PROTOTAXITE_SEPTA, new SimpleBlockFeature(simpleState(ModBlocks.PROTOTAXITE_SAPLING)));
        context.register(PROTOTAXITE_SEPTA_GROWTH, new SequenceFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(new BlockColumnFeature(
                                List.of(new BlockColumnFeature.Layer(
                                                UniformInt.of(1, 2), simpleStateHolder(ModBlocks.PROTOTAXITE_LOG)),
                                        new BlockColumnFeature.Layer(
                                                ConstantInt.of(1), simpleStateHolder(ModBlocks.PROTOTAXITE_SAPLING))),
                                Direction.UP, BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, true
                        )),
                        PlacementUtils.inlinePlaced(new SimpleBlockFeature(rootsProvider),
                                OffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(-1)))
                )
        ));
        context.register(PROTOTAXITE_WITH_SEPTAS, new SequenceFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(PROTOTAXITE)),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(PROTOTAXITE_SEPTA),
                                CountPlacement.of(UniformInt.of(8, 16)), OffsetPlacement.of(
                                        TrapezoidInt.of(-4, 4, 0), ConstantInt.of(16)),
                                OffsetPlacement.of(ConstantInt.ZERO, ConstantInt.of(16)),
                                PlacementUtils.HEIGHTMAP_NO_LEAVES, PlacementUtils.filteredByBlockSurvival(ModBlocks.PROTOTAXITE_SAPLING)),
                        PlacementUtils.inlinePlaced(new SimpleBlockFeature(rootsProvider),
                                CountPlacement.of(UniformInt.of(64, 128)),
                                OffsetPlacement.of(TrapezoidInt.of(-2, 2, 0),
                                        TrapezoidInt.of(-11, -2, 0)))
                )
        ));
        context.register(PROTOTAXITE_SMALL_WITH_SEPTAS, new SequenceFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(PROTOTAXITE_SMALL)),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(PROTOTAXITE_SEPTA),
                                CountPlacement.of(UniformInt.of(3, 5)), OffsetPlacement.of(
                                        TrapezoidInt.of(-3, 3, 0), ConstantInt.of(16)),
                                OffsetPlacement.of(ConstantInt.ZERO, ConstantInt.of(16)),
                                PlacementUtils.HEIGHTMAP_NO_LEAVES, PlacementUtils.filteredByBlockSurvival(ModBlocks.PROTOTAXITE_SAPLING)),
                        PlacementUtils.inlinePlaced(new SimpleBlockFeature(rootsProvider),
                                CountPlacement.of(UniformInt.of(64, 128)),
                                OffsetPlacement.of(TrapezoidInt.of(-2, 2, 0),
                                        TrapezoidInt.of(-11, -2, 0)))
                )
        ));

        context.register(APPLE_OAK_TREE, new TreeFeature.Builder(
                simpleState(Blocks.OAK_LOG),
                new StraightTrunkPlacer(4, 2, 0),
                new WeightedStateProvider(
                        WeightedList.<BlockState>builder()
                                .add(ModBlocks.APPLE_LEAVES.defaultBlockState().setValue(AppleLeavesBlock.STAGE, 1), 2)
                                .add(ModBlocks.APPLE_LEAVES.defaultBlockState().setValue(AppleLeavesBlock.STAGE, 2), 1)
                ),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1),
                belowTrunkProvider).ignoreVines().build()
        );
        context.register(APPLE_OAK_TREE_BEES_005, new TreeFeature.Builder(
                simpleState(Blocks.OAK_LOG),
                new StraightTrunkPlacer(4, 2, 0),
                new WeightedStateProvider(
                        WeightedList.<BlockState>builder()
                                .add(ModBlocks.APPLE_LEAVES.defaultBlockState().setValue(AppleLeavesBlock.STAGE, 1), 2)
                                .add(ModBlocks.APPLE_LEAVES.defaultBlockState().setValue(AppleLeavesBlock.STAGE, 2), 1)
                ),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1),
                belowTrunkProvider).ignoreVines().decorators(List.of(beehive005)).build()
        );

        context.register(FANCY_APPLE_OAK_TREE, new TreeFeature.Builder(
                simpleState(Blocks.OAK_LOG),
                new FancyTrunkPlacer(3, 11, 0),
                new WeightedStateProvider(
                        WeightedList.<BlockState>builder()
                                .add(ModBlocks.APPLE_LEAVES.defaultBlockState().setValue(AppleLeavesBlock.STAGE, 1), 2)
                                .add(ModBlocks.APPLE_LEAVES.defaultBlockState().setValue(AppleLeavesBlock.STAGE, 2), 1)
                ),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)),
                belowTrunkProvider).ignoreVines().build()
        );
        context.register(FANCY_APPLE_OAK_TREE_BEES_005, new TreeFeature.Builder(
                simpleState(Blocks.OAK_LOG),
                new FancyTrunkPlacer(3, 11, 0),
                new WeightedStateProvider(
                        WeightedList.<BlockState>builder()
                                .add(ModBlocks.APPLE_LEAVES.defaultBlockState().setValue(AppleLeavesBlock.STAGE, 1), 2)
                                .add(ModBlocks.APPLE_LEAVES.defaultBlockState().setValue(AppleLeavesBlock.STAGE, 2), 1)
                ),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)),
                belowTrunkProvider).ignoreVines().decorators(List.of(beehive005)).build()
        );

        context.register(GINKGO_TREE, ginkgoTreeConfig.ignoreVines().build());
        context.register(GINKGO_TREE_BEES_005, ginkgoTreeConfig.decorators(List.of(beehive005)).ignoreVines().build());
        context.register(OLD_GROWTH_GINKGO_TREE, new SequenceFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(oldGrowthGinkgoTreeConfig.ignoreVines().build()),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(DRIED_GRASS_PATCH),
                                CountPlacement.of(UniformInt.of(2, 3)),
                                OffsetPlacement.of(ConstantInt.of(4), UniformInt.of(-3, 1))),
                        PlacementUtils.inlinePlaced(new SimpleBlockFeature(RuleBasedStateProvider.ifTrueThenProvide(
                                        BlockPredicate.allOf(BlockPredicate.solid(), BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.FEATURES_CANNOT_REPLACE))),
                                        ModBlocks.ROOTED_DRIED_DIRT)),
                                CountPlacement.of(UniformInt.of(16, 60)),
                                OffsetPlacement.of(TrapezoidInt.of(-2, 2, 0), TrapezoidInt.of(-8, -1, 0)))
                )
        ));
        context.register(OLD_GROWTH_GINKGO_TREE_BEES_005, new SequenceFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(oldGrowthGinkgoTreeConfig.ignoreVines().decorators(List.of(beehive005)).build()),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(DRIED_GRASS_PATCH),
                                CountPlacement.of(UniformInt.of(2, 3)),
                                OffsetPlacement.of(ConstantInt.of(4), UniformInt.of(-3, 1))),
                        PlacementUtils.inlinePlaced(new SimpleBlockFeature(RuleBasedStateProvider.ifTrueThenProvide(
                                        BlockPredicate.allOf(BlockPredicate.solid(), BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.FEATURES_CANNOT_REPLACE))),
                                        ModBlocks.ROOTED_DRIED_DIRT)),
                                CountPlacement.of(UniformInt.of(16, 60)),
                                OffsetPlacement.of(TrapezoidInt.of(-2, 2, 0), TrapezoidInt.of(-8, -1, 0)))
                )
        ));

        context.register(ICE_TOP_LAYER, new SimpleBlockFeature(
                RuleBasedStateProvider.builder()
                        .ifTrueThenProvide(BlockPredicate.allOf(BlockPredicate.matchesBlocks(Blocks.WATER),
                                BlockPredicate.matchesBlocks(Direction.UP.getUnitVec3i(), List.of(Blocks.AIR))), Blocks.ICE)
                        .ifTrueThenProvide(BlockPredicate.allOf(BlockPredicate.matchesBlocks(Blocks.LAVA),
                                BlockPredicate.matchesBlocks(Direction.UP.getUnitVec3i(), List.of(Blocks.AIR))), ModBlocks.POTENT_MAGMA_BLOCK).build()
        ));
        context.register(BLUE_ICE_ORE, new OreFeature(
                List.of(BlockReplacement.replace(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), Blocks.BLUE_ICE.defaultBlockState()),
                        BlockReplacement.replace(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), Blocks.PACKED_ICE.defaultBlockState()),
                        BlockReplacement.replace(new BlockMatchTest(Blocks.PACKED_ICE), Blocks.BLUE_ICE.defaultBlockState())),
                24, 0.6f));

        // copied from dripstone features
        context.register(ICICLE_CLUSTER, new SpeleothemClusterFeature(
                ModBlocks.PERMAFROST_BLOCK.defaultBlockState(), ModBlocks.ICICLE.defaultBlockState(),
                blocks.getOrThrow(BlockTags.DRIPSTONE_REPLACEABLE), 12, UniformInt.of(3, 6), UniformInt.of(2, 8),
                1, 3, UniformInt.of(2, 4), UniformFloat.of(0.3F, 0.7F),
                ClampedNormalFloat.of(0.1F, 0.3F, 0.1F, 0.9F),
                0.1F, 3, 8));
        context.register(LARGE_ICICLE, new LargeIcicleFeature(
                simpleStateHolder(ModBlocks.PERMAFROST_BLOCK), blocks.getOrThrow(BlockTags.DRIPSTONE_REPLACEABLE), 30,
                ClampedInt.of(UniformInt.of(3, 19), 3, 16),
                UniformFloat.of(0.4F, 2.0F), 0.33F, UniformFloat.of(0.3F, 0.9F),
                UniformFloat.of(0.4F, 1.0F), UniformFloat.of(0.0F, 0.3F), 4, 0.6F));
        context.register(POINTED_ICICLE, new SimpleRandomSelectorFeature(
                HolderSet.direct(PlacementUtils.inlinePlaced(new SpeleothemFeature(ModBlocks.PERMAFROST_BLOCK.defaultBlockState(),
                                ModBlocks.ICICLE.defaultBlockState(),
                                blocks.getOrThrow(BlockTags.DRIPSTONE_REPLACEABLE),
                                0.2F, 0.7F,
                                0.5F, 0.5F),
                                        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(),
                                                BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                                OffsetPlacement.vertical(ConstantInt.of(1))),
                        PlacementUtils.inlinePlaced(new SpeleothemFeature(ModBlocks.PERMAFROST_BLOCK.defaultBlockState(),
                                        ModBlocks.ICICLE.defaultBlockState(), blocks.getOrThrow(BlockTags.DRIPSTONE_REPLACEABLE),
                                        0.2F, 0.7F,
                                        0.5F, 0.5F),
                                                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(),
                                                        BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                                        OffsetPlacement.vertical(ConstantInt.of(-1))))));

        context.register(BARREL_CACTUS, new BlockColumnFeature(
                List.of(
                        BlockColumnFeature.layer(UniformInt.of(0, 2), simpleState(ModBlocks.LARGE_BARREL_CACTUS)),
                        BlockColumnFeature.layer(ConstantInt.of(1),
                                new WeightedStateProviderProvider(WeightedList.<Holder<BlockStateProvider>>builder()
                                        .add(simpleStateHolder(ModBlocks.PRICKLY_PEAR_SEED.defaultBlockState()), 2)
                                        .add(simpleStateHolder(ModBlocks.SMALL_BARREL_CACTUS.defaultBlockState()
                                                .setValue(SmallBarrelCactusBlock.AGE, 1)), 5)
                                        .add(simpleStateHolder(ModBlocks.SMALL_BARREL_CACTUS.defaultBlockState()
                                                .setValue(SmallBarrelCactusBlock.AGE, 2)), 5)
                                        .add(Holder.direct(RuleBasedStateProvider.ifTrueThenProvide(BlockPredicate
                                                .matchesBlocks(Direction.DOWN.getUnitVec3i(), List.of(ModBlocks.LARGE_BARREL_CACTUS)), 
                                                Blocks.CACTUS_FLOWER)), 1)
                                        .add(simpleStateHolder(Blocks.AIR.defaultBlockState()), 9)
                                ))
                ),
                Direction.UP, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
        ));
        context.register(BARREL_CACTUS_PATCH, new SequenceFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(BARREL_CACTUS),
                                CountPlacement.of(UniformInt.of(5, 6)), PlacementUtils.HEIGHTMAP_NO_LEAVES,
                                OffsetPlacement.of(TrapezoidInt.of(-3, 3, 0), ConstantInt.of(0)),
                                EnvironmentScanPlacement.scanningFor(
                                        Direction.DOWN, BlockPredicate.matchesTag(new Vec3i(0, -1, 0),
                                                ModTags.Blocks.SUPPORTS_BARREL_CACTUS), 4),
                                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
                        ),
                        PlacementUtils.inlinePlaced(new SimpleBlockFeature(
                                        RuleBasedStateProvider.builder()
                                                .ifTrueThenProvide(BlockPredicate.matchesBlocks(Blocks.SAND, Blocks.SANDSTONE),
                                                        ModBlocks.ROOTED_SAND)
                                                .ifTrueThenProvide(BlockPredicate.matchesBlocks(Blocks.RED_SAND),
                                                        ModBlocks.ROOTED_RED_SAND)
                                                .ifTrueThenProvide(BlockPredicate.matchesBlocks(ModBlocks.DRIED_DIRT),
                                                        ModBlocks.ROOTED_DRIED_DIRT).build()),
                                CountPlacement.of(UniformInt.of(5, 12)), PlacementUtils.HEIGHTMAP_NO_LEAVES,
                                OffsetPlacement.of(
                                        TrapezoidInt.of(-2, 2, 0),
                                        TrapezoidInt.of(-3, -1, 0)),
                                BlockPredicateFilter.forPredicate(BlockPredicate.solid())
                        )
                )
        ));

        context.register(DRIED_GRASS_PATCH, new DiskFeature(
                driedDirtProvider, BlockPredicate.solid(), UniformInt.of(3, 7), 4));
        context.register(DRIED_DIRT_PATCH_DESERT, new SequenceFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(new SimpleBlockFeature(
                                RuleBasedStateProvider.ifTrueThenProvide(
                                        BlockPredicate.matchesBlocks(Blocks.SAND, Blocks.SANDSTONE), ModBlocks.DRIED_DIRT)),
                                CountPlacement.of(UniformInt.of(48, 60)),
                                OffsetPlacement.ofTriangle(5, 2),
                                EnvironmentScanPlacement.scanningFor(
                                        Direction.DOWN, BlockPredicate.solid(), 4)),
                        PlacementUtils.inlinePlaced(new SimpleBlockFeature(
                                        RuleBasedStateProvider.ifTrueThenProvide(
                                                BlockPredicate.matchesBlocks(Blocks.SAND), ModBlocks.DRIED_DIRT)),
                                CountPlacement.of(UniformInt.of(54, 88)),
                                OffsetPlacement.ofTriangle(6, 3)
                        )
                )
        ));
        context.register(DRIED_GRASS_PATCH_DESERT, new SequenceFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(new SimpleBlockFeature(
                                        RuleBasedStateProvider.builder().ifTrueThenProvide(
                                                BlockPredicate.allOf(BlockPredicate.matchesBlocks(Blocks.SAND, ModBlocks.DRIED_DIRT, Blocks.SANDSTONE),
                                                        BlockPredicate.matchesBlocks(Direction.UP.getUnitVec3i(), List.of(Blocks.AIR))),
                                                ModBlocks.DRIED_GRASS_BLOCK
                                        ).build()),
                                CountPlacement.of(UniformInt.of(54, 88)),
                                OffsetPlacement.ofTriangle(7, 2),
                                EnvironmentScanPlacement.scanningFor(
                                        Direction.DOWN, BlockPredicate.allOf(BlockPredicate.solid(),
                                                BlockPredicate.matchesBlocks(Direction.UP.getUnitVec3i(), List.of(Blocks.AIR))), 4)),
                        PlacementUtils.inlinePlaced(new SimpleBlockFeature(
                                        RuleBasedStateProvider.ifTrueThenProvide(
                                                BlockPredicate.matchesBlocks(Blocks.SAND), ModBlocks.DRIED_DIRT)),
                                CountPlacement.of(UniformInt.of(60, 102)),
                                OffsetPlacement.ofTriangle(7, 3)
                        )
                )
        ));
        context.register(WET_GRASS_PATCH, new DiskFeature(
                wetlandProvider, BlockPredicate.solid(), UniformInt.of(3, 7), 4));

        context.register(BRIMSTONE_RUINS, new TemplateFeature(
                WeightedList.<TemplateFeature.TemplateEntry>builder()
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/small1"), List.of(Rotation.NONE)), 4)
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/small2"), List.of(Rotation.NONE)), 4)
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/small3"), List.of(Rotation.values())), 4)
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/small4"), List.of(Rotation.values())), 4)
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/small5"), List.of(Rotation.values())), 4)
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/small6"), List.of(Rotation.NONE)), 4)
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/medium1"), List.of(Rotation.values())), 3)
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/medium2"), List.of(Rotation.values())), 2)
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/medium3"), List.of(Rotation.NONE)), 1)
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/medium4"), List.of(Rotation.values())), 3)
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/large1"), List.of(Rotation.NONE)), 1)
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/large2"), List.of(Rotation.NONE)), 1)
                        .add(new TemplateFeature.TemplateEntry(JirniyBiomes.id("brimstone_ruins/large3"), List.of(Rotation.values())), 2)
                        .build()
        ));
        context.register(GOLD_BRIMSTONE_RUINS, new RootSystemFeature(
                PlacementUtils.inlinePlaced(new SequenceFeature(
                                HolderSet.direct(
                                        PlacementUtils.inlinePlaced(new DiskFeature(
                                                simpleStateHolder(ModBlocks.BRIMSTONE),
                                                BlockPredicate.allOf(
                                                        BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.FEATURES_CANNOT_REPLACE)),
                                                        BlockPredicate.not(BlockPredicate.matchesBlocks(ModBlocks.BRIMSTONE_GOLD_ORE))
                                                ),
                                                UniformInt.of(4, 6), 2),
                                                OffsetPlacement.vertical(ConstantInt.of(-2))),
                                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(BRIMSTONE_RUINS),
                                                CountPlacement.of(1))
                                )),
                        CountPlacement.of(1), OffsetPlacement.vertical(ConstantInt.of(-1))),
                3, 0, 0, 2,
                blocks.getOrThrow(ModTags.Blocks.BRIMSTONE_GOLD_REPLACEABLE),
                Holder.<BlockStateProvider>direct(new MapStateProvider(
                        new Block[]{ModBlocks.BRIMSTONE, ModBlocks.IGNITED_BRIMSTONE},
                        List.<Holder<BlockStateProvider>>of(Holder.direct(new WeightedStateProvider(
                                    WeightedList.<BlockState>builder()
                                            .add(ModBlocks.BRIMSTONE.defaultBlockState(), 2)
                                            .add(ModBlocks.BRIMSTONE_GOLD_ORE.defaultBlockState(), 1)
                                )),
                                Holder.direct(new WeightedStateProvider(
                                    WeightedList.<BlockState>builder()
                                            .add(ModBlocks.IGNITED_BRIMSTONE.defaultBlockState(), 1)
                                            .add(ModBlocks.BRIMSTONE_GOLD_ORE.defaultBlockState(), 3)
                                ))))),
                7, 17, 3, 2,
                simpleStateHolder(Blocks.AIR), 1, 1,
                BlockPredicate.allOf(BlockPredicate.anyOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.not(BlockPredicate.solid())),
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), List.of(ModBlocks.BRIMGRASS_BLOCK, ModBlocks.BRIMSTONE)))
        ));

        context.register(CEILING_TENEBRIS_PATCH, new VegetationPatchFeature(
                blocks.getOrThrow(ModTags.Blocks.BRIMSTONE_GOLD_REPLACEABLE),
                simpleStateHolder(ModBlocks.BRIMSTONE),
                PlacementUtils.inlinePlaced(new WeightedRandomSelectorFeature(
                        WeightedList.<Holder<PlacedFeature>>builder()
                                .add(PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(DOWNWARDS_TENEBRIS)), 1)
                                .add(PlacementUtils.inlinePlaced(new SimpleBlockFeature(
                                        simpleState(ModBlocks.TENEBRIS_LEAVES.defaultBlockState()
                                                .setValue(PlantLikeLeavesBlock.BLOCKSHAPE, AttachFace.CEILING)))), 4)
                                .build())),
                CaveSurface.CEILING, UniformInt.of(1, 2), 0f, 5, 0.2f,
                UniformInt.of(2, 5), 0.75F
        ));

        context.register(SAND_SHELLS, new OreFeature(
                List.of(BlockReplacement.replace(new BlockMatchTest(Blocks.SAND), ModBlocks.SAND_SHELLS.defaultBlockState()),
                        BlockReplacement.replace(new BlockMatchTest(Blocks.RED_SAND), ModBlocks.RED_SAND_SHELLS.defaultBlockState())),
                28, 0f));
        context.register(SAND_SHELLS_SMALL, new OreFeature(
                List.of(BlockReplacement.replace(new BlockMatchTest(Blocks.SAND), ModBlocks.SAND_SHELLS.defaultBlockState()),
                        BlockReplacement.replace(new BlockMatchTest(Blocks.RED_SAND), ModBlocks.RED_SAND_SHELLS.defaultBlockState())),
                8, 0.1f));
        context.register(SMALL_MUSHROOMS, new SimpleBlockFeature(
                new WeightedStateProvider(flowerBedPatchBuilder(ModBlocks.SMALL_MUSHROOMS))));
        context.register(HANGING_MUSHROOMS, new BlockColumnFeature(
                List.of(BlockColumnFeature.layer(
                            TrapezoidInt.of(0, 3, 1), new WeightedStateProvider(WeightedList.<BlockState>builder()
                                        .add(ModBlocks.HANGING_MUSHROOMS.defaultBlockState().setValue(HangingMushroomsBlock.LIT, true)
                                                .setValue(HangingMushroomsBlock.TIP, false), 2)
                                        .add(ModBlocks.HANGING_MUSHROOMS.defaultBlockState().setValue(HangingMushroomsBlock.LIT, false)
                                                .setValue(HangingMushroomsBlock.TIP, false), 1)
                                )),
                        BlockColumnFeature.layer(
                            ConstantInt.of(1), new WeightedStateProvider(WeightedList.<BlockState>builder()
                                        .add(ModBlocks.HANGING_MUSHROOMS.defaultBlockState().setValue(HangingMushroomsBlock.LIT, true)
                                                .setValue(HangingMushroomsBlock.TIP, true), 2)
                                        .add(ModBlocks.HANGING_MUSHROOMS.defaultBlockState().setValue(HangingMushroomsBlock.LIT, false)
                                                .setValue(HangingMushroomsBlock.TIP, true), 1)
                                ))),
                Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, true
        ));
        context.register(MUD_POOL, new WaterloggedVegetationPatchFeature(
                blocks.getOrThrow(ModTags.Blocks.PROTOTAXITE_ROOTS_REPLACEABLE),
                simpleStateHolder(Blocks.MUD),
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AquaticFeatures.SEAGRASS_SHORT)),
                CaveSurface.FLOOR, ConstantInt.of(2), 0.8F, 5, 0.1F,
                UniformInt.of(3, 4), 0.7F
        ));

        context.register(LOTUS_FLOWER, new SimpleBlockFeature(RuleBasedStateProvider
                .builder(simpleState(ModBlocks.LOTUS_FLOWER.defaultBlockState().setValue(LotusFlowerBlock.FLOATING, false)))
                .ifTrueThenProvide(BlockPredicate.matchesFluids(Direction.DOWN.getUnitVec3i(), List.of(Fluids.WATER)),
                        simpleState(ModBlocks.LOTUS_FLOWER.defaultBlockState().setValue(LotusFlowerBlock.FLOATING, true))).build()));
        context.register(CATTAIL, new SimpleBlockFeature(simpleState(ModBlocks.CATTAIL)));
        context.register(FROZEN_GRASS, new SimpleBlockFeature(simpleState(ModBlocks.FROZEN_GRASS)));
        context.register(SNOWY_BUSH, new SimpleBlockFeature(simpleStateHolder(
                ModBlocks.SNOWY_BUSH.defaultBlockState().setValue(SnowloggableVegetationBlock.SNOWLOGGED_LAYERS, 1)), true));
        context.register(SEA_URCHIN, new SimpleBlockFeature(simpleState(
                ModBlocks.SEA_URCHIN.defaultBlockState().setValue(SeaUrchinBlock.WATERLOGGED, true).setValue(SeaUrchinBlock.FLOATING, false))));
        context.register(SEA_URCHIN_SHIPWRECK, new SimpleBlockFeature(RuleBasedStateProvider
                .builder(simpleState(ModBlocks.SEA_URCHIN.defaultBlockState().setValue(SeaUrchinBlock.WATERLOGGED, true).setValue(SeaUrchinBlock.FLOATING, true)))
                .ifTrueThenProvide(BlockPredicate.hasSturdyFace(Direction.DOWN, Direction.UP),
                        ModBlocks.SEA_URCHIN.defaultBlockState().setValue(SeaUrchinBlock.WATERLOGGED, true).setValue(SeaUrchinBlock.FLOATING, false)).build()));
        context.register(ALGAE, new SimpleBlockFeature(new WeightedStateProvider(
                WeightedList.<BlockState>builder()
                        .add(ModBlocks.ALGAE.defaultBlockState().setValue(AlgaeBlock.DENSE, false), 2)
                        .add(ModBlocks.ALGAE.defaultBlockState().setValue(AlgaeBlock.DENSE, true), 1)
        )));
        context.register(ALGAE_PATCH, new SequenceFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ALGAE),
                                CountPlacement.of(20), OffsetPlacement.ofTriangle(3, 0),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                        BlockPredicate.matchesFluids(Fluids.WATER),
                                        BlockPredicate.anyOf(BlockPredicate.matchesTag(
                                                Direction.UP.getUnitVec3i(), ModTags.Blocks.ALGAE_SURVIVES_UNDER
                                        ), BlockPredicate.matchesTag(
                                                Direction.UP.getUnitVec3i(), BlockTags.AIR
                                        ))
                                ))))
        ));
        context.register(SHARP_RIBS, new SimpleBlockFeature(new WeightedStateProvider(
                WeightedList.<BlockState>builder()
                        .add(ModBlocks.SHARP_RIBS.defaultBlockState().setValue(RibsBlock.AXIS, Direction.Axis.X))
                        .add(ModBlocks.SHARP_RIBS.defaultBlockState().setValue(RibsBlock.AXIS, Direction.Axis.Z))
        )));

        context.register(POTENT_MAGMA_LAYER, new SequenceFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(new SimpleBlockFeature(RuleBasedStateProvider
                                        .ifTrueThenProvide(BlockPredicate.allOf(
                                                BlockPredicate.matchesFluids(Fluids.LAVA),
                                                BlockPredicate.matchesBlocks(Direction.UP.getUnitVec3i(), List.of(Blocks.AIR))
                                        ), ModBlocks.POTENT_MAGMA_BLOCK)),
                                CountPlacement.of(26), OffsetPlacement.ofTriangle(4, 0)))
        ));
        context.register(POTENT_MAGMA_ORE, new OreFeature(
                List.of(BlockReplacement.replace(new TagMatchTest(ModTags.Blocks.POTENT_MAGMA_ORE_REPLACEABLE),
                        ModBlocks.POTENT_MAGMA_BLOCK.defaultBlockState())), 14));

        context.register(BRIMGRASS, new SimpleBlockFeature(simpleState(ModBlocks.BRIMGRASS)));
        context.register(TENEBRIS_BUD, new SimpleBlockFeature(simpleState(ModBlocks.TENEBRIS_SAPLING)));
        context.register(SALT, new SimpleBlockFeature(simpleState(ModBlocks.SALT_BLOCK)));

        context.register(NETHERSTONE, new OreFeature(
                List.of(BlockReplacement.replace(new BlockMatchTest(Blocks.NETHERRACK), ModBlocks.NETHERSTONE.defaultBlockState())), 32));
        context.register(BRIMSTONE_PATCH, new OreFeature(
                List.of(BlockReplacement.replace(new BlockMatchTest(Blocks.NETHERRACK), ModBlocks.BRIMSTONE.defaultBlockState()),
                        BlockReplacement.replace(new BlockMatchTest(ModBlocks.NETHERSTONE), ModBlocks.BRIMSTONE.defaultBlockState()),
                        BlockReplacement.replace(new BlockMatchTest(Blocks.MAGMA_BLOCK), ModBlocks.IGNITED_BRIMSTONE.defaultBlockState())), 32));
        context.register(IGNITED_BRIMSTONE_PATCH, new SequenceFeature(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(BRIMSTONE_PATCH), CountPlacement.of(1)),
                        PlacementUtils.inlinePlaced(new ScatteredOreFeature(
                                List.of(BlockReplacement.replace(new BlockMatchTest(ModBlocks.BRIMSTONE),
                                        ModBlocks.IGNITED_BRIMSTONE.defaultBlockState())), 40, 0f))
                )
        ));
        context.register(GOLD_BRIMSTONE, new ScatteredOreFeature(
                List.of(BlockReplacement.replace(new BlockMatchTest(ModBlocks.BRIMSTONE), ModBlocks.BRIMSTONE_GOLD_ORE.defaultBlockState()),
                        BlockReplacement.replace(new BlockMatchTest(ModBlocks.IGNITED_BRIMSTONE), ModBlocks.BRIMSTONE_GOLD_ORE.defaultBlockState())),
                35, 0f));
    }

    public static ResourceKey<Feature> registryKey(String name) {
        return ResourceKey.create(Registries.FEATURE, JirniyBiomes.id(name));
    }

    private static WeightedList.Builder<BlockState> flowerBedPatchBuilder(final Block flowerBedBlock) {
        return segmentedBlockPatchBuilder(flowerBedBlock, 1, 4, FlowerBedBlock.AMOUNT, FlowerBedBlock.FACING);
    }
    private static WeightedList.Builder<BlockState> segmentedBlockPatchBuilder(
            final Block block, final int minState, final int maxState, final IntegerProperty amountProperty, final EnumProperty<Direction> directionProperty
    ) {
        WeightedList.Builder<BlockState> segmentedBlockBuild = WeightedList.builder();

        for (int amount = minState; amount <= maxState; amount++) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                segmentedBlockBuild.add(block.defaultBlockState().setValue(amountProperty, amount).setValue(directionProperty, direction), 1);
            }
        }

        return segmentedBlockBuild;
    }
    public static BlockStateProvider simpleState(Block block) {
        return BlockStateProvider.of(block);
    }
    public static BlockStateProvider simpleState(BlockState state) {
        return BlockStateProvider.of(state);
    }
    public static Holder<BlockStateProvider> simpleStateHolder(Block block) {
        return BlockStateProvider.holderOf(block);
    }
    public static Holder<BlockStateProvider> simpleStateHolder(BlockState state) {
        return BlockStateProvider.holderOf(state);
    }
}

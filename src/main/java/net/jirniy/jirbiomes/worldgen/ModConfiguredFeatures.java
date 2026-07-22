package net.jirniy.jirbiomes.worldgen;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.block.custom.AppleLeavesBlock;
import net.jirniy.jirbiomes.block.custom.SmallBarrelCactusBlock;
import net.jirniy.jirbiomes.misc.ModTags;
import net.jirniy.jirbiomes.worldgen.blockstate.MapStateProvider;
import net.jirniy.jirbiomes.worldgen.feature.ModFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.TrapezoidInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
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

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHERSTONE = registryKey("netherstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRIMSTONE_PATCH = registryKey("brimstone_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> IGNITED_BRIMSTONE_PATCH = registryKey("ignited_brimstone_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLD_BRIMSTONE = registryKey("brimstone_gold");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GINKGO_TREE = registryKey("ginkgo_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GINKGO_TREE_BEES_005 = registryKey("ginkgo_tree_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLD_GROWTH_GINKGO_TREE = registryKey("old_growth_ginkgo_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLD_GROWTH_GINKGO_TREE_BEES_005 = registryKey("old_growth_ginkgo_tree_bees_005");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TENEBRIS = registryKey("tenebris");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRIMSTONE_RUINS = registryKey("brimstone_ruins");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLD_BRIMSTONE_RUINS = registryKey("gold_brimstone_ruins");

    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_OAK_TREE = registryKey("apple_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_OAK_TREE_BEES_005 = registryKey("apple_oak_tree_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_APPLE_OAK_TREE = registryKey("fancy_apple_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_APPLE_OAK_TREE_BEES_005 = registryKey("fancy_apple_oak_tree_bees_005");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DRIED_GRASS_PATCH = registryKey("dried_grass_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRIED_GRASS_PATCH_DESERT = registryKey("dried_grass_patch_desert");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WET_GRASS_PATCH = registryKey("wet_grass_patch");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BARREL_CACTUS = registryKey("barrel_cactus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BARREL_CACTUS_PATCH = registryKey("barrel_cactus_patch");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BRIMGRASS = registryKey("brimgrass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TENEBRIS_BUD = registryKey("tenebris_bud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SALT = registryKey("salt");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        BlockStateProvider belowTrunkProvider = TreeConfiguration.defaultPlaceBelowTreeTrunkProvider(context.lookup(Registries.BIOME));

        BlockStateProvider driedDirtProvider = new MapStateProvider(
                new Block[]{Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.ROOTED_DIRT, Blocks.COARSE_DIRT, Blocks.DIRT_PATH, Blocks.FARMLAND},
                new Block[]{ModBlocks.DRIED_GRASS_BLOCK, ModBlocks.DRIED_DIRT, ModBlocks.ROOTED_DRIED_DIRT, ModBlocks.COARSE_DRIED_DIRT, ModBlocks.DRIED_DIRT_PATH, ModBlocks.DRY_FARMLAND});
        BlockStateProvider wetlandProvider = new MapStateProvider(
                new Block[]{Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.ROOTED_DIRT, Blocks.COARSE_DIRT, Blocks.DIRT_PATH, Blocks.FARMLAND},
                new Block[]{ModBlocks.WET_GRASS_BLOCK, ModBlocks.WETLAND, ModBlocks.ROOTED_WETLAND, ModBlocks.COARSE_WETLAND, ModBlocks.WETLAND_PATH, ModBlocks.WET_FARMLAND});

        PlaceOnGroundDecorator sparseLeafLitter = new PlaceOnGroundDecorator(96, 4, 2,
                new WeightedStateProvider(VegetationFeatures.leafLitterPatchBuilder(1, 3)));
        PlaceOnGroundDecorator thickLeafLitter = new PlaceOnGroundDecorator(150, 2, 2,
                new WeightedStateProvider(VegetationFeatures.leafLitterPatchBuilder(1, 4)));

        BeehiveDecorator beehive0002 = new BeehiveDecorator(0.002F);
        BeehiveDecorator beehive001 = new BeehiveDecorator(0.01F);
        BeehiveDecorator beehive002 = new BeehiveDecorator(0.02F);
        BeehiveDecorator beehive005 = new BeehiveDecorator(0.05F);
        BeehiveDecorator beehive = new BeehiveDecorator(1.0F);

        register(context, TENEBRIS, ModFeatures.TENEBRIS, new NoneFeatureConfiguration());

        TreeConfiguration.TreeConfigurationBuilder ginkgoTreeConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.GINKGO_LOG),
                new FancyTrunkPlacer(9, 4, 1),
                BlockStateProvider.simple(ModBlocks.GINKGO_LEAVES),
                new AcaciaFoliagePlacer(UniformInt.of(2, 3), ConstantInt.of(0)),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)),
                belowTrunkProvider
        );
        TreeConfiguration.TreeConfigurationBuilder oldGrowthGinkgoTreeConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.GINKGO_LOG),
                new FancyTrunkPlacer(11, 6, 1),
                BlockStateProvider.simple(ModBlocks.GINKGO_LEAVES),
                new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)),
                belowTrunkProvider
        );

        register(context, APPLE_OAK_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
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
        register(context, APPLE_OAK_TREE_BEES_005, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
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

        register(context, FANCY_APPLE_OAK_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
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
        register(context, FANCY_APPLE_OAK_TREE_BEES_005, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
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

        register(context, GINKGO_TREE, Feature.TREE, ginkgoTreeConfig.ignoreVines().build());
        register(context, GINKGO_TREE_BEES_005, Feature.TREE, ginkgoTreeConfig.decorators(List.of(beehive005)).ignoreVines().build());
        register(context, OLD_GROWTH_GINKGO_TREE, Feature.SEQUENCE, new CompositeFeatureConfiguration(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(Feature.TREE, oldGrowthGinkgoTreeConfig.ignoreVines().build()),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(DRIED_GRASS_PATCH),
                                CountPlacement.of(UniformInt.of(2, 3)),
                                RandomOffsetPlacement.of(ConstantInt.of(4), UniformInt.of(-3, 1))),
                        PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(RuleBasedStateProvider.ifTrueThenProvide(
                                        BlockPredicate.allOf(BlockPredicate.solid(), BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.FEATURES_CANNOT_REPLACE))),
                                        ModBlocks.ROOTED_DRIED_DIRT)),
                                CountPlacement.of(UniformInt.of(16, 60)),
                                RandomOffsetPlacement.of(TrapezoidInt.of(-2, 2, 0), TrapezoidInt.of(-8, -1, 0)))
                )
        ));
        register(context, OLD_GROWTH_GINKGO_TREE_BEES_005, Feature.SEQUENCE, new CompositeFeatureConfiguration(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(Feature.TREE, oldGrowthGinkgoTreeConfig.ignoreVines().decorators(List.of(beehive005)).build()),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(DRIED_GRASS_PATCH),
                                CountPlacement.of(UniformInt.of(2, 3)),
                                RandomOffsetPlacement.of(ConstantInt.of(4), UniformInt.of(-3, 1))),
                        PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(RuleBasedStateProvider.ifTrueThenProvide(
                                        BlockPredicate.allOf(BlockPredicate.solid(), BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.FEATURES_CANNOT_REPLACE))),
                                        ModBlocks.ROOTED_DRIED_DIRT)),
                                CountPlacement.of(UniformInt.of(16, 60)),
                                RandomOffsetPlacement.of(TrapezoidInt.of(-2, 2, 0), TrapezoidInt.of(-8, -1, 0)))
                )
        ));

        register(context, BARREL_CACTUS, Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(
                        BlockColumnConfiguration.layer(UniformInt.of(0, 2), BlockStateProvider.simple(ModBlocks.LARGE_BARREL_CACTUS)),
                        BlockColumnConfiguration.layer(ConstantInt.of(1),
                                new WeightedStateProvider(WeightedList.<BlockState>builder()
                                        .add(ModBlocks.PRICKLY_PEAR_SEED.defaultBlockState(), 2)
                                        .add(ModBlocks.SMALL_BARREL_CACTUS.defaultBlockState().setValue(SmallBarrelCactusBlock.AGE, 1), 5)
                                        .add(ModBlocks.SMALL_BARREL_CACTUS.defaultBlockState().setValue(SmallBarrelCactusBlock.AGE, 2), 5)
                                        .add(Blocks.CACTUS_FLOWER.defaultBlockState(), 1)
                                        .add(Blocks.AIR.defaultBlockState(), 9)
                                ))
                ),
                Direction.UP, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
        ));
        register(context, BARREL_CACTUS_PATCH, Feature.SEQUENCE, new CompositeFeatureConfiguration(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                        RuleBasedStateProvider.builder(BlockStateProvider.simple(ModBlocks.DRIED_DIRT))
                                                .ifTrueThenProvide(BlockPredicate.matchesTag(
                                                BlockTags.SUPPORTS_CACTUS), ModBlocks.ROOTED_DRIED_DIRT).build()),
                                CountPlacement.of(UniformInt.of(5, 12)), PlacementUtils.HEIGHTMAP,
                                RandomOffsetPlacement.of(
                                        TrapezoidInt.of(-2, 2, 0),
                                        TrapezoidInt.of(-3, -1, 0)),
                                BlockPredicateFilter.forPredicate(BlockPredicate.solid())
                        ),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(BARREL_CACTUS),
                                CountPlacement.of(UniformInt.of(5, 6)), PlacementUtils.HEIGHTMAP,
                                RandomOffsetPlacement.of(TrapezoidInt.of(-3, 3, 0), ConstantInt.of(0)),
                                EnvironmentScanPlacement.scanningFor(
                                        Direction.DOWN, BlockPredicate.matchesTag(new Vec3i(0, -1, 0),
                                                ModTags.Blocks.SUPPORTS_BARREL_CACTUS), 4),
                                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
                        )
                )
        ));

        register(context, DRIED_GRASS_PATCH, Feature.DISK, new DiskConfiguration(
                driedDirtProvider,
                BlockPredicate.solid(),
                UniformInt.of(3, 7),
                4
        ));
        register(context, DRIED_GRASS_PATCH_DESERT, Feature.SEQUENCE, new CompositeFeatureConfiguration(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(Feature.DISK, new DiskConfiguration(
                                RuleBasedStateProvider.ifTrueThenProvide(
                                        BlockPredicate.matchesBlocks(Blocks.SAND),
                                        ModBlocks.DRIED_DIRT),
                                BlockPredicate.solid(),
                                UniformInt.of(2, 5),
                                3
                        )),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(VegetationFeatures.DRY_GRASS),
                                RarityFilter.onAverageOnceEvery(3),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP,
                                CountPlacement.of(32),
                                RandomOffsetPlacement.ofTriangle(5, 3),
                                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE))
                )
        ));
        register(context, WET_GRASS_PATCH, Feature.DISK, new DiskConfiguration(
                wetlandProvider,
                BlockPredicate.solid(),
                UniformInt.of(3, 7),
                4
        ));

        register(context, BRIMSTONE_RUINS, Feature.TEMPLATE, new TemplateFeatureConfiguration(
                WeightedList.<TemplateFeatureConfiguration.TemplateEntry>builder()
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/small1"), List.of(Rotation.NONE)), 4)
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/small2"), List.of(Rotation.NONE)), 4)
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/small3"), List.of(Rotation.values())), 4)
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/small4"), List.of(Rotation.values())), 4)
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/small5"), List.of(Rotation.values())), 4)
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/small6"), List.of(Rotation.NONE)), 4)
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/medium1"), List.of(Rotation.values())), 3)
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/medium2"), List.of(Rotation.values())), 2)
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/medium3"), List.of(Rotation.NONE)), 1)
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/medium4"), List.of(Rotation.values())), 3)
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/large1"), List.of(Rotation.NONE)), 1)
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/large2"), List.of(Rotation.NONE)), 1)
                        .add(new TemplateFeatureConfiguration.TemplateEntry(JirniyBiomes.id("brimstone_ruins/large3"), List.of(Rotation.values())), 2)
                        .build()
        ));
        register(context, GOLD_BRIMSTONE_RUINS, Feature.ROOT_SYSTEM, new RootSystemConfiguration(
                PlacementUtils.inlinePlaced(Feature.SEQUENCE, new CompositeFeatureConfiguration(
                                HolderSet.direct(
                                        PlacementUtils.inlinePlaced(Feature.DISK, new DiskConfiguration(
                                                BlockStateProvider.simple(ModBlocks.BRIMSTONE),
                                                BlockPredicate.allOf(
                                                        BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.FEATURES_CANNOT_REPLACE)),
                                                        BlockPredicate.not(BlockPredicate.matchesBlocks(ModBlocks.BRIMSTONE_GOLD_ORE))
                                                ),
                                                UniformInt.of(4, 6), 2),
                                                RandomOffsetPlacement.vertical(ConstantInt.of(-2))),
                                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(BRIMSTONE_RUINS),
                                                CountPlacement.of(1))
                                )),
                        CountPlacement.of(1), RandomOffsetPlacement.vertical(ConstantInt.of(-1))),
                3, 0, 0, 2,
                blocks.getOrThrow(ModTags.Blocks.BRIMSTONE_GOLD_REPLACEABLE),
                new MapStateProvider(
                        new Block[]{ModBlocks.BRIMSTONE, ModBlocks.IGNITED_BRIMSTONE},
                        List.of(new WeightedStateProvider(
                                    WeightedList.<BlockState>builder()
                                            .add(ModBlocks.BRIMSTONE.defaultBlockState(), 2)
                                            .add(ModBlocks.BRIMSTONE_GOLD_ORE.defaultBlockState(), 1)
                                ),
                                new WeightedStateProvider(
                                    WeightedList.<BlockState>builder()
                                            .add(ModBlocks.IGNITED_BRIMSTONE.defaultBlockState(), 1)
                                            .add(ModBlocks.BRIMSTONE_GOLD_ORE.defaultBlockState(), 3)
                                ))),
                7, 17, 3, 2,
                BlockStateProvider.simple(Blocks.AIR), 1, 1,
                BlockPredicate.allOf(BlockPredicate.anyOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.not(BlockPredicate.solid())),
                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), ModBlocks.BRIMGRASS_BLOCK, ModBlocks.BRIMSTONE))
        ));

        register(context, BRIMGRASS, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BRIMGRASS)));
        register(context, TENEBRIS_BUD, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.TENEBRIS_SAPLING)));
        register(context, SALT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SALT_BLOCK)));

        register(context, NETHERSTONE, Feature.ORE, new OreConfiguration(
                List.of(OreConfiguration.target(new BlockMatchTest(Blocks.NETHERRACK), ModBlocks.NETHERSTONE.defaultBlockState())), 32));
        register(context, BRIMSTONE_PATCH, Feature.ORE, new OreConfiguration(
                List.of(OreConfiguration.target(new BlockMatchTest(Blocks.NETHERRACK), ModBlocks.BRIMSTONE.defaultBlockState()),
                        OreConfiguration.target(new BlockMatchTest(ModBlocks.NETHERSTONE), ModBlocks.BRIMSTONE.defaultBlockState()),
                        OreConfiguration.target(new BlockMatchTest(Blocks.MAGMA_BLOCK), ModBlocks.IGNITED_BRIMSTONE.defaultBlockState())), 32));
        register(context, IGNITED_BRIMSTONE_PATCH, Feature.SEQUENCE, new CompositeFeatureConfiguration(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(BRIMSTONE_PATCH), CountPlacement.of(1)),
                        PlacementUtils.inlinePlaced(Feature.SCATTERED_ORE, new OreConfiguration(
                                List.of(OreConfiguration.target(new BlockMatchTest(ModBlocks.BRIMSTONE), ModBlocks.IGNITED_BRIMSTONE.defaultBlockState())), 40))
                )
        ));
        register(context, GOLD_BRIMSTONE, Feature.SCATTERED_ORE, new OreConfiguration(
                List.of(OreConfiguration.target(new BlockMatchTest(ModBlocks.BRIMSTONE), ModBlocks.BRIMSTONE_GOLD_ORE.defaultBlockState()),
                        OreConfiguration.target(new BlockMatchTest(ModBlocks.IGNITED_BRIMSTONE), ModBlocks.BRIMSTONE_GOLD_ORE.defaultBlockState())),
                35));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registryKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, JirniyBiomes.id(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key,
                                                                                          F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}

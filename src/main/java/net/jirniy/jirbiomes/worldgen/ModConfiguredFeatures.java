package net.jirniy.jirbiomes.worldgen;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.worldgen.blockstate.MapStateProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.TrapezoidInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.SequenceFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.PlaceOnGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHERSTONE = registryKey("netherstone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GINKGO_TREE = registryKey("ginkgo_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GINKGO_TREE_BEES_005 = registryKey("ginkgo_tree_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLD_GROWTH_GINKGO_TREE = registryKey("old_growth_ginkgo_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLD_GROWTH_GINKGO_TREE_BEES_005 = registryKey("old_growth_ginkgo_tree_bees_005");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DRIED_GRASS_PATCH = registryKey("dried_grass_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRIED_GRASS_PATCH_DESERT = registryKey("dried_grass_patch_desert");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WET_GRASS_PATCH = registryKey("wet_grass_patch");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
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

        register(context, GINKGO_TREE, Feature.TREE, ginkgoTreeConfig.ignoreVines().build());
        register(context, GINKGO_TREE_BEES_005, Feature.TREE, ginkgoTreeConfig.decorators(List.of(beehive005)).ignoreVines().build());
        register(context, OLD_GROWTH_GINKGO_TREE, Feature.SEQUENCE, new CompositeFeatureConfiguration(
                HolderSet.direct(
                        PlacementUtils.inlinePlaced(Feature.TREE, oldGrowthGinkgoTreeConfig.ignoreVines().build()),
                        PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(DRIED_GRASS_PATCH),
                                CountPlacement.of(UniformInt.of(2, 3)),
                                RandomOffsetPlacement.of(UniformInt.of(-4, 4), UniformInt.of(-3, 1))),
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
                                RandomOffsetPlacement.of(UniformInt.of(-4, 4), UniformInt.of(-3, 1))),
                        PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(RuleBasedStateProvider.ifTrueThenProvide(
                                        BlockPredicate.allOf(BlockPredicate.solid(), BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.FEATURES_CANNOT_REPLACE))),
                                        ModBlocks.ROOTED_DRIED_DIRT)),
                                CountPlacement.of(UniformInt.of(16, 60)),
                                RandomOffsetPlacement.of(TrapezoidInt.of(-2, 2, 0), TrapezoidInt.of(-8, -1, 0)))
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
                                        BlockPredicate.matchesBlocks(Blocks.SAND, Blocks.SANDSTONE),
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

        register(context, NETHERSTONE, Feature.ORE, new OreConfiguration(
                List.of(OreConfiguration.target(new BlockMatchTest(Blocks.NETHERRACK), ModBlocks.NETHERSTONE.defaultBlockState())), 32
        ));
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

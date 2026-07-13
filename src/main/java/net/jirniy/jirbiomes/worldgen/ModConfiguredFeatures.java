package net.jirniy.jirbiomes.worldgen;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.worldgen.blockstate.MapStateProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.PlaceOnGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> GINKGO_TREE = registryKey("ginkgo_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GINKGO_TREE_BEES_005 = registryKey("ginkgo_tree_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLD_GROWTH_GINKGO_TREE = registryKey("old_growth_ginkgo_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLD_GROWTH_GINKGO_TREE_BEES_005 = registryKey("old_growth_ginkgo_tree_bees_005");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DRIED_GRASS_PATCH = registryKey("dried_grass_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WET_GRASS_PATCH = registryKey("wet_grass_patch");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
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
        register(context, OLD_GROWTH_GINKGO_TREE, Feature.TREE, oldGrowthGinkgoTreeConfig.ignoreVines().build());
        register(context, OLD_GROWTH_GINKGO_TREE_BEES_005, Feature.TREE, oldGrowthGinkgoTreeConfig.decorators(List.of(beehive005)).ignoreVines().build());

        register(context, DRIED_GRASS_PATCH, Feature.DISK, new DiskConfiguration(
                driedDirtProvider,
                BlockPredicate.solid(),
                UniformInt.of(3, 7),
                4
        ));
        register(context, WET_GRASS_PATCH, Feature.DISK, new DiskConfiguration(
                wetlandProvider,
                BlockPredicate.solid(),
                UniformInt.of(3, 7),
                4
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

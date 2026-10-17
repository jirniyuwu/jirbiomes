package net.jirniy.jirbiomes.worldgen;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower GINKGO = new TreeGrower(
            "ginkgo",
            WeightedList.of(new Weighted<>(ModConfiguredFeatures.GINKGO_TREE, 1)),
            WeightedList.of(),
            WeightedList.of(new Weighted<>(ModConfiguredFeatures.GINKGO_TREE_BEES_005, 1)),
            ModConfiguredFeatures.GINKGO_TREE
    );

    public static final TreeGrower PALM = new TreeGrower(
            "palm",
            WeightedList.of(new Weighted<>(ModConfiguredFeatures.PALM_TREE, 1)),
            WeightedList.of(),
            WeightedList.of(new Weighted<>(ModConfiguredFeatures.PALM_TREE_BEES_005, 1)),
            ModConfiguredFeatures.PALM_TREE
    );

    public static final TreeGrower APPLE_OAK = new TreeGrower(
            "apple_oak",
            WeightedList.of(new Weighted<>(ModConfiguredFeatures.APPLE_OAK_TREE, 9), new Weighted<>(ModConfiguredFeatures.FANCY_APPLE_OAK_TREE, 1)),
            WeightedList.of(),
            WeightedList.of(new Weighted<>(ModConfiguredFeatures.APPLE_OAK_TREE_BEES_005, 9), new Weighted<>(ModConfiguredFeatures.FANCY_APPLE_OAK_TREE_BEES_005, 1)),
            ModConfiguredFeatures.APPLE_OAK_TREE
    );

    public static final TreeGrower TENEBRIS = new TreeGrower(
            "tenebris",
            WeightedList.of(new Weighted<>(ModConfiguredFeatures.TENEBRIS, 1)),
            WeightedList.of(),
            WeightedList.of(new Weighted<>(ModConfiguredFeatures.TENEBRIS, 1)),
            ModConfiguredFeatures.TENEBRIS
    );
}

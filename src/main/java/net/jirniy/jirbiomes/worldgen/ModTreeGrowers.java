package net.jirniy.jirbiomes.worldgen;

import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;

public class ModTreeGrowers {
    public static final TreeGrower GINKGO = new TreeGrower(
            "ginkgo",
            WeightedList.of(new Weighted<>(ModFeatures.GINKGO_TREE, 1).value()),
            WeightedList.of(),
            WeightedList.of(new Weighted<>(ModFeatures.GINKGO_TREE_BEES_005, 1).value()),
            ModFeatures.GINKGO_TREE
    );

    public static final TreeGrower PALM = new TreeGrower(
            "palm",
            WeightedList.of(new Weighted<>(ModFeatures.PALM_TREE, 1).value()),
            WeightedList.of(),
            WeightedList.of(new Weighted<>(ModFeatures.PALM_TREE_BEES_005, 1).value()),
            ModFeatures.PALM_TREE
    );

    public static final TreeGrower APPLE_OAK = new TreeGrower(
            "apple_oak",
            WeightedList.of(new Weighted<>(ModFeatures.APPLE_OAK_TREE, 9), new Weighted<>(ModFeatures.FANCY_APPLE_OAK_TREE, 1)),
            WeightedList.of(),
            WeightedList.of(new Weighted<>(ModFeatures.APPLE_OAK_TREE_BEES_005, 9), new Weighted<>(ModFeatures.FANCY_APPLE_OAK_TREE_BEES_005, 1)),
            ModFeatures.APPLE_OAK_TREE
    );

    public static final TreeGrower TENEBRIS = new TreeGrower(
            "tenebris",
            WeightedList.of(new Weighted<>(ModFeatures.TENEBRIS, 1).value()),
            WeightedList.of(),
            WeightedList.of(new Weighted<>(ModFeatures.TENEBRIS, 1).value()),
            ModFeatures.TENEBRIS
    );
}

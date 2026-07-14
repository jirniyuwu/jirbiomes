package net.jirniy.jirbiomes.worldgen;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower GINKGO = new TreeGrower(
            "ginkgo",
            0.0F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.GINKGO_TREE),
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.GINKGO_TREE_BEES_005),
            Optional.empty()
    );

    public static final TreeGrower APPLE_OAK = new TreeGrower(
            "apple_oak",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.APPLE_OAK_TREE),
            Optional.of(ModConfiguredFeatures.FANCY_APPLE_OAK_TREE),
            Optional.of(ModConfiguredFeatures.APPLE_OAK_TREE_BEES_005),
            Optional.of(ModConfiguredFeatures.FANCY_APPLE_OAK_TREE_BEES_005)
    );
}

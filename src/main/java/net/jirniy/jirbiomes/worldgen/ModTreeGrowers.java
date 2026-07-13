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
}

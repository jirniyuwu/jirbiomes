package net.jirniy.jirbiomes.block;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModBlockSetTypes {
    public static class Sets {
        public static final BlockSetType GINKGO = new BlockSetType("ginkgo");
    }
    public static class WoodTypes {
        public static final WoodType GINKGO = new WoodType("ginkgo", Sets.GINKGO);
    }
}

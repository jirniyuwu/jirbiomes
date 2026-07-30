package net.jirniy.jirbiomes.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModBlockSetTypes {
    public static class Sets {
        public static final BlockSetType GINKGO = new BlockSetType("ginkgo");
        public static final BlockSetType PALM = new BlockSetType("palm");
        public static final BlockSetType TENEBRIS = new BlockSetType("tenebris",
                true, true, true,
                BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.NETHER_WOOD,
                SoundEvents.NETHER_WOOD_DOOR_CLOSE, SoundEvents.NETHER_WOOD_DOOR_OPEN,
                SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE, SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN,
                SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON,
                SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF, SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON);
    }
    public static class WoodTypes {
        public static final WoodType GINKGO = new WoodType("ginkgo", Sets.GINKGO);
        public static final WoodType PALM = new WoodType("palm", Sets.PALM);
        public static final WoodType TENEBRIS = new WoodType("tenebris", Sets.TENEBRIS, SoundType.NETHER_WOOD,
                SoundType.NETHER_WOOD_HANGING_SIGN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE, SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN);
    }
}

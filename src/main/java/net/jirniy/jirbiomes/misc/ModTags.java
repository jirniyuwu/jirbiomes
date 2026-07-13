package net.jirniy.jirbiomes.misc;

import net.jirniy.jirbiomes.JirniyBiomes;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> DRIED_DIRT = create("dried_dirt");
        public static final TagKey<Block> WET_DIRT = create("wet_dirt");
        public static final TagKey<Block> GRASS_BLOCKS = create("grass_blocks");
        public static final TagKey<Block> GINKGO_LOGS = create("ginkgo_logs");

        private static TagKey<Block> create(final String name) {
            return TagKey.create(Registries.BLOCK, JirniyBiomes.id(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> GINKGO_LOGS = create("ginkgo_logs");

        private static TagKey<Item> create(final String name) {
            return TagKey.create(Registries.ITEM, JirniyBiomes.id(name));
        }
    }
}

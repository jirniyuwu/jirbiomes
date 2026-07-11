package net.jirniy.jirbiomes.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public class Blocks {
        public static final TagKey<Block> DRIED_DIRT = create("dried_dirt");
        public static final TagKey<Block> WET_DIRT = create("wet_dirt");
        public static final TagKey<Block> GRASS_BLOCKS = create("grass_blocks");

        private static TagKey<Block> create(final String name) {
            return TagKey.create(Registries.BLOCK, Identifier.withDefaultNamespace(name));
        }
    }
}

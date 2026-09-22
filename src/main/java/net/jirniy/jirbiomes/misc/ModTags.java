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
        public static final TagKey<Block> ROOTED_BLOCKS = create("rooted_blocks");

        public static final TagKey<Block> GINKGO_LOGS = create("ginkgo_logs");
        public static final TagKey<Block> PALM_LOGS = create("palm_logs");
        public static final TagKey<Block> TENEBRIS_LOGS = create("tenebris_logs");

        public static final TagKey<Block> SUPPORTS_BARREL_CACTUS = create("supports_barrel_cactus");
        public static final TagKey<Block> BARREL_CACTUS_FAST_GROWTH = create("barrel_cactus_fast_growth");
        public static final TagKey<Block> CACTUS_SEED_FLOWER_OVERRIDE = create("cactus_seed_flower_override");
        public static final TagKey<Block> BARREL_CACTUSES = create("barrel_cactuses");

        public static final TagKey<Block> PALM_PLACEABLE = create("palm_placeable");
        public static final TagKey<Block> SUPPORTS_ICE_VEGETATION = create("supports_ice_vegetation");
        public static final TagKey<Block> ALGAE_SURVIVES_UNDER = create("algae_survives_under");

        public static final TagKey<Block> GLOWSTONE_FEATURE_PLACEABLE = create("glowstone_feature_placeable");
        public static final TagKey<Block> SEA_URCHIN_SHIPWRECK_PLACEMENT = create("sea_urchin_shipwreck_placement");
        public static final TagKey<Block> BRIMSTONE_GOLD_REPLACEABLE = create("brimstone_gold_replaceable");

        public static final TagKey<Block> FALLING_STALACTITE_NO_DROP = create("falling_stalactite_without_drop");

        private static TagKey<Block> create(final String name) {
            return TagKey.create(Registries.BLOCK, JirniyBiomes.id(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> GINKGO_LOGS = create("ginkgo_logs");
        public static final TagKey<Item> PALM_LOGS = create("palm_logs");
        public static final TagKey<Item> TENEBRIS_LOGS = create("tenebris_logs");

        public static final TagKey<Item> ROOTED_BLOCKS = create("rooted_blocks");
        public static final TagKey<Item> FROZEN_GRASS_CRAFTABLE = create("frozen_grass_craftable");

        private static TagKey<Item> create(final String name) {
            return TagKey.create(Registries.ITEM, JirniyBiomes.id(name));
        }
    }
}

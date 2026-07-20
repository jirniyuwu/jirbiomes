package net.jirniy.jirbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT)
                .add(ModBlocks.getKeys(ModBlocks.DRY_FARMLAND, ModBlocks.WET_FARMLAND))
                .add(ModBlocks.getKeys(ModBlocks.DRIED_DIRT_PATH, ModBlocks.WETLAND_PATH));
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getKey(ModBlocks.IRON_GRATE))
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE))
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE, ModBlocks.BRIMGRASS_BLOCK, ModBlocks.IGNITED_BRIMSTONE))
                .add(ModBlocks.getKey(ModBlocks.CHISELED_BRIMSTONE_BRICKS))
                .add(ModBlocks.getKeys(ModBlocks.MOSSY_BRIMSTONE_BRICKS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_STAIRS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_SLAB, ModBlocks.MOSSY_BRIMSTONE_BRICKS_WALL))
                .add(ModBlocks.getKeys(ModBlocks.CRACKED_BRIMSTONE_BRICKS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_STAIRS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_SLAB, ModBlocks.CRACKED_BRIMSTONE_BRICKS_WALL))
                .add(ModBlocks.getKeys(ModBlocks.POLISHED_BRIMSTONE, ModBlocks.POLISHED_BRIMSTONE_STAIRS, ModBlocks.POLISHED_BRIMSTONE_SLAB, ModBlocks.POLISHED_BRIMSTONE_WALL))
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE_BRICKS, ModBlocks.BRIMSTONE_BRICKS_STAIRS, ModBlocks.BRIMSTONE_BRICKS_SLAB, ModBlocks.BRIMSTONE_BRICKS_WALL));
        tag(BlockTags.MINEABLE_WITH_AXE)
                .addOptionalTag(ModTags.Blocks.GINKGO_LOGS)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_PLANKS))
                .add(ModBlocks.getKeys(ModBlocks.GINKGO_STAIRS, ModBlocks.GINKGO_SLAB, ModBlocks.GINKGO_PRESSURE_PLATE, ModBlocks.GINKGO_BUTTON,
                        ModBlocks.GINKGO_FENCE, ModBlocks.GINKGO_FENCE_GATE, ModBlocks.GINKGO_TRAPDOOR, ModBlocks.GINKGO_DOOR))
                .addOptionalTag(ModTags.Blocks.TENEBRIS_LOGS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_PLANKS))
                .add(ModBlocks.getKeys(ModBlocks.TENEBRIS_STAIRS, ModBlocks.TENEBRIS_SLAB, ModBlocks.TENEBRIS_PRESSURE_PLATE, ModBlocks.TENEBRIS_BUTTON,
                        ModBlocks.TENEBRIS_FENCE, ModBlocks.TENEBRIS_FENCE_GATE, ModBlocks.TENEBRIS_TRAPDOOR, ModBlocks.TENEBRIS_DOOR));
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.getKeys(ModBlocks.PRICKLY_PEAR_SEED, ModBlocks.SMALL_BARREL_CACTUS, ModBlocks.LARGE_BARREL_CACTUS));

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.getKey(ModBlocks.IRON_GRATE))
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE, ModBlocks.BRIMGRASS_BLOCK, ModBlocks.IGNITED_BRIMSTONE))
                .add(ModBlocks.getKey(ModBlocks.CHISELED_BRIMSTONE_BRICKS))
                .add(ModBlocks.getKeys(ModBlocks.MOSSY_BRIMSTONE_BRICKS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_STAIRS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_SLAB, ModBlocks.MOSSY_BRIMSTONE_BRICKS_WALL))
                .add(ModBlocks.getKeys(ModBlocks.CRACKED_BRIMSTONE_BRICKS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_STAIRS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_SLAB, ModBlocks.CRACKED_BRIMSTONE_BRICKS_WALL))
                .add(ModBlocks.getKeys(ModBlocks.POLISHED_BRIMSTONE, ModBlocks.POLISHED_BRIMSTONE_STAIRS, ModBlocks.POLISHED_BRIMSTONE_SLAB, ModBlocks.POLISHED_BRIMSTONE_WALL))
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE_BRICKS, ModBlocks.BRIMSTONE_BRICKS_STAIRS, ModBlocks.BRIMSTONE_BRICKS_SLAB, ModBlocks.BRIMSTONE_BRICKS_WALL));

        tag(ModTags.Blocks.DRIED_DIRT)
                .add(ModBlocks.getKeys(ModBlocks.DRIED_DIRT, ModBlocks.COARSE_DRIED_DIRT, ModBlocks.ROOTED_DRIED_DIRT, ModBlocks.DRIED_GRASS_BLOCK));
        tag(ModTags.Blocks.WET_DIRT)
                .add(ModBlocks.getKeys(ModBlocks.WETLAND, ModBlocks.COARSE_WETLAND, ModBlocks.ROOTED_WETLAND, ModBlocks.WET_GRASS_BLOCK));
        tag(ModTags.Blocks.GRASS_BLOCKS)
                .add(ModBlocks.getKey(ModBlocks.DRIED_GRASS_BLOCK))
                .add(ModBlocks.getKey(ModBlocks.WET_GRASS_BLOCK))
                .add(ModBlocks.getKey(Blocks.GRASS_BLOCK));

        tag(ModTags.Blocks.GINKGO_LOGS)
                .add(ModBlocks.getKeys(ModBlocks.GINKGO_LOG, ModBlocks.STRIPPED_GINKGO_LOG))
                .add(ModBlocks.getKeys(ModBlocks.GINKGO_WOOD, ModBlocks.STRIPPED_GINKGO_WOOD));
        tag(ModTags.Blocks.TENEBRIS_LOGS)
                .add(ModBlocks.getKeys(ModBlocks.TENEBRIS_LOG, ModBlocks.STRIPPED_TENEBRIS_LOG))
                .add(ModBlocks.getKeys(ModBlocks.TENEBRIS_WOOD, ModBlocks.STRIPPED_TENEBRIS_WOOD));

        tag(BlockTags.LEAVES)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_LEAVES));
        tag(BlockTags.PLANKS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_PLANKS))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_PLANKS));
        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_STAIRS))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_STAIRS));
        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_SLAB))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_SLAB));
        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_BUTTON))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_BUTTON));
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_PRESSURE_PLATE))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_PRESSURE_PLATE));
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_TRAPDOOR))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_TRAPDOOR));
        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_DOOR))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_DOOR));
        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_FENCE))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_FENCE));
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_FENCE_GATE))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_FENCE_GATE));
        tag(BlockTags.WOODEN_SHELVES)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_SHELF))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_SHELF));
        tag(BlockTags.ALL_SIGNS)
                .add(ModBlocks.getKeys(ModBlocks.TENEBRIS_SIGN, ModBlocks.WALL_TENEBRIS_SIGN))
                .add(ModBlocks.getKeys(ModBlocks.GINKGO_SIGN, ModBlocks.WALL_GINKGO_SIGN));
        tag(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_SIGN))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_SIGN));
        tag(BlockTags.WALL_SIGNS)
                .add(ModBlocks.getKey(ModBlocks.WALL_TENEBRIS_SIGN))
                .add(ModBlocks.getKey(ModBlocks.WALL_GINKGO_SIGN));
        tag(BlockTags.ALL_HANGING_SIGNS)
                .add(ModBlocks.getKeys(ModBlocks.HANGING_TENEBRIS_SIGN, ModBlocks.HANGING_WALL_TENEBRIS_SIGN))
                .add(ModBlocks.getKeys(ModBlocks.HANGING_GINKGO_SIGN, ModBlocks.HANGING_WALL_GINKGO_SIGN));
        tag(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.getKey(ModBlocks.HANGING_TENEBRIS_SIGN))
                .add(ModBlocks.getKey(ModBlocks.HANGING_GINKGO_SIGN));
        tag(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.getKey(ModBlocks.HANGING_WALL_TENEBRIS_SIGN))
                .add(ModBlocks.getKey(ModBlocks.HANGING_WALL_GINKGO_SIGN));

        tag(BlockTags.STAIRS)
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE_BRICKS_STAIRS, ModBlocks.POLISHED_BRIMSTONE_STAIRS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_STAIRS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_STAIRS));
        tag(BlockTags.SLABS)
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE_BRICKS_SLAB, ModBlocks.POLISHED_BRIMSTONE_SLAB, ModBlocks.CRACKED_BRIMSTONE_BRICKS_SLAB, ModBlocks.MOSSY_BRIMSTONE_BRICKS_SLAB));
        tag(BlockTags.WALLS)
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE_BRICKS_WALL, ModBlocks.POLISHED_BRIMSTONE_WALL, ModBlocks.CRACKED_BRIMSTONE_BRICKS_WALL, ModBlocks.MOSSY_BRIMSTONE_BRICKS_WALL));

        tag(BlockTags.BEE_ATTRACTIVE)
                .add(ModBlocks.getKey(ModBlocks.APPLE_LEAVES));
        tag(BlockTags.BEE_GROWABLES)
                .add(ModBlocks.getKey(ModBlocks.APPLE_LEAVES));

        tag(BlockTags.CROPS)
                .add(ModBlocks.getKey(ModBlocks.APPLE_CROP));
        tag(BlockTags.GROWS_CROPS)
                .add(ModBlocks.getKeys(ModBlocks.DRY_FARMLAND, ModBlocks.WET_FARMLAND));
        tag(BlockTags.SUPPORTS_CROPS)
                .add(ModBlocks.getKeys(ModBlocks.DRY_FARMLAND, ModBlocks.WET_FARMLAND));
        tag(BlockTags.SUPPORTS_BAMBOO)
                .addOptionalTag(ModTags.Blocks.WET_DIRT);
        tag(BlockTags.SUPPORTS_DRY_VEGETATION)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.ENDERMAN_HOLDABLE)
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.SNIFFER_DIGGABLE_BLOCK)
                .add(ModBlocks.getKey(ModBlocks.BRIMGRASS_BLOCK))
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.LUSH_GROUND_REPLACEABLE)
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.MOSS_REPLACEABLE)
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.SUPPORTS_SUGAR_CANE)
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.SUPPORTS_CACTUS)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.SUPPORTS_VEGETATION)
                .add(ModBlocks.getKeys(ModBlocks.DRY_FARMLAND, ModBlocks.WET_FARMLAND, ModBlocks.BRIMGRASS_BLOCK))
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.getKey(ModBlocks.POTTED_BARREL_CACTUS))
                .add(ModBlocks.getKey(ModBlocks.POTTED_GINKGO_SAPLING))
                .add(ModBlocks.getKey(ModBlocks.POTTED_APPLE_OAK_SAPLING))
                .add(ModBlocks.getKey(ModBlocks.POTTED_TENEBRIS_SAPLING));
        tag(BlockTags.CANNOT_REPLACE_BELOW_TREE_TRUNK)
                .add(ModBlocks.getKey(ModBlocks.BRIMGRASS_BLOCK))
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.ANIMALS_SPAWNABLE_ON)
                .add(ModBlocks.getKey(ModBlocks.BRIMGRASS_BLOCK))
                .addOptionalTag(ModTags.Blocks.GRASS_BLOCKS);
        tag(BlockTags.VALID_SPAWN)
                .add(ModBlocks.getKey(ModBlocks.BRIMGRASS_BLOCK))
                .addOptionalTag(ModTags.Blocks.GRASS_BLOCKS);
        tag(BlockTags.GRASS_BLOCKS)
                .addOptionalTag(ModTags.Blocks.GRASS_BLOCKS);
        tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .addOptionalTag(ModTags.Blocks.GINKGO_LOGS);
        tag(BlockTags.LOGS)
                .addOptionalTag(ModTags.Blocks.TENEBRIS_LOGS)
                .addOptionalTag(ModTags.Blocks.GINKGO_LOGS);
        tag(BlockTags.SUPPORT_OVERRIDE_CACTUS_FLOWER)
                .add(ModBlocks.getKey(ModBlocks.LARGE_BARREL_CACTUS));
        tag(BlockTags.FLOWERS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_SAPLING));

        tag(ModTags.Blocks.SUPPORTS_BARREL_CACTUS)
                .addOptionalTag(BlockTags.SUPPORTS_CACTUS)
                .add(ModBlocks.getKey(ModBlocks.LARGE_BARREL_CACTUS))
                .add(ModBlocks.getKey(ModBlocks.DRY_FARMLAND));
        tag(ModTags.Blocks.CACTUS_SEED_FLOWER_OVERRIDE)
                .add(ModBlocks.getKey(Blocks.CACTUS));
        tag(ModTags.Blocks.BARREL_CACTUS_FAST_GROWTH)
                .add(ModBlocks.getKey(ModBlocks.DRY_FARMLAND))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_DRIED_DIRT));

        tag(ModTags.Blocks.GLOWSTONE_FEATURE_PLACEABLE)
                .add(BlockItemIds.BASALT.block())
                .add(BlockItemIds.BASALT.block())
                .add(BlockItemIds.BASALT.block())
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE))
                .add(ModBlocks.getKey(ModBlocks.BRIMSTONE));

        tag(BlockTags.NETHER_CARVER_REPLACEABLES)
                .add(ModBlocks.getKey(ModBlocks.BRIMGRASS_BLOCK))
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE))
                .add(ModBlocks.getKey(ModBlocks.IGNITED_BRIMSTONE))
                .add(ModBlocks.getKey(ModBlocks.BRIMSTONE));
        tag(BlockTags.BASE_STONE_NETHER)
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE))
                .add(ModBlocks.getKey(ModBlocks.IGNITED_BRIMSTONE))
                .add(ModBlocks.getKey(ModBlocks.BRIMSTONE));
        tag(BlockTags.INFINIBURN_NETHER)
                .add(ModBlocks.getKey(ModBlocks.BRIMGRASS_BLOCK))
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE))
                .add(ModBlocks.getKey(ModBlocks.IGNITED_BRIMSTONE))
                .add(ModBlocks.getKey(ModBlocks.BRIMSTONE));
        tag(BlockTags.INFINIBURN_OVERWORLD)
                .add(ModBlocks.getKey(ModBlocks.BRIMGRASS_BLOCK))
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE))
                .add(ModBlocks.getKey(ModBlocks.IGNITED_BRIMSTONE))
                .add(ModBlocks.getKey(ModBlocks.BRIMSTONE));
        tag(BlockTags.INFINIBURN_END)
                .add(ModBlocks.getKey(ModBlocks.BRIMGRASS_BLOCK))
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE))
                .add(ModBlocks.getKey(ModBlocks.IGNITED_BRIMSTONE))
                .add(ModBlocks.getKey(ModBlocks.BRIMSTONE));
    }
}

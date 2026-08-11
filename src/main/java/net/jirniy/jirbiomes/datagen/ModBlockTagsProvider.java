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
                .add(ModBlocks.getKeys(ModBlocks.DRIED_DIRT_PATH, ModBlocks.WETLAND_PATH))
                .add(ModBlocks.getKeys(ModBlocks.ROOTED_MUD, ModBlocks.ROOTED_SAND, ModBlocks.ROOTED_RED_SAND))
                .add(ModBlocks.getKey(ModBlocks.PACKED_SNOW));
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getKey(ModBlocks.BRIMSTONE_GOLD_ORE))
                .add(ModBlocks.getKey(ModBlocks.IRON_GRATE))
                .add(ModBlocks.getKeys(ModBlocks.NETHERSTONE, ModBlocks.NETHERSTONE_STAIRS, ModBlocks.NETHERSTONE_SLAB, ModBlocks.NETHERSTONE_WALL))
                .add(ModBlocks.getKeys(ModBlocks.POLISHED_NETHERSTONE, ModBlocks.POLISHED_NETHERSTONE_STAIRS, ModBlocks.POLISHED_NETHERSTONE_SLAB, ModBlocks.POLISHED_NETHERSTONE_WALL))
                .add(ModBlocks.getKeys(ModBlocks.NETHERSTONE_BRICKS, ModBlocks.NETHERSTONE_BRICKS_STAIRS, ModBlocks.NETHERSTONE_BRICKS_SLAB, ModBlocks.NETHERSTONE_BRICKS_WALL))
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE, ModBlocks.BRIMGRASS_BLOCK, ModBlocks.IGNITED_BRIMSTONE))
                .add(ModBlocks.getKey(ModBlocks.CHISELED_BRIMSTONE_BRICKS))
                .add(ModBlocks.getKeys(ModBlocks.MOSSY_BRIMSTONE_BRICKS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_STAIRS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_SLAB, ModBlocks.MOSSY_BRIMSTONE_BRICKS_WALL))
                .add(ModBlocks.getKeys(ModBlocks.CRACKED_BRIMSTONE_BRICKS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_STAIRS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_SLAB, ModBlocks.CRACKED_BRIMSTONE_BRICKS_WALL))
                .add(ModBlocks.getKeys(ModBlocks.POLISHED_BRIMSTONE, ModBlocks.POLISHED_BRIMSTONE_STAIRS, ModBlocks.POLISHED_BRIMSTONE_SLAB, ModBlocks.POLISHED_BRIMSTONE_WALL))
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE_BRICKS, ModBlocks.BRIMSTONE_BRICKS_STAIRS, ModBlocks.BRIMSTONE_BRICKS_SLAB, ModBlocks.BRIMSTONE_BRICKS_WALL))
                .add(ModBlocks.getKey(ModBlocks.PERMAFROST_BLOCK))
                .add(ModBlocks.getKeys(ModBlocks.FROSTED_STONE, ModBlocks.FROSTED_STONE_STAIRS, ModBlocks.FROSTED_STONE_SLAB, ModBlocks.FROSTED_STONE_WALL))
                .add(ModBlocks.getKeys(ModBlocks.POLISHED_FROST, ModBlocks.POLISHED_FROST_STAIRS, ModBlocks.POLISHED_FROST_SLAB, ModBlocks.POLISHED_FROST_WALL))
                .add(ModBlocks.getKeys(ModBlocks.FROST_BRICKS, ModBlocks.FROST_BRICKS_STAIRS, ModBlocks.FROST_BRICKS_SLAB, ModBlocks.FROST_BRICKS_WALL))
                .add(ModBlocks.getKey(ModBlocks.PACKED_SNOW))
                .add(ModBlocks.getKeys(ModBlocks.POLISHED_SNOW, ModBlocks.POLISHED_SNOW_STAIRS, ModBlocks.POLISHED_SNOW_SLAB, ModBlocks.POLISHED_SNOW_WALL))
                .add(ModBlocks.getKeys(ModBlocks.SNOW_BRICKS, ModBlocks.SNOW_BRICKS_STAIRS, ModBlocks.SNOW_BRICKS_SLAB, ModBlocks.SNOW_BRICKS_WALL))
                .add(ModBlocks.getKey(ModBlocks.SALT_LAMP))
                .add(ModBlocks.getKeys(ModBlocks.SALT_BLOCK, ModBlocks.SALT_BLOCK_STAIRS, ModBlocks.SALT_BLOCK_SLAB, ModBlocks.SALT_BLOCK_WALL))
                .add(ModBlocks.getKeys(ModBlocks.POLISHED_SALT, ModBlocks.POLISHED_SALT_STAIRS, ModBlocks.POLISHED_SALT_SLAB, ModBlocks.POLISHED_SALT_WALL))
                .add(ModBlocks.getKeys(ModBlocks.SALT_BRICKS, ModBlocks.SALT_BRICKS_STAIRS, ModBlocks.SALT_BRICKS_SLAB, ModBlocks.SALT_BRICKS_WALL));
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.getKey(ModBlocks.STRAW_BLOCK))
                .add(ModBlocks.getKeys(ModBlocks.PACKED_STRAW, ModBlocks.PACKED_STRAW_SLAB, ModBlocks.PACKED_STRAW_STAIRS))
                .add(ModBlocks.getKey(ModBlocks.COCONUT_PLANT))
                .addOptionalTag(ModTags.Blocks.GINKGO_LOGS)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_PLANKS))
                .add(ModBlocks.getKeys(ModBlocks.GINKGO_STAIRS, ModBlocks.GINKGO_SLAB, ModBlocks.GINKGO_PRESSURE_PLATE, ModBlocks.GINKGO_BUTTON,
                        ModBlocks.GINKGO_FENCE, ModBlocks.GINKGO_FENCE_GATE, ModBlocks.GINKGO_TRAPDOOR, ModBlocks.GINKGO_DOOR))
                .addOptionalTag(ModTags.Blocks.PALM_LOGS)
                .add(ModBlocks.getKey(ModBlocks.PALM_PLANKS))
                .add(ModBlocks.getKeys(ModBlocks.PALM_STAIRS, ModBlocks.PALM_SLAB, ModBlocks.PALM_PRESSURE_PLATE, ModBlocks.PALM_BUTTON,
                        ModBlocks.PALM_FENCE, ModBlocks.PALM_FENCE_GATE, ModBlocks.PALM_TRAPDOOR, ModBlocks.PALM_DOOR))
                .addOptionalTag(ModTags.Blocks.TENEBRIS_LOGS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_PLANKS))
                .add(ModBlocks.getKeys(ModBlocks.TENEBRIS_STAIRS, ModBlocks.TENEBRIS_SLAB, ModBlocks.TENEBRIS_PRESSURE_PLATE, ModBlocks.TENEBRIS_BUTTON,
                        ModBlocks.TENEBRIS_FENCE, ModBlocks.TENEBRIS_FENCE_GATE, ModBlocks.TENEBRIS_TRAPDOOR, ModBlocks.TENEBRIS_DOOR));
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.getKey(ModBlocks.SEA_URCHIN))
                .add(ModBlocks.getKey(ModBlocks.STRAW_BLOCK))
                .add(ModBlocks.getKeys(ModBlocks.PACKED_STRAW, ModBlocks.PACKED_STRAW_SLAB, ModBlocks.PACKED_STRAW_STAIRS))
                .add(ModBlocks.getKeys(ModBlocks.PRICKLY_PEAR_SEED, ModBlocks.SMALL_BARREL_CACTUS, ModBlocks.LARGE_BARREL_CACTUS));
        tag(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.getKey(ModBlocks.SEA_URCHIN));
        tag(BlockTags.SHEARS_MAJOR_BREAKING_SPEED)
                .add(ModBlocks.getKey(ModBlocks.SEA_URCHIN));

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.getKey(ModBlocks.BRIMSTONE_GOLD_ORE))
                .add(ModBlocks.getKey(ModBlocks.IRON_GRATE))
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE, ModBlocks.BRIMGRASS_BLOCK, ModBlocks.IGNITED_BRIMSTONE))
                .add(ModBlocks.getKey(ModBlocks.CHISELED_BRIMSTONE_BRICKS))
                .add(ModBlocks.getKeys(ModBlocks.MOSSY_BRIMSTONE_BRICKS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_STAIRS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_SLAB, ModBlocks.MOSSY_BRIMSTONE_BRICKS_WALL))
                .add(ModBlocks.getKeys(ModBlocks.CRACKED_BRIMSTONE_BRICKS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_STAIRS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_SLAB, ModBlocks.CRACKED_BRIMSTONE_BRICKS_WALL))
                .add(ModBlocks.getKeys(ModBlocks.POLISHED_BRIMSTONE, ModBlocks.POLISHED_BRIMSTONE_STAIRS, ModBlocks.POLISHED_BRIMSTONE_SLAB, ModBlocks.POLISHED_BRIMSTONE_WALL))
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE_BRICKS, ModBlocks.BRIMSTONE_BRICKS_STAIRS, ModBlocks.BRIMSTONE_BRICKS_SLAB, ModBlocks.BRIMSTONE_BRICKS_WALL));
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.getKey(ModBlocks.PERMAFROST_BLOCK));

        tag(ModTags.Blocks.DRIED_DIRT)
                .add(ModBlocks.getKeys(ModBlocks.DRIED_DIRT, ModBlocks.COARSE_DRIED_DIRT, ModBlocks.ROOTED_DRIED_DIRT, ModBlocks.DRIED_GRASS_BLOCK));
        tag(ModTags.Blocks.WET_DIRT)
                .add(ModBlocks.getKeys(ModBlocks.WETLAND, ModBlocks.COARSE_WETLAND, ModBlocks.ROOTED_WETLAND, ModBlocks.WET_GRASS_BLOCK));
        tag(ModTags.Blocks.GRASS_BLOCKS)
                .add(ModBlocks.getKey(ModBlocks.DRIED_GRASS_BLOCK))
                .add(ModBlocks.getKey(ModBlocks.WET_GRASS_BLOCK))
                .add(ModBlocks.getKey(Blocks.GRASS_BLOCK));
        tag(ModTags.Blocks.ROOTED_BLOCKS)
                .add(ModBlocks.getKey(ModBlocks.ROOTED_MUD))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_WETLAND))
                .add(ModBlocks.getKey(Blocks.ROOTED_DIRT))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_DRIED_DIRT))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_SAND))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_RED_SAND));

        tag(ModTags.Blocks.GINKGO_LOGS)
                .add(ModBlocks.getKeys(ModBlocks.GINKGO_LOG, ModBlocks.STRIPPED_GINKGO_LOG))
                .add(ModBlocks.getKeys(ModBlocks.GINKGO_WOOD, ModBlocks.STRIPPED_GINKGO_WOOD));
        tag(ModTags.Blocks.PALM_LOGS)
                .add(ModBlocks.getKeys(ModBlocks.PALM_LOG, ModBlocks.STRIPPED_PALM_LOG))
                .add(ModBlocks.getKeys(ModBlocks.PALM_WOOD, ModBlocks.STRIPPED_PALM_WOOD));
        tag(ModTags.Blocks.TENEBRIS_LOGS)
                .add(ModBlocks.getKeys(ModBlocks.TENEBRIS_LOG, ModBlocks.STRIPPED_TENEBRIS_LOG))
                .add(ModBlocks.getKeys(ModBlocks.TENEBRIS_WOOD, ModBlocks.STRIPPED_TENEBRIS_WOOD));

        tag(BlockTags.LEAVES)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_LEAVES))
                .add(ModBlocks.getKey(ModBlocks.PALM_LEAVES));
        tag(BlockTags.PLANKS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_PLANKS))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_PLANKS))
                .add(ModBlocks.getKey(ModBlocks.PALM_PLANKS));
        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_STAIRS))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_STAIRS))
                .add(ModBlocks.getKey(ModBlocks.PALM_STAIRS));
        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_SLAB))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_SLAB))
                .add(ModBlocks.getKey(ModBlocks.PALM_SLAB));
        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_BUTTON))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_BUTTON))
                .add(ModBlocks.getKey(ModBlocks.PALM_BUTTON));
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_PRESSURE_PLATE))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_PRESSURE_PLATE))
                .add(ModBlocks.getKey(ModBlocks.PALM_PRESSURE_PLATE));
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_TRAPDOOR))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_TRAPDOOR))
                .add(ModBlocks.getKey(ModBlocks.PALM_TRAPDOOR));
        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_DOOR))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_DOOR))
                .add(ModBlocks.getKey(ModBlocks.PALM_DOOR));
        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_FENCE))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_FENCE))
                .add(ModBlocks.getKey(ModBlocks.PALM_FENCE));
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_FENCE_GATE))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_FENCE_GATE))
                .add(ModBlocks.getKey(ModBlocks.PALM_FENCE_GATE));
        tag(BlockTags.WOODEN_SHELVES)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_SHELF))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_SHELF))
                .add(ModBlocks.getKey(ModBlocks.PALM_SHELF));
        tag(BlockTags.ALL_SIGNS)
                .add(ModBlocks.getKeys(ModBlocks.TENEBRIS_SIGN, ModBlocks.WALL_TENEBRIS_SIGN))
                .add(ModBlocks.getKeys(ModBlocks.GINKGO_SIGN, ModBlocks.WALL_GINKGO_SIGN))
                .add(ModBlocks.getKeys(ModBlocks.PALM_SIGN, ModBlocks.WALL_PALM_SIGN));
        tag(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_SIGN))
                .add(ModBlocks.getKey(ModBlocks.GINKGO_SIGN))
                .add(ModBlocks.getKey(ModBlocks.PALM_SIGN));
        tag(BlockTags.WALL_SIGNS)
                .add(ModBlocks.getKey(ModBlocks.WALL_TENEBRIS_SIGN))
                .add(ModBlocks.getKey(ModBlocks.WALL_GINKGO_SIGN))
                .add(ModBlocks.getKey(ModBlocks.WALL_PALM_SIGN));
        tag(BlockTags.ALL_HANGING_SIGNS)
                .add(ModBlocks.getKeys(ModBlocks.HANGING_TENEBRIS_SIGN, ModBlocks.HANGING_WALL_TENEBRIS_SIGN))
                .add(ModBlocks.getKeys(ModBlocks.HANGING_GINKGO_SIGN, ModBlocks.HANGING_WALL_GINKGO_SIGN))
                .add(ModBlocks.getKeys(ModBlocks.HANGING_PALM_SIGN, ModBlocks.HANGING_WALL_PALM_SIGN));
        tag(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.getKey(ModBlocks.HANGING_TENEBRIS_SIGN))
                .add(ModBlocks.getKey(ModBlocks.HANGING_GINKGO_SIGN))
                .add(ModBlocks.getKey(ModBlocks.HANGING_PALM_SIGN));
        tag(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.getKey(ModBlocks.HANGING_WALL_TENEBRIS_SIGN))
                .add(ModBlocks.getKey(ModBlocks.HANGING_WALL_GINKGO_SIGN))
                .add(ModBlocks.getKey(ModBlocks.HANGING_WALL_PALM_SIGN));

        tag(BlockTags.STAIRS)
                .add(ModBlocks.getKey(ModBlocks.PACKED_STRAW_STAIRS))
                .add(ModBlocks.getKeys(ModBlocks.SNOW_BRICKS_STAIRS, ModBlocks.POLISHED_SNOW_STAIRS))
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE_BRICKS_STAIRS, ModBlocks.POLISHED_BRIMSTONE_STAIRS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_STAIRS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_STAIRS)).add(ModBlocks.getKey(ModBlocks.SALT_BLOCK))
                .add(ModBlocks.getKeys(ModBlocks.FROSTED_STONE_STAIRS, ModBlocks.POLISHED_FROST_STAIRS, ModBlocks.FROST_BRICKS))
                .add(ModBlocks.getKeys(ModBlocks.NETHERSTONE_STAIRS, ModBlocks.POLISHED_NETHERSTONE_STAIRS, ModBlocks.NETHERSTONE_BRICKS_STAIRS))
                .add(ModBlocks.getKeys(ModBlocks.SALT_BLOCK_STAIRS, ModBlocks.POLISHED_SALT_STAIRS, ModBlocks.SALT_BRICKS_STAIRS));
        tag(BlockTags.SLABS)
                .add(ModBlocks.getKey(ModBlocks.PACKED_STRAW_SLAB))
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE_BRICKS_SLAB, ModBlocks.POLISHED_BRIMSTONE_SLAB, ModBlocks.CRACKED_BRIMSTONE_BRICKS_SLAB, ModBlocks.MOSSY_BRIMSTONE_BRICKS_SLAB))
                .add(ModBlocks.getKeys(ModBlocks.SNOW_BRICKS_SLAB, ModBlocks.POLISHED_SNOW_SLAB))
                .add(ModBlocks.getKeys(ModBlocks.FROSTED_STONE_SLAB, ModBlocks.POLISHED_FROST_SLAB, ModBlocks.FROST_BRICKS_SLAB))
                .add(ModBlocks.getKeys(ModBlocks.NETHERSTONE_SLAB, ModBlocks.POLISHED_NETHERSTONE_SLAB, ModBlocks.NETHERSTONE_BRICKS_SLAB))
                .add(ModBlocks.getKeys(ModBlocks.SALT_BLOCK_SLAB, ModBlocks.POLISHED_SALT_SLAB, ModBlocks.SALT_BRICKS_SLAB));
        tag(BlockTags.WALLS)
                .add(ModBlocks.getKeys(ModBlocks.BRIMSTONE_BRICKS_WALL, ModBlocks.POLISHED_BRIMSTONE_WALL, ModBlocks.CRACKED_BRIMSTONE_BRICKS_WALL, ModBlocks.MOSSY_BRIMSTONE_BRICKS_WALL))
                .add(ModBlocks.getKeys(ModBlocks.SNOW_BRICKS_WALL, ModBlocks.POLISHED_SNOW_WALL))
                .add(ModBlocks.getKeys(ModBlocks.FROSTED_STONE_WALL, ModBlocks.FROST_BRICKS_WALL, ModBlocks.POLISHED_FROST_WALL))
                .add(ModBlocks.getKeys(ModBlocks.NETHERSTONE_WALL, ModBlocks.POLISHED_NETHERSTONE_WALL, ModBlocks.NETHERSTONE_BRICKS_WALL))
                .add(ModBlocks.getKeys(ModBlocks.SALT_BLOCK_WALL, ModBlocks.POLISHED_SALT_WALL, ModBlocks.SALT_BRICKS_WALL));

        tag(BlockTags.BEE_ATTRACTIVE)
                .add(ModBlocks.getKey(ModBlocks.APPLE_LEAVES));
        tag(BlockTags.BEE_GROWABLES)
                .add(ModBlocks.getKey(ModBlocks.APPLE_LEAVES));

        tag(ModTags.Blocks.SUPPORTS_ICE_VEGETATION)
                .add(ModBlocks.getKey(ModBlocks.PERMAFROST_BLOCK))
                .add(ModBlocks.getKey(ModBlocks.PACKED_SNOW))
                .add(ModBlocks.getKey(Blocks.SNOW_BLOCK))
                .add(ModBlocks.getKey(Blocks.PACKED_ICE))
                .add(ModBlocks.getKey(Blocks.BLUE_ICE));

        tag(BlockTags.SAND)
                .add(ModBlocks.getKey(ModBlocks.ROOTED_SAND))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_RED_SAND));
        tag(BlockTags.CONVERTABLE_TO_MUD)
                .add(ModBlocks.getKeys(ModBlocks.WETLAND, ModBlocks.COARSE_WETLAND, ModBlocks.ROOTED_WETLAND));
        tag(BlockTags.SUPPORTS_BIG_DRIPLEAF)
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .add(ModBlocks.getKey(ModBlocks.ROOTED_MUD));
        tag(BlockTags.SUPPORTS_SMALL_DRIPLEAF)
                .addOptionalTag(ModTags.Blocks.WET_DIRT);
        tag(BlockTags.CROPS)
                .add(ModBlocks.getKey(ModBlocks.APPLE_CROP));
        tag(BlockTags.GROWS_CROPS)
                .add(ModBlocks.getKeys(ModBlocks.DRY_FARMLAND, ModBlocks.WET_FARMLAND));
        tag(BlockTags.SUPPORTS_CROPS)
                .add(ModBlocks.getKeys(ModBlocks.DRY_FARMLAND, ModBlocks.WET_FARMLAND));
        tag(BlockTags.SUPPORTS_BAMBOO)
                .add(ModBlocks.getKey(ModBlocks.ROOTED_MUD))
                .addOptionalTag(ModTags.Blocks.WET_DIRT);
        tag(BlockTags.SUPPORTS_DRY_VEGETATION)
                .add(ModBlocks.getKey(ModBlocks.ROOTED_SAND))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_RED_SAND))
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
                .add(ModBlocks.getKey(ModBlocks.ROOTED_MUD))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_SAND))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_RED_SAND))
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.SUPPORTS_CACTUS)
                .add(ModBlocks.getKey(ModBlocks.ROOTED_SAND))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_RED_SAND))
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.SUPPORTS_VEGETATION)
                .add(ModBlocks.getKey(ModBlocks.ROOTED_MUD))
                .add(ModBlocks.getKeys(ModBlocks.DRY_FARMLAND, ModBlocks.WET_FARMLAND, ModBlocks.BRIMGRASS_BLOCK))
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.getKey(ModBlocks.POTTED_FROZEN_GRASS))
                .add(ModBlocks.getKey(ModBlocks.POTTED_BARREL_CACTUS))
                .add(ModBlocks.getKey(ModBlocks.POTTED_GINKGO_SAPLING))
                .add(ModBlocks.getKey(ModBlocks.POTTED_PALM_SAPLING))
                .add(ModBlocks.getKey(ModBlocks.POTTED_APPLE_OAK_SAPLING))
                .add(ModBlocks.getKey(ModBlocks.POTTED_TENEBRIS_SAPLING));
        tag(BlockTags.CANNOT_REPLACE_BELOW_TREE_TRUNK)
                .add(ModBlocks.getKey(ModBlocks.ROOTED_MUD))
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
                .addOptionalTag(ModTags.Blocks.GINKGO_LOGS)
                .addOptionalTag(ModTags.Blocks.PALM_LOGS);
        tag(BlockTags.LOGS)
                .addOptionalTag(ModTags.Blocks.TENEBRIS_LOGS)
                .addOptionalTag(ModTags.Blocks.GINKGO_LOGS)
                .addOptionalTag(ModTags.Blocks.PALM_LOGS);
        tag(BlockTags.SUPPORT_OVERRIDE_CACTUS_FLOWER)
                .add(ModBlocks.getKey(ModBlocks.LARGE_BARREL_CACTUS));
        tag(BlockTags.FLOWERS)
                .add(ModBlocks.getKey(ModBlocks.TENEBRIS_SAPLING));
        tag(BlockTags.SPELEOTHEMS)
                .add(ModBlocks.getKey(ModBlocks.ICICLE));

        tag(ModTags.Blocks.BARREL_CACTUSES)
                .add(ModBlocks.getKey(ModBlocks.LARGE_BARREL_CACTUS))
                .add(ModBlocks.getKey(ModBlocks.SMALL_BARREL_CACTUS))
                .add(ModBlocks.getKey(ModBlocks.PRICKLY_PEAR_SEED));
        tag(ModTags.Blocks.SUPPORTS_BARREL_CACTUS)
                .addOptionalTag(BlockTags.SUPPORTS_CACTUS)
                .add(ModBlocks.getKey(ModBlocks.LARGE_BARREL_CACTUS))
                .add(ModBlocks.getKey(ModBlocks.DRY_FARMLAND));
        tag(ModTags.Blocks.CACTUS_SEED_FLOWER_OVERRIDE)
                .add(ModBlocks.getKey(Blocks.CACTUS));
        tag(ModTags.Blocks.BARREL_CACTUS_FAST_GROWTH)
                .add(ModBlocks.getKey(ModBlocks.DRY_FARMLAND))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_DRIED_DIRT))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_SAND))
                .add(ModBlocks.getKey(ModBlocks.ROOTED_RED_SAND));

        tag(ModTags.Blocks.GLOWSTONE_FEATURE_PLACEABLE)
                .add(BlockItemIds.NETHERRACK.block())
                .add(BlockItemIds.BLACKSTONE.block())
                .add(BlockItemIds.BASALT.block())
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE))
                .add(ModBlocks.getKey(ModBlocks.BRIMSTONE));
        tag(ModTags.Blocks.BRIMSTONE_GOLD_REPLACEABLE)
                .add(ModBlocks.getKey(ModBlocks.BRIMSTONE))
                .add(ModBlocks.getKey(ModBlocks.BRIMGRASS_BLOCK))
                .add(ModBlocks.getKey(ModBlocks.IGNITED_BRIMSTONE));
        tag(ModTags.Blocks.PALM_PLACEABLE)
                .addOptionalTag(BlockTags.SUPPORTS_VEGETATION)
                .addOptionalTag(BlockTags.SUPPORTS_DRY_VEGETATION)
                .addOptionalTag(BlockTags.SAND);
        tag(ModTags.Blocks.SEA_URCHIN_SHIPWRECK_PLACEMENT)
                .addOptionalTag(BlockTags.PLANKS)
                .addOptionalTag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .addOptionalTag(BlockTags.WOODEN_STAIRS)
                .addOptionalTag(BlockTags.WOODEN_SLABS)
                .addOptionalTag(BlockTags.WOODEN_TRAPDOORS);

        tag(BlockTags.SUPPORTS_COCOA)
                .addOptionalTag(ModTags.Blocks.PALM_LOGS);
        tag(ModTags.Blocks.FALLING_STALACTITE_NO_DROP)
                .add(ModBlocks.getKey(ModBlocks.ICICLE));

        tag(BlockTags.GOLD_ORES)
                .add(ModBlocks.getKey(ModBlocks.BRIMSTONE_GOLD_ORE));
        tag(BlockTags.GUARDED_BY_PIGLINS)
                .add(ModBlocks.getKey(ModBlocks.BRIMSTONE_GOLD_ORE));

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
        tag(BlockTags.BASE_STONE_OVERWORLD)
                .add(ModBlocks.getKey(ModBlocks.FROSTED_STONE));
        tag(BlockTags.STONE_ORE_REPLACEABLES)
                .add(ModBlocks.getKey(ModBlocks.FROSTED_STONE));
        tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
                .add(ModBlocks.getKey(ModBlocks.FROSTED_STONE))
                .add(ModBlocks.getKey(ModBlocks.PACKED_SNOW));
        tag(BlockTags.DRIPSTONE_REPLACEABLE)
                .add(ModBlocks.getKey(ModBlocks.FROSTED_STONE))
                .add(ModBlocks.getKey(ModBlocks.PACKED_SNOW))
                .add(ModBlocks.getKey(Blocks.PACKED_ICE));
    }
}

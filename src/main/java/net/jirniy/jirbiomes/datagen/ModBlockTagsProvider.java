package net.jirniy.jirbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.HolderLookup;
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
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE));
        tag(BlockTags.MINEABLE_WITH_AXE)
                .addOptionalTag(ModTags.Blocks.GINKGO_LOGS)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_PLANKS))
                .add(ModBlocks.getKeys(ModBlocks.GINKGO_STAIRS, ModBlocks.GINKGO_SLAB, ModBlocks.GINKGO_PRESSURE_PLATE, ModBlocks.GINKGO_BUTTON,
                        ModBlocks.GINKGO_FENCE, ModBlocks.GINKGO_FENCE_GATE, ModBlocks.GINKGO_TRAPDOOR, ModBlocks.GINKGO_DOOR));

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.getKey(ModBlocks.IRON_GRATE));

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

        tag(BlockTags.LEAVES)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_LEAVES));
        tag(BlockTags.PLANKS)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_PLANKS));
        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_STAIRS));
        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_SLAB));
        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_BUTTON));
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_PRESSURE_PLATE));
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_TRAPDOOR));
        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_DOOR));
        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_FENCE));
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.getKey(ModBlocks.GINKGO_FENCE_GATE));

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
                .add(ModBlocks.getKeys(ModBlocks.DRY_FARMLAND, ModBlocks.WET_FARMLAND))
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.getKey(ModBlocks.POTTED_GINKGO_SAPLING));
        tag(BlockTags.CANNOT_REPLACE_BELOW_TREE_TRUNK)
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.ANIMALS_SPAWNABLE_ON)
                .addOptionalTag(ModTags.Blocks.GRASS_BLOCKS);
        tag(BlockTags.VALID_SPAWN)
                .addOptionalTag(ModTags.Blocks.GRASS_BLOCKS);
        tag(BlockTags.GRASS_BLOCKS)
                .addOptionalTag(ModTags.Blocks.GRASS_BLOCKS);
        tag(BlockTags.LOGS)
                .addOptionalTag(ModTags.Blocks.GINKGO_LOGS);

        tag(BlockTags.NETHER_CARVER_REPLACEABLES)
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE));
        tag(BlockTags.BASE_STONE_NETHER)
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE));
        tag(BlockTags.INFINIBURN_NETHER)
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE));
        tag(BlockTags.INFINIBURN_OVERWORLD)
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE));
        tag(BlockTags.INFINIBURN_END)
                .add(ModBlocks.getKey(ModBlocks.NETHERSTONE));
    }
}

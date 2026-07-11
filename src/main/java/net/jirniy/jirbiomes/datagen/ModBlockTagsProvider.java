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
                .add(ModBlocks.getKeys(ModBlocks.DRY_FARMLAND, ModBlocks.WET_FARMLAND));

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getKey(ModBlocks.IRON_GRATE));
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
        tag(BlockTags.SUPPORTS_VEGETATION)
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);
        tag(BlockTags.CANNOT_REPLACE_BELOW_TREE_TRUNK)
                .addOptionalTag(ModTags.Blocks.WET_DIRT)
                .addOptionalTag(ModTags.Blocks.DRIED_DIRT);

        tag(BlockTags.ANIMALS_SPAWNABLE_ON)
                .addOptionalTag(ModTags.Blocks.GRASS_BLOCKS);
        tag(BlockTags.VALID_SPAWN)
                .addOptionalTag(ModTags.Blocks.GRASS_BLOCKS);
        tag(BlockTags.GRASS_BLOCKS)
                .addOptionalTag(ModTags.Blocks.GRASS_BLOCKS);
    }
}

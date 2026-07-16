package net.jirniy.jirbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.item.ModItems;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.DIRT)
                .add(ModItems.getKeys(ModBlocks.DRIED_DIRT, ModBlocks.COARSE_DRIED_DIRT, ModBlocks.ROOTED_DRIED_DIRT))
                .add(ModItems.getKeys(ModBlocks.WETLAND, ModBlocks.COARSE_WETLAND, ModBlocks.ROOTED_WETLAND));

        tag(ModTags.Items.GINKGO_LOGS)
                .add(ModItems.getKeys(ModBlocks.GINKGO_LOG, ModBlocks.STRIPPED_GINKGO_LOG))
                .add(ModItems.getKeys(ModBlocks.GINKGO_WOOD, ModBlocks.STRIPPED_GINKGO_WOOD));
        tag(ItemTags.LEAVES)
                .add(ModItems.getKey(ModBlocks.GINKGO_LEAVES))
                .add(ModItems.getKey(ModBlocks.APPLE_LEAVES));
        tag(ItemTags.LOGS_THAT_BURN)
                .addOptionalTag(ModTags.Items.GINKGO_LOGS);

        tag(ItemTags.PLANKS)
                .add(ModItems.getKey(ModBlocks.GINKGO_PLANKS));
        tag(ItemTags.SAPLINGS)
                .add(ModItems.getKey(ModBlocks.GINKGO_SAPLING))
                .add(ModItems.getKey(ModBlocks.APPLE_OAK_SAPLING));
        tag(ItemTags.WOODEN_STAIRS)
                .add(ModItems.getKey(ModBlocks.GINKGO_STAIRS));
        tag(ItemTags.WOODEN_SLABS)
                .add(ModItems.getKey(ModBlocks.GINKGO_SLAB));
        tag(ItemTags.WOODEN_DOORS)
                .add(ModItems.getKey(ModBlocks.GINKGO_DOOR));
        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModItems.getKey(ModBlocks.GINKGO_TRAPDOOR));
        tag(ItemTags.WOODEN_BUTTONS)
                .add(ModItems.getKey(ModBlocks.GINKGO_BUTTON));
        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModItems.getKey(ModBlocks.GINKGO_PRESSURE_PLATE));
        tag(ItemTags.WOODEN_FENCES)
                .add(ModItems.getKey(ModBlocks.GINKGO_FENCE));
        tag(ItemTags.FENCE_GATES)
                .add(ModItems.getKey(ModBlocks.GINKGO_FENCE_GATE));
        tag(ItemTags.WOODEN_SHELVES)
                .add(ModItems.getKey(ModBlocks.GINKGO_SHELF));
        tag(ItemTags.SIGNS)
                .add(ModItems.getKey(ModItems.GINKGO_SIGN));
        tag(ItemTags.HANGING_SIGNS)
                .add(ModItems.getKey(ModItems.HANGING_GINKGO_SIGN));

        tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.getKey(ModItems.APPLE_SEEDS));
    }
}

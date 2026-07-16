package net.jirniy.jirbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.entity.ModEntities;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagsProvider extends FabricTagsProvider.EntityTypeTagsProvider {
    public ModEntityTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EntityTypeTags.BOAT)
                .add(ModEntities.getKey(ModEntities.GINKGO_BOAT))
                .add(ModEntities.getKey(ModEntities.GINKGO_CHEST_BOAT));
    }
}

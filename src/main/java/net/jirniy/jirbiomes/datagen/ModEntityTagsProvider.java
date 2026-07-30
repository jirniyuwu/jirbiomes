package net.jirniy.jirbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.jirniy.jirbiomes.entity.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagsProvider extends FabricTagsProvider.EntityTypeTagsProvider {
    public ModEntityTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EntityTypeTags.BOAT)
                .add(ModEntities.getKey(ModEntities.GINKGO_BOAT))
                .add(ModEntities.getKey(ModEntities.GINKGO_CHEST_BOAT))
                .add(ModEntities.getKey(ModEntities.PALM_BOAT))
                .add(ModEntities.getKey(ModEntities.PALM_CHEST_BOAT));
    }
}

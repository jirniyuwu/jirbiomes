package net.jirniy.jirbiomes.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.jirniy.jirbiomes.block.ModFluids;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagsProvider extends FabricTagsProvider.FluidTagsProvider {
    public ModFluidTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Fluids.TARR)
                .add(ModFluids.getKeys(ModFluids.TARR, ModFluids.FLOWING_TARR));
    }
}

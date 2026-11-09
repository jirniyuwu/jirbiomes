package net.jirniy.jirbiomes.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.jirniy.jirbiomes.villager.ModVillagerTrades;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

public class ModVillagerTradeTags extends FabricTagsProvider<VillagerTrade> {
    public ModVillagerTradeTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.VILLAGER_TRADE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(VillagerTradeTags.FARMER_LEVEL_2)
                .add(TagEntry.element(ModVillagerTrades.FARMER_2_EMERALD_APPLE_SEEDS.identifier()))
                .add(TagEntry.element(ModVillagerTrades.FARMER_2_EMERALD_COTTON_SEEDS.identifier()));
        getOrCreateRawBuilder(VillagerTradeTags.FARMER_LEVEL_3)
                .add(TagEntry.element(ModVillagerTrades.FARMER_3_COCONUT_EMERALD.identifier()))
                .add(TagEntry.element(ModVillagerTrades.FARMER_3_EMERALD_PRICKLY_PEAR.identifier()));

        getOrCreateRawBuilder(VillagerTradeTags.MASON_LEVEL_2)
                .add(TagEntry.element(ModVillagerTrades.MASON_2_EMERALD_SALT.identifier()));

        getOrCreateRawBuilder(VillagerTradeTags.SHEPHERD_LEVEL_2)
                .add(TagEntry.element(ModVillagerTrades.SHEPHERD_2_COTTON_STRING.identifier()));
        getOrCreateRawBuilder(VillagerTradeTags.SHEPHERD_LEVEL_5)
                .add(TagEntry.element(ModVillagerTrades.SHEPHERD_5_COTTON_EMERALD.identifier()));

        getOrCreateRawBuilder(VillagerTradeTags.WANDERING_TRADER_BUYING)
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_COTTON_EMERALD.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_SALT_LAMP_EMERALD.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_DRIED_FIBERS_EMERALD.identifier()));
        getOrCreateRawBuilder(VillagerTradeTags.WANDERING_TRADER_COMMON)
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_APPLE_SEEDS.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_COTTON_SEEDS.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_BARREL_CACTUS.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_HANGING_MUSHROOMS.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_SMALL_MUSHROOMS.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_LOTUS.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_SEA_URCHIN.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_SEASHELLS.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_THICK_ICICLE.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_GINKGO_LOG.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_GINKGO_SAPLING.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_PALM_LOG.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_PALM_SAPLING.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_PROTOTAXITE_LOG.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_PROTOTAXITE_SAPLING.identifier()));
        getOrCreateRawBuilder(VillagerTradeTags.WANDERING_TRADER_UNCOMMON)
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_POTENT_MAGMA.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_PERMAFROST.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_TENEBRIS_SAPLING.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_ALGAE_BUCKET.identifier()))
                .add(TagEntry.element(ModVillagerTrades.WANDERING_TRADER_EMERALD_TARR_BUCKET.identifier()));

    }
}

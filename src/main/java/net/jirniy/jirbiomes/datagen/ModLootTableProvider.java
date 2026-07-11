package net.jirniy.jirbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableSubProvider;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModLootTableProvider extends FabricBlockLootSubProvider {
    public ModLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.IRON_GRATE);

        dropSelf(ModBlocks.DRIED_DIRT);
        dropSelf(ModBlocks.COARSE_DRIED_DIRT);
        dropSelf(ModBlocks.ROOTED_DRIED_DIRT);

        dropSelf(ModBlocks.WETLAND);
        dropSelf(ModBlocks.COARSE_WETLAND);
        dropSelf(ModBlocks.ROOTED_WETLAND);

        add(ModBlocks.DRIED_GRASS_BLOCK, silkTouchOrElseDrop(ModBlocks.DRIED_GRASS_BLOCK, ModBlocks.DRIED_DIRT));
        add(ModBlocks.WET_GRASS_BLOCK, silkTouchOrElseDrop(ModBlocks.WET_GRASS_BLOCK, ModBlocks.WETLAND));

        dropOther(ModBlocks.DRY_FARMLAND, ModBlocks.DRIED_DIRT);
        dropOther(ModBlocks.WET_FARMLAND, ModBlocks.WETLAND);
    }

    public LootTable.Builder silkTouchOrElseDrop(final Block block, ItemLike drop) {
        return this.createSilkTouchDispatchTable(
                block,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(drop)
                )
        );
    }

    public LootTable.Builder createMultiOreDrops(final Block block, ItemLike drop, float min, float max) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(drop)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }
}

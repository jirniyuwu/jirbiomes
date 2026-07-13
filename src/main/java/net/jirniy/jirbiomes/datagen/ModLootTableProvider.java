package net.jirniy.jirbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.block.custom.AppleCropBlock;
import net.jirniy.jirbiomes.item.ModItems;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootSubProvider {
    public ModLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.IRON_GRATE);
        add(ModBlocks.NETHERSTONE, silkTouchOrElseDrop(ModBlocks.NETHERSTONE, Blocks.NETHERRACK));

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
        dropOther(ModBlocks.DRIED_DIRT_PATH, ModBlocks.DRIED_DIRT);
        dropOther(ModBlocks.WETLAND_PATH, ModBlocks.WETLAND);

        dropSelf(ModBlocks.GINKGO_LOG);
        dropSelf(ModBlocks.STRIPPED_GINKGO_LOG);
        dropSelf(ModBlocks.GINKGO_WOOD);
        dropSelf(ModBlocks.STRIPPED_GINKGO_WOOD);
        dropSelf(ModBlocks.GINKGO_SAPLING);
        dropSelf(ModBlocks.GINKGO_PLANKS);
        dropSelf(ModBlocks.GINKGO_STAIRS);
        add(ModBlocks.GINKGO_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.GINKGO_TRAPDOOR);
        dropSelf(ModBlocks.GINKGO_FENCE);
        dropSelf(ModBlocks.GINKGO_FENCE_GATE);
        add(ModBlocks.GINKGO_DOOR, this::createDoorTable);
        dropSelf(ModBlocks.GINKGO_BUTTON);
        dropSelf(ModBlocks.GINKGO_PRESSURE_PLATE);

        add(ModBlocks.GINKGO_LEAVES, createLeavesDrops(ModBlocks.GINKGO_LEAVES, ModBlocks.GINKGO_SAPLING, 0.05f));

        this.add(ModBlocks.APPLE_CROP, this.createCropDrops(ModBlocks.APPLE_CROP, Blocks.OAK_SAPLING.asItem(), ModItems.APPLE_SEEDS,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.APPLE_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AppleCropBlock.AGE, AppleCropBlock.MAX_AGE))));
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

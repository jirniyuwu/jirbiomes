package net.jirniy.jirbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.block.custom.AppleCropBlock;
import net.jirniy.jirbiomes.block.custom.CoconutBlock;
import net.jirniy.jirbiomes.block.custom.SmallBarrelCactusBlock;
import net.jirniy.jirbiomes.item.ModItems;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
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

        dropSelf(ModBlocks.DRIED_DIRT);
        dropSelf(ModBlocks.COARSE_DRIED_DIRT);
        dropSelf(ModBlocks.ROOTED_DRIED_DIRT);

        dropSelf(ModBlocks.WETLAND);
        dropSelf(ModBlocks.COARSE_WETLAND);
        dropSelf(ModBlocks.ROOTED_WETLAND);

        dropSelf(ModBlocks.ROOTED_SAND);
        dropSelf(ModBlocks.ROOTED_RED_SAND);
        dropSelf(ModBlocks.ROOTED_MUD);
        add(ModBlocks.SAND_SHELLS, createMultiDropsWithoutFortune(ModBlocks.SAND_SHELLS, ModItems.SEASHELL, 1, 2));
        add(ModBlocks.RED_SAND_SHELLS, createMultiDropsWithoutFortune(ModBlocks.RED_SAND_SHELLS, ModItems.SEASHELL, 1, 2));

        add(ModBlocks.DRIED_GRASS_BLOCK, silkTouchOrElseDrop(ModBlocks.DRIED_GRASS_BLOCK, ModBlocks.DRIED_DIRT));
        add(ModBlocks.WET_GRASS_BLOCK, silkTouchOrElseDrop(ModBlocks.WET_GRASS_BLOCK, ModBlocks.WETLAND));

        dropOther(ModBlocks.DRY_FARMLAND, ModBlocks.DRIED_DIRT);
        dropOther(ModBlocks.WET_FARMLAND, ModBlocks.WETLAND);
        dropOther(ModBlocks.DRIED_DIRT_PATH, ModBlocks.DRIED_DIRT);
        dropOther(ModBlocks.WETLAND_PATH, ModBlocks.WETLAND);

        add(ModBlocks.CATTAIL, this::createTallPlantShearsDrop);
        add(ModBlocks.SEA_URCHIN, this::createShearsOrSilkTouchOnlyDrop);
        add(ModBlocks.SHARP_RIBS, silkTouchOrElseDrop(ModBlocks.SHARP_RIBS, Items.BONE));
        dropSelf(ModBlocks.LOTUS_FLOWER);
        dropPottedContents(ModBlocks.POTTED_LOTUS_FLOWER);

        add(ModBlocks.NETHERSTONE, silkTouchOrElseDrop(ModBlocks.NETHERSTONE, Blocks.NETHERRACK));
        dropSelf(ModBlocks.NETHERSTONE_STAIRS);
        add(ModBlocks.NETHERSTONE_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.NETHERSTONE_WALL);
        dropSelf(ModBlocks.NETHERSTONE_BRICKS);
        dropSelf(ModBlocks.NETHERSTONE_BRICKS_STAIRS);
        add(ModBlocks.NETHERSTONE_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.NETHERSTONE_BRICKS_WALL);
        dropSelf(ModBlocks.POLISHED_NETHERSTONE);
        dropSelf(ModBlocks.POLISHED_NETHERSTONE_STAIRS);
        add(ModBlocks.POLISHED_NETHERSTONE_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.POLISHED_NETHERSTONE_WALL);

        add(ModBlocks.SEASHELL_BLOCK, createMultiDropsWithoutFortune(ModBlocks.SEASHELL_BLOCK, ModItems.SEASHELL, 3, 4));
        add(ModBlocks.SUSPICIOUS_SEASHELLS, createMultiDropsWithoutFortune(ModBlocks.SEASHELL_BLOCK, ModItems.SEASHELL, 3, 4));
        dropSelf(ModBlocks.SEASHELL_BRICKS);
        dropSelf(ModBlocks.SEASHELL_BRICKS_STAIRS);
        add(ModBlocks.SEASHELL_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.SEASHELL_BRICKS_WALL);

        dropSelf(ModBlocks.SALT_BLOCK);
        dropSelf(ModBlocks.SALT_BLOCK_STAIRS);
        add(ModBlocks.SALT_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.SALT_BLOCK_WALL);
        dropSelf(ModBlocks.SALT_BRICKS);
        dropSelf(ModBlocks.SALT_BRICKS_STAIRS);
        add(ModBlocks.SALT_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.SALT_BRICKS_WALL);
        dropSelf(ModBlocks.POLISHED_SALT);
        dropSelf(ModBlocks.POLISHED_SALT_STAIRS);
        add(ModBlocks.POLISHED_SALT_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.POLISHED_SALT_WALL);

        add(ModBlocks.BRIMSTONE_GOLD_ORE, createOreDrop(ModBlocks.BRIMSTONE_GOLD_ORE, Items.RAW_GOLD));

        dropWhenSilkTouch(ModBlocks.ICICLE);
        dropWhenSilkTouch(ModBlocks.PERMAFROST_BLOCK);

        add(ModBlocks.FROSTED_STONE, silkTouchOrElseDrop(ModBlocks.FROSTED_STONE, Blocks.COBBLESTONE));
        dropSelf(ModBlocks.FROSTED_STONE_STAIRS);
        add(ModBlocks.FROSTED_STONE_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.FROSTED_STONE_WALL);
        dropSelf(ModBlocks.FROST_BRICKS);
        dropSelf(ModBlocks.FROST_BRICKS_STAIRS);
        add(ModBlocks.FROST_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.FROST_BRICKS_WALL);
        dropSelf(ModBlocks.POLISHED_FROST);
        dropSelf(ModBlocks.POLISHED_FROST_STAIRS);
        add(ModBlocks.POLISHED_FROST_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.POLISHED_FROST_WALL);

        dropWhenSilkTouch(ModBlocks.FROZEN_GRASS);
        dropPottedContents(ModBlocks.POTTED_FROZEN_GRASS);
        dropSelf(ModBlocks.PACKED_SNOW);
        dropSelf(ModBlocks.SNOW_BRICKS);
        dropSelf(ModBlocks.SNOW_BRICKS_STAIRS);
        add(ModBlocks.SNOW_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.SNOW_BRICKS_WALL);
        dropSelf(ModBlocks.POLISHED_SNOW);
        dropSelf(ModBlocks.POLISHED_SNOW_STAIRS);
        add(ModBlocks.POLISHED_SNOW_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.POLISHED_SNOW_WALL);

        dropSelf(ModBlocks.STRAW_BLOCK);
        dropSelf(ModBlocks.PACKED_STRAW);
        dropSelf(ModBlocks.PACKED_STRAW_STAIRS);
        add(ModBlocks.PACKED_STRAW_SLAB, this::createSlabItemTable);

        dropWhenSilkTouch(ModBlocks.POTENT_MAGMA_BLOCK);
        add(ModBlocks.BRIMGRASS_BLOCK, silkTouchOrElseDrop(ModBlocks.BRIMGRASS_BLOCK, ModBlocks.BRIMSTONE));
        dropWhenSilkTouch(ModBlocks.BRIMGRASS);
        dropSelf(ModBlocks.CHISELED_BRIMSTONE_BRICKS);
        dropSelf(ModBlocks.BRIMSTONE);
        dropSelf(ModBlocks.IGNITED_BRIMSTONE);
        dropSelf(ModBlocks.MOSSY_BRIMSTONE_BRICKS);
        dropSelf(ModBlocks.MOSSY_BRIMSTONE_BRICKS_STAIRS);
        add(ModBlocks.MOSSY_BRIMSTONE_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.MOSSY_BRIMSTONE_BRICKS_WALL);
        dropSelf(ModBlocks.CRACKED_BRIMSTONE_BRICKS);
        dropSelf(ModBlocks.CRACKED_BRIMSTONE_BRICKS_STAIRS);
        add(ModBlocks.CRACKED_BRIMSTONE_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.CRACKED_BRIMSTONE_BRICKS_WALL);
        dropSelf(ModBlocks.BRIMSTONE_BRICKS);
        dropSelf(ModBlocks.BRIMSTONE_BRICKS_STAIRS);
        add(ModBlocks.BRIMSTONE_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.BRIMSTONE_BRICKS_WALL);
        dropSelf(ModBlocks.POLISHED_BRIMSTONE);
        dropSelf(ModBlocks.POLISHED_BRIMSTONE_STAIRS);
        add(ModBlocks.POLISHED_BRIMSTONE_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.POLISHED_BRIMSTONE_WALL);

        dropSelf(ModBlocks.GINKGO_LOG);
        dropSelf(ModBlocks.STRIPPED_GINKGO_LOG);
        dropSelf(ModBlocks.GINKGO_WOOD);
        dropSelf(ModBlocks.STRIPPED_GINKGO_WOOD);
        dropSelf(ModBlocks.GINKGO_SAPLING);
        dropPottedContents(ModBlocks.POTTED_GINKGO_SAPLING);
        dropSelf(ModBlocks.GINKGO_PLANKS);
        dropSelf(ModBlocks.GINKGO_STAIRS);
        add(ModBlocks.GINKGO_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.GINKGO_TRAPDOOR);
        dropSelf(ModBlocks.GINKGO_FENCE);
        dropSelf(ModBlocks.GINKGO_FENCE_GATE);
        add(ModBlocks.GINKGO_DOOR, this::createDoorTable);
        dropSelf(ModBlocks.GINKGO_BUTTON);
        dropSelf(ModBlocks.GINKGO_PRESSURE_PLATE);
        dropSelf(ModBlocks.GINKGO_SHELF);
        dropSelf(ModBlocks.GINKGO_SIGN);
        dropSelf(ModBlocks.HANGING_GINKGO_SIGN);

        add(ModBlocks.GINKGO_LEAVES, createLeavesDrops(ModBlocks.GINKGO_LEAVES, ModBlocks.GINKGO_SAPLING, 0.05f));

        dropSelf(ModBlocks.PALM_LOG);
        dropSelf(ModBlocks.STRIPPED_PALM_LOG);
        dropSelf(ModBlocks.PALM_WOOD);
        dropSelf(ModBlocks.STRIPPED_PALM_WOOD);
        dropSelf(ModBlocks.PALM_SAPLING);
        dropPottedContents(ModBlocks.POTTED_PALM_SAPLING);
        dropSelf(ModBlocks.PALM_PLANKS);
        dropSelf(ModBlocks.PALM_STAIRS);
        add(ModBlocks.PALM_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.PALM_TRAPDOOR);
        dropSelf(ModBlocks.PALM_FENCE);
        dropSelf(ModBlocks.PALM_FENCE_GATE);
        add(ModBlocks.PALM_DOOR, this::createDoorTable);
        dropSelf(ModBlocks.PALM_BUTTON);
        dropSelf(ModBlocks.PALM_PRESSURE_PLATE);
        dropSelf(ModBlocks.PALM_SHELF);
        dropSelf(ModBlocks.PALM_SIGN);
        dropSelf(ModBlocks.HANGING_PALM_SIGN);

        add(ModBlocks.PALM_LEAVES, createLeavesDrops(ModBlocks.PALM_LEAVES, ModBlocks.PALM_SAPLING, 0.1f));

        dropSelf(ModBlocks.TENEBRIS_LOG);
        dropSelf(ModBlocks.STRIPPED_TENEBRIS_LOG);
        dropSelf(ModBlocks.TENEBRIS_WOOD);
        dropSelf(ModBlocks.STRIPPED_TENEBRIS_WOOD);
        dropSelf(ModBlocks.TENEBRIS_PLANKS);
        dropSelf(ModBlocks.TENEBRIS_STAIRS);
        add(ModBlocks.TENEBRIS_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.TENEBRIS_TRAPDOOR);
        dropSelf(ModBlocks.TENEBRIS_FENCE);
        dropSelf(ModBlocks.TENEBRIS_FENCE_GATE);
        add(ModBlocks.TENEBRIS_DOOR, this::createDoorTable);
        dropSelf(ModBlocks.TENEBRIS_BUTTON);
        dropSelf(ModBlocks.TENEBRIS_PRESSURE_PLATE);
        dropSelf(ModBlocks.TENEBRIS_SHELF);
        dropSelf(ModBlocks.TENEBRIS_SIGN);
        dropSelf(ModBlocks.HANGING_TENEBRIS_SIGN);
        dropSelf(ModBlocks.TENEBRIS_SAPLING);
        dropPottedContents(ModBlocks.POTTED_TENEBRIS_SAPLING);
        add(ModBlocks.TENEBRIS_LEAVES, createShearsOrSilkTouchOnlyDrop(ModBlocks.TENEBRIS_LEAVES));

        add(ModBlocks.APPLE_CROP, createCropDrops(ModBlocks.APPLE_CROP, ModBlocks.APPLE_OAK_SAPLING.asItem(), ModItems.APPLE_SEEDS,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.APPLE_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AppleCropBlock.AGE, AppleCropBlock.MAX_AGE))));
        add(ModBlocks.APPLE_LEAVES, createLeavesDrops(ModBlocks.APPLE_LEAVES, Blocks.OAK_SAPLING, 0.05f));
        dropSelf(ModBlocks.APPLE_OAK_SAPLING);

        dropOther(ModBlocks.PRICKLY_PEAR_SEED, ModItems.PRICKLY_PEAR);
        this.add(ModBlocks.SMALL_BARREL_CACTUS, block -> this.applyExplosionDecay(block,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.SMALL_BARREL_CACTUS)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(SmallBarrelCactusBlock.AGE, 1))
                        .or(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.SMALL_BARREL_CACTUS)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(SmallBarrelCactusBlock.AGE, 2))))
                        .add(LootItem.lootTableItem(ModItems.BARREL_CACTUS)))
        ));
        dropOther(ModBlocks.LARGE_BARREL_CACTUS, ModItems.BARREL_CACTUS);

        this.add(ModBlocks.COCONUT_PLANT, block -> this.applyExplosionDecay(block,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.COCONUT_PLANT)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(CoconutBlock.AGE, 2)))
                        .add(LootItem.lootTableItem(ModItems.COCONUT)))
        ));

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

    public LootTable.Builder createTallPlantShearsDrop(final Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().when(this.hasShears()).add(LootItem.lootTableItem(block)));
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

    public LootTable.Builder createMultiDropsWithoutFortune(final Block block, ItemLike drop, float min, float max) {
        return this.createSilkTouchDispatchTable(
                block,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(drop)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                )
        );
    }
}

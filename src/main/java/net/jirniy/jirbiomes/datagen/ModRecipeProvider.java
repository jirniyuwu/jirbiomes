package net.jirniy.jirbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.item.ModItems;
import net.jirniy.jirbiomes.misc.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public static final int BASE_COOKING_TIME = 200;
    public static final int BASE_SMELTING_TIME = BASE_COOKING_TIME/2;
    public static final int BASE_CAMPFIRE_TIME = 600;

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                final List<ItemLike> DRIED_FIBER_SMELTABLE = List.of(
                        Items.SHORT_GRASS,
                        Items.TALL_GRASS,
                        Items.BUSH,
                        Items.FERN,
                        Items.LARGE_FERN,
                        Items.BAMBOO,
                        Items.SUGAR_CANE,
                        Items.FIREFLY_BUSH,
                        Items.WHEAT,
                        ModBlocks.CATTAIL,
                        ModBlocks.BRIMGRASS
                );
                final List<ItemLike> DRIED_FIBER_QUICK_SMELTABLE = List.of(
                        Items.DRY_SHORT_GRASS,
                        Items.DRY_TALL_GRASS
                );

                oreSmelting(List.of(ModBlocks.FROSTED_STONE), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, Blocks.STONE, 0, BASE_COOKING_TIME, "netherstone");
                oreBlasting(List.of(ModBlocks.FROSTED_STONE), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, Blocks.STONE, 0, BASE_SMELTING_TIME, "netherstone");

                oreSmelting(List.of(ModBlocks.BRIMSTONE_GOLD_ORE), RecipeCategory.MISC, CookingBookCategory.MISC, Items.GOLD_INGOT, 1.0f, BASE_COOKING_TIME, "gold_ingot");
                oreBlasting(List.of(ModBlocks.BRIMSTONE_GOLD_ORE), RecipeCategory.MISC, CookingBookCategory.MISC, Items.GOLD_INGOT, 1.0f, BASE_SMELTING_TIME, "gold_ingot");

                oreSmelting(DRIED_FIBER_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.DRIED_FIBERS, 0.1f, BASE_COOKING_TIME, "dried_fibers");
                oreSmoking(DRIED_FIBER_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.DRIED_FIBERS, 0.1f, BASE_SMELTING_TIME, "dried_fibers");
                campfireCooking(DRIED_FIBER_SMELTABLE, ModItems.DRIED_FIBERS, BASE_CAMPFIRE_TIME, 0.1f);

                oreSmelting(DRIED_FIBER_QUICK_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.DRIED_FIBERS, 0.1f, BASE_COOKING_TIME/4, "dried_fibers");
                oreSmoking(DRIED_FIBER_QUICK_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.DRIED_FIBERS, 0.1f, BASE_SMELTING_TIME/4, "dried_fibers");
                campfireCooking(DRIED_FIBER_QUICK_SMELTABLE, ModItems.DRIED_FIBERS, BASE_CAMPFIRE_TIME/4, 0.1f, "campfire_cooking_quick");

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_GRATE, 4)
                        .pattern(" S ")
                        .pattern("S S")
                        .pattern(" S ")
                        .define('S', Items.IRON_INGOT)
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .unlockedBy(getHasName(ModBlocks.IRON_GRATE), has(ModBlocks.IRON_GRATE))
                        .save(output, "iron_grate");

                shapeless(RecipeCategory.MISC, ModItems.APPLE_SEEDS, 1)
                        .requires(Items.APPLE)
                        .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE))
                        .unlockedBy(getHasName(ModItems.APPLE_SEEDS), has(ModItems.APPLE_SEEDS))
                        .group("seeds").save(output, "apple_seeds");
                shapeless(RecipeCategory.MISC, ModItems.CRACKED_COCONUT, 2)
                        .requires(ModItems.COCONUT)
                        .unlockedBy(getHasName(ModItems.COCONUT), has(ModItems.COCONUT))
                        .unlockedBy(getHasName(ModItems.CRACKED_COCONUT), has(ModItems.CRACKED_COCONUT))
                        .save(output, "cracked_coconut");

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHERSTONE, 4)
                        .pattern("SS")
                        .pattern("SS")
                        .define('S', Blocks.NETHERRACK)
                        .unlockedBy(getHasName(Blocks.NETHERRACK), has(Blocks.NETHERRACK))
                        .unlockedBy(getHasName(ModBlocks.NETHERSTONE), has(ModBlocks.NETHERSTONE))
                        .save(output, "netherstone");

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRAW_BLOCK, 1)
                        .pattern("SS")
                        .pattern("SS")
                        .define('S', ModItems.DRIED_FIBERS)
                        .unlockedBy(getHasName(ModItems.DRIED_FIBERS), has(ModItems.DRIED_FIBERS))
                        .unlockedBy(getHasName(ModBlocks.STRAW_BLOCK), has(ModBlocks.STRAW_BLOCK))
                        .save(output, "straw_block");
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_STRAW, 4)
                        .pattern("SS")
                        .pattern("SS")
                        .define('S', ModBlocks.STRAW_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.PACKED_STRAW), has(ModBlocks.PACKED_STRAW))
                        .unlockedBy(getHasName(ModBlocks.STRAW_BLOCK), has(ModBlocks.STRAW_BLOCK))
                        .save(output, "packed_straw");
                stairBuilder(ModBlocks.PACKED_STRAW_STAIRS, Ingredient.of(ModBlocks.PACKED_STRAW))
                        .unlockedBy(getHasName(ModBlocks.PACKED_STRAW), has(ModBlocks.PACKED_STRAW))
                        .group("stairs").save(output, "packed_straw_stairs");
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_STRAW_SLAB, Ingredient.of(ModBlocks.PACKED_STRAW))
                        .unlockedBy(getHasName(ModBlocks.PACKED_STRAW), has(ModBlocks.PACKED_STRAW))
                        .group("slabs").save(output, "packed_straw_slab");

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICICLE, 4)
                        .pattern("I")
                        .pattern("I")
                        .define('I', Blocks.ICE)
                        .unlockedBy(getHasName(ModBlocks.ICICLE), has(ModBlocks.ICICLE))
                        .unlockedBy(getHasName(Blocks.ICE), has(Blocks.ICE))
                        .group("speleothem").save(output, "icicle");
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PERMAFROST_BLOCK, 4)
                        .pattern("BB")
                        .pattern("BB")
                        .define('B', Blocks.BLUE_ICE)
                        .unlockedBy(getHasName(ModBlocks.PERMAFROST_BLOCK), has(ModBlocks.PERMAFROST_BLOCK))
                        .unlockedBy(getHasName(Blocks.BLUE_ICE), has(Blocks.BLUE_ICE))
                        .save(output, "permafrost_block");

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRIED_DIRT, 4)
                        .pattern("DS")
                        .pattern("SD")
                        .define('S', Blocks.SAND)
                        .define('D', Blocks.DIRT)
                        .unlockedBy(getHasName(ModBlocks.DRIED_DIRT), has(ModBlocks.DRIED_DIRT))
                        .unlockedBy(getHasName(Blocks.DIRT), has(Blocks.DIRT))
                        .unlockedBy(getHasName(Blocks.SAND), has(Blocks.SAND))
                        .group("dirt_convertible").save(output, "dried_dirt");
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WETLAND, 4)
                        .pattern("DS")
                        .pattern("SD")
                        .define('S', Blocks.MUD)
                        .define('D', Blocks.DIRT)
                        .unlockedBy(getHasName(ModBlocks.WETLAND), has(ModBlocks.WETLAND))
                        .unlockedBy(getHasName(Blocks.DIRT), has(Blocks.DIRT))
                        .unlockedBy(getHasName(Blocks.MUD), has(Blocks.MUD))
                        .group("dirt_convertible").save(output, "wetland");

                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROOTED_MUD, 1)
                        .requires(Blocks.MUD).requires(Blocks.HANGING_ROOTS)
                        .unlockedBy(getHasName(ModBlocks.ROOTED_MUD), has(ModBlocks.ROOTED_MUD))
                        .group("rooted_dirt").save(output, "rooted_mud");
                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROOTED_SAND, 1)
                        .requires(Blocks.SAND).requires(Blocks.HANGING_ROOTS)
                        .unlockedBy(getHasName(ModBlocks.ROOTED_SAND), has(ModBlocks.ROOTED_SAND))
                        .group("rooted_dirt").save(output, "rooted_sand");
                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROOTED_RED_SAND, 1)
                        .requires(Blocks.RED_SAND).requires(Blocks.HANGING_ROOTS)
                        .unlockedBy(getHasName(ModBlocks.ROOTED_RED_SAND), has(ModBlocks.ROOTED_RED_SAND))
                        .group("rooted_dirt").save(output, "rooted_red_sand");
                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROOTED_DRIED_DIRT, 1)
                        .requires(ModBlocks.DRIED_DIRT).requires(Blocks.HANGING_ROOTS)
                        .unlockedBy(getHasName(ModBlocks.ROOTED_DRIED_DIRT), has(ModBlocks.ROOTED_DRIED_DIRT))
                        .group("rooted_dirt").save(output, "rooted_dried_dirt");
                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROOTED_WETLAND, 1)
                        .requires(ModBlocks.WETLAND).requires(Blocks.HANGING_ROOTS)
                        .unlockedBy(getHasName(ModBlocks.ROOTED_WETLAND), has(ModBlocks.ROOTED_WETLAND))
                        .group("rooted_dirt").save(output, "rooted_wetland");
                shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.ROOTED_DIRT, 1)
                        .requires(Blocks.DIRT).requires(Blocks.HANGING_ROOTS)
                        .unlockedBy(getHasName(Blocks.ROOTED_DIRT), has(Blocks.ROOTED_DIRT))
                        .group("rooted_dirt").save(output, "rooted_regular_dirt");

                bricksBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALT_BRICKS, Ingredient.of(ModBlocks.SALT_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.SALT_BLOCK), has(ModBlocks.SALT_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.SALT_BRICKS), has(ModBlocks.SALT_BRICKS))
                        .group("bricks").save(output, "salt_bricks");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALT_BRICKS, ModBlocks.SALT_BLOCK);
                stairBuilder(ModBlocks.SALT_BRICKS_STAIRS, Ingredient.of(ModBlocks.SALT_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.SALT_BRICKS), has(ModBlocks.SALT_BRICKS))
                        .group("stairs").save(output, "salt_bricks_stairs");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALT_BRICKS_STAIRS, ModBlocks.SALT_BRICKS);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALT_BRICKS_SLAB, Ingredient.of(ModBlocks.SALT_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.SALT_BRICKS), has(ModBlocks.SALT_BRICKS))
                        .group("slabs").save(output, "salt_bricks_slab");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALT_BRICKS_SLAB, ModBlocks.SALT_BRICKS, 2);
                wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALT_BRICKS_WALL, Ingredient.of(ModBlocks.SALT_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.SALT_BRICKS), has(ModBlocks.SALT_BRICKS))
                        .group("walls").save(output, "salt_bricks_wall");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALT_BRICKS_WALL, ModBlocks.SALT_BRICKS);
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALT_LAMP, 1)
                        .pattern("B")
                        .pattern("I")
                        .define('I', Items.TORCH)
                        .define('B', ModBlocks.SALT_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.SALT_BLOCK), has(ModBlocks.SALT_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.SALT_LAMP), has(ModBlocks.SALT_LAMP))
                        .save(output, "salt_lamp");

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTED_STONE, 4)
                        .pattern("BI")
                        .pattern("IB")
                        .define('I', Blocks.ICE)
                        .define('B', Blocks.STONE)
                        .unlockedBy(getHasName(Blocks.ICE), has(Blocks.ICE))
                        .unlockedBy(getHasName(ModBlocks.FROSTED_STONE), has(ModBlocks.FROSTED_STONE))
                        .save(output, "frosted_stone");
                bricksBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FROST, Ingredient.of(ModBlocks.FROSTED_STONE))
                        .unlockedBy(getHasName(ModBlocks.FROSTED_STONE), has(ModBlocks.FROSTED_STONE))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_FROST), has(ModBlocks.POLISHED_FROST))
                        .group("polished").save(output, "polished_frost");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FROST, ModBlocks.FROSTED_STONE);
                stairBuilder(ModBlocks.POLISHED_FROST_STAIRS, Ingredient.of(ModBlocks.POLISHED_FROST))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_FROST), has(ModBlocks.POLISHED_FROST))
                        .group("stairs").save(output, "polished_frost_stairs");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FROST_STAIRS, ModBlocks.POLISHED_FROST);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FROST_SLAB, Ingredient.of(ModBlocks.POLISHED_FROST))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_FROST), has(ModBlocks.POLISHED_FROST))
                        .group("slabs").save(output, "polished_frost_slab");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FROST_SLAB, ModBlocks.POLISHED_FROST, 2);
                wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FROST_WALL, Ingredient.of(ModBlocks.POLISHED_FROST))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_FROST), has(ModBlocks.POLISHED_FROST))
                        .group("walls").save(output, "polished_frost_wall");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FROST_WALL, ModBlocks.POLISHED_FROST);
                bricksBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROST_BRICKS, Ingredient.of(ModBlocks.POLISHED_FROST))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_FROST), has(ModBlocks.POLISHED_FROST))
                        .unlockedBy(getHasName(ModBlocks.FROST_BRICKS), has(ModBlocks.FROST_BRICKS))
                        .group("bricks").save(output, "frost_bricks");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROST_BRICKS, ModBlocks.FROSTED_STONE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROST_BRICKS, ModBlocks.POLISHED_FROST);
                stairBuilder(ModBlocks.FROST_BRICKS_STAIRS, Ingredient.of(ModBlocks.FROST_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.FROST_BRICKS), has(ModBlocks.FROST_BRICKS))
                        .group("stairs").save(output, "frost_bricks_stairs");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROST_BRICKS_STAIRS, ModBlocks.FROST_BRICKS);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROST_BRICKS_SLAB, Ingredient.of(ModBlocks.FROST_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.FROST_BRICKS), has(ModBlocks.FROST_BRICKS))
                        .group("slabs").save(output, "frost_bricks_slab");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROST_BRICKS_SLAB, ModBlocks.FROST_BRICKS, 2);
                wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROST_BRICKS_WALL, Ingredient.of(ModBlocks.FROST_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.FROST_BRICKS), has(ModBlocks.FROST_BRICKS))
                        .group("walls").save(output, "frost_bricks_wall");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROST_BRICKS_WALL, ModBlocks.FROST_BRICKS);

                shapeless(RecipeCategory.MISC, ModBlocks.FROZEN_GRASS)
                        .requires(Blocks.ICE).requires(ModTags.Items.FROZEN_GRASS_CRAFTABLE)
                        .unlockedBy(getHasName(ModBlocks.FROZEN_GRASS), has(ModBlocks.FROZEN_GRASS))
                        .save(output, "frozen_grass");
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_SNOW, 1)
                        .pattern("II")
                        .pattern("II")
                        .define('I', Blocks.SNOW_BLOCK)
                        .unlockedBy(getHasName(Blocks.SNOW_BLOCK), has(Blocks.SNOW_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.PACKED_SNOW), has(ModBlocks.PACKED_SNOW))
                        .save(output, "packed_snow");
                bricksBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SNOW, Ingredient.of(ModBlocks.PACKED_SNOW))
                        .unlockedBy(getHasName(ModBlocks.PACKED_SNOW), has(ModBlocks.PACKED_SNOW))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_SNOW), has(ModBlocks.POLISHED_SNOW))
                        .group("polished").save(output, "polished_snow");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SNOW, ModBlocks.PACKED_SNOW);
                stairBuilder(ModBlocks.POLISHED_SNOW_STAIRS, Ingredient.of(ModBlocks.POLISHED_SNOW))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_SNOW), has(ModBlocks.POLISHED_SNOW))
                        .group("stairs").save(output, "polished_snow_stairs");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SNOW_STAIRS, ModBlocks.POLISHED_SNOW);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SNOW_SLAB, Ingredient.of(ModBlocks.POLISHED_SNOW))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_SNOW), has(ModBlocks.POLISHED_SNOW))
                        .group("slabs").save(output, "polished_snow_slab");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SNOW_SLAB, ModBlocks.POLISHED_SNOW, 2);
                wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SNOW_WALL, Ingredient.of(ModBlocks.POLISHED_SNOW))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_SNOW), has(ModBlocks.POLISHED_SNOW))
                        .group("walls").save(output, "polished_snow_wall");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SNOW_WALL, ModBlocks.POLISHED_SNOW);
                bricksBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICKS, Ingredient.of(ModBlocks.POLISHED_SNOW))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_SNOW), has(ModBlocks.POLISHED_SNOW))
                        .unlockedBy(getHasName(ModBlocks.SNOW_BRICKS), has(ModBlocks.SNOW_BRICKS))
                        .group("bricks").save(output, "snow_bricks");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICKS, ModBlocks.PACKED_SNOW);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICKS, ModBlocks.POLISHED_SNOW);
                stairBuilder(ModBlocks.SNOW_BRICKS_STAIRS, Ingredient.of(ModBlocks.SNOW_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.SNOW_BRICKS), has(ModBlocks.SNOW_BRICKS))
                        .group("stairs").save(output, "snow_bricks_stairs");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICKS_STAIRS, ModBlocks.SNOW_BRICKS);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICKS_SLAB, Ingredient.of(ModBlocks.SNOW_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.SNOW_BRICKS), has(ModBlocks.SNOW_BRICKS))
                        .group("slabs").save(output, "snow_bricks_slab");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICKS_SLAB, ModBlocks.SNOW_BRICKS, 2);
                wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICKS_WALL, Ingredient.of(ModBlocks.SNOW_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.SNOW_BRICKS), has(ModBlocks.SNOW_BRICKS))
                        .group("walls").save(output, "snow_bricks_wall");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SNOW_BRICKS_WALL, ModBlocks.SNOW_BRICKS);

                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.IGNITED_BRIMSTONE, 4)
                        .requires(Blocks.MAGMA_BLOCK).requires(ModBlocks.BRIMSTONE)
                        .requires(ModBlocks.BRIMSTONE).requires(Blocks.MAGMA_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.BRIMSTONE), has(ModBlocks.BRIMSTONE))
                        .unlockedBy(getHasName(ModBlocks.IGNITED_BRIMSTONE), has(ModBlocks.IGNITED_BRIMSTONE))
                        .save(output, "ignited_brimstone");
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_BRIMSTONE_BRICKS, 4)
                        .pattern("BBB")
                        .pattern("III")
                        .pattern("BBB")
                        .define('I', ModBlocks.IGNITED_BRIMSTONE)
                        .define('B', ModBlocks.BRIMSTONE_BRICKS)
                        .unlockedBy(getHasName(ModBlocks.IGNITED_BRIMSTONE), has(ModBlocks.IGNITED_BRIMSTONE))
                        .unlockedBy(getHasName(ModBlocks.CHISELED_BRIMSTONE_BRICKS), has(ModBlocks.CHISELED_BRIMSTONE_BRICKS))
                        .group("chiseled_bricks").save(output, "chiseled_brimstone_bricks");
                bricksBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_BRIMSTONE, Ingredient.of(ModBlocks.BRIMSTONE))
                        .unlockedBy(getHasName(ModBlocks.BRIMSTONE), has(ModBlocks.BRIMSTONE))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_BRIMSTONE), has(ModBlocks.POLISHED_BRIMSTONE))
                        .group("polished").save(output, "polished_brimstone");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_BRIMSTONE, ModBlocks.BRIMSTONE);
                stairBuilder(ModBlocks.POLISHED_BRIMSTONE_STAIRS, Ingredient.of(ModBlocks.POLISHED_BRIMSTONE))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_BRIMSTONE), has(ModBlocks.POLISHED_BRIMSTONE))
                        .group("stairs").save(output, "polished_brimstone_stairs");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_BRIMSTONE_STAIRS, ModBlocks.POLISHED_BRIMSTONE);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_BRIMSTONE_SLAB, Ingredient.of(ModBlocks.POLISHED_BRIMSTONE))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_BRIMSTONE), has(ModBlocks.POLISHED_BRIMSTONE))
                        .group("slabs").save(output, "polished_brimstone_slab");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_BRIMSTONE_SLAB, ModBlocks.POLISHED_BRIMSTONE, 2);
                wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_BRIMSTONE_WALL, Ingredient.of(ModBlocks.POLISHED_BRIMSTONE))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_BRIMSTONE), has(ModBlocks.POLISHED_BRIMSTONE))
                        .group("walls").save(output, "polished_brimstone_wall");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_BRIMSTONE_WALL, ModBlocks.POLISHED_BRIMSTONE);
                bricksBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRIMSTONE_BRICKS, Ingredient.of(ModBlocks.POLISHED_BRIMSTONE))
                        .unlockedBy(getHasName(ModBlocks.POLISHED_BRIMSTONE), has(ModBlocks.POLISHED_BRIMSTONE))
                        .unlockedBy(getHasName(ModBlocks.BRIMSTONE_BRICKS), has(ModBlocks.BRIMSTONE_BRICKS))
                        .group("bricks").save(output, "brimstone_bricks");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRIMSTONE_BRICKS, ModBlocks.BRIMSTONE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRIMSTONE_BRICKS, ModBlocks.POLISHED_BRIMSTONE);
                stairBuilder(ModBlocks.BRIMSTONE_BRICKS_STAIRS, Ingredient.of(ModBlocks.BRIMSTONE_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.BRIMSTONE_BRICKS), has(ModBlocks.BRIMSTONE_BRICKS))
                        .group("stairs").save(output, "brimstone_bricks_stairs");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRIMSTONE_BRICKS_STAIRS, ModBlocks.BRIMSTONE_BRICKS);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRIMSTONE_BRICKS_SLAB, Ingredient.of(ModBlocks.BRIMSTONE_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.BRIMSTONE_BRICKS), has(ModBlocks.BRIMSTONE_BRICKS))
                        .group("slabs").save(output, "brimstone_bricks_slab");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRIMSTONE_BRICKS_SLAB, ModBlocks.BRIMSTONE_BRICKS, 2);
                wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRIMSTONE_BRICKS_WALL, Ingredient.of(ModBlocks.BRIMSTONE_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.BRIMSTONE_BRICKS), has(ModBlocks.BRIMSTONE_BRICKS))
                        .group("walls").save(output, "brimstone_bricks_wall");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRIMSTONE_BRICKS_WALL, ModBlocks.BRIMSTONE_BRICKS);
                oreSmelting(List.of(ModBlocks.BRIMSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, ModBlocks.CRACKED_BRIMSTONE_BRICKS, 0, 200, "cracked_brimstone_bricks");
                oreBlasting(List.of(ModBlocks.BRIMSTONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, ModBlocks.CRACKED_BRIMSTONE_BRICKS, 0, 100, "cracked_brimstone_bricks");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_BRIMSTONE_BRICKS, ModBlocks.BRIMSTONE_BRICKS);
                stairBuilder(ModBlocks.CRACKED_BRIMSTONE_BRICKS_STAIRS, Ingredient.of(ModBlocks.CRACKED_BRIMSTONE_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.CRACKED_BRIMSTONE_BRICKS), has(ModBlocks.CRACKED_BRIMSTONE_BRICKS))
                        .group("stairs").save(output, "cracked_brimstone_bricks_stairs");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_STAIRS, ModBlocks.CRACKED_BRIMSTONE_BRICKS);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_SLAB, Ingredient.of(ModBlocks.CRACKED_BRIMSTONE_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.CRACKED_BRIMSTONE_BRICKS), has(ModBlocks.CRACKED_BRIMSTONE_BRICKS))
                        .group("slabs").save(output, "cracked_brimstone_bricks_slab");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_SLAB, ModBlocks.CRACKED_BRIMSTONE_BRICKS, 2);
                wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_WALL, Ingredient.of(ModBlocks.CRACKED_BRIMSTONE_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.CRACKED_BRIMSTONE_BRICKS), has(ModBlocks.CRACKED_BRIMSTONE_BRICKS))
                        .group("walls").save(output, "cracked_brimstone_bricks_wall");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_BRIMSTONE_BRICKS_WALL, ModBlocks.CRACKED_BRIMSTONE_BRICKS);
                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_BRIMSTONE_BRICKS, 1)
                        .requires(ModBlocks.BRIMGRASS).requires(ModBlocks.BRIMSTONE_BRICKS)
                        .unlockedBy(getHasName(ModBlocks.BRIMSTONE_BRICKS), has(ModBlocks.BRIMSTONE_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.MOSSY_BRIMSTONE_BRICKS), has(ModBlocks.MOSSY_BRIMSTONE_BRICKS))
                        .save(output, "mossy_brimstone_bricks");
                stairBuilder(ModBlocks.MOSSY_BRIMSTONE_BRICKS_STAIRS, Ingredient.of(ModBlocks.MOSSY_BRIMSTONE_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.MOSSY_BRIMSTONE_BRICKS), has(ModBlocks.MOSSY_BRIMSTONE_BRICKS))
                        .group("stairs").save(output, "mossy_brimstone_bricks_stairs");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_STAIRS, ModBlocks.MOSSY_BRIMSTONE_BRICKS);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_SLAB, Ingredient.of(ModBlocks.MOSSY_BRIMSTONE_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.MOSSY_BRIMSTONE_BRICKS), has(ModBlocks.MOSSY_BRIMSTONE_BRICKS))
                        .group("slabs").save(output, "mossy_brimstone_bricks_slab");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_SLAB, ModBlocks.MOSSY_BRIMSTONE_BRICKS, 2);
                wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_WALL, Ingredient.of(ModBlocks.MOSSY_BRIMSTONE_BRICKS))
                        .unlockedBy(getHasName(ModBlocks.MOSSY_BRIMSTONE_BRICKS), has(ModBlocks.MOSSY_BRIMSTONE_BRICKS))
                        .group("walls").save(output, "mossy_brimstone_bricks_wall");
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_BRIMSTONE_BRICKS_WALL, ModBlocks.MOSSY_BRIMSTONE_BRICKS);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COARSE_DRIED_DIRT, 4)
                        .pattern("DS")
                        .pattern("SD")
                        .define('S', Blocks.GRAVEL)
                        .define('D', ModBlocks.DRIED_DIRT)
                        .unlockedBy(getHasName(ModBlocks.DRIED_DIRT), has(ModBlocks.DRIED_DIRT))
                        .unlockedBy(getHasName(ModBlocks.COARSE_DRIED_DIRT), has(ModBlocks.COARSE_DRIED_DIRT))
                        .group("coarse_dirt").save(output, "coarse_dried_dirt");
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COARSE_WETLAND, 4)
                        .pattern("DS")
                        .pattern("SD")
                        .define('S', Blocks.GRAVEL)
                        .define('D', ModBlocks.WETLAND)
                        .unlockedBy(getHasName(ModBlocks.WETLAND), has(ModBlocks.WETLAND))
                        .unlockedBy(getHasName(ModBlocks.COARSE_WETLAND), has(ModBlocks.COARSE_WETLAND))
                        .group("coarse_dirt").save(output, "coarse_wetland");

                shaped(RecipeCategory.MISC, Blocks.CACTUS)
                        .pattern("OO")
                        .pattern("OO")
                        .define('O', ModItems.BARREL_CACTUS)
                        .unlockedBy(getHasName(ModItems.BARREL_CACTUS), has(ModItems.BARREL_CACTUS))
                        .save(output, "cactus_from_barrel_cactus");

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GINKGO_WOOD, 3)
                        .pattern("LL")
                        .pattern("LL")
                        .define('L', ModBlocks.GINKGO_LOG)
                        .unlockedBy(getHasName(ModBlocks.GINKGO_LOG), has(ModBlocks.GINKGO_LOG))
                        .unlockedBy(getHasName(ModBlocks.GINKGO_WOOD), has(ModBlocks.GINKGO_WOOD))
                        .group("wood").save(output, "ginkgo_wood");
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_GINKGO_WOOD, 3)
                        .pattern("LL")
                        .pattern("LL")
                        .define('L', ModBlocks.STRIPPED_GINKGO_LOG)
                        .unlockedBy(getHasName(ModBlocks.STRIPPED_GINKGO_LOG), has(ModBlocks.STRIPPED_GINKGO_LOG))
                        .unlockedBy(getHasName(ModBlocks.STRIPPED_GINKGO_WOOD), has(ModBlocks.STRIPPED_GINKGO_WOOD))
                        .group("wood").save(output, "stripped_ginkgo_wood");
                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GINKGO_PLANKS, 4)
                        .requires(ModTags.Items.GINKGO_LOGS)
                        .unlockedBy(getHasName(ModBlocks.GINKGO_LOG), has(ModTags.Items.GINKGO_LOGS))
                        .group("planks").save(output, "ginkgo_planks");
                stairBuilder(ModBlocks.GINKGO_STAIRS, Ingredient.of(ModBlocks.GINKGO_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.GINKGO_PLANKS), has(ModBlocks.GINKGO_PLANKS))
                        .group("wooden_stairs").save(output, "ginkgo_stairs");
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GINKGO_SLAB, Ingredient.of(ModBlocks.GINKGO_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.GINKGO_PLANKS), has(ModBlocks.GINKGO_PLANKS))
                        .group("wooden_slabs").save(output, "ginkgo_slab");
                fenceBuilder(ModBlocks.GINKGO_FENCE, Ingredient.of(ModBlocks.GINKGO_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.GINKGO_PLANKS), has(ModBlocks.GINKGO_PLANKS))
                        .group("wooden_fences").save(output, "ginkgo_fence");
                fenceGateBuilder(ModBlocks.GINKGO_FENCE_GATE, Ingredient.of(ModBlocks.GINKGO_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.GINKGO_PLANKS), has(ModBlocks.GINKGO_PLANKS))
                        .group("wooden_fence_gates").save(output, "ginkgo_fence_gate");
                pressurePlateBuilder(RecipeCategory.REDSTONE, ModBlocks.GINKGO_PRESSURE_PLATE, Ingredient.of(ModBlocks.GINKGO_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.GINKGO_PLANKS), has(ModBlocks.GINKGO_PLANKS))
                        .group("wooden_pressure_plates").save(output, "ginkgo_pressure_plate");
                buttonBuilder(ModBlocks.GINKGO_BUTTON, Ingredient.of(ModBlocks.GINKGO_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.GINKGO_PLANKS), has(ModBlocks.GINKGO_PLANKS))
                        .group("wooden_buttons").save(output, "ginkgo_button");
                doorBuilder(ModBlocks.GINKGO_DOOR, Ingredient.of(ModBlocks.GINKGO_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.GINKGO_PLANKS), has(ModBlocks.GINKGO_PLANKS))
                        .group("wooden_doors").save(output, "ginkgo_door");
                trapdoorBuilder(ModBlocks.GINKGO_TRAPDOOR, Ingredient.of(ModBlocks.GINKGO_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.GINKGO_PLANKS), has(ModBlocks.GINKGO_PLANKS))
                        .group("wooden_trapdoors").save(output, "ginkgo_trapdoor");
                shelf(ModBlocks.GINKGO_SHELF, ModBlocks.STRIPPED_GINKGO_LOG);
                signBuilder(ModItems.GINKGO_SIGN, Ingredient.of(ModBlocks.GINKGO_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.GINKGO_PLANKS), has(ModBlocks.GINKGO_PLANKS))
                        .group("signs").save(output, "ginkgo_sign");
                hangingSignBuilder(ModItems.HANGING_GINKGO_SIGN, Ingredient.of(ModBlocks.STRIPPED_GINKGO_LOG))
                        .unlockedBy(getHasName(ModBlocks.STRIPPED_GINKGO_LOG), has(ModBlocks.STRIPPED_GINKGO_LOG))
                        .group("hanging_signs").save(output, "hanging_ginkgo_sign");
                chestBoat(ModItems.GINKGO_CHEST_BOAT, ModItems.GINKGO_BOAT);
                woodenBoat(ModItems.GINKGO_BOAT, ModBlocks.GINKGO_PLANKS);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_WOOD, 3)
                        .pattern("LL")
                        .pattern("LL")
                        .define('L', ModBlocks.PALM_LOG)
                        .unlockedBy(getHasName(ModBlocks.PALM_LOG), has(ModBlocks.PALM_LOG))
                        .unlockedBy(getHasName(ModBlocks.PALM_WOOD), has(ModBlocks.PALM_WOOD))
                        .group("wood").save(output, "palm_wood");
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_PALM_WOOD, 3)
                        .pattern("LL")
                        .pattern("LL")
                        .define('L', ModBlocks.STRIPPED_PALM_LOG)
                        .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_LOG), has(ModBlocks.STRIPPED_PALM_LOG))
                        .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_WOOD), has(ModBlocks.STRIPPED_PALM_WOOD))
                        .group("wood").save(output, "stripped_palm_wood");
                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_PLANKS, 4)
                        .requires(ModTags.Items.PALM_LOGS)
                        .unlockedBy(getHasName(ModBlocks.PALM_LOG), has(ModTags.Items.PALM_LOGS))
                        .group("planks").save(output, "palm_planks");
                stairBuilder(ModBlocks.PALM_STAIRS, Ingredient.of(ModBlocks.PALM_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.PALM_PLANKS), has(ModBlocks.PALM_PLANKS))
                        .group("wooden_stairs").save(output, "palm_stairs");
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_SLAB, Ingredient.of(ModBlocks.PALM_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.PALM_PLANKS), has(ModBlocks.PALM_PLANKS))
                        .group("wooden_slabs").save(output, "palm_slab");
                fenceBuilder(ModBlocks.PALM_FENCE, Ingredient.of(ModBlocks.PALM_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.PALM_PLANKS), has(ModBlocks.PALM_PLANKS))
                        .group("wooden_fences").save(output, "palm_fence");
                fenceGateBuilder(ModBlocks.PALM_FENCE_GATE, Ingredient.of(ModBlocks.PALM_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.PALM_PLANKS), has(ModBlocks.PALM_PLANKS))
                        .group("wooden_fence_gates").save(output, "palm_fence_gate");
                pressurePlateBuilder(RecipeCategory.REDSTONE, ModBlocks.PALM_PRESSURE_PLATE, Ingredient.of(ModBlocks.PALM_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.PALM_PLANKS), has(ModBlocks.PALM_PLANKS))
                        .group("wooden_pressure_plates").save(output, "palm_pressure_plate");
                buttonBuilder(ModBlocks.PALM_BUTTON, Ingredient.of(ModBlocks.PALM_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.PALM_PLANKS), has(ModBlocks.PALM_PLANKS))
                        .group("wooden_buttons").save(output, "palm_button");
                doorBuilder(ModBlocks.PALM_DOOR, Ingredient.of(ModBlocks.PALM_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.PALM_PLANKS), has(ModBlocks.PALM_PLANKS))
                        .group("wooden_doors").save(output, "palm_door");
                trapdoorBuilder(ModBlocks.PALM_TRAPDOOR, Ingredient.of(ModBlocks.PALM_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.PALM_PLANKS), has(ModBlocks.PALM_PLANKS))
                        .group("wooden_trapdoors").save(output, "palm_trapdoor");
                shelf(ModBlocks.PALM_SHELF, ModBlocks.STRIPPED_PALM_LOG);
                signBuilder(ModItems.PALM_SIGN, Ingredient.of(ModBlocks.PALM_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.PALM_PLANKS), has(ModBlocks.PALM_PLANKS))
                        .group("signs").save(output, "palm_sign");
                hangingSignBuilder(ModItems.HANGING_PALM_SIGN, Ingredient.of(ModBlocks.STRIPPED_PALM_LOG))
                        .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_LOG), has(ModBlocks.STRIPPED_PALM_LOG))
                        .group("hanging_signs").save(output, "hanging_palm_sign");
                chestBoat(ModItems.PALM_CHEST_BOAT, ModItems.PALM_BOAT);
                woodenBoat(ModItems.PALM_BOAT, ModBlocks.PALM_PLANKS);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TENEBRIS_SAPLING, 2)
                        .pattern("S")
                        .pattern("L")
                        .define('S', Blocks.SHROOMLIGHT)
                        .define('L', ModBlocks.TENEBRIS_LEAVES)
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_LOG), has(ModBlocks.TENEBRIS_LOG))
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_SAPLING), has(ModBlocks.TENEBRIS_SAPLING))
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_LEAVES), has(ModBlocks.TENEBRIS_LEAVES))
                        .save(output, "tenebris_bud");
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TENEBRIS_WOOD, 3)
                        .pattern("LL")
                        .pattern("LL")
                        .define('L', ModBlocks.TENEBRIS_LOG)
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_LOG), has(ModBlocks.TENEBRIS_LOG))
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_WOOD), has(ModBlocks.TENEBRIS_WOOD))
                        .group("wood").save(output, "tenebris_wood");
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_TENEBRIS_WOOD, 3)
                        .pattern("LL")
                        .pattern("LL")
                        .define('L', ModBlocks.STRIPPED_TENEBRIS_LOG)
                        .unlockedBy(getHasName(ModBlocks.STRIPPED_TENEBRIS_LOG), has(ModBlocks.STRIPPED_TENEBRIS_LOG))
                        .unlockedBy(getHasName(ModBlocks.STRIPPED_TENEBRIS_WOOD), has(ModBlocks.STRIPPED_TENEBRIS_WOOD))
                        .group("wood").save(output, "stripped_tenebris_wood");
                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TENEBRIS_PLANKS, 4)
                        .requires(ModTags.Items.TENEBRIS_LOGS)
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_LOG), has(ModTags.Items.TENEBRIS_LOGS))
                        .group("planks").save(output, "tenebris_planks");
                stairBuilder(ModBlocks.TENEBRIS_STAIRS, Ingredient.of(ModBlocks.TENEBRIS_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_PLANKS), has(ModBlocks.TENEBRIS_PLANKS))
                        .group("wooden_stairs").save(output, "tenebris_stairs");
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TENEBRIS_SLAB, Ingredient.of(ModBlocks.TENEBRIS_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_PLANKS), has(ModBlocks.TENEBRIS_PLANKS))
                        .group("wooden_slabs").save(output, "tenebris_slab");
                fenceBuilder(ModBlocks.TENEBRIS_FENCE, Ingredient.of(ModBlocks.TENEBRIS_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_PLANKS), has(ModBlocks.TENEBRIS_PLANKS))
                        .group("wooden_fences").save(output, "tenebris_fence");
                fenceGateBuilder(ModBlocks.TENEBRIS_FENCE_GATE, Ingredient.of(ModBlocks.TENEBRIS_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_PLANKS), has(ModBlocks.TENEBRIS_PLANKS))
                        .group("wooden_fence_gates").save(output, "tenebris_fence_gate");
                pressurePlateBuilder(RecipeCategory.REDSTONE, ModBlocks.TENEBRIS_PRESSURE_PLATE, Ingredient.of(ModBlocks.TENEBRIS_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_PLANKS), has(ModBlocks.TENEBRIS_PLANKS))
                        .group("wooden_pressure_plates").save(output, "tenebris_pressure_plate");
                buttonBuilder(ModBlocks.TENEBRIS_BUTTON, Ingredient.of(ModBlocks.TENEBRIS_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_PLANKS), has(ModBlocks.TENEBRIS_PLANKS))
                        .group("wooden_buttons").save(output, "tenebris_button");
                doorBuilder(ModBlocks.TENEBRIS_DOOR, Ingredient.of(ModBlocks.TENEBRIS_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_PLANKS), has(ModBlocks.TENEBRIS_PLANKS))
                        .group("wooden_doors").save(output, "tenebris_door");
                trapdoorBuilder(ModBlocks.TENEBRIS_TRAPDOOR, Ingredient.of(ModBlocks.TENEBRIS_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_PLANKS), has(ModBlocks.TENEBRIS_PLANKS))
                        .group("wooden_trapdoors").save(output, "tenebris_trapdoor");
                shelf(ModBlocks.TENEBRIS_SHELF, ModBlocks.STRIPPED_TENEBRIS_LOG);
                signBuilder(ModItems.TENEBRIS_SIGN, Ingredient.of(ModBlocks.TENEBRIS_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.TENEBRIS_PLANKS), has(ModBlocks.TENEBRIS_PLANKS))
                        .group("signs").save(output, "tenebris_sign");
                hangingSignBuilder(ModItems.HANGING_TENEBRIS_SIGN, Ingredient.of(ModBlocks.STRIPPED_TENEBRIS_LOG))
                        .unlockedBy(getHasName(ModBlocks.STRIPPED_TENEBRIS_LOG), has(ModBlocks.STRIPPED_TENEBRIS_LOG))
                        .group("hanging_signs").save(output, "hanging_tenebris_sign");
            }

            public void oreSmoking(final List<ItemLike> smeltables, final RecipeCategory craftingCategory, final CookingBookCategory cookingCategory, final ItemLike result, final float experience, final int cookingTime, final String group) {
                this.oreCooking(SmokingRecipe::new, smeltables, craftingCategory, cookingCategory, result, experience, cookingTime, group, "_from_smoking");
            }
            public void campfireCooking(final ItemLike smeltable, final ItemLike result, final int cookingTime, final float experience, final String source) {
                this.simpleCookingRecipe(source, CampfireCookingRecipe::new, cookingTime, smeltable, result, experience);
            }
            public void campfireCooking(final List<ItemLike> smeltables, final ItemLike result, final int cookingTime, final float experience, final String source) {
                for (int i = 0; i < smeltables.size(); i++) {
                    ItemLike item = smeltables.get(i);
                    campfireCooking(item, result, cookingTime, experience, source + i);
                }
            }
            public void campfireCooking(final ItemLike smeltable, final ItemLike result, final int cookingTime, final String source) {
                this.simpleCookingRecipe(source, CampfireCookingRecipe::new, cookingTime, smeltable, result, 0.35f);
            }
            public void campfireCooking(final List<ItemLike> smeltables, final ItemLike result, final int cookingTime, final String source) {
                for (int i = 0; i < smeltables.size(); i++) {
                    ItemLike item = smeltables.get(i);
                    campfireCooking(item, result, cookingTime, source + i);
                }
            }
            public void campfireCooking(final ItemLike smeltable, final ItemLike result, final int cookingTime, final float experience) {
                this.simpleCookingRecipe("campfire_cooking", CampfireCookingRecipe::new, cookingTime, smeltable, result, experience);
            }
            public void campfireCooking(final List<ItemLike> smeltables, final ItemLike result, final int cookingTime, final float experience) {
                for (int i = 0; i < smeltables.size(); i++) {
                    ItemLike item = smeltables.get(i);
                    campfireCooking(item, result, cookingTime, experience, "campfire_cooking" + i);
                }
            }
            public void campfireCooking(final ItemLike smeltable, final ItemLike result, final int cookingTime) {
                this.simpleCookingRecipe("campfire_cooking", CampfireCookingRecipe::new, cookingTime, smeltable, result, 0.35f);
            }
            public void campfireCooking(final List<ItemLike> smeltables, final ItemLike result, final int cookingTime) {
                for (int i = 0; i < smeltables.size(); i++) {
                    ItemLike item = smeltables.get(i);
                    campfireCooking(item, result, cookingTime, "campfire_cooking" + i);
                }
            }
        };
    }

    @Override
    public String getName() {
        return "Recipes";
    }
}

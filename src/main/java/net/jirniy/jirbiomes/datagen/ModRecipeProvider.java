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
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                oreSmelting(List.of(Blocks.NETHERRACK), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, ModBlocks.NETHERSTONE, 0, 200, "netherstone");
                oreBlasting(List.of(Blocks.NETHERRACK), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, ModBlocks.NETHERSTONE, 0, 100, "netherstone");

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

                shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.IGNITED_BRIMSTONE, 4)
                        .requires(Blocks.MAGMA_BLOCK).requires(ModBlocks.BRIMSTONE)
                        .requires(ModBlocks.BRIMSTONE).requires(Blocks.MAGMA_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.BRIMSTONE), has(ModBlocks.BRIMSTONE))
                        .unlockedBy(getHasName(ModBlocks.IGNITED_BRIMSTONE), has(ModBlocks.IGNITED_BRIMSTONE))
                        .save(output, "ignited_brimstone");
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
                        .group("slabs").save(output, "polished_brimstone_wall");
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
                        .group("slabs").save(output, "brimstone_bricks_wall");
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
                        .group("slabs").save(output, "cracked_brimstone_bricks_wall");
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
                        .group("slabs").save(output, "mossy_brimstone_bricks_wall");
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
        };
    }

    @Override
    public String getName() {
        return "Recipes";
    }
}

package net.jirniy.jirbiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

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
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_GRATE, 4)
                        .pattern(" S ")
                        .pattern("S S")
                        .pattern(" S ")
                        .define('S', Items.IRON_INGOT)
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .unlockedBy(getHasName(ModBlocks.IRON_GRATE), has(ModBlocks.IRON_GRATE))
                        .save(output, "iron_grate");

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
            }
        };
    }

    @Override
    public String getName() {
        return "jirbiomes_recipes";
    }
}

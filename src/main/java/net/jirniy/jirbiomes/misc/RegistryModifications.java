package net.jirniy.jirbiomes.misc;

import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class RegistryModifications {
    public static void register() {
        JirniyBiomes.LOGGER.info("registering registry modifications for " + JirniyBiomes.MOD_ID);

        BlockTransformerHelper.registerTilling(ModBlocks.DRIED_DIRT, ModBlocks.DRY_FARMLAND);
        BlockTransformerHelper.registerTilling(ModBlocks.DRIED_GRASS_BLOCK, ModBlocks.DRY_FARMLAND);
        BlockTransformerHelper.registerTilling(ModBlocks.ROOTED_DRIED_DIRT, ModBlocks.DRIED_DIRT);
        BlockTransformerHelper.registerTilling(ModBlocks.WETLAND, ModBlocks.WET_FARMLAND);
        BlockTransformerHelper.registerTilling(ModBlocks.WET_GRASS_BLOCK, ModBlocks.WET_FARMLAND);
        BlockTransformerHelper.registerTilling(ModBlocks.ROOTED_WETLAND, ModBlocks.WETLAND);

        BlockTransformerHelper.registerTilling(ModBlocks.ROOTED_MUD, Blocks.MUD);
        BlockTransformerHelper.registerTilling(ModBlocks.ROOTED_SAND, Blocks.SAND);
        BlockTransformerHelper.registerTilling(ModBlocks.ROOTED_RED_SAND, Blocks.RED_SAND);

        BlockTransformerHelper.registerFlattening(ModBlocks.DRIED_DIRT, ModBlocks.DRIED_DIRT_PATH);
        BlockTransformerHelper.registerFlattening(ModBlocks.DRIED_GRASS_BLOCK, ModBlocks.DRIED_DIRT_PATH);
        BlockTransformerHelper.registerFlattening(ModBlocks.WETLAND, ModBlocks.WETLAND_PATH);
        BlockTransformerHelper.registerFlattening(ModBlocks.WET_GRASS_BLOCK, ModBlocks.WETLAND_PATH);

        BlockTransformerHelper.registerStripping(ModBlocks.GINKGO_LOG, ModBlocks.STRIPPED_GINKGO_LOG);
        BlockTransformerHelper.registerStripping(ModBlocks.GINKGO_WOOD, ModBlocks.STRIPPED_GINKGO_WOOD);
        BlockTransformerHelper.registerStripping(ModBlocks.PALM_LOG, ModBlocks.STRIPPED_PALM_LOG);
        BlockTransformerHelper.registerStripping(ModBlocks.PALM_WOOD, ModBlocks.STRIPPED_PALM_WOOD);
        BlockTransformerHelper.registerStripping(ModBlocks.TENEBRIS_LOG, ModBlocks.STRIPPED_TENEBRIS_LOG);
        BlockTransformerHelper.registerStripping(ModBlocks.TENEBRIS_WOOD, ModBlocks.STRIPPED_TENEBRIS_WOOD);

        BlockColorRegistry.register(List.of(BlockTintSources.grassBlock()), ModBlocks.DRIED_GRASS_BLOCK);
        BlockColorRegistry.register(List.of(BlockTintSources.grassBlock()), ModBlocks.WET_GRASS_BLOCK);

        BlockColorRegistry.register(List.of(BlockTintSources.foliage()), ModBlocks.APPLE_LEAVES);

        BlockColorRegistry.register(List.of(BlockTintSources.grass()), ModBlocks.SNOWY_BUSH);
        BlockColorRegistry.register(List.of(BlockTintSources.doubleTallGrass()), ModBlocks.CATTAIL);
        BlockColorRegistry.register(List.of(BlockTintSources.constant(0x208030)), ModBlocks.LOTUS_FLOWER);

//        CompostableRegistry.INSTANCE.add(ModItems.APPLE_SEEDS, 0.3f);
//        CompostableRegistry.INSTANCE.add(ModBlocks.GINKGO_LEAVES, 0.3f);
//        CompostableRegistry.INSTANCE.add(ModBlocks.GINKGO_SAPLING, 0.3f);
//        CompostableRegistry.INSTANCE.add(ModBlocks.PALM_LEAVES, 0.3f);
//        CompostableRegistry.INSTANCE.add(ModBlocks.PALM_SAPLING, 0.3f);
//        CompostableRegistry.INSTANCE.add(ModBlocks.APPLE_LEAVES, 0.5f);
//        CompostableRegistry.INSTANCE.add(ModBlocks.APPLE_OAK_SAPLING, 0.5f);
//        CompostableRegistry.INSTANCE.add(ModItems.BARREL_CACTUS, 0.3f);
//        CompostableRegistry.INSTANCE.add(ModItems.PRICKLY_PEAR, 0.3f);
//        CompostableRegistry.INSTANCE.add(ModBlocks.CATTAIL, 0.3f);
//        CompostableRegistry.INSTANCE.add(ModItems.LOTUS_FLOWER, 0.5f);

//        FuelValueEvents.BUILD.register((builder, context) -> {
//            final int BASE_TIME = context.baseSmeltTime();
//            builder.add(ModItems.DRIED_FIBERS, BASE_TIME/6);
//            builder.add(ModBlocks.STRAW_BLOCK, BASE_TIME);
//            builder.add(ModItems.TARR_BUCKET, BASE_TIME*160);
//            builder.add(ModTags.Items.WOODEN_PLATFORMS, BASE_TIME/2);
//        });

        FlammableBlockRegistry.getDefaultInstance().add(ModTags.Blocks.WOODEN_PLATFORMS, 20, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CATTAIL, 40, 100);
        FlammableBlockRegistry.getDefaultInstance().add(ModTags.Blocks.BARREL_CACTUSES, 40, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.LOTUS_FLOWER, 20, 40);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRAW_BLOCK, 40, 100);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PACKED_STRAW, 20, 40);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PACKED_STRAW_SLAB, 20, 40);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PACKED_STRAW_STAIRS, 20, 40);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.SOLID_TARR, 80, 1);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.SOLID_TARR_SLAB, 80, 1);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.SOLID_TARR_STAIRS, 80, 1);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.SOLID_TARR_WALL, 80, 1);

        Registry<Block> blockRegistry = BuiltInRegistries.BLOCK;
        blockRegistry.addAlias(JirniyBiomes.id("icicle"), JirniyBiomes.id("thick_icicle"));
        blockRegistry.addAlias(JirniyBiomes.id("polished_snow"), JirniyBiomes.id("polished_packed_snow"));
        blockRegistry.addAlias(JirniyBiomes.id("polished_snow_stairs"), JirniyBiomes.id("polished_packed_snow_stairs"));
        blockRegistry.addAlias(JirniyBiomes.id("polished_snow_slab"), JirniyBiomes.id("polished_packed_snow_slab"));
        blockRegistry.addAlias(JirniyBiomes.id("polished_snow_wall"), JirniyBiomes.id("polished_packed_snow_wall"));
        blockRegistry.addAlias(JirniyBiomes.id("snow_bricks"), JirniyBiomes.id("packed_snow_bricks"));
        blockRegistry.addAlias(JirniyBiomes.id("snow_bricks_stairs"), JirniyBiomes.id("packed_snow_bricks_stairs"));
        blockRegistry.addAlias(JirniyBiomes.id("snow_bricks_slab"), JirniyBiomes.id("packed_snow_bricks_slab"));
        blockRegistry.addAlias(JirniyBiomes.id("snow_bricks_wall"), JirniyBiomes.id("packed_snow_bricks_wall"));

        Registry<Item> itemRegistry = BuiltInRegistries.ITEM;
        itemRegistry.addAlias(JirniyBiomes.id("icicle"), JirniyBiomes.id("thick_icicle"));
        itemRegistry.addAlias(JirniyBiomes.id("polished_snow"), JirniyBiomes.id("polished_packed_snow"));
        itemRegistry.addAlias(JirniyBiomes.id("polished_snow_stairs"), JirniyBiomes.id("polished_packed_snow_stairs"));
        itemRegistry.addAlias(JirniyBiomes.id("polished_snow_slab"), JirniyBiomes.id("polished_packed_snow_slab"));
        itemRegistry.addAlias(JirniyBiomes.id("polished_snow_wall"), JirniyBiomes.id("polished_packed_snow_wall"));
        itemRegistry.addAlias(JirniyBiomes.id("snow_bricks"), JirniyBiomes.id("packed_snow_bricks"));
        itemRegistry.addAlias(JirniyBiomes.id("snow_bricks_stairs"), JirniyBiomes.id("packed_snow_bricks_stairs"));
        itemRegistry.addAlias(JirniyBiomes.id("snow_bricks_slab"), JirniyBiomes.id("packed_snow_bricks_slab"));
        itemRegistry.addAlias(JirniyBiomes.id("snow_bricks_wall"), JirniyBiomes.id("packed_snow_bricks_wall"));
    }
}

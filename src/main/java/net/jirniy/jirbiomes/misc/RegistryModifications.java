package net.jirniy.jirbiomes.misc;

import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class RegistryModifications {
    public static void register() {
        JirniyBiomes.LOGGER.info("registering block registry modifications for " + JirniyBiomes.MOD_ID);

        BlockTransformerHelper.registerTilling(ModBlocks.DRIED_DIRT, ModBlocks.DRY_FARMLAND);
        BlockTransformerHelper.registerTilling(ModBlocks.DRIED_GRASS_BLOCK, ModBlocks.DRIED_DIRT_PATH);
        BlockTransformerHelper.registerTilling(ModBlocks.ROOTED_DRIED_DIRT, ModBlocks.DRIED_DIRT);
        BlockTransformerHelper.registerTilling(ModBlocks.WETLAND, ModBlocks.WET_FARMLAND);
        BlockTransformerHelper.registerTilling(ModBlocks.WET_GRASS_BLOCK, ModBlocks.WETLAND_PATH);
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


        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CATTAIL, 40, 100);
        FlammableBlockRegistry.getDefaultInstance().add(ModTags.Blocks.BARREL_CACTUSES, 40, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.LOTUS_FLOWER, 20, 40);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRAW_BLOCK, 40, 100);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PACKED_STRAW, 20, 40);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PACKED_STRAW_SLAB, 20, 40);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PACKED_STRAW_STAIRS, 20, 40);
    }
}

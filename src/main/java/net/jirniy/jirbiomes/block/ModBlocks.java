package net.jirniy.jirbiomes.block;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.custom.CustomFarmlandBlock;
import net.jirniy.jirbiomes.block.custom.CustomGrassBlock;
import net.jirniy.jirbiomes.block.custom.CustomPathBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Stream;

public class ModBlocks {
    public static final Block IRON_GRATE = registerBlock("iron_grate", true, properties ->
            new WaterloggedTransparentBlock(properties.noOcclusion().strength(3f).requiresCorrectToolForDrops()
                    .isValidSpawn(Blocks::never).isRedstoneConductor(Blocks::never).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)
                    .mapColor(MapColor.METAL).pushReaction(PushReaction.NORMAL).sound(SoundType.COPPER_GRATE)));

    public static final Block DRIED_DIRT = registerBlock("dried_dirt", true, properties ->
            new Block(properties.strength(0.4f)
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block ROOTED_DRIED_DIRT = registerBlock("rooted_dried_dirt", true, properties ->
            new Block(properties.strength(0.4f)
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block COARSE_DRIED_DIRT = registerBlock("coarse_dried_dirt", true, properties ->
            new Block(properties.strength(0.4f)
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block DRIED_GRASS_BLOCK = registerBlock("dried_grass_block", true, properties ->
            new CustomGrassBlock(getKey(DRIED_DIRT), properties.strength(0.4f).randomTicks()
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block DRY_FARMLAND = registerBlock("dry_farmland", true, properties ->
            new CustomFarmlandBlock(DRIED_DIRT, properties.strength(0.4f).randomTicks()
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));
    public static final Block DRIED_DIRT_PATH = registerBlock("dried_dirt_path", true, properties ->
            new CustomPathBlock(DRIED_DIRT, properties.strength(0.6f).isViewBlocking(Blocks::always).isSuffocating(Blocks::always)
                    .mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.NORMAL).sound(SoundType.ROOTED_DIRT)));


    public static final Block WETLAND = registerBlock("wetland", true, properties ->
            new Block(properties.strength(0.6f).speedFactor(0.98f)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block ROOTED_WETLAND = registerBlock("rooted_wetland", true, properties ->
            new Block(properties.strength(0.6f).speedFactor(0.98f)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block COARSE_WETLAND = registerBlock("coarse_wetland", true, properties ->
            new Block(properties.strength(0.6f).speedFactor(0.98f)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block WET_GRASS_BLOCK = registerBlock("wet_grass_block", true, properties ->
            new CustomGrassBlock(getKey(WETLAND), properties.strength(0.6f).speedFactor(0.98f).randomTicks()
                    .mapColor(MapColor.GRASS).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block WET_FARMLAND = registerBlock("wet_farmland", true, properties ->
            new CustomFarmlandBlock(WETLAND, properties.strength(0.6f).randomTicks().speedFactor(0.98f)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));
    public static final Block WETLAND_PATH = registerBlock("wetland_path", true, properties ->
            new CustomPathBlock(WETLAND, properties.strength(0.8f).isViewBlocking(Blocks::always).isSuffocating(Blocks::always)
                    .mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.NORMAL).sound(SoundType.WET_GRASS)));

    private static Block registerBlock(String name, boolean addItem, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, JirniyBiomes.id(name))));
        if (addItem) {
            registerBlockItem(name, toRegister);
        }
        return Registry.register(BuiltInRegistries.BLOCK, JirniyBiomes.id(name), toRegister);
    }

    public static ResourceKey<Block> getKey(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }
    public static ResourceKey<Block>[] getKeys(Block... blocks) {
        ResourceKey<Block>[] keys = new ResourceKey[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            keys[i] = getKey(blocks[i]);
        }
        return keys;
    }

    private static void registerBlockItem(String name, Block block) {
        Item item = Registry.register(BuiltInRegistries.ITEM, JirniyBiomes.id(name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, JirniyBiomes.id(name)))));
        CreativeModeTabEvents.modifyOutputEvent(ResourceKey.create(Registries.CREATIVE_MODE_TAB, JirniyBiomes.id("jirbiomes_tab"))).register(output -> {
            output.accept(item);
        });
    }
    public static void register() {
        JirniyBiomes.LOGGER.info("registering blocks for " + JirniyBiomes.MOD_ID);
    }
}

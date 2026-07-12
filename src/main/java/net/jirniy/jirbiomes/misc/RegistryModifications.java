package net.jirniy.jirbiomes.misc;

import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.jirniy.jirbiomes.JirniyBiomes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.item.ModItems;
import net.minecraft.client.color.block.BlockTintSources;

import java.util.List;

public class RegistryModifications {
    public static void register() {
        JirniyBiomes.LOGGER.info("registering block registry modifications for " + JirniyBiomes.MOD_ID);

        TillableBlockRegistry.register(ModBlocks.DRIED_DIRT,
                itemUsageContext -> !itemUsageContext.isSecondaryUseActive(),
                ModBlocks.DRY_FARMLAND.defaultBlockState());
        TillableBlockRegistry.register(ModBlocks.DRIED_GRASS_BLOCK,
                itemUsageContext -> !itemUsageContext.isSecondaryUseActive(),
                ModBlocks.DRY_FARMLAND.defaultBlockState());
        TillableBlockRegistry.register(ModBlocks.ROOTED_DRIED_DIRT,
                itemUsageContext -> !itemUsageContext.isSecondaryUseActive(),
                ModBlocks.DRIED_DIRT.defaultBlockState());
        TillableBlockRegistry.register(ModBlocks.WETLAND,
                itemUsageContext -> !itemUsageContext.isSecondaryUseActive(),
                ModBlocks.WET_FARMLAND.defaultBlockState());
        TillableBlockRegistry.register(ModBlocks.WET_GRASS_BLOCK,
                itemUsageContext -> !itemUsageContext.isSecondaryUseActive(),
                ModBlocks.WET_FARMLAND.defaultBlockState());
        TillableBlockRegistry.register(ModBlocks.ROOTED_WETLAND,
                itemUsageContext -> !itemUsageContext.isSecondaryUseActive(),
                ModBlocks.WETLAND.defaultBlockState());

        FlattenableBlockRegistry.register(ModBlocks.DRIED_DIRT, ModBlocks.DRIED_DIRT_PATH.defaultBlockState());
        FlattenableBlockRegistry.register(ModBlocks.DRIED_GRASS_BLOCK, ModBlocks.DRIED_DIRT_PATH.defaultBlockState());
        FlattenableBlockRegistry.register(ModBlocks.WETLAND, ModBlocks.WETLAND_PATH.defaultBlockState());
        FlattenableBlockRegistry.register(ModBlocks.WET_GRASS_BLOCK, ModBlocks.WETLAND_PATH.defaultBlockState());

        BlockColorRegistry.register(List.of(BlockTintSources.grassBlock()), ModBlocks.DRIED_GRASS_BLOCK);
        BlockColorRegistry.register(List.of(BlockTintSources.grassBlock()), ModBlocks.WET_GRASS_BLOCK);

        CompostableRegistry.INSTANCE.add(ModItems.APPLE_SEEDS, 0.2f);
    }
}

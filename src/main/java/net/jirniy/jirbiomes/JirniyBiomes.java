package net.jirniy.jirbiomes;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.item.ModCreativeModeTabs;
import net.jirniy.jirbiomes.item.ModItems;
import net.minecraft.resources.Identifier;

import net.minecraft.world.InteractionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JirniyBiomes implements ModInitializer {
	public static final String MOD_ID = "jirbiomes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.register();
		ModBlocks.register();
		ModCreativeModeTabs.register();

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
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}

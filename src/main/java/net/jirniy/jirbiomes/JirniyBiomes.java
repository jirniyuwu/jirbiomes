package net.jirniy.jirbiomes;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.item.ModCreativeModeTabs;
import net.jirniy.jirbiomes.item.ModItems;
import net.jirniy.jirbiomes.misc.BlockRegistryModifications;
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

		BlockRegistryModifications.register();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}

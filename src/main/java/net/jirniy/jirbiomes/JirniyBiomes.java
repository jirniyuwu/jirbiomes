package net.jirniy.jirbiomes;

import net.fabricmc.api.ModInitializer;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.effect.ModEffects;
import net.jirniy.jirbiomes.entity.ModEntities;
import net.jirniy.jirbiomes.entity.ModModelLayers;
import net.jirniy.jirbiomes.item.ModCreativeModeTabs;
import net.jirniy.jirbiomes.item.ModItems;
import net.jirniy.jirbiomes.misc.RegistryModifications;
import net.jirniy.jirbiomes.particle.ModParticles;
import net.jirniy.jirbiomes.worldgen.ModBiomeModifications;
import net.jirniy.jirbiomes.worldgen.blockstate.ModBlockStateProviderType;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JirniyBiomes implements ModInitializer {
	public static final String MOD_ID = "jirbiomes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlockStateProviderType.register();

		ModItems.register();
		ModBlocks.register();
		ModCreativeModeTabs.register();
		ModParticles.register();
		ModEntities.register();
		ModModelLayers.register();
		ModEffects.registerEffects();

		ModBiomeModifications.register();
		RegistryModifications.register();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}

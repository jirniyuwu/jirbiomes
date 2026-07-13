package net.jirniy.jirbiomes;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.jirniy.jirbiomes.block.ModBlocks;
import net.jirniy.jirbiomes.item.ModCreativeModeTabs;
import net.jirniy.jirbiomes.item.ModItems;
import net.jirniy.jirbiomes.misc.RegistryModifications;
import net.jirniy.jirbiomes.particle.ModParticles;
import net.jirniy.jirbiomes.worldgen.ModConfiguredFeatures;
import net.jirniy.jirbiomes.worldgen.blockstate.ModBlockStateProviderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
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

		RegistryModifications.register();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}

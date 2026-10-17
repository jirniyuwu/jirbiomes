package net.jirniy.jirbiomes;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.jirniy.jirbiomes.datagen.*;
import net.jirniy.jirbiomes.misc.ModDamageTypes;
import net.jirniy.jirbiomes.worldgen.ModFeatures;
import net.jirniy.jirbiomes.worldgen.ModPlacements;
import net.jirniy.jirbiomes.worldgen.biome.ModBiomes;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class JirniyBiomesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModItemTagsProvider::new);
		pack.addProvider(ModEntityTagsProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModRegistryDataProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.FEATURE, ModFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacements::bootstrap);
		registryBuilder.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);
		registryBuilder.add(Registries.BIOME, ModBiomes::bootstrap);
	}
}

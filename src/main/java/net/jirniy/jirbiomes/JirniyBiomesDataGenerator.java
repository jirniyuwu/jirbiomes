package net.jirniy.jirbiomes;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.jirniy.jirbiomes.datagen.*;
import net.jirniy.jirbiomes.misc.ModDamageTypes;
import net.jirniy.jirbiomes.worldgen.ModFeatures;
import net.jirniy.jirbiomes.worldgen.ModPlacements;
import net.jirniy.jirbiomes.worldgen.biome.ModBiomes;
import net.jirniy.jirbiomes.worldgen.blockstate.ModBlockStateProviderType;
import net.jirniy.jirbiomes.worldgen.feature.ModFeatureTypes;
import net.jirniy.jirbiomes.worldgen.feature.ModFoliagePlacerTypes;
import net.jirniy.jirbiomes.worldgen.feature.ModTreeDecoratorTypes;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class JirniyBiomesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		ModFoliagePlacerTypes.register();
		ModTreeDecoratorTypes.register();
		ModBlockStateProviderType.register();
		ModFeatureTypes.register();

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
		registryBuilder.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);

		registryBuilder.add(Registries.FEATURE, ModFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacements::bootstrap);
		registryBuilder.add(Registries.BIOME, ModBiomes::bootstrap);
	}
}

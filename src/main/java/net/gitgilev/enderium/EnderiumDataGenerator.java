package net.gitgilev.enderium;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.gitgilev.enderium.worldgen.feature.EnderiumConfiguredFeatures;
import net.gitgilev.enderium.worldgen.feature.EnderiumPlacedFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class EnderiumDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(EnderiumDynamicRegistryProvider::new);

	}
	@Override
	public void buildRegistry(RegistrySetBuilder builder) {
		builder.add(Registries.CONFIGURED_FEATURE, EnderiumConfiguredFeatures::bootstrap);
		builder.add(Registries.PLACED_FEATURE, EnderiumPlacedFeatures::bootstrap);
	}

}

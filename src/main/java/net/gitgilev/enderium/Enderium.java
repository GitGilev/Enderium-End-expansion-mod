package net.gitgilev.enderium;


import com.mojang.serialization.Codec;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.fabricmc.api.ModInitializer;


import net.gitgilev.enderium.registry.EnderiumBlocks;
import net.gitgilev.enderium.worldgen.EnderiumBiomes;

import net.gitgilev.enderium.worldgen.EnderiumFeaturesGeneration;
import net.gitgilev.enderium.worldgen.feature.EnderiumFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Climate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static foundry.veil.Veil.MODID;
import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public class Enderium implements ModInitializer {
	public static final String MOD_ID = "enderium";
	private static Boolean initialized = false;
	private static final ArrayList<Runnable> runnables = new ArrayList<>(1);

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static void callbackWhenInitialized(Runnable callback) {
		if (initialized) {
			callback.run();
		} else {
			runnables.add(callback);
		}
	}
	@Override
	public void onInitialize() {

		BiomePlacement.addEnd(EnderiumBiomes.CUBIARC,
				new Climate.ParameterPoint(
						Climate.Parameter.span(-1.0f, -0.15f),//temperature
						Climate.Parameter.span(-1.0f, -0.35f),//humidity
						Climate.Parameter.span(0.3f, 0.4f),//continentalness
						Climate.Parameter.span(-0.375f, 0.04f),//erosion
						Climate.Parameter.point(0.0f),//depth
						Climate.Parameter.span(0.0f, 0.2f),//weirdness
						0L));//offset

// At this point Traverse is completely initialized.
		initialized = true;
		for (Runnable callback : runnables) {
			callback.run();
		}
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		EnderiumBlocks.registerModBlocks();
		EnderiumFeatures.register();
		EnderiumFeaturesGeneration.register();
		LOGGER.info("Hello Fabric world!");
	}
	public static ResourceLocation path(String path) {
		return new ResourceLocation(MODID, path);
	}
}
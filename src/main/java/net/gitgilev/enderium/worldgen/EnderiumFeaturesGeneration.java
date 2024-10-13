package net.gitgilev.enderium.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.gitgilev.enderium.Enderium;

import net.gitgilev.enderium.worldgen.feature.EnderiumPlacedFeatures;
import net.minecraft.world.level.levelgen.GenerationStep;

public class EnderiumFeaturesGeneration {
    public static void register() {

        generateFeatures();

    }
    public static void generateFeatures() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(EnderiumBiomes.CUBIARC),
                GenerationStep.Decoration.SURFACE_STRUCTURES,
                EnderiumPlacedFeatures.CUBE_PLACE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(EnderiumBiomes.CUBIARC),
                GenerationStep.Decoration.SURFACE_STRUCTURES,
                EnderiumPlacedFeatures.CUBE_SMALL_PLACE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(EnderiumBiomes.CUBIARC),
                GenerationStep.Decoration.SURFACE_STRUCTURES,
                EnderiumPlacedFeatures.CUBE_BIG_PLACE);
    }
}

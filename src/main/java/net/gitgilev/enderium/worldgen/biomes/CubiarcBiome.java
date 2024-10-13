package net.gitgilev.enderium.worldgen.biomes;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import net.minecraft.world.level.biome.AmbientAdditionsSettings;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class CubiarcBiome {
    public static final Climate.ParameterPoint NOISE_POINT = Climate.parameters(0.35F, 0.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.225F);

    public static Biome create(FabricDynamicRegistryProvider.Entries entries) {
        return new Biome.BiomeBuilder()
                .generationSettings(createGenerationSettings(entries))
                .mobSpawnSettings(createSpawnSettings())
                .hasPrecipitation(false)
                .temperature(2.0F)
                .downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .skyColor(2)
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(2297392)
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.WARPED_SPORE, 0.01428F))
                        .ambientLoopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
                        .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D))
                        .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111D))
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.AMBIENT_CAVE))
                        .build())
                .build();
    }

    private static BiomeGenerationSettings createGenerationSettings(FabricDynamicRegistryProvider.Entries entries) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(entries.placedFeatures(), entries.configuredCarvers());

        // DEFAULT MINECRAFT FEATURES
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, EndPlacements.END_SPIKE);
        BiomeDefaultFeatures.addNetherDefaultOres(builder);



        return builder.build();
    }

    private static MobSpawnSettings createSpawnSettings() {
        MobSpawnSettings.Builder builder = new MobSpawnSettings.Builder();

        // SPAWNS
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 60, 1, 2));

        return builder.build();
    }
}

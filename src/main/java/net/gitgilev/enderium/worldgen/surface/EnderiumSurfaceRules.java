package net.gitgilev.enderium.worldgen.surface;

import net.gitgilev.enderium.Enderium;
import net.gitgilev.enderium.mixin.access.NoiseGeneratorSettingsAccess;
import net.gitgilev.enderium.worldgen.EnderiumBiomes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;
import static net.minecraft.world.level.levelgen.SurfaceRules.sequence;

public class EnderiumSurfaceRules {
    public static SurfaceRules.RuleSource createRules() {
        SurfaceRules.ConditionSource is_cubiarc = SurfaceRules.isBiome(EnderiumBiomes.CUBIARC);
        SurfaceRules.RuleSource cubiarc = SurfaceRules.ifTrue(
                is_cubiarc,
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(
                                SurfaceRules.yBlockCheck(VerticalAnchor.aboveBottom(50), 0),
                                block(Blocks.RED_TERRACOTTA)
                        )
                )
        );
        RuleSource test = ifTrue(is_cubiarc, sequence(
                ifTrue(ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(Noises.NETHER_WART, 1.17),
                                        block(Blocks.TERRACOTTA)), //normal terracota don't spawn for some reason
                                block(Blocks.RED_TERRACOTTA))),
                block(Blocks.GREEN_TERRACOTTA)));


        // Return a surface-only sequence of our surface rules
        return SurfaceRules.sequence(

                cubiarc,
                test

        );
    }

    private static SurfaceRules.RuleSource block(Block b) {
        return SurfaceRules.state(b.defaultBlockState());
    }


    public static void init() {
    }
    // Define a condition that forces cubic terrain based on height intervals
    private static SurfaceRules.ConditionSource cubeTerrainCondition(int minHeight, int maxHeight) {
        // Forces sharp cutoffs between height ranges, creating cube-like terrain blocks
        return
                SurfaceRules.yBlockCheck(VerticalAnchor.aboveBottom(minHeight), 0)
        ;
    }
    public static void addModMaterialRules(MinecraftServer server, ResourceKey<LevelStem> dimensionKey) {
        LevelStem levelStem = server.registries().compositeAccess()
                .registryOrThrow(Registries.LEVEL_STEM).get(dimensionKey);
        if (levelStem == null) {
            Enderium.LOGGER.error("Couldn't find the End noise generation provider");
            return;
        }

        ChunkGenerator chunkGenerator = levelStem.generator();
        boolean hasEndBiomes = chunkGenerator.getBiomeSource().possibleBiomes().stream().anyMatch(
                biomeHolder -> biomeHolder.unwrapKey().orElseThrow().location().getNamespace().equals(Enderium.MOD_ID));
        if (hasEndBiomes) {
            if (chunkGenerator instanceof NoiseBasedChunkGenerator noiseGenerator) {
                NoiseGeneratorSettings settings = noiseGenerator.generatorSettings().value();
                ((NoiseGeneratorSettingsAccess) (Object) settings).addSurfaceRule(
                        SurfaceRules.sequence(
                                EnderiumSurfaceRules.createRules(), settings.surfaceRule()
                        )
                );
                Enderium.LOGGER.info("Successfully added Surface Rules for the End");
            }
        }
    }

}

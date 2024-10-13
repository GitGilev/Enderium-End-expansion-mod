package net.gitgilev.enderium.worldgen;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.gitgilev.enderium.Enderium;
import net.gitgilev.enderium.worldgen.biomes.CubiarcBiome;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import java.util.List;

public class EnderiumBiomes {

        public static final ResourceKey<Biome> CUBIARC = ResourceKey.create(Registries.BIOME, ResourceLocation.tryBuild(Enderium.MOD_ID, "cubiarc"));


        public static final List<ResourceKey<Biome>> BIOMES = List.of(
                CUBIARC
        );

        public static void populate(FabricDynamicRegistryProvider.Entries entries) {
            entries.add(CUBIARC, CubiarcBiome.create(entries));

        }

        // Required because something referencing this class has to be called in order to force the
        // static initializer to run (Minecraft's Bootstrap class does similar things)
        public static void init() {

        }
    }


package net.gitgilev.enderium.worldgen.feature;

import net.gitgilev.enderium.Enderium;
import net.gitgilev.enderium.worldgen.feature.cubiarc.CubeFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class EnderiumFeatures {
    public static void register() {
        Enderium.LOGGER.info("Registering Features for modid : " + Enderium.MOD_ID);


        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(Enderium.MOD_ID, "cube_cubiarc"), CubeFeature.INSTANCE);
    }
}

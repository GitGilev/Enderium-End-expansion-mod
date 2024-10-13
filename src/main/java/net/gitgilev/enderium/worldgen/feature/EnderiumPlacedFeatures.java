package net.gitgilev.enderium.worldgen.feature;
import net.gitgilev.enderium.Enderium;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import java.util.List;

public class EnderiumPlacedFeatures {
    public static ResourceKey<PlacedFeature> create(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Enderium.MOD_ID, name));
    }
    private static void register(BootstapContext<PlacedFeature> context,
                                 ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> config,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context,
                                 ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> config,
                                 PlacementModifier... modifiers) {
        register(context, key, config, List.of(modifiers));
    }
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var configLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, CUBE_PLACE, configLookup.getOrThrow(EnderiumConfiguredFeatures.CUBE_BIG),
                InSquarePlacement.spread(),
                //PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomeFilter.biome(),
                PlacementUtils.FULL_RANGE,
                RarityFilter.onAverageOnceEvery(6)
        );
        register(context, CUBE_SMALL_PLACE, configLookup.getOrThrow(EnderiumConfiguredFeatures.CUBE_SMALL),
                InSquarePlacement.spread(),
                //PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomeFilter.biome(),
                PlacementUtils.FULL_RANGE,
                RarityFilter.onAverageOnceEvery(6)
        );
        register(context, CUBE_BIG_PLACE, configLookup.getOrThrow(EnderiumConfiguredFeatures.CUBE),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(2)
        );
    }
    public static final ResourceKey<PlacedFeature> CUBE_PLACE = create("cube_cubiarc");
    public static final ResourceKey<PlacedFeature> CUBE_SMALL_PLACE = create("cube_small_cubiarc");
    public static final ResourceKey<PlacedFeature> CUBE_BIG_PLACE = create("cube_big_cubiarc");
}

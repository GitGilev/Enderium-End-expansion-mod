package net.gitgilev.enderium.worldgen.feature;
import net.gitgilev.enderium.Enderium;
import net.gitgilev.enderium.worldgen.feature.cubiarc.CubeFeature;
import net.gitgilev.enderium.worldgen.feature.cubiarc.config.CubeFeatureConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import java.util.List;
public class EnderiumConfiguredFeatures {
    public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Enderium.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                   ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, CUBE_BIG, CubeFeature.INSTANCE,
                new CubeFeatureConfig(
                        UniformInt.of(5, 10),  // Cube sizes
                        UniformInt.of(1, 3),  // Number of cubes
                        new WeightedStateProvider(
                                SimpleWeightedRandomList.<BlockState>builder()
                                        .add(Blocks.END_STONE.defaultBlockState(), 5)  // Common block
                                        .add(Blocks.PURPUR_BLOCK.defaultBlockState(), 2)  // Less common
                                        .add(Blocks.OBSIDIAN.defaultBlockState(), 1)  // Rare block
                        )
                )
        );
        register(context, CUBE_SMALL, CubeFeature.INSTANCE,
                new CubeFeatureConfig(
                        UniformInt.of(3, 5),  // Cube sizes
                        UniformInt.of(1, 5),  // Number of cubes
                        new WeightedStateProvider(
                                SimpleWeightedRandomList.<BlockState>builder()
                                        .add(Blocks.END_STONE.defaultBlockState(), 5)  // Common block
                                        .add(Blocks.PURPUR_BLOCK.defaultBlockState(), 2)  // Less common
                                        .add(Blocks.OBSIDIAN.defaultBlockState(), 1)  // Rare block
                        )
                )
        );
        register(context, CUBE, CubeFeature.INSTANCE,
                new CubeFeatureConfig(
                        UniformInt.of(1, 7),  // Cube sizes
                        UniformInt.of(1, 1),  // Number of cubes
                        new WeightedStateProvider(
                                SimpleWeightedRandomList.<BlockState>builder()
                                        .add(Blocks.END_STONE.defaultBlockState(), 5)  // Common block
                                        .add(Blocks.PURPUR_BLOCK.defaultBlockState(), 2)  // Less common
                                        .add(Blocks.OBSIDIAN.defaultBlockState(), 1)  // Rare block
                        )
                )
        );
    }
    public static final ResourceKey<ConfiguredFeature<?, ?>> CUBE_BIG = create("cube_big_cubiarc");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CUBE = create("cube_cubiarc");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CUBE_SMALL = create("cube_small_cubiarc");
}

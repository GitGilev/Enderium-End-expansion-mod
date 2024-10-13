package net.gitgilev.enderium.worldgen.feature.cubiarc.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record CubeFeatureConfig(IntProvider size, IntProvider cubeCount, BlockStateProvider block) implements FeatureConfiguration {
    public static Codec<CubeFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    IntProvider.POSITIVE_CODEC.fieldOf("size").forGetter(CubeFeatureConfig::size),
                    IntProvider.POSITIVE_CODEC.fieldOf("cubeCount").forGetter(CubeFeatureConfig::cubeCount),
                    BlockStateProvider.CODEC.fieldOf("block").forGetter(CubeFeatureConfig::block)
            ).apply(instance, CubeFeatureConfig::new));
}

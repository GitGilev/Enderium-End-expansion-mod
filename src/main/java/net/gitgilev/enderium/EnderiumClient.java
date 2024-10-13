package net.gitgilev.enderium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gitgilev.enderium.registry.EnderiumBlocks;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.gitgilev.enderium.client.render.RaincubeBlockEntityRenderer;
@Environment(EnvType.CLIENT)
public class EnderiumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(EnderiumBlocks.RAINCUBE_S, RaincubeBlockEntityRenderer::new);
    }
}

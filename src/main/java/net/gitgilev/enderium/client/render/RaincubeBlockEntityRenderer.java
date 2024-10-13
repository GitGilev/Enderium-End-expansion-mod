package net.gitgilev.enderium.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.shader.program.ShaderProgram;
import net.gitgilev.enderium.Enderium;
import net.gitgilev.enderium.blockentity.RaincubeBlockEntity;
import net.gitgilev.enderium.registry.EnderiumRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class RaincubeBlockEntityRenderer implements BlockEntityRenderer<RaincubeBlockEntity> {
    public RaincubeBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        // You can initialize necessary components here if needed
    }
    @Override
    public void render(RaincubeBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        RenderType renderType = EnderiumRenderTypes.raincube();
        ShaderProgram shader = VeilRenderSystem.getShader();
        renderType.setupRenderState();
        if (shader == null) {
            renderType.clearRenderState();
            return;
        }
    }
}

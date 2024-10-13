package net.gitgilev.enderium.registry;

import net.gitgilev.enderium.Enderium;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import foundry.veil.api.client.render.VeilRenderBridge;

public class EnderiumRenderTypes extends RenderType {
    private static final RenderStateShard.ShaderStateShard RAINCUBE_SHADER = VeilRenderBridge.shaderState(Enderium.path("raincube"));
    private static final RenderType RAINCUBE = create(
            "raincube",
            DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP,
            VertexFormat.Mode.QUADS,
            TRANSIENT_BUFFER_SIZE,
            true,
            false,
            RenderType.CompositeState.builder()
                    .setLightmapState(LIGHTMAP)
                    .setShaderState(RAINCUBE_SHADER)
                    .setLayeringState(POLYGON_OFFSET_LAYERING)
                    .createCompositeState(true));
    public EnderiumRenderTypes(String name, VertexFormat vertexFormat, VertexFormat.Mode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }
    public static RenderType raincube() {
        return RAINCUBE;
    }

}

package net.gitgilev.enderium.mixin;


import net.gitgilev.enderium.worldgen.surface.EnderiumSurfaceRules;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.level.dimension.LevelStem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(method = "createLevels", at = @At("RETURN"))
    private void createEPLevels(ChunkProgressListener worldGenerationProgressListener, CallbackInfo ci) {
        EnderiumSurfaceRules.addModMaterialRules((MinecraftServer) (Object) this, LevelStem.END);
    }
}

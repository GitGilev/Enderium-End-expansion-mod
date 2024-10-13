package net.gitgilev.enderium.blockentity;

import net.gitgilev.enderium.registry.EnderiumBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RaincubeBlockEntity extends BlockEntity {
    public RaincubeBlockEntity(BlockPos pos, BlockState state) {
        super(EnderiumBlocks.RAINCUBE_S, pos, state);
    }
}

package net.gitgilev.enderium.block;

import net.gitgilev.enderium.blockentity.RaincubeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;


public class RaincubeBlock extends Block implements EntityBlock {

    public RaincubeBlock(Properties settings) {
        super(settings);
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RaincubeBlockEntity(pos, state);
    }
}

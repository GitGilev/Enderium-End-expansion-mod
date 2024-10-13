package net.gitgilev.enderium.worldgen.feature.cubiarc;

import com.mojang.serialization.Codec;
import net.gitgilev.enderium.worldgen.feature.cubiarc.config.CubeFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CubeFeature extends Feature<CubeFeatureConfig> {
    public static final Feature<CubeFeatureConfig> INSTANCE = new CubeFeature(CubeFeatureConfig.CODEC);


    public CubeFeature(Codec<CubeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CubeFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        CubeFeatureConfig config = context.config();


        List<BlockPos> toPlace = new ArrayList<>();
        int cubeCount = config.cubeCount().sample(random) + 10;  // Increase the cube count by 10 to generate more cubes

        for (int i = 0; i < cubeCount; i++) {
            int size = config.size().sample(random);  // Get random size for the cube
            BlockPos cubeOrigin = this.getHorizontalEdgePosition(world, origin, random);  // Find position on horizontal edge
            if (cubeOrigin != null) {
                this.generateCube(toPlace, cubeOrigin, size);
            }
        }

        for (BlockPos place : toPlace) {
            this.safeSetBlock(world, place, config.block().getState(random, place), block -> block.isAir() || block.canBeReplaced());
        }

        return true;
    }

    // Find a position on horizontal side of a block, ensuring air for 3 blocks ahead and height < 52
    private BlockPos getHorizontalEdgePosition(WorldGenLevel world, BlockPos origin, RandomSource random) {
        BlockPos pos = origin.mutable();

        if (pos.getY() >= 200) {  // Restrict to height less than 52
            return null;
        }

        Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};  // Horizontal directions

        // Iterate over horizontal directions
        for (Direction dir : directions) {
            BlockPos checkPos = pos.relative(dir);  // Check adjacent block in the horizontal direction
            // Check if the next 3 blocks in the direction are air and within height constraint
            if (world.getBlockState(checkPos).isAir()
                    && world.getBlockState(checkPos.relative(dir)).isAir()
                    && world.getBlockState(checkPos.relative(dir, 2)).isAir()) {

                // Shift the cube slightly into the block by moving it back by one block in the opposite direction
                return checkPos.relative(dir.getOpposite()).below(random.nextInt(2));  // Optional downward adjustment for more variation
            }
        }

        return null;  // Return null if no suitable position is found
    }

    // Generate a cube at a given position
    private void generateCube(List<BlockPos> toPlace, BlockPos origin, int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    toPlace.add(origin.offset(x, y, z));
                }
            }
        }
    }

    // Set the block state if the condition is met
    public void safeSetBlock(WorldGenLevel world, BlockPos pos, BlockState state, Predicate<BlockState> condition) {
        if (condition.test(world.getBlockState(pos))) {
            world.setBlock(pos, state, 3);
        }
    }


}

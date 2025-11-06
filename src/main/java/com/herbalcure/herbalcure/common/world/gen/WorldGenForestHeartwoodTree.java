package com.herbalcure.herbalcure.common.world.gen;

import com.herbalcure.herbalcure.common.registry.ModRegistries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

/**
 * World generator for Forest Heartwood Tree
 * Generates a tree structure using Forest Heartwood Log and Forest Heartwood Leaves
 */
public class WorldGenForestHeartwoodTree extends WorldGenAbstractTree
{
    public WorldGenForestHeartwoodTree(boolean notify)
    {
        super(notify);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        // Random tree parameters
        int trunkHeight = rand.nextInt(2) + 4; // Trunk height: 4-5 blocks (random)
        int middleLeavesLayers = rand.nextFloat() < 0.5f ? 3 : 4; // 50% chance: 3-5 layers or 3-6 layers (3 or 4 middle layers)
        
        // Tree structure: trunk + 1 (top) + middle layers + 1 (bottom log) = total leaves layers
        // Note: bottom layer is just a log, not leaves
        int totalLeavesLayers = 1 + middleLeavesLayers + 1; // Top layer + middle layers + bottom log layer
        int totalHeight = trunkHeight + totalLeavesLayers;

        // Check if there's enough space
        if (position.getY() >= 1 && position.getY() + totalHeight <= worldIn.getHeight())
        {
            // Check if the ground is suitable
            if (!isSoil(worldIn, position.down(), Blocks.GRASS) && !isSoil(worldIn, position.down(), Blocks.DIRT))
            {
                return false;
            }

            // Check if there's enough space above (5x5 area needed for leaves)
            for (int y = 0; y <= totalHeight; ++y)
            {
                int checkRadius = 2; // 5x5 area (radius 2)
                if (y == 0)
                {
                    checkRadius = 0; // Ground level
                }
                else if (y <= trunkHeight)
                {
                    checkRadius = 0; // Trunk area
                }

                for (int x = -checkRadius; x <= checkRadius; ++x)
                {
                    for (int z = -checkRadius; z <= checkRadius; ++z)
                    {
                        BlockPos checkPos = position.add(x, y, z);
                        IBlockState state = worldIn.getBlockState(checkPos);
                        Block block = state.getBlock();

                        if (!block.isAir(state, worldIn, checkPos) && 
                            !block.isLeaves(state, worldIn, checkPos) && 
                            block != Blocks.VINE && 
                            block != Blocks.GRASS && 
                            block != Blocks.TALLGRASS)
                        {
                            return false;
                        }
                    }
                }
            }

            // Generate the tree
            if (ModRegistries.blockForestHeartwoodLog == null || ModRegistries.blockForestHeartwoodLeaves == null)
            {
                return false;
            }
            
            IBlockState logState = ModRegistries.blockForestHeartwoodLog.getDefaultState()
                .withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
            IBlockState leavesState = ModRegistries.blockForestHeartwoodLeaves.getDefaultState()
                .withProperty(net.minecraft.block.BlockLeaves.DECAYABLE, Boolean.valueOf(true))
                .withProperty(net.minecraft.block.BlockLeaves.CHECK_DECAY, Boolean.valueOf(true));

            // Place trunk logs
            for (int y = 0; y < trunkHeight; ++y)
            {
                BlockPos logPos = position.up(y);
                IBlockState state = worldIn.getBlockState(logPos);
                if (state.getBlock().isAir(state, worldIn, logPos) || 
                    state.getBlock().isLeaves(state, worldIn, logPos))
                {
                    this.setBlockAndNotifyAdequately(worldIn, logPos, logState);
                }
            }

            // Place leaves from top to bottom
            // Top layer (layer 0) - only center leaf
            BlockPos topLayerPos = position.up(trunkHeight + totalLeavesLayers - 1);
            this.placeLeavesAt(worldIn, topLayerPos, leavesState);

            // Second layer (layer 1) - cross pattern
            BlockPos secondLayerPos = position.up(trunkHeight + totalLeavesLayers - 2);
            this.placeLeavesAt(worldIn, secondLayerPos.add(0, 0, 0), leavesState); // Center (will be replaced by log)
            this.placeLeavesAt(worldIn, secondLayerPos.add(-1, 0, 0), leavesState);
            this.placeLeavesAt(worldIn, secondLayerPos.add(1, 0, 0), leavesState);
            this.placeLeavesAt(worldIn, secondLayerPos.add(0, 0, -1), leavesState);
            this.placeLeavesAt(worldIn, secondLayerPos.add(0, 0, 1), leavesState);
            // Place log in center
            this.setBlockAndNotifyAdequately(worldIn, secondLayerPos, logState);

            for (int layer = 0; layer < middleLeavesLayers; ++layer)
            {
                BlockPos layerPos = position.up((trunkHeight + totalLeavesLayers - 3) - layer);
                
                // Center cross (will be replaced by log in center)
                this.placeLeavesAt(worldIn, layerPos.add(0, 0, 0), leavesState);
                this.placeLeavesAt(worldIn, layerPos.add(-1, 0, 0), leavesState);
                this.placeLeavesAt(worldIn, layerPos.add(1, 0, 0), leavesState);
                this.placeLeavesAt(worldIn, layerPos.add(0, 0, -1), leavesState);
                this.placeLeavesAt(worldIn, layerPos.add(0, 0, 1), leavesState);
                
                // Outer corners
                this.placeLeavesAt(worldIn, layerPos.add(-2, 0, 0), leavesState);
                this.placeLeavesAt(worldIn, layerPos.add(2, 0, 0), leavesState);
                this.placeLeavesAt(worldIn, layerPos.add(0, 0, -2), leavesState);
                this.placeLeavesAt(worldIn, layerPos.add(0, 0, 2), leavesState);
                
                // Diagonal leaves
                this.placeLeavesAt(worldIn, layerPos.add(-1, 0, -1), leavesState);
                this.placeLeavesAt(worldIn, layerPos.add(1, 0, -1), leavesState);
                this.placeLeavesAt(worldIn, layerPos.add(-1, 0, 1), leavesState);
                this.placeLeavesAt(worldIn, layerPos.add(1, 0, 1), leavesState);
                
                // Place log in center
                this.setBlockAndNotifyAdequately(worldIn, layerPos, logState);
            }

            // Bottom layer: just the log at trunkHeight (top of trunk)
            // No leaves at bottom layer as requested
            BlockPos bottomLayerPos = position.up(trunkHeight);
            this.setBlockAndNotifyAdequately(worldIn, bottomLayerPos, logState);

            return true;
        }

        return false;
    }

    /**
     * Place leaves at position if the block is air or leaves
     */
    private void placeLeavesAt(World worldIn, BlockPos pos, IBlockState leavesState)
    {
        IBlockState state = worldIn.getBlockState(pos);
        if (state.getBlock().isAir(state, worldIn, pos) || 
            state.getBlock().isLeaves(state, worldIn, pos))
        {
            this.setBlockAndNotifyAdequately(worldIn, pos, leavesState);
        }
    }

    /**
     * Check if the block is suitable soil for tree growth
     */
    private boolean isSoil(World worldIn, BlockPos pos, Block soilBlock)
    {
        return worldIn.getBlockState(pos).getBlock() == soilBlock;
    }
}


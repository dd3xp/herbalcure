package com.herbalcure.herbalcure.common.world.gen;

import com.herbalcure.herbalcure.HerbalCure;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

/**
 * World generator for Jungle Heartwood trees in forest biomes
 * Generates trees naturally in forest-related biomes during world generation
 */
public class WorldGenJungleHeartwood implements net.minecraftforge.fml.common.IWorldGenerator
{
    private WorldGenerator treeGenerator;

    public WorldGenJungleHeartwood()
    {
        // Create tree generator instance
        this.treeGenerator = new WorldGenJungleHeartwoodTree(true);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        // Only generate in overworld
        if (world.provider.getDimension() != 0)
        {
            return;
        }

        // Get biome at chunk center
        int x = chunkX * 16 + 8;
        int z = chunkZ * 16 + 8;
        BlockPos chunkPos = new BlockPos(x, 0, z);
        Biome biome = world.getBiome(chunkPos);

        // Check if biome is forest-related
        if (isForestBiome(biome))
        {
            // Generate trees in this chunk
            // 30% chance to attempt generation, with 1-2 attempts per chunk
            // This balances frequency with available space (4-block spacing requirement)
            // Approximately every 35-40 blocks you'll see one tree
            if (random.nextInt(100) < 30) // 30% chance
            {
                // Try to generate 1-2 trees (but may fail if no suitable position)
                int attempts = random.nextInt(2) + 1; // 1 or 2 attempts
                
                for (int attempt = 0; attempt < attempts; attempt++)
                {
                    // Random position within chunk
                    int xPos = x + random.nextInt(16);
                    int zPos = z + random.nextInt(16);
                    
                    // Find suitable ground level
                    int yPos = findGroundLevel(world, xPos, zPos);
                    
                    if (yPos > 0)
                    {
                        BlockPos treePos = new BlockPos(xPos, yPos, zPos);
                        
                        // Check if position is suitable for tree
                        if (isSuitablePosition(world, treePos))
                        {
                            // Generate tree
                            treeGenerator.generate(world, random, treePos);
                            // Break after first successful generation to avoid clustering
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Check if biome is forest-related
     * Includes: FOREST, BIRCH_FOREST, ROOFED_FOREST, TAIGA, JUNGLE, etc.
     */
    private boolean isForestBiome(Biome biome)
    {
        // Check BiomeDictionary tags for forest-related biomes
        return BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST) ||
               BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE) ||
               BiomeDictionary.hasType(biome, BiomeDictionary.Type.CONIFEROUS) ||
               BiomeDictionary.hasType(biome, BiomeDictionary.Type.SPOOKY) ||
               BiomeDictionary.hasType(biome, BiomeDictionary.Type.DENSE);
    }

    /**
     * Find suitable ground level for tree generation
     * Returns the Y coordinate of the first solid block with grass or dirt on top
     */
    private int findGroundLevel(World world, int x, int z)
    {
        // Start from bottom and go up to find the topmost solid block
        int maxY = world.getHeight() - 10; // Leave some space for tree
        
        for (int y = maxY; y >= 0; y--)
        {
            BlockPos pos = new BlockPos(x, y, z);
            Block block = world.getBlockState(pos).getBlock();
            
            // Check if we have grass or dirt
            if (block == Blocks.GRASS || block == Blocks.DIRT)
            {
                // Check if there's air or leaves above
                BlockPos posAbove = pos.up();
                Block blockAbove = world.getBlockState(posAbove).getBlock();
                
                if (blockAbove == Blocks.AIR || blockAbove.isLeaves(world.getBlockState(posAbove), world, posAbove))
                {
                    return y + 1; // Return the Y position above ground
                }
            }
        }
        
        return -1; // No suitable ground found
    }

    /**
     * Check if position is suitable for tree generation
     * Ensures there's enough space and proper ground, and not too close to other trees
     */
    private boolean isSuitablePosition(World world, BlockPos pos)
    {
        // Check ground block
        BlockPos groundPos = pos.down();
        Block groundBlock = world.getBlockState(groundPos).getBlock();
        
        if (groundBlock != Blocks.GRASS && groundBlock != Blocks.DIRT)
        {
            return false;
        }
        
        // Check if there's enough space above (at least 10 blocks)
        for (int y = 0; y < 10; y++)
        {
            BlockPos checkPos = pos.up(y);
            Block block = world.getBlockState(checkPos).getBlock();
            
            // Allow air, leaves, and grass
            if (block != Blocks.AIR && 
                !block.isLeaves(world.getBlockState(checkPos), world, checkPos) &&
                block != Blocks.GRASS &&
                block != Blocks.TALLGRASS &&
                block != Blocks.VINE)
            {
                return false;
            }
        }
        
        // Check if there are other tree trunks nearby - keep at least 4 blocks away
        // Similar to IC2 rubber trees and Thaumcraft silverwood - only check for nearby logs
        // This prevents trees from generating inside other trees, but allows reasonable spacing
        int checkRadius = 4; // Minimum distance from other tree trunks (horizontal)
        
        // Only check ground level and a few blocks above for logs (trunks)
        // This is more efficient and matches how other mods handle tree generation
        for (int x = -checkRadius; x <= checkRadius; x++)
        {
            for (int z = -checkRadius; z <= checkRadius; z++)
            {
                // Skip the center position itself
                if (x == 0 && z == 0)
                {
                    continue;
                }
                
                // Only check horizontal distance
                double horizontalDist = Math.sqrt(x * x + z * z);
                if (horizontalDist > checkRadius)
                {
                    continue;
                }
                
                // Check ground level and up to 10 blocks above for logs (trunk area)
                // This is where tree trunks would be, so we avoid generating on top of them
                for (int yOffset = 0; yOffset <= 10; yOffset++)
                {
                    BlockPos checkPos = pos.add(x, yOffset, z);
                    Block block = world.getBlockState(checkPos).getBlock();
                    
                    // If we find a log block (any type of log) at trunk height, this position is too close
                    if (block instanceof net.minecraft.block.BlockLog)
                    {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}


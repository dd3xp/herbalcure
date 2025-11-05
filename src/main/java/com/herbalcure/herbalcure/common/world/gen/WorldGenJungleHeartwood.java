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
            // Generate trees in this chunk with low frequency
            // Only 15% chance to generate a tree in each chunk (approximately every 3-4 chunks = 48-64 blocks)
            // This means roughly every 50-60 blocks you'll see one tree
            if (random.nextInt(100) < 15) // 15% chance
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
     * Ensures there's enough space and proper ground
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
        
        return true;
    }
}


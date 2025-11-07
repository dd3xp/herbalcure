package com.herbalcure.herbalcure.common.world.gen;

import com.herbalcure.herbalcure.HerbalCure;
import com.herbalcure.herbalcure.common.registry.ModRegistries;
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
 * World generator for Verdscale Fern in forest biomes
 * Generates small patches (2-3 ferns) with lower probability than vanilla flowers
 */
public class WorldGenVerdscaleFern implements net.minecraftforge.fml.common.IWorldGenerator
{
    private WorldGenerator fernGenerator;

    public WorldGenVerdscaleFern()
    {
        this.fernGenerator = new WorldGenVerdscaleFernPatch();
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
            // Lower probability than vanilla flowers (vanilla flowers have ~10-20% chance)
            // Use 5% chance to generate, making it rarer than vanilla flowers
            if (random.nextInt(100) < 5) // 5% chance
            {
                int attempts = 1; // Only 1 attempt per chunk (lower than trees)
                for (int attempt = 0; attempt < attempts; attempt++)
                {
                    int xPos = x + random.nextInt(16);
                    int zPos = z + random.nextInt(16);
                    int yPos = findGroundLevel(world, xPos, zPos);
                    if (yPos > 0)
                    {
                        BlockPos fernPos = new BlockPos(xPos, yPos, zPos);
                        if (isSuitablePosition(world, fernPos))
                        {
                            fernGenerator.generate(world, random, fernPos);
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean isForestBiome(Biome biome)
    {
        return BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST) ||
               BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE) ||
               BiomeDictionary.hasType(biome, BiomeDictionary.Type.CONIFEROUS) ||
               BiomeDictionary.hasType(biome, BiomeDictionary.Type.SPOOKY) ||
               BiomeDictionary.hasType(biome, BiomeDictionary.Type.DENSE) ||
               BiomeDictionary.hasType(biome, BiomeDictionary.Type.COLD);
    }

    private int findGroundLevel(World world, int x, int z)
    {
        int maxY = world.getHeight() - 10;
        for (int y = maxY; y >= 0; y--)
        {
            BlockPos pos = new BlockPos(x, y, z);
            Block block = world.getBlockState(pos).getBlock();
            if (block == Blocks.GRASS || block == Blocks.DIRT)
            {
                BlockPos posAbove = pos.up();
                Block blockAbove = world.getBlockState(posAbove).getBlock();
                if (blockAbove == Blocks.AIR || blockAbove.isLeaves(world.getBlockState(posAbove), world, posAbove))
                {
                    return y + 1;
                }
            }
        }
        return -1;
    }

    private boolean isSuitablePosition(World world, BlockPos pos)
    {
        BlockPos groundPos = pos.down();
        Block groundBlock = world.getBlockState(groundPos).getBlock();
        if (groundBlock != Blocks.GRASS && groundBlock != Blocks.DIRT)
        {
            return false;
        }

        // Check if position is air
        Block posBlock = world.getBlockState(pos).getBlock();
        if (posBlock != Blocks.AIR && !posBlock.isLeaves(world.getBlockState(pos), world, pos))
        {
            return false;
        }

        return true;
    }

    /**
     * WorldGenerator for generating a patch of 2-3 Verdscale Ferns
     */
    private static class WorldGenVerdscaleFernPatch extends WorldGenerator
    {
        @Override
        public boolean generate(World worldIn, Random rand, BlockPos position)
        {
            if (ModRegistries.blockVerdscaleFern == null)
            {
                return false;
            }

            // Generate 2-3 ferns in a small patch
            int fernCount = rand.nextInt(2) + 2; // 2-3 ferns
            int placedCount = 0;

            for (int i = 0; i < 10 && placedCount < fernCount; i++)
            {
                // Random offset within 2 blocks radius
                int offsetX = rand.nextInt(3) - 1; // -1 to 1
                int offsetZ = rand.nextInt(3) - 1; // -1 to 1
                BlockPos fernPos = position.add(offsetX, 0, offsetZ);

                // Find ground level at this position
                int yPos = findGroundLevelAt(worldIn, fernPos);
                if (yPos > 0)
                {
                    BlockPos finalPos = new BlockPos(fernPos.getX(), yPos, fernPos.getZ());
                    if (canPlaceFern(worldIn, finalPos))
                    {
                        worldIn.setBlockState(finalPos, ModRegistries.blockVerdscaleFern.getDefaultState(), 2);
                        placedCount++;
                    }
                }
            }

            return placedCount > 0;
        }

        private int findGroundLevelAt(World world, BlockPos pos)
        {
            for (int y = world.getHeight() - 1; y >= 0; y--)
            {
                BlockPos checkPos = new BlockPos(pos.getX(), y, pos.getZ());
                Block block = world.getBlockState(checkPos).getBlock();
                if (block == Blocks.GRASS || block == Blocks.DIRT)
                {
                    BlockPos posAbove = checkPos.up();
                    Block blockAbove = world.getBlockState(posAbove).getBlock();
                    if (blockAbove == Blocks.AIR || blockAbove.isLeaves(world.getBlockState(posAbove), world, posAbove))
                    {
                        return y + 1;
                    }
                }
            }
            return -1;
        }

        private boolean canPlaceFern(World world, BlockPos pos)
        {
            BlockPos groundPos = pos.down();
            Block groundBlock = world.getBlockState(groundPos).getBlock();
            if (groundBlock != Blocks.GRASS && groundBlock != Blocks.DIRT)
            {
                return false;
            }

            Block posBlock = world.getBlockState(pos).getBlock();
            return posBlock == Blocks.AIR || posBlock.isLeaves(world.getBlockState(pos), world, pos);
        }
    }
}


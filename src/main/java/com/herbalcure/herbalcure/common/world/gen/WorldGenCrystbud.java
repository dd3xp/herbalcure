package com.herbalcure.herbalcure.common.world.gen;

import com.herbalcure.herbalcure.HerbalCure;
import com.herbalcure.herbalcure.common.registry.ModRegistries;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * World generator for Crystbud in the Nether
 * Generates small patches (2-3 herbs) on netherrack
 * Lower generation probability than Pyrisage (5% vs 10%) because netherrack is more common than soul sand
 */
public class WorldGenCrystbud implements net.minecraftforge.fml.common.IWorldGenerator
{
    private WorldGenerator herbGenerator;

    public WorldGenCrystbud()
    {
        this.herbGenerator = new WorldGenCrystbudPatch();
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        // Only generate in the Nether (dimension -1)
        if (world.provider.getDimension() != -1)
        {
            return;
        }

        // Get chunk center
        int x = chunkX * 16 + 8;
        int z = chunkZ * 16 + 8;

        // Generate with probability (5% chance, lower than Pyrisage's 10% because netherrack is more common)
        if (random.nextInt(100) < 5) // 5% chance
        {
            int attempts = 2; // 2 attempts per chunk to find suitable netherrack
            for (int attempt = 0; attempt < attempts; attempt++)
            {
                int xPos = x + random.nextInt(16);
                int zPos = z + random.nextInt(16);
                int yPos = findNetherrackLevel(world, xPos, zPos);
                if (yPos > 0)
                {
                    BlockPos herbPos = new BlockPos(xPos, yPos, zPos);
                    if (isSuitablePosition(world, herbPos))
                    {
                        herbGenerator.generate(world, random, herbPos);
                        break;
                    }
                }
            }
        }
    }

    private int findNetherrackLevel(World world, int x, int z)
    {
        // In the Nether, search from top to bottom for netherrack
        int maxY = world.getHeight() - 10;
        for (int y = maxY; y >= 0; y--)
        {
            BlockPos pos = new BlockPos(x, y, z);
            Block block = world.getBlockState(pos).getBlock();
            if (block == Blocks.NETHERRACK)
            {
                BlockPos posAbove = pos.up();
                Block blockAbove = world.getBlockState(posAbove).getBlock();
                if (blockAbove == Blocks.AIR)
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
        if (groundBlock != Blocks.NETHERRACK)
        {
            return false;
        }

        // Check if position is air
        Block posBlock = world.getBlockState(pos).getBlock();
        if (posBlock != Blocks.AIR)
        {
            return false;
        }

        return true;
    }

    /**
     * WorldGenerator for generating a patch of 2-3 Crystbud herbs
     */
    private static class WorldGenCrystbudPatch extends WorldGenerator
    {
        @Override
        public boolean generate(World worldIn, Random rand, BlockPos position)
        {
            if (ModRegistries.blockCrystbud == null)
            {
                return false;
            }

            // Generate 2-3 herbs in a small patch
            int herbCount = rand.nextInt(2) + 2; // 2-3 herbs
            int placedCount = 0;

            for (int i = 0; i < 10 && placedCount < herbCount; i++)
            {
                // Random offset within 2 blocks radius
                int offsetX = rand.nextInt(3) - 1; // -1 to 1
                int offsetZ = rand.nextInt(3) - 1; // -1 to 1
                BlockPos herbPos = position.add(offsetX, 0, offsetZ);

                // Find netherrack level at this position
                int yPos = findNetherrackLevelAt(worldIn, herbPos);
                if (yPos > 0)
                {
                    BlockPos finalPos = new BlockPos(herbPos.getX(), yPos, herbPos.getZ());
                    if (canPlaceHerb(worldIn, finalPos))
                    {
                        worldIn.setBlockState(finalPos, ModRegistries.blockCrystbud.getDefaultState(), 2);
                        placedCount++;
                    }
                }
            }

            return placedCount > 0;
        }

        private int findNetherrackLevelAt(World world, BlockPos pos)
        {
            for (int y = world.getHeight() - 1; y >= 0; y--)
            {
                BlockPos checkPos = new BlockPos(pos.getX(), y, pos.getZ());
                Block block = world.getBlockState(checkPos).getBlock();
                if (block == Blocks.NETHERRACK)
                {
                    BlockPos posAbove = checkPos.up();
                    Block blockAbove = world.getBlockState(posAbove).getBlock();
                    if (blockAbove == Blocks.AIR)
                    {
                        return y + 1;
                    }
                }
            }
            return -1;
        }

        private boolean canPlaceHerb(World world, BlockPos pos)
        {
            BlockPos groundPos = pos.down();
            Block groundBlock = world.getBlockState(groundPos).getBlock();
            // Can only be placed on netherrack
            if (groundBlock != Blocks.NETHERRACK)
            {
                return false;
            }

            Block posBlock = world.getBlockState(pos).getBlock();
            return posBlock == Blocks.AIR;
        }
    }
}


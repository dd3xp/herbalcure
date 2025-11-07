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
 * World generator for Dewpetal in water-related biomes
 * Generates small patches (2-3 flowers) with higher probability than other herbs
 * because water-related biomes have limited suitable ground
 */
public class WorldGenDewpetal implements net.minecraftforge.fml.common.IWorldGenerator
{
    private WorldGenerator flowerGenerator;

    public WorldGenDewpetal()
    {
        this.flowerGenerator = new WorldGenDewpetalPatch();
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

        // Check if biome is water-related
        if (isWaterBiome(biome))
        {
            // Higher probability (15%) because water-related biomes have limited suitable ground
            if (random.nextInt(100) < 15) // 15% chance
            {
                int attempts = 2; // 2 attempts per chunk to find suitable ground
                for (int attempt = 0; attempt < attempts; attempt++)
                {
                    int xPos = x + random.nextInt(16);
                    int zPos = z + random.nextInt(16);
                    int yPos = findGroundLevel(world, xPos, zPos);
                    if (yPos > 0)
                    {
                        BlockPos flowerPos = new BlockPos(xPos, yPos, zPos);
                        if (isSuitablePosition(world, flowerPos))
                        {
                            flowerGenerator.generate(world, random, flowerPos);
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean isWaterBiome(Biome biome)
    {
        return BiomeDictionary.hasType(biome, BiomeDictionary.Type.WATER) ||
               BiomeDictionary.hasType(biome, BiomeDictionary.Type.SWAMP) ||
               BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER) ||
               BiomeDictionary.hasType(biome, BiomeDictionary.Type.WET);
    }

    private int findGroundLevel(World world, int x, int z)
    {
        int maxY = world.getHeight() - 10;
        for (int y = maxY; y >= 0; y--)
        {
            BlockPos pos = new BlockPos(x, y, z);
            Block block = world.getBlockState(pos).getBlock();
            // Check for suitable ground blocks (grass, dirt, farmland, etc.)
            if (block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.FARMLAND)
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
        // Can only be placed on grass, dirt, or farmland (same as vanilla flowers)
        if (groundBlock != Blocks.GRASS && groundBlock != Blocks.DIRT && groundBlock != Blocks.FARMLAND)
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
     * WorldGenerator for generating a patch of 2-3 Dewpetals
     */
    private static class WorldGenDewpetalPatch extends WorldGenerator
    {
        @Override
        public boolean generate(World worldIn, Random rand, BlockPos position)
        {
            if (ModRegistries.blockDewpetal == null)
            {
                return false;
            }

            // Generate 2-3 flowers in a small patch
            int flowerCount = rand.nextInt(2) + 2; // 2-3 flowers
            int placedCount = 0;

            for (int i = 0; i < 10 && placedCount < flowerCount; i++)
            {
                // Random offset within 2 blocks radius
                int offsetX = rand.nextInt(3) - 1; // -1 to 1
                int offsetZ = rand.nextInt(3) - 1; // -1 to 1
                BlockPos flowerPos = position.add(offsetX, 0, offsetZ);

                // Find ground level at this position
                int yPos = findGroundLevelAt(worldIn, flowerPos);
                if (yPos > 0)
                {
                    BlockPos finalPos = new BlockPos(flowerPos.getX(), yPos, flowerPos.getZ());
                    if (canPlaceFlower(worldIn, finalPos))
                    {
                        worldIn.setBlockState(finalPos, ModRegistries.blockDewpetal.getDefaultState(), 2);
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
                // Check for suitable ground blocks (grass, dirt, farmland)
                if (block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.FARMLAND)
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

        private boolean canPlaceFlower(World world, BlockPos pos)
        {
            BlockPos groundPos = pos.down();
            Block groundBlock = world.getBlockState(groundPos).getBlock();
            // Can only be placed on grass, dirt, or farmland (same as vanilla flowers)
            if (groundBlock != Blocks.GRASS && groundBlock != Blocks.DIRT && groundBlock != Blocks.FARMLAND)
            {
                return false;
            }

            Block posBlock = world.getBlockState(pos).getBlock();
            return posBlock == Blocks.AIR || posBlock.isLeaves(world.getBlockState(pos), world, pos);
        }
    }
}


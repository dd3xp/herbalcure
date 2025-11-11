package cahcap.herbalcure.common.world.gen;

import cahcap.herbalcure.HerbalCure;
import cahcap.herbalcure.common.registry.ModRegistries;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * World generator for Rosynia in the End
 * Generates small patches (2-3 roses) on end stone
 */
public class WorldGenRosynia implements net.minecraftforge.fml.common.IWorldGenerator
{
    private WorldGenerator roseGenerator;

    public WorldGenRosynia()
    {
        this.roseGenerator = new WorldGenRosyniaPatch();
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        // Only generate in the End (dimension 1)
        if (world.provider.getDimension() != 1)
        {
            return;
        }

        // Get chunk center
        int x = chunkX * 16 + 8;
        int z = chunkZ * 16 + 8;

        // Generate with probability (10% chance)
        if (random.nextInt(100) < 10) // 10% chance
        {
            int attempts = 2; // 2 attempts per chunk to find suitable end stone
            for (int attempt = 0; attempt < attempts; attempt++)
            {
                int xPos = x + random.nextInt(16);
                int zPos = z + random.nextInt(16);
                int yPos = findEndStoneLevel(world, xPos, zPos);
                if (yPos > 0)
                {
                    BlockPos rosePos = new BlockPos(xPos, yPos, zPos);
                    if (isSuitablePosition(world, rosePos))
                    {
                        roseGenerator.generate(world, random, rosePos);
                        break;
                    }
                }
            }
        }
    }

    private int findEndStoneLevel(World world, int x, int z)
    {
        // In the End, search from top to bottom for end stone
        int maxY = world.getHeight() - 10;
        for (int y = maxY; y >= 0; y--)
        {
            BlockPos pos = new BlockPos(x, y, z);
            Block block = world.getBlockState(pos).getBlock();
            if (block == Blocks.END_STONE)
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
        if (groundBlock != Blocks.END_STONE)
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
     * WorldGenerator for generating a patch of 2-3 Rosynia
     */
    private static class WorldGenRosyniaPatch extends WorldGenerator
    {
        @Override
        public boolean generate(World worldIn, Random rand, BlockPos position)
        {
            if (ModRegistries.blockRosynia == null)
            {
                return false;
            }

            // Generate 2-3 roses in a small patch
            int roseCount = rand.nextInt(2) + 2; // 2-3 roses
            int placedCount = 0;

            for (int i = 0; i < 10 && placedCount < roseCount; i++)
            {
                // Random offset within 2 blocks radius
                int offsetX = rand.nextInt(3) - 1; // -1 to 1
                int offsetZ = rand.nextInt(3) - 1; // -1 to 1
                BlockPos rosePos = position.add(offsetX, 0, offsetZ);

                // Find end stone level at this position
                int yPos = findEndStoneLevelAt(worldIn, rosePos);
                if (yPos > 0)
                {
                    BlockPos finalPos = new BlockPos(rosePos.getX(), yPos, rosePos.getZ());
                    if (canPlaceRose(worldIn, finalPos))
                    {
                        worldIn.setBlockState(finalPos, ModRegistries.blockRosynia.getDefaultState(), 2);
                        placedCount++;
                    }
                }
            }

            return placedCount > 0;
        }

        private int findEndStoneLevelAt(World world, BlockPos pos)
        {
            for (int y = world.getHeight() - 1; y >= 0; y--)
            {
                BlockPos checkPos = new BlockPos(pos.getX(), y, pos.getZ());
                Block block = world.getBlockState(checkPos).getBlock();
                if (block == Blocks.END_STONE)
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

        private boolean canPlaceRose(World world, BlockPos pos)
        {
            BlockPos groundPos = pos.down();
            Block groundBlock = world.getBlockState(groundPos).getBlock();
            // Can only be placed on end stone
            if (groundBlock != Blocks.END_STONE)
            {
                return false;
            }

            Block posBlock = world.getBlockState(pos).getBlock();
            return posBlock == Blocks.AIR;
        }
    }
}


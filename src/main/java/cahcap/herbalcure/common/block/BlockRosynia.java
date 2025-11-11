package cahcap.herbalcure.common.block;

import cahcap.herbalcure.HerbalCure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Rosynia block
 * A herb that can only grow on end stone in the End
 * Slightly larger than vanilla flowers (0.2-0.8 vs 0.3-0.7, 0.5 high vs 0.4 high)
 */
public class BlockRosynia extends BlockBush
{
    // Slightly larger than vanilla flowers (0.2-0.8 vs 0.3-0.7, 0.5 high vs 0.4 high)
    protected static final AxisAlignedBB ROSE_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.5D, 0.8D);

    public BlockRosynia()
    {
        super();
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
        setCreativeTab(HerbalCure.CREATIVE_TAB);
        // Set light level to 10 (higher than redstone torch 7, lower than torch 14)
        setLightLevel(10.0F / 15.0F);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return ROSE_AABB;
    }

    /**
     * Can only be placed on end stone
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        BlockPos downPos = pos.down();
        IBlockState downState = worldIn.getBlockState(downPos);
        return downState.getBlock() == Blocks.END_STONE && super.canPlaceBlockAt(worldIn, pos);
    }

    /**
     * Can only sustain on end stone
     */
    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.END_STONE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, net.minecraft.util.EnumFacing side)
    {
        return true;
    }

    /**
     * Generate ender portal particles floating up from the herb
     * Increased particle count for more visible effect
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double x = (double)pos.getX() + 0.5D;
        double y = (double)pos.getY() + 0.5D;
        double z = (double)pos.getZ() + 0.5D;
        
        double offsetX = (rand.nextDouble() - 0.5D) * 0.6D;
        double offsetZ = (rand.nextDouble() - 0.5D) * 0.6D;
        
        if (rand.nextInt(2) == 0)
        {
            worldIn.spawnParticle(
                EnumParticleTypes.PORTAL,
                x + offsetX,
                y,
                z + offsetZ,
                (rand.nextDouble() - 0.5D) * 0.1D,
                0.05D + rand.nextDouble() * 0.05D,
                (rand.nextDouble() - 0.5D) * 0.1D,
                new int[0]
            );
            
            if (rand.nextInt(3) == 0)
            {
                double offsetX2 = (rand.nextDouble() - 0.5D) * 0.6D;
                double offsetZ2 = (rand.nextDouble() - 0.5D) * 0.6D;
                worldIn.spawnParticle(
                    EnumParticleTypes.PORTAL,
                    x + offsetX2,
                    y,
                    z + offsetZ2,
                    (rand.nextDouble() - 0.5D) * 0.1D,
                    0.05D + rand.nextDouble() * 0.05D,
                    (rand.nextDouble() - 0.5D) * 0.1D,
                    new int[0]
                );
            }
        }
    }
}


package cahcap.herbalcure.common.block;

import cahcap.herbalcure.HerbalCure;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Dewpetal block
 * A small herb that generates in water-related biomes
 * Slightly larger than vanilla flowers (0.2-0.8 vs 0.3-0.7, 0.5 high vs 0.4 high)
 */
public class BlockDewpetal extends BlockBush
{
    // Slightly larger than vanilla flowers (0.2-0.8 vs 0.3-0.7, 0.5 high vs 0.4 high)
    protected static final AxisAlignedBB FLOWER_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.5D, 0.8D);

    public BlockDewpetal()
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
        return FLOWER_AABB;
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
}


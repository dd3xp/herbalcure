package com.herbalcure.herbalcure.common.block;

import com.herbalcure.herbalcure.HerbalCure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Pyrisage block
 * A herb that can only grow on soul sand in the Nether
 * Slightly larger than vanilla flowers (0.2-0.8 vs 0.3-0.7, 0.5 high vs 0.4 high)
 */
public class BlockPyrisage extends BlockBush
{
    // Slightly larger than vanilla flowers (0.2-0.8 vs 0.3-0.7, 0.5 high vs 0.4 high)
    protected static final AxisAlignedBB HERB_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.5D, 0.8D);

    public BlockPyrisage()
    {
        super();
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
        setCreativeTab(HerbalCure.CREATIVE_TAB);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return HERB_AABB;
    }

    /**
     * Can only be placed on soul sand
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        BlockPos downPos = pos.down();
        IBlockState downState = worldIn.getBlockState(downPos);
        return downState.getBlock() == Blocks.SOUL_SAND && super.canPlaceBlockAt(worldIn, pos);
    }

    /**
     * Can only sustain on soul sand
     */
    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.SOUL_SAND;
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


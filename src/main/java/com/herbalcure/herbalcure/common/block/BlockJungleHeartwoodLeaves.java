package com.herbalcure.herbalcure.common.block;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

/**
 * Jungle Heartwood Leaves block
 * Similar to vanilla leaves blocks, but with different texture
 */
public class BlockJungleHeartwoodLeaves extends BlockLeaves
{
    public BlockJungleHeartwoodLeaves()
    {
        super();
        setHardness(0.2F);
        setLightOpacity(1); // Light opacity for leaves (1 = blocks light, but still renders with cutout)
        setSoundType(SoundType.PLANT);
        setDefaultState(this.blockState.getBaseState()
            .withProperty(CHECK_DECAY, Boolean.valueOf(true))
            .withProperty(DECAYABLE, Boolean.valueOf(true)));
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta)
    {
        return null;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return java.util.Arrays.asList(new ItemStack(this, 1, 0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        // Use the same logic as vanilla leaves for proper transparency
        return Blocks.LEAVES.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(net.minecraft.init.Blocks.SAPLING);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
        {
            i |= 4;
        }
        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
        {
            i |= 8;
        }
        return i;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState()
            .withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0))
            .withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    @Override
    protected net.minecraft.block.state.BlockStateContainer createBlockState()
    {
        return new net.minecraft.block.state.BlockStateContainer(this, new net.minecraft.block.properties.IProperty[] {CHECK_DECAY, DECAYABLE});
    }

    /**
     * Called when the block is placed in the world
     * This ensures that manually placed leaves don't decay (DECAYABLE = false)
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        // Set manually placed leaves to not decay
        worldIn.setBlockState(pos, state.withProperty(DECAYABLE, Boolean.valueOf(false)).withProperty(CHECK_DECAY, Boolean.valueOf(false)), 4);
    }
}


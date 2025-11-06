package com.herbalcure.herbalcure.common.block;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

/**
 * Forest Heartwood Log block
 * Similar to vanilla log blocks, but with different texture
 */
public class BlockForestHeartwoodLog extends BlockLog
{
    public BlockForestHeartwoodLog()
    {
        super();
        setHardness(2.0F);
        setResistance(2.0F);
        setSoundType(SoundType.WOOD);
        setHarvestLevel("axe", 0);
        setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {LOG_AXIS});
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        BlockLog.EnumAxis axis = BlockLog.EnumAxis.Y;
        int axisBits = meta & 12;

        if (axisBits == 4)
        {
            axis = BlockLog.EnumAxis.X;
        }
        else if (axisBits == 8)
        {
            axis = BlockLog.EnumAxis.Z;
        }
        else if (axisBits == 0)
        {
            axis = BlockLog.EnumAxis.Y;
        }

        return this.getDefaultState().withProperty(LOG_AXIS, axis);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int meta = 0;
        BlockLog.EnumAxis axis = (BlockLog.EnumAxis)state.getValue(LOG_AXIS);

        if (axis == BlockLog.EnumAxis.X)
        {
            meta |= 4;
        }
        else if (axis == BlockLog.EnumAxis.Z)
        {
            meta |= 8;
        }
        else if (axis == BlockLog.EnumAxis.Y)
        {
            meta |= 0;
        }

        return meta;
    }
}


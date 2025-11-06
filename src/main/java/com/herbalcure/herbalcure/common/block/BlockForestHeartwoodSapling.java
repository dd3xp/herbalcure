package com.herbalcure.herbalcure.common.block;

import com.herbalcure.herbalcure.HerbalCure;
import com.herbalcure.herbalcure.common.world.gen.WorldGenForestHeartwoodTree;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Forest Heartwood Sapling block
 * Grows into a tree made of Forest Heartwood Log and Forest Heartwood Leaves
 */
public class BlockForestHeartwoodSapling extends BlockSapling
{
    public BlockForestHeartwoodSapling()
    {
        super();
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
        setTickRandomly(true); // Enable random tick for natural growth
        // Set default state with TYPE=oak (required by base class) and STAGE=0
        setDefaultState(this.blockState.getBaseState()
            .withProperty(TYPE, BlockPlanks.EnumType.OAK)
            .withProperty(STAGE, Integer.valueOf(0)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        // Include both TYPE and STAGE properties
        // TYPE is required by BlockSapling base class constructor
        return new BlockStateContainer(this, new IProperty[] {TYPE, STAGE});
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // Only use STAGE from meta, TYPE is always OAK
        return this.getDefaultState().withProperty(STAGE, Integer.valueOf(meta & 1));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // Only return STAGE as meta, TYPE is ignored
        return ((Integer)state.getValue(STAGE)).intValue();
    }

    /**
     * Override getSubBlocks to only show one variant in creative tab
     * This prevents multiple sapling items from appearing (one for each TYPE)
     */
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        // Only add one item stack with default state (stage=0)
        items.add(new ItemStack(this, 1, 0));
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            // Don't call super.updateTick() as it might generate vanilla trees
            // Instead, implement our own growth logic
            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                this.grow(worldIn, rand, pos, state);
            }
        }
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        if (((Integer)state.getValue(STAGE)).intValue() == 0)
        {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {
            generateTree(worldIn, rand, pos, state);
        }
    }

    private void generateTree(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos))
        {
            return;
        }

        WorldGenerator treeGenerator = new WorldGenForestHeartwoodTree(true);

        // Clear the sapling block
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

        // Try to generate the tree
        if (!treeGenerator.generate(worldIn, rand, pos))
        {
            // If generation failed, restore the sapling
            worldIn.setBlockState(pos, state, 4);
        }
    }
}


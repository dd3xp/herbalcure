package com.herbalcure.herbalcure.common.block;

import com.herbalcure.herbalcure.HerbalCure;
import com.herbalcure.herbalcure.common.registry.ModRegistries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Forest Berry Bush block
 * Can be planted under Forest Heartwood Leaves
 * Has 3 growth stages (0, 1, 2)
 * Stage 2 (mature) can be harvested for Forest Berry
 * 
 * Growth conditions:
 * - Must have Forest Heartwood Leaves above
 * - Requires light level >= 6
 * - Can be fertilized with bonemeal
 */
public class BlockForestBerryBush extends BlockBush implements IGrowable
{
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);
    // Collision boxes for hanging berry bush (hangs from leaves above)
    // Stage 0: small, hangs down 0.5 blocks from top
    protected static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.25D, 0.5D, 0.25D, 0.75D, 1.0D, 0.75D);
    // Stage 1: medium, hangs down 0.75 blocks from top
    protected static final AxisAlignedBB GROWING_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 1.0D, 0.75D);
    // Stage 2: mature, hangs down 1.0 block from top (full block)
    protected static final AxisAlignedBB MATURE_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);

    public BlockForestBerryBush()
    {
        super();
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
        setTickRandomly(true);
        setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
        setCreativeTab(HerbalCure.CREATIVE_TAB);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(Math.min(meta, 2)));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        int age = ((Integer)state.getValue(AGE)).intValue();
        if (age == 0)
        {
            return BUSH_AABB;
        }
        else if (age == 1)
        {
            return GROWING_AABB;
        }
        else
        {
            return MATURE_AABB;
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        // Can only be placed under Forest Heartwood Leaves (hanging from leaves)
        // No need to check for soil below - it hangs from the leaves
        BlockPos upPos = pos.up();
        IBlockState upState = worldIn.getBlockState(upPos);
        return upState.getBlock() == ModRegistries.blockForestHeartwoodLeaves;
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        // This bush hangs from leaves, doesn't need soil
        // Always return true since we check for leaves above in canPlaceBlockAt
        return true;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);

        if (!worldIn.isRemote)
        {
            int age = ((Integer)state.getValue(AGE)).intValue();
            
            // Check if there's still Forest Heartwood Leaves above
            BlockPos upPos = pos.up();
            IBlockState upState = worldIn.getBlockState(upPos);
            if (upState.getBlock() != ModRegistries.blockForestHeartwoodLeaves)
            {
                // If leaves are gone, drop items and break the bush
                if (ModRegistries.itemForestBerry != null)
                {
                    if (age >= 2)
                    {
                        // Mature stage (age 2): drop 1-2 berries
                        int dropCount = 1 + worldIn.rand.nextInt(2); // 1-2 berries
                        spawnAsEntity(worldIn, pos, new ItemStack(ModRegistries.itemForestBerry, dropCount));
                    }
                    else
                    {
                        // Stage 0 and 1: drop 1 berry
                        spawnAsEntity(worldIn, pos, new ItemStack(ModRegistries.itemForestBerry, 1));
                    }
                }
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                return;
            }

            // Grow if conditions are met
            if (age < 2 && worldIn.getLightFromNeighbors(pos.up()) >= 6)
            {
                float growthChance = getGrowthChance(this, worldIn, pos);
                
                if (rand.nextInt((int)(25.0F / growthChance) + 1) == 0)
                {
                    worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(age + 1)), 2);
                }
            }
        }
    }

    protected float getGrowthChance(Block blockIn, World worldIn, BlockPos pos)
    {
        float chance = 1.0F;

        // Check surrounding blocks (bonus for nearby bushes)
        for (int x = -1; x <= 1; ++x)
        {
            for (int z = -1; z <= 1; ++z)
            {
                if (x == 0 && z == 0)
                {
                    continue;
                }
                
                BlockPos checkPos = pos.add(x, 0, z);
                IBlockState checkState = worldIn.getBlockState(checkPos);
                if (checkState.getBlock() == blockIn)
                {
                    float neighborAge = ((Integer)checkState.getValue(AGE)).intValue();
                    if (neighborAge >= 2)
                    {
                        chance /= 2.0F; // Slight penalty for nearby mature bushes
                    }
                }
            }
        }

        return chance;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        int age = ((Integer)state.getValue(AGE)).intValue();
        
        // Only harvest when mature (age 2)
        if (age >= 2)
        {
            // Drop 1-2 Forest Berries
            if (!worldIn.isRemote && ModRegistries.itemForestBerry != null)
            {
                int dropCount = worldIn.rand.nextInt(2) + 1; // 1-2 berries
                spawnAsEntity(worldIn, pos, new ItemStack(ModRegistries.itemForestBerry, dropCount));
                
                // Reset to stage 0 (first stage)
                worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(0)), 2);
            }
            
            return true;
        }
        
        return false;
    }

    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        int age = ((Integer)state.getValue(AGE)).intValue();
        
        if (ModRegistries.itemForestBerry != null)
        {
            if (age >= 2)
            {
                // Mature stage (age 2): drop 1-2 berries
                int dropCount = 1 + ((World)world).rand.nextInt(2); // 1-2 berries
                drops.add(new ItemStack(ModRegistries.itemForestBerry, dropCount));
            }
            else
            {
                // Stage 0 and 1: drop 1 berry (because it was planted with 1 berry)
                drops.add(new ItemStack(ModRegistries.itemForestBerry, 1));
            }
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        // Don't add any stages to creative tab
        // Players should obtain berry bushes by planting Forest Berries
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
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    // ========== IGrowable implementation for bonemeal ==========

    /**
     * Check if the block can grow (not at max age and has leaves above)
     */
    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        int age = ((Integer)state.getValue(AGE)).intValue();
        if (age >= 2)
        {
            return false; // Already mature
        }
        
        // Check if there's still Forest Heartwood Leaves above
        BlockPos upPos = pos.up();
        IBlockState upState = worldIn.getBlockState(upPos);
        return upState.getBlock() == ModRegistries.blockForestHeartwoodLeaves;
    }

    /**
     * Check if bonemeal can be used on this block
     * Same conditions as canGrow
     */
    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return canGrow(worldIn, pos, state, false);
    }

    /**
     * Grow the block (called when bonemeal is used)
     * Advances to next growth stage
     */
    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        int age = ((Integer)state.getValue(AGE)).intValue();
        if (age < 2)
        {
            // Advance to next stage
            worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(age + 1)), 2);
        }
    }
}


package com.herbalcure.herbalcure.common.block;

import com.herbalcure.herbalcure.common.registry.ModRegistries;
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
 * Forest Heartwood Leaves block
 * Similar to vanilla leaves blocks, but with different texture
 */
public class BlockForestHeartwoodLeaves extends BlockLeaves
{
    public BlockForestHeartwoodLeaves()
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
        // Drop Forest Heartwood Sapling instead of vanilla sapling
        if (ModRegistries.blockForestHeartwoodSapling != null)
        {
            return Item.getItemFromBlock(ModRegistries.blockForestHeartwoodSapling);
        }
        return Item.getItemFromBlock(net.minecraft.init.Blocks.SAPLING);
    }

    /**
     * Called when the block is broken or decayed
     * Adds Forest Berry to the drop list with a chance lower than apple (1/300 vs 1/200 for apple)
     */
    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        
        // Drop sapling (base class handles this)
        super.getDrops(drops, world, pos, state, fortune);
        
        // Drop Forest Berry with lower probability than apple (1/300 vs 1/200)
        // Fortune increases drop chance
        int chance = 300;
        if (fortune > 0)
        {
            chance -= 20 * fortune; // Fortune reduces chance (e.g., Fortune I: 280, Fortune II: 260, Fortune III: 240)
            if (chance < 50) chance = 50; // Minimum chance is 1/50
        }
        
        if (rand.nextInt(chance) == 0 && ModRegistries.itemForestBerry != null)
        {
            drops.add(new ItemStack(ModRegistries.itemForestBerry, 1));
        }
    }

    /**
     * Called when the block decays naturally
     * Also drops Forest Berry with chance (lower than apple)
     */
    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        if (!worldIn.isRemote)
        {
            // Drop sapling (base class handles this)
            super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
            
            // Drop Forest Berry with chance (1/300, lower than apple's 1/200)
            Random rand = worldIn.rand;
            int berryChance = 300;
            if (fortune > 0)
            {
                berryChance -= 20 * fortune;
                if (berryChance < 50) berryChance = 50;
            }
            
            if (rand.nextInt(berryChance) == 0 && ModRegistries.itemForestBerry != null)
            {
                spawnAsEntity(worldIn, pos, new ItemStack(ModRegistries.itemForestBerry, 1));
            }
        }
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

    /**
     * Called when the block is broken
     * Check if there's a berry bush below and make it drop items
     */
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            // Check if there's a berry bush below
            BlockPos downPos = pos.down();
            IBlockState downState = worldIn.getBlockState(downPos);
            
            if (downState.getBlock() == ModRegistries.blockForestBerryBush)
            {
                // Get the berry bush's age
                int age = ((Integer)downState.getValue(com.herbalcure.herbalcure.common.block.BlockForestBerryBush.AGE)).intValue();
                
                // Drop items based on age (same as breaking the bush)
                if (ModRegistries.itemForestBerry != null)
                {
                    if (age >= 2)
                    {
                        // Mature stage (age 2): drop 1-2 berries
                        int dropCount = 1 + worldIn.rand.nextInt(2); // 1-2 berries
                        spawnAsEntity(worldIn, downPos, new ItemStack(ModRegistries.itemForestBerry, dropCount));
                    }
                    else
                    {
                        // Stage 0 and 1: drop 1 berry
                        spawnAsEntity(worldIn, downPos, new ItemStack(ModRegistries.itemForestBerry, 1));
                    }
                }
                
                // Remove the berry bush
                worldIn.setBlockState(downPos, Blocks.AIR.getDefaultState(), 3);
            }
        }
        
        super.breakBlock(worldIn, pos, state);
    }
}


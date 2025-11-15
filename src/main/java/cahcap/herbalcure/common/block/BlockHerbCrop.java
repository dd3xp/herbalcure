package cahcap.herbalcure.common.block;

import cahcap.herbalcure.HerbalCure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Base class for herb crops
 * Has 8 growth stages (0-7)
 * Can be planted on farmland
 */
public class BlockHerbCrop extends BlockCrops implements IGrowable
{
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
    
    // Standard crop bounding box (like wheat)
    protected static final AxisAlignedBB[] CROP_AABB = new AxisAlignedBB[] {
        new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), // Stage 0
        new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),  // Stage 1
        new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), // Stage 2
        new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D),   // Stage 3
        new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), // Stage 4
        new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D),   // Stage 5
        new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), // Stage 6
        new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)    // Stage 7 (mature)
    };
    
    protected final Item productItem;
    
    public BlockHerbCrop(Item productItem)
    {
        this.productItem = productItem;
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
        setTickRandomly(true);
        setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
        setCreativeTab(HerbalCure.CREATIVE_TAB);
        disableStats();
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(Math.min(meta, 7)));
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
        return CROP_AABB[Math.min(age, 7)];
    }
    
    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.FARMLAND;
    }
    
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);
        
        if (!worldIn.isRemote)
        {
            int age = ((Integer)state.getValue(AGE)).intValue();
            
            // Check if farmland is still below
            IBlockState soil = worldIn.getBlockState(pos.down());
            if (soil.getBlock() != Blocks.FARMLAND)
            {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                return;
            }
            
            // Grow if conditions are met
            if (age < 7 && worldIn.getLightFromNeighbors(pos.up()) >= 9)
            {
                float growthChance = getGrowthChance(this, worldIn, pos);
                
                if (rand.nextInt((int)(25.0F / growthChance) + 1) == 0)
                {
                    worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(age + 1)), 2);
                }
            }
        }
    }
    
    protected static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos)
    {
        float chance = 1.0F;
        
        // Check surrounding blocks (bonus for nearby crops)
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
                    if (neighborAge >= 7)
                    {
                        chance /= 2.0F; // Slight penalty for nearby mature crops
                    }
                }
            }
        }
        
        return chance;
    }
    
    @Override
    protected Item getSeed()
    {
        // Subclasses should override this method to return the correct seed
        return null;
    }
    
    @Override
    protected Item getCrop()
    {
        return productItem;
    }
    
    @Override
    public int getMaxAge()
    {
        return 7;
    }
    
    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        int age = ((Integer)state.getValue(AGE)).intValue();
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        
        if (age >= 7)
        {
            // Mature stage: drop 1 product (fixed) and seed with chance (like wheat)
            if (productItem != null)
            {
                drops.add(new ItemStack(productItem, 1));
            }
            
            // Seed drop chance: base 50% (like wheat), fortune increases chance
            // Fortune I: ~57%, Fortune II: ~64%, Fortune III: ~71%
            int seedChance = 2; // 1/2 = 50%
            if (fortune > 0)
            {
                seedChance = Math.max(1, seedChance - fortune); // Fortune reduces denominator
            }
            
            if (rand.nextInt(seedChance) == 0)
            {
                Item seed = getSeed();
                if (seed != null)
                {
                    drops.add(new ItemStack(seed, 1));
                }
            }
        }
        else
        {
            // Immature stage: drop seed with lower chance (like wheat)
            // Base chance: 1/3 (33%), fortune increases chance
            int seedChance = 3;
            if (fortune > 0)
            {
                seedChance = Math.max(1, seedChance - fortune);
            }
            
            if (rand.nextInt(seedChance) == 0)
            {
                Item seed = getSeed();
                if (seed != null)
                {
                    drops.add(new ItemStack(seed, 1));
                }
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public net.minecraft.util.BlockRenderLayer getBlockLayer()
    {
        return net.minecraft.util.BlockRenderLayer.CUTOUT;
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


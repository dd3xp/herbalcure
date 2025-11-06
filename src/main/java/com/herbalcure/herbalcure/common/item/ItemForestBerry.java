package com.herbalcure.herbalcure.common.item;

import com.herbalcure.herbalcure.HerbalCure;
import com.herbalcure.herbalcure.common.registry.ModRegistries;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Forest Berry item
 * Can be eaten to restore hunger points
 * Can be right-clicked to plant under Forest Heartwood Leaves
 */
public class ItemForestBerry extends ItemFood
{
    public ItemForestBerry()
    {
        super(4, 1.0F, false); // restore 4 hunger, saturation 1.0, not wolf food
        setUnlocalizedName("forest_berry");
        setRegistryName("forest_berry");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 16; // Same as apple (fast eating)
    }

    /**
     * Called when a Block is right-clicked with this Item
     * Allows planting Forest Berry Bush under Forest Heartwood Leaves
     */
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        
        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }

        // Check if we're clicking on Forest Heartwood Leaves
        IBlockState clickedState = worldIn.getBlockState(pos);
        if (clickedState.getBlock() == ModRegistries.blockForestHeartwoodLeaves)
        {
            // The position where we want to plant (below the leaves, hanging from them)
            BlockPos plantPos = pos.down();
            IBlockState plantState = worldIn.getBlockState(plantPos);
            
            // Check if the position is suitable for planting (air or replaceable)
            // No need to check for soil - it hangs from the leaves
            if (plantState.getBlock().isReplaceable(worldIn, plantPos) || 
                plantState.getBlock().isAir(plantState, worldIn, plantPos))
            {
                // Check if we can place the berry bush here (only needs leaves above)
                if (ModRegistries.blockForestBerryBush != null && 
                    ModRegistries.blockForestBerryBush.canPlaceBlockAt(worldIn, plantPos))
                {
                    worldIn.setBlockState(plantPos, ModRegistries.blockForestBerryBush.getDefaultState(), 3);
                    
                    if (!player.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);
                    }
                    
                    return EnumActionResult.SUCCESS;
                }
            }
        }

        return EnumActionResult.PASS;
    }
}


package cahcap.herbalcure.common.item;

import cahcap.herbalcure.HerbalCure;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Base class for herb seeds
 * Can be planted on farmland to grow herb crops
 */
public class ItemHerbSeed extends Item
{
    private final Block cropBlock;
    
    public ItemHerbSeed(Block cropBlock)
    {
        this.cropBlock = cropBlock;
        setCreativeTab(HerbalCure.CREATIVE_TAB);
    }
    
    /**
     * Called when a Block is right-clicked with this Item
     * Allows planting the seed on farmland
     */
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        IBlockState state = worldIn.getBlockState(pos);
        Block block = state.getBlock();
        
        // Check if we're clicking on farmland
        if (block == net.minecraft.init.Blocks.FARMLAND && facing == EnumFacing.UP)
        {
            BlockPos cropPos = pos.up();
            
            // Check if the position above farmland is air
            if (worldIn.isAirBlock(cropPos))
            {
                // Check if crop block can be placed here
                if (cropBlock != null && cropBlock.canPlaceBlockAt(worldIn, cropPos))
                {
                    worldIn.setBlockState(cropPos, cropBlock.getDefaultState(), 3);
                    
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


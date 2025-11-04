package com.herbalcure.herbalcure.client.handler;

import com.herbalcure.herbalcure.common.registry.ModRegistries;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;

import javax.annotation.Nullable;

/**
 * Block color handler for mod blocks
 * Handles biome-based coloring for blocks like leaves
 */
public class BlockColorHandler
{
    /**
     * Color handler for leaves - uses biome foliage color
     * Note: Pink colors (#FF00FF or similar) in the texture should not be tinted
     * In Minecraft, pink is often used as a mask color that should remain unchanged
     */
    public static final IBlockColor LEAVES_COLOR = new IBlockColor()
    {
        @Override
        public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
        {
            // For tintIndex 0, apply biome color (normal leaves)
            // For other tintIndex values, return white (no color change) to preserve special colors
            if (tintIndex == 0)
            {
                if (worldIn != null && pos != null)
                {
                    // Use biome foliage color, same as vanilla leaves
                    return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
                }
                // Default color if no world/pos provided
                return ColorizerFoliage.getFoliageColor(0.5D, 1.0D);
            }
            // Return white for non-zero tintIndex to preserve original colors (like pink)
            return 0xFFFFFF;
        }
    };

    /**
     * Color handler for leaves item in inventory - uses green color
     * This makes the leaves appear green in inventory and hand
     */
    public static final IItemColor LEAVES_ITEM_COLOR = new IItemColor()
    {
        @Override
        public int colorMultiplier(ItemStack stack, int tintIndex)
        {
            // Return green color for leaves in inventory (0x48B518 is similar to vanilla foliage green)
            return 0x48B518;
        }
    };

    /**
     * Register block color handlers
     * This should be called from ClientProxy during initialization
     */
    public static void registerBlockColorHandlers(BlockColors blockColors)
    {
        // Register leaves color handler
        if (ModRegistries.blockJungleHeartwoodLeaves != null)
        {
            blockColors.registerBlockColorHandler(LEAVES_COLOR, ModRegistries.blockJungleHeartwoodLeaves);
        }
    }
}


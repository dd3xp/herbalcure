package com.herbalcure.herbalcure.common.item;

import com.herbalcure.herbalcure.HerbalCure;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

/**
 * Forest Berry item
 * Can be eaten to restore 2 hunger points (like apple)
 */
public class ItemForestBerry extends ItemFood
{
    public ItemForestBerry()
    {
        super(4, 1.0F, false); // restore 4 hunger, saturation 0.8, not wolf food
        setUnlocalizedName("forest_berry");
        setRegistryName("forest_berry");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 16; // Same as apple (fast eating)
    }
}


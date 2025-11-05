package com.herbalcure.herbalcure.common.creativetab;

import com.herbalcure.herbalcure.HerbalCure;
import com.herbalcure.herbalcure.common.registry.ModRegistries;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * Custom creative tab for HerbalCure mod
 * All mod items will appear in this creative tab
 */
public class HerbalCureCreativeTab extends CreativeTabs
{
    public HerbalCureCreativeTab()
    {
        super(HerbalCure.MODID);
    }

    @Override
    public ItemStack getTabIconItem()
    {
        // Use Jungle Heartwood Leaves as the icon for the creative tab
        if (ModRegistries.blockJungleHeartwoodLeaves != null)
        {
            return new ItemStack(net.minecraft.item.Item.getItemFromBlock(ModRegistries.blockJungleHeartwoodLeaves));
        }
        // Fallback: use a stick if leaves are not available
        return new ItemStack(net.minecraft.init.Items.STICK);
    }

    @Override
    public String getTranslatedTabLabel()
    {
        return HerbalCure.NAME;
    }

}


package cahcap.herbalcure.common.creativetab;

import cahcap.herbalcure.HerbalCure;
import cahcap.herbalcure.common.registry.ModRegistries;
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
        // Use Weaveflow Loop as the icon for the creative tab
        if (ModRegistries.itemWeaveflowLoop != null)
        {
            return new ItemStack(ModRegistries.itemWeaveflowLoop);
        }
        // Fallback: use Forest Heartwood Leaves if Weaveflow Loop is not available
        if (ModRegistries.blockForestHeartwoodLeaves != null)
        {
            return new ItemStack(net.minecraft.item.Item.getItemFromBlock(ModRegistries.blockForestHeartwoodLeaves));
        }
        // Final fallback: use a stick
        return new ItemStack(net.minecraft.init.Items.STICK);
    }

    @Override
    public String getTranslatedTabLabel()
    {
        return HerbalCure.NAME;
    }

}


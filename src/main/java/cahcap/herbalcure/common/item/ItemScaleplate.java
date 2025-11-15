package cahcap.herbalcure.common.item;

import cahcap.herbalcure.HerbalCure;
import net.minecraft.item.Item;

/**
 * Scaleplate
 * Product of Verdscale Fern crop
 * Used for crafting Weaveleaf Armor and Thornmark Tools
 */
public class ItemScaleplate extends Item
{
    public ItemScaleplate()
    {
        setUnlocalizedName("scaleplate");
        setRegistryName("scaleplate");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
    }
}


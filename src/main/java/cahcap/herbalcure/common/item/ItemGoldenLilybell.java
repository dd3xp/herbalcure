package cahcap.herbalcure.common.item;

import cahcap.herbalcure.HerbalCure;
import net.minecraft.item.Item;

/**
 * Golden Lilybell
 * Product of Zephyr Lily crop
 * Used for crafting logistics-related items including Sylvana contracts
 */
public class ItemGoldenLilybell extends Item
{
    public ItemGoldenLilybell()
    {
        setUnlocalizedName("golden_lilybell");
        setRegistryName("golden_lilybell");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
    }
}


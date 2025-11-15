package cahcap.herbalcure.common.item;

import cahcap.herbalcure.HerbalCure;
import net.minecraft.item.Item;

/**
 * Dewpetal Shard
 * Product of Dewpetal crop
 * Used for crafting potion-related items
 */
public class ItemDewpetalShard extends Item
{
    public ItemDewpetalShard()
    {
        setUnlocalizedName("dewpetal_shard");
        setRegistryName("dewpetal_shard");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
    }
}


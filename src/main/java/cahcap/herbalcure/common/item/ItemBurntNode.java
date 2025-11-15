package cahcap.herbalcure.common.item;

import cahcap.herbalcure.HerbalCure;
import net.minecraft.item.Item;

/**
 * Burnt Node
 * Product of Pyrisage crop
 * Used for upgrading vanilla equipment
 */
public class ItemBurntNode extends Item
{
    public ItemBurntNode()
    {
        setUnlocalizedName("burnt_node");
        setRegistryName("burnt_node");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
    }
}


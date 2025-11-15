package cahcap.herbalcure.common.block;

import cahcap.herbalcure.common.registry.ModRegistries;
import net.minecraft.item.Item;

/**
 * Pyrisage Crop
 * Has 8 growth stages (0-7)
 * Mature stage (7) drops 1 Burnt Node and 1 Emberseed
 */
public class BlockPyrisageCrop extends BlockHerbCrop
{
    public BlockPyrisageCrop()
    {
        super(ModRegistries.itemBurntNode);
        setUnlocalizedName("pyrisage_crop");
    }
    
    @Override
    protected net.minecraft.item.Item getSeed()
    {
        return ModRegistries.itemPyrisageSeed;
    }
}


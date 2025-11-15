package cahcap.herbalcure.common.item;

import net.minecraft.block.Block;

/**
 * Pyrisage Seed
 * Can be planted on farmland to grow Pyrisage crops
 */
public class ItemPyrisageSeed extends ItemHerbSeed
{
    public ItemPyrisageSeed(Block cropBlock)
    {
        super(cropBlock);
        setUnlocalizedName("pyrisage_seed");
        setRegistryName("pyrisage_seed");
    }
}


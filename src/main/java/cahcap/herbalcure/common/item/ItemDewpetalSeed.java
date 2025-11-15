package cahcap.herbalcure.common.item;

import net.minecraft.block.Block;

/**
 * Dewpetal Seed
 * Can be planted on farmland to grow Dewpetal crops
 */
public class ItemDewpetalSeed extends ItemHerbSeed
{
    public ItemDewpetalSeed(Block cropBlock)
    {
        super(cropBlock);
        setUnlocalizedName("dewpetal_seed");
        setRegistryName("dewpetal_seed");
    }
}


package cahcap.herbalcure.common.item;

import net.minecraft.block.Block;

/**
 * Verdscale Fern Seed
 * Can be planted on farmland to grow Verdscale Fern crops
 */
public class ItemVerdscaleFernSeed extends ItemHerbSeed
{
    public ItemVerdscaleFernSeed(Block cropBlock)
    {
        super(cropBlock);
        setUnlocalizedName("verdscale_fern_seed");
        setRegistryName("verdscale_fern_seed");
    }
}


package cahcap.herbalcure.common.item;

import net.minecraft.block.Block;

/**
 * Crystbud Seed (Crystallization Seed)
 * Can be planted on farmland to grow Crystbud crops
 */
public class ItemCrystbudSeed extends ItemHerbSeed
{
    public ItemCrystbudSeed(Block cropBlock)
    {
        super(cropBlock);
        setUnlocalizedName("crystbud_seed");
        setRegistryName("crystbud_seed");
    }
}


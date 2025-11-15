package cahcap.herbalcure.common.item;

import net.minecraft.block.Block;

/**
 * Rosynia Seed
 * Can be planted on farmland to grow Rosynia crops
 */
public class ItemRosyniaSeed extends ItemHerbSeed
{
    public ItemRosyniaSeed(Block cropBlock)
    {
        super(cropBlock);
        setUnlocalizedName("rosynia_seed");
        setRegistryName("rosynia_seed");
    }
}


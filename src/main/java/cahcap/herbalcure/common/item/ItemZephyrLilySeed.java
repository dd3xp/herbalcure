package cahcap.herbalcure.common.item;

import net.minecraft.block.Block;

/**
 * Zephyr Lily Seed
 * Can be planted on farmland to grow Zephyr Lily crops
 */
public class ItemZephyrLilySeed extends ItemHerbSeed
{
    public ItemZephyrLilySeed(Block cropBlock)
    {
        super(cropBlock);
        setUnlocalizedName("zephyr_lily_seed");
        setRegistryName("zephyr_lily_seed");
    }
}


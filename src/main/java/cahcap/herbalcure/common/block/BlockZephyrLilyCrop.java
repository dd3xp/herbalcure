package cahcap.herbalcure.common.block;

import cahcap.herbalcure.common.registry.ModRegistries;
import net.minecraft.item.Item;

/**
 * Zephyr Lily Crop
 * Has 8 growth stages (0-7)
 * Mature stage (7) drops 1 Golden Lilybell and 1 Zephyr Lily Seed
 */
public class BlockZephyrLilyCrop extends BlockHerbCrop
{
    public BlockZephyrLilyCrop()
    {
        super(ModRegistries.itemGoldenLilybell);
        setUnlocalizedName("zephyr_lily_crop");
    }
    
    @Override
    protected net.minecraft.item.Item getSeed()
    {
        return ModRegistries.itemZephyrLilySeed;
    }
}


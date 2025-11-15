package cahcap.herbalcure.common.block;

import cahcap.herbalcure.common.registry.ModRegistries;
import net.minecraft.item.Item;

/**
 * Dewpetal Crop
 * Has 8 growth stages (0-7)
 * Mature stage (7) drops 1 Dewpetal Shard and 1 Dewpetal Seed
 */
public class BlockDewpetalCrop extends BlockHerbCrop
{
    public BlockDewpetalCrop()
    {
        super(ModRegistries.itemDewpetalShard);
        setUnlocalizedName("dewpetal_crop");
    }
    
    @Override
    protected net.minecraft.item.Item getSeed()
    {
        return ModRegistries.itemDewpetalSeed;
    }
}


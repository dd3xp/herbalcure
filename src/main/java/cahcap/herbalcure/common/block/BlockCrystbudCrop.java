package cahcap.herbalcure.common.block;

import cahcap.herbalcure.common.registry.ModRegistries;
import net.minecraft.item.Item;

/**
 * Crystbud Crop
 * Has 8 growth stages (0-7)
 * Mature stage (7) drops 1 Cryst Spine and 1 Crystbud Seed
 */
public class BlockCrystbudCrop extends BlockHerbCrop
{
    public BlockCrystbudCrop()
    {
        super(ModRegistries.itemCrystSpine);
        setUnlocalizedName("crystbud_crop");
    }
    
    @Override
    protected net.minecraft.item.Item getSeed()
    {
        return ModRegistries.itemCrystbudSeed;
    }
}


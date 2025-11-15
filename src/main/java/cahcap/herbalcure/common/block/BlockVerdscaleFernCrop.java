package cahcap.herbalcure.common.block;

import cahcap.herbalcure.common.registry.ModRegistries;
import net.minecraft.item.Item;

/**
 * Verdscale Fern Crop
 * Has 8 growth stages (0-7)
 * Mature stage (7) drops 1 Scaleplate and 1 Verdscale Fern Seed
 */
public class BlockVerdscaleFernCrop extends BlockHerbCrop
{
    public BlockVerdscaleFernCrop()
    {
        super(ModRegistries.itemScaleplate);
        setUnlocalizedName("verdscale_fern_crop");
    }
    
    @Override
    protected net.minecraft.item.Item getSeed()
    {
        return ModRegistries.itemVerdscaleFernSeed;
    }
}


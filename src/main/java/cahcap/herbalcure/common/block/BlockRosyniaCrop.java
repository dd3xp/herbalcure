package cahcap.herbalcure.common.block;

import cahcap.herbalcure.common.registry.ModRegistries;
import net.minecraft.item.Item;

/**
 * Rosynia Crop
 * Has 8 growth stages (0-7)
 * Mature stage (7) drops 1 Heart of Stardream and 1 Mysterious Seed
 */
public class BlockRosyniaCrop extends BlockHerbCrop
{
    public BlockRosyniaCrop()
    {
        super(ModRegistries.itemHeartOfStardream);
        setUnlocalizedName("rosynia_crop");
    }
    
    @Override
    protected net.minecraft.item.Item getSeed()
    {
        return ModRegistries.itemRosyniaSeed;
    }
}


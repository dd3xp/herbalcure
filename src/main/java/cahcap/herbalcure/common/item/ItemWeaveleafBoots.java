package cahcap.herbalcure.common.item;

import cahcap.herbalcure.HerbalCure;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

/**
 * Weaveleaf Boots
 * 1 armor, 90 durability
 * Increases movement speed, affects jump hang time speed, auto-step 1 block
 */
public class ItemWeaveleafBoots extends ItemArmor
{
    public ItemWeaveleafBoots()
    {
        super(ArmorMaterialWeaveleaf.WEAVELEAF, 1, EntityEquipmentSlot.FEET);
        setUnlocalizedName("weaveleaf_boots");
        setRegistryName("weaveleaf_boots");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
        setMaxDamage(90);
    }
}


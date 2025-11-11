package cahcap.herbalcure.common.item;

import cahcap.herbalcure.HerbalCure;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

/**
 * Weaveleaf Leggings
 * 4 armor, 98 durability
 * Reduces fall damage
 */
public class ItemWeaveleafLeggings extends ItemArmor
{
    public ItemWeaveleafLeggings()
    {
        super(ArmorMaterialWeaveleaf.WEAVELEAF, 2, EntityEquipmentSlot.LEGS);
        setUnlocalizedName("weaveleaf_leggings");
        setRegistryName("weaveleaf_leggings");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
        setMaxDamage(98);
    }
}


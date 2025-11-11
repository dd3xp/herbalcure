package cahcap.herbalcure.common.item;

import cahcap.herbalcure.HerbalCure;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

/**
 * Weaveleaf Chestplate
 * 6 armor, 95 durability
 * Permanent Haste 1 buff
 */
public class ItemWeaveleafChestplate extends ItemArmor
{
    public ItemWeaveleafChestplate()
    {
        super(ArmorMaterialWeaveleaf.WEAVELEAF, 1, EntityEquipmentSlot.CHEST);
        setUnlocalizedName("weaveleaf_chestplate");
        setRegistryName("weaveleaf_chestplate");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
        setMaxDamage(95);
    }
}


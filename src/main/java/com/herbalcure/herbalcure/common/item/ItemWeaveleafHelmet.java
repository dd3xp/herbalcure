package com.herbalcure.herbalcure.common.item;

import com.herbalcure.herbalcure.HerbalCure;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

/**
 * Weaveleaf Helmet
 * 1 armor, 92 durability
 * Regenerates 1 HP every 5 seconds
 */
public class ItemWeaveleafHelmet extends ItemArmor
{
    public ItemWeaveleafHelmet()
    {
        super(ArmorMaterialWeaveleaf.WEAVELEAF, 1, EntityEquipmentSlot.HEAD);
        setUnlocalizedName("weaveleaf_helmet");
        setRegistryName("weaveleaf_helmet");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
        setMaxDamage(92);
    }
}


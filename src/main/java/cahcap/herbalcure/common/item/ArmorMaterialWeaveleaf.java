package cahcap.herbalcure.common.item;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Weaveleaf Armor Material
 * Early game armor with durability regeneration
 */
public class ArmorMaterialWeaveleaf
{
    public static final ItemArmor.ArmorMaterial WEAVELEAF = EnumHelper.addArmorMaterial(
        "WEAVELEAF",
        "herbalcure:weaveleaf",
        90, // Max durability (average of 92, 95, 98, 90 = 93.75, rounded to 90)
        new int[]{1, 6, 4, 1}, // Damage reduction: helmet, chestplate, leggings, boots
        0, // Enchantability (same as leather)
        SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
        0.0F // Toughness
    );
}


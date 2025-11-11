package cahcap.herbalcure.common.item;

import cahcap.herbalcure.HerbalCure;
import cahcap.herbalcure.client.model.ModelWeaveleafArmor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.EnumMap;
import java.util.Map;

/**
 * Weaveleaf Leggings
 * 4 armor, 98 durability
 * Reduces fall damage
 */
public class ItemWeaveleafLeggings extends ItemArmor
{
    @SideOnly(Side.CLIENT)
    private static Map<EntityEquipmentSlot, ModelBiped> models;

    public ItemWeaveleafLeggings()
    {
        super(ArmorMaterialWeaveleaf.WEAVELEAF, 2, EntityEquipmentSlot.LEGS);
        setUnlocalizedName("weaveleaf_leggings");
        setRegistryName("weaveleaf_leggings");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
        setMaxDamage(98);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return "herbalcure:textures/models/armor/weaveleaf_armor.png";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped original)
    {
        if (models == null)
        {
            models = new EnumMap<>(EntityEquipmentSlot.class);
        }

        ModelBiped model = models.get(armorSlot);
        if (model == null)
        {
            model = new ModelWeaveleafArmor(armorSlot);
            models.put(armorSlot, model);
        }

        model.setModelAttributes(original);
        return model;
    }
}


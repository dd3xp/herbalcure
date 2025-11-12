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
 * Weaveleaf Helmet
 * 1 armor, 92 durability
 * Regenerates 1 HP every 5 seconds
 */
public class ItemWeaveleafHelmet extends ItemArmor
{
    @SideOnly(Side.CLIENT)
    private static Map<EntityEquipmentSlot, ModelBiped> models;

    public ItemWeaveleafHelmet()
    {
        super(ArmorMaterialWeaveleaf.WEAVELEAF, 1, EntityEquipmentSlot.HEAD);
        setUnlocalizedName("weaveleaf_helmet");
        setRegistryName("weaveleaf_helmet");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
        setMaxDamage(92);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        // All armor pieces use the same texture file
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


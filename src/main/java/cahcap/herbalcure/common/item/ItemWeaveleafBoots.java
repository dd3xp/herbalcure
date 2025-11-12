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
 * Weaveleaf Boots
 * 1 armor, 90 durability
 * Increases movement speed, affects jump hang time speed, auto-step 1 block
 */
public class ItemWeaveleafBoots extends ItemArmor
{
    @SideOnly(Side.CLIENT)
    private static Map<EntityEquipmentSlot, ModelBiped> models;

    public ItemWeaveleafBoots()
    {
        super(ArmorMaterialWeaveleaf.WEAVELEAF, 1, EntityEquipmentSlot.FEET);
        setUnlocalizedName("weaveleaf_boots");
        setRegistryName("weaveleaf_boots");
        setCreativeTab(HerbalCure.CREATIVE_TAB);
        setMaxDamage(90);
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


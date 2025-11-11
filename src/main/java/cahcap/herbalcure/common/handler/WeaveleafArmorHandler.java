package cahcap.herbalcure.common.handler;

import cahcap.herbalcure.HerbalCure;
import cahcap.herbalcure.common.item.ItemWeaveleafBoots;
import cahcap.herbalcure.common.item.ItemWeaveleafChestplate;
import cahcap.herbalcure.common.item.ItemWeaveleafHelmet;
import cahcap.herbalcure.common.item.ItemWeaveleafLeggings;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import java.util.UUID;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Event handler for Weaveleaf Armor special effects
 */
@Mod.EventBusSubscriber(modid = HerbalCure.MODID)
public class WeaveleafArmorHandler
{
    private static final int DURABILITY_REGEN_TICK_INTERVAL = 20; // 1 second (20 ticks)
    private static final int HEALTH_REGEN_TICK_INTERVAL = 100; // 5 seconds (100 ticks)
    private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("8C5B5B5B-5B5B-5B5B-5B5B-5B5B5B5B5B5B");
    
    /**
     * Handle durability regeneration (1 point per second for all armor pieces)
     * Handle health regeneration (1 HP per 5 seconds for helmet)
     * Handle Haste 1 buff (permanent for chestplate)
     */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if (event.phase != TickEvent.Phase.END)
        {
            return;
        }
        
        EntityPlayer player = event.player;
        if (player.world.isRemote)
        {
            return;
        }
        
        // Check if player is wearing Weaveleaf armor
        ItemStack helmet = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack leggings = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        
        boolean wearingWeaveleaf = false;
        
        // Durability regeneration (1 point per second for all armor pieces)
        if (helmet.getItem() instanceof ItemWeaveleafHelmet && !helmet.isEmpty() && helmet.getItemDamage() > 0)
        {
            wearingWeaveleaf = true;
            if (player.ticksExisted % DURABILITY_REGEN_TICK_INTERVAL == 0)
            {
                helmet.setItemDamage(Math.max(0, helmet.getItemDamage() - 1));
            }
        }
        
        if (chestplate.getItem() instanceof ItemWeaveleafChestplate && !chestplate.isEmpty() && chestplate.getItemDamage() > 0)
        {
            wearingWeaveleaf = true;
            if (player.ticksExisted % DURABILITY_REGEN_TICK_INTERVAL == 0)
            {
                chestplate.setItemDamage(Math.max(0, chestplate.getItemDamage() - 1));
            }
        }
        
        if (leggings.getItem() instanceof ItemWeaveleafLeggings && !leggings.isEmpty() && leggings.getItemDamage() > 0)
        {
            wearingWeaveleaf = true;
            if (player.ticksExisted % DURABILITY_REGEN_TICK_INTERVAL == 0)
            {
                leggings.setItemDamage(Math.max(0, leggings.getItemDamage() - 1));
            }
        }
        
        if (boots.getItem() instanceof ItemWeaveleafBoots && !boots.isEmpty() && boots.getItemDamage() > 0)
        {
            wearingWeaveleaf = true;
            if (player.ticksExisted % DURABILITY_REGEN_TICK_INTERVAL == 0)
            {
                boots.setItemDamage(Math.max(0, boots.getItemDamage() - 1));
            }
        }
        
        // Health regeneration (1 HP per 5 seconds for helmet)
        if (helmet.getItem() instanceof ItemWeaveleafHelmet && !helmet.isEmpty())
        {
            if (player.ticksExisted % HEALTH_REGEN_TICK_INTERVAL == 0)
            {
                if (player.getHealth() < player.getMaxHealth())
                {
                    player.heal(1.0F);
                }
            }
        }
        
        // Haste 1 buff (permanent for chestplate)
        // Refresh every 1.5 seconds (30 ticks) instead of every tick for better performance
        if (chestplate.getItem() instanceof ItemWeaveleafChestplate && !chestplate.isEmpty())
        {
            // Only refresh the effect every 30 ticks (1.5 seconds) instead of every tick
            if (player.ticksExisted % 30 == 0)
            {
                player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 40, 0, false, false));
            }
        }
    }
    
    /**
     * Handle fall damage reduction (for leggings)
     * Also increases safe fall height by 1 block if wearing boots (can jump 2 blocks)
     */
    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event)
    {
        if (!(event.getEntityLiving() instanceof EntityPlayer))
        {
            return;
        }
        
        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        ItemStack leggings = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        
        float fallDistance = event.getDistance();
        
        // If wearing boots, increase safe fall height by 1 block (from 3 to 4 blocks)
        // Since boots allow jumping to 2 blocks, safe fall height should be 4 blocks
        if (boots.getItem() instanceof ItemWeaveleafBoots && !boots.isEmpty())
        {
            // If fall distance is 4 blocks or less, completely negate damage
            if (fallDistance <= 4.0F)
            {
                event.setCanceled(true);
                return;
            }
        }
        
        // Reduce fall damage by 30% if wearing leggings
        if (leggings.getItem() instanceof ItemWeaveleafLeggings && !leggings.isEmpty())
        {
            event.setDamageMultiplier(0.7F);
        }
    }
    
    /**
     * Handle movement speed increase and auto-step (for boots)
     * Also handles flying speed boost
     */
    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if (!(event.getEntityLiving() instanceof EntityPlayer))
        {
            return;
        }
        
        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        
        IAttributeInstance movementSpeed = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        
        if (boots.getItem() instanceof ItemWeaveleafBoots && !boots.isEmpty())
        {
            // Increase movement speed (30% faster) using attribute modifier
            // In 1.12.2, operation is an int: 0=ADD, 1=MULTIPLY, 2=MULTIPLY_BASE
            if (movementSpeed.getModifier(SPEED_MODIFIER_UUID) == null)
            {
                movementSpeed.applyModifier(new AttributeModifier(
                    SPEED_MODIFIER_UUID,
                    "Weaveleaf Boots Speed Boost",
                    0.3D, // 30% increase
                    1 // MULTIPLY operation
                ));
            }
            
            // Increase flying speed by 30% (same as movement speed)
            // Default fly speed is 0.05F, so 30% increase makes it 0.065F
            // Apply whenever player has flying capability, not just when actively flying
            if (player.capabilities.allowFlying)
            {
                // Only apply if not already modified (check if it's the default value or our modified value)
                // We'll set it to 1.3x the default (0.05F * 1.3 = 0.065F)
                if (player.capabilities.getFlySpeed() < 0.07F)
                {
                    player.capabilities.setFlySpeed(0.065F);
                }
            }
            
            // Auto-step 1 block
            player.stepHeight = 1.0F;
        }
        else
        {
            // Remove speed modifier if not wearing boots
            if (movementSpeed.getModifier(SPEED_MODIFIER_UUID) != null)
            {
                movementSpeed.removeModifier(SPEED_MODIFIER_UUID);
            }
            
            // Reset flying speed if not wearing boots
            if (player.capabilities.getFlySpeed() > 0.066F)
            {
                player.capabilities.setFlySpeed(0.05F); // Default fly speed
            }
            
            // Reset step height if not wearing boots
            if (player.stepHeight == 1.0F)
            {
                player.stepHeight = 0.6F; // Default step height
            }
        }
    }
    
    /**
     * Handle jump height increase and horizontal movement boost (for boots)
     * Allows jumping to 2 blocks height and increases horizontal movement speed during jump
     */
    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event)
    {
        if (!(event.getEntityLiving() instanceof EntityPlayer))
        {
            return;
        }
        
        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        
        if (boots.getItem() instanceof ItemWeaveleafBoots && !boots.isEmpty())
        {
            // Increase jump height by 50%
            // This makes jump speed consistent with movement speed boost
            double currentMotionY = player.motionY;
            if (currentMotionY > 0.0D)
            {
                // Increase jump speed by 50% (multiply by 1.5)
                player.motionY = currentMotionY * 1.5D;
            }
            
            // Increase horizontal movement speed during jump by 30%
            // This makes jumping forward/backward/sideways faster
            double currentMotionX = player.motionX;
            double currentMotionZ = player.motionZ;
            
            // Only boost horizontal movement if player is actually moving horizontally
            if (Math.abs(currentMotionX) > 0.01D || Math.abs(currentMotionZ) > 0.01D)
            {
                player.motionX = currentMotionX * 1.3D;
                player.motionZ = currentMotionZ * 1.3D;
            }
        }
    }
}


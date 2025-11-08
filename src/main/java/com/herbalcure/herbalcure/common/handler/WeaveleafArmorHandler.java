package com.herbalcure.herbalcure.common.handler;

import com.herbalcure.herbalcure.HerbalCure;
import com.herbalcure.herbalcure.common.item.ItemWeaveleafBoots;
import com.herbalcure.herbalcure.common.item.ItemWeaveleafChestplate;
import com.herbalcure.herbalcure.common.item.ItemWeaveleafHelmet;
import com.herbalcure.herbalcure.common.item.ItemWeaveleafLeggings;
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
            // Increase movement speed (50% faster) using attribute modifier
            // In 1.12.2, operation is an int: 0=ADD, 1=MULTIPLY, 2=MULTIPLY_BASE
            if (movementSpeed.getModifier(SPEED_MODIFIER_UUID) == null)
            {
                movementSpeed.applyModifier(new AttributeModifier(
                    SPEED_MODIFIER_UUID,
                    "Weaveleaf Boots Speed Boost",
                    0.5D, // 50% increase
                    1 // MULTIPLY operation
                ));
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
            
            // Reset step height if not wearing boots
            if (player.stepHeight == 1.0F)
            {
                player.stepHeight = 0.6F; // Default step height
            }
        }
    }
    
    /**
     * Handle jump height increase (for boots)
     * Allows jumping to 2 blocks height
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
            // Increase jump speed by 50% (same as movement speed increase)
            // This makes jump speed consistent with movement speed boost
            double currentMotionY = player.motionY;
            if (currentMotionY > 0.0D)
            {
                // Increase jump speed by 50% (multiply by 1.5)
                player.motionY = currentMotionY * 1.5D;
            }
        }
    }
}


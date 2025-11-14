package cahcap.herbalcure.common.handler;

import cahcap.herbalcure.HerbalCure;
import cahcap.herbalcure.common.item.ItemWeaveleafBoots;
import cahcap.herbalcure.common.item.ItemWeaveleafChestplate;
import cahcap.herbalcure.common.item.ItemWeaveleafHelmet;
import cahcap.herbalcure.common.item.ItemWeaveleafLeggings;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
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
    
    // Speed boost value: 30% of base movement speed (base speed is typically 0.1, so 30% = 0.03)
    // This makes final speed = 130% of base speed
    // Using constant for better performance (no calculation every tick)
    private static final float SPEED_BOOST = 0.03F;
    
    /**
     * Handle durability regeneration (1 point per second for all armor pieces)
     * Durability regeneration works for armor in inventory, not just equipped
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
        
        // Check if player is wearing Weaveleaf armor (for special effects)
        ItemStack helmet = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack leggings = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        
        // Durability regeneration (1 point per second for all armor pieces in inventory)
        // Check all items in player's inventory, including equipped armor
        if (player.ticksExisted % DURABILITY_REGEN_TICK_INTERVAL == 0)
        {
            // Check main inventory (0-35 slots)
            for (int i = 0; i < player.inventory.mainInventory.size(); i++)
            {
                ItemStack stack = player.inventory.mainInventory.get(i);
                if (!stack.isEmpty() && stack.getItemDamage() > 0)
                {
                    if (stack.getItem() instanceof ItemWeaveleafHelmet ||
                        stack.getItem() instanceof ItemWeaveleafChestplate ||
                        stack.getItem() instanceof ItemWeaveleafLeggings ||
                        stack.getItem() instanceof ItemWeaveleafBoots)
                    {
                        stack.setItemDamage(Math.max(0, stack.getItemDamage() - 1));
                    }
                }
            }
            
            // Check armor inventory (36-39 slots: boots, leggings, chestplate, helmet)
            for (int i = 0; i < player.inventory.armorInventory.size(); i++)
            {
                ItemStack stack = player.inventory.armorInventory.get(i);
                if (!stack.isEmpty() && stack.getItemDamage() > 0)
                {
                    if (stack.getItem() instanceof ItemWeaveleafHelmet ||
                        stack.getItem() instanceof ItemWeaveleafChestplate ||
                        stack.getItem() instanceof ItemWeaveleafLeggings ||
                        stack.getItem() instanceof ItemWeaveleafBoots)
                    {
                        stack.setItemDamage(Math.max(0, stack.getItemDamage() - 1));
                    }
                }
            }
        }
        
        // Health regeneration (1 HP per 5 seconds for helmet) - only when equipped
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
        
        // Haste 1 buff (permanent for chestplate) - only when equipped
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
     * Uses moveRelative method to work on ice and other special blocks
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
        
        if (boots.getItem() instanceof ItemWeaveleafBoots && !boots.isEmpty())
        {
            // Auto-step 1 block, but not when sneaking or moving backward
            if (!player.isSneaking() && player.moveForward >= 0F)
            {
                player.stepHeight = 1.0F;
            }
            else
            {
                player.stepHeight = 0.6F; // Default step height when sneaking or moving backward
            }
            
            // Apply movement speed boost using moveRelative (works on ice and other special blocks)
            // This is done on client side only
            // Using moveRelative instead of attribute modifier ensures it works on ice and other special blocks
            if (player.world.isRemote)
            {
                // Apply when player is on ground, moving forward, and not in water
                if (player.onGround && player.moveForward > 0F && !player.isInsideOfMaterial(Material.WATER))
                {
                    // Use constant speed boost value (30% of base movement speed = 0.03)
                    // Final speed = base speed * 1.3 (130% of base speed)
                    // This avoids recalculating every tick for better performance
                    player.moveRelative(0F, 0F, 1F, SPEED_BOOST);
                }
                // Also apply when player is in air (jumping/falling) but not flying, to affect jump hang time speed
                // Use half the speed boost to avoid speed being faster than ground speed
                else if (!player.onGround && !player.capabilities.isFlying && player.moveForward > 0F && !player.isInsideOfMaterial(Material.WATER))
                {
                    // Apply half speed boost during jump/fall to affect jump hang time speed
                    // This ensures jump horizontal speed is consistent but not faster than ground speed
                    player.moveRelative(0F, 0F, 1F, SPEED_BOOST * 0.5F);
                }
            }
        }
        else
        {
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
            
            // Note: Horizontal movement speed during jump is handled by onLivingUpdate
            // using moveRelative with half the speed boost, so we don't need to modify motionX/motionZ here
        }
    }
}


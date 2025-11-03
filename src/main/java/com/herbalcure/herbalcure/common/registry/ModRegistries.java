package com.herbalcure.herbalcure.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import com.herbalcure.herbalcure.HerbalCure;

/**
 * Mod registry manager
 * Uses RegistryEvent to register blocks, items, and other objects
 * This is the recommended registration method according to Forge documentation
 */
@Mod.EventBusSubscriber(modid = HerbalCure.MODID)
public class ModRegistries
{
    /**
     * Register blocks
     * RegistryEvent.Register<Block> is triggered when the Block registry is being registered
     * Block is always triggered first, before Item
     */
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        HerbalCure.getLogger().info("Registering blocks...");
        
        // Register your blocks here
        // Example:
        // event.getRegistry().registerAll(
        //     new BlockHerbGarden().setRegistryName("herb_garden"),
        //     new BlockDryingRack().setRegistryName("drying_rack")
        // );
        
        HerbalCure.getLogger().info("Blocks registered!");
    }

    /**
     * Register items
     * RegistryEvent.Register<Item> is triggered when the Item registry is being registered
     * Item is always triggered second, after Block
     */
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        HerbalCure.getLogger().info("Registering items...");
        
        // Register your items here
        // Example:
        // event.getRegistry().registerAll(
        //     new ItemHerb().setRegistryName("herb"),
        //     new ItemPotion().setRegistryName("potion")
        // );
        
        HerbalCure.getLogger().info("Items registered!");
    }

    /**
     * Create new registries (if needed)
     * RegistryEvent.NewRegistry is triggered when registries are being created
     * If your mod needs to create custom registries, create them here
     */
    @SubscribeEvent
    public static void registerRegistries(RegistryEvent.NewRegistry event)
    {
        // Note: Logger may not be initialized yet when this event fires
        // Only use logger if it's available
        Logger logger = HerbalCure.getLogger();
        if (logger != null) {
            logger.info("Creating new registries if needed...");
        }
        
        // Example: Create custom registry
        // RegistryBuilder<CustomType> builder = new RegistryBuilder<>();
        // builder.setName(new ResourceLocation(HerbalCure.MODID, "custom_type"));
        // builder.setType(CustomType.class);
        // builder.create();
    }
}


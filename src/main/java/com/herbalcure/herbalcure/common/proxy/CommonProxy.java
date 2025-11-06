package com.herbalcure.herbalcure.common.proxy;

import com.herbalcure.herbalcure.HerbalCure;
import com.herbalcure.herbalcure.common.world.gen.WorldGenForestHeartwood;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Common proxy class - shared between server and client
 */
public class CommonProxy
{
    /**
     * Pre-initialization - register blocks, items, etc.
     */
    public void preInit(FMLPreInitializationEvent event)
    {
        // Register blocks, items, entities, etc. here
    }

    /**
     * Initialization - register recipes, event handlers, etc.
     */
    public void init(FMLInitializationEvent event)
    {
        // Register world generator for Forest Heartwood trees
        HerbalCure.getLogger().info("Registering world generator for Forest Heartwood trees...");
        GameRegistry.registerWorldGenerator(new WorldGenForestHeartwood(), 10); // Priority 10 (higher = later)
        HerbalCure.getLogger().info("World generator registered!");
    }

    /**
     * Post-initialization - interact with other mods
     */
    public void postInit(FMLPostInitializationEvent event)
    {
        // Handle interactions with other mods here
    }
}


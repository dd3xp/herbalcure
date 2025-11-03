package com.herbalcure.herbalcure.common.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

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
        // Register recipes, event handlers, etc. here
    }

    /**
     * Post-initialization - interact with other mods
     */
    public void postInit(FMLPostInitializationEvent event)
    {
        // Handle interactions with other mods here
    }
}


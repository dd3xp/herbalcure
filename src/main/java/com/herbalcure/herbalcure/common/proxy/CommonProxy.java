package com.herbalcure.herbalcure.common.proxy;

import com.herbalcure.herbalcure.HerbalCure;
import com.herbalcure.herbalcure.common.world.gen.WorldGenForestHeartwood;
import com.herbalcure.herbalcure.common.world.gen.WorldGenVerdscaleFern;
import com.herbalcure.herbalcure.common.world.gen.WorldGenZephyrLily;
import com.herbalcure.herbalcure.common.world.gen.WorldGenDewpetal;
import com.herbalcure.herbalcure.common.world.gen.WorldGenPyrisage;
import com.herbalcure.herbalcure.common.world.gen.WorldGenRosynia;
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
        
        // Register world generator for Verdscale Fern
        HerbalCure.getLogger().info("Registering world generator for Verdscale Fern...");
        GameRegistry.registerWorldGenerator(new WorldGenVerdscaleFern(), 10); // Priority 10 (higher = later)
        HerbalCure.getLogger().info("Verdscale Fern world generator registered!");
        
        // Register world generator for Zephyr Lily
        HerbalCure.getLogger().info("Registering world generator for Zephyr Lily...");
        GameRegistry.registerWorldGenerator(new WorldGenZephyrLily(), 10); // Priority 10 (higher = later)
        HerbalCure.getLogger().info("Zephyr Lily world generator registered!");
        
        // Register world generator for Dewpetal
        HerbalCure.getLogger().info("Registering world generator for Dewpetal...");
        GameRegistry.registerWorldGenerator(new WorldGenDewpetal(), 10); // Priority 10 (higher = later)
        HerbalCure.getLogger().info("Dewpetal world generator registered!");
        
        // Register world generator for Pyrisage
        HerbalCure.getLogger().info("Registering world generator for Pyrisage...");
        GameRegistry.registerWorldGenerator(new WorldGenPyrisage(), 10); // Priority 10 (higher = later)
        HerbalCure.getLogger().info("Pyrisage world generator registered!");
        
        // Register world generator for Rosynia
        HerbalCure.getLogger().info("Registering world generator for Rosynia...");
        GameRegistry.registerWorldGenerator(new WorldGenRosynia(), 10); // Priority 10 (higher = later)
        HerbalCure.getLogger().info("Rosynia world generator registered!");
    }

    /**
     * Post-initialization - interact with other mods
     */
    public void postInit(FMLPostInitializationEvent event)
    {
        // Handle interactions with other mods here
    }
}


package com.herbalcure.herbalcure.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Logger;

import com.herbalcure.herbalcure.HerbalCure;
import com.herbalcure.herbalcure.common.block.BlockForestHeartwoodLog;
import com.herbalcure.herbalcure.common.block.BlockForestHeartwoodLeaves;
import com.herbalcure.herbalcure.common.block.BlockForestHeartwoodSapling;
import com.herbalcure.herbalcure.common.block.BlockForestBerryBush;
import com.herbalcure.herbalcure.common.item.ItemForestBerry;

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
    public static BlockForestHeartwoodLog blockForestHeartwoodLog;
    public static BlockForestHeartwoodLeaves blockForestHeartwoodLeaves;
    public static BlockForestHeartwoodSapling blockForestHeartwoodSapling;
    public static BlockForestBerryBush blockForestBerryBush;
    public static ItemForestBerry itemForestBerry;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        HerbalCure.getLogger().info("Registering blocks...");
        
        IForgeRegistry<Block> registry = event.getRegistry();
        
        // Register Forest Heartwood Log
        blockForestHeartwoodLog = new BlockForestHeartwoodLog();
        blockForestHeartwoodLog.setRegistryName("forest_heartwood_log");
        blockForestHeartwoodLog.setUnlocalizedName("forest_heartwood_log");
        blockForestHeartwoodLog.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockForestHeartwoodLog);
        
        // Register Forest Heartwood Leaves
        blockForestHeartwoodLeaves = new BlockForestHeartwoodLeaves();
        blockForestHeartwoodLeaves.setRegistryName("forest_heartwood_leaves");
        blockForestHeartwoodLeaves.setUnlocalizedName("forest_heartwood_leaves");
        blockForestHeartwoodLeaves.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockForestHeartwoodLeaves);
        
        // Register Forest Heartwood Sapling
        blockForestHeartwoodSapling = new BlockForestHeartwoodSapling();
        blockForestHeartwoodSapling.setRegistryName("forest_heartwood_sapling");
        blockForestHeartwoodSapling.setUnlocalizedName("forest_heartwood_sapling");
        blockForestHeartwoodSapling.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockForestHeartwoodSapling);
        
        // Register Forest Berry Bush
        blockForestBerryBush = new BlockForestBerryBush();
        blockForestBerryBush.setRegistryName("forest_berry_bush");
        blockForestBerryBush.setUnlocalizedName("forest_berry_bush");
        blockForestBerryBush.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockForestBerryBush);
        
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
        
        IForgeRegistry<Item> registry = event.getRegistry();
        
        // Register ItemBlock for Forest Heartwood Log
        if (blockForestHeartwoodLog != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockForestHeartwoodLog);
            itemBlock.setRegistryName(blockForestHeartwoodLog.getRegistryName());
            itemBlock.setUnlocalizedName(blockForestHeartwoodLog.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        
        // Register ItemBlock for Forest Heartwood Leaves
        if (blockForestHeartwoodLeaves != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockForestHeartwoodLeaves);
            itemBlock.setRegistryName(blockForestHeartwoodLeaves.getRegistryName());
            itemBlock.setUnlocalizedName(blockForestHeartwoodLeaves.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        
        // Register ItemBlock for Forest Heartwood Sapling
        if (blockForestHeartwoodSapling != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockForestHeartwoodSapling);
            itemBlock.setRegistryName(blockForestHeartwoodSapling.getRegistryName());
            itemBlock.setUnlocalizedName(blockForestHeartwoodSapling.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        
        // Register ItemBlock for Forest Berry Bush
        if (blockForestBerryBush != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockForestBerryBush);
            itemBlock.setRegistryName(blockForestBerryBush.getRegistryName());
            itemBlock.setUnlocalizedName(blockForestBerryBush.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        
        // Register Forest Berry
        itemForestBerry = new ItemForestBerry();
        registry.register(itemForestBerry);
        
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


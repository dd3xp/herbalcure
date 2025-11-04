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
import com.herbalcure.herbalcure.common.block.BlockJungleHeartwoodLog;
import com.herbalcure.herbalcure.common.block.BlockJungleHeartwoodLeaves;

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
    public static BlockJungleHeartwoodLog blockJungleHeartwoodLog;
    public static BlockJungleHeartwoodLeaves blockJungleHeartwoodLeaves;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        HerbalCure.getLogger().info("Registering blocks...");
        
        IForgeRegistry<Block> registry = event.getRegistry();
        
        // Register Jungle Heartwood Log
        blockJungleHeartwoodLog = new BlockJungleHeartwoodLog();
        blockJungleHeartwoodLog.setRegistryName("jungle_heartwood_log");
        blockJungleHeartwoodLog.setUnlocalizedName("jungle_heartwood_log");
        blockJungleHeartwoodLog.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockJungleHeartwoodLog);
        
        // Register Jungle Heartwood Leaves
        blockJungleHeartwoodLeaves = new BlockJungleHeartwoodLeaves();
        blockJungleHeartwoodLeaves.setRegistryName("jungle_heartwood_leaves");
        blockJungleHeartwoodLeaves.setUnlocalizedName("jungle_heartwood_leaves");
        blockJungleHeartwoodLeaves.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockJungleHeartwoodLeaves);
        
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
        
        // Register ItemBlock for Jungle Heartwood Log
        if (blockJungleHeartwoodLog != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockJungleHeartwoodLog);
            itemBlock.setRegistryName(blockJungleHeartwoodLog.getRegistryName());
            itemBlock.setUnlocalizedName(blockJungleHeartwoodLog.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        
        // Register ItemBlock for Jungle Heartwood Leaves
        if (blockJungleHeartwoodLeaves != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockJungleHeartwoodLeaves);
            itemBlock.setRegistryName(blockJungleHeartwoodLeaves.getRegistryName());
            itemBlock.setUnlocalizedName(blockJungleHeartwoodLeaves.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        
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


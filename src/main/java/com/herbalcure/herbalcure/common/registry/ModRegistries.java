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
import com.herbalcure.herbalcure.common.block.BlockVerdscaleFern;
import com.herbalcure.herbalcure.common.block.BlockZephyrLily;
import com.herbalcure.herbalcure.common.block.BlockDewpetal;
import com.herbalcure.herbalcure.common.block.BlockPyrisage;
import com.herbalcure.herbalcure.common.block.BlockRosynia;
import com.herbalcure.herbalcure.common.block.BlockCrystbud;
import com.herbalcure.herbalcure.common.item.ItemForestBerry;
import com.herbalcure.herbalcure.common.item.ItemWeaveleafHelmet;
import com.herbalcure.herbalcure.common.item.ItemWeaveleafChestplate;
import com.herbalcure.herbalcure.common.item.ItemWeaveleafLeggings;
import com.herbalcure.herbalcure.common.item.ItemWeaveleafBoots;

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
    public static BlockVerdscaleFern blockVerdscaleFern;
    public static BlockZephyrLily blockZephyrLily;
    public static BlockDewpetal blockDewpetal;
    public static BlockPyrisage blockPyrisage;
    public static BlockRosynia blockRosynia;
    public static BlockCrystbud blockCrystbud;
    public static ItemForestBerry itemForestBerry;
    public static ItemWeaveleafHelmet itemWeaveleafHelmet;
    public static ItemWeaveleafChestplate itemWeaveleafChestplate;
    public static ItemWeaveleafLeggings itemWeaveleafLeggings;
    public static ItemWeaveleafBoots itemWeaveleafBoots;

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
        
        // Register Verdscale Fern
        blockVerdscaleFern = new BlockVerdscaleFern();
        blockVerdscaleFern.setRegistryName("verdscale_fern");
        blockVerdscaleFern.setUnlocalizedName("verdscale_fern");
        blockVerdscaleFern.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockVerdscaleFern);
        
        // Register Zephyr Lily
        blockZephyrLily = new BlockZephyrLily();
        blockZephyrLily.setRegistryName("zephyr_lily");
        blockZephyrLily.setUnlocalizedName("zephyr_lily");
        blockZephyrLily.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockZephyrLily);
        
        // Register Dewpetal
        blockDewpetal = new BlockDewpetal();
        blockDewpetal.setRegistryName("dewpetal");
        blockDewpetal.setUnlocalizedName("dewpetal");
        blockDewpetal.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockDewpetal);
        
        // Register Crystbud (before Pyrisage to appear first in creative tab)
        blockCrystbud = new BlockCrystbud();
        blockCrystbud.setRegistryName("crystbud");
        blockCrystbud.setUnlocalizedName("crystbud");
        blockCrystbud.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockCrystbud);
        
        // Register Pyrisage
        blockPyrisage = new BlockPyrisage();
        blockPyrisage.setRegistryName("pyrisage");
        blockPyrisage.setUnlocalizedName("pyrisage");
        blockPyrisage.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockPyrisage);
        
        // Register Rosynia
        blockRosynia = new BlockRosynia();
        blockRosynia.setRegistryName("rosynia");
        blockRosynia.setUnlocalizedName("rosynia");
        blockRosynia.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockRosynia);
        
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
        
        // Register ItemBlock for Verdscale Fern
        if (blockVerdscaleFern != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockVerdscaleFern);
            itemBlock.setRegistryName(blockVerdscaleFern.getRegistryName());
            itemBlock.setUnlocalizedName(blockVerdscaleFern.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        
        // Register ItemBlock for Zephyr Lily
        if (blockZephyrLily != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockZephyrLily);
            itemBlock.setRegistryName(blockZephyrLily.getRegistryName());
            itemBlock.setUnlocalizedName(blockZephyrLily.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        
        // Register ItemBlock for Dewpetal
        if (blockDewpetal != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockDewpetal);
            itemBlock.setRegistryName(blockDewpetal.getRegistryName());
            itemBlock.setUnlocalizedName(blockDewpetal.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        
        // Register ItemBlock for Crystbud (before Pyrisage to appear first in creative tab)
        if (blockCrystbud != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockCrystbud);
            itemBlock.setRegistryName(blockCrystbud.getRegistryName());
            itemBlock.setUnlocalizedName(blockCrystbud.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        
        // Register ItemBlock for Pyrisage
        if (blockPyrisage != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockPyrisage);
            itemBlock.setRegistryName(blockPyrisage.getRegistryName());
            itemBlock.setUnlocalizedName(blockPyrisage.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        
        // Register ItemBlock for Rosynia
        if (blockRosynia != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockRosynia);
            itemBlock.setRegistryName(blockRosynia.getRegistryName());
            itemBlock.setUnlocalizedName(blockRosynia.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        
        // Register Forest Berry
        itemForestBerry = new ItemForestBerry();
        registry.register(itemForestBerry);
        
        // Register Weaveleaf Armor
        itemWeaveleafHelmet = new ItemWeaveleafHelmet();
        registry.register(itemWeaveleafHelmet);
        
        itemWeaveleafChestplate = new ItemWeaveleafChestplate();
        registry.register(itemWeaveleafChestplate);
        
        itemWeaveleafLeggings = new ItemWeaveleafLeggings();
        registry.register(itemWeaveleafLeggings);
        
        itemWeaveleafBoots = new ItemWeaveleafBoots();
        registry.register(itemWeaveleafBoots);
        
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


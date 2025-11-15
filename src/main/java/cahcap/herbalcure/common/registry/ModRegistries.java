package cahcap.herbalcure.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Logger;

import cahcap.herbalcure.HerbalCure;
import cahcap.herbalcure.common.block.BlockForestHeartwoodLog;
import cahcap.herbalcure.common.block.BlockForestHeartwoodLeaves;
import cahcap.herbalcure.common.block.BlockForestHeartwoodSapling;
import cahcap.herbalcure.common.block.BlockForestBerryBush;
import cahcap.herbalcure.common.block.BlockVerdscaleFern;
import cahcap.herbalcure.common.block.BlockZephyrLily;
import cahcap.herbalcure.common.block.BlockDewpetal;
import cahcap.herbalcure.common.block.BlockPyrisage;
import cahcap.herbalcure.common.block.BlockRosynia;
import cahcap.herbalcure.common.block.BlockCrystbud;
import cahcap.herbalcure.common.block.BlockVerdscaleFernCrop;
import cahcap.herbalcure.common.block.BlockDewpetalCrop;
import cahcap.herbalcure.common.block.BlockZephyrLilyCrop;
import cahcap.herbalcure.common.block.BlockCrystbudCrop;
import cahcap.herbalcure.common.block.BlockPyrisageCrop;
import cahcap.herbalcure.common.block.BlockRosyniaCrop;
import cahcap.herbalcure.common.item.ItemForestBerry;
import cahcap.herbalcure.common.item.ItemWeaveleafHelmet;
import cahcap.herbalcure.common.item.ItemWeaveleafChestplate;
import cahcap.herbalcure.common.item.ItemWeaveleafLeggings;
import cahcap.herbalcure.common.item.ItemWeaveleafBoots;
import cahcap.herbalcure.common.item.ItemVerdscaleFernSeed;
import cahcap.herbalcure.common.item.ItemDewpetalSeed;
import cahcap.herbalcure.common.item.ItemZephyrLilySeed;
import cahcap.herbalcure.common.item.ItemCrystbudSeed;
import cahcap.herbalcure.common.item.ItemPyrisageSeed;
import cahcap.herbalcure.common.item.ItemRosyniaSeed;
import cahcap.herbalcure.common.item.ItemScaleplate;
import cahcap.herbalcure.common.item.ItemDewpetalShard;
import cahcap.herbalcure.common.item.ItemGoldenLilybell;
import cahcap.herbalcure.common.item.ItemCrystSpine;
import cahcap.herbalcure.common.item.ItemBurntNode;
import cahcap.herbalcure.common.item.ItemHeartOfStardream;
import cahcap.herbalcure.common.item.ItemWeaveflowLoop;

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
    public static BlockVerdscaleFernCrop blockVerdscaleFernCrop;
    public static BlockDewpetalCrop blockDewpetalCrop;
    public static BlockZephyrLilyCrop blockZephyrLilyCrop;
    public static BlockCrystbudCrop blockCrystbudCrop;
    public static BlockPyrisageCrop blockPyrisageCrop;
    public static BlockRosyniaCrop blockRosyniaCrop;
    public static ItemForestBerry itemForestBerry;
    public static ItemWeaveleafHelmet itemWeaveleafHelmet;
    public static ItemWeaveleafChestplate itemWeaveleafChestplate;
    public static ItemWeaveleafLeggings itemWeaveleafLeggings;
    public static ItemWeaveleafBoots itemWeaveleafBoots;
    public static ItemVerdscaleFernSeed itemVerdscaleFernSeed;
    public static ItemDewpetalSeed itemDewpetalSeed;
    public static ItemZephyrLilySeed itemZephyrLilySeed;
    public static ItemCrystbudSeed itemCrystbudSeed;
    public static ItemPyrisageSeed itemPyrisageSeed;
    public static ItemRosyniaSeed itemRosyniaSeed;
    public static ItemScaleplate itemScaleplate;
    public static ItemDewpetalShard itemDewpetalShard;
    public static ItemGoldenLilybell itemGoldenLilybell;
    public static ItemCrystSpine itemCrystSpine;
    public static ItemBurntNode itemBurntNode;
    public static ItemHeartOfStardream itemHeartOfStardream;
    public static ItemWeaveflowLoop itemWeaveflowLoop;

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
        
        // Register Herb Flowers and Crops - Order: Verdscale Fern, Dewpetal, Zephyr Lily, Crystbud, Pyrisage, Rosynia
        // For each herb: Flower -> Crop
        
        // Verdscale Fern: Flower -> Crop
        blockVerdscaleFern = new BlockVerdscaleFern();
        blockVerdscaleFern.setRegistryName("verdscale_fern");
        blockVerdscaleFern.setUnlocalizedName("verdscale_fern");
        blockVerdscaleFern.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockVerdscaleFern);
        blockVerdscaleFernCrop = new BlockVerdscaleFernCrop();
        blockVerdscaleFernCrop.setRegistryName("verdscale_fern_crop");
        blockVerdscaleFernCrop.setUnlocalizedName("verdscale_fern_crop");
        registry.register(blockVerdscaleFernCrop);
        
        // Dewpetal: Flower -> Crop
        blockDewpetal = new BlockDewpetal();
        blockDewpetal.setRegistryName("dewpetal");
        blockDewpetal.setUnlocalizedName("dewpetal");
        blockDewpetal.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockDewpetal);
        blockDewpetalCrop = new BlockDewpetalCrop();
        blockDewpetalCrop.setRegistryName("dewpetal_crop");
        blockDewpetalCrop.setUnlocalizedName("dewpetal_crop");
        registry.register(blockDewpetalCrop);
        
        // Zephyr Lily: Flower -> Crop
        blockZephyrLily = new BlockZephyrLily();
        blockZephyrLily.setRegistryName("zephyr_lily");
        blockZephyrLily.setUnlocalizedName("zephyr_lily");
        blockZephyrLily.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockZephyrLily);
        blockZephyrLilyCrop = new BlockZephyrLilyCrop();
        blockZephyrLilyCrop.setRegistryName("zephyr_lily_crop");
        blockZephyrLilyCrop.setUnlocalizedName("zephyr_lily_crop");
        registry.register(blockZephyrLilyCrop);
        
        // Crystbud: Flower -> Crop
        blockCrystbud = new BlockCrystbud();
        blockCrystbud.setRegistryName("crystbud");
        blockCrystbud.setUnlocalizedName("crystbud");
        blockCrystbud.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockCrystbud);
        blockCrystbudCrop = new BlockCrystbudCrop();
        blockCrystbudCrop.setRegistryName("crystbud_crop");
        blockCrystbudCrop.setUnlocalizedName("crystbud_crop");
        registry.register(blockCrystbudCrop);
        
        // Pyrisage: Flower -> Crop
        blockPyrisage = new BlockPyrisage();
        blockPyrisage.setRegistryName("pyrisage");
        blockPyrisage.setUnlocalizedName("pyrisage");
        blockPyrisage.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockPyrisage);
        blockPyrisageCrop = new BlockPyrisageCrop();
        blockPyrisageCrop.setRegistryName("pyrisage_crop");
        blockPyrisageCrop.setUnlocalizedName("pyrisage_crop");
        registry.register(blockPyrisageCrop);
        
        // Rosynia: Flower -> Crop
        blockRosynia = new BlockRosynia();
        blockRosynia.setRegistryName("rosynia");
        blockRosynia.setUnlocalizedName("rosynia");
        blockRosynia.setCreativeTab(HerbalCure.CREATIVE_TAB);
        registry.register(blockRosynia);
        blockRosyniaCrop = new BlockRosyniaCrop();
        blockRosyniaCrop.setRegistryName("rosynia_crop");
        blockRosyniaCrop.setUnlocalizedName("rosynia_crop");
        registry.register(blockRosyniaCrop);
        
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
        
        // Register Forest Berry (before herbs)
        itemForestBerry = new ItemForestBerry();
        registry.register(itemForestBerry);
        
        // Register Herbs - Order: Verdscale Fern, Dewpetal, Zephyr Lily, Crystbud, Pyrisage, Rosynia
        // For each herb: Flower (ItemBlock) -> Seed -> Product
        
        // Verdscale Fern: Flower -> Seed -> Product
        if (blockVerdscaleFern != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockVerdscaleFern);
            itemBlock.setRegistryName(blockVerdscaleFern.getRegistryName());
            itemBlock.setUnlocalizedName(blockVerdscaleFern.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        itemVerdscaleFernSeed = new ItemVerdscaleFernSeed(blockVerdscaleFernCrop);
        registry.register(itemVerdscaleFernSeed);
        itemScaleplate = new ItemScaleplate();
        registry.register(itemScaleplate);
        
        // Dewpetal: Flower -> Seed -> Product
        if (blockDewpetal != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockDewpetal);
            itemBlock.setRegistryName(blockDewpetal.getRegistryName());
            itemBlock.setUnlocalizedName(blockDewpetal.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        itemDewpetalSeed = new ItemDewpetalSeed(blockDewpetalCrop);
        registry.register(itemDewpetalSeed);
        itemDewpetalShard = new ItemDewpetalShard();
        registry.register(itemDewpetalShard);
        
        // Zephyr Lily: Flower -> Seed -> Product
        if (blockZephyrLily != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockZephyrLily);
            itemBlock.setRegistryName(blockZephyrLily.getRegistryName());
            itemBlock.setUnlocalizedName(blockZephyrLily.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        itemZephyrLilySeed = new ItemZephyrLilySeed(blockZephyrLilyCrop);
        registry.register(itemZephyrLilySeed);
        itemGoldenLilybell = new ItemGoldenLilybell();
        registry.register(itemGoldenLilybell);
        
        // Crystbud: Flower -> Seed -> Product
        if (blockCrystbud != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockCrystbud);
            itemBlock.setRegistryName(blockCrystbud.getRegistryName());
            itemBlock.setUnlocalizedName(blockCrystbud.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        itemCrystbudSeed = new ItemCrystbudSeed(blockCrystbudCrop);
        registry.register(itemCrystbudSeed);
        itemCrystSpine = new ItemCrystSpine();
        registry.register(itemCrystSpine);
        
        // Pyrisage: Flower -> Seed -> Product
        if (blockPyrisage != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockPyrisage);
            itemBlock.setRegistryName(blockPyrisage.getRegistryName());
            itemBlock.setUnlocalizedName(blockPyrisage.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        itemPyrisageSeed = new ItemPyrisageSeed(blockPyrisageCrop);
        registry.register(itemPyrisageSeed);
        itemBurntNode = new ItemBurntNode();
        registry.register(itemBurntNode);
        
        // Rosynia: Flower -> Seed -> Product
        if (blockRosynia != null)
        {
            ItemBlock itemBlock = new ItemBlock(blockRosynia);
            itemBlock.setRegistryName(blockRosynia.getRegistryName());
            itemBlock.setUnlocalizedName(blockRosynia.getUnlocalizedName());
            itemBlock.setCreativeTab(HerbalCure.CREATIVE_TAB);
            registry.register(itemBlock);
        }
        itemRosyniaSeed = new ItemRosyniaSeed(blockRosyniaCrop);
        registry.register(itemRosyniaSeed);
        itemHeartOfStardream = new ItemHeartOfStardream();
        registry.register(itemHeartOfStardream);
        
        // Register Weaveflow Loop
        itemWeaveflowLoop = new ItemWeaveflowLoop();
        registry.register(itemWeaveflowLoop);
        
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


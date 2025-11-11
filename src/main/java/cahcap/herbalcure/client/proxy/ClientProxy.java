package cahcap.herbalcure.client.proxy;

import cahcap.herbalcure.client.handler.BlockColorHandler;
import cahcap.herbalcure.common.proxy.CommonProxy;
import cahcap.herbalcure.common.registry.ModRegistries;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import cahcap.herbalcure.HerbalCure;

/**
 * Client proxy class - client-side only code (rendering, GUI, etc.)
 */
@Mod.EventBusSubscriber(modid = HerbalCure.MODID)
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        // Register models, textures, and other client-side only content here
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        // Register block color handlers for biome-based coloring
        BlockColors blockColors = net.minecraft.client.Minecraft.getMinecraft().getBlockColors();
        BlockColorHandler.registerBlockColorHandlers(blockColors);
        
        // Register item color handlers for inventory/hand rendering
        ItemColors itemColors = net.minecraft.client.Minecraft.getMinecraft().getItemColors();
        if (ModRegistries.blockForestHeartwoodLeaves != null)
        {
            ItemBlock itemBlock = (ItemBlock)Item.getItemFromBlock(ModRegistries.blockForestHeartwoodLeaves);
            if (itemBlock != null)
            {
                itemColors.registerItemColorHandler(BlockColorHandler.LEAVES_ITEM_COLOR, itemBlock);
            }
        }
        
        // Register berry bush item color handler (uses same color as leaves)
        if (ModRegistries.blockForestBerryBush != null)
        {
            ItemBlock itemBlock = (ItemBlock)Item.getItemFromBlock(ModRegistries.blockForestBerryBush);
            if (itemBlock != null)
            {
                itemColors.registerItemColorHandler(BlockColorHandler.LEAVES_ITEM_COLOR, itemBlock);
            }
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
        // Client-side post-initialization
    }

    /**
     * Register item models for rendering in inventory and hand
     * This is called automatically by Forge when ModelRegistryEvent fires
     */
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        // Register model for Forest Heartwood Log ItemBlock
        if (ModRegistries.blockForestHeartwoodLog != null)
        {
            Item itemBlock = Item.getItemFromBlock(ModRegistries.blockForestHeartwoodLog);
            if (itemBlock != null)
            {
                ModelLoader.setCustomModelResourceLocation(
                    itemBlock,
                    0,
                    new ModelResourceLocation(itemBlock.getRegistryName(), "inventory")
                );
            }
        }
        
        // Register model for Forest Heartwood Leaves ItemBlock
        if (ModRegistries.blockForestHeartwoodLeaves != null)
        {
            Item itemBlock = Item.getItemFromBlock(ModRegistries.blockForestHeartwoodLeaves);
            if (itemBlock != null)
            {
                ModelLoader.setCustomModelResourceLocation(
                    itemBlock,
                    0,
                    new ModelResourceLocation(itemBlock.getRegistryName(), "inventory")
                );
            }
        }
        
        // Register model for Forest Heartwood Sapling ItemBlock (same as Log and Leaves)
        if (ModRegistries.blockForestHeartwoodSapling != null)
        {
            Item itemBlock = Item.getItemFromBlock(ModRegistries.blockForestHeartwoodSapling);
            if (itemBlock != null)
            {
                ModelLoader.setCustomModelResourceLocation(
                    itemBlock,
                    0,
                    new ModelResourceLocation(itemBlock.getRegistryName(), "inventory")
                );
            }
            // Register block state mapper to ignore TYPE property
            StateMap.Builder stateMapBuilder = new StateMap.Builder();
            stateMapBuilder.ignore(net.minecraft.block.BlockSapling.TYPE);
            ModelLoader.setCustomStateMapper(ModRegistries.blockForestHeartwoodSapling, stateMapBuilder.build());
        }
        
        // Register model for Forest Berry
        if (ModRegistries.itemForestBerry != null)
        {
            ModelLoader.setCustomModelResourceLocation(
                ModRegistries.itemForestBerry,
                0,
                new ModelResourceLocation(ModRegistries.itemForestBerry.getRegistryName(), "inventory")
            );
        }
        
        // Register model for Forest Berry Bush ItemBlock
        if (ModRegistries.blockForestBerryBush != null)
        {
            Item itemBlock = Item.getItemFromBlock(ModRegistries.blockForestBerryBush);
            if (itemBlock != null)
            {
                // Register models for all 3 stages (age 0, 1, 2)
                for (int age = 0; age <= 2; age++)
                {
                    ModelLoader.setCustomModelResourceLocation(
                        itemBlock,
                        age,
                        new ModelResourceLocation(ModRegistries.blockForestBerryBush.getRegistryName(), "age=" + age)
                    );
                }
            }
        }
        
        // Register model for Verdscale Fern ItemBlock
        if (ModRegistries.blockVerdscaleFern != null)
        {
            Item itemBlock = Item.getItemFromBlock(ModRegistries.blockVerdscaleFern);
            if (itemBlock != null)
            {
                ModelLoader.setCustomModelResourceLocation(
                    itemBlock,
                    0,
                    new ModelResourceLocation(itemBlock.getRegistryName(), "inventory")
                );
            }
        }
        
        // Register model for Zephyr Lily ItemBlock
        if (ModRegistries.blockZephyrLily != null)
        {
            Item itemBlock = Item.getItemFromBlock(ModRegistries.blockZephyrLily);
            if (itemBlock != null)
            {
                ModelLoader.setCustomModelResourceLocation(
                    itemBlock,
                    0,
                    new ModelResourceLocation(itemBlock.getRegistryName(), "inventory")
                );
            }
        }
        
        // Register model for Dewpetal ItemBlock
        if (ModRegistries.blockDewpetal != null)
        {
            Item itemBlock = Item.getItemFromBlock(ModRegistries.blockDewpetal);
            if (itemBlock != null)
            {
                ModelLoader.setCustomModelResourceLocation(
                    itemBlock,
                    0,
                    new ModelResourceLocation(itemBlock.getRegistryName(), "inventory")
                );
            }
        }
        
        // Register model for Crystbud ItemBlock (before Pyrisage to appear first in creative tab)
        if (ModRegistries.blockCrystbud != null)
        {
            Item itemBlock = Item.getItemFromBlock(ModRegistries.blockCrystbud);
            if (itemBlock != null)
            {
                ModelLoader.setCustomModelResourceLocation(
                    itemBlock,
                    0,
                    new ModelResourceLocation(itemBlock.getRegistryName(), "inventory")
                );
            }
        }
        
        // Register model for Pyrisage ItemBlock
        if (ModRegistries.blockPyrisage != null)
        {
            Item itemBlock = Item.getItemFromBlock(ModRegistries.blockPyrisage);
            if (itemBlock != null)
            {
                ModelLoader.setCustomModelResourceLocation(
                    itemBlock,
                    0,
                    new ModelResourceLocation(itemBlock.getRegistryName(), "inventory")
                );
            }
        }
        
        // Register model for Rosynia ItemBlock
        if (ModRegistries.blockRosynia != null)
        {
            Item itemBlock = Item.getItemFromBlock(ModRegistries.blockRosynia);
            if (itemBlock != null)
            {
                ModelLoader.setCustomModelResourceLocation(
                    itemBlock,
                    0,
                    new ModelResourceLocation(itemBlock.getRegistryName(), "inventory")
                );
            }
        }
        
        // Register models for Weaveleaf Armor
        if (ModRegistries.itemWeaveleafHelmet != null)
        {
            ModelLoader.setCustomModelResourceLocation(
                ModRegistries.itemWeaveleafHelmet,
                0,
                new ModelResourceLocation(ModRegistries.itemWeaveleafHelmet.getRegistryName(), "inventory")
            );
        }
        
        if (ModRegistries.itemWeaveleafChestplate != null)
        {
            ModelLoader.setCustomModelResourceLocation(
                ModRegistries.itemWeaveleafChestplate,
                0,
                new ModelResourceLocation(ModRegistries.itemWeaveleafChestplate.getRegistryName(), "inventory")
            );
        }
        
        if (ModRegistries.itemWeaveleafLeggings != null)
        {
            ModelLoader.setCustomModelResourceLocation(
                ModRegistries.itemWeaveleafLeggings,
                0,
                new ModelResourceLocation(ModRegistries.itemWeaveleafLeggings.getRegistryName(), "inventory")
            );
        }
        
        if (ModRegistries.itemWeaveleafBoots != null)
        {
            ModelLoader.setCustomModelResourceLocation(
                ModRegistries.itemWeaveleafBoots,
                0,
                new ModelResourceLocation(ModRegistries.itemWeaveleafBoots.getRegistryName(), "inventory")
            );
        }
    }

}


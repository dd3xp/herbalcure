package com.herbalcure.herbalcure.client.proxy;

import com.herbalcure.herbalcure.client.handler.BlockColorHandler;
import com.herbalcure.herbalcure.common.proxy.CommonProxy;
import com.herbalcure.herbalcure.common.registry.ModRegistries;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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

import com.herbalcure.herbalcure.HerbalCure;

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
        if (ModRegistries.blockJungleHeartwoodLeaves != null)
        {
            ItemBlock itemBlock = (ItemBlock)Item.getItemFromBlock(ModRegistries.blockJungleHeartwoodLeaves);
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
        // Register model for Jungle Heartwood Log ItemBlock
        if (ModRegistries.blockJungleHeartwoodLog != null)
        {
            Item itemBlock = Item.getItemFromBlock(ModRegistries.blockJungleHeartwoodLog);
            if (itemBlock != null)
            {
                ModelLoader.setCustomModelResourceLocation(
                    itemBlock,
                    0,
                    new ModelResourceLocation(itemBlock.getRegistryName(), "inventory")
                );
            }
        }
        
        // Register model for Jungle Heartwood Leaves ItemBlock
        if (ModRegistries.blockJungleHeartwoodLeaves != null)
        {
            Item itemBlock = Item.getItemFromBlock(ModRegistries.blockJungleHeartwoodLeaves);
            if (itemBlock != null)
            {
                ModelLoader.setCustomModelResourceLocation(
                    itemBlock,
                    0,
                    new ModelResourceLocation(itemBlock.getRegistryName(), "inventory")
                );
            }
        }
    }
}


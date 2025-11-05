package com.herbalcure.herbalcure;

import com.herbalcure.herbalcure.client.proxy.ClientProxy;
import com.herbalcure.herbalcure.common.creativetab.HerbalCureCreativeTab;
import com.herbalcure.herbalcure.common.proxy.CommonProxy;
import com.herbalcure.herbalcure.server.proxy.ServerProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/**
 * HerbalCure Mod main class
 * This is the mod entry point, marked with @Mod annotation
 */
@Mod(
    modid = HerbalCure.MODID, 
    name = HerbalCure.NAME, 
    version = HerbalCure.VERSION, 
    useMetadata = true, 
    updateJSON = "https://raw.githubusercontent.com/dd3xp/herbalcure/main/update.json", 
    certificateFingerprint = "c66c93d47927d06d9ac4aab9cb6b637a0f638d2d"
    )
public class HerbalCure
{
    public static final String MODID = "herbalcure";
    public static final String NAME = "HerbalCure";
    public static final String VERSION = "1.0";

    /**
     * Creative tab for HerbalCure mod items
     * All mod items will appear in this creative tab
     */
    public static final CreativeTabs CREATIVE_TAB = new HerbalCureCreativeTab();

    private static Logger logger;

    /**
     * Proxy instance - automatically selects client or server proxy based on runtime environment
     */
    @SidedProxy(
        clientSide = "com.herbalcure.herbalcure.client.proxy.ClientProxy",
        serverSide = "com.herbalcure.herbalcure.server.proxy.ServerProxy"
    )
    public static CommonProxy proxy;

    /**
     * Get the logger instance
     */
    public static Logger getLogger()
    {
        return logger;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        logger.info("HerbalCure pre-initializing...");
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("HerbalCure initializing...");
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        logger.info("HerbalCure post-initializing...");
        proxy.postInit(event);
        logger.info("HerbalCure mod loaded successfully!");
    }
}


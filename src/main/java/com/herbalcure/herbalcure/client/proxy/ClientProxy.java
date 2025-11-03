package com.herbalcure.herbalcure.client.proxy;

import com.herbalcure.herbalcure.common.proxy.CommonProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

/**
 * Client proxy class - client-side only code (rendering, GUI, etc.)
 */
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
        // Register client-side only event handlers, renderers, etc. here
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
        // Client-side post-initialization
    }
}


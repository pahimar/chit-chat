package com.pahimar.chitchat.proxy;

import net.minecraftforge.fml.common.event.*;

public interface IProxy {

    ClientProxy getClientProxy();

    void onServerStarting(FMLServerStartingEvent event);

    void onPreInit(FMLPreInitializationEvent event);

    void onInit(FMLInitializationEvent event);

    void onPostInit(FMLPostInitializationEvent event);

    void onServerStopping(FMLServerStoppingEvent event);

    void registerEventHandlers();
}

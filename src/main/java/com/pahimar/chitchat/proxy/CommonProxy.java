package com.pahimar.chitchat.proxy;

import com.pahimar.chitchat.configuration.ConfigurationHandler;
import net.minecraftforge.fml.common.event.*;

public abstract class CommonProxy implements IProxy {

    @Override
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
    }

    @Override
    public void onInit(FMLInitializationEvent event) {

    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {

    }

    @Override
    public void onServerStopping(FMLServerStoppingEvent event) {

    }

    @Override
    public void registerEventHandlers() {

    }
}

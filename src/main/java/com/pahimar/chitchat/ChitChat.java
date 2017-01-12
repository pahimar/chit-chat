package com.pahimar.chitchat;

import com.pahimar.chitchat.proxy.IProxy;
import com.pahimar.chitchat.reference.Messages;
import com.pahimar.chitchat.util.helper.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = ChitChat.MOD_ID,
        name = "Chit Chat",
        certificateFingerprint = ChitChat.FINGERPRINT,
        version = "@MOD_VERSION@",
        guiFactory = "com.pahimar.chitchat.client.gui.GuiFactory",
        updateJSON = "http://chitchat.pahimar.com/update/versions.json")
public class ChitChat {

    public static final String MOD_ID = "chitchat";
    static final String FINGERPRINT = "@FINGERPRINT@";

    @Mod.Instance(ChitChat.MOD_ID)
    public static ChitChat instance;

    @SidedProxy(clientSide = "com.pahimar.chitchat.proxy.ClientProxy", serverSide = "com.pahimar.chitchat.proxy.ServerProxy")
    public static IProxy proxy;

    @Mod.EventHandler
    public void onInvalidFingerprint(FMLFingerprintViolationEvent event) {

        if (FINGERPRINT.equals("@FINGERPRINT@")) {
            LogHelper.info(Messages.NO_FINGERPRINT_MESSAGE);
        }
        else {
            LogHelper.warn(Messages.INVALID_FINGERPRINT_MESSAGE);
        }
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        proxy.onPreInit(event);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.onInit(event);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        proxy.onPostInit(event);
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        proxy.onServerStarting(event);
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        proxy.onServerStopping(event);
    }
}

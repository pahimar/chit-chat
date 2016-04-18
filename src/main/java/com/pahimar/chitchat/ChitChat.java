package com.pahimar.chitchat;

import com.pahimar.chitchat.command.CommandHandler;
import com.pahimar.chitchat.proxy.IProxy;
import com.pahimar.chitchat.reference.Messages;
import com.pahimar.chitchat.reference.Reference;
import com.pahimar.chitchat.util.helper.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, certificateFingerprint = Reference.FINGERPRINT, version = Reference.VERSION)
public class ChitChat
{
    @Mod.Instance(com.pahimar.chitchat.reference.Reference.MOD_ID)
    public static ChitChat instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event)
    {
        if (Reference.FINGERPRINT.equals("@FINGERPRINT@"))
        {
            LogHelper.info(Messages.NO_FINGERPRINT_MESSAGE);
        }
        else
        {
            LogHelper.warn(Messages.INVALID_FINGERPRINT_MESSAGE);
        }
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        CommandHandler.initCommands(event);
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        // TODO: Load in configuration and registries from disk
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event)
    {
        // Register the Items Event Handler
        proxy.registerEventHandlers();
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event)
    {
        // TODO: Serialize the contents of the registries to disk
    }
}

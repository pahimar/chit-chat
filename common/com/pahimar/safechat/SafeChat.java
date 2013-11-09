package com.pahimar.safechat;

import com.pahimar.safechat.chat.ChatListener;
import com.pahimar.safechat.lib.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER, dependencies = Reference.DEPENDENCIES, certificateFingerprint = Reference.FINGERPRINT)
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class SafeChat {
    
    @Instance(com.pahimar.safechat.lib.Reference.MOD_ID)
    public static SafeChat instance;
    
    @EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event) {
        
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.instance().registerChatListener(new ChatListener());
    }
}

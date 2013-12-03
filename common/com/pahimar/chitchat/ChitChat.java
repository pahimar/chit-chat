package com.pahimar.chitchat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;
import com.pahimar.chitchat.banned.BannedWord;
import com.pahimar.chitchat.banned.BannedWordRegistry;
import com.pahimar.chitchat.chat.ChatListener;
import com.pahimar.chitchat.configuration.ConfigurationHandler;
import com.pahimar.chitchat.helper.LogHelper;
import com.pahimar.chitchat.lib.Reference;
import com.pahimar.chitchat.proxy.IProxy;
import com.pahimar.chitchat.strike.ConnectionHandler;
import com.pahimar.chitchat.strike.StrikeRegistryTickHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * ChitChat
 * 
 * ChitChat
 * 
 * @author pahimar
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 * 
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER, certificateFingerprint = Reference.FINGERPRINT)
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ChitChat {

    @Instance(com.pahimar.chitchat.lib.Reference.MOD_ID)
    public static ChitChat instance;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event) {

        if (Reference.FINGERPRINT.equals("@FINGERPRINT@")) {
            LogHelper.warning("The copy of ChitChat that you are running is a development version of the mod, and as such may be unstable and/or incomplete.");
        }
        else {
            LogHelper.severe("The copy of ChitChat that you are running has been modified from the original, and unpredictable things may happen. Please consider re-downloading the original version of the mod.");
        }
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {

        // TODO Finish this
        // CommandHandler.initCommands(event);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        // Initialize the log helper
        LogHelper.init();

        // Initialize the configuration 
        ConfigurationHandler.init(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MOD_ID.toLowerCase() + File.separator);
        
        // Register the Strike Registry tick handler
        TickRegistry.registerScheduledTickHandler(new StrikeRegistryTickHandler(), Side.SERVER);

        // Register the chat listener
        NetworkRegistry.instance().registerChatListener(new ChatListener());
        
        // Register the connection listener
        NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());
        
        testJson(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MOD_ID.toLowerCase() + File.separator);
    }

    /**
     * Just a test method at the moment
     * @see http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/stream/JsonWriter.html
     */
    private static void testJson(String path) {
        
        String filePath = path + "bannedWords.json";
        JsonWriter jsonWriter = null;
        
        try {
            
            jsonWriter = new JsonWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
            jsonWriter.setIndent("    ");
            
            Gson gson = new GsonBuilder().create();
            jsonWriter.beginArray();
            for (String bannedWord : BannedWordRegistry.getBannedWordMap().keySet()) {
                BannedWord bannedWordObject = BannedWordRegistry.getBannedWordMap().get(bannedWord);
                jsonWriter.beginObject();
                jsonWriter.name("bannedText").value(bannedWordObject.getBannedText());
                jsonWriter.name("mustStartWith").value(bannedWordObject.mustStartWithBannedText());
                jsonWriter.name("mustEndWith").value(bannedWordObject.mustEndWithBannedText());
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                jsonWriter.close();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

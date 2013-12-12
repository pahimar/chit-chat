package com.pahimar.chitchat;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pahimar.chitchat.banned.BannedWord;
import com.pahimar.chitchat.banned.BannedWordRegistry;
import com.pahimar.chitchat.chat.ChatListener;
import com.pahimar.chitchat.configuration.ConfigurationHandler;
import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.helper.FileHelper;
import com.pahimar.chitchat.helper.JsonFileHelper;
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
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
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
        
        // Set the config folder path
        Settings.CONFIG_DIRECTORY_PATH = event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MOD_ID.toLowerCase() + File.separator;

        // Initialize the configuration 
        ConfigurationHandler.init(Settings.CONFIG_DIRECTORY_PATH);
        
        // Register the Strike Registry tick handler
        TickRegistry.registerScheduledTickHandler(new StrikeRegistryTickHandler(), Side.SERVER);

        // Register the chat listener
        NetworkRegistry.instance().registerChatListener(new ChatListener());
        
        // Register the connection listener
        NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());
        
        BannedWordRegistry.getInstance();
        BannedWordRegistry.getInstance().addBannedWord("this");
        BannedWordRegistry.getInstance().addBannedWord("is");
        BannedWordRegistry.getInstance().addBannedWord("a");
        BannedWordRegistry.getInstance().addBannedWord("test");
        
        BannedWordRegistry.getInstance().addBannedWord(new BannedWord("this", false, false));
        BannedWordRegistry.getInstance().addBannedWord(new BannedWord("is", false, false));
        BannedWordRegistry.getInstance().addBannedWord(new BannedWord("a", false, false));
        BannedWordRegistry.getInstance().addBannedWord(new BannedWord("test", false, false));
    }
    
    @EventHandler
    public void serverShutdown(FMLServerStoppedEvent event) {
        
        List<BannedWord> customBannedWords = BannedWordRegistry.getInstance().getCustomBannedWordList();

        List<BannedWord> simpleBannedWords = new ArrayList<BannedWord>();
        List<BannedWord> advancedBannedWords = new ArrayList<BannedWord>();
        
        for (BannedWord bannedWord : customBannedWords) {
            
            if (bannedWord.isSimple() && !simpleBannedWords.contains(bannedWord)) {
                simpleBannedWords.add(bannedWord);
            }
            else {
                if (!advancedBannedWords.contains(bannedWord)) {
                    advancedBannedWords.add(bannedWord);
                }
            }
        }
        
        Collections.sort(simpleBannedWords);
        Collections.sort(advancedBannedWords);
        
        FileHelper.writeBannedWordsToFile(Reference.SIMPLE_BANNED_WORDS_FILE_LOCATION, simpleBannedWords);
        JsonFileHelper.writeBannedWordsToFile(Reference.ADVANCED_BANNED_WORDS_FILE_LOCATION, advancedBannedWords);
    }
}

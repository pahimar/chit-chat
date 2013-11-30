package com.pahimar.chitchat.banned;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.lib.Reference;

/**
 * ChitChat
 * 
 * BlackList
 * 
 * @author pahimar
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 * 
 */
public class BannedWordRegistry {

    private static BannedWordRegistry bannedWordRegistry;
    
    private static Map<String, BannedWord> bannedWordMap;
    
    private BannedWordRegistry() {
        
    }
    
    public static void init() {
        
        if (bannedWordRegistry == null) {
            bannedWordRegistry = new BannedWordRegistry();
            loadBannedWordMap();
        }
    }
    
    
    public static Map<String, BannedWord> getBannedWordMap() {
        
        init();
        
        return ImmutableMap.copyOf(bannedWordMap);
    }
    
    private static void loadBannedWordMap() {
        
        if (bannedWordMap == null) {
            bannedWordMap = new HashMap<String, BannedWord>();
        }
        
        if (Settings.DEFAULT_BAN_LIST_ENABLED) {
            
            InputStream inputStream = null; 
            BufferedReader reader = null;
            
            try {
                inputStream = bannedWordRegistry.getClass().getResourceAsStream(Reference.DEFAULT_BLACKLIST_FILE_LOCATION);
                
                if (inputStream != null) {
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = null;
                    
                    while ((line = reader.readLine()) != null) {
                        if (!bannedWordMap.containsKey(line.toLowerCase())) {
                            bannedWordMap.put(line.toLowerCase(), new BannedWord(line.toLowerCase()));
                        }
                    }
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    inputStream.close();
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        // TODO Load in custom banned words list from external file
    }
}

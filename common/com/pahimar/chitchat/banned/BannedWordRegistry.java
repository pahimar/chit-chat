package com.pahimar.chitchat.banned;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.helper.JsonFileHelper;

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

    private static BannedWordRegistry bannedWordRegistry = null;
    
    private static List<BannedWord> bannedWordList;
    
    private BannedWordRegistry() {
        
        bannedWordList = new ArrayList<BannedWord>();
        
        if (Settings.DEFAULT_BAN_LIST_ENABLED) {
            // TODO Load default banned word file
        }
        
        // TODO Load simple banned word file
        // TODO Load advanced banned word file
    }
    
    public static BannedWordRegistry getInstance() {
        
        if (bannedWordRegistry == null) {
            bannedWordRegistry = new BannedWordRegistry();
        }
        
        return bannedWordRegistry;
    }
    
    
    public List<BannedWord> getBannedWordList() {
        
        return ImmutableList.copyOf(bannedWordList);
    }
    
    private static List<BannedWord> readFromSimpleFile() {
        
        return null;
    }
    
    private static List<BannedWord> readFromAdvancedFile() {
        
        return JsonFileHelper.readBannedWordsFromFile(Settings.CONFIG_DIRECTORY_PATH + "/bannedWords/advanced.json");
    }
    
    /**
     * @see http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/stream/JsonWriter.html
     */
//    private static void loadBannedWordMap() {
//        
//        if (bannedWordMap == null) {
//            bannedWordMap = new TreeMap<String, BannedWord>();
//        }
//        
//        if (Settings.DEFAULT_BAN_LIST_ENABLED) {
//            
//            InputStream inputStream = null; 
//            BufferedReader reader = null;
//            
//            try {
//                inputStream = bannedWordRegistry.getClass().getResourceAsStream(Reference.DEFAULT_BLACKLIST_FILE_LOCATION);
//                
//                if (inputStream != null) {
//                    reader = new BufferedReader(new InputStreamReader(inputStream));
//                    String line = null;
//                    
//                    while ((line = reader.readLine()) != null) {
//                        if (!bannedWordMap.containsKey(line.toLowerCase())) {
//                            bannedWordMap.put(line.toLowerCase(), new BannedWord(line.toLowerCase()));
//                        }
//                    }
//                }
//            }
//            catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//            finally {
//                try {
//                    inputStream.close();
//                    reader.close();
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        
//        // TODO Load in custom banned words list from external file
//    }
}

package com.pahimar.chitchat.blacklist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

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
    
    public static List<String> getBannedWordList() {
        
        if (bannedWordRegistry == null) {
            bannedWordRegistry = new BannedWordRegistry();
            loadBannedWordMap();
        }
        
        return ImmutableList.copyOf(bannedWordMap.keySet());
    }
    
    public static void loadBannedWordMap() {
        
        if (bannedWordMap == null) {
            bannedWordMap = new HashMap<String, BannedWord>();
        }
//        
//        try {
//            InputStream inputStream = BlackList.class.getClass().getResourceAsStream(Reference.DEFAULT_BLACKLIST_FILE_LOCATION);
//            
//            if (inputStream != null) {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                String line = null;
//                
//                while ((line = reader.readLine()) != null) {
//                    if (!blackList.contains(line.toLowerCase())) {
//                        blackList.add(line.toLowerCase());
//                    }
//                }
//            }
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

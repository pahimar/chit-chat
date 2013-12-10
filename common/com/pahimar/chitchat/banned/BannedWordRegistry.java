package com.pahimar.chitchat.banned;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.pahimar.chitchat.ChitChat;
import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.helper.FileHelper;
import com.pahimar.chitchat.helper.JsonFileHelper;
import com.pahimar.chitchat.helper.LogHelper;
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

    private static BannedWordRegistry bannedWordRegistry = null;
    
    private static List<BannedWord> bannedWordList;
    
    private BannedWordRegistry() {
        
        bannedWordList = new ArrayList<BannedWord>();
        
        // If the default banned word list is enabled, load it
        if (Settings.DEFAULT_BAN_LIST_ENABLED) {
            
            List<BannedWord> defaultBannedWords = JsonFileHelper.readBannedWordsFromFile(ChitChat.instance.getClass().getResourceAsStream(Reference.DEFAULT_BANNEDWORDS_FILE_LOCATION));
            
            for (BannedWord bannedWord : defaultBannedWords) {
                bannedWordList.add(bannedWord);
            }
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
}

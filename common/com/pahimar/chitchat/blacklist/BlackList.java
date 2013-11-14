package com.pahimar.chitchat.blacklist;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
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
public class BlackList {

    private static List<String> blackList;
    
    public static List<String> getBlackList() {
        
        if (blackList == null) {
            loadBlackList();
        }
        
        return ImmutableList.copyOf(blackList);
    }
    
    public static void loadBlackList() {
        
        if (blackList == null) {
            blackList = new ArrayList<String>();
        }
        
        try {
            InputStream inputStream = BlackList.class.getClass().getResourceAsStream(Reference.DEFAULT_BLACKLIST_FILE_LOCATION);
            
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                
                while ((line = reader.readLine()) != null) {
                    if (!blackList.contains(line.toLowerCase())) {
                        blackList.add(line.toLowerCase());
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
    }
}

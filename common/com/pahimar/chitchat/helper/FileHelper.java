package com.pahimar.chitchat.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pahimar.chitchat.banned.BannedWord;
import com.pahimar.chitchat.lib.Reference;


public class FileHelper {

    public static void writeBannedWordsToFile(String filePath, Map<String, BannedWord> bannedWordMap) {
        
    }
    
    public static List<BannedWord> readBannedWordsFromFile(String filePath) {
        
        return null;
    }
    
    public static List<BannedWord> readDefaultBannedWordsFromFile(String resourcePath) {

        List<BannedWord> bannedWords = new ArrayList<BannedWord>();
            
        InputStream inputStream = null; 
        BufferedReader reader = null;
        
        try {
            inputStream = new FileHelper().getClass().getResourceAsStream(Reference.DEFAULT_BANNEDWORDS_FILE_LOCATION);
            
            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                
                while ((line = reader.readLine()) != null) {
                    if (!bannedWords.contains(line.toLowerCase())) {
                        bannedWords.add(new BannedWord(line.toLowerCase()));
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
                if (inputStream != null) {
                    inputStream.close();
                }
                
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return bannedWords;
    }
}

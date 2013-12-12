package com.pahimar.chitchat.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.pahimar.chitchat.ChitChat;
import com.pahimar.chitchat.banned.BannedWord;
import com.pahimar.chitchat.lib.Reference;


public class FileHelper {

    public static void writeBannedWordsToFile(String filePath, List<BannedWord> bannedWordList) {
        
        BufferedWriter bufferedWriter = null;
        
        try {
            
            bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            
            for (BannedWord bannedWord : bannedWordList) {
                bufferedWriter.write(bannedWord.getBannedText());
                bufferedWriter.newLine();
            }
        }
        catch (FileNotFoundException e) {
            
            if (filePath.startsWith(Reference.BANNED_WORDS_DIRECTORY_LOCATION)) {
                File directory = new File(Reference.BANNED_WORDS_DIRECTORY_LOCATION);
                directory.mkdir();
                
                writeBannedWordsToFile(filePath, bannedWordList);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }
            catch (IOException e) {
                // NOOP
            }
        }
    }
    
    public static List<BannedWord> readBannedWordsFromFile(String filePath) {
        
        List<BannedWord> bannedWords = new ArrayList<BannedWord>();
        
        BufferedReader bufferedReader = null;
        
        try {
            
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String currentLine;
            
            while ((currentLine = bufferedReader.readLine()) != null) {
                    
                BannedWord bannedWord = new BannedWord(currentLine.trim());
                
                if (!bannedWords.contains(bannedWord)) {
                    bannedWords.add(bannedWord);
                }
            }
            
        }
        catch (FileNotFoundException e) {

            File directory = new File(Reference.BANNED_WORDS_DIRECTORY_LOCATION);
            directory.mkdir();
            
            try {
                File file = new File(filePath);
                file.createNewFile();
            }
            catch (IOException e1) {
                // NOOP
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException e) {
                    // NOOP
                }
            }
        }
        
        return bannedWords;
    }
    
    public static List<BannedWord> readDefaultBannedWordsFromFile(String resourcePath) {

        List<BannedWord> bannedWords = new ArrayList<BannedWord>();
            
        InputStream inputStream = null; 
        BufferedReader reader = null;
        
        try {
            inputStream = ChitChat.instance.getClass().getResourceAsStream(Reference.DEFAULT_BANNED_WORDS_FILE_LOCATION);
            
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

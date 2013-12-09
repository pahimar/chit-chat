package com.pahimar.chitchat.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.pahimar.chitchat.banned.BannedWord;

public class JsonFileHelper {

    public static void writeBannedWordsToFile(String filePath, List<BannedWord> bannedWordList) {

        JsonWriter jsonWriter = null;

        try {

            jsonWriter = new JsonWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
            jsonWriter.setIndent("    ");

            jsonWriter.beginArray();
            for (BannedWord bannedWord : bannedWordList) {
                jsonWriter.beginObject();
                jsonWriter.name("bannedText").value(bannedWord.getBannedText());
                jsonWriter.name("mustStartWith").value(bannedWord.mustStartWithBannedText());
                jsonWriter.name("mustEndWith").value(bannedWord.mustEndWithBannedText());
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
                e.printStackTrace();
            }
        }
    }

    public static List<BannedWord> readBannedWordsFromFile(String filePath) {

        try {
            return readBannedWordsFromFile(new FileInputStream(filePath));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static List<BannedWord> readBannedWordsFromFile(InputStream inputStream) {

        List<BannedWord> bannedWords = new ArrayList<BannedWord>();
        JsonReader jsonReader = null;

        try {
            jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

            String bannedText = null;
            boolean mustStartWith = false;
            boolean mustEndWith = false;

            jsonReader.beginArray();
            while (jsonReader.hasNext()) {

                jsonReader.beginObject();
                while (jsonReader.hasNext()) {

                    String name = jsonReader.nextName();
                    if (name.equalsIgnoreCase("bannedText")) {
                        bannedText = jsonReader.nextString();
                    }
                    else if (name.equalsIgnoreCase("mustStartWith")) {
                        mustStartWith = jsonReader.nextBoolean();
                    }
                    else if (name.equalsIgnoreCase("mustEndWith")) {
                        mustEndWith = jsonReader.nextBoolean();
                    }
                    else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();

                // TODO Handle exceptions
                BannedWord bannedWord = new BannedWord(bannedText, mustStartWith, mustEndWith);
                bannedWords.add(bannedWord);
            }
            jsonReader.endArray();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                jsonReader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        // TODO Don't return anything in the event of something failing to be parsed
        return bannedWords;
    }
}

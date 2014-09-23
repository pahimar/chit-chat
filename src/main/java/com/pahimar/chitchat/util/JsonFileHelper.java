package com.pahimar.chitchat.util;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.pahimar.chitchat.banned.BannedWord;
import com.pahimar.chitchat.reference.Reference;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonFileHelper
{

    public static void writeBannedWordsToFile(String filePath, List<BannedWord> bannedWordList)
    {

        JsonWriter jsonWriter = null;

        try
        {

            jsonWriter = new JsonWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
            jsonWriter.setIndent("    ");

            jsonWriter.beginArray();
            for (BannedWord bannedWord : bannedWordList)
            {
                jsonWriter.beginObject();
                jsonWriter.name("bannedText").value(bannedWord.getBannedText());
                jsonWriter.name("mustStartWith").value(bannedWord.mustStartWithBannedText());
                jsonWriter.name("mustEndWith").value(bannedWord.mustEndWithBannedText());
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
        }
        catch (FileNotFoundException e)
        {

            if (filePath.startsWith(Reference.BANNED_WORDS_DIRECTORY_LOCATION))
            {
                File directory = new File(Reference.BANNED_WORDS_DIRECTORY_LOCATION);
                directory.mkdir();

                writeBannedWordsToFile(filePath, bannedWordList);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (jsonWriter != null)
                {
                    jsonWriter.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static List<BannedWord> readBannedWordsFromFile(String filePath)
    {

        List<BannedWord> bannedWords = new ArrayList<BannedWord>();

        try
        {
            bannedWords = readBannedWordsFromFile(new FileInputStream(filePath));
        }
        catch (FileNotFoundException e)
        {

            File directory = new File(Reference.BANNED_WORDS_DIRECTORY_LOCATION);
            directory.mkdir();

            writeBannedWordsToFile(filePath, bannedWords);
        }

        return bannedWords;
    }

    public static List<BannedWord> readBannedWordsFromFile(InputStream inputStream)
    {

        List<BannedWord> bannedWords = new ArrayList<BannedWord>();
        JsonReader jsonReader = null;

        try
        {
            jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

            String bannedText = null;
            boolean mustStartWith = false;
            boolean mustEndWith = false;

            jsonReader.beginArray();
            while (jsonReader.hasNext())
            {

                jsonReader.beginObject();
                while (jsonReader.hasNext())
                {

                    String name = jsonReader.nextName();
                    if (name.equalsIgnoreCase("bannedText"))
                    {
                        bannedText = jsonReader.nextString();
                    }
                    else if (name.equalsIgnoreCase("mustStartWith"))
                    {
                        mustStartWith = jsonReader.nextBoolean();
                    }
                    else if (name.equalsIgnoreCase("mustEndWith"))
                    {
                        mustEndWith = jsonReader.nextBoolean();
                    }
                    else
                    {
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
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (jsonReader != null)
                {
                    jsonReader.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        // TODO Don't return anything in the event of something failing to be parsed
        return bannedWords;
    }
}

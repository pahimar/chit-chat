package com.pahimar.chitchat.banned;

import com.google.common.collect.ImmutableList;
import com.pahimar.chitchat.ChitChat;
import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.reference.Reference;
import com.pahimar.chitchat.util.FileHelper;
import com.pahimar.chitchat.util.JsonFileHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ChitChat
 * <p/>
 * BlackList
 *
 * @author pahimar
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 */
public class BannedWordRegistry
{

    private static BannedWordRegistry bannedWordRegistry = null;

    private static List<BannedWord> bannedWordList;
    private static List<BannedWord> customBannedWordList;

    private BannedWordRegistry()
    {

        bannedWordList = new ArrayList<BannedWord>();
        customBannedWordList = new ArrayList<BannedWord>();

        // If the default banned word list is enabled, load it
        if (Settings.DEFAULT_BAN_LIST_ENABLED)
        {

            List<BannedWord> defaultBannedWords = JsonFileHelper.readBannedWordsFromFile(ChitChat.instance.getClass().getResourceAsStream(Reference.DEFAULT_BANNED_WORDS_FILE_LOCATION));

            for (BannedWord bannedWord : defaultBannedWords)
            {
                bannedWordList.add(bannedWord);
            }
        }

        // Load simple banned word file
        List<BannedWord> simpleBannedWords = FileHelper.readBannedWordsFromFile(Reference.SIMPLE_BANNED_WORDS_FILE_LOCATION);
        for (BannedWord bannedWord : simpleBannedWords)
        {
            if (!customBannedWordList.contains(bannedWord))
            {
                customBannedWordList.add(bannedWord);
            }
        }

        // Load advanced banned word file
        List<BannedWord> advancedBannedWords = JsonFileHelper.readBannedWordsFromFile(Reference.ADVANCED_BANNED_WORDS_FILE_LOCATION);
        for (BannedWord bannedWord : advancedBannedWords)
        {
            if (!customBannedWordList.contains(bannedWord))
            {
                customBannedWordList.add(bannedWord);
            }
        }

        // Merge in custom banned words
        for (BannedWord bannedWord : customBannedWordList)
        {
            if (!bannedWordList.contains(bannedWord))
            {
                bannedWordList.add(bannedWord);
            }
        }
        Collections.sort(bannedWordList);
    }

    public static BannedWordRegistry getInstance()
    {

        if (bannedWordRegistry == null)
        {
            bannedWordRegistry = new BannedWordRegistry();
        }

        return bannedWordRegistry;
    }

    public List<BannedWord> getBannedWordList()
    {

        return ImmutableList.copyOf(bannedWordList);
    }

    public List<BannedWord> getCustomBannedWordList()
    {

        return ImmutableList.copyOf(customBannedWordList);
    }

    public void addBannedWord(String bannedText)
    {

        if (bannedText != null && bannedText.length() > 0)
        {
            addBannedWord(new BannedWord(bannedText));
        }
    }

    public void addBannedWord(BannedWord bannedWord)
    {

        if (!customBannedWordList.contains(bannedWord))
        {
            customBannedWordList.add(bannedWord);
        }

        Collections.sort(bannedWordList);
    }

    public void reloadBannedWords()
    {

        bannedWordRegistry = new BannedWordRegistry();
    }
}

package com.pahimar.chitchat.banned;

import com.pahimar.chitchat.util.BannedWordHelper;

import java.util.regex.Pattern;

public class BannedWord implements Comparable<BannedWord>
{

    private String bannedText;
    private boolean mustStartWith;
    private boolean mustEndWith;
    private Pattern bannedPattern;

    public BannedWord(String bannedWord)
    {

        this(bannedWord, true, true);
    }

    public BannedWord(String bannedText, boolean mustStartWith, boolean mustEndWith)
    {

        this.bannedText = bannedText.toLowerCase();
        this.mustStartWith = mustStartWith;
        this.mustEndWith = mustEndWith;
        this.bannedPattern = BannedWordHelper.generatePatternFromBannedWord(this);
    }

    public String getBannedText()
    {

        return bannedText;
    }

    public void setBannedText(String bannedText)
    {

        this.bannedText = bannedText;
        this.bannedPattern = BannedWordHelper.generatePatternFromBannedWord(this);
    }

    public boolean mustStartWithBannedText()
    {

        return mustStartWith;
    }

    public void setMustStartWithBannedText(boolean startsWith)
    {

        this.mustStartWith = startsWith;
        this.bannedPattern = BannedWordHelper.generatePatternFromBannedWord(this);
    }

    public boolean mustEndWithBannedText()
    {

        return mustEndWith;
    }

    public void setMustEndWithBannedText(boolean mustEndWith)
    {

        this.mustEndWith = mustEndWith;
        this.bannedPattern = BannedWordHelper.generatePatternFromBannedWord(this);
    }

    public Pattern getPattern()
    {

        return bannedPattern;
    }

    public boolean isSimple()
    {

        return mustStartWith && mustEndWith;
    }

    @Override
    public boolean equals(Object object)
    {

        if (object instanceof BannedWord)
        {
            BannedWord bannedWord = (BannedWord) object;

            return (this.bannedText.equalsIgnoreCase(bannedWord.bannedText) && this.mustStartWith == bannedWord.mustStartWith && this.mustEndWith == bannedWord.mustEndWith);
        }

        return false;
    }

    @Override
    public String toString()
    {

        StringBuilder stringBuilder = new StringBuilder();

        if (!mustStartWith)
        {
            stringBuilder.append("*");
        }

        stringBuilder.append(bannedText);

        if (!mustEndWith)
        {
            stringBuilder.append("*");
        }
        return String.format("BannedWord[%s]", stringBuilder.toString());
    }

    @Override
    public int compareTo(BannedWord bannedWord)
    {

        if (bannedWord != null)
        {
            if (this.bannedText.equalsIgnoreCase(bannedWord.bannedText))
            {
                if (this.mustStartWith == bannedWord.mustStartWith)
                {
                    if (this.mustEndWith == bannedWord.mustEndWith)
                    {
                        return 0;
                    }
                    else
                    {
                        if (this.mustEndWith == true)
                        {
                            return -1;
                        }
                        else
                        {
                            return 1;
                        }
                    }
                }
                else
                {
                    if (this.mustStartWith == true)
                    {
                        return -1;
                    }
                    else
                    {
                        return 1;
                    }
                }
            }
            else
            {
                return this.bannedText.compareToIgnoreCase(bannedWord.bannedText);
            }
        }

        return -1;
    }
}

package com.pahimar.chitchat.blacklist;

import java.util.regex.Pattern;

import com.pahimar.chitchat.helper.BannedWordHelper;


public class BannedWord {
    
    private String bannedText;
    private boolean mustStartWith;
    private boolean mustEndWith;
    private Pattern bannedPattern;
    
    public BannedWord(String bannedWord) {
        this(bannedWord, true, true);
    }
    
    public BannedWord(String bannedText, boolean mustStartWith, boolean mustEndWith) {
        this.bannedText = bannedText;
        this.mustStartWith = mustStartWith;
        this.mustEndWith = mustEndWith;
        this.bannedPattern = BannedWordHelper.generatePatternFromBannedWord(this);
    }

    public String getBannedText() {
        return bannedText;
    }
    
    public void setBannedText(String bannedText) {
        this.bannedText = bannedText;
        this.bannedPattern = BannedWordHelper.generatePatternFromBannedWord(this);
    }
    
    public boolean mustStartWithBannedText() {
        return mustStartWith;
    }
    
    public void setMustStartWithBannedText(boolean startsWith) {
        this.mustStartWith = startsWith;
        this.bannedPattern = BannedWordHelper.generatePatternFromBannedWord(this);
    }
    
    public boolean mustEndWithBannedText() {
        return mustEndWith;
    }
    
    public void setMustEndWithBannedText(boolean mustEndWith) {
        this.mustEndWith = mustEndWith;
        this.bannedPattern = BannedWordHelper.generatePatternFromBannedWord(this);
    }
    
    public Pattern getPattern() {
        return bannedPattern;
    }

}

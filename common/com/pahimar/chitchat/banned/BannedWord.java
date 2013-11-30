package com.pahimar.chitchat.banned;

import java.lang.reflect.Type;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pahimar.chitchat.helper.BannedWordHelper;

public class BannedWord implements JsonDeserializer<BannedWord>, JsonSerializer<BannedWord> {

    private Gson gsonSerializer = (new GsonBuilder()).registerTypeAdapter(BannedWord.class, new BannedWord()).create();

    private String bannedText;
    private boolean mustStartWith;
    private boolean mustEndWith;
    private Pattern bannedPattern;

    private BannedWord() {

    }

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

    @Override
    public JsonElement serialize(BannedWord src, Type typeOfSrc, JsonSerializationContext context) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BannedWord deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        // TODO Auto-generated method stub
        return null;
    }

}

package com.pahimar.chitchat.strike;

import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class StrikeRegistry implements JsonSerializer<StrikeRegistry>, JsonDeserializer<StrikeRegistry> {

    private static StrikeRegistry strikeRegistry = null;
    
    private HashMap<String, Strike> strikeMap;
    
    private StrikeRegistry() {
        
    }
    
    public static StrikeRegistry getInstance() {
        
        if (strikeRegistry == null) {
            strikeRegistry = new StrikeRegistry();
            strikeRegistry.strikeMap = new HashMap<String, Strike>();
        }
        
        return strikeRegistry; 
    }
    
    public boolean hasStrikes(String playerName) {
        
        return strikeMap.keySet().contains(playerName.toLowerCase());
    }
    
    public int getStrikes(String playerName) {
        
        if (hasStrikes(playerName)) {
            return strikeMap.get(playerName.toLowerCase()).getStrikeCount();
        }
        
        return 0;
    }
    
    public int getTicksRemaining(String playerName) {
        
        if (strikeMap.keySet().contains(playerName.toLowerCase())) {
            return strikeMap.get(playerName.toLowerCase()).getTicksRemaining();
        }
        
        return 0;
    }
    
    public void addStrike(String playerName) {
        // TODO Add a strike to an existing mapping, or add a new one if an existing mapping doesn't exist
    }
    
    public void updateRegistry() {
        
        for (String playerName : strikeMap.keySet()) {
            // TODO Update strike counts against players
        }
    }

    @Override
    public StrikeRegistry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonElement serialize(StrikeRegistry src, Type typeOfSrc, JsonSerializationContext context) {

        // TODO Auto-generated method stub
        return null;
    }
}

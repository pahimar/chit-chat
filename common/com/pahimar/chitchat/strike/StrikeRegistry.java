package com.pahimar.chitchat.strike;

import java.util.HashMap;

import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.helper.LogHelper;
import com.pahimar.chitchat.lib.Reference;

public class StrikeRegistry {

    private static StrikeRegistry strikeRegistry = null;

    private HashMap<String, Strike> strikeMap;

    private StrikeRegistry() {

        strikeMap = new HashMap<String, Strike>();
    }

    public static StrikeRegistry getInstance() {

        if (strikeRegistry == null) {
            strikeRegistry = new StrikeRegistry();
        }

        return strikeRegistry;
    }

    public boolean hasStrikes(String playerName) {

        return strikeMap.keySet().contains(playerName.toLowerCase());
    }

    public int getStrikes(String playerName) {

        String playerNameLowerCase = playerName.toLowerCase();

        if (hasStrikes(playerNameLowerCase)) {
            return strikeMap.get(playerNameLowerCase).getStrikeCount();
        }

        return 0;
    }

    public int getTicksRemaining(String playerName) {

        String playerNameLowerCase = playerName.toLowerCase();

        if (hasStrikes(playerNameLowerCase)) {
            return strikeMap.get(playerNameLowerCase).getTicksRemaining();
        }

        return 0;
    }

    public void addStrike(String playerName) {

        String playerNameLowerCase = playerName.toLowerCase();

        if (hasStrikes(playerNameLowerCase)) {
            if (getStrikes(playerNameLowerCase) < Settings.MAX_STRIKES_ALLOWED) {
                strikeMap.get(playerNameLowerCase).updateStrikeCountBy(1);
            }
            else {
                strikeMap.get(playerNameLowerCase).updateStrikeCountBy(Settings.MAX_STRIKES_ALLOWED);
            }
        }
        else {
            strikeMap.put(playerNameLowerCase, new Strike(playerNameLowerCase));
        }
        
        // TODO Logging
    }
    
    public boolean isStruckOut(String playerName) {
        
        String playerNameLowerCase = playerName.toLowerCase();
        
        if (hasStrikes(playerNameLowerCase)) {
            return strikeMap.get(playerNameLowerCase).getStrikeCount() >= Settings.MAX_STRIKES_ALLOWED;
        }
        
        return false;
    }

    public void clearStrikes(String playerName) {

        if (hasStrikes(playerName)) {
            strikeMap.remove(playerName.toLowerCase());
            // TODO Logging
        }
    }
    
    public void strikeOut(String playerName) {
        
        String playerNameLowerCase = playerName.toLowerCase();

        if (hasStrikes(playerNameLowerCase)) {
            strikeMap.get(playerNameLowerCase).updateStrikeCountBy(Settings.MAX_STRIKES_ALLOWED);
        }
        else {
            strikeMap.put(playerNameLowerCase, new Strike(playerNameLowerCase, Settings.MAX_STRIKES_ALLOWED, Settings.STRIKEOUT_ACTION_DURATION * Reference.TICKS_IN_SECOND));
        }
        
        // TODO Logging
    }

    public void updateRegistry() {

        for (String playerName : strikeMap.keySet()) {
            Strike strike = strikeMap.get(playerName);

            if (strike.getTicksRemaining() < Reference.TICKS_IN_SECOND) {
                clearStrikes(playerName);
            }
            else {
                strikeMap.get(playerName).updateTicksRemainingBy(-Reference.TICKS_IN_SECOND);
            }
        }
    }

    public void printDebug() {

        for (String playerName : strikeMap.keySet()) {
            LogHelper.debug(String.format("Strike Registry Entry - Player %s, Strike Count %s, Ticks Remaining %s", playerName, strikeMap.get(playerName).getStrikeCount(), strikeMap.get(playerName).getTicksRemaining()));
        }
    }
}

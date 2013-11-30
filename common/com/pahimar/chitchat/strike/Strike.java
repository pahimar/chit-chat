package com.pahimar.chitchat.strike;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.lib.Reference;

public class Strike {

    private static Gson gsonSerializer = new Gson();

    private final String playerName;
    private int strikeCount;
    private int ticksRemaining;

    public Strike(String playerName) {

        this.playerName = playerName;
        strikeCount = 1;
        ticksRemaining = Settings.STRIKE_EXPIRATION_TIME * Reference.TICKS_IN_SECOND;
    }

    public String getPlayerName() {

        return playerName;
    }

    public int getStrikeCount() {

        return strikeCount;
    }

    public void setStrikeCount(int strikeCount) {

        this.strikeCount = strikeCount;
    }

    public void updateStrikeCountBy(int amount) {

        this.strikeCount = this.strikeCount + amount;
    }

    public int getTicksRemaining() {

        return ticksRemaining;
    }

    public void setTicksRemaining(int ticksRemaining) {

        this.ticksRemaining = ticksRemaining;
    }

    public void updateTicksRemainingBy(int amount) {

        this.ticksRemaining = this.ticksRemaining + amount;
    }

    public static Strike createFromJson(String jsonSrike) {

        try {
            return gsonSerializer.fromJson(jsonSrike, Strike.class);
        }
        catch (JsonSyntaxException exception) {
            // TODO Log something regarding the failed parse
        }

        return null;
    }

    public String toJson() {

        return gsonSerializer.toJson(this);
    }

}

package com.pahimar.chitchat.strike;

import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.lib.Reference;

public class Strike {

    private final String playerName;
    private int strikeCount;
    private int ticksRemaining;

    public Strike(String playerName) {

        this.playerName = playerName.toLowerCase();
        strikeCount = 1;
        ticksRemaining = Settings.STRIKE_DURATION_TIME * Reference.TICKS_IN_SECOND;
    }

    public Strike(String playerName, int strikeCount) {

        this.playerName = playerName.toLowerCase();
        this.strikeCount = strikeCount;
        ticksRemaining = Settings.STRIKE_DURATION_TIME * Reference.TICKS_IN_SECOND;
    }
    
    public Strike(String playerName, int strikeCount, int ticksRemaining) {

        this.playerName = playerName.toLowerCase();
        this.strikeCount = strikeCount;
        this.ticksRemaining = ticksRemaining;
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
        if (this.strikeCount >= Settings.MAX_STRIKES_ALLOWED) {
            this.strikeCount = Settings.MAX_STRIKES_ALLOWED;
            this.ticksRemaining = Settings.STRIKEOUT_ACTION_DURATION * Reference.TICKS_IN_SECOND;
        }
        else {
            this.ticksRemaining = Settings.STRIKE_DURATION_TIME * Reference.TICKS_IN_SECOND;
        }
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

}

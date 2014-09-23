package com.pahimar.chitchat.strike;

import com.google.common.collect.ImmutableMap;
import com.pahimar.chitchat.ChitChat;
import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.reference.Reference;
import com.pahimar.chitchat.reference.Strings;
import com.pahimar.chitchat.util.GeneralHelper;
import com.pahimar.chitchat.util.LogHelper;

import java.util.HashMap;
import java.util.Map;

public class StrikeRegistry
{

    private static StrikeRegistry strikeRegistry = null;

    private HashMap<String, Strike> strikeMap;

    private StrikeRegistry()
    {

        strikeMap = new HashMap<String, Strike>();
    }

    public static StrikeRegistry getInstance()
    {

        if (strikeRegistry == null)
        {
            strikeRegistry = new StrikeRegistry();
        }

        return strikeRegistry;
    }

    public boolean hasStrikes(String playerName)
    {

        return strikeMap.keySet().contains(playerName.toLowerCase());
    }

    public int getStrikes(String playerName)
    {

        String playerNameLowerCase = playerName.toLowerCase();

        if (hasStrikes(playerNameLowerCase))
        {
            return strikeMap.get(playerNameLowerCase).getStrikeCount();
        }

        return 0;
    }

    public int getTicksRemaining(String playerName)
    {

        String playerNameLowerCase = playerName.toLowerCase();

        if (hasStrikes(playerNameLowerCase))
        {
            return strikeMap.get(playerNameLowerCase).getTicksRemaining();
        }

        return 0;
    }

    public void addStrike(String playerName, String reason)
    {

        String playerNameLowerCase = playerName.toLowerCase();

        LogHelper.info(String.format(Strings.TEMPLATE_STRIKE_ADDED, playerName, reason));

        if (hasStrikes(playerNameLowerCase))
        {
            if (getStrikes(playerNameLowerCase) < Settings.MAX_STRIKES_ALLOWED)
            {
                strikeMap.get(playerNameLowerCase).updateStrikeCountBy(1);
            }
            else
            {
                strikeOut(playerName, reason);
            }
        }
        else
        {
            strikeMap.put(playerNameLowerCase, new Strike(playerNameLowerCase));
        }
    }

    public boolean isStruckOut(String playerName)
    {

        String playerNameLowerCase = playerName.toLowerCase();

        if (hasStrikes(playerNameLowerCase))
        {
            return strikeMap.get(playerNameLowerCase).getStrikeCount() >= Settings.MAX_STRIKES_ALLOWED;
        }

        return false;
    }

    public void clearStrikes(String playerName, String reason)
    {

        if (hasStrikes(playerName))
        {

            if (isStruckOut(playerName))
            {
                ChitChat.proxy.notifyAdmins(String.format(Strings.TEMPLATE_STRIKES_CLEARED, playerName, reason));
                LogHelper.info(String.format(Strings.TEMPLATE_STRIKES_CLEARED, playerName, reason));
            }

            strikeMap.remove(playerName.toLowerCase());
        }
    }

    public void strikeOut(String playerName, String reason)
    {

        String playerNameLowerCase = playerName.toLowerCase();

        if (hasStrikes(playerNameLowerCase))
        {
            strikeMap.get(playerNameLowerCase).updateStrikeCountBy(Settings.MAX_STRIKES_ALLOWED);
        }
        else
        {
            strikeMap.put(playerNameLowerCase, new Strike(playerNameLowerCase, Settings.MAX_STRIKES_ALLOWED, Settings.STRIKEOUT_ACTION_DURATION * Reference.TICKS_IN_SECOND));
        }

        LogHelper.info(String.format(Strings.TEMPLATE_STRIKE_OUT_WITH_TIMED_PENALTY, playerName, GeneralHelper.formatTimeFromTicks(strikeMap.get(playerNameLowerCase).getTicksRemaining()), reason));
    }

    public void updateRegistry()
    {

        for (String playerName : strikeMap.keySet())
        {
            Strike strike = strikeMap.get(playerName);

            if (strike.getTicksRemaining() < Reference.TICKS_IN_SECOND)
            {

                clearStrikes(playerName, Strings.REASON_STRIKE_EXPIRATION);
            }
            else
            {
                strikeMap.get(playerName).updateTicksRemainingBy(-Reference.TICKS_IN_SECOND);
            }
        }
    }

    public Map<String, Strike> getStrikeMap()
    {
        return ImmutableMap.copyOf(strikeMap);
    }
}

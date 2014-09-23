package com.pahimar.chitchat.proxy;

public class ClientProxy extends CommonProxy
{
    @Override
    public void notifyAdmins(String message)
    {
        // NOOP
    }

    @Override
    public void handleKick(String playerName, String reason)
    {
        // NOOP
    }

    @Override
    public void handleTimeOut(String playerName, String reason)
    {
        // NOOP
    }

    @Override
    public void handleBan(String playerName, String reason)
    {
        // NOOP
    }
}

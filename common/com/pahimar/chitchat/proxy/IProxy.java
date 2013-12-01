package com.pahimar.chitchat.proxy;

public interface IProxy {

    public abstract void notifyAdmins(String message);
    public abstract void handleKick(String playerName, String reason);
    public abstract void handleChatDisabled(String playerName, String reason);
    public abstract void handleTimeOut(String playerName, String reason);
    public abstract void handleBan(String playerName, String reason);
}

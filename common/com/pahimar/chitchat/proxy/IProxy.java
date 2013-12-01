package com.pahimar.chitchat.proxy;

public interface IProxy {

    public abstract void handleKick();
    public abstract void handleChatDisabled();
    public abstract void handleTimeOut();
    public abstract void handleBan();
}

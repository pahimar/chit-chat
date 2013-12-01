package com.pahimar.chitchat.proxy;

import com.pahimar.chitchat.helper.LogHelper;

public class ServerProxy implements IProxy {

    @Override
    public void handleKick() {

        LogHelper.debug("Server - handle kick");
    }
    
    @Override
    public void handleChatDisabled() {

        LogHelper.debug("Server - handle chat disabled");
    }

    @Override
    public void handleTimeOut() {

        LogHelper.debug("Server - handle time out");
    }

    @Override
    public void handleBan() {

        LogHelper.debug("Server - handle ban");
    }
}

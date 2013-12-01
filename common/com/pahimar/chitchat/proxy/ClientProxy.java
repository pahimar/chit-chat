package com.pahimar.chitchat.proxy;

import com.pahimar.chitchat.helper.LogHelper;

public class ClientProxy implements IProxy {

    @Override
    public void handleKick() {

        LogHelper.debug("Client - handle kick");
    }
    
    @Override
    public void handleChatDisabled() {

        LogHelper.debug("Client - handle chat disabled");
    }

    @Override
    public void handleTimeOut() {

        LogHelper.debug("Client - handle time out");
    }

    @Override
    public void handleBan() {

        LogHelper.debug("Client - handle ban");
    }
}

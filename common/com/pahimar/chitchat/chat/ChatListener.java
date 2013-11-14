package com.pahimar.chitchat.chat;

import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet3Chat;

import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.helper.BlackListHelper;
import com.pahimar.chitchat.helper.LogHelper;
import com.pahimar.chitchat.lib.Reference;

import cpw.mods.fml.common.network.IChatListener;

/**
 * ChitChat
 * 
 * ChatListener
 * 
 * @author pahimar
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 * 
 */
public class ChatListener implements IChatListener {

    /*
     * (non-Javadoc)
     * @see
     * cpw.mods.fml.common.network.IChatListener#serverChat(net.minecraft.network
     * .packet.NetHandler, net.minecraft.network.packet.Packet3Chat)
     */
    @Override
    public Packet3Chat serverChat(NetHandler handler, Packet3Chat message) {

        if (Settings.FILTER_MODE > Reference.FILTER_MODE_NONE) {
            
            if (message.message.startsWith("/msg") || message.message.startsWith("/me") || !message.message.startsWith("/")) {
                
                if (BlackListHelper.findBannedWords(message.message)) {
                    
                    if (Settings.FILTER_MODE == Reference.FILTER_MODE_WORD_CENSOR) {
                        // TODO Look for bad words
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_LINE_CENSOR) {
                        // TODO Look for bad words
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_HIDE) {
                        LogHelper.debug("Server nulling the message");
                        message = null;
                    }

                    // TODO if any of the filters trips, tell the offending user what word they said that's not allowed
                }
            }
        }
        
        return message;
    }

    /*
     * (non-Javadoc)
     * @see
     * cpw.mods.fml.common.network.IChatListener#clientChat(net.minecraft.network
     * .packet.NetHandler, net.minecraft.network.packet.Packet3Chat)
     */
    @Override
    public Packet3Chat clientChat(NetHandler handler, Packet3Chat message) {

        if (Settings.FILTER_MODE > Reference.FILTER_MODE_NONE) {
            
            if (message.message.startsWith("/msg") || message.message.startsWith("/me") || !message.message.startsWith("/")) {
                
                if (BlackListHelper.findBannedWords(message.message)) {
                    
                    if (Settings.FILTER_MODE == Reference.FILTER_MODE_WORD_CENSOR) {
                        // TODO Replace the bad word
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_LINE_CENSOR) {
                        // TODO Replace the line
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_HIDE) {
                        LogHelper.debug("Client nulling the message");
                        message = null;
                    }
                }
            }
        }
        
        return message;
    }
}

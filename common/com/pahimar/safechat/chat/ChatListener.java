package com.pahimar.safechat.chat;

import java.util.Iterator;

import com.pahimar.safechat.blacklist.BlackList;
import com.pahimar.safechat.configuration.Settings;
import com.pahimar.safechat.helper.LogHelper;
import com.pahimar.safechat.lib.Reference;

import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet3Chat;
import cpw.mods.fml.common.network.IChatListener;

/**
 * SafeChat
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
                LogHelper.debug("Server received a chat message that could be filtered message");
                
                if (Settings.FILTER_MODE == Reference.FILTER_MODE_REPLACE) {
                    
                }
                else if (Settings.FILTER_MODE == Reference.FILTER_MODE_HIDE) {
                    
                    boolean blackListWordFound = false;
                    String messageLowerCase = message.message.toLowerCase();
                    
                    Iterator<String> blackListIterator = BlackList.getBlackList().iterator();
                    String blackListWord = null;
                    while (!blackListWordFound && blackListIterator.hasNext()) {
                        blackListWord = blackListIterator.next();
                        if (messageLowerCase.contains(blackListWord.toLowerCase())) {
                            blackListWordFound = true;
                        }
                    }
                    
                    if (blackListWordFound) {
                        message.message = "[Message removed by SafeChat]";
                        return message;
                    }
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

        return message;
    }
}

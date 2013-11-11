package com.pahimar.safechat.chat;

import java.util.Iterator;

import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.StatCollector;

import com.pahimar.safechat.blacklist.BlackList;
import com.pahimar.safechat.configuration.Settings;
import com.pahimar.safechat.helper.LogHelper;
import com.pahimar.safechat.lib.Reference;
import com.pahimar.safechat.lib.Strings;

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
                
                if (Settings.FILTER_MODE == Reference.FILTER_MODE_WORD_CENSOR) {
                    // TODO Only filter out the censored word
                }
                else if (Settings.FILTER_MODE == Reference.FILTER_MODE_LINE_CENSOR) {
                    
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
                        if (Settings.STRIKE_SYSTEM_ENABLED) {
                            // TODO Handle whether this is a strikeout, or just a strike
                            message = new Packet3Chat(new ChatMessageComponent().addText(StatCollector.translateToLocal(Strings.LINE_REMOVED_WITH_STRIKE)).setItalic(true));
                        }
                        else {
                            message = new Packet3Chat(new ChatMessageComponent().addText(StatCollector.translateToLocal(Strings.LINE_REMOVED)).setItalic(true));
                        }
                        return message;
                    }
                }
                else if (Settings.FILTER_MODE == Reference.FILTER_MODE_HIDE) {
                    // TODO This is waiting on FML update, but don't send the message at all
                }
                // TODO if any of the filters trips, tell the offending user what word they said that's not allowed
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

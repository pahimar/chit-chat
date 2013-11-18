package com.pahimar.chitchat.chat;

import java.util.List;

import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;

import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.helper.BannedWordHelper;
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
    public Packet3Chat serverChat(NetHandler netHandler, Packet3Chat packet3Chat) {

        if (Settings.FILTER_MODE > Reference.FILTER_MODE_NONE) {
            
            if (packet3Chat.message.startsWith("/msg") || packet3Chat.message.startsWith("/me") || !packet3Chat.message.startsWith("/")) {
                
                if (BannedWordHelper.checkForBannedWords(packet3Chat.message)) {
                    
                    // Gather the banned words the player used
                    List<String> bannedWordsUsed = BannedWordHelper.getBannedWordsUsed(packet3Chat.message);
                    StringBuilder bannedWordBuilder = new StringBuilder();
                    for (String bannedWord : bannedWordsUsed) {
                        bannedWordBuilder.append(String.format("'%s' ", bannedWord));
                    }
                    
                    // Filter the banned words from chat
                    if (Settings.FILTER_MODE == Reference.FILTER_MODE_WORD_CENSOR) {
                        LogHelper.info(String.format("Partially censored a message that was sent from player '%s' for using banned words: %s", netHandler.getPlayer().username, bannedWordBuilder.toString()));
                        packet3Chat.message = BannedWordHelper.censorPartialMessage(packet3Chat.message, true);
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_LINE_CENSOR) {
                        LogHelper.info(String.format("Completely censored a message that was sent from player '%s' for using banned words: %s", netHandler.getPlayer().username, bannedWordBuilder.toString()));
                        packet3Chat.message = BannedWordHelper.censorEntireMessage(packet3Chat.message, true);
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_HIDE && Settings.FML_CAN_CANCEL_MESSAGES) {
                        LogHelper.info(String.format("Stopped a message from being sent from player '%s' for using banned words: %s", netHandler.getPlayer().username, bannedWordBuilder.toString()));
                        packet3Chat = null;
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_HIDE && !Settings.FML_CAN_CANCEL_MESSAGES) {
                        LogHelper.info(String.format("Completely censored a message that was sent from player '%s' for using banned words: %s", netHandler.getPlayer().username, bannedWordBuilder.toString()));
                        packet3Chat.message = BannedWordHelper.censorEntireMessage(packet3Chat.message, true);
                    }

                    // Notify the player that they said something that is banned, and what they said
                    netHandler.getPlayer().sendChatToPlayer(ChatMessageComponent.createFromText(String.format("<%s-Server> Not allowed to say the following words on this server: %s", Reference.MOD_NAME, bannedWordBuilder.toString())).setColor(EnumChatFormatting.GRAY));
                    
                    // Perform whatever strikes are necessary, if the strike system is enabled
                    if (Settings.STRIKE_SYSTEM_ENABLED) {
                        // TODO Strike system
                    }
                }
            }
        }
        
        return packet3Chat;
    }

    /*
     * (non-Javadoc)
     * @see
     * cpw.mods.fml.common.network.IChatListener#clientChat(net.minecraft.network
     * .packet.NetHandler, net.minecraft.network.packet.Packet3Chat)
     */
    @Override
    public Packet3Chat clientChat(NetHandler netHandler, Packet3Chat packet3Chat) {

        if (Settings.FILTER_MODE > Reference.FILTER_MODE_NONE) {
            
            if (packet3Chat.message.startsWith("/msg") || packet3Chat.message.startsWith("/me") || !packet3Chat.message.startsWith("/")) {
                
                if (BannedWordHelper.checkForBannedWords(packet3Chat.message)) {
                    
                    if (Settings.FILTER_MODE == Reference.FILTER_MODE_WORD_CENSOR) {
                        // TODO Localize the message and format
                        packet3Chat.message = BannedWordHelper.censorPartialMessage(packet3Chat.message, false);
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_LINE_CENSOR) {
                        packet3Chat.message = BannedWordHelper.censorEntireMessage(packet3Chat.message, false);
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_HIDE && Settings.FML_CAN_CANCEL_MESSAGES) {
                        packet3Chat = null;
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_HIDE && !Settings.FML_CAN_CANCEL_MESSAGES) {
                        packet3Chat.message = BannedWordHelper.censorEntireMessage(packet3Chat.message, false);
                    }
                }
            }
        }
        
        return packet3Chat;
    }
}

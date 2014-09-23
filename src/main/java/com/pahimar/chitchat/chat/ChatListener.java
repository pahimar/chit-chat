package com.pahimar.chitchat.chat;

import com.pahimar.chitchat.ChitChat;
import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.reference.Reference;
import com.pahimar.chitchat.reference.Strings;
import com.pahimar.chitchat.strike.StrikeRegistry;
import com.pahimar.chitchat.util.BannedWordHelper;
import com.pahimar.chitchat.util.GeneralHelper;
import com.pahimar.chitchat.util.LogHelper;
import cpw.mods.fml.common.network.IChatListener;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

/**
 * ChitChat
 * <p/>
 * ChatListener
 *
 * @author pahimar
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 */
public class ChatListener implements IChatListener
{

    /*
     * (non-Javadoc)
     * @see
     * cpw.mods.fml.common.network.IChatListener#serverChat(net.minecraft.network
     * .packet.NetHandler, net.minecraft.network.packet.Packet3Chat)
     */
    @Override
    public Packet3Chat serverChat(NetHandler netHandler, Packet3Chat packet3Chat)
    {

        if (Settings.FILTER_MODE > Reference.FILTER_MODE_NONE)
        {

            if (!StrikeRegistry.getInstance().isStruckOut(netHandler.getPlayer().username))
            {

                if (packet3Chat.message.startsWith("/msg") || packet3Chat.message.startsWith("/me") || !packet3Chat.message.startsWith("/"))
                {

                    if (BannedWordHelper.checkForBannedWords(packet3Chat.message))
                    {

                        // Gather the banned words the player used
                        List<String> bannedWordsUsed = BannedWordHelper.getBannedWordsUsed(packet3Chat.message);
                        StringBuilder bannedWordBuilder = new StringBuilder();
                        for (String bannedWord : bannedWordsUsed)
                        {
                            bannedWordBuilder.append(String.format("'%s' ", bannedWord));
                        }

                        // Filter the banned words from chat
                        if (Settings.FILTER_MODE == Reference.FILTER_MODE_WORD_CENSOR)
                        {
                            LogHelper.info(String.format(Strings.TEMPLATE_CENSOR_WORD_MESSAGE, netHandler.getPlayer().username, bannedWordBuilder.toString()));
                            packet3Chat.message = BannedWordHelper.censorPartialMessage(packet3Chat.message, true);
                        }
                        else if (Settings.FILTER_MODE == Reference.FILTER_MODE_LINE_CENSOR)
                        {
                            LogHelper.info(String.format(Strings.TEMPLATE_CENSOR_LINE_MESSAGE, netHandler.getPlayer().username, bannedWordBuilder.toString()));
                            packet3Chat.message = BannedWordHelper.censorEntireMessage(packet3Chat.message, true);
                        }
                        else if (Settings.FILTER_MODE == Reference.FILTER_MODE_HIDE && Settings.FML_CAN_CANCEL_MESSAGES)
                        {
                            LogHelper.info(String.format(Strings.TEMPLATE_HIDE_MESSAGE, netHandler.getPlayer().username, bannedWordBuilder.toString()));
                            packet3Chat = null;
                        }
                        else if (Settings.FILTER_MODE == Reference.FILTER_MODE_HIDE && !Settings.FML_CAN_CANCEL_MESSAGES)
                        {
                            LogHelper.info(String.format(Strings.TEMPLATE_CENSOR_LINE_MESSAGE, netHandler.getPlayer().username, bannedWordBuilder.toString()));
                            packet3Chat.message = BannedWordHelper.censorEntireMessage(packet3Chat.message, true);
                        }

                        // Notify the player that they said something that is banned, and what they said
                        // TODO Decide if this should be a config setting or not
                        netHandler.getPlayer().sendChatToPlayer(ChatMessageComponent.createFromText(String.format("<%s-Server> Not allowed to say the following words on this server: %s", Reference.MOD_NAME, bannedWordBuilder.toString())).setColor(EnumChatFormatting.GRAY));

                        // Perform whatever strikes are necessary, if the strike system is enabled
                        if (Settings.STRIKE_SYSTEM_ENABLED)
                        {

                            StrikeRegistry.getInstance().addStrike(netHandler.getPlayer().username, Strings.REASON_BANNED_WORD_USAGE);
                            int strikeCount = StrikeRegistry.getInstance().getStrikes(netHandler.getPlayer().username);

                            if (strikeCount < Settings.MAX_STRIKES_ALLOWED)
                            {
                                netHandler.getPlayer().sendChatToPlayer(ChatMessageComponent.createFromText(String.format(Strings.TEMPLATE_STRIKE_COUNT_MESSAGE, Reference.MOD_NAME, strikeCount, Settings.MAX_STRIKES_ALLOWED)).setColor(EnumChatFormatting.GRAY));
                            }
                            // Player just struck out
                            else
                            {

                                if (Settings.STRIKEOUT_ACTION == Reference.ACTION_KICK)
                                {
                                    LogHelper.info(String.format(Strings.TEMPLATE_STRIKE_OUT_WITH_PENALTY, netHandler.getPlayer().username, Strings.ACTION_KICKED, Strings.REASON_BANNED_WORD_USAGE));
                                    ChitChat.proxy.handleKick(netHandler.getPlayer().username, String.format(Strings.TEMPLATE_STRUCK_OUT_KICK_MESSAGE, Reference.MOD_NAME, Strings.REASON_STRIKEOUT_WORD_USAGE));
                                }
                                else if (Settings.STRIKEOUT_ACTION == Reference.ACTION_DISABLE_CHAT)
                                {
                                    LogHelper.info(String.format(Strings.TEMPLATE_STRIKE_OUT_WITH_TIMED_PENALTY_AND_ACTION, netHandler.getPlayer().username, GeneralHelper.formatTimeFromTicks(StrikeRegistry.getInstance().getTicksRemaining(netHandler.getPlayer().username)), Strings.REASON_BANNED_WORD_USAGE, Strings.ACTION_CHAT_DISABLED, Strings.REASON_STRIKEOUT_WORD_USAGE));
                                    ChitChat.proxy.notifyAdmins(String.format(Strings.TEMPLATE_STRIKE_OUT_WITH_TIMED_PENALTY_AND_ACTION, netHandler.getPlayer().username, GeneralHelper.formatTimeFromTicks(StrikeRegistry.getInstance().getTicksRemaining(netHandler.getPlayer().username)), Strings.REASON_BANNED_WORD_USAGE, Strings.ACTION_CHAT_DISABLED, Strings.REASON_STRIKEOUT_WORD_USAGE));
                                    netHandler.getPlayer().sendChatToPlayer(ChatMessageComponent.createFromText(String.format(Strings.TEMPLATE_CHAT_DISABLED_MESSAGE, Reference.MOD_NAME, GeneralHelper.formatTimeFromTicks(StrikeRegistry.getInstance().getTicksRemaining(netHandler.getPlayer().username)), Strings.REASON_BANNED_WORD_USAGE)).setColor(EnumChatFormatting.GRAY));
                                }
                                else if (Settings.STRIKEOUT_ACTION == Reference.ACTION_TIME_OUT)
                                {
                                    LogHelper.info(String.format(Strings.TEMPLATE_STRIKE_OUT_WITH_TIMED_PENALTY_AND_ACTION, netHandler.getPlayer().username, GeneralHelper.formatTimeFromTicks(StrikeRegistry.getInstance().getTicksRemaining(netHandler.getPlayer().username)), Strings.REASON_BANNED_WORD_USAGE, Strings.ACTION_TIMED_OUT, Strings.REASON_STRIKEOUT_WORD_USAGE));
                                    ChitChat.proxy.handleTimeOut(netHandler.getPlayer().username, String.format(Strings.TEMPLATE_STRUCK_OUT_TIMEOUT_MESSAGE, Reference.MOD_NAME, GeneralHelper.formatTimeFromTicks(StrikeRegistry.getInstance().getTicksRemaining(netHandler.getPlayer().username)), Strings.REASON_STRIKEOUT_WORD_USAGE));
                                }
                                else if (Settings.STRIKEOUT_ACTION == Reference.ACTION_BAN)
                                {
                                    LogHelper.info(String.format(Strings.TEMPLATE_STRIKE_OUT_WITH_PENALTY, netHandler.getPlayer().username, Strings.ACTION_BANNED, Strings.REASON_BANNED_WORD_USAGE));
                                    ChitChat.proxy.handleBan(netHandler.getPlayer().username, String.format(Strings.TEMPLATE_STRUCK_OUT_BANNED_MESSAGE, Reference.MOD_NAME, Strings.REASON_STRIKEOUT_WORD_USAGE));
                                }
                            }
                        }
                    }
                }
            }
            // Player is struck out
            else
            {
                if (Settings.STRIKEOUT_ACTION == Reference.ACTION_KICK)
                {
                    // NOOP
                }
                else if (Settings.STRIKEOUT_ACTION == Reference.ACTION_DISABLE_CHAT)
                {

                    netHandler.getPlayer().sendChatToPlayer(ChatMessageComponent.createFromText(String.format(Strings.TEMPLATE_CHAT_STILL_DISABLED_MESSAGE, Reference.MOD_NAME, GeneralHelper.formatTimeFromTicks(StrikeRegistry.getInstance().getTicksRemaining(netHandler.getPlayer().username)), Strings.REASON_BANNED_WORD_USAGE)).setColor(EnumChatFormatting.GRAY));

                    if (Settings.FML_CAN_CANCEL_MESSAGES)
                    {
                        packet3Chat = null;
                    }
                    else
                    {
                        packet3Chat.message = BannedWordHelper.censorEntireMessage(packet3Chat.message, true);
                    }
                }
                else if (Settings.STRIKEOUT_ACTION == Reference.ACTION_TIME_OUT)
                {
                    // NOOP
                }
                else if (Settings.STRIKEOUT_ACTION == Reference.ACTION_BAN)
                {
                    // NOOP
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
    public Packet3Chat clientChat(NetHandler netHandler, Packet3Chat packet3Chat)
    {

        if (Settings.FILTER_MODE > Reference.FILTER_MODE_NONE)
        {

            if (packet3Chat.message.startsWith("/msg") || packet3Chat.message.startsWith("/me") || !packet3Chat.message.startsWith("/"))
            {

                if (BannedWordHelper.checkForBannedWords(packet3Chat.message))
                {

                    if (Settings.FILTER_MODE == Reference.FILTER_MODE_WORD_CENSOR)
                    {
                        packet3Chat.message = BannedWordHelper.censorPartialMessage(packet3Chat.message, false);
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_LINE_CENSOR)
                    {
                        packet3Chat.message = BannedWordHelper.censorEntireMessage(packet3Chat.message, false);
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_HIDE && Settings.FML_CAN_CANCEL_MESSAGES)
                    {
                        packet3Chat = null;
                    }
                    else if (Settings.FILTER_MODE == Reference.FILTER_MODE_HIDE && !Settings.FML_CAN_CANCEL_MESSAGES)
                    {
                        packet3Chat.message = BannedWordHelper.censorEntireMessage(packet3Chat.message, false);
                    }
                }
            }
        }

        return packet3Chat;
    }
}

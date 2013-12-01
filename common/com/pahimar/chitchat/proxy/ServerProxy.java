package com.pahimar.chitchat.proxy;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.BanEntry;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;

import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.helper.GeneralHelper;
import com.pahimar.chitchat.lib.Reference;
import com.pahimar.chitchat.lib.Strings;
import com.pahimar.chitchat.strike.StrikeRegistry;

public class ServerProxy implements IProxy {

    @Override
    public void notifyAdmins(String message) {
        
        if (Settings.STRIKEOUT_NOTIFY_OPS) {
            for (Object op : MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).getOps()) {
                if (op instanceof String) {
                    EntityPlayerMP adminPlayer = MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).getPlayerForUsername((String) op);
                    if (adminPlayer != null) {
                        adminPlayer.sendChatToPlayer(ChatMessageComponent.createFromText(String.format(Strings.TEMPLATE_ADMIN_MESSAGE, Reference.MOD_NAME, message)).setItalic(true).setColor(EnumChatFormatting.GRAY));
                    }
                }
            }
        }
    }
    
    @Override
    public void handleKick(String playerName, String reason) {

        EntityPlayerMP playerToKick = MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).getPlayerForUsername(playerName);
        
        if (playerToKick != null) {
            playerToKick.playerNetServerHandler.kickPlayerFromServer(reason);
            StrikeRegistry.getInstance().clearStrikes(playerName, Strings.REASON_STRIKEOUT_KICK);
            notifyAdmins(String.format("Kicking player '%s' (Reason: %s)", playerName, reason));
        }
    }

    @Override
    public void handleTimeOut(String playerName, String reason) {

        EntityPlayerMP playerToKick = MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).getPlayerForUsername(playerName);
        
        if (playerToKick != null) {
            playerToKick.playerNetServerHandler.kickPlayerFromServer(reason);
            notifyAdmins(String.format("Kicking player '%s' and preventing them from joining again for %s (Reason: %s)", playerName, GeneralHelper.formatTimeFromTicks(StrikeRegistry.getInstance().getTicksRemaining(playerName)), reason));
        }
    }

    @Override
    public void handleBan(String playerName, String reason) {

        EntityPlayerMP playerToBan = MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).getPlayerForUsername(playerName);
        
        if (playerToBan != null) {
            
            BanEntry banEntry = new BanEntry(playerName);
            banEntry.setBannedBy(Reference.MOD_NAME);
            banEntry.setBanReason(String.format("%s %s", Reference.MOD_NAME, Strings.REASON_STRIKEOUT_BAN.toLowerCase()));
            MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().put(banEntry);
            playerToBan.playerNetServerHandler.kickPlayerFromServer(reason);
            StrikeRegistry.getInstance().clearStrikes(playerName, Strings.REASON_STRIKEOUT_BAN);
            notifyAdmins(String.format("Banning player '%s' (Reason: %s)", playerName, reason));
        }
    }
}

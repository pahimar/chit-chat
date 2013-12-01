package com.pahimar.chitchat.strike;

import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.helper.GeneralHelper;
import com.pahimar.chitchat.helper.LogHelper;
import com.pahimar.chitchat.lib.Reference;
import com.pahimar.chitchat.lib.Strings;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;


/**
 * @author pahimar
 *
 */
public class ConnectionHandler implements IConnectionHandler {

    /* (non-Javadoc)
     * @see cpw.mods.fml.common.network.IConnectionHandler#playerLoggedIn(cpw.mods.fml.common.network.Player, net.minecraft.network.packet.NetHandler, net.minecraft.network.INetworkManager)
     */
    @Override
    public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
        // NOOP
    }

    /* (non-Javadoc)
     * @see cpw.mods.fml.common.network.IConnectionHandler#connectionReceived(net.minecraft.network.NetLoginHandler, net.minecraft.network.INetworkManager)
     */
    @Override
    public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {

        if (netHandler.clientUsername != null) {
            if (Settings.STRIKE_SYSTEM_ENABLED) {
                if (Settings.STRIKEOUT_ACTION == Reference.ACTION_TIME_OUT) {
                    if (StrikeRegistry.getInstance().isStruckOut(netHandler.clientUsername)) {
                        LogHelper.info(String.format(Strings.STRIKEOUT_TIME_OUT_TEMPLATE, netHandler.clientUsername, GeneralHelper.formatTimeFromTicks(StrikeRegistry.getInstance().getTicksRemaining(netHandler.clientUsername))));
                        return String.format(Strings.STRIKEOUT_TIME_OUT_TEMPLATE, netHandler.clientUsername, GeneralHelper.formatTimeFromTicks(StrikeRegistry.getInstance().getTicksRemaining(netHandler.clientUsername)));
                    }
                }
            }
        }

        return null;
    }

    /* (non-Javadoc)
     * @see cpw.mods.fml.common.network.IConnectionHandler#connectionOpened(net.minecraft.network.packet.NetHandler, java.lang.String, int, net.minecraft.network.INetworkManager)
     */
    @Override
    public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
        // NOOP
    }

    /* (non-Javadoc)
     * @see cpw.mods.fml.common.network.IConnectionHandler#connectionOpened(net.minecraft.network.packet.NetHandler, net.minecraft.server.MinecraftServer, net.minecraft.network.INetworkManager)
     */
    @Override
    public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
        // NOOP
    }

    /* (non-Javadoc)
     * @see cpw.mods.fml.common.network.IConnectionHandler#connectionClosed(net.minecraft.network.INetworkManager)
     */
    @Override
    public void connectionClosed(INetworkManager manager) {
        // NOOP
    }

    /* (non-Javadoc)
     * @see cpw.mods.fml.common.network.IConnectionHandler#clientLoggedIn(net.minecraft.network.packet.NetHandler, net.minecraft.network.INetworkManager, net.minecraft.network.packet.Packet1Login)
     */
    @Override
    public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
        // NOOP
    }

}

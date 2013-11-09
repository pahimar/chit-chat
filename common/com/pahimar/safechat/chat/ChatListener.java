package com.pahimar.safechat.chat;

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

        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * cpw.mods.fml.common.network.IChatListener#clientChat(net.minecraft.network
     * .packet.NetHandler, net.minecraft.network.packet.Packet3Chat)
     */
    @Override
    public Packet3Chat clientChat(NetHandler handler, Packet3Chat message) {

        return null;
    }
}

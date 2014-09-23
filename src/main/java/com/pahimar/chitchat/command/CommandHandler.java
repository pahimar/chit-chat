package com.pahimar.chitchat.command;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

/**
 * ChitChat
 * <p/>
 * CommandHandler
 *
 * @author pahimar
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 */
public class CommandHandler
{

    public static void initCommands(FMLServerStartingEvent event)
    {

        event.registerServerCommand(new CommandChitChatClient());
        event.registerServerCommand(new CommandChitChatServer());
    }
}

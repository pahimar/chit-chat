package com.pahimar.chitchat.command;

import com.pahimar.chitchat.reference.Reference;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

// TODO Complete server side commands
public class CommandChitChatServer extends CommandBase
{

    @Override
    public String getCommandName()
    {

        return Reference.MOD_NAME.toLowerCase() + "-server";
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender)
    {

        return null;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {

    }

    @Override
    @SuppressWarnings("rawtypes")
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {

        return new ArrayList();
    }
}

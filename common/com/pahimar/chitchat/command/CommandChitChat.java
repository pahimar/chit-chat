package com.pahimar.chitchat.command;

import java.util.Arrays;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;

import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.lib.Reference;

public class CommandChitChat extends CommandBase {

    @Override
    public String getCommandName() {

        return Reference.MOD_NAME.toLowerCase();
    }

    public int getRequiredPermissionLevel() {

        return 3;
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args) {

        if (args.length > 0) {
            
            String commandName = args[0];

            if (commandName.equalsIgnoreCase(Commands.CHECK_STRIKE_SYSTEM)) {
                processCheckStrikeSystem(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.ENABLE_STRIKE_SYSTEM)) {
                processEnableStrikeSystem(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.DISABLE_STRIKE_SYSTEM)) {
                processDisableStrikeSystem(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.ADD_BANNED_WORD)) {
                processAddBannedWord(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.REMOVE_BANNED_WORD)) {
                processRemoveBannedWord(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.CLEAR_BANNED_WORDS)) {
                processClearBannedWords(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.LIST_STRIKES)) {
                processListStrikes(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.LIST_ALL_STRIKES)) {
                processListAllStrikes(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.ADD_STRIKE)) {
                processAddStrike(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.CLEAR_STRIKES)) {
                processClearStrikes(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.CLEAR_ALL_STRIKES)) {
                processClearAllStrikes(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.STRIKE_DURATION)) {
                processStrikeDuration(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.SET_STRIKE_DURATION)) {
                processSetStrikeDuration(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.STRIKEOUT_PENALTY)) {
                processStrikeoutPenalty(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.SET_STRIKEOUT_PENALTY)) {
                processSetStrikeoutPenalty(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.STRIKEOUT_DURATION)) {
                processStrikeoutDuration(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.SET_STRIKEOUT_DURATION)) {
                processSetStrikeoutDuration(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.STRIKEOUT_NOTIFY_OPS)) {
                processStrikeoutNotifyOps(commandSender, args);
            }
            else if (commandName.equalsIgnoreCase(Commands.SET_STRIKEOUT_NOTIFY_OPS)) {
                processSetStrikeoutNotifyOps(commandSender, args);
            }
        }
    }
    
    @Override
    @SuppressWarnings("rawtypes")
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args) {

        switch (args.length) {
            case 1: {
                return getListOfStringsMatchingLastWord(args, Commands.COMMAND_LIST);
            }
            case 2: {
                if (Arrays.asList(Commands.COMMAND_LIST).contains(args[0].toLowerCase())) {
                    return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
                }
            }
            default: {
                return null;
            }
        }
    }
    
    private static void processCheckStrikeSystem(ICommandSender commandSender, String[] args) {
        commandSender.sendChatToPlayer(ChatMessageComponent.createFromText(String.format("Strike System is currently %s", Settings.STRIKE_SYSTEM_ENABLED)));
    }
    
    private static void processEnableStrikeSystem(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processDisableStrikeSystem(ICommandSender commandSender, String[] args) {
    
    }
    
    private static void processAddBannedWord(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processRemoveBannedWord(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processClearBannedWords(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processListStrikes(ICommandSender commandSender, String[] args) {
        commandSender.sendChatToPlayer(ChatMessageComponent.createFromText("list-strikes"));
    }
    
    private static void processListAllStrikes(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processAddStrike(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processClearStrikes(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processClearAllStrikes(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processStrikeDuration(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processSetStrikeDuration(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processStrikeoutPenalty(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processSetStrikeoutPenalty(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processStrikeoutDuration(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processSetStrikeoutDuration(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processStrikeoutNotifyOps(ICommandSender commandSender, String[] args) {
        
    }
    
    private static void processSetStrikeoutNotifyOps(ICommandSender commandSender, String[] args) {
        
    }
}

package com.pahimar.chitchat.command;

public class Commands {

    public static final String CHECK_STRIKE_SYSTEM = "check-strike-system";
    public static final String ENABLE_STRIKE_SYSTEM = "enable-strike-system";
    public static final String DISABLE_STRIKE_SYSTEM = "disable-strike-system";
    
    public static final String ADD_BANNED_WORD = "add-banned-word";
    public static final String REMOVE_BANNED_WORD = "remove-banned-word";
    public static final String CLEAR_BANNED_WORDS = "clear-banned-words";

    public static final String LIST_STRIKES = "list-strikes";
    public static final String LIST_ALL_STRIKES = "list-all-strikes";
    public static final String ADD_STRIKE = "add-strike";
    public static final String CLEAR_STRIKES = "clear-strikes";
    public static final String CLEAR_ALL_STRIKES = "clear-all-strikes";

    public static final String STRIKE_DURATION = "strike-duration";
    public static final String SET_STRIKE_DURATION = "set-strike-duration";

    public static final String STRIKEOUT_PENALTY = "strikeout-penalty";
    public static final String SET_STRIKEOUT_PENALTY = "set-strikeout-penalty";

    public static final String STRIKEOUT_DURATION = "strikeout-duration";
    public static final String SET_STRIKEOUT_DURATION = "set-strikeout-duration";

    public static final String STRIKEOUT_NOTIFY_OPS = "strikeout-notify-ops";
    public static final String SET_STRIKEOUT_NOTIFY_OPS = "set-strikeout-notify-ops";
    
    public static final String[] COMMAND_LIST = {
        CHECK_STRIKE_SYSTEM, ENABLE_STRIKE_SYSTEM, DISABLE_STRIKE_SYSTEM,
        ADD_BANNED_WORD, REMOVE_BANNED_WORD, CLEAR_BANNED_WORDS, 
        LIST_STRIKES, LIST_ALL_STRIKES, ADD_STRIKE, CLEAR_STRIKES, CLEAR_ALL_STRIKES, 
        STRIKE_DURATION, SET_STRIKE_DURATION,
        STRIKEOUT_PENALTY, SET_STRIKEOUT_PENALTY, 
        STRIKEOUT_DURATION, SET_STRIKEOUT_DURATION,
        STRIKEOUT_NOTIFY_OPS, SET_STRIKEOUT_NOTIFY_OPS
    };
    
    public static final String[] ARG_COMMAND_LIST = {
        ENABLE_STRIKE_SYSTEM, DISABLE_STRIKE_SYSTEM,
        ADD_BANNED_WORD, REMOVE_BANNED_WORD, 
        LIST_STRIKES, ADD_STRIKE, CLEAR_STRIKES,
        SET_STRIKE_DURATION,
        SET_STRIKEOUT_PENALTY, 
        SET_STRIKEOUT_DURATION,
        SET_STRIKEOUT_NOTIFY_OPS
    };
}

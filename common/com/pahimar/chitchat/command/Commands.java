package com.pahimar.chitchat.command;

import com.pahimar.chitchat.lib.Reference;

public class Commands {

    public static final String BASE = Reference.MOD_NAME.toLowerCase();

    public static final String ADD_BANNED_WORD = "add-banned-word";
    public static final String REMOVE_BANNED_WORD = "remove-banned-word";
    public static final String CLEAR_BANNED_WORDS = "clear-banned-words";

    public static final String LIST_STRIKES = "list-strikes";
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
}

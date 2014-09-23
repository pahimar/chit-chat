package com.pahimar.chitchat.reference;

/**
 * ChitChat
 * <p/>
 * Strings
 *
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Strings
{

    public static final String CENSOR_REPLACEMENT_TEXT = "chitchat:censor_replacement_text";

    // Strike reasons
    public static final String REASON_BANNED_WORD_USAGE = "Banned word usage";
    public static final String REASON_ADMIN = "Admin";
    public static final String REASON_STRIKEOUT_ADMIN = "an admin struck you out!";
    public static final String REASON_STRIKEOUT_WORD_USAGE = "struck out for using banned words!";
    public static final String REASON_STRIKEOUT_KICK = "Strike out kick";
    public static final String REASON_STRIKEOUT_KICK_AND_TIMEOUT = "Strike out kick and time out";
    public static final String REASON_STRIKEOUT_BAN = "Strike out ban";
    public static final String REASON_STRIKE_EXPIRATION = "Strike expiration";

    // Strike actions
    public static final String ACTION_KICKED = "kicked";
    public static final String ACTION_CHAT_DISABLED = "chat disabled";
    public static final String ACTION_TIMED_OUT = "timed out";
    public static final String ACTION_BANNED = "banned";

    // String templates
    public static final String TEMPLATE_CENSOR_WORD_MESSAGE = "Partially censored a message that was sent from player '%s' for using banned words: %s";
    public static final String TEMPLATE_CENSOR_LINE_MESSAGE = "Completely censored a message that was sent from player '%s' for using banned words: %s";
    public static final String TEMPLATE_HIDE_MESSAGE = "Stopped a message from being sent from player '%s' for using banned words: %s";
    public static final String TEMPLATE_STRIKE_COUNT_MESSAGE = "<%s-Server> Strike %s of %s!";
    public static final String TEMPLATE_CHAT_DISABLED_MESSAGE = "<%s-Server> Your chat has been disabled for %s (Reason: %s)";
    public static final String TEMPLATE_CHAT_STILL_DISABLED_MESSAGE = "<%s-Server> Not allowed to say anything for another %s (Reason: %s)";
    public static final String TEMPLATE_ADMIN_MESSAGE = "<%s-Server> %s";
    public static final String TEMPLATE_STRUCK_OUT_KICK_MESSAGE = "%s: You've been kicked from the server because - %s";
    public static final String TEMPLATE_STRUCK_OUT_TIMEOUT_MESSAGE = "%s: You've been removed from the server for %s because - %s";
    public static final String TEMPLATE_STRUCK_OUT_BANNED_MESSAGE = "%s: You've been banned from the server because - %s";
    public static final String TEMPLATE_STRIKE_ADDED = "Player '%s' has received a strike (Reason: %s)";
    public static final String TEMPLATE_STRIKES_CLEARED = "Player '%s' has had all strikes cleared (Reason: %s)";
    public static final String TEMPLATE_STRIKE_OUT_WITH_PENALTY = "Player '%s' has struck out and was %s (Reason: %s)";
    public static final String TEMPLATE_STRIKE_OUT_WITH_TIMED_PENALTY = "Player '%s' has struck out and will be penalized for %s (Reason: %s)";
    public static final String TEMPLATE_STRIKE_OUT_WITH_TIMED_PENALTY_AND_ACTION = "Player '%s' has struck out and will be penalized for %s with the following penalty: %s (Reason: %s)";
    public static final String TEMPLATE_TIME_OUT_ATTEMPTED_JOIN = "[ChitChat] Player '%s' is timed out and prevented from joining the server again for another %s (Reason: Struck out)";

    // Regex util constants
    public static final String REGEX_WORD_DELIMITER = "\\b";
    public static final String REGEX_SPACE_CHARACTER = "\\s";
    public static final String REGEX_SPECIAL_CHARACTERS = "\\^$.|?*+()[{";

}

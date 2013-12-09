package com.pahimar.chitchat.configuration;

import com.pahimar.chitchat.lib.Reference;

/**
 * ChitChat
 * 
 * Settings
 * 
 * @author pahimar
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 * 
 */
public class Settings {

    /*
     *  FML capabilities
     */
    public static boolean FML_CAN_CANCEL_MESSAGES;
    
    /*
     *  
     */
    public static String CONFIG_DIRECTORY_PATH;
    
    /*
     *  Filter mode
     */
    public static int FILTER_MODE;
    public static final String FILTER_MODE_CONFIGNAME = "filter_mode";
    public static final int FILTER_MODE_DEFAULT = Reference.FILTER_MODE_LINE_CENSOR;
    public static final String FILTER_MODE_COMMENT = "Sets the filtering mode for ChitChat (default is 1):\n0 - No filtering\n1 - Word replacement (black listed words are replaced with ******)\n2 - Line replacement (entire line is replaced)\n3 - Hidden (chat messages containing black listed words do not appear)";
    
    /*
     *  Use default banned word list
     */
    public static boolean DEFAULT_BAN_LIST_ENABLED;
    public static final String DEFAULT_BAN_LIST_ENABLED_CONFIGNAME = "default_banned_words_list_enabled";
    public static final boolean DEFAULT_BAN_LIST_ENABLED_DEFAULT = true;
    public static final String DEFAULT_BAN_LIST_ENABLED_COMMENT = "Whether or not to use the included default banned words list (default is true)";
    
    /*
     *  Default censor replacement text
     */
    public static String CENSOR_REPLACEMENT_TEXT;
    public static final String CENSOR_REPLACEMENT_TEXT_CONFIGNAME = "server.censor_replacement_text";
    public static final String CENSOR_REPLACEMENT_TEXT_DEFAULT = "******";
    public static final String CENSOR_REPLACEMENT_TEXT_COMMENT = "The replacement text that should be used when the server censors a word/line a player says (default is '******')";
    
    /*
     *  Strikes
     */
    public static boolean STRIKE_SYSTEM_ENABLED;
    public static final String STRIKE_SYSTEM_ENABLED_CONFIGNAME = "strikes.enabled";
    public static final boolean STRIKE_SYSTEM_ENABLED_DEFAULT = false;
    public static final String STRIKE_SYSTEM_ENABLED_COMMENT = "Whether or not the Strikes System is enabled (default is 'false')";
    
    public static int MAX_STRIKES_ALLOWED;
    public static final String MAX_STRIKES_ALLOWED_CONFIGNAME = "strikes.max_allowed";
    public static final int MAX_STRIKES_ALLOWED_DEFAULT = 3;
    public static final String MAX_STRIKES_ALLOWED_COMMENT = "Maximum number of strikes a player is allowed at one time (default is 3)";
    
    public static int STRIKE_DURATION_TIME;
    public static final String STRIKE_DURATION_TIME_CONFIGNAME = "strikes.duration";
    public static final int STRIKE_DURATION_TIME_DEFAULT = 60;
    public static final String STRIKE_DURATION_TIME_COMMENT = "How long, in seconds, a strike will stay on a player until the strike is removed (default is 60, maximum is 86,400 - 1 day)";
    
    /*
     *  Strikeout Action
     */
    public static int STRIKEOUT_ACTION;
    public static final String STRIKEOUT_ACTION_CONFIGNAME = "strikeout.action";
    public static final int STRIKEOUT_ACTION_DEFAULT = Reference.ACTION_DISABLE_CHAT;
    public static final String STRIKEOUT_ACTION_COMMENT = "Sets the action for when a player exceeds the max number of allowed strikes (default is 2):\n0 - Nothing\n1 - Kick\n2 - Disables sending of chat messages (for the specified duration)\n3 - Time out (kicks the player and does not allow rejoin until for the specified duration\n4 - Ban (player is removed from the server and placed on the server's player blacklist)";
    
    public static boolean STRIKEOUT_NOTIFY_OPS;
    public static final String STRIKEOUT_NOTIFY_OPS_CONFIGNAME = "strikeout.notify_admins";
    public static final boolean STRIKEOUT_NOTIFY_OPS_DEFAULT = true;
    public static final String STRIKEOUT_NOTIFY_OPS_COMMENT = "Whether or not server admins are notified when a player strikes out (default is 'true')";
    
    public static int STRIKEOUT_ACTION_DURATION;
    public static final String STRIKEOUT_ACTION_DURATION_CONFIGNAME = "strikeout.duration";
    public static final int STRIKEOUT_ACTION_DURATION_DEFAULT = 300;
    public static final String STRIKEOUT_ACTION_DURATION_COMMENT = "How long the action lasts for when a player exceeds the max number of allowed strikes, in seconds (default is 60, maximum is 86,400 - 1 day)";
}

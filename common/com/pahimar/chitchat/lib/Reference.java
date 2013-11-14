package com.pahimar.chitchat.lib;

/**
 * ChitChat
 * 
 * Reference
 * 
 * @author pahimar
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 * 
 */
public class Reference {

    /* General @Mod related constants */
    public static final String MOD_ID = "ChitChat";
    public static final String MOD_NAME = "ChitChat";
    public static final String VERSION_NUMBER = "@VERSION@ (build @BUILD_NUMBER@)";
    public static final String FINGERPRINT = "@FINGERPRINT@";
    
    /* Misc constants */
    public static final int MIN_FML_BUILD_NUMBER = 951;
    
    /* Locations of the blacklists */
    public static final String DEFAULT_BLACKLIST_FILE_LOCATION = "/assets/chitchat/lang/defaultCurseBlackList.lang";
    public static final String CONFIG_BLACKLIST_FILE_LOCATION = "";
    
    /* Filter mode related constants */
    public static final int FILTER_MODE_NONE = 0;
    public static final int FILTER_MODE_WORD_CENSOR = 1;
    public static final int FILTER_MODE_LINE_CENSOR = 2;
    public static final int FILTER_MODE_HIDE = 3;
    
    /* Action related constants */
    public static final int ACTION_NOTHING = 0;
    public static final int ACTION_KICK = 1;
    public static final int ACTION_DISABLE_CHAT = 2;
    public static final int ACTION_TIME_OUT = 3;
    public static final int ACTION_BAN = 4;
}

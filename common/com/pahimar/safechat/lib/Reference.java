package com.pahimar.safechat.lib;

/**
 * SafeChat
 * 
 * Reference
 * 
 * @author pahimar
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 * 
 */
public class Reference {

    /* Debug Mode On-Off */
    public static final boolean DEBUG_MODE = false;

    /* General Mod related constants */
    public static final String MOD_ID = "SafeChat";
    public static final String MOD_NAME = "SafeChat";
    public static final String VERSION_NUMBER = "@VERSION@ (build @BUILD_NUMBER@)";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String DEPENDENCIES = "required-after:Forge@[9.10.1.849,)";
    public static final String FINGERPRINT = "@FINGERPRINT@";
    public static final String SERVER_PROXY_CLASS = "com.pahimar.safechat.core.proxy.CommonProxy";
    public static final String CLIENT_PROXY_CLASS = "com.pahimar.safechat.core.proxy.ClientProxy";
    public static final int VERSION_CHECK_ATTEMPTS = 3;

}


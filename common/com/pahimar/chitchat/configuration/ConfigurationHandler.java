package com.pahimar.chitchat.configuration;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import com.pahimar.chitchat.helper.GeneralHelper;
import com.pahimar.chitchat.lib.Reference;

import cpw.mods.fml.common.FMLLog;

/**
 * ChitChat
 * 
 * ConfigurationHandler
 * 
 * @author pahimar
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 * 
 */
public class ConfigurationHandler {

    private static Configuration configuration;
    
    private static final String CATEGORY_STRIKE_SYSTEM = "strike_system";
    private static final String CATEGORY_STRIKE_SYSTEM_COMMENT = "Settings related to the Strikes sytem. These settings are server-only.";

    public static void init(String configPath) {

        configuration = new Configuration(new File(configPath + "general.properties"));

        try {
            configuration.load();

            Settings.FILTER_MODE = configuration.get(CATEGORY_GENERAL, Settings.FILTER_MODE_CONFIGNAME, "" + Settings.FILTER_MODE_DEFAULT, Settings.FILTER_MODE_COMMENT, Property.Type.INTEGER).getInt(Settings.FILTER_MODE_DEFAULT);
            
            Settings.DEFAULT_BAN_LIST_ENABLED = configuration.get(CATEGORY_GENERAL, Settings.DEFAULT_BAN_LIST_ENABLED_CONFIGNAME, "" + Settings.DEFAULT_BAN_LIST_ENABLED_DEFAULT, Settings.DEFAULT_BAN_LIST_ENABLED_COMMENT, Property.Type.BOOLEAN).getBoolean(Settings.DEFAULT_BAN_LIST_ENABLED_DEFAULT);
            
            Settings.CENSOR_REPLACEMENT_TEXT = configuration.get(CATEGORY_GENERAL, Settings.CENSOR_REPLACEMENT_TEXT_CONFIGNAME, "" + Settings.CENSOR_REPLACEMENT_TEXT_DEFAULT, Settings.CENSOR_REPLACEMENT_TEXT_COMMENT, Property.Type.STRING).getString();

            Settings.STRIKE_SYSTEM_ENABLED = configuration.get(CATEGORY_GENERAL, Settings.STRIKE_SYSTEM_ENABLED_CONFIGNAME, "" + Settings.STRIKE_SYSTEM_ENABLED_DEFAULT, Settings.STRIKE_SYSTEM_ENABLED_COMMENT, Property.Type.BOOLEAN).getBoolean(Settings.STRIKE_SYSTEM_ENABLED_DEFAULT);

            configuration.addCustomCategoryComment(CATEGORY_STRIKE_SYSTEM, CATEGORY_STRIKE_SYSTEM_COMMENT);
            
            // Strike duration
            Settings.STRIKE_DURATION_TIME = configuration.get(CATEGORY_STRIKE_SYSTEM, Settings.STRIKE_DURATION_TIME_CONFIGNAME, "" + Settings.STRIKE_DURATION_TIME_DEFAULT, Settings.STRIKE_DURATION_TIME_COMMENT, Property.Type.INTEGER).getInt(Settings.STRIKE_DURATION_TIME_DEFAULT);
            if (Settings.STRIKE_DURATION_TIME > Reference.MAX_SECONDS) {
                Settings.STRIKE_DURATION_TIME = Reference.MAX_SECONDS;
            }
            
            // Maximum number of strikes allowed
            Settings.MAX_STRIKES_ALLOWED = configuration.get(CATEGORY_STRIKE_SYSTEM, Settings.MAX_STRIKES_ALLOWED_CONFIGNAME, "" + Settings.MAX_STRIKES_ALLOWED_DEFAULT, Settings.MAX_STRIKES_ALLOWED_COMMENT, Property.Type.INTEGER).getInt(Settings.MAX_STRIKES_ALLOWED_DEFAULT);

            // Strikeout penalty
            Settings.STRIKEOUT_ACTION = configuration.get(CATEGORY_STRIKE_SYSTEM, Settings.STRIKEOUT_ACTION_CONFIGNAME, "" + Settings.STRIKEOUT_ACTION_DEFAULT, Settings.STRIKEOUT_ACTION_COMMENT, Property.Type.INTEGER).getInt(Settings.STRIKEOUT_ACTION_DEFAULT);
            
            // Notify admins on strikeout
            Settings.STRIKEOUT_NOTIFY_OPS = configuration.get(CATEGORY_STRIKE_SYSTEM, Settings.STRIKEOUT_NOTIFY_OPS_CONFIGNAME, "" + Settings.STRIKEOUT_NOTIFY_OPS_DEFAULT, Settings.STRIKEOUT_NOTIFY_OPS_COMMENT, Property.Type.BOOLEAN).getBoolean(Settings.STRIKEOUT_NOTIFY_OPS_DEFAULT);

            // Strikeout penalty duration
            Settings.STRIKEOUT_ACTION_DURATION = configuration.get(CATEGORY_STRIKE_SYSTEM, Settings.STRIKEOUT_ACTION_DURATION_CONFIGNAME, "" + Settings.STRIKEOUT_ACTION_DURATION_DEFAULT, Settings.STRIKEOUT_ACTION_DURATION_COMMENT, Property.Type.INTEGER).getInt(Settings.STRIKEOUT_ACTION_DURATION_DEFAULT);
            if (Settings.STRIKEOUT_ACTION_DURATION > Reference.MAX_SECONDS) {
                Settings.STRIKEOUT_ACTION_DURATION = Reference.MAX_SECONDS;
            }
            
            Settings.FML_CAN_CANCEL_MESSAGES = GeneralHelper.canFMLCancelChatMessages();
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its general configuration");
        }
        finally {
            configuration.save();
        }
    }

    public static void set(String categoryName, String propertyName, String newValue) {

        configuration.load();
        if (configuration.getCategoryNames().contains(categoryName)) {
            if (configuration.getCategory(categoryName).containsKey(propertyName)) {
                configuration.getCategory(categoryName).get(propertyName).set(newValue);
            }
        }
        configuration.save();
    }
}

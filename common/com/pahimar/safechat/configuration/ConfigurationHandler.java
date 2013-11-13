package com.pahimar.safechat.configuration;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import com.pahimar.safechat.helper.GeneralHelper;
import com.pahimar.safechat.lib.Reference;

import cpw.mods.fml.common.FMLLog;

/**
 * SafeChat
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

            Settings.STRIKE_SYSTEM_ENABLED = configuration.get(CATEGORY_GENERAL, Settings.STRIKE_SYSTEM_ENABLED_CONFIGNAME, "" + Settings.STRIKE_SYSTEM_ENABLED_DEFAULT, Settings.STRIKE_SYSTEM_ENABLED_COMMENT, Property.Type.BOOLEAN).getBoolean(Settings.STRIKE_SYSTEM_ENABLED_DEFAULT);

            configuration.addCustomCategoryComment(CATEGORY_STRIKE_SYSTEM, CATEGORY_STRIKE_SYSTEM_COMMENT);
            
            Settings.STRIKE_EXPIRATION_TIME = configuration.get(CATEGORY_STRIKE_SYSTEM, Settings.STRIKE_EXPIRATION_TIME_CONFIGNAME, "" + Settings.STRIKE_EXPIRATION_TIME_DEFAULT, Settings.STRIKE_EXPIRATION_TIME_COMMENT, Property.Type.INTEGER).getInt(Settings.STRIKE_EXPIRATION_TIME_DEFAULT);
            
            Settings.MAX_STRIKES_ALLOWED = configuration.get(CATEGORY_STRIKE_SYSTEM, Settings.MAX_STRIKES_ALLOWED_CONFIGNAME, "" + Settings.MAX_STRIKES_ALLOWED_DEFAULT, Settings.MAX_STRIKES_ALLOWED_COMMENT, Property.Type.INTEGER).getInt(Settings.MAX_STRIKES_ALLOWED_DEFAULT);

            Settings.STRIKEOUT_ACTION = configuration.get(CATEGORY_STRIKE_SYSTEM, Settings.STRIKEOUT_ACTION_CONFIGNAME, "" + Settings.STRIKEOUT_ACTION_DEFAULT, Settings.STRIKEOUT_ACTION_COMMENT, Property.Type.INTEGER).getInt(Settings.STRIKEOUT_ACTION_DEFAULT);

            Settings.STRIKEOUT_ACTION_DURATION = configuration.get(CATEGORY_STRIKE_SYSTEM, Settings.STRIKEOUT_ACTION_DURATION_CONFIGNAME, "" + Settings.STRIKEOUT_ACTION_DURATION_DEFAULT, Settings.STRIKEOUT_ACTION_DURATION_COMMENT, Property.Type.INTEGER).getInt(Settings.STRIKEOUT_ACTION_DURATION_DEFAULT);
            
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

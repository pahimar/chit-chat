package com.pahimar.safechat.configuration;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import com.pahimar.safechat.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class ConfigurationHandler {

    private static Configuration configuration;

    public static void init(String configPath) {
        
        configuration = new Configuration(new File(configPath + "general.properties"));
        
        try {
            configuration.load();
            Settings.FILTER_MODE = configuration.get(CATEGORY_GENERAL, Settings.FILTER_MODE_CONFIGNAME, Settings.FILTER_MODE_DEFAULT).getInt(Settings.FILTER_MODE_DEFAULT);
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

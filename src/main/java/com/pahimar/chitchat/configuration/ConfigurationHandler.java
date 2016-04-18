package com.pahimar.chitchat.configuration;

import com.pahimar.chitchat.reference.Reference;
import com.pahimar.chitchat.util.helper.LogHelper;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{

    private static Configuration configuration;

    public static void init(String configPath) {

        configuration = new Configuration(new File(configPath + "general.properties"));

        try {
            configuration.load();
        }
        catch (Exception e) {
            LogHelper.getLogger().error(LogHelper.MOD_MARKER, Reference.MOD_NAME + " has had a problem loading its general configuration", e);
        }
        finally
        {
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

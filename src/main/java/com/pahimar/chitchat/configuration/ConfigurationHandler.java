package com.pahimar.chitchat.configuration;

import com.pahimar.chitchat.ChitChat;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;

    private static final String CATEGORY_DEBUG = "general.debug";

    public static void init(File configFile) {

        if (configuration == null) {
            configuration = new Configuration(configFile, true);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {

        Settings.debugEnabled = configuration.getBoolean(
                Settings.DEBUG_ENABLED_NAME,
                CATEGORY_DEBUG,
                Settings.DEBUG_ENABLED_DEFAULT,
                I18n.translateToLocal(Settings.DEBUG_ENABLED_COMMENT),
                Settings.DEBUG_ENABLED_LABEL);

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {

        if (event.getModID().equalsIgnoreCase(ChitChat.MOD_ID)) {
            loadConfiguration();
        }
    }

    public static class Settings {

        public static boolean debugEnabled;
        private static final String DEBUG_ENABLED_NAME = "enabled";
        private static final String DEBUG_ENABLED_LABEL = "debug.enabled.label";
        private static final String DEBUG_ENABLED_COMMENT = "debug.enabled.comment";
        private static final boolean DEBUG_ENABLED_DEFAULT = false;
    }
}

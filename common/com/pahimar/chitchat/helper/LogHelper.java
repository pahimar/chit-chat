package com.pahimar.chitchat.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.pahimar.chitchat.lib.Reference;

import cpw.mods.fml.common.FMLLog;

/**
 * ChitChat
 * 
 * LogHelper
 * 
 * @author pahimar
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 * 
 */
public class LogHelper {

    private static Logger logger = Logger.getLogger(Reference.MOD_ID);

    public static void init() {

        logger.setParent(FMLLog.getLogger());
    }

    public static void log(Level logLevel, Object object) {

        if (object != null) {
            logger.log(logLevel, object.toString());
        }
        else {
            logger.log(logLevel, "null");
        }
    }

    public static void severe(Object object) {

        log(Level.SEVERE, object);
    }

    public static void debug(Object object) {

        if (object != null) {
            log(Level.WARNING, "[DEBUG] " + object.toString());
        }
        else {
            log(Level.WARNING, object);
        }
    }

    public static void warning(Object object) {

        log(Level.WARNING, object);
    }

    public static void info(Object object) {

        log(Level.INFO, object);
    }

    public static void config(Object object) {

        log(Level.CONFIG, object);
    }

    public static void fine(Object object) {

        log(Level.FINE, object);
    }

    public static void finer(Object object) {

        log(Level.FINER, object);
    }

    public static void finest(Object object) {

        log(Level.FINEST, object);
    }
}

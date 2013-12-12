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

        if (object != null) {
            log(Level.SEVERE, object.toString());
        }
        else {
            log(Level.SEVERE, "null");
        }
    }

    public static void debug(Object object) {

        if (object != null) {
            log(Level.WARNING, "[DEBUG] " + object.toString());
        }
        else {
            log(Level.WARNING, "null");
        }
    }

    public static void warning(Object object) {

        if (object != null) {
            log(Level.WARNING, object.toString());
        }
        else {
            log(Level.WARNING, "null");
        }
    }

    public static void info(Object object) {

        if (object != null) {
            log(Level.INFO, object.toString());
        }
        else {
            log(Level.INFO, "null");
        }
    }

    public static void config(Object object) {

        if (object != null) {
            log(Level.CONFIG, object.toString());
        }
        else {
            log(Level.CONFIG, "null");
        }
    }

    public static void fine(Object object) {

        if (object != null) {
            log(Level.FINE, object.toString());
        }
        else {
            log(Level.FINE, "null");
        }
    }

    public static void finer(Object object) {

        if (object != null) {
            log(Level.FINER, object.toString());
        }
        else {
            log(Level.FINER, "null");
        }
    }

    public static void finest(Object object) {

        if (object != null) {
            log(Level.FINEST, object.toString());
        }
        else {
            log(Level.FINEST, "null");
        }
    }
}

package com.pahimar.chitchat.helper;

import com.pahimar.chitchat.lib.Reference;

import cpw.mods.fml.common.Loader;

public class GeneralHelper {

    public static boolean canFMLCancelChatMessages() {

        String fmlVersion = Loader.instance().getFMLVersionString();
        String[] fmlVersionComponents = fmlVersion.split("\\.");

        if (fmlVersionComponents.length == 4) {
            try {
                if (Integer.parseInt(fmlVersionComponents[3]) >= Reference.MIN_FML_BUILD_NUMBER) {
                    return true;
                }
            }
            catch (NumberFormatException e) {
                return false;
            }
        }

        return false;
    }

    public static String formatTimeFromTicks(int ticks) {

        int totalSeconds = ticks / Reference.TICKS_IN_SECOND;

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = (totalSeconds % 3600) % 60;

        if (totalSeconds >= 3600) {
            return String.format("%dh %dmin %dsec", hours, minutes, seconds);
        }
        else if (totalSeconds >= 60) {
            return String.format("%dmin %dsec", minutes, seconds);
        }
        else {
            return String.format("%dsec", seconds);
        }
    }

}

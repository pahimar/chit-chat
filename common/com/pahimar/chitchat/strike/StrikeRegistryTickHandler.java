package com.pahimar.chitchat.strike;

import java.util.EnumSet;

import com.pahimar.chitchat.lib.Reference;

import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;

public class StrikeRegistryTickHandler implements IScheduledTickHandler {

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

        // NOP
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

        for (TickType tickType : type) {
            if (tickType == TickType.SERVER) {
                StrikeRegistry.getInstance().updateRegistry();
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel() {

        return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
    }

    @Override
    public int nextTickSpacing() {

        return Reference.TICKS_IN_SECOND;
    }
}

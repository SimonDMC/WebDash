package com.simondmc.webdash.data;

import com.simondmc.webdash.config.DataConfig;
import com.simondmc.webdash.websocket.WSSHandler;

public class StatusHandler {

    private static boolean enabled;

    public static void init() {
        enabled = DataConfig.getEnabled();
    }

    public static boolean enable() {
        // already on
        if (enabled) return false;

        enabled = true;
        DataConfig.setEnabled(true);

        // close all connections
        WSSHandler.closeAll();

        return true;
    }

    public static boolean disable() {
        // already off
        if (!enabled) return false;

        enabled = false;
        DataConfig.setEnabled(false);

        // close all connections
        WSSHandler.closeAll();

        return true;
    }

    public static boolean isEnabled() {
        return enabled;
    }
}

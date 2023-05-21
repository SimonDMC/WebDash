package com.simondmc.webdash.dashboard;

import com.simondmc.webdash.config.DataConfig;

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
        return true;
    }

    public static boolean disable() {
        // already off
        if (!enabled) return false;

        enabled = false;
        DataConfig.setEnabled(false);

        return true;
    }

    public static boolean isEnabled() {
        return enabled;
    }
}

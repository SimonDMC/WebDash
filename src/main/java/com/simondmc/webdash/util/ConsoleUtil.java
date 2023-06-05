package com.simondmc.webdash.util;

import org.bukkit.Bukkit;

public class ConsoleUtil {
    public static void sendColored(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }
}

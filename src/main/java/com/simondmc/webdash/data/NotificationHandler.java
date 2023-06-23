package com.simondmc.webdash.data;

import com.simondmc.webdash.config.DataConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class NotificationHandler {

    private static List<String> playersToNotify;
    private static boolean notifyConsole;

    public static void init() {
        playersToNotify = DataConfig.getPlayersToNotify();
        notifyConsole = DataConfig.getConsoleNotifyEnabled();
    }

    public static boolean toggleNotification(CommandSender sender) {
        // if sender is a player, toggle them from the list
        if (sender instanceof Player) {
            String uuid = ((Player) sender).getUniqueId().toString();
            if (playersToNotify.contains(uuid)) {
                playersToNotify.remove(uuid);
                DataConfig.setPlayersToNotify(playersToNotify);
                return false;
            } else {
                playersToNotify.add(uuid);
                DataConfig.setPlayersToNotify(playersToNotify);
                return true;
            }
        } else {
            // if ran from console or command block, toggle console notifications
            if (notifyConsole) {
                notifyConsole = false;
                DataConfig.setConsoleNotifyEnabled(notifyConsole);
                return false;
            } else {
                notifyConsole = true;
                DataConfig.setConsoleNotifyEnabled(notifyConsole);
                return true;
            }
        }
    }
}

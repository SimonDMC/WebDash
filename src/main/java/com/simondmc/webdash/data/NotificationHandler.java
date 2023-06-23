package com.simondmc.webdash.data;

import com.simondmc.webdash.config.DataConfig;
import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.util.ChatUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

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

    private static void notify(String message, CommandSender toExclude) {
        // send message to all players in the list
        for (String uuid : playersToNotify) {
            Player player = Bukkit.getPlayer(UUID.fromString(uuid));
            if (player != null && !player.equals(toExclude)) {
                player.sendMessage("§d[WebDash] §r" + message);
            }
        }
        // send message to console if enabled
        if (notifyConsole && !Bukkit.getConsoleSender().equals(toExclude)) {
            ChatUtil.sendColored("§d[WebDash] §r" + message);
        }
    }

    public static void notifyRouteAdd(Route r, CommandSender toExclude) {
        notify(String.format(MessagesConfig.get("notify-route-add"), ChatColor.of(r.getColor()) + r.getName(), r.getCommand()), toExclude);
    }

    public static void notifyRouteEdit(Route oldRoute, Route newRoute) {
        String oldColor = oldRoute.getColor();
        String newColor = newRoute.getColor();
        String oldName = oldRoute.getName();
        String newName = newRoute.getName();
        String oldCommand = oldRoute.getCommand();
        String newCommand = newRoute.getCommand();

        boolean sameColor = oldColor.equals(newColor);
        boolean sameName = oldName.equals(newName);
        boolean sameCommand = oldCommand.equals(newCommand);

        // only name and/or color changed
        if (!(sameColor && sameName) && sameCommand) {
            notify(String.format(MessagesConfig.get("notify-route-edit-one"), ChatColor.of(oldColor) + oldName, ChatColor.of(oldColor) + oldName, ChatColor.of(newColor) + newName), null);
        }

        // only command changed
        if ((sameColor && sameName) && !sameCommand) {
            notify(String.format(MessagesConfig.get("notify-route-edit-one"), ChatColor.of(oldColor) + oldName, "§a/" + oldCommand, "§a/" + newCommand), null);
        }

        // both changed
        if (!(sameColor && sameName) && !sameCommand) {
            notify(String.format(MessagesConfig.get("notify-route-edit-two"), ChatColor.of(oldColor) + oldName, ChatColor.of(oldColor) + oldName, ChatColor.of(newColor) + newName, "§a/" + oldCommand, "§a/" + newCommand), null);
        }
    }

    public static void notifyRouteRemove(Route r, CommandSender toExclude) {
        notify(String.format(MessagesConfig.get("notify-route-remove"), ChatColor.of(r.getColor()) + r.getName(), r.getCommand()), toExclude);
    }

    public static void notifyOn(CommandSender toExclude) {
        notify(MessagesConfig.get("notify-on"), toExclude);
    }

    public static void notifyOff(CommandSender toExclude) {
        notify(MessagesConfig.get("notify-off"), toExclude);
    }

    public static void notifyKeyOn(CommandSender toExclude) {
        notify(MessagesConfig.get("notify-key-on"), toExclude);
    }

    public static void notifyKeyOff(CommandSender toExclude) {
        notify(MessagesConfig.get("notify-key-off"), toExclude);
    }

    public static void notifyKeyReset(CommandSender toExclude) {
        if (KeyHandler.isEnabled()) {
            notify(MessagesConfig.get("notify-key-reset"), toExclude);
        } else {
            notify(MessagesConfig.get("notify-key-reset-off"), toExclude);
        }
    }
}

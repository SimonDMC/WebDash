package com.simondmc.webdash.command;

import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.dashboard.KeyHandler;
import com.simondmc.webdash.server.WebServer;
import com.simondmc.webdash.util.PlayerUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WebDashKeySubcommand {

    /* /webdash key <on/off/reset> */
    public WebDashKeySubcommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(MessagesConfig.get("key-help"));
            return;
        }

        String subarg = args[1];
        String openSuffix = sender instanceof Player ? MessagesConfig.get("player-open") : MessagesConfig.get("console-open");

        if (subarg.equalsIgnoreCase("on")) {
            if (KeyHandler.enableKey()) {
                String link = WebServer.getLink();
                String message = MessagesConfig.get("key-on") + openSuffix.formatted(link);
                PlayerUtil.sendClickableMessage(sender, message, link);
            } else {
                String link = WebServer.getLink();
                String message = MessagesConfig.get("key-already-on") + openSuffix.formatted(link);
                PlayerUtil.sendClickableMessage(sender, message, link);
            }
            return;
        }
        if (subarg.equalsIgnoreCase("off")) {
            if (KeyHandler.disableKey()) {
                String link = WebServer.getLink();
                String message = MessagesConfig.get("key-off") + openSuffix.formatted(link);
                PlayerUtil.sendClickableMessage(sender, message, link);
            } else {
                String link = WebServer.getLink();
                String message = MessagesConfig.get("key-already-off") + openSuffix.formatted(link);
                PlayerUtil.sendClickableMessage(sender, message, link);
            }
            return;
        }
        if (subarg.equalsIgnoreCase("reset")) {
            KeyHandler.generateKey();
            String link = WebServer.getLink();
            if (KeyHandler.isEnabled()) {
                String message = MessagesConfig.get("key-reset") + openSuffix.formatted(link);
                PlayerUtil.sendClickableMessage(sender, message, link);
            } else {
                String message = MessagesConfig.get("key-reset-off") + openSuffix.formatted(link);
                PlayerUtil.sendClickableMessage(sender, message, link);
            }
            return;
        }
        sender.sendMessage(MessagesConfig.get("key-help"));
    }
}

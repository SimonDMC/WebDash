package com.simondmc.webdash.command.subcommands;

import com.simondmc.webdash.command.WebDashSubcommand;
import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.data.KeyHandler;
import com.simondmc.webdash.data.NotificationHandler;
import com.simondmc.webdash.server.WebServer;
import com.simondmc.webdash.util.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WebDashKeySubcommand implements WebDashSubcommand {

    /* /webdash key <on/off/reset> */
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(MessagesConfig.get("key-help"));
            return;
        }

        String subarg = args[1].toLowerCase();
        String openSuffix = sender instanceof Player ? MessagesConfig.get("player-open") : MessagesConfig.get("console-open");

        if (subarg.equals("on") || subarg.equals("enable")) {
            if (KeyHandler.enableKey()) {
                String link = WebServer.getLink();
                String message = MessagesConfig.get("key-on") + String.format(openSuffix, link);
                ChatUtil.sendClickableMessage(sender, message, link);

                // notify
                NotificationHandler.notifyKeyOn();
            } else {
                String link = WebServer.getLink();
                String message = MessagesConfig.get("key-already-on") + String.format(openSuffix, link);
                ChatUtil.sendClickableMessage(sender, message, link);
            }
            return;
        }
        if (subarg.equals("off") || subarg.equals("disable")) {
            if (KeyHandler.disableKey()) {
                String link = WebServer.getLink();
                String message = MessagesConfig.get("key-off") + String.format(openSuffix, link);
                ChatUtil.sendClickableMessage(sender, message, link);

                // notify
                NotificationHandler.notifyKeyOff();
            } else {
                String link = WebServer.getLink();
                String message = MessagesConfig.get("key-already-off") + String.format(openSuffix, link);
                ChatUtil.sendClickableMessage(sender, message, link);
            }
            return;
        }
        if (subarg.equals("reset")) {
            KeyHandler.generateKey();
            String link = WebServer.getLink();
            if (KeyHandler.isEnabled()) {
                String message = MessagesConfig.get("key-reset") + String.format(openSuffix, link);
                ChatUtil.sendClickableMessage(sender, message, link);
            } else {
                String message = MessagesConfig.get("key-reset-off") + String.format(openSuffix, link);
                ChatUtil.sendClickableMessage(sender, message, link);
            }

            // notify
            NotificationHandler.notifyKeyReset();

            return;
        }
        sender.sendMessage(MessagesConfig.get("key-help"));
    }
}

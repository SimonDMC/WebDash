package com.simondmc.webdash.command;

import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.server.WebServer;
import com.simondmc.webdash.util.PlayerUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WebDashRestartSubcommand {

    /* /webdash restart */
    public WebDashRestartSubcommand(CommandSender sender) {
        if (WebServer.isRunning()) {
            WebServer.stop();
        }
        boolean success = WebServer.start();
        if (success) {
            String link = WebServer.getLink();
            String baseLink = WebServer.getBaseLink();
            String message = String.format(MessagesConfig.get("restart-success"), baseLink);
            if (sender instanceof Player) {
                PlayerUtil.sendClickableMessage(sender, message, link);
            } else {
                sender.sendMessage(message);
            }
        } else {
            sender.sendMessage(MessagesConfig.get("restart-fail"));
        }
    }
}

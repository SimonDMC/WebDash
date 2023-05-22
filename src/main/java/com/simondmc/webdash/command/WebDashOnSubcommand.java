package com.simondmc.webdash.command;

import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.dashboard.KeyHandler;
import com.simondmc.webdash.dashboard.StatusHandler;
import com.simondmc.webdash.server.WebServer;
import com.simondmc.webdash.util.PlayerUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WebDashOnSubcommand {

    /* /webdash on */
    public WebDashOnSubcommand(CommandSender sender) {
        if (!WebServer.isRunning()) {
            sender.sendMessage(MessagesConfig.get("not-running"));
            return;
        }

        String openSuffix = sender instanceof Player ? MessagesConfig.get("player-open") : MessagesConfig.get("console-open");

        if (StatusHandler.enable()) {
            String link = WebServer.getLink();
            String message = MessagesConfig.get("on-success") + openSuffix.formatted(link);
            PlayerUtil.sendClickableMessage(sender, message, link);
        } else {
            String link = WebServer.getLink();
            String message = MessagesConfig.get("on-already-on") + openSuffix.formatted(link);
            PlayerUtil.sendClickableMessage(sender, message, link);
        }
    }
}

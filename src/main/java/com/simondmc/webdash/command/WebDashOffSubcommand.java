package com.simondmc.webdash.command;

import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.dashboard.StatusHandler;
import com.simondmc.webdash.server.WebServer;
import com.simondmc.webdash.util.PlayerUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WebDashOffSubcommand {

    /* /webdash off */
    public WebDashOffSubcommand(CommandSender sender) {
        if (!WebServer.isRunning()) {
            sender.sendMessage(MessagesConfig.get("not-running"));
            return;
        }
        if (StatusHandler.disable()) {
            sender.sendMessage(MessagesConfig.get("off-success"));
        } else {
            sender.sendMessage(MessagesConfig.get("off-already-off"));
        }
    }
}

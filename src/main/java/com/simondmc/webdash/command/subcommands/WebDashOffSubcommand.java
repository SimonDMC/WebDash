package com.simondmc.webdash.command.subcommands;

import com.simondmc.webdash.command.WebDashSubcommand;
import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.data.StatusHandler;
import com.simondmc.webdash.server.WebServer;
import org.bukkit.command.CommandSender;

public class WebDashOffSubcommand implements WebDashSubcommand {

    /* /webdash off */
    @Override
    public void execute(CommandSender sender, String[] args) {
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

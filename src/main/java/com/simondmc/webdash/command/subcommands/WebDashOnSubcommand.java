package com.simondmc.webdash.command.subcommands;

import com.simondmc.webdash.command.WebDashSubcommand;
import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.data.StatusHandler;
import com.simondmc.webdash.server.WebServer;
import com.simondmc.webdash.util.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WebDashOnSubcommand implements WebDashSubcommand {

    /* /webdash on */
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!WebServer.isRunning()) {
            sender.sendMessage(MessagesConfig.get("not-running"));
            return;
        }

        String openSuffix = sender instanceof Player ? MessagesConfig.get("player-open") : MessagesConfig.get("console-open");

        if (StatusHandler.enable()) {
            String link = WebServer.getLink();
            String message = MessagesConfig.get("on-success") + String.format(openSuffix, link);
            ChatUtil.sendClickableMessage(sender, message, link);
        } else {
            String link = WebServer.getLink();
            String message = MessagesConfig.get("on-already-on") + String.format(openSuffix, link);
            ChatUtil.sendClickableMessage(sender, message, link);
        }
    }
}

package com.simondmc.webdash.command.subcommands;

import com.simondmc.webdash.command.WebDashSubcommand;
import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.server.WebServer;
import com.simondmc.webdash.util.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WebDashRestartSubcommand implements WebDashSubcommand {

    /* /webdash restart */
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (WebServer.isRunning()) {
            WebServer.stop();
        }
        boolean success = WebServer.start();
        if (success) {
            String link = WebServer.getLink();
            String baseLink = WebServer.getBaseLink();
            String message = String.format(MessagesConfig.get("restart-success"), baseLink);
            if (sender instanceof Player) {
                ChatUtil.sendClickableMessage(sender, message, link);
            } else {
                sender.sendMessage(message);
            }
        } else {
            sender.sendMessage(MessagesConfig.get("restart-fail"));
        }
    }
}

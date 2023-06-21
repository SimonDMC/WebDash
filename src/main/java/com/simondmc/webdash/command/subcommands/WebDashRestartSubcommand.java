package com.simondmc.webdash.command.subcommands;

import com.simondmc.webdash.command.WebDashSubcommand;
import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.server.WebServer;
import com.simondmc.webdash.util.ChatUtil;
import com.simondmc.webdash.websocket.WSSHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WebDashRestartSubcommand implements WebDashSubcommand {

    /* /webdash restart */
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (WebServer.isRunning()) {
            WebServer.stop();
        }
        if (WSSHandler.isRunning()) {
            WSSHandler.stop();
        }

        boolean webSuccess = WebServer.start();
        boolean wssSuccess = WSSHandler.start();
        
        if (webSuccess && wssSuccess) {
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

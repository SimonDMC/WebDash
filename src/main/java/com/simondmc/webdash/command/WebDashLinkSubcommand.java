package com.simondmc.webdash.command;

import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.dashboard.KeyHandler;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import com.simondmc.webdash.server.WebServer;
import com.simondmc.webdash.util.PlayerUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WebDashLinkSubcommand {

    /* /webdash link */
    public WebDashLinkSubcommand(CommandSender sender) {
        if (!WebServer.isRunning()) {
            sender.sendMessage(MessagesConfig.get("not-running"));
            return;
        }
        String link = WebServer.getLink();
        String baseLink = WebServer.getBaseLink();
        String message = MessagesConfig.get("link-success").formatted(sender instanceof Player ? baseLink : link);
        if (KeyHandler.isEnabled() && sender instanceof Player) {
            message += MessagesConfig.get("link-key-suffix");
        }
        PlayerUtil.sendClickableMessage(sender, message, link);
    }
}

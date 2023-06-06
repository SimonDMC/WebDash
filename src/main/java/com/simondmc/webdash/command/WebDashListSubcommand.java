package com.simondmc.webdash.command;

import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import org.bukkit.command.CommandSender;

import java.util.List;

public class WebDashListSubcommand {

    /* /webdash list */
    public WebDashListSubcommand(CommandSender sender) {
        List<Route> routes = RouteHandler.getRoutes();
        if (routes.size() == 0) {
            sender.sendMessage(MessagesConfig.get("list-empty"));
            return;
        }
        sender.sendMessage(MessagesConfig.get("list-header"));
        for (Route route : routes) {
            sender.sendMessage(String.format(MessagesConfig.get("list-item"), route.getName(), route.getCommand()));
        }
    }
}

package com.simondmc.webdash.command;

import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import org.bukkit.command.CommandSender;

public class WebDashRemoveSubcommand {

    /* /webdash remove <id> */
    public WebDashRemoveSubcommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(MessagesConfig.get("remove-help"));
            return;
        }
        String id = args[1];

        Route route = RouteHandler.getRoute(id);

        if (RouteHandler.removeRoute(id)) {
            sender.sendMessage(String.format(MessagesConfig.get("remove-success"), route.getName(), route.getCommand()));
        } else {
            sender.sendMessage(String.format(MessagesConfig.get("remove-fail"), id));
        }
    }
}

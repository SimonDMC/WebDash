package com.simondmc.webdash.command.subcommands;

import com.simondmc.webdash.command.WebDashSubcommand;
import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.data.NotificationHandler;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import org.bukkit.command.CommandSender;

public class WebDashRemoveSubcommand implements WebDashSubcommand {

    /* /webdash remove <id> */
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(MessagesConfig.get("remove-help"));
            return;
        }
        String id = args[1];

        Route route = RouteHandler.getRoute(id);

        if (RouteHandler.removeRoute(id)) {
            sender.sendMessage(String.format(MessagesConfig.get("remove-success"), route.getName(), route.getCommand()));

            // notify
            NotificationHandler.notifyRouteRemove(route, sender);
        } else {
            sender.sendMessage(String.format(MessagesConfig.get("remove-fail"), id));
        }
    }
}

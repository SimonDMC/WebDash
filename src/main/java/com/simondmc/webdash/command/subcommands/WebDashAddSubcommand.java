package com.simondmc.webdash.command.subcommands;

import com.simondmc.webdash.command.WebDashSubcommand;
import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.data.NotificationHandler;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import com.simondmc.webdash.util.StringUtil;
import org.bukkit.command.CommandSender;

public class WebDashAddSubcommand implements WebDashSubcommand {

    /* /webdash add <name> /<command> */
    @Override
    public void execute(CommandSender sender, String[] args) {
        String joinedArgs = StringUtil.joinStringArray(args, " ", 1);
        String name = joinedArgs.split(" /")[0];
        String command = StringUtil.getRestOfString(joinedArgs, " /");
        if (command.equals("")) {
            sender.sendMessage(MessagesConfig.get("add-help"));
            return;
        }
        sender.sendMessage(String.format(MessagesConfig.get("add-success"), name, command));
        Route route = new Route(name, command);
        RouteHandler.addRoute(route);

        // notify
        NotificationHandler.notifyRouteAdd(route, sender);
    }
}

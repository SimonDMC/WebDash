package com.simondmc.webdash.command;

import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import com.simondmc.webdash.util.StringUtil;
import org.bukkit.command.CommandSender;

public class WebDashAddSubcommand {

    /* /webdash add <name> /<command> */
    public WebDashAddSubcommand(CommandSender sender, String[] args) {
        String joinedArgs = StringUtil.joinStringArray(args, " ", 1);
        String name = joinedArgs.split(" /")[0];
        String command = StringUtil.getRestOfString(joinedArgs, " /");
        if (command.equals("")) {
            sender.sendMessage(MessagesConfig.get("add-help"));
            return;
        }
        sender.sendMessage(MessagesConfig.get("add-success").formatted(name, command));
        RouteHandler.addRoute(new Route(name, command));
    }
}

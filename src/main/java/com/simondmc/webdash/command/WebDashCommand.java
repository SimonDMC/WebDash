package com.simondmc.webdash.command;

import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.dashboard.KeyHandler;
import com.simondmc.webdash.dashboard.StatusHandler;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import com.simondmc.webdash.server.WebServer;
import com.simondmc.webdash.util.PlayerUtil;
import com.simondmc.webdash.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WebDashCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equals("webdash")) {
            if (args.length == 0) {
                // TODO: explanatory message
                return false;
            }

            String subcommand = args[0];

            /* /webdash add <name> /<command> */
            if (subcommand.equalsIgnoreCase("add")) {
                String joinedArgs = StringUtil.joinStringArray(args, " ", 1);
                String name = joinedArgs.split(" /")[0];
                String command = StringUtil.getRestOfString(joinedArgs, " /");
                if (command.equals("")) {
                    sender.sendMessage(MessagesConfig.get("add-help"));
                    return true;
                }
                sender.sendMessage(MessagesConfig.get("add-success").formatted(name, command));
                RouteHandler.addRoute(new Route(name, command));
                return true;
            }

            /* /webdash remove <id> */
            if (subcommand.equalsIgnoreCase("remove")) {
                if (args.length < 2) {
                    sender.sendMessage(MessagesConfig.get("remove-help"));
                    return true;
                }
                String id = args[1];

                Route route = RouteHandler.getRoute(id);

                if (RouteHandler.removeRoute(id)) {
                    sender.sendMessage(MessagesConfig.get("remove-success").formatted(route.getName()));
                } else {
                    sender.sendMessage(MessagesConfig.get("remove-fail").formatted(id));
                }
                return true;
            }

            /* /webdash list */
            if (subcommand.equalsIgnoreCase("list")) {
                List<Route> routes = RouteHandler.getRoutes();
                if (routes.size() == 0) {
                    sender.sendMessage(MessagesConfig.get("list-empty"));
                    return true;
                }
                sender.sendMessage(MessagesConfig.get("list-header"));
                for (Route route : routes) {
                    sender.sendMessage(MessagesConfig.get("list-item").formatted(route.getName(), route.getCommand()));
                }
                return true;
            }

            /* /webdash link */
            if (subcommand.equalsIgnoreCase("link")) {
                // check if server is running
                if (!WebServer.isRunning()) {
                    sender.sendMessage(MessagesConfig.get("not-running"));
                    return true;
                }
                String link = WebServer.getLink();
                String baseLink = WebServer.getBaseLink();
                String message = MessagesConfig.get("link-success").formatted(sender instanceof Player ? baseLink : link);
                if (KeyHandler.isEnabled() && sender instanceof Player) {
                    message += MessagesConfig.get("link-key-suffix");
                }
                PlayerUtil.sendClickableMessage(sender, message, link);
                return true;
            }

            /* /webdash restart */
            if (subcommand.equalsIgnoreCase("restart")) {
                if (WebServer.isRunning()) {
                    WebServer.stop();
                }
                boolean success = WebServer.start();
                if (success) {
                    String link = WebServer.getLink();
                    String baseLink = WebServer.getBaseLink();
                    String message = MessagesConfig.get("restart-success").formatted(baseLink);
                    if (sender instanceof Player) {
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    } else {
                        sender.sendMessage(message);
                    }
                } else {
                    sender.sendMessage(MessagesConfig.get("restart-fail"));
                }
                return true;
            }

            /* /webdash key <on/off/reset> */
            if (subcommand.equalsIgnoreCase("key")) {
                if (args.length < 2) {
                    sender.sendMessage(MessagesConfig.get("key-help"));
                    return true;
                }
                String subarg = args[1];
                if (subarg.equalsIgnoreCase("on")) {
                    if (KeyHandler.enableKey()) {
                        String link = WebServer.getLink();
                        String message = MessagesConfig.get("key-on");
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    } else {
                        String link = WebServer.getLink();
                        String message = MessagesConfig.get("key-already-on");
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    }
                    return true;
                }
                if (subarg.equalsIgnoreCase("off")) {
                    if (KeyHandler.disableKey()) {
                        String link = WebServer.getLink();
                        String message = MessagesConfig.get("key-off");
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    } else {
                        String link = WebServer.getLink();
                        String message = MessagesConfig.get("key-already-off");
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    }
                    return true;
                }
                if (subarg.equalsIgnoreCase("reset")) {
                    KeyHandler.generateKey();
                    String link = WebServer.getLink();
                    if (KeyHandler.isEnabled()) {
                        String message = MessagesConfig.get("key-reset");
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    } else {
                        String message = MessagesConfig.get("key-reset-off");
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    }
                    return true;
                }
                sender.sendMessage(MessagesConfig.get("key-help"));
                return true;
            }

            /* /webdash on */
            if (subcommand.equalsIgnoreCase("on") || subcommand.equalsIgnoreCase("enable")) {
                // check if server is running
                if (!WebServer.isRunning()) {
                    sender.sendMessage(MessagesConfig.get("not-running"));
                    return true;
                }
                if (StatusHandler.enable()) {
                    String link = WebServer.getLink();
                    String message = MessagesConfig.get("on-success");
                    PlayerUtil.sendClickableMessage(sender, message, link);
                } else {
                    String link = WebServer.getLink();
                    String message = MessagesConfig.get("on-already-on");
                    PlayerUtil.sendClickableMessage(sender, message, link);
                }
                return true;
            }

            /* /webdash off */
            if (subcommand.equalsIgnoreCase("off") || subcommand.equalsIgnoreCase("disable")) {
                // check if server is running
                if (!WebServer.isRunning()) {
                    sender.sendMessage(MessagesConfig.get("not-running"));
                    return true;
                }
                if (StatusHandler.disable()) {
                    sender.sendMessage(MessagesConfig.get("off-success"));
                } else {
                    sender.sendMessage(MessagesConfig.get("off-already-off"));
                }
                return true;
            }
            return false;
        }
        return false;
    }
}

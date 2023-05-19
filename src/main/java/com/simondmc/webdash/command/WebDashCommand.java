package com.simondmc.webdash.command;

import com.simondmc.webdash.key.KeyHandler;
import com.simondmc.webdash.server.Route;
import com.simondmc.webdash.server.RouteHandler;
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
        // take webdash and wd, case-insensitive, with optional `webdash:` prefix
        if (cmd.getName().toLowerCase().matches("^(webdash:)?(webdash|wd)$")) {
            if (args.length == 0) {
                // base help message
                sender.sendMessage("§cValid subcommands: add, remove, list, link, restart, key, on, off");
                return true;
            }

            String subcommand = args[0];

            /* /webdash add <name> /<command> */
            if (subcommand.equalsIgnoreCase("add")) {
                String joinedArgs = StringUtil.joinStringArray(args, " ", 1);
                String name = joinedArgs.split(" /")[0];
                String command = StringUtil.getRestOfString(joinedArgs, " /");
                if (command.equals("")) {
                    sender.sendMessage("§cUsage: /webdash add <name> /<command>");
                    return true;
                }
                sender.sendMessage("§aAdded button §e" + name + "§a with command §b/" + command + "§a.");
                RouteHandler.addRoute(new Route(name, command));
                return true;
            }

            /* /webdash remove <id> */
            if (subcommand.equalsIgnoreCase("remove")) {
                if (args.length < 2) {
                    sender.sendMessage("§cUsage: /webdash remove <id>");
                    return true;
                }
                String id = args[1];

                Route route = RouteHandler.getRoute(id);

                if (RouteHandler.removeRoute(id)) {
                    sender.sendMessage("§aRemoved button §e" + route.getName() + "§a.");
                } else {
                    sender.sendMessage("§cButton with id §e" + id + "§c does not exist.");
                }
                return true;
            }

            /* /webdash list */
            if (subcommand.equalsIgnoreCase("list")) {
                List<Route> routes = RouteHandler.getRoutes();
                if (routes.size() == 0) {
                    sender.sendMessage("§cNo buttons have been added yet, use §e/webdash add <name> /<command>§c to add one.");
                    return true;
                }
                sender.sendMessage("§aListing all buttons:");
                for (Route route : routes) {
                    sender.sendMessage("§e" + route.getName() + "§a: §b/" + route.getCommand());
                }
                return true;
            }

            /* /webdash link */
            if (subcommand.equalsIgnoreCase("link")) {
                // check if server is running
                if (!WebServer.isRunning()) {
                    sender.sendMessage("§cServer is not running. Run §e/webdash restart§c and check the console to see what went wrong.");
                    return true;
                }
                String link = WebServer.getLink();
                String baseLink = WebServer.getBaseLink();
                String message = "§aDashboard Link: §e" + (sender instanceof Player ? baseLink : link);
                if (sender instanceof Player) {
                    PlayerUtil.sendClickableMessage(sender, message, link);
                } else {
                    sender.sendMessage(message);
                }
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
                    String message = "§aServer restarted successfully at §e" + baseLink + "§a.";
                    if (sender instanceof Player) {
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    } else {
                        sender.sendMessage(message);
                    }
                } else {
                    sender.sendMessage("§cServer failed to start. Check the console for more information.");
                }
                return true;
            }

            /* /webdash key <on/off/reset> */
            if (subcommand.equalsIgnoreCase("key")) {
                if (args.length < 2) {
                    sender.sendMessage("§cUsage: /webdash key <on/off/reset>");
                    return true;
                }
                String subarg = args[1];
                if (subarg.equalsIgnoreCase("on")) {
                    if (KeyHandler.enableKey()) {
                        String link = WebServer.getLink();
                        String message = "§eDashboard key protection is now §a§lON§e. Open the dashboard by clicking §e§lhere§e.";
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    } else {
                        String link = WebServer.getLink();
                        String message = "§eDashboard key protection is already on. Open the dashboard by clicking §e§lhere§e.";
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    }
                    return true;
                }
                if (subarg.equalsIgnoreCase("off")) {
                    if (KeyHandler.disableKey()) {
                        String link = WebServer.getLink();
                        String message = "§eDashboard key protection is now §c§lOFF§e. Open the dashboard by clicking §e§lhere§e.";
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    } else {
                        String link = WebServer.getLink();
                        String message = "§eDashboard key protection is already off. Open the dashboard by clicking §e§lhere§e.";
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    }
                    return true;
                }
                if (subarg.equalsIgnoreCase("reset")) {
                    KeyHandler.generateKey();
                    String link = WebServer.getLink();
                    if (KeyHandler.isEnabled()) {
                        String message = "§eThe dashboard key has been reset. Open the dashboard by clicking §e§lhere§e.";
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    } else {
                        String message = "§eDashboard key protection is currently off. Nonetheless, the dashboard key has been reset. Open the dashboard by clicking §e§lhere§e.";
                        PlayerUtil.sendClickableMessage(sender, message, link);
                    }
                    return true;
                }
                sender.sendMessage("§cUsage: /webdash key <on/off/reset>");
                return true;
            }

            // base help message
            sender.sendMessage("§cValid subcommands: add, remove, list, link, restart, key, on, off");
            return true;
        }
        return false;
    }
}

package com.simondmc.webdash.command;

import com.simondmc.webdash.data.KeyHandler;
import com.simondmc.webdash.data.StatusHandler;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebDashTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        // base command
        if (args.length == 1) {
            List<String> list = Arrays.asList("add", "remove", "list", "link", "restart", "key", "on", "off", "help", "info", "notify");
            List<String> arguments = new ArrayList<>(list);
            // remove current on/off setting
            if (StatusHandler.isEnabled()) {
                arguments.remove("on");
            } else {
                arguments.remove("off");
            }
            for (String arg : list) {
                if (!arg.toLowerCase().startsWith(args[0].toLowerCase())) {
                    arguments.remove(arg);
                }
            }
            return arguments;
        }

        // remove subcommand
        if (args.length == 2 && new ArrayList<>(Arrays.asList("remove", "delete", "rm", "del")).contains(args[0].toLowerCase())) {
            List<String> list = RouteHandler.getRoutes().stream().map(Route::getId).toList();
            List<String> arguments = new ArrayList<>(list);
            for (String arg : list) {
                if (!arg.toLowerCase().startsWith(args[1].toLowerCase())) {
                    arguments.remove(arg);
                }
            }
            return arguments;
        }

        // key subcommand
        if (args.length == 2 && args[0].equalsIgnoreCase("key")) {
            List<String> list = Arrays.asList("on", "off", "reset");
            List<String> arguments = new ArrayList<>(list);
            // remove current on/off setting
            if (KeyHandler.isEnabled()) {
                arguments.remove("on");
            } else {
                arguments.remove("off");
            }
            for (String arg : list) {
                if (!arg.toLowerCase().startsWith(args[1].toLowerCase())) {
                    arguments.remove(arg);
                }
            }
            return arguments;
        }

        // autocomplete player names if in notify subcommand
        if (args.length == 2 && args[0].equalsIgnoreCase("notify")) {
            return null;
        }

        // make sure it doesn't autocomplete player names
        return new ArrayList<>();
    }
}

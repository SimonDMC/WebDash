package com.simondmc.webdash.command;

import com.simondmc.webdash.server.RouteHandler;
import com.simondmc.webdash.server.Route;
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
            List<String> list = Arrays.asList("add", "remove", "list", "link", "restart", "on", "off");
            List<String> arguments = new ArrayList<>(list);
            for (String arg : list) {
                if (!arg.toLowerCase().startsWith(args[0].toLowerCase())) {
                    arguments.remove(arg);
                }
            }
            return arguments;
        }

        // remove command
        if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
            List<String> list = RouteHandler.getRoutes().stream().map(Route::getId).toList();
            List<String> arguments = new ArrayList<>(list);
            for (String arg : list) {
                if (!arg.toLowerCase().startsWith(args[1].toLowerCase())) {
                    arguments.remove(arg);
                }
            }
            return arguments;
        }

        // make sure it doesn't autocomplete player names
        return new ArrayList<>();
    }
}

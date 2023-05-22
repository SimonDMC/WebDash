package com.simondmc.webdash.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WebDashCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equals("webdash")) {
            if (args.length == 0) {
                // TODO: explanatory message
                return false;
            }

            String subcommand = args[0];

            if (subcommand.equalsIgnoreCase("add")) {
                new WebDashAddSubcommand(sender, args);
                return true;
            }

            if (subcommand.equalsIgnoreCase("remove")) {
                new WebDashRemoveSubcommand(sender, args);
                return true;
            }

            if (subcommand.equalsIgnoreCase("list")) {
                new WebDashListSubcommand(sender);
                return true;
            }

            if (subcommand.equalsIgnoreCase("link")) {
                new WebDashLinkSubcommand(sender);
                return true;
            }

            if (subcommand.equalsIgnoreCase("restart")) {
                new WebDashRestartSubcommand(sender);
                return true;
            }

            if (subcommand.equalsIgnoreCase("key")) {
                new WebDashKeySubcommand(sender, args);
                return true;
            }

            if (subcommand.equalsIgnoreCase("on") || subcommand.equalsIgnoreCase("enable")) {
                new WebDashOnSubcommand(sender);
                return true;
            }

            if (subcommand.equalsIgnoreCase("off") || subcommand.equalsIgnoreCase("disable")) {
                new WebDashOffSubcommand(sender);
                return true;
            }
            return false;
        }
        return false;
    }
}

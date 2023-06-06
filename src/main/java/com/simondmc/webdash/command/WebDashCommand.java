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

            String subcommand = args[0].toLowerCase();

            if (subcommand.equals("add") || subcommand.equals("a")) {
                new WebDashAddSubcommand(sender, args);
                return true;
            }

            if (subcommand.equals("remove") || subcommand.equals("delete") || subcommand.equals("rm") || subcommand.equals("del")) {
                new WebDashRemoveSubcommand(sender, args);
                return true;
            }

            if (subcommand.equals("list") || subcommand.equals("ls")) {
                new WebDashListSubcommand(sender);
                return true;
            }

            if (subcommand.equals("link") || subcommand.equals("url")) {
                new WebDashLinkSubcommand(sender);
                return true;
            }

            if (subcommand.equals("restart") || subcommand.equals("reload")) {
                new WebDashRestartSubcommand(sender);
                return true;
            }

            if (subcommand.equals("key")) {
                new WebDashKeySubcommand(sender, args);
                return true;
            }

            if (subcommand.equals("on") || subcommand.equals("enable")) {
                new WebDashOnSubcommand(sender);
                return true;
            }

            if (subcommand.equals("off") || subcommand.equals("disable")) {
                new WebDashOffSubcommand(sender);
                return true;
            }
            return false;
        }
        return false;
    }
}

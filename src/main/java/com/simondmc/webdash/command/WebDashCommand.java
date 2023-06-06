package com.simondmc.webdash.command;

import com.simondmc.webdash.command.subcommands.*;
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
            WebDashSubcommand executor = null;

            if (subcommand.equals("add") || subcommand.equals("a")) {
                executor = new WebDashAddSubcommand();
            }

            if (subcommand.equals("remove") || subcommand.equals("delete") || subcommand.equals("rm") || subcommand.equals("del")) {
                executor = new WebDashRemoveSubcommand();
            }

            if (subcommand.equals("list") || subcommand.equals("ls")) {
                executor = new WebDashListSubcommand();
            }

            if (subcommand.equals("link") || subcommand.equals("url")) {
                executor = new WebDashLinkSubcommand();
            }

            if (subcommand.equals("restart") || subcommand.equals("reload")) {
                executor = new WebDashRestartSubcommand();
            }

            if (subcommand.equals("key")) {
                executor = new WebDashKeySubcommand();
            }

            if (subcommand.equals("on") || subcommand.equals("enable")) {
                executor = new WebDashOnSubcommand();
            }

            if (subcommand.equals("off") || subcommand.equals("disable")) {
                executor = new WebDashOffSubcommand();
            }

            if (executor == null) {
                return false;
            } else {
                executor.execute(sender, args);
                return true;
            }
        }
        return false;
    }
}

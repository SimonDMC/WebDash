package com.simondmc.webdash.command;

import com.simondmc.webdash.command.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WebDashCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equals("webdash")) {

            String subcommand;
            if (args.length >= 1) {
                subcommand = args[0].toLowerCase();
            } else {
                subcommand = "help";
            }

            WebDashSubcommand executor;
            switch (subcommand) {
                case "add", "a" -> executor = new WebDashAddSubcommand();
                case "remove", "delete", "rm", "del" -> executor = new WebDashRemoveSubcommand();
                case "list", "ls" -> executor = new WebDashListSubcommand();
                case "link", "url" -> executor = new WebDashLinkSubcommand();
                case "restart", "reload" -> executor = new WebDashRestartSubcommand();
                case "key" -> executor = new WebDashKeySubcommand();
                case "on", "enable" -> executor = new WebDashOnSubcommand();
                case "off", "disable" -> executor = new WebDashOffSubcommand();
                case "help" -> executor = new WebDashHelpSubcommand();
                case "info" -> executor = new WebDashInfoSubcommand();
                case "notify" -> executor = new WebDashNotifySubcommand();
                default -> {
                    return false;
                }
            }
            executor.execute(sender, args);
            return true;
        }
        return false;
    }
}

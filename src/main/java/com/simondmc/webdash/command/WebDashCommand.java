package com.simondmc.webdash.command;

import com.simondmc.webdash.command.subcommands.*;
import com.simondmc.webdash.config.VersionConfig;
import com.simondmc.webdash.util.PlayerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WebDashCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equals("webdash")) {
            if (args.length == 0) {
                sender.sendMessage("§6§l§m---------------------------------------------");
                PlayerUtil.sendCenteredMessage(sender, "§b§lWebDash §7v" + VersionConfig.getVersion());
                PlayerUtil.sendCenteredMessage(sender, "");
                PlayerUtil.sendCenteredMessage(sender, "§3An easy and convenient web dashboard for");
                PlayerUtil.sendCenteredMessage(sender, "§3managing your Minecraft server.");
                PlayerUtil.sendCenteredMessage(sender, "");
                PlayerUtil.sendCenteredMessage(sender, "§aTo start, run §e§l/webdash link", "/webdash link");
                PlayerUtil.sendCenteredMessage(sender, "");
                PlayerUtil.sendCenteredMessage(sender, "§7For more detailed info, run /webdash help", "/webdash help");
                sender.sendMessage("§6§l§m---------------------------------------------");
                return true;
            }

            String subcommand = args[0].toLowerCase();
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

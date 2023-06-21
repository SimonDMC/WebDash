package com.simondmc.webdash.command.subcommands;

import com.simondmc.webdash.command.WebDashSubcommand;
import com.simondmc.webdash.config.VersionConfig;
import com.simondmc.webdash.util.ChatUtil;
import org.bukkit.command.CommandSender;

public class WebDashInfoSubcommand implements WebDashSubcommand {

    /* /webdash info */
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("§6§l§m---------------------------------------------");
        ChatUtil.sendCenteredMessage(sender, "§b§lWebDash §7v" + VersionConfig.getVersion());
        ChatUtil.sendCenteredMessage(sender, "");
        ChatUtil.sendCenteredMessage(sender, "§3An easy and convenient web dashboard for");
        ChatUtil.sendCenteredMessage(sender, "§3managing your Minecraft server.");
        ChatUtil.sendCenteredMessage(sender, "");
        ChatUtil.sendCenteredMessage(sender, "§aTo start, run §e§l/webdash link", "/webdash link");
        ChatUtil.sendCenteredMessage(sender, "");
        ChatUtil.sendCenteredMessage(sender, "§7For more detailed info, run /webdash help", "/webdash help");
        sender.sendMessage("§6§l§m---------------------------------------------");
    }
}

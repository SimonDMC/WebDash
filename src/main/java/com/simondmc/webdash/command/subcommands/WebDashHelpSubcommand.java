package com.simondmc.webdash.command.subcommands;

import com.simondmc.webdash.command.WebDashSubcommand;
import com.simondmc.webdash.dashboard.KeyHandler;
import com.simondmc.webdash.dashboard.StatusHandler;
import com.simondmc.webdash.util.ChatUtil;
import org.bukkit.command.CommandSender;

public class WebDashHelpSubcommand implements WebDashSubcommand {

    /* /webdash help */
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("§bWebDash Subcommands:");
        ChatUtil.sendCommandSuggestingMessage(sender, "§a/webdash link§r: Opens the WebDash dashboard", "/webdash link");
        ChatUtil.sendCommandSuggestingMessage(sender, "§a/webdash on/off§r: Enables/disables WebDash", StatusHandler.isEnabled() ? "/webdash off" : "/webdash on");
        ChatUtil.sendCommandSuggestingMessage(sender, "§a/webdash key on/off§r: Enables/disables dashboard key protection §7(to prevent unauthorized access)", KeyHandler.isEnabled() ? "/webdash key off" : "/webdash key on");
        ChatUtil.sendCommandSuggestingMessage(sender, "§a/webdash key reset§r: Regenerates the dashboard key", "/webdash key reset");
        ChatUtil.sendCommandSuggestingMessage(sender, "§a/webdash restart§r: Restarts the WebDash servers", "/webdash restart");
        ChatUtil.sendCommandSuggestingMessage(sender, "§a/webdash add <key> /<command>§r: Adds a WebDash button", "/webdash add");
        ChatUtil.sendCommandSuggestingMessage(sender, "§a/webdash remove <id>§r: Removes a WebDash button by its ID §7(if you're unsure of the button ID, remove it through the dashboard)", "/webdash remove");
        ChatUtil.sendCommandSuggestingMessage(sender, "§a/webdash list§r: Lists all WebDash buttons", "/webdash list");
    }
}

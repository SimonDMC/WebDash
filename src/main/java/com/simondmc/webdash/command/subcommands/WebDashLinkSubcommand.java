package com.simondmc.webdash.command.subcommands;

import com.simondmc.webdash.command.WebDashSubcommand;
import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.dashboard.KeyHandler;
import com.simondmc.webdash.server.WebServer;
import com.simondmc.webdash.util.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WebDashLinkSubcommand implements WebDashSubcommand {

    /* /webdash link */
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!WebServer.isRunning()) {
            sender.sendMessage(MessagesConfig.get("not-running"));
            return;
        }
        String link = WebServer.getLink();
        String baseLink = WebServer.getBaseLink();
        String message = String.format(MessagesConfig.get("link-success"), sender instanceof Player ? baseLink : link);
        if (KeyHandler.isEnabled() && sender instanceof Player) {
            message += MessagesConfig.get("link-key-suffix");
        }
        ChatUtil.sendClickableMessage(sender, message, link);
    }
}

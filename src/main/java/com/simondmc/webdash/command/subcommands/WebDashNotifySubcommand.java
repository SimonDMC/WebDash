package com.simondmc.webdash.command.subcommands;

import com.simondmc.webdash.command.WebDashSubcommand;
import com.simondmc.webdash.config.MessagesConfig;
import com.simondmc.webdash.data.NotificationHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class WebDashNotifySubcommand implements WebDashSubcommand {

    /* /webdash notify [player] */
    @Override
    public void execute(CommandSender sender, String[] args) {
        CommandSender s;
        // if player is specified as an argument, use that player
        if (args.length > 1) {
            try {
                s = Bukkit.getPlayer(args[1]);
            } catch (Exception e) {
                s = null;
            }
            if (s == null) {
                sender.sendMessage(String.format(MessagesConfig.get("notify-fail-player-not-found"), args[1]));
                return;
            }
        } else {
            // if player is not specified as an argument, use the sender
            s = sender;
        }

        if (NotificationHandler.toggleNotification(s)) {
            sender.sendMessage(MessagesConfig.get("notify-success-on"));
        } else {
            sender.sendMessage(MessagesConfig.get("notify-success-off"));
        }
    }
}

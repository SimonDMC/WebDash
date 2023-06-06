package com.simondmc.webdash.command;

import org.bukkit.command.CommandSender;

public interface WebDashSubcommand {
    void execute(CommandSender sender, String[] args);
}

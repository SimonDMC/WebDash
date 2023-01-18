package com.simondmc.webdash.command;

import com.simondmc.webdash.server.SendRoute;
import com.simondmc.webdash.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BindCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("bind")) {
            if (args.length < 2) {
                sender.sendMessage("§cUsage: /bind <button> <command>");
                return true;
            }
            String key = args[0];
            String command = StringUtil.unformatCommand(args, 1);
            sender.sendMessage("§aBound button §e" + key + "§a to command §e/" + command + "§a.");
            SendRoute.addRoute(key, command);
            return true;
        }
        return false;
    }
}

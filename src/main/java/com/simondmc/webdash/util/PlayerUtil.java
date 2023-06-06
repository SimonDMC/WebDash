package com.simondmc.webdash.util;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

public class PlayerUtil {

    public static void sendClickableMessage(CommandSender sender, String message, String link) {
        TextComponent textComponent = new TextComponent(message);
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link));
        sender.spigot().sendMessage(textComponent);
    }

    public static void sendCenteredMessage(CommandSender sender, String message) {
        sender.sendMessage(getCenteredMessage(message));
    }

    public static void sendCenteredMessage(CommandSender sender, String message, String command) {
        TextComponent textComponent = new TextComponent(getCenteredMessage(message));
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        sender.spigot().sendMessage(textComponent);
    }

    private static String getCenteredMessage(String message) {
        /* https://spigotmc.org/threads/95872/ */
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§'){
                previousCode = true;
            } else if (previousCode) {
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = 154 - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        // messes up in newer version so compensate manually
        sb.append("   ");
        return sb + message;
    }
}

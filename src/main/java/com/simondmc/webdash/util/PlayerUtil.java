package com.simondmc.webdash.util;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class PlayerUtil {

    public static void sendClickableMessage(Player player, String message, String link) {
        TextComponent textComponent = new TextComponent(message);
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link));
        player.spigot().sendMessage(textComponent);
    }
}

package com.jgamesexe.jgamescore.utils;

import com.jgamesexe.jgamescore.JGamesCore;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class ActionBar {

    public static void sendActionBar(Player player, String actionMsg) {
        try {
            if (!JGamesCore.oldVersions) {

                Constructor<?> construtor = Reflection.getNMSClass("PacketPlayOutChat").getConstructor(Reflection.getNMSClass("IChatBaseComponent"), Reflection.getNMSClass("ChatMessageType"));
                Object packet = construtor.newInstance(Reflection.generateIChatBaseComponent(actionMsg), Reflection.getNMSClass("ChatMessageType").getField("GAME_INFO").get(null));
                Packets.sendPacket(player, packet);

            } else {

                Constructor<?> construtor = Reflection.getNMSClass("PacketPlayOutChat").getConstructor(Reflection.getNMSClass("IChatBaseComponent"), byte.class);
                Object packet = construtor.newInstance(Reflection.generateIChatBaseComponent(actionMsg), (byte) 2);
                Packets.sendPacket(player, packet);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

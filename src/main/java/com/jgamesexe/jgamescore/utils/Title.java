package com.jgamesexe.jgamescore.utils;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class Title {

    public static void updateSubtitle(Player player, String subtitle) {
        sendSubtitle(player, subtitle, -1, -1, -1);
    }

    public static void sendSubtitle(Player player, String subtitle, int fadeIn, int showTime, int fadeOut) {
        sendFullTitle(player, null, subtitle, fadeIn, showTime, fadeOut);
    }

    public static void sendTitle(Player player, String title, int fadeIn, int showTime, int fadeOut) {
        sendFullTitle(player, title, null, fadeIn, showTime, fadeOut);
    }

    public static void sendFullTitle(Player player, String title, String subtitle, int fadeIn, int showTime, int fadeOut) {
        try {

            if (fadeIn > 0 && showTime > 0 && fadeOut > 0) {
                Constructor<?> timesConstrutor = Reflection.getNMSClass("PacketPlayOutTitle").getConstructor(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Reflection.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
                Object packetTimes = timesConstrutor.newInstance(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null), null, fadeIn, showTime, fadeOut);
                Packets.sendPacket(player, packetTimes);
            }

            Constructor<?> titlesConstrutor = Reflection.getNMSClass("PacketPlayOutTitle").getConstructor(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Reflection.getNMSClass("IChatBaseComponent"));

            if (title != null) {
                Object packetTitle = titlesConstrutor.newInstance(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), Reflection.generateIChatBaseComponent(title));
                Packets.sendPacket(player, packetTitle);
            }

            if (subtitle != null) {
                Object packetSubtitle = titlesConstrutor.newInstance(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), Reflection.generateIChatBaseComponent(subtitle));
                Packets.sendPacket(player, packetSubtitle);
            }
//            Object chatBaseTitle = Reflection.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getDeclaredMethod("a", String.class).invoke(null,"{\"text\": \"" + title + "\"}");
//            Constructor<?> titleConstrutor = Reflection.getNMSClass("PacketPlayOutTitle").getConstructor(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Reflection.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
//            Object packetTitle = titleConstrutor.newInstance(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chatBaseTitle, fadeIn, showTime, fadeOut);
//
//
//            Object chatBaseSubtitle = Reflection.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getDeclaredMethod("a", String.class).invoke(null,"{\"text\": \"" + subtitle + "\"}");
//            Constructor<?> subtitleConstrutor = Reflection.getNMSClass("PacketPlayOutTitle").getConstructor(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Reflection.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
//            Object packetSubtitle = subtitleConstrutor.newInstance(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chatBaseSubtitle, fadeIn, showTime, fadeOut);
//
//            Packets.sendPacket(player, packetTitle);
//            Packets.sendPacket(player, packetSubtitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

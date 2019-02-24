package com.jgamesexe.jgamescore.utils;

import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Packets {

    public static void sendPacket(Player player, Object packet) {
        if (!player.isOnline()) {
            new Exception("Player is OFFLINE").printStackTrace();
            return;
        }
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", Reflection.getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendCustomListname(Player observer, Player target, String content) {
        try {

            Object iTarget = target.getClass().getMethod("getHandle").invoke(target);
            Field listName = iTarget.getClass().getDeclaredField("listName");
            listName.set(iTarget, Reflection.generateIChatBaseComponent(content));

            Object eTarget = Array.newInstance(iTarget.getClass(), 1);
            Array.set(eTarget, 0, iTarget);

            Constructor constructor = Reflection.getNMSClass("PacketPlayOutPlayerInfo").getConstructor(Reflection.getNMSClass("PacketPlayOutPlayerInfo$EnumPlayerInfoAction"), eTarget.getClass());
            Object packet = constructor.newInstance(Reflection.getNMSClass("PacketPlayOutPlayerInfo$EnumPlayerInfoAction").getField("UPDATE_DISPLAY_NAME").get(null), eTarget);
            Packets.sendPacket(observer, packet);

        } catch (Exception e) {
            e.printStackTrace();
        }

//      EntityPlayer eTarget = ((CraftPlayer) target).getHandle();
//      eTarget.listName = new ChatComponentText(content);


//      PacketPlayOutPlayerInfo pf = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_DISPLAY_NAME, eTarget);
//      ((CraftPlayer) observer).getHandle().playerConnection.sendPacket(pf);
    }

    public static void sendHeaderAndFooter(Player player, String header, String footer) {
        try {

            Object packet = Reflection.getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor().newInstance();
            Object headerX = Reflection.generateIChatBaseComponent(header);
            Object footerX = Reflection.generateIChatBaseComponent(footer);

            Field a = packet.getClass().getDeclaredField("a");
            a.setAccessible(true);
            a.set(packet, headerX);

            Field b = packet.getClass().getDeclaredField("b");
            b.setAccessible(true);
            b.set(packet, footerX);

            sendPacket(player, packet);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

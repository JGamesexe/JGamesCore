package com.jgamesexe.jgamescore.utils;

import com.jgamesexe.jgamescore.JGamesCore;

public class Reflection {

    public static Class<?> getNMSClass(String className) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + JGamesCore.version + "." + className);
    }

    public static Class<?> getOBCClass(String className) throws ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit." + JGamesCore.version + "." + className);
    }

    public static Object generateIChatBaseComponent(String text) {
        try {
            return Reflection.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getDeclaredMethod("a", String.class).invoke(null, "{\"text\": \"" + text + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

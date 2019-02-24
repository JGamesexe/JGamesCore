package com.jgamesexe.jgamescore.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;

public class CommandRegister {

    private static CommandMap commandMap = getCommandMap();

    private static CommandMap getCommandMap() {
        try {
            Field f = Reflection.getOBCClass("CraftServer").getDeclaredField("commandMap");
            f.setAccessible(true);
            return (CommandMap) f.get(Bukkit.getServer());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void register(String cmd, Plugin plugin, Command command) {
        commandMap.register(cmd, plugin.getName(), command);
    }

}

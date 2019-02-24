package com.jgamesexe.jgamescore;

import com.jgamesexe.jgamescore.events.GUIHandlers;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class JGamesCore extends JavaPlugin {

    public static Plugin core;
    public static String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    public static boolean oldVersions = false;
    private static ConsoleCommandSender console = Bukkit.getConsoleSender();
    int x = 8000;

    @Override
    public void onEnable() {
        core = this;

        log("Running at version " + version);

        if (version.contains("1_8")) oldVersions = true;

        if (oldVersions) log("Plugin has set on oldVersion");
        else log("Plugin is on latestVersion");

        Bukkit.getPluginManager().registerEvents(new GUIHandlers(), this);
//      Bukkit.getPluginManager().registerEvents(new TestEvents(), this);

//      CommandRegister.register("teste", this, new TestEvents.ComandoTeste());

    }

    @Override
    public void onDisable() {
        log("Disable Completed");
    }

    public static void log(String log) {
        console.sendMessage("§7[§6JGames§3Core§7] §7[§8LOG§7] §f" + log);
    }

}

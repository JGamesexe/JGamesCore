package com.jgamesexe.jgamescore.events;

import com.jgamesexe.jgamescore.JGamesCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class TestEvents implements Listener {

    @EventHandler
    public void sneak(PlayerToggleSneakEvent event) {

    }

    public static class ComandoTeste extends Command {

        public ComandoTeste() {
            super("teste");
        }

        @Override
        public boolean execute(CommandSender commandSender, String s, String[] strings) {
            JGamesCore.log("BATATA");
            return false;
        }
    }

}

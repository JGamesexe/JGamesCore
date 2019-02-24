package com.jgamesexe.jgamescore.utils;

import com.jgamesexe.jgamescore.JGamesCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

public abstract class GUI {

    public static HashMap<UUID, GUI> openGUI = new HashMap<>();

    public Inventory inventory;
    protected boolean cancelInteract = true;
    protected boolean ignoreOutsideClick = true;

    public GUI(String display, int size) {
        this(Bukkit.createInventory(null, size, display));
    }

    public GUI(Inventory inventory) {
        this.inventory = inventory;
    }

    public void open(Player player) {
        player.closeInventory();
        if (openGUI.containsKey(player.getUniqueId())) {
            Bukkit.getScheduler().runTaskLater(JGamesCore.core, () -> {
                if (openGUI.containsKey(player.getUniqueId())) new Exception("Some GUI alredy OPEN").printStackTrace();
                else this.open(player);
            }, 2);
            return;
        }
        openInventory(player);
        player.openInventory(inventory);
        openGUI.put(player.getUniqueId(), this);
    }

    public void openInventory(Player player) {
    }

    public void close(InventoryCloseEvent event) {
        closeInventory(event);
        openGUI.remove(event.getPlayer().getUniqueId());
    }

    public void closeInventory(InventoryCloseEvent event) {
    }

    public void interact(InventoryClickEvent event, boolean insideGUI) {
    }

    public boolean isCancelInteract() {
        return cancelInteract;
    }

    public boolean ignoreOutsideClick() {
        return ignoreOutsideClick;
    }

    protected void glassFill() {
        glassFill(true);
    }

    protected void glassFill(boolean force) {
        for (int x = 0; x < this.inventory.getSize(); x++) {
            if (!force && inventory.getItem(x) != null && inventory.getItem(x).getType() != Material.AIR) continue;
            inventory.setItem(x, new Item(Material.STAINED_GLASS_PANE, (short) 8, "§0Vazio " + Math.random()).build());
        }
    }

}

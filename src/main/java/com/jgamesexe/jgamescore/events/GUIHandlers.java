package com.jgamesexe.jgamescore.events;

import com.jgamesexe.jgamescore.utils.GUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

public class GUIHandlers implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void inventoryClick(InventoryClickEvent event) {
        if (!GUI.openGUI.containsKey(event.getWhoClicked().getUniqueId())) return;
        GUI gui = GUI.openGUI.get(event.getWhoClicked().getUniqueId());
        if (gui.isCancelInteract()) event.setCancelled(true);
        if (event.getSlotType() == InventoryType.SlotType.OUTSIDE || event.getRawSlot() >= event.getInventory().getSize()) {
            if (!gui.ignoreOutsideClick()) gui.interact(event, false);
        } else {
            gui.interact(event, true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void inventoryClose(InventoryCloseEvent event) {
        if (GUI.openGUI.containsKey(event.getPlayer().getUniqueId())) GUI.openGUI.get(event.getPlayer().getUniqueId()).close(event);
    }

}

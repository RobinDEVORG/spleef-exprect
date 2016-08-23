package de.robindev.spleef.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author RobinDEV
 * 
 * Klasse, um das verschieben von Items im Inventar zu verhindern
 */
public class InventoryClickEventListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player) {
			Player player = (Player) event.getWhoClicked();
			if (!player.getGameMode().equals(GameMode.CREATIVE)) {
				// "InventoryClickEvent" cancellen
				event.setCancelled(true);
			}
		}
	}
}

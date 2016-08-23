package de.robindev.spleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * @author RobinDEV
 * 
 * Klasse, um das Item-Droppen zu verhindern
 */
public class PlayerDropItemEventListener implements Listener {
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		// "PlayerDropItemEvent" cancellen
		event.setCancelled(true);
	}
}

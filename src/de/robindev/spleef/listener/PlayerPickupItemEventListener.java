package de.robindev.spleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * @author RobinDEV
 * 
 * Klasse, um das aufheben von Items zu verhindern
 */
public class PlayerPickupItemEventListener implements Listener {
	
	@EventHandler
	public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
		// "PlayerPickupItemEvent" cancellen
		event.setCancelled(true);
	}
}

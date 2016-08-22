package de.robindev.spleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItemEventListener implements Listener {
	
	@EventHandler
	public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
		event.setCancelled(true);
	}
}

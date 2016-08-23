package de.robindev.spleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.robindev.spleef.manager.LocationManager;

/**
 * @author RobinDEV
 * 
 * Setzt die Respawn-Location auf den Spawn
 */
public class PlayerRespawnEventListener implements Listener {
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		// Respawn-Location auf die Map setzen
		event.setRespawnLocation(LocationManager.map);
	}
}

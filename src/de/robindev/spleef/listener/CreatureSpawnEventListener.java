package de.robindev.spleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * @author RobinDEV
 * 
 * Klasse, um Mobspawning zu verhindern
 */
public class CreatureSpawnEventListener implements Listener {
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		// "CreatureSpawnEvent" cancellen
		event.setCancelled(true);
	}
}

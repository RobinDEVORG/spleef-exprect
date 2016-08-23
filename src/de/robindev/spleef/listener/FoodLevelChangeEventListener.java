package de.robindev.spleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * @author RobinDEV
 * 
 * Klasse, um den Hunger auszuschalten
 */
public class FoodLevelChangeEventListener implements Listener {
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		// "FoodLevelChangeEvent" cancellen
		event.setCancelled(true);
	}
}

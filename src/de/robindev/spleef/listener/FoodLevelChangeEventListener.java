package de.robindev.spleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeEventListener implements Listener {
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}
}

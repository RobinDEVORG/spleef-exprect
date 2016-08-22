package de.robindev.spleef.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;

public class EntityDamageEventListener implements Listener {
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntityType().equals(EntityType.PLAYER)) {
			if (Spleef.state.equals(GameState.INGAME)) {
				event.setDamage(0.0D);
			} else {
				event.setCancelled(true);
			}
		}
	}
}

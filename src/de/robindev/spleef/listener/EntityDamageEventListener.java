package de.robindev.spleef.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;

/**
 * @author RobinDEV
 * 
 * Klasse, um den Schaden im "EntityDamageEvent" auf 0.0D zu stellen um nurnoch Knockback zu erhalten,
 * sollte das Entity das Schaden erleidet ein Spieler sein und das GameState GameState.INGAME sein
 */
public class EntityDamageEventListener implements Listener {
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		// Ist das Entity ein Spieler
		if (event.getEntityType().equals(EntityType.PLAYER)) {
			// Ist das Spiel Ingame?
			if (Spleef.state.equals(GameState.INGAME)) {
				// Falls ja, Schaden ausschalten um nur noch Rückstoß zu erhalten
				event.setDamage(0.0D);
			} else {
				// Falls nein, "EntityDamageEvent" cancellen
				event.setCancelled(true);
			}
		}
	}
}

package de.robindev.spleef.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;
import de.robindev.spleef.manager.LocationManager;

/**
 * @author RobinDEV
 * 
 * Klasse, um die Bewegungen des Spielers zu verwalten und ihn zu töten,
 * sollte er 5 Blöcke unter dem Spawn-Y sein
 */
public class PlayerMoveEventListener implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (Spleef.state.equals(GameState.INGAME)) {
			// Spieler auslesen
			Player player = event.getPlayer();

			// Null-Check für den Spiel
			if (player != null) {
				// Ist der Spieler noch nicht tot?
				if (Spleef.playerData.contains(player.getName())) {
					// Ist der Y-Wert vom Spieler + 5 kleiner als der Y-Wet vom
					// Spawn?
					if (player.getLocation().getY() + 5 < LocationManager.map.getY()) {
						// Falls ja, töten und respawnen
						player.setHealth(0.0D);
						player.spigot().respawn();
					}
				}
			}
		}
	}
}

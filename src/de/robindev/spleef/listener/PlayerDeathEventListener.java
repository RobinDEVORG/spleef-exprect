package de.robindev.spleef.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.robindev.spleef.Spleef;

/**
 * @author RobinDEV
 * 
 * Verwaltet die Tode von Spielern
 */
public class PlayerDeathEventListener implements Listener {
	
	@EventHandler
	public void onPlayerDeathEventListener(PlayerDeathEvent event) {
		// Spieler auslesen
		Player player = event.getEntity();
		
		// Drops löschen
		event.getDrops().clear();
		
		// Erfahrung auf dem Boden löschen
		event.setDroppedExp(0);
		
		// In der "playerData" tot auf "true" setzen
		Spleef.playerData.replace(player.getName(), true);
		
		// Deathmessage setzen
		event.setDeathMessage(Spleef.PREFIX + "§a" + player.getName() + " §bist gestorben.");
	}
}

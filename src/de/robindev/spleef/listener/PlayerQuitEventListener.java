package de.robindev.spleef.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.robindev.spleef.Spleef;

/**
 * @author RobinDEV
 * 
 * Klasse, um die Quitmessage zu setzen und den Spieler aus der "playerData" zu löschen
 */
public class PlayerQuitEventListener implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		// Spieler auslesen
		Player player = event.getPlayer();
		
		// Spiele NACH quit ausrechnen
		int size = Bukkit.getOnlinePlayers().size() - 1;
		
		// Spieler aus der "playerData" löschen
		Spleef.playerData.remove(player.getName());
		
		// Quitmessage setzen
		event.setQuitMessage(Spleef.PREFIX + "§a" + player.getName() + " §bhat das Spiel verlassen (§8" + size + "§7/§8" + Bukkit.getServer().getMaxPlayers() + "§b).");
	}

}

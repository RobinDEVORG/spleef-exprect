package de.robindev.spleef.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @author RobinDEV
 * 
 * Kleine Klasse, um den Chat zu stylen	
 */
public class AsyncPlayerChatEventListener implements Listener {
	
	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
		// Spieler, der das Event getriggert hat auslesen
		Player player = event.getPlayer();
		// Format setzen.
		event.setFormat("§7" + player.getName() + " §8>> §7" + event.getMessage());
	}
}

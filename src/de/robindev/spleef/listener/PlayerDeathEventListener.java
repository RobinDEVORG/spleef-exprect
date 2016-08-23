package de.robindev.spleef.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;
import de.robindev.spleef.manager.GameStateManager;

/**
 * @author RobinDEV
 * 
 * Verwaltet die Tode von Spielern
 */
public class PlayerDeathEventListener implements Listener {
	
	private Spleef main;
	
	public PlayerDeathEventListener(Spleef main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerDeathEventListener(PlayerDeathEvent event) {
		// Spieler auslesen
		Player player = event.getEntity();
		
		// Deathmessage setzen
		event.setDeathMessage(Spleef.PREFIX + "§a" + player.getName() + " §bist gestorben.");
		
		// Drops löschen
		event.getDrops().clear();
		
		// Erfahrung auf dem Boden löschen
		event.setDroppedExp(0);
		
		// Spieler aus den Ingame-Spielern löschen
		Spleef.playerData.remove(player.getName());
		
		if (Spleef.playerData.size() == 1) {
			GameStateManager.setup(main, GameState.FINISHED);
		}
	}
}

package de.robindev.spleef.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;
import de.robindev.spleef.manager.GameStateManager;

public class PlayerDeathEventListener implements Listener {
	
	private Spleef main;
	
	public PlayerDeathEventListener(Spleef main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerDeathEventListener(PlayerDeathEvent event) {
		Player player = event.getEntity();
		
		event.setDeathMessage(Spleef.PREFIX + "§a" + player.getName() + " §bist gestorben.");
		
		player.spigot().respawn();
		
		event.getDrops().clear();
		
		event.setDroppedExp(0);
		
		Spleef.playerData.remove(player.getName());
		
		if (Spleef.playerData.size() == 1) {
			GameStateManager.setup(main, GameState.FINISHED);
		}
	}
}

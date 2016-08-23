package de.robindev.spleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;

/**
 * @author RobinDEV
 * 
 * Klasse, um den Blockabbau zu verhindern, sollte das Spiel nicht "Ingame" sein
 */
public class BlockBreakEventListener implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		// Wenn das GameState nicht GameState.INGAME ODER der Spieler tot ist, "BlockBreakEvent" cancellen und returnen
		if (!Spleef.state.equals(GameState.INGAME) || Spleef.playerData.get(event.getPlayer().getName())) {
			event.setCancelled(true);
			return;
		}
		
	}
}

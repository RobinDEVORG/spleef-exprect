package de.robindev.spleef.listener;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @author RobinDEV
 * 
 * Klasse, um den Blockaufbau zu verhindern
 */
public class BlockPlaceEventListener implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			// "BlockPlaceEvent" cancellen
			event.setCancelled(true);
		}
	}
}

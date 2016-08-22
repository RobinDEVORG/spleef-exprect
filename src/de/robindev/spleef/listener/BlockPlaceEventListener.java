package de.robindev.spleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;

public class BlockPlaceEventListener implements Listener {
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (!Spleef.state.equals(GameState.INGAME)) {
			event.setCancelled(true);
		}
	}
}

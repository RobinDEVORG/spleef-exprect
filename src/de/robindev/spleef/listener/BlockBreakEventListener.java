package de.robindev.spleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;

public class BlockBreakEventListener implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (!Spleef.state.equals(GameState.INGAME)) {
			event.setCancelled(true);
			return;
		}
		
	}
}

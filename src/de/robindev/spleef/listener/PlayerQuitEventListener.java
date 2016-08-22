package de.robindev.spleef.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.robindev.spleef.Spleef;

public class PlayerQuitEventListener implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		event.setQuitMessage(Spleef.PREFIX + "§a" + player.getName() + " §bhat das Spiel verlassen (§8" + Bukkit.getOnlinePlayers().size() + "§7/§8" + Bukkit.getServer().getMaxPlayers() + "§b).");
	}

}

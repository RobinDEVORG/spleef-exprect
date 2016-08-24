package de.robindev.spleef.listener;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.robindev.spleef.Spleef;
import de.robindev.spleef.manager.LocationManager;
import de.robindev.spleef.util.PlayerUtil;

/**
 * @author RobinDEV
 * 
 * Setzt die Respawn-Location auf den Spawn
 */
public class PlayerRespawnEventListener implements Listener {
	
	private ItemStack players;
	
	public PlayerRespawnEventListener() {
		this.players = new ItemStack(Material.COMPASS);
		
		ItemMeta meta = players.getItemMeta();
		meta.setDisplayName("§bSpieler");
		meta.addItemFlags(ItemFlag.values());
		meta.setLore(Arrays.asList("§"));
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		
		Spleef.playerData.stream().forEach(name -> {
			Bukkit.getPlayer(name).hidePlayer(player);
		});
		
		PlayerUtil.readySpectator(player);
		
		event.setRespawnLocation(LocationManager.map);
	}
}

package de.robindev.spleef.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerInteractEventListener implements Listener {
	
	List<Player> players;
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (event.getItem().getItemMeta().getDisplayName().equals("§bSpieler")) {
			Inventory inv = Bukkit.createInventory(null, 18, "§bSpieler");
			
			players = new ArrayList<>(Bukkit.getOnlinePlayers());

			for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
				ItemStack skull = new ItemStack(Material.SKULL_ITEM);
				SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
				skullMeta.addItemFlags(ItemFlag.values());
				skullMeta.setDisplayName("§a" + players.get(i));
				skull.setItemMeta(skullMeta);
				
				inv.setItem(i, skull);
			}
			
			player.openInventory(inv);
		}
	}
}

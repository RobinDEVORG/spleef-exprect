package de.robindev.spleef.manager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;

public class GameStateManager {

	public static void setup(Spleef main, GameState state) {
		
		switch (state) {
		case WAITING:

			break;

		case INGAME:
			ItemStack shovel = new ItemStack(Material.DIAMOND_SPADE);
			
			ItemMeta shovelMeta = shovel.getItemMeta();
			shovelMeta.setDisplayName("§bSchaufel");
			shovelMeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
			shovelMeta.spigot().setUnbreakable(true);
			
			shovel.setItemMeta(shovelMeta);
			
			Bukkit.getOnlinePlayers().stream().forEach(p -> {
				p.getInventory().clear();
				p.teleport(LocationManager.getSpawn());
				p.getInventory().setItem(0, shovel);
			});
			
			Bukkit.broadcastMessage(Spleef.PREFIX + "§bDas Spiel beginnt! Schutzzeit für §a5 §bSekunden.");
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
				Bukkit.broadcastMessage(Spleef.PREFIX + "§bSchutzzeit zuende.");
				Spleef.state = GameState.INGAME;
			}, 20 * 5);
			
			break;

		case FINISHED:

			break;
		}
	}
}

package de.robindev.spleef.manager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;

/**
 * @author RobinDEV
 * 
 * Der "GameStateManager" bereitet das Spiel und die Spieler auf die verschiedenen GameStates vor
 */
public class GameStateManager {

	public static void setup(Spleef main, GameState state) {
		// Hier habe ich einen "switch-case-block" gewählt, der übersicht halber
		switch (state) {
		case WAITING:
			Spleef.state = GameState.WAITING;
			
			Spleef.winner = "";
			
			// Hier aus dem "switch-case-block" raus gehen
			break;

		case INGAME:
			// Die Schaufel erstellen
			ItemStack shovel = new ItemStack(Material.DIAMOND_SPADE);
			// Meta der Schaufel erstellen
			ItemMeta shovelMeta = shovel.getItemMeta();
			// Namen der Schaufel setzen
			shovelMeta.setDisplayName("§bSchaufel");
			// Effizienz III auf die Schaufel "klatschen" :D
			shovelMeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
			// Die Schaufel unzerstörbar machen
			shovelMeta.spigot().setUnbreakable(true);
			// Die Meta der Schaufel auf die "shovelMeta" setzen
			shovel.setItemMeta(shovelMeta);
			
			// Durch alle Spieler iterieren
			Bukkit.getOnlinePlayers().stream().forEach(p -> {
				// Inventar von allen leeren
				p.getInventory().clear();
				// Alle zur Map teleportieren
				p.teleport(LocationManager.map);
				// Die Schaufel ins Inventar legen
				p.getInventory().setItem(0, shovel);
			});
			
			// Nachricht verschicken, dass die Spiele beginnen und nun eine Fünf-Sekündige Schutzzeit beginnt
			Bukkit.broadcastMessage(Spleef.PREFIX + "§bDas Spiel beginnt! Schutzzeit für §a5 §bSekunden.");
			
			// Scheduler starten, der nach 5 Sekunden das Spiel in die "Ingame-Phase" setzt (So funktioniert die Schutzzeit)
			Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
				// Nachricht verschicken, dass die Schutzzeit jetzt zuende ist
				Bukkit.broadcastMessage(Spleef.PREFIX + "§bSchutzzeit zuende.");
				// Das Spiel in die "Ingame-Phase" stecken
				Spleef.state = GameState.INGAME;
			}, 20 * 5);
			
			// Hier aus dem "switch-case-block" raus gehen
			break;

		case FINISHED:
			
			// Nachricht broadcasten, dass das Spiel zu ende ist. Am Ende ist nur einer in der playerData noch drin, somit ergibt "get(0)" den Gewinner
			Bukkit.broadcastMessage(Spleef.PREFIX + "§a" + Spleef.playerData.get(0) + " §bhat das Spiel gewonnen");
			
			// Map resetten
			Spleef.destroyedBlocks.stream().forEach(block -> {
				block.getWorld().getBlockAt(block.getLocation()).setType(block.getType());
				System.out.println(Spleef.destroyedBlocks.size());
			});
			
			Spleef.destroyedBlocks.clear();
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
				Bukkit.getOnlinePlayers().stream().forEach(player -> {
					player.kickPlayer("§cDas Spiel ist vorbei. §a" + Spleef.winner + " §bhat gewonnen.");
				});
			}, 20 * 10);
			
			Spleef.playerData.clear();
			
			setup(main, GameState.WAITING);
			
			// Hier aus dem "switch-case-block" raus gehen
			break;
		}
	}
}

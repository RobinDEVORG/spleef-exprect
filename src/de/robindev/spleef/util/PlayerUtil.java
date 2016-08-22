package de.robindev.spleef.util;

import java.util.Arrays;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class PlayerUtil {
	
	public static void readyPlayer(Player player) {
		
		player.setHealth(20.0D);
		player.setMaxHealth(20.0D);
		player.setFoodLevel(20);
		player.setLevel(60);
		player.setExp(0);
		player.setFireTicks(0);
		player.setGameMode(GameMode.SURVIVAL);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		
		ItemStack howTo = new ItemStack(Material.WRITTEN_BOOK);
		
		BookMeta howToMeta = (BookMeta) howTo.getItemMeta();
		
		howToMeta.setAuthor("§aExprect");
		howToMeta.addPage("§7In §eSpleef §7geht es darum, seinem Gegner den Block unter seinen Füßen wegzubauen. "
				+ "Dafür erhälst du eine §eDiamantschaufel §7mit §eEffizient III§7. Der letzte Überlebende gewinnt.");
		howToMeta.setTitle("§bWas ist §eSpleef§e?");
		
		howToMeta.setLore(Arrays.asList("§bDieses Buch erklärt dir, was §eSpleef §beigentlich ist."));

		howTo.setItemMeta(howToMeta);
		
		player.getInventory().setItem(0, howTo);
	}
}

package de.robindev.spleef.util;

import java.util.Arrays;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

/**
 * @author RobinDEV
 * 
 * Stellt eine Methode bereit, um den übergegenen Spieler fertig für das Spiel zu machen
 */
public class PlayerUtil {
	
	/**
	 * Macht den übergebenen Spieler fertig für das Spiel
	 * 
	 * @param player Der Spieler, der fertig für das Spiel gemacht werden soll
	 */
	public static void readyPlayer(Player player) {
		// Leben auffüllen
		player.setHealth(20.0D);
		// Maximales Leben auf den Standardwert setzen
		player.setMaxHealth(20.0D);
		// Hunger aufüllen
		player.setFoodLevel(20);
		// Level für den Countdown auf 60 setzen
		player.setLevel(60);
		// Erfahrungspunkte zurücksetzen
		player.setExp(0);
		// Verbrennug beenden
		player.setFireTicks(0);
		// Spielmodus auf "Überleben" setzen
		player.setGameMode(GameMode.SURVIVAL);
		// Inventar leeren
		player.getInventory().clear();
		// Rüstung entfernen
		player.getInventory().setArmorContents(null);
		
		// Anleitungs-Buch erstellen
		ItemStack howTo = new ItemStack(Material.WRITTEN_BOOK);
		// Meta vom Buch holen und in eine "BookMeta" casten (Casten -> Typumwandlung)
		BookMeta howToMeta = (BookMeta) howTo.getItemMeta();
		
		// Autor auf den Netzwerknamen setzen
		howToMeta.setAuthor("§aExprect");
		// Die Erklärung hinzufügen
		howToMeta.addPage("§7In §eSpleef §7geht es darum, seinem Gegner den Block unter seinen Füßen wegzubauen. "
				+ "Dafür erhälst du eine §eDiamantschaufel §7mit §eEffizient III§7. Der letzte Überlebende gewinnt.");
		// Titel des Buches setzen
		howToMeta.setTitle("§bWas ist §eSpleef§e?");
		// Lore setzen mit Arrays#asList(String... args) (Geht natürlich auch mit einer normal "Stringlist")
		howToMeta.setLore(Arrays.asList("§bDieses Buch erklärt dir, was §eSpleef §beigentlich ist."));
		// ItemMeta wieder setzen
		howTo.setItemMeta(howToMeta);
		
		//Anleitung ins Inventar auf den ersten Slot legen (Der Computer fängt bei 0 an zu zählen) 
		player.getInventory().setItem(0, howTo);
	}
}

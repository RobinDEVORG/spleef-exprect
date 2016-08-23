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
 * Stellt eine Methode bereit, um den �bergegenen Spieler fertig f�r das Spiel zu machen
 */
public class PlayerUtil {
	
	/**
	 * Macht den �bergebenen Spieler fertig f�r das Spiel
	 * 
	 * @param player Der Spieler, der fertig f�r das Spiel gemacht werden soll
	 */
	public static void readyPlayer(Player player) {
		// Leben auff�llen
		player.setHealth(20.0D);
		// Maximales Leben auf den Standardwert setzen
		player.setMaxHealth(20.0D);
		// Hunger auf�llen
		player.setFoodLevel(20);
		// Level f�r den Countdown auf 60 setzen
		player.setLevel(60);
		// Erfahrungspunkte zur�cksetzen
		player.setExp(0);
		// Verbrennug beenden
		player.setFireTicks(0);
		// Spielmodus auf "�berleben" setzen
		player.setGameMode(GameMode.SURVIVAL);
		// Inventar leeren
		player.getInventory().clear();
		// R�stung entfernen
		player.getInventory().setArmorContents(null);
		
		// Anleitungs-Buch erstellen
		ItemStack howTo = new ItemStack(Material.WRITTEN_BOOK);
		// Meta vom Buch holen und in eine "BookMeta" casten (Casten -> Typumwandlung)
		BookMeta howToMeta = (BookMeta) howTo.getItemMeta();
		
		// Autor auf den Netzwerknamen setzen
		howToMeta.setAuthor("�aExprect");
		// Die Erkl�rung hinzuf�gen
		howToMeta.addPage("�7In �eSpleef �7geht es darum, seinem Gegner den Block unter seinen F��en wegzubauen. "
				+ "Daf�r erh�lst du eine �eDiamantschaufel �7mit �eEffizient III�7. Der letzte �berlebende gewinnt.");
		// Titel des Buches setzen
		howToMeta.setTitle("�bWas ist �eSpleef�e?");
		// Lore setzen mit Arrays#asList(String... args) (Geht nat�rlich auch mit einer normal "Stringlist")
		howToMeta.setLore(Arrays.asList("�bDieses Buch erkl�rt dir, was �eSpleef �beigentlich ist."));
		// ItemMeta wieder setzen
		howTo.setItemMeta(howToMeta);
		
		//Anleitung ins Inventar auf den ersten Slot legen (Der Computer f�ngt bei 0 an zu z�hlen) 
		player.getInventory().setItem(0, howTo);
	}
}

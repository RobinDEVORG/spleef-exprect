package de.robindev.spleef.util;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import de.robindev.spleef.Spleef;
import de.robindev.spleef.listener.PlayerJoinEventListener;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

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
		howToMeta.addPage("§7In §3Spleef §7geht es darum, seinem Gegner den Block unter seinen Füßen wegzubauen. "
				+ "Dafür erhälst du eine §3Diamantschaufel §7verzaubert mit §3Effizient III§7. Der letzte Überlebende gewinnt.");
		howToMeta.setTitle("§bWas ist §eSpleef§b?");
		howToMeta.setLore(Arrays.asList("§bDieses Buch erklärt dir, was §eSpleef §beigentlich ist."));
		howTo.setItemMeta(howToMeta);
		player.getInventory().setItem(0, howTo);
	}
	
	public static void readySpectator(Player player) {
		PacketPlayOutChat packetChat = new PacketPlayOutChat(PlayerJoinEventListener.getIChatBaseComponent("§aDu §7bist Spectator"), (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetChat);
		
		Spleef.playerData.stream().forEach(name -> {
			Bukkit.getPlayer(name).hidePlayer(player);
		});
		
		player.setAllowFlight(true);
		player.setFlying(true);
		
		player.getInventory().clear();
		
		ItemStack players = new ItemStack(Material.COMPASS);
		ItemMeta playersMeta = players.getItemMeta();
		playersMeta.setDisplayName("§bSpieler");
		playersMeta.addItemFlags(ItemFlag.values());
		players.setItemMeta(playersMeta);
		
		player.getInventory().setItem(0, players);
	}
}

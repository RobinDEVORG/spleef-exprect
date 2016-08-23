package de.robindev.spleef.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;

/**
 * @author RobinDEV
 * 
 * Klasse, um den Blockabbau zu verhindern, sollte das Spiel nicht
 * "Ingame" sein oder der Spieler nicht im GameMode 1 sein
 */
public class BlockBreakEventListener implements Listener {

	private ItemStack snowBall;

	public BlockBreakEventListener() {
		this.snowBall = new ItemStack(Material.SNOW_BALL, 1);

		ItemMeta metaSnowBall = snowBall.getItemMeta();
		metaSnowBall.setDisplayName("§bSchneeball");
		metaSnowBall.addItemFlags(ItemFlag.values());
		snowBall.setItemMeta(metaSnowBall);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		if (player.getGameMode().equals(GameMode.CREATIVE)) {
			event.setCancelled(false);
			return;
		}
		
		// Wenn das GameState nicht GameState.INGAME ODER der Spieler raus
		// ist, "BlockBreakEvent" cancellen und returnen
		if (!Spleef.state.equals(GameState.INGAME) || !Spleef.playerData.contains(player.getName())) {
			event.setCancelled(true);
			return;
		}
		
		if (!block.getType().equals(Material.SNOW_BLOCK)) {
			event.setCancelled(true);
		} else if (block.getType().equals(Material.SNOW_BLOCK)) {
			Spleef.destroyedBlocks.add(block);
			player.getInventory().addItem(snowBall);
		}
	}
}

package de.robindev.spleef.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;
import de.robindev.spleef.manager.GameStateManager;
import de.robindev.spleef.manager.LocationManager;
import de.robindev.spleef.util.PlayerUtil;

public class PlayerJoinEventListener implements Listener {

	private Spleef main;

	public PlayerJoinEventListener(Spleef main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		player.teleport(LocationManager.getSpawn());
		
		PlayerUtil.readyPlayer(player);
		
		if (Spleef.state.equals(GameState.WAITING)) {
			event.setJoinMessage(Spleef.PREFIX + "§a" + player.getName() + " §bhat das Spiel betreten (§8" + Bukkit.getOnlinePlayers().size() + "§7/§8" + Bukkit.getServer().getMaxPlayers() + "§b).");
		}

		if (!Spleef.state.equals(GameState.INGAME) || !Spleef.state.equals(GameState.FINISHED)) {
			if (Bukkit.getOnlinePlayers().size() == 2) {
				Spleef.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, () -> {

					if (Spleef.currentTick != 0) {

						if (Bukkit.getOnlinePlayers().size() < 2) {
							Bukkit.getScheduler().cancelTask(Spleef.taskID);
							Bukkit.broadcastMessage(Spleef.PREFIX + "§bStart abgebrochen. Mindestens §a2 §bSpieler erforderlich.");
							Spleef.currentTick = 60;
						}

						if (Spleef.currentTick == 50) {
							Bukkit.broadcastMessage(Spleef.PREFIX + "§bDas Spiel beginnt in §a" + Spleef.currentTick + " §bSekunden");
						}

						if (Spleef.currentTick == 40) {
							Bukkit.broadcastMessage(Spleef.PREFIX + "§bDas Spiel beginnt in §a" + Spleef.currentTick + " §bSekunden");
						}

						if (Spleef.currentTick == 30) {
							Bukkit.broadcastMessage(Spleef.PREFIX + "§bDas Spiel beginnt in §a" + Spleef.currentTick + " §bSekunden");
						}

						if (Spleef.currentTick == 20) {
							Bukkit.broadcastMessage(Spleef.PREFIX + "§bDas Spiel beginnt in §a" + Spleef.currentTick + " §bSekunden");
						}

						if (Spleef.currentTick <= 10) {
							Bukkit.broadcastMessage(Spleef.PREFIX + "§bDas Spiel beginnt in §a" + Spleef.currentTick + " §bSekunden");
						}

						Bukkit.getOnlinePlayers().stream().forEach(p -> {
							p.setLevel(Spleef.currentTick);
						});

						Spleef.currentTick--;
					} else if (Spleef.currentTick == 0) {
						Bukkit.getScheduler().cancelTask(Spleef.taskID);
						Spleef.currentTick = 60;
						
						player.setLevel(0);

						if (Bukkit.getOnlinePlayers().size() < 2) {
							Bukkit.broadcastMessage(Spleef.PREFIX + "§bStart abgebrochen. Mindestens §a2 §bSpieler erforderlich.");
						} else {
							GameStateManager.setup(main, GameState.INGAME);
						}
					}
				}, 20, 20);
			}
		}
	}
}

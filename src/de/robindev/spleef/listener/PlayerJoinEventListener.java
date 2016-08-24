package de.robindev.spleef.listener;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.robindev.spleef.GameState;
import de.robindev.spleef.Spleef;
import de.robindev.spleef.manager.GameStateManager;
import de.robindev.spleef.manager.LocationManager;
import de.robindev.spleef.manager.ScoreboardManager;
import de.robindev.spleef.util.PlayerUtil;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class PlayerJoinEventListener implements Listener {

	private Spleef main;

	public PlayerJoinEventListener(Spleef main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (Spleef.state.equals(GameState.WAITING)) {
			Spleef.playerData.add(player.getName());
		}

		ScoreboardManager.playersTeam.addEntry(player.getName());

		Bukkit.getOnlinePlayers().stream().forEach(all -> {
			if (!ScoreboardManager.playersTeam.hasEntry(all.getName())) {
				ScoreboardManager.playersTeam.addEntry(all.getName());
			}
			
			all.setScoreboard(ScoreboardManager.scoreboard);
		});

		if (Spleef.state.equals(GameState.WAITING)) {
			PlayerUtil.readyPlayer(player);
			player.teleport(LocationManager.lobby);
			PacketPlayOutChat packetChat = new PacketPlayOutChat(getIChatBaseComponent("§aDu §7bist der §e" + Bukkit.getOnlinePlayers().size() + ". §7von §e" + Bukkit.getServer().getMaxPlayers() + " §7Spielern"), (byte) 2);
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetChat);
			event.setJoinMessage(Spleef.PREFIX + "§a" + player.getName() + " §bhat das Spiel betreten (§8" + Bukkit.getOnlinePlayers().size() + "§7/§8" + Bukkit.getServer().getMaxPlayers() + "§b).");
		} else {
			event.setJoinMessage(null);
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

						if (Spleef.currentTick == 50 || Spleef.currentTick == 40 || Spleef.currentTick == 30 || Spleef.currentTick == 20 || Spleef.currentTick == 10 || Spleef.currentTick <= 5) {
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

	public static IChatBaseComponent getIChatBaseComponent(String msg) {
		return ChatSerializer.a("{\"text\": \"" + msg + "\"}");
	}
}

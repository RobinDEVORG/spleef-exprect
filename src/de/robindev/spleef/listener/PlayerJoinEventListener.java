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

/**
 * @author RobinDEV
 * 
 * Klasse, um dem beigetretenen Spieler die "Willkommens-ActionBAR" zu
 * senden, den Spieler in der "playerData" zu registrieren, dem
 * Scoreboard-Team "playersTeam" einen Eintrag hinzuzufügen, den Spieler
 * fertig für das Spiel zu machen, allen Spielern auf dem Server das
 * Scoreboard "scoreboard" der Klasse "ScoreboardManager" zu setzen, die
 * "Joinmessage" zu verändern und den Starcountdown zu starten und zu
 * verwalten
 * 
 * Besitzt außerdem noch eine Funktion, um einen "IChatBaseComponent"
 * aus einem String zu bekommen
 */
public class PlayerJoinEventListener implements Listener {

	/**
	 * Instanz der Hauptklasse
	 */
	private Spleef main;

	/**
	 * Einziger Konstruktor, erwartet eine Instanz der Hautplasse "Spleef"
	 * 
	 * @param main
	 *            Instanz der Hauptklasse
	 */
	public PlayerJoinEventListener(Spleef main) {
		// Instanz dieser Klasse auf die Übergebene im Konstruktor setzen
		this.main = main;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		// Beigetretenen Spieler auslesen
		Player player = event.getPlayer();

		// Wenn das Spiel wartet -> Spieler in der "playerData" registrieren
		if (Spleef.state.equals(GameState.WAITING)) {
			Spleef.playerData.add(player.getName());
		}

		// Spieler im Team "playersTeam" registrieren, um die Tabliste farbig zu
		// gestalten
		ScoreboardManager.playersTeam.addEntry(player.getName());

		// Allen Spielern auf dem Server das Scoreboard "scoreboard" der Klasse
		// "ScoreboardManager" setzen
		Bukkit.getOnlinePlayers().stream().forEach(all -> {
			if (!ScoreboardManager.playersTeam.hasEntry(all.getName())) {
				ScoreboardManager.playersTeam.addEntry(all.getName());
			}
			
			all.setScoreboard(ScoreboardManager.scoreboard);
		});

		// ActionBAR senden
		PacketPlayOutChat packetChat = new PacketPlayOutChat(getIChatBaseComponent("§aDu §7bist der §e" + Bukkit.getOnlinePlayers().size() + ". §7von §e" + Bukkit.getServer().getMaxPlayers() + " §7Spielern"), (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetChat);

		// Spieler zum Spawn teleportieren
		player.teleport(LocationManager.lobby);

		// Spieler fertig machen
		PlayerUtil.readyPlayer(player);

		// Ist das Spiel "am warten"?
		if (Spleef.state.equals(GameState.WAITING)) {
			// Falls ja, dann veränder die Joinmessage
			event.setJoinMessage(Spleef.PREFIX + "§a" + player.getName() + " §bhat das Spiel betreten (§8" + Bukkit.getOnlinePlayers().size() + "§7/§8" + Bukkit.getServer().getMaxPlayers() + "§b).");
		} else {
			// Falls nein, lösche sie komplett
			event.setJoinMessage(null);
		}

		// Ist das Spiel weder ingame, noch zuende?
		if (!Spleef.state.equals(GameState.INGAME) || !Spleef.state.equals(GameState.FINISHED)) {
			// Sind 2 Spieler online?
			if (Bukkit.getOnlinePlayers().size() == 2) {
				// Falls ja, setze den Integer "taskID" der Klasse Spleef auf
				// den Rückgabewert der Methode "scheduleSyncRepeatingTask"
				Spleef.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, () -> {

					// Ist die aktuelle Sekunde noch nicht 0?
					if (Spleef.currentTick != 0) {

						// Ist die Spieleranzahl kleiner als 2?
						if (Bukkit.getOnlinePlayers().size() < 2) {
							// Falls ja, breche ab und sende an alle Spieler ein
							// Nachricht, dass der Start abgebrochen wurde
							Bukkit.getScheduler().cancelTask(Spleef.taskID);
							Bukkit.broadcastMessage(Spleef.PREFIX + "§bStart abgebrochen. Mindestens §a2 §bSpieler erforderlich.");
							// Sekunden zurücksetzen
							Spleef.currentTick = 60;
						}

						// Sind die Sekunden auf 50, 40, 30, 20 oder kleiner als
						// 11?
						if (Spleef.currentTick == 50 || Spleef.currentTick == 40 || Spleef.currentTick == 30 || Spleef.currentTick == 20 || Spleef.currentTick == 10 || Spleef.currentTick <= 5) {
							// Falls ja, broadcaste eine Nachricht mit der
							// aktuellen Zeit
							Bukkit.broadcastMessage(Spleef.PREFIX + "§bDas Spiel beginnt in §a" + Spleef.currentTick + " §bSekunden");
						}

						// Setze das Level aller Spieler auf dem Server auf die
						// aktuelle Sekundenanzahl
						Bukkit.getOnlinePlayers().stream().forEach(p -> {
							p.setLevel(Spleef.currentTick);
						});

						// Inkrementiere "currentTick"
						Spleef.currentTick--;
					} else /* Oder ist die Aktuelle Sekunde doch 0? */ if (Spleef.currentTick == 0) {
						// Breche den Scheduler ab
						Bukkit.getScheduler().cancelTask(Spleef.taskID);
						// Sekunden zurücksetzen
						Spleef.currentTick = 60;

						// Level auf 0 setzen
						player.setLevel(0);

						// Ist die Spieleranzahl beim Start kleiner als 2?
						if (Bukkit.getOnlinePlayers().size() < 2) {
							// Falls ja, sende an alle Spieler ein Nachricht,
							// dass das Spiel doch nicht startet
							Bukkit.broadcastMessage(Spleef.PREFIX + "§bStart abgebrochen. Mindestens §a2 §bSpieler erforderlich.");
						} else {
							// Falls nein, setze das Spiel in die "Ingame-Phase"
							GameStateManager.setup(main, GameState.INGAME);
						}
					}
				}, 20, 20);
			}
		}
	}

	/**
	 * Gibt einen IChatBaseComponent zurück mit dem Text "msg"
	 * 
	 * @param msg
	 *            Den String, den du als "IChatBaseComponent" haben willst
	 * @return Den fertigen "IChatBaseComponent"
	 */
	private IChatBaseComponent getIChatBaseComponent(String msg) {
		return ChatSerializer.a("{\"text\": \"" + msg + "\"}");
	}
}

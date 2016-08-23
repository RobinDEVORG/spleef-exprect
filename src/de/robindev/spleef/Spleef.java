package de.robindev.spleef;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import de.robindev.spleef.commands.SetspawnCommand;
import de.robindev.spleef.listener.AsyncPlayerChatEventListener;
import de.robindev.spleef.listener.BlockBreakEventListener;
import de.robindev.spleef.listener.BlockPlaceEventListener;
import de.robindev.spleef.listener.CreatureSpawnEventListener;
import de.robindev.spleef.listener.EntityDamageEventListener;
import de.robindev.spleef.listener.FoodLevelChangeEventListener;
import de.robindev.spleef.listener.InventoryClickEventListener;
import de.robindev.spleef.listener.PlayerDropItemEventListener;
import de.robindev.spleef.listener.PlayerJoinEventListener;
import de.robindev.spleef.listener.PlayerPickupItemEventListener;
import de.robindev.spleef.listener.PlayerQuitEventListener;
import de.robindev.spleef.manager.LocationManager;
import de.robindev.spleef.manager.ScoreboardManager;

/**
 * @author RobinDEV
 * 
 * Haupt-Klasse des Plugins "Spleef"
 */
public class Spleef extends JavaPlugin {
	
	/**
	 * Dieser String steht vor allen Nachrichten als "Template". Das Design lässt sich natürlich ändern
	 */
	public static final String PREFIX = "§6Spleef §7>> §r";
	
	/**
	 * Diese Nachricht wird gesendet, wenn der Spieler keine Rechte für diese Funktion hat
	 */
	public static final String NO_PERM = PREFIX + "§cFür diese Funktion hast du keine Rechte.";
	
	/**
	 * Diese Map speichert einen String als Schlüssel und Boolean als Wert, um zu unterscheiden ob jemand Spectator ist oder nicht.
	 */
	public static final Map<String, Boolean> playerData = new HashMap<>();
	
	/**
	 * Diese Liste wird für den Map-Reset gebraucht.
	 */
	public static final List<Block> destroyedBlocks = new ArrayList<>();
	
	/**
	 * Für den Start countdown. Von "currentTick" wird jede Sekunde 1 abgezogen
	 */
	public static int currentTick;
	
	/**
	 * Der Rückgabewert des Startschedulers
	 */
	public static int taskID;
	
	/**
	 * In welcher Phase sich das Spiel befindet
	 */
	public static GameState state;
	
	@Override
	public void onEnable() {
		// Initialisiert den ScoreboardManager
		ScoreboardManager.init();
		
		// Am Anfang ist das Spiel natürlich in der "Warte-Phase"
		state = GameState.WAITING;
		
		// taskID setzen
		taskID = 0;
		// Die Wartezeit auf 60 Sekunden setzen
		currentTick = 60;

		// Standardwerte für den Spawn setzen
		LocationManager.DATA.addDefault("Spawn.World", Bukkit.getWorlds().get(0).getName());
		LocationManager.DATA.addDefault("Spawn.X", Bukkit.getWorlds().get(0).getSpawnLocation().getX());
		LocationManager.DATA.addDefault("Spawn.Y", Bukkit.getWorlds().get(0).getSpawnLocation().getY());
		LocationManager.DATA.addDefault("Spawn.Z", Bukkit.getWorlds().get(0).getSpawnLocation().getZ());
		LocationManager.DATA.addDefault("Spawn.Yaw", Bukkit.getWorlds().get(0).getSpawnLocation().getYaw());
		LocationManager.DATA.addDefault("Spawn.Pitch", Bukkit.getWorlds().get(0).getSpawnLocation().getPitch());
		
		// Standardwerte speichern
		try {
			LocationManager.DATA.save(LocationManager.FILE);
		} catch (IOException e) {
		}
		
		// Den Ausführer des Commands "setspawn" auf eine Instanz der Klasse "SetspawnCommand" setzen
		getCommand("setspawn").setExecutor(new SetspawnCommand());
		
		// Alle Listener registrieren
		getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerQuitEventListener(), this);
		getServer().getPluginManager().registerEvents(new FoodLevelChangeEventListener(), this);
		getServer().getPluginManager().registerEvents(new CreatureSpawnEventListener(), this);
		getServer().getPluginManager().registerEvents(new EntityDamageEventListener(), this);
		getServer().getPluginManager().registerEvents(new BlockBreakEventListener(), this);
		getServer().getPluginManager().registerEvents(new BlockPlaceEventListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerDropItemEventListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerPickupItemEventListener(), this);
		getServer().getPluginManager().registerEvents(new InventoryClickEventListener(), this);
		getServer().getPluginManager().registerEvents(new AsyncPlayerChatEventListener(), this);
		
		// Debug-Nachricht, um zu sehen ob alles glatt gegangen ist.
		System.out.println("Spleef >> Plugin geladen!");
	}
	
	@Override
	public void onDisable() {
		// Debug-Nachricht, um zu sehen ob alles glatt gegangen ist.
		System.out.println("Spleef >> Plugin beendet!");
	}
}

package de.robindev.spleef;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.robindev.spleef.commands.SetspawnCommand;
import de.robindev.spleef.listener.AsyncPlayerChatEventListener;
import de.robindev.spleef.listener.BlockBreakEventListener;
import de.robindev.spleef.listener.BlockPlaceEventListener;
import de.robindev.spleef.listener.CreatureSpawnEventListener;
import de.robindev.spleef.listener.EntityDamageEventListener;
import de.robindev.spleef.listener.FoodLevelChangeEventListener;
import de.robindev.spleef.listener.InventoryClickEventListener;
import de.robindev.spleef.listener.PlayerDeathEventListener;
import de.robindev.spleef.listener.PlayerDropItemEventListener;
import de.robindev.spleef.listener.PlayerJoinEventListener;
import de.robindev.spleef.listener.PlayerMoveEventListener;
import de.robindev.spleef.listener.PlayerPickupItemEventListener;
import de.robindev.spleef.listener.PlayerQuitEventListener;
import de.robindev.spleef.listener.PlayerRespawnEventListener;
import de.robindev.spleef.manager.GameStateManager;
import de.robindev.spleef.manager.LocationManager;
import de.robindev.spleef.manager.ScoreboardManager;

public class Spleef extends JavaPlugin {
	
	public static final String PREFIX = "§6Spleef §7>> §r";
	
	public static final String NO_PERM = PREFIX + "§cFür diese Funktion hast du keine Rechte.";
	
	public static final List<String> playerData = new ArrayList<>();
	
	public static int currentTick;
	
	public static int taskID;
	
	public static GameState state;
	
	public static String winner;
	
	@Override
	public void onEnable() {
		ScoreboardManager.init();
		
		GameStateManager.setup(this, GameState.WAITING);
		
		taskID = 0;
		currentTick = 60;
		
		LocationManager.DATA.addDefault("Lobby.World", Bukkit.getWorlds().get(0).getName());
		LocationManager.DATA.addDefault("Lobby.X", Bukkit.getWorlds().get(0).getSpawnLocation().getX());
		LocationManager.DATA.addDefault("Lobby.Y", Bukkit.getWorlds().get(0).getSpawnLocation().getY());
		LocationManager.DATA.addDefault("Lobby.Z", Bukkit.getWorlds().get(0).getSpawnLocation().getZ());
		LocationManager.DATA.addDefault("Lobby.Yaw", Bukkit.getWorlds().get(0).getSpawnLocation().getYaw());
		LocationManager.DATA.addDefault("Lobby.Pitch", Bukkit.getWorlds().get(0).getSpawnLocation().getPitch());
		
		LocationManager.DATA.addDefault("Map.World", Bukkit.getWorlds().get(0).getName());
		LocationManager.DATA.addDefault("Map.X", Bukkit.getWorlds().get(0).getSpawnLocation().getX());
		LocationManager.DATA.addDefault("Map.Y", Bukkit.getWorlds().get(0).getSpawnLocation().getY());
		LocationManager.DATA.addDefault("Map.Z", Bukkit.getWorlds().get(0).getSpawnLocation().getZ());
		LocationManager.DATA.addDefault("Map.Yaw", Bukkit.getWorlds().get(0).getSpawnLocation().getYaw());
		LocationManager.DATA.addDefault("Map.Pitch", Bukkit.getWorlds().get(0).getSpawnLocation().getPitch());
		
		try {
			LocationManager.DATA.save(LocationManager.FILE);
			LocationManager.updateLobby();
			LocationManager.updateMap();
		} catch (IOException e) {
		}
		
		getCommand("setspawn").setExecutor(new SetspawnCommand());
		
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
		getServer().getPluginManager().registerEvents(new PlayerMoveEventListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerDeathEventListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawnEventListener(), this);
		
		System.out.println("Spleef >> Plugin geladen!");
	}
	
	@Override
	public void onDisable() {
		System.out.println("Spleef >> Plugin beendet!");
	}
}

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

public class Spleef extends JavaPlugin {
	
	public static final String PREFIX = "§6Spleef §7>> §r";
	public static final String NO_PERM = PREFIX + "§cFür diese Funktion hast du keine Rechte.";
	
	public static final Map<String, Boolean> playerData = new HashMap<>();
	public static final List<Block> destroyedBlocks = new ArrayList<>();
	
	public static int currentTick;
	public static int taskID;
	
	public static GameState state;
	
	@Override
	public void onEnable() {
		ScoreboardManager.init();
		
		state = GameState.WAITING;
		
		taskID = 0;
		currentTick = 60;

		LocationManager.DATA.addDefault("Spawn.World", Bukkit.getWorlds().get(0).getName());
		LocationManager.DATA.addDefault("Spawn.X", Bukkit.getWorlds().get(0).getSpawnLocation().getX());
		LocationManager.DATA.addDefault("Spawn.Y", Bukkit.getWorlds().get(0).getSpawnLocation().getY());
		LocationManager.DATA.addDefault("Spawn.Z", Bukkit.getWorlds().get(0).getSpawnLocation().getZ());
		LocationManager.DATA.addDefault("Spawn.Yaw", Bukkit.getWorlds().get(0).getSpawnLocation().getYaw());
		LocationManager.DATA.addDefault("Spawn.Pitch", Bukkit.getWorlds().get(0).getSpawnLocation().getPitch());
		
		try {
			LocationManager.DATA.save(LocationManager.FILE);
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
		
		System.out.println("Spleef >> Plugin geladen!");
	}
	
	@Override
	public void onDisable() {
		System.out.println("Spleef >> Plugin beendet!");
	}
}

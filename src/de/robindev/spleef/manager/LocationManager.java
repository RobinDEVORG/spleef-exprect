package de.robindev.spleef.manager;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author RobinDEV
 * 
 * Der "LocationManager" verwaltet, wer hätte es gedacht, die Locations oder Positionen des Spiel, bspw. den Spawn
 */
public class LocationManager {
	
	/**
	 * Die Yaml-Datei, in der alles gespeichert wird
	 */
	public static final File FILE = new File("plugins" + File.separator + "Spleef" + File.separator + "data.yml");
	/**
	 * Die "FileConfiguration", mit der ich in die Datei schreibe
	 */
	public static final FileConfiguration DATA = YamlConfiguration.loadConfiguration(FILE);
	
	public static Location lobby;
	public static Location map;
	
	/**
	 * Updated die Location "lobby"
	 * 
	 * @return Die Position der Lobby
	 */
	public static Location updateLobby() {
		
		lobby =  new Location(Bukkit.getWorld(DATA.getString("Lobby.World")), DATA.getDouble("Lobby.X"), DATA.getDouble("Lobby.Y")
				, DATA.getDouble("Lobby.Z"), (float) DATA.getDouble("Lobby.Yaw")
				, (float) DATA.getDouble("Lobby.Pitch"));
		return lobby;
	}
	
	/**
	 * Updated die Location "map"
	 * 
	 * @return Die Position der Karte
	 */
	public static Location updateMap() {
		
		map =  new Location(Bukkit.getWorld(DATA.getString("Map.World")), DATA.getDouble("Map.X"), DATA.getDouble("Map.Y")
				, DATA.getDouble("Map.Z"), (float) DATA.getDouble("Map.Yaw")
				, (float) DATA.getDouble("Map.Pitch"));
		return map;
	}
	
	/**
	 * Schreibt die Daten der neuen Lobby-Location in die Yaml-Datei
	 * 
	 * @param location Die Postion an der die Lobby gesetzt werden soll
	 * @return Wenn die IOException auftritt, "false", andernfallls "true"
	 */
	public static boolean setLobby(Location location) {
		String world = location.getWorld().getName();
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		float yaw = location.getYaw();
		float pitch = location.getPitch();
		
		DATA.set("Lobby.World", world);
		DATA.set("Lobby.X", x);
		DATA.set("Lobby.Y", y);
		DATA.set("Lobby.Z", z);
		DATA.set("Lobby.Yaw", yaw);
		DATA.set("Lobby.Pitch", pitch);
		
		try {
			DATA.save(FILE);
			updateLobby();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * Schreibt die Daten der neuen Map-Location in die Yaml-Datei
	 * 
	 * @param location Die Postion an der die Map gesetzt werden soll
	 * @return Wenn die IOException auftritt, "false", andernfallls "true"
	 */
	public static boolean setMap(Location location) {
		String world = location.getWorld().getName();
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		float yaw = location.getYaw();
		float pitch = location.getPitch();
		
		DATA.set("Map.World", world);
		DATA.set("Map.X", x);
		DATA.set("Map.Y", y);
		DATA.set("Map.Z", z);
		DATA.set("Map.Yaw", yaw);
		DATA.set("Map.Pitch", pitch);
		
		try {
			DATA.save(FILE);
			updateMap();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}

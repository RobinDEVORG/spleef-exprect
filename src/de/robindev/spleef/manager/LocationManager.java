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
	
	/**
	 * Gibt die Position des Spawns zurück
	 * 
	 * @return Die Position des Spawns
	 */
	public static Location getSpawn() {
		
		return new Location(Bukkit.getWorld(DATA.getString("Spawn.World")), DATA.getDouble("Spawn.X"), DATA.getDouble("Spawn.Y")
				, DATA.getDouble("Spawn.Z"), (float) DATA.getDouble("Spawn.Yaw")
				, (float) DATA.getDouble("Spawn.Pitch"));
	}
	
	/**
	 * Schreibt die Daten der neuen Location in die Yaml-Datei
	 * 
	 * @param location Die Postion an der der Spawn gesetzt werden soll
	 * @return Wenn die IOException auftritt, "false", andernfallls "true"
	 */
	public static boolean setSpawn(Location location) {
		String world = location.getWorld().getName();
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		float yaw = location.getYaw();
		float pitch = location.getPitch();
		
		DATA.set("Spawn.World", world);
		DATA.set("Spawn.X", x);
		DATA.set("Spawn.Y", y);
		DATA.set("Spawn.Z", z);
		DATA.set("Spawn.Yaw", yaw);
		DATA.set("Spawn.Pitch", pitch);
		
		try {
			DATA.save(FILE);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}

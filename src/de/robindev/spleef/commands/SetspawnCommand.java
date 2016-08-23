package de.robindev.spleef.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import de.robindev.spleef.Spleef;
import de.robindev.spleef.manager.LocationManager;

/**
 * @author RobinDEV
 * 
 * Der Befehl, um den Spawn zu setzen
 */
public class SetspawnCommand extends AbstractPlayerCommand {
	
	@Override
	protected boolean execute(Player player, Command cmd, String label, String[] args) {
		
		// Hat der Spieler die "Permission" "spleef.command.setspawn"?
		if (player.hasPermission("spleef.command.setspawn")) {
			// Position des Spielers auslesen
			Location playerLocation = player.getLocation();
			
			// Je nach Rückgabewert der Methode "setSpawn", bekommt der Spieler unterschiedliche Nachrichten
			if (LocationManager.setSpawn(playerLocation)) {
				player.sendMessage(Spleef.PREFIX + "§bDer Spawn wurde erfolgreich an deiner Position gesetzt.");
			} else {
				player.sendMessage(Spleef.PREFIX + "§cWährend des setzens des Spawns an deiner Position ist ein Fehler aufgetreten. Siehe Server-Konsole");
			}
		}
		
		return true;
	}
}

package de.robindev.spleef.commands;

import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
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
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("map")) {
					// Je nach Rückgabewert der Methode "setMap", bekommt der
					// Spieler unterschiedliche Nachrichten
					if (LocationManager.setMap(player.getLocation())) {
						player.sendMessage(Spleef.PREFIX + "§bDer Spawn der Map wurde erfolgreich an deiner Position gesetzt.");
					} else {
						CraftPlayer cp = (CraftPlayer) player;
						cp.getHandle();
						player.sendMessage(Spleef.PREFIX + "§cWährend des setzens des Spawns der Map an deiner Position ist ein Fehler aufgetreten. Siehe Server-Konsole");
					}
				} else if (args[0].equalsIgnoreCase("lobby")) {
					// Je nach Rückgabewert der Methode "setLobby", bekommt der
					// Spieler unterschiedliche Nachrichten
					if (LocationManager.setLobby(player.getLocation())) {
						player.sendMessage(Spleef.PREFIX + "§bDer Spawn der Lobby wurde erfolgreich an deiner Position gesetzt.");
					} else {
						player.sendMessage(Spleef.PREFIX + "§cWährend des setzens des Spawns der Lobby an deiner Position ist ein Fehler aufgetreten. Siehe Server-Konsole");
					}
				}
			}
		}

		return true;
	}
}

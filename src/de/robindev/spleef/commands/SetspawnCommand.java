package de.robindev.spleef.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import de.robindev.spleef.Spleef;
import de.robindev.spleef.manager.LocationManager;

public class SetspawnCommand extends AbstractPlayerCommand {
	
	@Override
	protected boolean execute(Player player, Command cmd, String label, String[] args) {
		
		if (player.hasPermission("spleef.command.setspawn")) {
			Location playerLocation = player.getLocation();
			
			if (LocationManager.setSpawn(playerLocation)) {
				player.sendMessage(Spleef.PREFIX + "§bDer Spawn wurde erfolgreich an deiner Position gesetzt.");
			} else {
				player.sendMessage(Spleef.PREFIX + "§cWährend des setzens des Spawns an deiner Position ist ein Fehler aufgetreten. Siehe Server-Konsole");
			}
		}
		
		return true;
	}
}

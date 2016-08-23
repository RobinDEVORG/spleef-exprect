package de.robindev.spleef.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author RobinDEV
 * 
 * Eine abstrakte Klasse, um das Befehl-Erstellen zu vereinfachen
 */
public abstract class AbstractPlayerCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		return cs instanceof Player ? execute((Player) cs, cmd, label, args) : true;
	}
	
	/**
	 * Wird von "onCommand" aufgerufen, sollte der "ComandSender" ein Spieler sein
	 * 
	 * @param player Der Spieler, der den Befehl ausführt
	 * @param cmd Der Befehl
	 * @param label Der Name des Befehls
	 * @param args Die Argumente des Befehls
	 */
	protected abstract boolean execute(Player player, Command cmd, String label, String[] args);
}

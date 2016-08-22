package de.robindev.spleef.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractPlayerCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		return cs instanceof Player ? execute((Player) cs, cmd, label, args) : true;
	}

	protected abstract boolean execute(Player player, Command cmd, String label, String[] args);
}

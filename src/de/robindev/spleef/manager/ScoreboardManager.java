package de.robindev.spleef.manager;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardManager {
	
	public static Scoreboard scoreboard;
	
	public static Team playersTeam;
	
	public static void init() {
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		
		playersTeam = scoreboard.registerNewTeam("players");
		playersTeam.setPrefix("§7");
	}
}

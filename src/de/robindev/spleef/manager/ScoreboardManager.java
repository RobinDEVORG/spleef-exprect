package de.robindev.spleef.manager;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardManager {
	
	public static Scoreboard scoreboard;
	
	public static Objective infoObjective;
	private static Score info;
	
	public static Team playersTeam;
	
	public static void init() {
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		
		infoObjective = scoreboard.registerNewObjective("infoObjective", "info");
		infoObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		infoObjective.setDisplayName("§eSpleef");
		
		info = infoObjective.getScore("§bExprect.de");
		info.setScore(0);
		
		playersTeam = scoreboard.registerNewTeam("players");
		playersTeam.setPrefix("§7");
	}
}

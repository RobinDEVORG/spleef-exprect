package de.robindev.spleef.manager;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * @author RobinDEV
 * 
 * Der "ScoreboardManager" verwaltet die Scoreboards, Objectives, Scores und Teams
 */
public class ScoreboardManager {
	
	/**
	 * Das Hauptscoreboard
	 */
	public static Scoreboard scoreboard;
	
	/**
	 * Das Objective zur Information
	 */
	public static Objective infoObjective;
	/**
	 * Das Score, dass "Exprect.de" anzeigt
	 */
	private static Score info;
	
	/**
	 * Das Team mit allen Spielern, um die Tablist farbig zu haben
	 */
	public static Team playersTeam;
	
	/**
	 * Initialisiert alle Variablen
	 */
	public static void init() {
		// Neues Scoreboard erstellen
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		
		// Neuses Objective registrieren
		infoObjective = scoreboard.registerNewObjective("infoObjective", "info");
		// Position des Ojectives festlegen
		infoObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		// Spleef als Namen nehmen
		infoObjective.setDisplayName("§eSpleef");
		
		// Score holen
		info = infoObjective.getScore("§bExprect.de");
		// Den Score im Score setzen
		info.setScore(0);
		
		// Team der Spieler registrieren
		playersTeam = scoreboard.registerNewTeam("players");
		// Prefix des Spieler-Team auf Grau setzen
		playersTeam.setPrefix("§7");
	}
}

package de.robindev.spleef;

/**
 * @author RobinDEV
 * 
 * Die verschieden Phasen des Spiels
 */
public enum GameState {
	
	/**
	 * Die Warte-Phase
	 */
	WAITING(0),
	/**
	 * Die Imspiel-Phase
	 */
	INGAME(1),
	/**
	 * Die Fertig-Phase
	 */
	FINISHED(2);
	
	/**
	 * Der Level, brauch man zwar eig. nicht, habe mir aber angew�hnt, dass bei Enumerations so zu machen
	 */
	private int level;
	
	/**
	 * Der Konstruktor des Enums.
	 * 
	 * @param level Der Level
	 */
	private GameState(int level) {
		this.level = level;
	}
	
	/**
	 * Gibt den Level zur�ck
	 * 
	 * @return Den Level des GameState
	 */
	public int getLevel() {
		return level;
	}
}

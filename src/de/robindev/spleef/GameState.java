package de.robindev.spleef;

public enum GameState {
	
	WAITING(0),
	INGAME(1),
	FINISHED(2);
	
	private int level;

	private GameState(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
}

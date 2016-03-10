package eg.edu.guc.yugioh.cards;

import eg.edu.guc.yugioh.board.Board;

public abstract class Card implements Cloneable {
	private String name;
	private String description;
	private Boolean isHidden;
	private Location location;
	private static Board board;

	public Card(String name, String description) {
		this.name = name;
		this.description = description;
		this.isHidden = true;
		this.location = Location.DECK;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean unhide) {
		isHidden = unhide;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location locate) {
		location = locate;
	}

	public static Board getBoard() {
		return board;
	}

	 public static void setBoard(Board newBoard) {
		board = newBoard;
	}

	abstract public void action(MonsterCard monster);

}

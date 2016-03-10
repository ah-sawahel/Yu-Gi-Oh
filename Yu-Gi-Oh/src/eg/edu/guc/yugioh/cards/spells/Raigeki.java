package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class Raigeki extends SpellCard {
	public Raigeki(String name, String description) {
		super(name, description);
	}

	public SpellCard clone() {
		return new Raigeki(this.getName(), this.getDescription());
	}

	public void action(MonsterCard c) {
		getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(getBoard().getOpponentPlayer().getField().getMonstersArea());
//		for (int i = 0; i < temp.size(); i++) {
//			temp.get(i).setLocation(Location.GRAVEYARD);
//		}
	}

}

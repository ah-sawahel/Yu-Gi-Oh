package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class ChangeOfHeart extends SpellCard {
	public ChangeOfHeart(String name, String description) {
		super(name, description);
	}

	public SpellCard clone() {
		return new ChangeOfHeart(this.getName(), this.getDescription());
	}

	public void action(MonsterCard monster) {
		if (getBoard().getActivePlayer().getField().getMonstersArea().size() < 5) {
			if (getBoard().getOpponentPlayer().getField().getMonstersArea()
					.contains(monster)) {
				getBoard().getActivePlayer().getField().getMonstersArea()
						.add(monster);
				getBoard().getOpponentPlayer().getField().getMonstersArea()
						.remove(monster);
			}
		}
	}

}

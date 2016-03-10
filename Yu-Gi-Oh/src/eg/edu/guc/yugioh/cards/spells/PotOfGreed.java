package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;


public class PotOfGreed extends SpellCard {
	public PotOfGreed(String name, String description){
		super(name,description);
	}
	public SpellCard clone(){
		return new PotOfGreed(this.getName(),this.getDescription());
	}
	public void action(MonsterCard c){
		getBoard().getActivePlayer().addNCardsToHand(2);
	}

	
}

package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class HeavyStorm extends HarpieFeatherDuster {
	public HeavyStorm(String name, String description){
		super(name,description);
	}
	public SpellCard clone(){
		return new HeavyStorm(this.getName(),this.getDescription());
	}
	public void action(MonsterCard c){
		super.action(c);
	//	getBoard().getActivePlayer().getField().getGraveyard().addAll(getBoard().getActivePlayer().getField().getSpellArea());
		getBoard().getActivePlayer().getField().removeSpellToGraveyard(getBoard().getActivePlayer().getField().getSpellArea());
	}

}

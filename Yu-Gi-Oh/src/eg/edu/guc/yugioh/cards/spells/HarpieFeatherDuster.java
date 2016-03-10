package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class HarpieFeatherDuster extends SpellCard {
	public HarpieFeatherDuster(String name, String description){
		super(name,description);
	}
	public SpellCard clone(){
		return new HarpieFeatherDuster(this.getName(),this.getDescription());
	}
	public void action(MonsterCard c){
//		getBoard().getOpponentPlayer().getField().getGraveyard().addAll(getBoard().getOpponentPlayer().getField().getSpellArea());
		getBoard().getOpponentPlayer().getField().removeSpellToGraveyard(getBoard().getOpponentPlayer().getField().getSpellArea());
	}

}

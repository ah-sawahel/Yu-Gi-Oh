package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class CardDestruction extends SpellCard {
	
	public CardDestruction(String name, String description){
		super(name,description);
	}
	public SpellCard clone(){
		return new CardDestruction(this.getName(),this.getDescription());
	}
	public void action(MonsterCard c){
		int x1 = getBoard().getActivePlayer().getField().getHand().size();
		int x2 = getBoard().getOpponentPlayer().getField().getHand().size();
		for (int i = 0; i < x1; i++) {
			getBoard().getActivePlayer().getField().getHand().get(i).setLocation(Location.GRAVEYARD);
		}
		for (int i = 0; i < x2; i++) {
			getBoard().getOpponentPlayer().getField().getHand().get(i).setLocation(Location.GRAVEYARD);
		}
		getBoard().getActivePlayer().getField().getGraveyard().addAll(getBoard().getActivePlayer().getField().getHand());
		getBoard().getOpponentPlayer().getField().getGraveyard().addAll(getBoard().getOpponentPlayer().getField().getHand());
		
		getBoard().getActivePlayer().getField().getHand().clear();
		getBoard().getOpponentPlayer().getField().getHand().clear();
		
		getBoard().getActivePlayer().addNCardsToHand(x1);
		getBoard().getOpponentPlayer().addNCardsToHand(x2);
		}

}

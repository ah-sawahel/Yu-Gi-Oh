package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class DarkHole extends Raigeki {
	public DarkHole(String name, String description){
		super(name,description);
	}
	public SpellCard clone(){
		return new DarkHole(this.getName(),this.getDescription());
	}
	public void action(MonsterCard c){
		super.action(c);
//		getBoard().getActivePlayer().getField().getGraveyard().addAll(getBoard().getActivePlayer().getField().getMonstersArea());
//		int s =getBoard().getActivePlayer().getField().getMonstersArea().size();
//		for (int i = 0; i < s; i++) {
//			getBoard().getActivePlayer().getField().getMonstersArea().remove(0).setLocation(Location.GRAVEYARD);
//		}
		getBoard().getActivePlayer().getField().removeMonsterToGraveyard(getBoard().getActivePlayer().getField().getMonstersArea());
		getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(getBoard().getOpponentPlayer().getField().getMonstersArea());
	}

}

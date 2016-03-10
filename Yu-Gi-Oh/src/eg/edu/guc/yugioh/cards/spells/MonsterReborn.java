package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class MonsterReborn extends SpellCard {
	public MonsterReborn(String name, String description){
		super(name,description);
	}
	public SpellCard clone(){
		return new MonsterReborn(this.getName(),this.getDescription());
	}
	public void action(MonsterCard c){
		if(getBoard().getActivePlayer().getField().getMonstersArea().size() < 5){
			int max = 0;
			MonsterCard m = null;
			int s;
			boolean active = true;
			if(!getBoard().getActivePlayer().getField().getGraveyard().isEmpty()){
				s = getBoard().getActivePlayer().getField().getGraveyard().size();
				for (int i = 0; i < s; i++) {
					Card temp = getBoard().getActivePlayer().getField().getGraveyard().get(i);
					if (temp instanceof MonsterCard){
						if(((MonsterCard) temp).getAttackPoints() > max){
							active = true;
							m = (MonsterCard)temp;
							max = m.getAttackPoints();
						}	
					}
				}
			}
			if(!getBoard().getOpponentPlayer().getField().getGraveyard().isEmpty()){
				s = getBoard().getOpponentPlayer().getField().getGraveyard().size();
				for (int i = 0; i < s; i++) {
					Card temp = getBoard().getOpponentPlayer().getField().getGraveyard().get(i);
					if (temp instanceof MonsterCard){
						if(((MonsterCard) temp).getAttackPoints() > max){
							active = false;
							m = (MonsterCard)temp;
							max = m.getAttackPoints();
						}	
					}
				}
			}
			if(active){
				getBoard().getActivePlayer().getField().getGraveyard().remove(m);
				getBoard().getActivePlayer().getField().getMonstersArea().add(m);
				m.setMode(Mode.ATTACK);
				m.setLocation(Location.FIELD);
			}else{
				getBoard().getOpponentPlayer().getField().getGraveyard().remove(m);
				getBoard().getActivePlayer().getField().getMonstersArea().add(m);
				m.setMode(Mode.ATTACK);
				m.setLocation(Location.FIELD);
			}
			
		}

	}
}

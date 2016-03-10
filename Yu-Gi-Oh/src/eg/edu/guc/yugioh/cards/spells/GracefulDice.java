package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class GracefulDice extends SpellCard {
	public GracefulDice(String name, String description){
		super(name,description);
	}
	public SpellCard clone(){
		return new GracefulDice(this.getName(),this.getDescription());
	}
	public void action(MonsterCard c){
		int x =(int)(Math.floor(Math.random()*10)+1);
		int gain= x*100;
		x = getBoard().getActivePlayer().getField().getMonstersArea().size();
		int atk,def;
		for(int i=0;i<x;i++){
			MonsterCard temp = getBoard().getActivePlayer().getField().getMonstersArea().get(i);
			atk = temp.getAttackPoints();
			def=temp.getDefensePoints();
			temp.setAttackPoints(atk+gain);
			temp.setDefensePoints(def+gain);
		}
	}
}

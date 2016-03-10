package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class MagePower extends SpellCard {
	
	public MagePower(String name, String description){
		super(name,description);
	}
	public SpellCard clone(){
		return new MagePower(this.getName(),this.getDescription());
	}
	public void action(MonsterCard monster){
		int x = getBoard().getActivePlayer().getField().getSpellArea().size();
		int gain = x*500;
		monster.setAttackPoints(monster.getAttackPoints()+gain);
		monster.setDefensePoints(monster.getDefensePoints()+gain);
	}

}

package eg.edu.guc.yugioh.cards;

public class MonsterCard extends Card {
	private int level;
	private int defensePoints;
	private int attackPoints;
	private Mode mode;
	private boolean attacked;
	private boolean switched;

	public MonsterCard(String name, String description, int level, int attack, int defence){
		super(name, description);
		this.level=level;
		attackPoints=attack;
		defensePoints=defence;
		mode= Mode.DEFENSE;

	}
	public MonsterCard clone(){
		MonsterCard ret = new MonsterCard(this.getName(), this.getDescription(),getLevel() ,this.getAttackPoints(), this.getDefensePoints());
		return ret;
	}
	public int getLevel() {
		return level;
	}
	public int getDefensePoints(){
		return defensePoints;
	}
	public void setDefensePoints(int newDefencePoints){
		defensePoints=newDefencePoints;
	}
	public int getAttackPoints(){
		return attackPoints;
	}
	public void setAttackPoints(int newAttackPoints){
		attackPoints=newAttackPoints;
	}
	public Mode getMode() {
		return mode;
	}
	public void setMode(Mode newMode) {
		mode = newMode;
	}
	public void switchMode(){
		if(mode.equals(Mode.ATTACK))
			mode = Mode.DEFENSE;
		else mode = Mode.ATTACK;
	}
	@Override
	public void action(MonsterCard monster) {
			if(monster.getMode().equals(Mode.ATTACK))	{	
				int score = this.attackPoints - monster.getAttackPoints();
				if(score > 0){
					getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
					getBoard().getOpponentPlayer().setLifePoints(getBoard().getOpponentPlayer().getLifePoints()-score);
				}
				else{
					if(score == 0){
						getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
						getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
					}
					else{
						getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
						getBoard().getActivePlayer().setLifePoints(getBoard().getActivePlayer().getLifePoints()+score);

					}
				}
			}
			else{
				int score = this.attackPoints - monster.getDefensePoints();
				if(score > 0){
					getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
				}
				else{ 
					if(score < 0){
						getBoard().getActivePlayer().setLifePoints(getBoard().getActivePlayer().getLifePoints()+score);
					}
				}
			}
			if(getBoard().getActivePlayer().getLifePoints()<=0){
				getBoard().setWinner(getBoard().getOpponentPlayer());
			}
			else{
				if(getBoard().getOpponentPlayer().getLifePoints()<=0){
					getBoard().setWinner(getBoard().getActivePlayer());
				}
			}
		
	}
	public void action(){
	//	if(getBoard().getActivePlayer().setMonster(this));
		getBoard().getOpponentPlayer().setLifePoints(getBoard().getOpponentPlayer().getLifePoints()-this.attackPoints);
		if(getBoard().getOpponentPlayer().getLifePoints()<=0){
			getBoard().setWinner(getBoard().getActivePlayer());
		}
	}
	public boolean getAttacked() {
		return attacked;
	}
	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}
	public boolean isSwitched() {
		return switched;
	}
	public void setSwitched(boolean switched) {
		this.switched = switched;
	}
	
}

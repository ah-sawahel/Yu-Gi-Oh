package eg.edu.guc.yugioh.board.player;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.CruelAttackOnLifePointsException;
import eg.edu.guc.yugioh.exceptions.CruelAttackOnMonsterException;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;

public class Player implements Duelist{
	private String name;
	private int lifePoints;
	private Field field;
	boolean summon;
	//	private boolean attackedDirectly;

	//	public boolean isAttackedDirectly() {
	//		return attackedDirectly;
	//	}
	//
	//	public void setAttackedDirectly(boolean attackedDirectly) {
	//		this.attackedDirectly = attackedDirectly;
	//	}

	public Player(String name) throws IOException, UnexpectedFormatException{
		this.name=name;
		lifePoints=8000;
		this.field = new Field();
	}

	public String getName() {
		return name;
	}
	public void setName(String s){
		this.name = s;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int newLifePoints) {
		lifePoints = newLifePoints;
	}
	public Field getField(){
		return field;
	}

	@Override
	public boolean summonMonster(MonsterCard monster) {
		if(Card.getBoard().getWinner() != null) return false;
		if(Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.BATTLE))
			throw new WrongPhaseException("YOU CAN'T SUMMON IN BATTLE PHASE");
		if(summon) throw new MultipleMonsterAdditionException("YOU CAN'T SUMMON 2 MONSTERS IN 1 TURN!");
		if(this.equals(Card.getBoard().getActivePlayer()) && summon == false && this.getField().getHand().contains(monster) && (Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN1) || Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN2))){
			getField().addMonsterToField(monster, Mode.ATTACK, false);
			if(getField().getMonstersArea().contains(monster)){
				summon = true; 
				return true;
			}
			else return false;
		}
		return false;
	}

	public boolean summonMonster(MonsterCard monster,ArrayList<MonsterCard> sacrifices) {
		if(Card.getBoard().getWinner() != null) return false;
		if(Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.BATTLE))
			throw new WrongPhaseException("YOU CAN'T SUMMON IN BATTLE PHASE");
		if(summon) throw new MultipleMonsterAdditionException("YOU CAN'T SUMMON 2 MONSTERS IN 1 TURN!");
		if(this.equals(Card.getBoard().getActivePlayer()) && summon == false && this.getField().getHand().contains(monster) && (Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN1) || Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN2))){
			getField().addMonsterToField(monster, Mode.ATTACK, sacrifices);
			if(getField().getMonstersArea().contains(monster)) {
				summon = true;
				return true;
			}
			else return false;
		}
		return false;
	}

	public boolean setMonster(MonsterCard monster) {
		if(Card.getBoard().getWinner() != null) return false;

		if((Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.BATTLE)))
			throw new WrongPhaseException("YOU CAN'T SUMMON IN BATTLE PHASE");

		if(summon) throw new MultipleMonsterAdditionException("YOU CAN'T SUMMON 2 MONSTERS IN 1 TURN!");

		if(this.equals(Card.getBoard().getActivePlayer()) && this.getField().getHand().contains(monster) && (Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN1) || Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN2))){
			getField().addMonsterToField(monster, Mode.DEFENSE, true);
			if(getField().getMonstersArea().contains(monster)){
				summon = true;
				return true;
			}
			else return false;
		}return false;
	}

	public boolean setMonster(MonsterCard monster,ArrayList<MonsterCard> sacrifices) {
		if(Card.getBoard().getWinner() != null) return false;
		if((Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.BATTLE)))
			throw new WrongPhaseException("YOU CAN'T SUMMON IN BATTLE PHASE");
		if(summon) throw new MultipleMonsterAdditionException("YOU CAN'T SUMMON 2 MONSTERS IN 1 TURN!");
		if(this.equals(Card.getBoard().getActivePlayer()) && summon == false && this.getField().getHand().contains(monster) && (Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN1) || Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN2))){
			getField().addMonsterToField(monster, Mode.DEFENSE, sacrifices);
			monster.setHidden(true);
			if(getField().getMonstersArea().contains(monster)){
				summon = true;
				return true;
			}
			else return false;
		}return false;

	}
	public boolean setSpell(SpellCard spell){
		if(Card.getBoard().getWinner() != null) return false;
		if(Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.BATTLE)){
			//		System.out.println("*******************");
			throw new WrongPhaseException();
		}

		if(this.equals(Card.getBoard().getActivePlayer()) && this.getField().getHand().contains(spell) && (Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN1) || Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN2))){
			getField().addSpellToField(spell, null, true);
			if(getField().getSpellArea().contains(spell)) return true;
			else return false;
		}return false;
	}

	public boolean activateSpell(SpellCard spell, MonsterCard monster) {
		if(Card.getBoard().getWinner() != null) return false;
		if(!(Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN1) || Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN2)))
			throw new WrongPhaseException("YOU CAN'T SET SPELL IN BATTLE PHASE");
		if(this.equals(Card.getBoard().getActivePlayer()) && (Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN1) || Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN2))){
			if(this.getField().getHand().contains(spell)&& !spell.getLocation().equals(Location.FIELD)){
				setSpell(spell);
			}

			switch(spell.getName()){
			case "Card Destruction" : 
			case "Dark Hole" : 
			case "Graceful Dice" : 
			case "Harpie's Feather Duster" : 
			case "Heavy Storm" : 
			case "Monster Reborn" : 
			case "Pot of Greed" : 
			case "Raigeki" : Card.getBoard().getActivePlayer().getField().activateSetSpell(spell, null); break;
			case "Change Of Heart" : 
			case "Mage Power" : Card.getBoard().getActivePlayer().getField().activateSetSpell(spell, monster);; break;
			}
			if(getField().getGraveyard().contains(spell)) return true;
			else return false;

		}return false;
	}


	public boolean declareAttack(MonsterCard activeMonster, MonsterCard opponentMonster) {
		if(Card.getBoard().getWinner() != null) return false;
		if(activeMonster.getMode().equals(Mode.DEFENSE))
			throw new DefenseMonsterAttackException("MONSTER IN DEFENCE MODE");
		if(!Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.BATTLE))
			throw new WrongPhaseException("YOU CAN'T ATTACK IN THIS PHASE!");
		if(activeMonster.getAttacked())
			throw new MonsterMultipleAttackException("YOU CAN'T ATTACK TWICE WITH THE SAME MONSTER!");
		//		if(activeMonster.getLevel() > opponentMonster.getLevel())
		//			throw new CruelAttackOnMonsterException();

		if(this.equals(Card.getBoard().getActivePlayer())){
			if(Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.BATTLE)){
				if(activeMonster.getMode().equals(Mode.ATTACK)){
					if(Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(activeMonster) && Card.getBoard().getOpponentPlayer().getField().getMonstersArea().contains(opponentMonster) ){
						if(!activeMonster.getAttacked()){
							activeMonster.action(opponentMonster);
							activeMonster.setAttacked(true);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean declareAttack(MonsterCard activeMonster) {
		if(Card.getBoard().getWinner() != null) return false;
		//	System.out.println(Card.getBoard().getActivePlayer().getField().getPhase());

		if((Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN1)) || (Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN2))){
			//	System.out.println(Card.getBoard().getActivePlayer().getField().getPhase());
			throw new WrongPhaseException("YOU CAN'T ATTACK IN THIS PHASE!");
		}
		//	System.out.println(activeMonster.getAttacked());
		if(activeMonster.getAttacked())
			throw new MonsterMultipleAttackException("YOU CAN'T ATTACK TWICE WITH THE SAME MONSTER!");
		//		System.out.println(activeMonster.getMode());
				if(activeMonster.getMode().equals(Mode.DEFENSE))
					throw new DefenseMonsterAttackException("MONSTER IN DEFENCE MODE");
		//		if(attackedDirectly) {
		//			//System.out.println(attackedDirectly);
		//			throw new CruelAttackOnLifePointsException();
		//		}
		//		if(attackedDirectly)
		//			return false;
		////		if(activeMonster.getName().equals("Blue Eyes 3"))
		//			throw new CruelAttackOnLifePointsException();
		//		


		if(this.equals(Card.getBoard().getActivePlayer())){
			if(Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.BATTLE)){
				if(activeMonster.getMode().equals(Mode.ATTACK)){
					if(Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(activeMonster)){
						if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().isEmpty()){
							if(!activeMonster.getAttacked()){
								//	if(!attackedDirectly){
								//		System.out.println(activeMonster.getName() + attackedDirectly);
								activeMonster.action();
								activeMonster.setAttacked(true);
								//	setAttackedDirectly(true);
								return true;
								//	}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public void addCardToHand() {
		if(Card.getBoard().getWinner() != null) return;

		if(this.equals(Card.getBoard().getActivePlayer()) && Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN1)){
			Card.getBoard().getActivePlayer().getField().addCardToHand();
		}
		System.out.println(Card.getBoard().getActivePlayer().getField().getDeck().getDeck().size());
		if(Card.getBoard().getActivePlayer().getField().getDeck().getDeck().size() < 1){
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			System.out.println(Card.getBoard().getWinner().getName());
			return;
		}
	}

	public void addNCardsToHand(int n) {
		if(Card.getBoard().getWinner() != null) return;
		if(this.equals(Card.getBoard().getActivePlayer())){
			if(Card.getBoard().getActivePlayer().getField().getDeck().getDeck().size() < n){
				Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
				return;
			}
			Card.getBoard().getActivePlayer().getField().addNCardsToHand(n);
		}
		if(this.equals(Card.getBoard().getOpponentPlayer())){
			if(Card.getBoard().getOpponentPlayer().getField().getDeck().getDeck().size() < n){
				Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
				return;
			}
			Card.getBoard().getOpponentPlayer().getField().addNCardsToHand(n);
		}
	}

	public void endPhase() {
		if(this.equals(Card.getBoard().getActivePlayer())){
			if(Card.getBoard().getWinner() != null) return;
			if(Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN1))
				Card.getBoard().getActivePlayer().getField().setPhase(Phase.BATTLE);
			else if(Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.BATTLE))
				Card.getBoard().getActivePlayer().getField().setPhase(Phase.MAIN2);
			else if(Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN2))
				endTurn();
		}
	}

	public boolean endTurn() {
		if(Card.getBoard().getWinner() != null) return false;
		if(this.equals(Card.getBoard().getActivePlayer()) && Card.getBoard().getWinner() == null){
			Card.getBoard().nextPlayer();
			Card.getBoard().getActivePlayer().getField().setPhase(Phase.MAIN1);
			summon = false;
			//			attackedDirectly = false;
			for (int i = 0; i < Card.getBoard().getActivePlayer().getField().getMonstersArea().size(); i++) {
				Card.getBoard().getActivePlayer().getField().getMonstersArea().get(i).setAttacked(false);
				Card.getBoard().getActivePlayer().getField().getMonstersArea().get(i).setSwitched(false);
			}
			return true;

		}
		return false;
	}

	public boolean switchMonsterMode(MonsterCard monster) {
		if(!(Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN1) || Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN2)))
			throw new WrongPhaseException("YOU CAN'T SWITCH MODE IN BATTLE PHASE!");
		if(this.equals(Card.getBoard().getActivePlayer())){
			if (Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN1) || Card.getBoard().getActivePlayer().getField().getPhase().equals(Phase.MAIN2)){
				if(Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(monster)){
					if(!monster.isSwitched()){
						if(monster.getMode().equals(Mode.ATTACK))
							monster.setMode(Mode.DEFENSE);
						else monster.setMode(Mode.ATTACK);
						monster.setSwitched(true);
						return true;
					}
				}
			}
		}
		return false;
	}


}

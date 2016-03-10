package eg.edu.guc.yugioh.board.player;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;

public class Field {
	private Phase phase;
	private ArrayList<MonsterCard> monstersArea;
	private ArrayList<SpellCard> spellArea;
	private Deck deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> graveyard;

	public Field() throws IOException, UnexpectedFormatException {
		phase = Phase.MAIN1;
		monstersArea = new ArrayList<MonsterCard>();
		spellArea = new ArrayList<SpellCard>();
		hand = new ArrayList<Card>();
		graveyard = new ArrayList<Card>();
		deck = new Deck();
	}

	public void addMonsterToField(MonsterCard monster, Mode m, boolean isHidden) {
		if (monstersArea.size() < 5) {
			monstersArea.add(monster);
			monster.setMode(m);
			monster.setHidden(isHidden);
			monster.setLocation(Location.FIELD);
			this.hand.remove(monster);
		}else throw new NoMonsterSpaceException();
	}

	public void addMonsterToField(MonsterCard monster, Mode mode, ArrayList<MonsterCard> sacrifices) {
		if (monstersArea.size() <= 4 ) {
			if (monster.getLevel() < 5 && sacrifices == null) {
				monster.setLocation(Location.FIELD);
				monstersArea.add(monster);
				this.hand.remove(monster);
				monster.setMode(mode);
			} else {
				if (monster.getLevel() < 7 && monster.getLevel() >4 && sacrifices.size() == 1) {
					//	sacrifices.get(0).setLocation(Location.GRAVEYARD);
					removeMonsterToGraveyard(sacrifices.remove(0));
					monster.setLocation(Location.FIELD);
					monstersArea.add(monster);
					this.hand.remove(monster);
					monster.setMode(mode);
				} else {
					if (monster.getLevel() < 9 && monster.getLevel() >6 && sacrifices.size() == 2) {
						//	sacrifices.get(0).setLocation(Location.GRAVEYARD);;
						removeMonsterToGraveyard(sacrifices.remove(0));
						//		sacrifices.get(0).setLocation(Location.GRAVEYARD);;
						removeMonsterToGraveyard(sacrifices.remove(0));
						monster.setLocation(Location.FIELD);
						monstersArea.add(monster);
						this.hand.remove(monster);
						monster.setMode(mode);
					}
				}
			}

		}else throw new NoMonsterSpaceException();
	}

	public void removeMonsterToGraveyard(MonsterCard monster) {
		//	if (monstersArea.contains(monster)) {
		//		int x = monstersArea.indexOf(monster);
		if(getMonstersArea().contains(monster)){
			monster.setLocation(Location.GRAVEYARD);
			graveyard.add(monster);
			monstersArea.remove(monster);
		}
		//	}
	}

	public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters) {

		while(monsters.size()>0) {
			removeMonsterToGraveyard(monsters.remove(0));
		}
		//monstersArea.removeAll(monsters);
	}

	public void addSpellToField(SpellCard action, MonsterCard monster,
			boolean hidden) {
		if (spellArea.size() < 5) {
			if (hidden) {
				action.setLocation(Location.FIELD);
				spellArea.add(action);
				monster = null;
			} else {
				spellArea.add(action);
				action.setLocation(Location.FIELD);
				activateSetSpell(action,monster);
			}
			this.hand.remove(action);
		}else throw new NoSpellSpaceException();
	}

	public void activateSetSpell(SpellCard action, MonsterCard monster) {
		if(spellArea.contains(action)){
			action.action(monster);
			this.removeSpellToGraveyard(action);
		}
	}

	public void removeSpellToGraveyard(SpellCard spell) {
		if(spellArea.contains(spell)){
			graveyard.add(spell);
			spellArea.remove(spell);
			spell.setLocation(Location.GRAVEYARD);
			//	this.getHand().remove(spell);
		}
	}

	public void removeSpellToGraveyard(ArrayList<SpellCard> spells) {
		//for (int i = 0; i < spells.size(); i++) {
		//removeSpellToGraveyard(spells.remove(i));
		//}
		while(spells.size()>0) {
			//			spells.get(0).setLocation(Location.GRAVEYARD);
			//			graveyard.add(spells.remove(0));
			removeSpellToGraveyard(spells.remove(0));
		}
		spellArea.removeAll(spells);

	}

	public void addCardToHand() {
		if(Card.getBoard().getWinner() == null){
			Card temp = deck.drawOneCard();
			hand.add(temp);
			temp.setLocation(Location.HAND);
		}
	}

	public void addNCardsToHand(int n) {
		//		ArrayList<Card> temp = deck.drawNCards(n);
		//		hand.addAll(temp);
		//		for (int i = 0; i < temp.size(); i++) {
		//			temp.get(i).setLocation(Location.HAND);
		//		}
		ArrayList<Card> a= deck.drawNCards(n);
		while(a.size()>0)
		{
			Card c = a.remove(0);
			c.setLocation(Location.HAND);
			hand.add(c);
		}
	}

	public ArrayList<MonsterCard> getMonstersArea() {
		return monstersArea;
	}

	public ArrayList<SpellCard> getSpellArea() {
		return spellArea;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}

	public Deck getDeck() {
		return deck;
	}

}

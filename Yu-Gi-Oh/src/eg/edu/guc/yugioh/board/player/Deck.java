package eg.edu.guc.yugioh.board.player;

import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.*;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Deck {
	private static ArrayList<Card> monsters;
	private static ArrayList<Card> spells;
	private ArrayList<Card> deck;
	private static String monstersPath = "Database-Monsters.csv";
	private static String spellsPath = "Database-Spells.csv";
	Scanner sc = new Scanner(System.in);
	public Deck() throws IOException, UnexpectedFormatException {

		//	boolean flag = false;
		//	int c = 1;
		//	while(c < 10){
		//		System.out.println("Please enter a correct path: ");
		//		String test = sc.nextLine();
		int c = 0;
		try{
			monsters = loadCardsFromFile(getMonstersPath());
		}catch(FileNotFoundException | UnexpectedFormatException e){
			//	e.printStackTrace();
			if( c == 3) throw e;
			c++;
			System.out.println("Please enter a correct path: ");
			String test = sc.nextLine();
			setMonstersPath(test);
			try{
				monsters = loadCardsFromFile(getMonstersPath());
			}catch(FileNotFoundException | UnexpectedFormatException e1){
				if( c == 3) throw e1;
				c++;
				//	e1.printStackTrace();
				System.out.println("Please enter a correct path: ");
				test = sc.nextLine();
				setMonstersPath(test);
				try{
					monsters = loadCardsFromFile(getMonstersPath());
				}catch(FileNotFoundException | UnexpectedFormatException e2){
					if( c == 3) throw e2;
					c++;
					//			e2.printStackTrace();
					System.out.println("Please enter a correct path: ");
					test = sc.nextLine();
					setMonstersPath(test);
					try{
						monsters = loadCardsFromFile(getMonstersPath());
					}catch(FileNotFoundException | UnexpectedFormatException e3){
						throw e3;
					}
				}

			}
		}
		try{
			spells = loadCardsFromFile(getSpellsPath());
		}catch(FileNotFoundException | UnexpectedFormatException e){
			//		e.printStackTrace();
			if( c == 3) throw e;
			c++;
			System.out.println("Please enter a correct path: ");
			String test = sc.nextLine();
			setSpellsPath(test);
			try{
				spells = loadCardsFromFile(getSpellsPath());
			}catch(FileNotFoundException | UnexpectedFormatException e1){
				if( c == 3) throw e1;
				c++;
				System.out.println("Please enter a correct path: ");
				test = sc.nextLine();
				setSpellsPath(test);
				try{
					spells = loadCardsFromFile(getSpellsPath());
				}catch(FileNotFoundException | UnexpectedFormatException e2){
					if( c == 3) throw e2;
					c++;
					System.out.println("Please enter a correct path: ");
					test = sc.nextLine();
					setSpellsPath(test);
					//		System.out.println(3);
					try{
						spells = loadCardsFromFile(getSpellsPath());
					}catch(FileNotFoundException | UnexpectedFormatException e3){
						throw e3;
					}
				}
			}
		}

		//		for (int i = 0; i < 2 && !flag ; i++) {
		//			System.out.println("Please enter a correct path: ");
		//			String test = sc.nextLine();
		//			try{
		//				monsters = loadCardsFromFile(test);
		//				flag = true;
		//			}catch(FileNotFoundException e){
		//
		//			}	
		//		}
		//		if(!flag){
		//			System.out.println("Please enter a correct path: ");
		//			String test = sc.nextLine();
		//			try{
		//				monsters = loadCardsFromFile(test);
		//			}catch(FileNotFoundException e){
		//				throw e;
		//			}
		//		}
		//		flag = false;
		//		for (int i = 0; i < 2 && !flag ; i++) {
		//			System.out.println("Please enter a correct path: ");
		//			String test = sc.nextLine();
		//			try{
		//				spells = loadCardsFromFile(test);
		//				flag = true;
		//			}catch(FileNotFoundException e){
		//				e = new FileNotFoundException();
		//			}	
		//		}
		//		if(!flag){
		//			System.out.println("Please enter a correct path: ");
		//			String test = sc.nextLine();
		//			try{
		//				spells = loadCardsFromFile(test);
		//			}catch(FileNotFoundException e){
		//				throw e;
		//			}
		//		}

		deck = new ArrayList<Card>();
		this.buildDeck(monsters, spells);
		this.shuffleDeck();
	}

	public ArrayList<Card> loadCardsFromFile(String path) throws IOException, UnexpectedFormatException {
		//		if(!(path.equals(getMonstersPath())|| path.equals(getSpellsPath()))){
		//			System.out.println("*********");
		//			throw new FileNotFoundException();
		//		}

		ArrayList<Card> Temp = new ArrayList<Card>();
		String currentLine = "";
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		int countLine = 1;
		while ((currentLine = br.readLine()) != null) {
			//			if(currentLine.equals("") || currentLine.equals(" ")){
			//				//		System.out.println("***********");
			//				throw new MissingFieldException(path, countLine);
			//			}

			String[] data = currentLine.split(",");

			for (int i = 0; i < data.length; i++) {
				if(data[i].equals(" ") || data[i].equals("")){
					System.out.println("***************");
					throw new EmptyFieldException(path, countLine, i+1);

				}
			}
			if(!(data[0].equals("Monster") || data[0].equals("Spell")))
				throw new UnknownCardTypeException(path, countLine, data[0]);
			//			if(path.equals(spellsPath) && !data[0].equals("Spell"))
			//				throw new UnknownCardTypeException(path, countLine, data[0]);
			//			if(path.equals(monstersPath) && !data[0].equals("Monster"))
			//				throw new UnknownCardTypeException(path, countLine, data[0]);
			if (data[0].equals("Monster")) {
				if(data.length < 6) throw new MissingFieldException(path, countLine);
				MonsterCard currentCard = new MonsterCard(data[1], data[2],
						Integer.parseInt(data[5]), Integer.parseInt(data[3]),
						Integer.parseInt(data[4]));
				Temp.add(currentCard);
			} else {
				SpellCard c = null;
				if(data.length < 3) throw new MissingFieldException(path, countLine);
				switch (data[1]) {
				case "Card Destruction":
					c = new CardDestruction(data[1], data[2]);
					break;
				case "Change Of Heart":
					c = new ChangeOfHeart(data[1], data[2]);
					break;
				case "Dark Hole":
					c = new DarkHole(data[1], data[2]);
					break;
				case "Graceful Dice":
					c = new GracefulDice(data[1], data[2]);
					break;
				case "Harpie's Feather Duster":
					c = new HarpieFeatherDuster(data[1], data[2]);
					break;
				case "Heavy Storm":
					c = new HeavyStorm(data[1], data[2]);
					break;
				case "Mage Power":
					c = new MagePower(data[1], data[2]);
					break;
				case "Monster Reborn":
					c = new MonsterReborn(data[1], data[2]);
					break;
				case "Pot of Greed":
					c = new PotOfGreed(data[1], data[2]);
					break;
				case "Raigeki":
					c = new Raigeki(data[1], data[2]);
					break;
				default:
					throw new UnknownSpellCardException(path, countLine, data[1]);
				}
				Temp.add(c);
			}
			countLine++;
		}
		return Temp;

	}

	private void buildDeck(ArrayList<Card> monsters, ArrayList<Card> spells) {

		int x;
		for (int i = 0; i < 15; i++) {
			x = (int) Math.floor(Math.random() * monsters.size());
			MonsterCard temp = (MonsterCard) monsters.get(x);
			MonsterCard temp2 = temp.clone();
			temp2.setLocation(Location.DECK);
			deck.add(temp2);
		}
		for (int i = 0; i < 5; i++) {
			x = (int) (Math.random() * spells.size());
			SpellCard temp = (SpellCard) spells.get(x);

			// switch(temp.getName()){
			// case "Card Destruction": temp = (CardDestruction)temp;break;
			// case "Change Of Heart": temp = (ChangeOfHeart)temp;break;
			// case "Dark Hole": temp = (DarkHole)temp;break;
			// case "Graceful Dice": temp = (GracefulDice)temp; break;
			// case "Harpie's Feather Duster": temp = (HarpieFeatherDuster)
			// temp; break;
			// case "Heavy Storm": temp= (HeavyStorm)temp; break;
			// case "Mage Power": temp =(MagePower)temp; break;
			// case "Monster Reborn": temp=(MonsterReborn) temp; break;
			// case "Pot of Greed": temp=(PotOfGreed) temp; break;
			// case "Raigeki": temp= (Raigeki) temp; break;
			// default: System.out.print("Error");
			//
			// }
			SpellCard temp2 = temp.clone();
			temp2.setLocation(Location.DECK);
			deck.add(temp2);
		}


	}

	private void shuffleDeck() {

		Card temp = null;
		int y, z;
		for (int i = 0; i < 30; i++) {
			y = (int) (Math.random() * 20);
			z = (int) (Math.random() * 20);
			temp = deck.get(y);
			deck.set(y, deck.get(z));
			deck.set(z, temp);
		}

	}

	public ArrayList<Card> drawNCards(int n) {
		if(this.getDeck().size() < n) return null;
		ArrayList<Card> drawn = new ArrayList<Card>();

		for (int i = 0; i < n; i++) {
			//	deck.get(0).setLocation(Location.HAND);
			drawn.add(deck.remove(0));
		}
		return drawn;
	}

	public Card drawOneCard() {
		//	deck.get(0).setLocation(Location.HAND);
		if(this.getDeck().size() == 0) return null;
		return deck.remove(0);
	}

	public static ArrayList<Card> getMonsters() {
		return monsters;
	}

	public void setMonsters(ArrayList<Card> monsters) {
		this.monsters = monsters;
	}

	public static ArrayList<Card> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<Card> spells) {
		this.spells = spells;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> d) {
		deck = d;
	}

	//	public static void main(String[] args) {
	//		try {
	//			Deck d = new Deck();
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//	}

	public static String getMonstersPath() {
		return monstersPath;
	}

	public static void setMonstersPath(String monstersPath) {
		Deck.monstersPath = monstersPath;
	}

	public static String getSpellsPath() {
		return spellsPath;
	}

	public static void setSpellsPath(String spellsPath) {
		Deck.spellsPath = spellsPath;
	}

	public static void main(String[] args) throws IOException, UnexpectedFormatException {
		Deck d = new Deck();
	}

}

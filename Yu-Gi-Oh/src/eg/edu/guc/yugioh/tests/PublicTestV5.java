package eg.edu.guc.yugioh.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.GracefulDice;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.exceptions.CruelAttackException;
import eg.edu.guc.yugioh.exceptions.CruelAttackOnLifePointsException;
import eg.edu.guc.yugioh.exceptions.CruelAttackOnMonsterException;

public class PublicTestV5 {
	Player p1;
	Player p2;
	Board board;
	final MonsterCard blueEyes1MonsterLevel4 = new MonsterCard("Blue Eyes 1", "Legendary dragon", 4, 3000, 3000);
	final MonsterCard blueEyes2MonsterLevel4 = new MonsterCard("Blue Eyes 2", "Legendary dragon", 4, 3000, 2000);
	final MonsterCard blueEyes3MonsterLevel3 = new MonsterCard("Blue Eyes 3", "Legendary dragon", 3, 2000, 2000);
	final MonsterCard blueEyes4MonsterLevel3 = new MonsterCard("Blue Eyes 4", "Legendary dragon", 3, 2000, 1000);
	final MonsterCard blueEyes5MonsterLevel2 = new MonsterCard("Blue Eyes 5", "Legendary dragon", 2, 1000, 1000);
	final MonsterCard blueEyes6MonsterLevel1 = new MonsterCard("Blue Eyes 6", "Legendary dragon", 1, 1000, 500);
	final ChangeOfHeart changeOfHeart1 = new ChangeOfHeart("change of heart", "change of heart");
	final ChangeOfHeart changeOfHeart2 = new ChangeOfHeart("change of heart", "change of heart");
	final DarkHole darkHoleSpell1 = new DarkHole("DarkHole", "DH");
	final DarkHole darkHoleSpell2 = new DarkHole("DarkHole", "DH");
	final MonsterReborn monsterReborn1= new MonsterReborn("monsterReborn", "monsterReborn");
	final MonsterReborn monsterReborn2= new MonsterReborn("monsterReborn", "monsterReborn");
	final PotOfGreed potOfGreed1= new PotOfGreed("potOfGreed", "potOfGreed");
	final PotOfGreed potOfGreed2= new PotOfGreed("potOfGreed", "potOfGreed");
	final GracefulDice gracefulDice= new GracefulDice("gracefulDice", "gracefulDice");
	final MagePower magePower= new MagePower("magePower", "magePower");
	@Test(timeout=1000)
	public void ClassesExistTest(){
		try {
	        Class.forName("eg.edu.guc.yugioh.exceptions.CruelAttackException");
	        Class.forName("eg.edu.guc.yugioh.exceptions.CruelAttackOnLifePointsException");
	        Class.forName("eg.edu.guc.yugioh.exceptions.CruelAttackOnMonsterException");
	    } catch (ClassNotFoundException e) {
	        fail("Missing classes or wrong packaging");
	    }
	}
	
	@Test(timeout=1000)
	public void CannotEmpowerMonstersExceptionInheritance(){
		assertEquals("CannotEmpowerMonstersException should inherit from the appropriate class.",
				RuntimeException.class, CruelAttackException.class.getSuperclass());
	}
	
	@Test(timeout=1000)
	public void CruelAttackOnLifePointsExceptionInheritance(){
		assertEquals("CannotUseGracefulDiceException should inherit from the appropriate class.",
				CruelAttackException.class, CruelAttackOnLifePointsException.class.getSuperclass());
	}
	
	@Test(timeout=1000)
	public void CruelAttackOnMonsterExceptionInheritance(){
		assertEquals("CannotUseMagePowerException should inherit from the appropriate class.",
				CruelAttackException.class, CruelAttackOnMonsterException.class.getSuperclass());
	}
	
	@Test(timeout=1000, expected= CruelAttackOnLifePointsException.class)
	public void CruelAttackOnLifePointsExceptionTest1(){
		try{
			buildBoard();
		}
		catch(Exception e){
			fail("There shouldn't be a problem to start the game");
		}
		
		try{
			scenario1();
			board.getActivePlayer().endPhase();
			assertTrue(board.getActivePlayer().declareAttack(blueEyes1MonsterLevel4));
		}
		catch(CruelAttackOnLifePointsException e){
			fail("Player should be able to declare the first attack on the opponent's lifepoints");
		}
		board.getActivePlayer().declareAttack(blueEyes3MonsterLevel3);
		fail("Player should not be able to declare a second attack on the opponent's lifepoints in the same turn");
	}
	
	@Test(timeout=1000)
	public void CruelAttackOnLifePointsExceptionTest2(){
		try{
			buildBoard();
		}
		catch(Exception e){
			fail("There shouldn't be a problem to start the game");
		}
		
		try{
			scenario1();
			board.getActivePlayer().endPhase();
			assertTrue(board.getActivePlayer().declareAttack(blueEyes1MonsterLevel4));
		}
		catch(CruelAttackOnLifePointsException e){
			fail("Player should be able to declare the first attack on the opponent's lifepoints");
		}
	}
	
	@Test(timeout=1000)
	public void CruelAttackOnLifePointsExceptionTest3(){
		try{
			buildBoard();
		}
		catch(Exception e){
			fail("There shouldn't be a problem to start the game");
		}
		
		try{
			scenario1();
			board.getActivePlayer().endPhase();
			assertTrue(board.getActivePlayer().declareAttack(blueEyes1MonsterLevel4));
			board.getActivePlayer().endTurn();
			
			board.getActivePlayer().endTurn();
			board.getActivePlayer().endPhase();
			assertTrue(board.getActivePlayer().declareAttack(blueEyes1MonsterLevel4));
		}
		catch(CruelAttackOnLifePointsException e){
			fail("Player should be able to declare another attack on the opponent's lifepoints in another turn");
		}
	}
	
	@Test(timeout=1000, expected= CruelAttackOnMonsterException.class)
	public void CruelAttackOnMonsterExceptionTest1(){
		try{
			buildBoard();
		}
		catch(Exception e){
			fail("There shouldn't be a problem to start the game");
		}
		
		try{
			scenario2();
			board.getActivePlayer().endPhase();
		}
		catch(CruelAttackOnMonsterException e){
			fail("Player should be able to add the cards.");
		}
		board.getActivePlayer().declareAttack(blueEyes2MonsterLevel4, blueEyes3MonsterLevel3);
		fail("Player should not be able to declare the attack.");
	}
	
	@Test(timeout=1000, expected= CruelAttackOnMonsterException.class)
	public void CruelAttackOnMonsterExceptionTest2(){
		try{
			buildBoard();
		}
		catch(Exception e){
			fail("There shouldn't be a problem to start the game");
		}
		
		try{
			scenario2();
			board.getActivePlayer().endPhase();
		}
		catch(CruelAttackOnMonsterException e){
			fail("Player should be able to add the cards.");
		}
		board.getActivePlayer().declareAttack(blueEyes2MonsterLevel4, blueEyes5MonsterLevel2);
		fail("Player should not be able to declare the attack.");
	}
	
	@Test(timeout=1000)
	public void CruelAttackOnMonsterExceptionTest3(){
		try{
			buildBoard();
		}
		catch(Exception e){
			fail("There shouldn't be a problem to start the game");
		}
		
		try{
			scenario2();
			board.getActivePlayer().endPhase();
			assertTrue(board.getActivePlayer().declareAttack(blueEyes2MonsterLevel4, blueEyes1MonsterLevel4));
		}
		catch(CruelAttackOnMonsterException e){
			fail("Player should be able to declare the attack.");
		}
	}
	//------------------------------------------------------------------------
	public void buildBoard() throws Exception{
		board=null;
		p1 = new Player("Yugi");
		p2 = new Player("Kaiba");
		board = new Board();
		board.startGame(p1, p2);
	}
	
	public void scenario1(){
		
		board.getActivePlayer().getField().getDeck().getDeck().add(0, changeOfHeart1);
		board.getActivePlayer().getField().getDeck().getDeck().add(1, potOfGreed2);
		board.getOpponentPlayer().getField().getDeck().getDeck().add(0, blueEyes1MonsterLevel4);
		board.getOpponentPlayer().getField().getDeck().getDeck().add(1, magePower);
		
		board.getActivePlayer().endTurn();
		
		//activePlayer will draw blueEyes3Monster
		board.getActivePlayer().summonMonster(blueEyes1MonsterLevel4);
	}
	
	public void scenario2(){
		
		board.getActivePlayer().getField().getDeck().getDeck().add(0, blueEyes1MonsterLevel4);
		board.getActivePlayer().getField().getDeck().getDeck().add(1, blueEyes3MonsterLevel3);
		board.getActivePlayer().getField().getDeck().getDeck().add(2, blueEyes5MonsterLevel2);
		board.getOpponentPlayer().getField().getDeck().getDeck().add(0, blueEyes2MonsterLevel4);
		board.getOpponentPlayer().getField().getDeck().getDeck().add(1, blueEyes4MonsterLevel3);
		board.getOpponentPlayer().getField().getDeck().getDeck().add(2, blueEyes6MonsterLevel1);
		board.getOpponentPlayer().getField().getDeck().getDeck().add(3, changeOfHeart2);
		
		board.getActivePlayer().endTurn();
		
		//activePlayer will draw blueEyes2MonsterLevel4
		board.getActivePlayer().summonMonster(blueEyes2MonsterLevel4);
		board.getActivePlayer().endTurn();
		
		//activePlayer will draw blueEyes1MonsterLevel4
		board.getActivePlayer().summonMonster(blueEyes1MonsterLevel4);
		board.getActivePlayer().endTurn();
		
		//activePlayer will draw blueEyes4MonsterLevel3
		board.getActivePlayer().summonMonster(blueEyes4MonsterLevel3);
		board.getActivePlayer().endTurn();

		//activePlayer will draw blueEyes3MonsterLevel3
		board.getActivePlayer().summonMonster(blueEyes3MonsterLevel3);
		board.getActivePlayer().endTurn();	
		
		//activePlayer will draw blueEyes6MonsterLevel1
		board.getActivePlayer().summonMonster(blueEyes6MonsterLevel1);
		board.getActivePlayer().endTurn();

		//activePlayer will draw blueEyes5MonsterLevel2
		board.getActivePlayer().setMonster(blueEyes5MonsterLevel2);
		board.getActivePlayer().endTurn();	
	}
}

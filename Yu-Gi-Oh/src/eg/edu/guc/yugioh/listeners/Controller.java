package eg.edu.guc.yugioh.listeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.gui.BattleScreen;
import eg.edu.guc.yugioh.gui.FirstWindow;
import eg.edu.guc.yugioh.gui.MonstersButton;
import eg.edu.guc.yugioh.gui.SpellsButton;

public class Controller implements ActionListener{

	BattleScreen BS;
	FirstWindow FW;
	Board board;
	Player player1;
	Player player2;
	handListeners hs;
	midPanelListener mp;
	Updated updater;

	public Controller(String p1n, String p2n) throws IOException, UnexpectedFormatException {
		FW = new FirstWindow();
		player1= new Player(p1n);
		player2= new Player(p2n);
		

		BS = new BattleScreen(p1n, p2n);
		BS.setExtendedState(BS.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		BS.getP1d().setText("DECK \n" + "  "+player1.getField().getDeck().getDeck().size());
		BS.getP2d().setText("DECK \n" + "  "+player2.getField().getDeck().getDeck().size());
		//
		board = new Board();
		hs = new handListeners(board, BS, player1, player2);
		mp = new midPanelListener(board,BS, player1, player2, hs);
		addActionListenersToButtons();
		fillData();
		updater = new Updated(board,BS,player1, player2, hs);
		updater.update();
	}
	

	public void addActionListenersToButtons(){
	//	this.FW.getStart().addActionListener(this);
		
		ArrayList<MonstersButton> m1Buttons = this.BS.getP1().getM().getMonstersButtons();
		for (MonstersButton m1Button : m1Buttons) {
			m1Button.addActionListener(hs);

		}

		ArrayList<SpellsButton> s1Buttons = this.BS.getP1().getS().getSpellsButtons();
		for (SpellsButton s1Button : s1Buttons) {
			s1Button.addActionListener(hs);

		}
		ArrayList<MonstersButton> m2Buttons = this.BS.getP2().getM().getMonstersButtons();
		for (MonstersButton m2Button : m2Buttons) {
			m2Button.addActionListener(hs);
		}

		ArrayList<SpellsButton> s2Buttons = this.BS.getP2().getS().getSpellsButtons();
		for (SpellsButton s2Button : s2Buttons) {
			s2Button.addActionListener(hs);
		}
		
		ArrayList<JButton> h1Buttons = this.BS.getP1().getH().getHand();
		for (JButton h1Button : h1Buttons) {
			h1Button.addActionListener(hs);
		}
		ArrayList<JButton> h2Buttons = this.BS.getH2().getHand();
		for (JButton h2Button : h2Buttons) {
			h2Button.addActionListener(hs);
		}
	}

	// add new instance variables for both 2 playes...
	public void fillData() {
		
		board.startGame(player1, player2);
	//	player2.addCardToHand();
//		player1.endTurn();
	// player2.getField().getSpellArea().add(new SpellCard("SAS","ASD") 
//		JButton life1 = new JButton("" + player1.getLifePoints());
//		JButton life2 = new JButton("" + player2.getLifePoints());
//		BS.setP1Life(life1);
//		BS.setP2Life(life2);
		
		ArrayList<Card> player1hand = player1.getField().getHand();
		ArrayList<JButton> player1button = this.BS.getH1().getHand();
		
		for (int i = 0; i < player1hand.size(); i++) {
		//	System.out.println("heree");
			JButton handCard = new JButton(player1hand.get(i).getName());
			handCard.setPreferredSize(new Dimension(100,130));
			handCard.setVisible(true);
			handCard.setToolTipText("<html>" + player1hand.get(i).getName() + "<br>" + player1hand.get(i).getDescription());
			this.BS.getH1().add(handCard);
			player1button.add(handCard);
			handCard.addActionListener(this.hs);
		}
		BS.getMid().getPhase().addActionListener(this.mp);
		BS.getMid().getTurn().addActionListener(this.mp);

		BS.getH1().setHand(player1button);
		
		ArrayList<Card> player2hand = player2.getField().getHand();
		ArrayList<JButton> player2button = this.BS.getH2().getHand();
		
		for (int i = 0; i < player2hand.size(); i++) {
		//	System.out.println("heree");
			JButton handCard = new JButton(player2hand.get(i).getName());
			handCard.setPreferredSize(new Dimension(100,130));
			handCard.setVisible(true);
			handCard.setToolTipText("<html>" + player2hand.get(i).getName() + "<br>" + player2hand.get(i).getDescription());
			this.BS.getH2().add(handCard);
			player2button.add(handCard);
			handCard.addActionListener(this.hs);
		}
		ArrayList<MonsterCard> player1MA = player1.getField().getMonstersArea();
		ArrayList<MonstersButton> player1MAB = this.BS.getP1().getM().getMonstersButtons();
		
		for (int i = 0; i < player1MA.size(); i++) {
			player1MAB.get(i).setText(player1MA.get(i).getName());
			player1MAB.get(i).setToolTipText("<html>" + player1MA.get(i).getName() + "<br>" + player1MA.get(i).getAttackPoints() + "<br>" + player1MA.get(i).getDefensePoints() + "<br>" + player1MA.get(i).getMode() + "<br>" + player1MA.get(i).getLevel());
			player1MAB.get(i).setVisible(true);
		
		}
		ArrayList<MonsterCard> player2MA = player2.getField().getMonstersArea();
		ArrayList<MonstersButton> player2MAB = this.BS.getP2().getM().getMonstersButtons();
		
		for (int i = 0; i < player2MA.size(); i++) {
			player2MAB.get(i).setText(player2MA.get(i).getName() + " \n " + player2MA.get(i).getAttackPoints() );
			player2MAB.get(i).setToolTipText("<html>" + player2MA.get(i).getName() + "<br>" + player2MA.get(i).getAttackPoints() + "<br>" + player2MA.get(i).getDefensePoints() + "<br>" + player2MA.get(i).getMode() + "<br>" + player2MA.get(i).getLevel());
			player1MAB.get(i).setVisible(true);
		
		}
		
		ArrayList<SpellCard> player1SA = player1.getField().getSpellArea();
		ArrayList<SpellsButton> player1SAB = this.BS.getP1().getS().getSpellsButtons();
		
		for (int i = 0; i < player1SA.size(); i++) {
			player1SAB.get(i).setText(player1SA.get(i).getName());
		
		}
		ArrayList<SpellCard> player2SA = player2.getField().getSpellArea();
		ArrayList<SpellsButton> player2SAB = this.BS.getP2().getS().getSpellsButtons();
		
		for (int i = 0; i < player2SA.size(); i++) {
		System.out.println("heree");
			player2SAB.get(i).setText(player2SA.get(i).getName());
		
		}

		this.BS.repaint();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}

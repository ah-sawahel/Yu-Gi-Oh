package eg.edu.guc.yugioh.listeners;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.gui.BattleScreen;
import eg.edu.guc.yugioh.gui.MonstersButton;
import eg.edu.guc.yugioh.gui.SpellsButton;

public class Updated {
	Board board;
	BattleScreen BS;
	Player player1;
	Player player2;
	handListeners hs;
	
	public Updated(Board b, BattleScreen bs, Player p1, Player p2, handListeners h){
		board = b;
		BS=bs;
		player1 = p1;
		player2 = p2;
		this.hs = h;
	}
	public void update(){
     //	JLabel life1 = new JLabel();
     //	life1.setText("LifePoints: " + player1.getLifePoints());
     //	BS.setP1Life(life1);
	//	JLabel life2 = new JLabel("" + player2.getLifePoints());
	//	BS.setP1Life(life1);
	//	BS.setP2Life(life2);
		BS.getP1name().getText();
		BS.getP2name().getText();
		BS.getP1Life().setText("" + player1.getLifePoints());
		BS.getP2Life().setText("" + player2.getLifePoints());
		BS.getMid().getCurrPhase().setText(board.getActivePlayer().getField().getPhase().name());
		BS.getMid().getCurrPlayer().setText(board.getActivePlayer().getName() + "'s turn");
		
		System.out.println(player1.getLifePoints() + "" + player2.getLifePoints());
		ArrayList<Card> player1hand = player1.getField().getHand();
		ArrayList<JButton> player1button = new ArrayList<JButton>();
		

		for (int i = 0; i < player1hand.size(); i++) {
			if(i>=player1button.size()){
				JButton handCard = new JButton(player1hand.get(i).getName());
				handCard.setPreferredSize(new Dimension(100,130));
				handCard.setVisible(true);
				player1button.add(handCard);
				if(player1hand.get(i) instanceof MonsterCard){
					MonsterCard temp = (MonsterCard) player1hand.get(i);
					handCard.setToolTipText("<html>" +temp.getName() + "<br>" + temp.getAttackPoints() + "<br>" + temp.getDefensePoints() + "<br>" + temp.getLevel() + "<br>" + player1hand.get(i).getDescription());
				}else handCard.setToolTipText("<html>" +player1hand.get(i).getName() + "<br>" + player1hand.get(i).getDescription());
				handCard.addActionListener(this.hs);
				handCard.setVisible(true);

			}
			if(!player1hand.get(i).getName().equals(player1button.get(i).getText())){
				player1button.remove(i);
				i--;
			}
			
		}

		BS.getH1().setHand(player1button);
		
		ArrayList<Card> player2hand = player2.getField().getHand();
		ArrayList<JButton> player2button = new ArrayList<JButton>();
		
		for (int i = 0; i < player2hand.size(); i++) {
			JButton handCard = new JButton(player2hand.get(i).getName());
			handCard.setPreferredSize(new Dimension(100,130));
			player2button.add(handCard);
			if(player2hand.get(i) instanceof MonsterCard){
				MonsterCard temp = (MonsterCard) player2hand.get(i);
				handCard.setToolTipText("<html>" +temp.getName() + "<br>" + temp.getAttackPoints() + "<br>" + temp.getDefensePoints() + "<br>" + temp.getLevel() + "<br>" + player2hand.get(i).getDescription());
			}else handCard.setToolTipText("<html>" +player2hand.get(i).getName() + "<br>" + player2hand.get(i).getDescription());
			handCard.setVisible(true);
			handCard.addActionListener(this.hs);
		}
		BS.getH2().setHand(player2button);
		ArrayList<MonsterCard> player1MA = player1.getField().getMonstersArea();
		ArrayList<MonstersButton> player1MAB = this.BS.getP1().getM().getMonstersButtons();
		int j = 0;
		for (int i = 0; i < player1MA.size(); i++, j++) {
			if(player1MA.get(i).getMode().equals(Mode.ATTACK)){
			player1MAB.get(i).setText(player1MA.get(i).getName());
			player1MAB.get(i).setToolTipText("<html>" + player1MA.get(i).getName() + "<br>" + player1MA.get(i).getAttackPoints() + "<br>" + player1MA.get(i).getDefensePoints() + "<br>" + player1MA.get(i).getMode() + "<br>" + player1MA.get(i).getLevel());
			}else player1MAB.get(i).setText("<html>" + " Hidden Monster" +"<br>" + "Defense Mode");
			//player1MAB.get(i).setToolTipText("<html>" + player1MA.get(i).getName() + "<br>" + player1MA.get(i).getAttackPoints() + "<br>" + player1MA.get(i).getDefensePoints() + "<br>" + player1MA.get(i).getMode());
			
			//player1MAB.get(i).addActionListener(hs);		
		}
		while(j < player1MAB.size()) {
			player1MAB.get(j).setText("");
			j++;
		}
		
		ArrayList<MonsterCard> player2MA = player2.getField().getMonstersArea();
		ArrayList<MonstersButton> player2MAB = this.BS.getP2().getM().getMonstersButtons();
		j = 0;
		for (int i = 0; i < player2MA.size(); i++, j++) {
			if(player2MA.get(i).getMode().equals(Mode.ATTACK)){
				player2MAB.get(i).setText(player2MA.get(i).getName());
				player2MAB.get(i).setToolTipText("<html>" + player2MA.get(i).getName() + "<br>" + player2MA.get(i).getAttackPoints() + "<br>" + player2MA.get(i).getDefensePoints() + "<br>" + player2MA.get(i).getMode() + "<br>" + player2MA.get(i).getLevel());
				}else player2MAB.get(i).setText("<html>" + " Hidden Monster" +"<br>" + "Defense Mode");
				//	player2MAB.get(i).addActionListener(hs);
		
		}
		while(j < player2MAB.size()) {
			player2MAB.get(j).setText("");
			j++;
		}

		ArrayList<SpellCard> player1SA = player1.getField().getSpellArea();
		ArrayList<SpellsButton> player1SAB = this.BS.getP1().getS().getSpellsButtons();
		j = 0;
		for (int i = 0; i < player1SA.size(); i++, j++) {
			if(player1SA.get(i).isHidden()){
				player1SAB.get(i).setText("hidden spell");
			}
			else player1SAB.get(i).setText(player1SA.get(i).getName());
			
		//	player1SAB.get(i).addActionListener(hs);
		
		}
		while(j < player1SAB.size()) {
			player1SAB.get(j).setText("");
			j++;
		}
		ArrayList<SpellCard> player2SA = player2.getField().getSpellArea();
		ArrayList<SpellsButton> player2SAB = this.BS.getP2().getS().getSpellsButtons();
		j = 0;
		for (int i = 0; i < player2SA.size(); i++, j++) {
			if(player2SA.get(i).isHidden()){
				player2SAB.get(i).setText("hidden spell");
			}
			else player2SAB.get(i).setText(player2SA.get(i).getName());
			
		//	player2SAB.get(i).addActionListener(hs);
		}
		while(j < player2SAB.size()) {
			player2SAB.get(j).setText("");
			j++;
		}
		if(player1.equals(board.getActivePlayer())){
			BS.getH1().setVisible(true);
			BS.getH2().setVisible(false);
		}else{
			BS.getH2().setVisible(true);
			BS.getH1().setVisible(false);
		}
		
		if(board.getWinner() != null){
			JOptionPane.showMessageDialog(new JFrame(), board.getWinner().getName() + " WINS THE GAMEE!");
			Object[] options = { "RESTART", "END GAME" };
			int ok = JOptionPane.showOptionDialog(null, "Restart the game ?!", "Play Again?!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
			if(ok == 0){
				String p1 = BS.getP1name().getText();
				String p2 = BS.getP2name().getText();
				BS.dispose();
				try {
					new Controller(p1,p2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnexpectedFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				BS.dispose();
			}
		}
		
		ArrayList <Card> Graveyard1 = player1.getField().getGraveyard();
		
		if(! Graveyard1.isEmpty()){
		    Card grave1 = Graveyard1.get(Graveyard1.size()-1);
		    BS.getP1gy().setText(grave1.getName());
		}
		
		ArrayList <Card> Graveyard2 = player2.getField().getGraveyard();
		
		if(! Graveyard2.isEmpty()){
			Card grave2 = Graveyard2.get(Graveyard2.size()-1);
			BS.getP2gy().setText(grave2.getName());
		}
		
		
		BS.getP1d().setText("<html>" + "DECK" + "<br>" + " " + player1.getField().getDeck().getDeck().size());
		BS.getP2d().setText("<html>" + "DECK" + "<br>" + " " +  player2.getField().getDeck().getDeck().size());
		BS.repaint();

	}

}

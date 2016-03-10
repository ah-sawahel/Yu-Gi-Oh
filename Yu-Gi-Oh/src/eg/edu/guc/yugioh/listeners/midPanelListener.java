package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.gui.BattleScreen;

public class midPanelListener implements ActionListener{

	Board board;
	BattleScreen BS;
	Updated updater;
	
	public midPanelListener(Board b, BattleScreen bs, Player p1, Player p2, handListeners hs){
		board = b;
		BS = bs;
		updater = new Updated(b, bs, p1, p2, hs);
	}
	
	public void addActionListeners(){
		
		BS.getMid().getPhase().addActionListener(this);
		BS.getMid().getTurn().addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
	//	System.out.println("********************");
		if(e.getSource().equals(BS.getMid().getPhase())){
			board.getActivePlayer().endPhase();
		}
		if(e.getSource().equals(BS.getMid().getTurn())){
	//		System.out.println(board.getActivePlayer().getField().getHand().size());
			board.getActivePlayer().endTurn();
		}
		updater.update();
	}

}

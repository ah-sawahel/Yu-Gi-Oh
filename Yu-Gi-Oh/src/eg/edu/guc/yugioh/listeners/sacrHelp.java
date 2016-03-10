package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.gui.BattleScreen;
import eg.edu.guc.yugioh.gui.MonstersButton;

public class sacrHelp implements ActionListener{

	MonstersButton sacr1;
	MonstersButton sacr2;
	ArrayList<MonsterCard> sacrifices;
	MonsterCard temp;
	Board board;
	BattleScreen BS;

	public sacrHelp(MonsterCard Temp, BattleScreen bs, Board b){
		temp = Temp;
		board = b;
		BS = bs;
		sacrifices = new ArrayList<MonsterCard>();
	}

	public void actionPerformed(ActionEvent e) {
		if(sacr1 == null){
			Object[] options = { "OK", "CANCEL" };
			int ok = JOptionPane.showOptionDialog(null, "Please choose a monster to sacrifice!", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
			if(ok == 0){
				if(e.getSource() instanceof MonstersButton){
					sacr1 = (MonstersButton) e.getSource();
					int indSacr1 = BS.getP1().getM().getMonstersButtons().indexOf(e.getSource());
					//		System.out.println(FC);
					//		System.out.println(e.getSource());
					sacrifices.add(board.getActivePlayer().getField().getMonstersArea().get(indSacr1));
				}
			}

		}
		if(temp.getLevel()>6){
			Object[] options = { "OK", "CANCEL" };

			int ok2 = JOptionPane.showOptionDialog(null, "Please choose another monster to sacrifice!", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
			if(ok2 == 0){
				if(e.getSource() instanceof MonstersButton){
					sacr2 = (MonstersButton) e.getSource();
					int indSacr2 = BS.getP1().getM().getMonstersButtons().indexOf(e.getSource());
					//		System.out.println(FC);
					//		System.out.println(e.getSource());
					sacrifices.add(board.getActivePlayer().getField().getMonstersArea().get(indSacr2));
				}	
			}
		}
		board.getActivePlayer().summonMonster(temp, sacrifices);
	}
}

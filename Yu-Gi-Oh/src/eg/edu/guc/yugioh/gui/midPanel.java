package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class midPanel extends JPanel{
	private JButton phase;
	private JButton turn;
	private JButton currPhase;
	private JButton currPlayer;
	
	public JButton getPhase() {
		return phase;
	}

	public JButton getCurrPlayer() {
		return currPlayer;
	}

	public void setCurrPlayer(JButton currPlayer) {
		this.currPlayer = currPlayer;
	}

	public void setPhase(JButton phase) {
		this.phase = phase;
	}

	public JButton getTurn() {
		return turn;
	}

	public void setTurn(JButton turn) {
		this.turn = turn;
	}

	public midPanel(){
		super();
		phase = new JButton("End Phase");
		turn = new JButton("End Turn");
		currPhase = new JButton();
		currPhase.setEnabled(false);
		currPhase.setSize(300, 200);
		currPlayer = new JButton();
		currPlayer.setEnabled(false);
		currPlayer.setSize(300, 200);
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(12000, 300));
		phase.setSize(300, 200);
		turn.setSize(300, 200);
		this.add(phase);
		this.add(currPhase);
		this.add(currPlayer);
		this.add(turn);
		setOpaque(true);
		this.setBackground(Color.DARK_GRAY);
		this.setVisible(true);
	}
	
	public JButton getCurrPhase() {
		return currPhase;
	}

	public void setCurrPhase(JButton currPhase) {
		this.currPhase = currPhase;
	}

}

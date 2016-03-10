package eg.edu.guc.yugioh.gui;

import java.awt.Color;

import javax.swing.JPanel;

public class Player2Panel extends JPanel {
	
	private MonstersPanel m;
	private SpellsPanel s;
//	private hand h;
	
	public Player2Panel(){
		super();
		this.setSize(700, 200);
		m = new MonstersPanel();
		s = new SpellsPanel();
	//	h = new hand();
		this.setLayout(null);
		this.add(m);
		this.add(s);
//		this.add(h);
		m.setBounds(0, 100, 650, 100);
		s.setBounds(0, 0, 650, 100);
		setBackground(Color.DARK_GRAY);
	//	h.setBounds(50, 50, 1000, 200);
		setVisible(true);
	}

	public MonstersPanel getM() {
		return m;
	}

	public void setM(MonstersPanel m) {
		this.m = m;
	}

	public SpellsPanel getS() {
		return s;
	}

	public void setS(SpellsPanel s) {
		this.s = s;
	}

//	public hand getH() {
//		return h;
//	}

//	public void setH(hand h) {
//		this.h = h;
//	}
	

}

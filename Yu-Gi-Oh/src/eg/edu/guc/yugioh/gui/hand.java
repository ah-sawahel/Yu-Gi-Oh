package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class hand extends JPanel{
	private ArrayList<JButton> hand;
	public hand(){
		super();
		hand = new ArrayList<JButton>();
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(1200, 300));
		setBackground(Color.DARK_GRAY);
	//	this.add(new JButton("!@312"));
		this.setVisible(true);
	}
	public ArrayList<JButton> getHand() {
		return hand;
	}
	public void setHand(ArrayList<JButton> hand) {
		this.hand = hand;
		this.removeAll();
		for (int i = 0; i < hand.size(); i++) {
	    //	System.out.println(i);
			
			hand.get(i).setVisible(true);
			this.add(hand.get(i));
		}
		this.setVisible(true);
		this.validate();
	}
}

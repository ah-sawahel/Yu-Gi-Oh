package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MonstersPanel extends JPanel {
	private ArrayList<MonstersButton> monstersButtons;
	
	public MonstersPanel(){
		super();
		this.setPreferredSize(new Dimension(700, 100));
		this.setLayout(new GridLayout(1,5));
		this.setVisible(true);
		
		setMonstersButtons(new ArrayList<MonstersButton>());
		
		for (int i=0; i<5;i++) {
			MonstersButton mButton= new MonstersButton();
			monstersButtons.add(mButton);
			this.add(mButton);
		}

	}
	

	public ArrayList<MonstersButton> getMonstersButtons() {
		return monstersButtons;
	}

	public void setMonstersButtons(ArrayList<MonstersButton> monstersButtons) {
		this.monstersButtons = monstersButtons;
	}


}
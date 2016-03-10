package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class SpellsPanel extends JPanel {
	private ArrayList<SpellsButton> spellsButtons;
	
	public SpellsPanel(){
		super();
		this.setPreferredSize(new Dimension(700, 100));
		this.setLayout(new GridLayout(1,5));
		this.setVisible(true);

		setSpellsButtons(new ArrayList<SpellsButton>());
		

		for (int i=0; i<5;i++) {
			SpellsButton sButton= new SpellsButton();
			spellsButtons.add(sButton);
			this.add(sButton);
		}
	}
	

	public ArrayList<SpellsButton> getSpellsButtons() {
		return spellsButtons;
	}

	public void setSpellsButtons(ArrayList<SpellsButton> spellsButtons) {
		this.spellsButtons = spellsButtons;
	}

}
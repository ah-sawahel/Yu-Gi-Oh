package eg.edu.guc.yugioh.gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.listeners.Controller;

public class FirstWindow extends JFrame 
{
	private JLabel background;
	private JButton start;
	private JTextArea p1;
	private JTextArea p2;
	private BattleScreen battleScreen;
	
	public FirstWindow() {
		background = new JLabel();
		start = new JButton();
		start.setText("Start Game");
		start.setBounds(500, 540, 200, 100);
		p1 = new JTextArea("Please enter 1st player's name");
		p2 = new JTextArea("Please enter 2nd player's name");
		
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String p1n = p1.getText();
				String p2n = p2.getText();
				try {
				//	battleScreen = new BattleScreen(p1n, p2n);
					new Controller(p1n, p2n);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnexpectedFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				 
			
			}
		});
		p1 = new JTextArea(p1.getText());
		p1.setBounds(400, 450, 400, 20);
		p2 = new JTextArea(p2.getText());
		p2.setBounds(400, 500, 400, 20);
		this.getContentPane().add(p1);
		this.getContentPane().add(p2);
		setSize(2000,1000);
		JLabel myLabel = new JLabel("Player 1: ");
		getContentPane().add(myLabel);
		WindowDestroyer myListener = new WindowDestroyer();
		addWindowListener(myListener);
		this.getContentPane().setLayout(null);
		background = new JLabel();
		background.setBounds(0,-4600, 10000, 10000);
		background.setIcon(new ImageIcon("start.jpg"));
		getContentPane().add(start);
		this.add(background);
		
	//	getContentPane().setBackground(Color.DARK_GRAY);
		
		this.setVisible(true);
		this.validate();

		
	//	getContentPane().add(new JLabel(new ImageIcon("C:/Users/farah_000/Desktop/start.jpg")));
	//	this.setVisible(true);
	}
	

	public JButton getStart() {
		return start;
	}


	public void setStart(JButton start) {
		this.start = start;
	}


	public JTextArea getP1name() {
		return p1;
	}


	public void setP1(JTextArea p1) {
		this.p1 = p1;
	}


	public JTextArea getP2name() {
		return p2;
	}


	public void setP2(JTextArea p2) {
		this.p2 = p2;
	}


	public static void main(String[] args) {
		FirstWindow fw = new FirstWindow();
		fw.add(new hand());
	}
}
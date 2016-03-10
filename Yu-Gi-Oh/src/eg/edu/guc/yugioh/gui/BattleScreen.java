package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Dimension2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class BattleScreen extends JFrame {
	
	private Player1Panel p1;
	private Player2Panel p2;
	private JButton p1d;
	private JButton p2d;
	private JButton p1gy;
	private JButton p2gy;
	private JButton p1name;
	private JButton p2name;
	private JButton p1Life;
	private JButton p2Life;
	private hand h1;
	private hand h2;
	private midPanel mid;

	
	
	public BattleScreen(String p1n, String p2n){
		
		super();
		this.setSize(1200, 800);
		this.setLayout(null);

		p1 = new Player1Panel();
		this.add(p1);
		p1.setBounds(150, 400, 700, 200);
		p1d = new JButton("Deck");
		this.add(p1d);
		p1d.setBounds(870, 400, 150, 200);
		p1gy = new JButton("Grave Yard");
		p1gy.setEnabled(false);
		this.add(p1gy);
		p1gy.setBounds(1030, 400, 150, 200);
		p1name = new JButton(p1n);
		p1name.setEnabled(false);
		this.add(p1name);
		p1name.setBounds(30, 550, 100, 50);
		p1name.setText(p1n);
		p1Life = new JButton("8000");
		this.add(p1Life);
		p1Life.setBounds(30, 450, 100, 50);
		p2 = new Player2Panel();
		this.add(p2);
		p2.setBounds(150, 150, 700, 200);
		p2d = new JButton("Deck");
		this.add(p2d);
		p2d.setBounds(870, 150, 150, 200);
		p2gy = new JButton("Grave Yard");
		p2gy.setEnabled(false);
		this.add(p2gy);
		p2gy.setBounds(1030, 150, 150, 200);
		p2name = new JButton(p2n);
		p2name.setEnabled(false);
		p2name.setBounds(30, 150, 100, 50);
		p2name.setText(p2n);
		this.add(p2name);
		p2Life = new JButton("8000");
		this.add(p2Life);
		p2Life.setBounds(30, 250, 100, 50);
		h1 = new hand();
		this.add(getH1());
		getH1().setBounds(0, 600, 1200, 200);
		h2 = new hand();
		
		this.add(getH2());
		getH2().setBounds(0, 0, 1200, 200);

		setMid(new midPanel());
		this.add(getMid());
		getMid().setBounds(0, 360, 1000, 500);
		

		getContentPane().setBackground(Color.DARK_GRAY);
		this.setVisible(true);
		this.validate();
	}
	
	public JButton getP1d(){
		return this.p1d;
	}
	
	public JButton getP2d(){
		return this.p2d;
	}
	
	public void setP1d(JButton p1d){
		this.p1d = p1d;
	}
	
	public void setP2d(JButton p2d){
		this.p2d = p2d;
	}
	
	public JButton getP1gy(){
		return this.p1gy;
	}
	
	public JButton getP2gy(){
		return this.p2gy;
	}
	
	public void setP1gy(JButton p1gy){
		this.p1gy = p1gy;
	}
	
	public void setP2gy(JButton p2gy){
		this.p2gy = p2gy;
	}
	
	public JButton getP1name() {
		return p1name;
	}

	public void setP1name(JButton p1name) {
		this.p1name = p1name;
	}

	public JButton getP2name() {
		return p2name;
	}

	public void setP2name(JButton p2name) {
		this.p2name = p2name;
	}

	public JButton getP1Life() {
		return p1Life;
	}

	public void setP1Life(JButton p1Life) {
		this.p1Life = p1Life;
		p1Life.setVisible(true);
		p1Life.validate();
		p1Life.repaint();
	}

	public JButton getP2Life() {
		return p2Life;
	}

	public void setP2Life(JButton p2Life) {
		this.p2Life = p2Life;
		p2Life.setVisible(true);
		p2Life.validate();
		p2Life.repaint();
	}
	
	public Player1Panel getP1() {
		return p1;
	}

	public void setP1(Player1Panel p1) {
		this.p1 = p1;
	}

	public Player2Panel getP2() {
		return p2;
	}

	public void setP2(Player2Panel p2) {
		this.p2 = p2;
	}

	public hand getH1() {
		return h1;
	}

	public void setH1(hand h1) {
		this.h1 = h1;
	}

	public hand getH2() {
		return h2;
	}

	public void setH2(hand h2) {
		this.h2 = h2;
	}

	public midPanel getMid() {
		return mid;
	}

	public void setMid(midPanel mid) {
		this.mid = mid;
	}


	

}

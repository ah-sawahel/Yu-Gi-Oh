package eg.edu.guc.yugioh.gui;

import javax.swing.*;
import java.awt.event.*;
public class WindowDestroyer extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0); } }
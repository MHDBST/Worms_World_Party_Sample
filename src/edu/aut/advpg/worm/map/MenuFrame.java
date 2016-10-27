package edu.aut.advpg.worm.map;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import edu.aut.advpg.worm.run.GameRunner;

public class MenuFrame extends JFrame implements KeyListener {
	Menu menu = new Menu(this);

	public MenuFrame() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(d.width, d.height);
		setLocation(0, 0);
		addKeyListener(this);
		setUndecorated(true);
		setVisible(true);
		getContentPane().add(menu);
		new Thread(menu).start();
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && menu.num < 3) {
			menu.repaint();
			if (menu.num != 1)
				menu.num++;

		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
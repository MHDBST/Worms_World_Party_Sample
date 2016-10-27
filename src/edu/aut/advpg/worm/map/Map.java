package edu.aut.advpg.worm.map;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.aut.advpg.worm.run.GameRunner;

public class Map extends JFrame implements KeyListener {

	public MapPanel map;

	public Map(int width, int height, boolean showGrid, int mod, GameRunner gmrn) {
		setSize(1366, 768);
		setBackground(Color.BLUE);
		setLayout(null);
		map = new MapPanel(width, height, showGrid, mod, this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		getContentPane().add(map);
		addKeyListener(map.shot);
		addKeyListener(this);

	}

	public MapPanel getMap() {
		return map;
	}

	public void setMap(MapPanel map) {
		this.map = map;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			String[] options = { "Exit", "Suddendeath" };

			int choice = JOptionPane.showOptionDialog(null,
					"Waht do you wanna do? ", "Game Exit",
					JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.WARNING_MESSAGE, null, options, options[1]);

			switch (choice) {
			case 0:
				System.exit(0);
				break;
			case 1:
				map.yWater -= 120;
				MapModel.getInstance().setYWater(map.yWater);
				for (int i = 0; i < MapModel.getInstance().noOfWorms; i++)
					MapModel.getInstance().playerAWorms[i].checkPosition();
				System.out.println(MapModel.getInstance().getYWater());
				break;

			}

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}

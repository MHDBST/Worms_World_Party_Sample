package edu.aut.advpg.worm.run;

import java.awt.Color;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import edu.aut.advpg.worm.map.Map;
import edu.aut.advpg.worm.map.MapModel;
import edu.aut.advpg.worm.map.MenuFrame;

public class GameRunner extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private int numA = 0, numB = 0;
	public Map gameMap;
	private JButton[] mapbutton;
	private int mod;
	private static int clientNumber = 0;

	public GameRunner(int mod) {
		setLayout(null);
		setSize(1366, 800);
		setTitle("Worm Game");
		setLocation(0, 0);
		setUndecorated(true);
		setBackground(Color.black);

		this.mod = mod;

		JButton mapbutton1 = new JButton("Mission 1", new ImageIcon(
				"data/maps/mission1-icon.png"));
		mapbutton1.setSize(200, 200);
		mapbutton1.setLocation(0, 0);

		mapbutton1.addActionListener(new ActionListener() {
			String loading = new String("mission1");

			@Override
			public void actionPerformed(ActionEvent e) {
				startGame(loading);
			}
		});

		JButton mapbutton2 = new JButton("Mission 2", new ImageIcon(
				"data/maps/mission2-icon.png"));
		mapbutton2.setSize(200, 200);
		mapbutton2.setLocation(210, 0);

		mapbutton2.addActionListener(new ActionListener() {
			String loading = new String("mission2");

			@Override
			public void actionPerformed(ActionEvent e) {
				startGame(loading);
			}
		});

		JButton mapbutton3 = new JButton("Mission 3", new ImageIcon(
				"data/maps/mission3-icon.png"));
		mapbutton3.setSize(200, 200);
		mapbutton3.setLocation(420, 0);

		mapbutton3.addActionListener(new ActionListener() {
			String loading = new String("mission3");

			@Override
			public void actionPerformed(ActionEvent e) {
				startGame(loading);
			}
		});

		JButton mapbutton4 = new JButton("Mission 4", new ImageIcon(
				"data/maps/mission4-icon.png"));
		mapbutton4.setSize(200, 200);
		mapbutton4.setLocation(640, 0);

		mapbutton4.addActionListener(new ActionListener() {
			String loading = new String("mission4");

			@Override
			public void actionPerformed(ActionEvent e) {
				startGame(loading);
			}
		});

		JButton mapbutton5 = new JButton("Mission 5", new ImageIcon(
				"data/maps/mission5-icon.png"));
		mapbutton5.setSize(200, 200);
		mapbutton5.setLocation(0, 400);
		mapbutton5.addActionListener(new ActionListener() {
			String loading = new String("mission5");

			@Override
			public void actionPerformed(ActionEvent e) {
				startGame(loading);
			}
		});

		JButton mapbutton6 = new JButton("Mission 6", new ImageIcon(
				"data/maps/mission6-icon.png"));
		mapbutton6.setSize(200, 200);
		mapbutton6.setLocation(210, 400);
		mapbutton6.addActionListener(new ActionListener() {
			String loading = new String("mission6");

			@Override
			public void actionPerformed(ActionEvent e) {
				startGame(loading);
			}
		});

		JButton mapbutton7 = new JButton("Mission 7", new ImageIcon(
				"data/maps/mission7-icon.png"));
		mapbutton7.setSize(200, 200);
		mapbutton7.setLocation(420, 400);
		mapbutton7.addActionListener(new ActionListener() {
			String loading = new String("mission7");

			@Override
			public void actionPerformed(ActionEvent e) {
				startGame(loading);
			}
		});

		JButton mapbutton8 = new JButton("Mission 8", new ImageIcon(
				"data/maps/mission8-icon.png"));
		mapbutton8.setSize(200, 200);
		mapbutton8.setLocation(640, 400);
		mapbutton8.addActionListener(new ActionListener() {
			String loading = new String("mission8");

			@Override
			public void actionPerformed(ActionEvent e) {
				startGame(loading);
			}
		});
		this.getContentPane().add(mapbutton1);
		this.getContentPane().add(mapbutton2);
		this.getContentPane().add(mapbutton3);
		this.getContentPane().add(mapbutton4);
		this.getContentPane().add(mapbutton5);
		this.getContentPane().add(mapbutton6);
		this.getContentPane().add(mapbutton7);
		this.getContentPane().add(mapbutton8);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		KeyboardFocusManager manager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new MyDispatcher());
		setVisible(true);

	}

	public GameRunner(int mod, int[][] mapData, Image mapTexture) {
		setLayout(null);
		setSize(1366, 800);
		setTitle("Worm Game");
		setLocation(0, 0);
		setUndecorated(true);
		setBackground(Color.black);

		this.mod = mod;

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		KeyboardFocusManager manager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new MyDispatcher());
		setVisible(true);

		this.getContentPane().removeAll();

		MapModel.getInstance().load(mapData, mapTexture);
		gameMap = new Map(getWidth(), getHeight(), false, mod, this);
		new Thread(this).start();
		dispose();
	}

	protected void startGame(String loading) {
		this.getContentPane().removeAll();
		MapModel.getInstance().load(loading);
		gameMap = new Map(getWidth(), getHeight(), false, mod, this);
		new Thread(this).start();
		dispose();
	}

	public static void main(String[] args) {
		// new MenuFrame();
		new GameRunner(2);

	}

	private class MyDispatcher implements KeyEventDispatcher {
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			{
				if (gameMap != null)
					if (gameMap.getMap().getClientNumber() == 0) {

						if (e.getID() == KeyEvent.KEY_PRESSED) {
							MapModel.getInstance().actionForActiveWorm(
									e.getKeyCode());
						} else if (e.getID() == KeyEvent.KEY_RELEASED) {
						} else if (e.getID() == KeyEvent.KEY_TYPED) {
						}
					}
			}
			{
				if (gameMap != null)
					if (gameMap.getMap().getClientNumber() == 1)
						if (e.getID() == KeyEvent.KEY_PRESSED) {
							MapModel.getInstance().actionForActiveWorm(
									e.getKeyCode());
						} else if (e.getID() == KeyEvent.KEY_RELEASED) {
						} else if (e.getID() == KeyEvent.KEY_TYPED) {
						}
			}
			return false;
		}
	}

	@Override
	public void run() {
		while (true) {

			if /* while */(MapModel.getInstance().isA()
					&& (gameMap.getMap().getClientNumber() == 0)) {
				{
					numA = MapModel.getInstance().getActiveWormIndex();
					try {
						Thread.sleep(45000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int i = 1;
					numA++;
					numA %= MapModel.getInstance().noOfWorms;
					while (MapModel.getInstance().playerAWorms[numA].getLife() == 0) {
						numA++;
						i++;
						// if (i == 3) {
						// JOptionPane.showMessageDialog(null, null,
						// "Game Lost", 0);
						// System.exit(0);
						// }
						numA %= MapModel.getInstance().noOfWorms;

					}
					MapModel.getInstance().playerAWorms[(numA)].checkPosition();
					MapModel.getInstance().setActiveWormIndex(numA);
					// if (mod == 2) {
					// gameMap.getMap().setClientNumber(1);
					// // break;
					// }

				}
			} else if
			/* while */(!MapModel.getInstance().isA()
					&& (gameMap.getMap().getClientNumber() == 1)) {
				{
					numB = MapModel.getInstance().getActiveWormIndex();
					synchronized (this) {

						try {
							wait(45000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					numB++;
					numB %= MapModel.getInstance().noOfWorms;
					while (MapModel.getInstance().playerBWorms[numB].getLife() == 0) {
						numB++;
						numB %= MapModel.getInstance().noOfWorms;
					}
					MapModel.getInstance().playerBWorms[(numB)].checkPosition();
					MapModel.getInstance().setActiveWormIndex(numB);

					// if (mod == 2) {
					// gameMap.getMap().setClientNumber(0);
					// // break;
					// }
				}

			}

		}

	}
}

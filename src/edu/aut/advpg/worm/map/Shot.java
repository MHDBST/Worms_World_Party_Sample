package edu.aut.advpg.worm.map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Shot implements Runnable, KeyListener {

	protected int xS, xH;
	protected int yS, yH;
	protected int numS = 0;
	protected int point1X, point1Y;
	protected int point2X, point2Y;
	protected int point3X, point3Y;
	protected int mousX = 0, mousY = 0;
	protected boolean airStrike, ds = false;
	private MapPanel map;
	protected JPanel hadaf;
	protected boolean sPressed, sReleased, teleport = true;
	private long st = 0, end = 0, length;
	protected int[] keyP = new int[12];
	private int numG = 1;
	private JButton shot1;
	private JButton shot2;
	private JButton shot3;
	private JLabel panel;
	private String name;
	private ImageIcon imageShot;
	private int k = 1;

	ImageIcon[] shotsImag = new ImageIcon[5];

	public Shot(MapPanel map) {
		this.map = map;
		sPressed = false;
		sReleased = false;
		if (map.clientNumber == 0) {
			xS = MapModel.getInstance().playerAWorms[MapModel.getInstance()
					.getActiveWormIndex()].getX();
			yS = MapModel.getInstance().playerAWorms[MapModel.getInstance()
					.getActiveWormIndex()].getY();
			xH = xS;
			yH = yS - 5;

		} else if (map.clientNumber == 1) {
			xS = MapModel.getInstance().playerBWorms[MapModel.getInstance()
					.getActiveWormIndex()].getX();
			yS = MapModel.getInstance().playerBWorms[MapModel.getInstance()
					.getActiveWormIndex()].getY();
			xH = xS;
			yH = yS - 5;

		}
		k = wormsDir();

		keyP[0] = 0;

		for (int i = 1; i < 12; i++)
			keyP[i] = 0;

		hadaf = new JPanel();
		hadaf.setLocation(xH, yH);
		hadaf.setSize(10, 10);

		name = new String("Bazooka");

		airStrike = false;

		shotsImag[0] = new ImageIcon("bazooka.png");
		shotsImag[1] = new ImageIcon("grenade.png");
		shotsImag[2] = new ImageIcon("handgun.png");

		imageShot = new ImageIcon();
		;

		map.add(hadaf);

	}

	public int getX() {
		return xS;
	}

	public int getY() {
		return yS;
	}

	public void setX(int xS) {
		this.xS = xS;
	}

	public void setY(int yS) {
		this.yS = yS;
	}

	@Override
	public void run() {
		if ((numG == 1) || (numG == 2)) {// bazook,grenade
			int x0 = xS;
			int y0 = yS;
			double v = (double) length / 100;
			double vatar = Math.sqrt((Math.pow((xS - xH), 2))
					+ (Math.pow(yS - yH, 2)));
			double sinT = (double) (yH - yS) / vatar;
			double cosT = (double) (k * (xH - xS)) / vatar;
			int t = 0;
			double a = 2;
			boolean line = true;
			int l = xS;
			int m = yS + 4;
			if (yS + 4 < 139)
				while (MapModel.getInstance().gamePlan[l][m] == 0) {
					int y2 = yS;
					try {
						Thread.sleep(200);
						while (yS != yH && xS != xH && line) {
							if ((xH != x0))
								yS = y0 + ((yH - y0) / (xH - x0)) + (x0 - xS);
							else {
								if (yH > y0)
									yS--;
								else
									yS++;
							}
							yS = 2 * y0 - yS;
							xS += k;
							setX(xS);
							setY(yS);
						}
						line = false;
						int y1 = yH;
						int x1 = xH;
						yS = (int) (0.5 * a * t * t + v * sinT * t + y1);
						xS = (int) (v * cosT * t + x1);
						t += k;
						boolean br = false;
						for (int i = y2; i < yS; i++)
							if (i < 140 && xS < 400 && i > 0) {
								if (MapModel.getInstance().gamePlan[xS][i] == 1) {
									br = true;
									yS = i - 1;
									break;
								}
							} else
								break;
						if (br)
							break;

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (yS + 4 > 139)
						break;
					l = xS - 5;
					m = yS - 5;
					if (l < 0)
						l = 0;
					if (m < 0)
						m = 0;

				}
			ds = true;
			map.xDestroy.add(xS - 5);
			map.yDestroy.add(yS - 5);
			map.radius.add((int) (length));
			destroy(2);
			newWorm();
			resetShot();

		}
		if (numG == 3) {// handgun
			k = wormsDir();
			double a = (double) (yS - yH) / ((xS - xH));
			double b = yS - a * xS;
			int t = xS;
			int limitX = xS;
			int limitY = yS;
			for (int i = 0; i < 3; i++) {
				t = limitX;
				yS = limitY;
				xS = limitX;
				int l = xS;
				int m = yS + k * 2;

				while (((xS < limitX + 45 && k == 1) || (xS > limitX - 45 && k == -1))
						&& MapModel.getInstance().gamePlan[l][m] == 0) {

					int y2 = yS;
					try {
						Thread.sleep(50);
						setX(xS);
						setY(yS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!Double.isInfinite(a)) {
						yS = (int) (a * t + b);
						t += k;
						xS = t;
					} else if (a == Double.POSITIVE_INFINITY)
						yS -= 5;
					else if (a == Double.NEGATIVE_INFINITY)
						yS += 5;
					if (yS < 0)
						yS = 0;

					boolean br = false;
					for (int j = y2; j < yS; j++)
						if (MapModel.getInstance().gamePlan[xS][j] == 1) {
							br = true;
							yS = j - 1;
							break;
						}
					if (br)
						break;
					l = xS;
					m = yS + k * 2;
					if (l < 0)
						l = 0;
					if (m < 0)
						m = 0;
				}
				ds = false;
				length = 900;
				map.xDestroyGun.add(xS - 5);
				map.yDestroyGun.add(yS - 5);
				destroy(3);
			}
			ds = true;
			setX(xS);
			setY(yS);
			length = 900;
			map.xDestroyGun.add(xS - 5);
			map.yDestroyGun.add(yS - 5);
			destroy(3);
			newWorm();
			resetShot();
		}
		if (numG == 4) {// shotgun
			k = wormsDir();
			numS++;
			double a = (double) (yS - yH) / (xS - xH);
			double b = yS - a * xS;
			int t = xS;
			int limitX = xS;
			int limitY = yS;
			setX(xS);
			setY(yS);
			int l = xS, m = yS + 4;
			while (((xS < limitX + 25 && k == 1) || (xS > limitX - 25 && k == -1))
					&& yS < limitY + 25
					&& MapModel.getInstance().gamePlan[l][m] == 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!Double.isInfinite(a)) {
					yS = (int) (a * t + b);
					t += k;
					xS = t;
				} else if (a == Double.POSITIVE_INFINITY)
					yS -= 5;
				else if (a == Double.NEGATIVE_INFINITY)
					yS += 5;
				length = 600;
				setX(xS);
				setY(yS);

				l = xS;
				m = yS + 4;
				if (l < 0)
					l = 0;
				if (m < 0)
					m = 0;
			}

			if (numS != 2) {
				sReleased = false;
				sPressed = false;

			}
			map.xDestroyGun.add(xS - 5);
			map.yDestroyGun.add(yS - 5);
			destroy(3);

			if (numS == 2) {
				ds = true;

				newWorm();
				resetShot();
			}
			t = limitX;
			yS = limitY;
			xS = limitX;
		}
		if (numG == 5) {// teleport
			map.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (teleport) {
						mousX = e.getX();
						mousY = e.getY();
						if (MapModel.getInstance().isA()) {
							MapModel.getInstance().playerAWorms[MapModel
									.getInstance().getActiveWormIndex()]
									.setX((e.getX() / 5 + MapModel
											.getInstance().getStartX()));
							MapModel.getInstance().playerAWorms[MapModel
									.getInstance().getActiveWormIndex()].setY(e
									.getY() / 5);
							MapModel.getInstance().playerAWorms[MapModel
									.getInstance().getActiveWormIndex()]
									.checkPosition();
						} else {
							MapModel.getInstance().playerBWorms[MapModel
									.getInstance().getActiveWormIndex()]
									.setX((e.getX() / 5 + MapModel
											.getInstance().getStartX()));
							MapModel.getInstance().playerBWorms[MapModel
									.getInstance().getActiveWormIndex()].setY(e
									.getY() / 5);
							MapModel.getInstance().playerBWorms[MapModel
									.getInstance().getActiveWormIndex()]
									.checkPosition();

						}
						sReleased = true;
						ds = true;
						resetShot();
						newWorm();
						teleport = false;
					}
				}
			});

		}
		if (numG == 6) {// air strike
			shot1 = new JButton();
			shot2 = new JButton();
			shot3 = new JButton();

			shot1.setSize(25, 25);
			shot2.setSize(25, 25);
			shot3.setSize(25, 25);

			map.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent arg0) {

				}

				@Override
				public void mousePressed(MouseEvent arg0) {

				}

				@Override
				public void mouseExited(MouseEvent arg0) {

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					sReleased = true;
					final int y = e.getY();
					mousY = e.getYOnScreen();// getY();
					point1X = (int) (e.getX() / MapModel.gridsize + MapModel
							.getInstance().getStartX());
					point1Y = 0;
					point2X = (int) ((e.getX() - 100) / MapModel.gridsize + MapModel
							.getInstance().getStartX());
					point2Y = 0;
					point3X = (int) ((e.getX() + 100) / MapModel.gridsize + MapModel
							.getInstance().getStartX());
					point3Y = 0;

					Thread t = new Thread() {
						public void run() {
							while ((point1Y < (mousY / MapModel.getInstance().gridsize)
									&& (point2Y) < (mousY / MapModel
											.getInstance().gridsize) && (point3Y) < (mousY / MapModel
									.getInstance().gridsize))

									&& ((MapModel.getInstance().gamePlan[point1X][point1Y + 1] != 1)
											|| (MapModel.getInstance().gamePlan[point2X][point2Y + 1] != 1) || (MapModel
											.getInstance().gamePlan[point3X][point3Y + 1] != 1))) {
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								point1Y++;
								point2Y++;
								point3Y++;
								if (MapModel.getInstance().gamePlan[point1X][point1Y] == 1)
									point1Y--;
								if (MapModel.getInstance().gamePlan[point2X][point2Y] == 1)
									point2Y--;

								if (MapModel.getInstance().gamePlan[point3X][point3Y] == 1)
									point3Y--;

							}
							airStrike = false;

						}
					};
					t.start();
				}
			});
			newWorm();
			resetShot();

		}

	}

	private int wormsDir() {
		if (MapModel.getInstance().isA()) {
			if (!MapModel.getInstance().playerAWorms[MapModel.getInstance()
					.getActiveWormIndex()].isDirection())
				return -1;
			else
				return 1;
		} else if (!MapModel.getInstance().isA())
			if (!MapModel.getInstance().playerBWorms[MapModel.getInstance()
					.getActiveWormIndex()].isDirection())
				return -1;
			else
				return 1;
		return 1;
	}

	private void destroy(int l) {
		for (int i = 0; i < 1366; i++)
			for (int j = 0; j < 768; j++)
				if ((i - xS - 25) * (i - xS - 25) + (j - yS - 25)
						* (j - yS - 25) < ((length) / 10) * ((length) / 10))
					MapModel.getInstance().gamePlan[i / 5][j / 5] = 0;

		for (int k = 0; k < MapModel.getInstance().noOfWorms; k++)
			for (int i = -(int) (length / 50) - l * 5; i < (int) (length / 50 + l * 5); i++)
				for (int j = -(int) (length / 50) - l * 5; j < (int) (length / 50 + l * 5); j++) {
					if ((MapModel.getInstance().playerAWorms[k].getX() == xS
							+ i)
							&& (MapModel.getInstance().playerAWorms[k].getY() == yS
									+ j)
							&& (MapModel.getInstance().playerAWorms[k]
									.getLife() > 0)) {
						MapModel.getInstance().playerAWorms[k]
								.setLife((MapModel.getInstance().playerAWorms[k]
										.getLife()) - 10);
						MapModel.getInstance().playerAWorms[k].startJump();
					}
					if ((MapModel.getInstance().playerBWorms[k].getX() == xS
							+ i)
							&& (MapModel.getInstance().playerBWorms[k].getY() == yS
									+ j)
							&& (MapModel.getInstance().playerBWorms[k]
									.getLife() > 0)) {
						MapModel.getInstance().playerBWorms[k]
								.setLife((MapModel.getInstance().playerBWorms[k]
										.getLife()) - 10);
						MapModel.getInstance().playerBWorms[k].startJump();
					}
				}

	}

	private void resetShot() {
		for (int i = 0; i < 7; i++)
			keyP[i] = 0;
		numG = 1;

	}

	private void newWorm() {
		int i = 1;
		if (map.clientNumber == 0) {
			while (MapModel.getInstance().playerAWorms[(MapModel.getInstance()
					.getActiveWormIndex() + i)
					% MapModel.getInstance().noOfWorms].getLife() <= 0) {
				i++;
				if (i == 4) {
					JOptionPane.showMessageDialog(null, null, "Game Lost", 0);
					System.exit(0);
				}
			}
			MapModel.getInstance().setActiveWormIndex(
					(MapModel.getInstance().getActiveWormIndex() + i)
							% MapModel.getInstance().noOfWorms);
		} else if (map.clientNumber == 1) {
			while (MapModel.getInstance().playerBWorms[(MapModel.getInstance()
					.getActiveWormIndex() + i)
					% MapModel.getInstance().noOfWorms].getLife() <= 0) {
				i++;
				if (i == 4) {
					JOptionPane.showMessageDialog(null, null, "Game Lost", 0);
					System.exit(0);
				}
			}
			MapModel.getInstance().setActiveWormIndex(
					(MapModel.getInstance().getActiveWormIndex() + i)
							% MapModel.getInstance().noOfWorms);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_F1) {
			numG = 1;
			imageShot.setImage(shotsImag[0].getImage());
			name = "Bazooka";
		}
		if (e.getKeyCode() == KeyEvent.VK_F2) {
			numG = 2;
			imageShot.setImage(shotsImag[1].getImage());
			name = "Grenade";
		}
		if (e.getKeyCode() == KeyEvent.VK_F3) {

			numG = 3;
			imageShot.setImage(shotsImag[2].getImage());
			name = "Handgun";

		}
		if (e.getKeyCode() == KeyEvent.VK_F4) {
			numG = 4;
			imageShot.setImage(shotsImag[2].getImage());
			name = "ShotGun";

		}
		if (e.getKeyCode() == KeyEvent.VK_F6) {
			numG = 6;
			name = "AirStrike";
		}
		if (e.getKeyCode() == KeyEvent.VK_F5) {
			numG = 5;
			name = "Teleport";
			new Thread(this).start();
		}

		if (e.getKeyCode() == KeyEvent.VK_UP && sPressed == false) {
			if (MapModel.getInstance().isA())
				if (!MapModel.getInstance().playerAWorms[MapModel.getInstance()
						.getActiveWormIndex()].isDirection())
					k = -1;
				else
					k = 1;
			else {
				if (!MapModel.getInstance().playerBWorms[MapModel.getInstance()
						.getActiveWormIndex()].isDirection())
					k = -1;
				else
					k = 1;
			}

			yH -= 1;
			if (Math.sqrt(25 - Math.pow(yH - yS, 2)) >= 0)
				xH = ((int) Math.sqrt(25 - Math.pow(yH - yS, 2)) * k) + xS;
			else
				yH += 1;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN && sPressed == false) {
			if (MapModel.getInstance().isA())
				if (!MapModel.getInstance().playerAWorms[MapModel.getInstance()
						.getActiveWormIndex()].isDirection())
					k = -1;
				else
					k = 1;
			else {
				if (!MapModel.getInstance().playerBWorms[MapModel.getInstance()
						.getActiveWormIndex()].isDirection())
					k = -1;
				else
					k = 1;
			}
			yH += 1;
			if (Math.sqrt(25 - Math.pow(yH - yS, 2)) >= 0)
				xH = ((int) Math.sqrt(25 - Math.pow(yH - yS, 2)) * k) + xS;
			else
				yH -= 1;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT
				|| e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_BACK_SPACE
				|| e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (map.clientNumber == 0) {
				setX(MapModel.getInstance().playerAWorms[MapModel.getInstance()
						.getActiveWormIndex()].getX());
				setY(MapModel.getInstance().playerAWorms[MapModel.getInstance()
						.getActiveWormIndex()].getY());
				xH = MapModel.getInstance().playerAWorms[MapModel.getInstance()
						.getActiveWormIndex()
						% MapModel.getInstance().noOfWorms].getX();
				yH = MapModel.getInstance().playerAWorms[MapModel.getInstance()
						.getActiveWormIndex()].getY() - 5;
			} else if (map.clientNumber == 1) {
				setX(MapModel.getInstance().playerBWorms[MapModel.getInstance()
						.getActiveWormIndex()].getX());
				setY(MapModel.getInstance().playerBWorms[MapModel.getInstance()
						.getActiveWormIndex()].getY());
				xH = MapModel.getInstance().playerBWorms[MapModel.getInstance()
						.getActiveWormIndex()
						% MapModel.getInstance().noOfWorms].getX();
				yH = MapModel.getInstance().playerBWorms[MapModel.getInstance()
						.getActiveWormIndex()].getY() - 5;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE && sPressed == false) {

			sPressed = true;
			st = System.currentTimeMillis();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && sReleased == false) {
			hadaf.setVisible(false);
			end = System.currentTimeMillis();
			length = end - st;
			sReleased = true;
			new Thread(this).start();
			// if (ds) {
			// newWorm();
			// }

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public String getName() {
		String nameS = name.toString();
		return nameS;
	}

	public ImageIcon getImage() {

		return imageShot;
	}

}

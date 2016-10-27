package edu.aut.advpg.worm.map;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Worm implements Runnable {
	private int x;
	private int y;
	private int life = 50;
	public boolean direction;
	boolean jumpingEnter;

	private ImageIcon rightIcon = new ImageIcon("data/wormright.png");
	private ImageIcon leftIcon = new ImageIcon("data/wormleft.png");
	private ImageIcon deadWorm = new ImageIcon("dead0.png");
	private ImageIcon deadWorm2 = new ImageIcon("dead2.png");
	private final int index;
	private boolean jumping;

	public Worm(int index, int x, int y) {
		jumping = false;
		life = 100;
		this.x = x;
		this.y = y;
		this.index = index;
		setX(x);
		setY(y);
	}

	public int getIndex() {
		return index;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public Image getImage() {
		if (life == 0) {
			if ((MapModel.getInstance().playerAWorms[(MapModel.getInstance()
					.getActiveWormIndex() + 2) % 3].getY()
					* MapModel.getInstance().gridsize >= MapModel.getInstance()
					.getYWater() && MapModel.getInstance().isA())
					|| (MapModel.getInstance().playerBWorms[(MapModel
							.getInstance().getActiveWormIndex() + 2) % 3]
							.getY() >= MapModel.getInstance().getYWater() && !MapModel
							.getInstance().isA())) {
				return deadWorm.getImage();
			} else {
				return deadWorm2.getImage();
			}
		}
		if (direction) // right
			return rightIcon.getImage();
		return leftIcon.getImage();
	}

	public void moveLeft() {
		direction = false;
		if (OkToGoLeft()) {
			x--;
			setX(x);
		}
		if (!jumping)
			checkPosition();
	}

	private boolean OkToGoLeft() {
		// checkPosition();
		int step = 0;
		for (int i = 7; i >= 0; i--) {
			if ((x - 1) > 0 && (y + i) > 0 && (y + i) < 140)
				if (MapModel.getInstance().gamePlan[x - 1][y + i] == 1)
					step++;
		}
		if (step > 7)
			return false;
		y -= step;
		return true;
	}

	public void moveRight() {
		// checkPosition();
		direction = true;
		if (OkToGoRight())
			x++;
		if (!jumping)
			checkPosition();
	}

	private boolean OkToGoRight() {
		int step = 0;
		for (int i = 7; i >= 0; i--) {
			if ((x + 8) > 0 && (y + i) > 0)
				if ((y + i) < 140 /* && (x + 8) < 140 && (y + i) < 140 */)
					if (MapModel.getInstance().gamePlan[x + 8][y + i] == 1)
						step++;
		}
		if (step > 7)
			return false;
		y -= step;
		return true;
	}

	public void checkPosition() {
		if (y < 132)
			if ((y) * MapModel.getInstance().gridsize >= MapModel.getInstance()
					.getYWater()) {
				life = 0;
				// y += 10;
				MapModel.getInstance().setActiveWormIndex(
						(MapModel.getInstance().getActiveWormIndex() + 1) % 3);
			}
		if (y < 132 && y >= 0)
			while (MapModel.getInstance().gamePlan[x][y + 7] == 0) {
				y++;
				if (y + 7 > 140)
					break;
				if ((y) * MapModel.getInstance().gridsize >= MapModel
						.getInstance().getYWater()) {
					life = 0;
					Thread t = new Thread() {
						@Override
						public void run() {
							while (y - 15 < MapModel.getInstance().mapHeight) {
								y++;
								try {
									Thread.sleep(50);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					};
					t.start();
					// y += 10;
					MapModel.getInstance()
							.setActiveWormIndex(
									(MapModel.getInstance()
											.getActiveWormIndex() + 1) % 3);
					break;
				}
			}
	}

	public void startJumpingEnter() {

		if (!jumpingEnter) {
			jumpingEnter = true;
			Thread t = new Thread() {
				@Override
				public void run() {

					for (int i = 0; i < 14; i++) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
						}
						if (i < 7) {
							if (x + 8 < 400 && (y - 8) > 0)
								if (direction
										&& MapModel.getInstance().gamePlan[x + 8][y - 8] == 0) {
									x++;
									y--;
								}
							if (x - 1 > 0 && y - 8 > 0)
								if (!direction
										&& MapModel.getInstance().gamePlan[x - 1][y - 8] == 0) {
									y--;
									x--;
								}
						} else {
							if (y + 8 < 139 && x + 8 < 400) {
								if (direction
										&& MapModel.getInstance().gamePlan[x + 8][y + 8] == 0) {
									y++;
									x++;
								}
							}
							if (x - 1 > 0 && y + 8 < 139)

								if (!direction
										&& MapModel.getInstance().gamePlan[x - 1][y + 8] == 0) {
									y++;
									x--;
								}
						}
					}
					checkPosition();
					jumpingEnter = false;
				}
			};
			t.start();
		}

	}

	public void startJump() {
		checkPosition();
		if (!jumping) {
			jumping = true;
			new Thread(this).start();
		}
	}

	@Override
	public void run() {
		for (int i = 0; i < 14; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			if (i < 7) {
				if (x > 0 && (y - 1) > 0)
					if (MapModel.getInstance().gamePlan[x][y - 1] == 0)
						y--;
			} else {
				y++;
				if (y + 8 < 140)
					if (MapModel.getInstance().gamePlan[x][y + 8] != 0)
						break;
			}
		}
		jumping = false;
	}

}

package edu.aut.advpg.worm.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

public class MapModel {

	private static MapModel instance;

	public static final int mapWidth = 400;
	public static final int mapHeight = 140;
	public static final int gridsize = 5;
	private static boolean A/* , B */;

	public int[][] gamePlan = new int[mapWidth][mapHeight];
	private Image mapImage;

	public static final int noOfWorms = 3;

	public Worm[] playerAWorms = new Worm[noOfWorms];
	public Worm[] playerBWorms = new Worm[noOfWorms];
	public int activeWormIndex = 0;

	private int startX = 50;

	private int yWater = 650;

	public static MapModel getInstance() {
		if (instance == null)
			instance = new MapModel();
		return instance;
	}

	public MapModel() {
		for (int j = 0; j < mapHeight; j++) {
			for (int i = 0; i < mapWidth; i++) {
				gamePlan[i][j] = 0;
			}
		}
		A = true;
	}

	public void setMapImage(Image image) {
		this.mapImage = image;
	}

	public Image getMapImage() {
		return mapImage;
	}

	public int getStartXPosition() {
		return startX * gridsize;
	}

	public int getStartX() {
		return startX;
	}

	public void ScrollRight() {
		startX -= 1;
	}

	public void ScrollLeft() {
		startX += 1;
	}

	public void autoGenerateMap() {
		for (int j = 0; j < mapHeight; j++) {
			for (int i = 0; i < mapWidth; i++) {
				gamePlan[i][j] = recognizePixelAt(i, j);
			}
		}
	}

	private int recognizePixelAt(int i, int j) {
		int notBlack = 0;
		int[] pixelBuffer = new int[gridsize * gridsize];
		PixelGrabber pg = new PixelGrabber(getMapImage(), i * gridsize, j
				* gridsize, gridsize, gridsize, pixelBuffer, 0, gridsize);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int p = 0; p < gridsize * gridsize; p++) {
			Color c = new Color(pixelBuffer[p]);
			if (!c.equals(Color.BLACK))
				notBlack++;
		}
		if (notBlack / (gridsize * gridsize) > .3)
			return 1;
		return 0;
	}

	public boolean isA() {
		if (A)
			return true;
		return false;
	}

	public static void setA(boolean a) {
		A = a;
	}

	public int getActiveWormIndex() {
		return (activeWormIndex % MapModel.getInstance().noOfWorms);
	}

	public void setActiveWormIndex(int activeWormIndex) {
		this.activeWormIndex = activeWormIndex;
	}

	public void load(int[][] mapData, Image mapTexture) {
		BufferedImage bufferedImage = new BufferedImage(2000, 700, 1);
		Graphics graphics = bufferedImage.getGraphics();
		for (int j = 0; j < mapHeight; j++) {
			for (int i = 0; i < mapWidth; i++) {
				if (mapData[i][j] == 1)
					graphics.drawImage(mapTexture, i * gridsize, j * gridsize,
							gridsize, gridsize, null);
				gamePlan[i][j] = mapData[i][j];
			}
		}
		setMapImage(bufferedImage);
		for (int i = 0; i < noOfWorms; i++) {
			playerAWorms[i] = new Worm(i, i * 20 + 120, 0);
			playerBWorms[i] = new Worm(i, 200 + i * 30, 0);
			playerAWorms[i].checkPosition();
			playerBWorms[i].checkPosition();
		}
	}

	public void load(String mapName) {
		try {
			FileReader fileReader = new FileReader("data/maps/" + mapName
					+ ".map");
			for (int j = 0; j < mapHeight; j++) {
				for (int i = 0; i < mapWidth; i++) {
					gamePlan[i][j] = Integer.valueOf(fileReader.read()) - 48;
				}
				fileReader.read();
				fileReader.read();
			}
			ImageIcon map = new ImageIcon("data/maps/" + mapName + ".png");
			setMapImage(map.getImage());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < noOfWorms; i++) {
			playerAWorms[i] = new Worm(i, i * 20 + 120, 0);
			playerBWorms[i] = new Worm(i, 200 + i * 30, 0);
			playerAWorms[i].checkPosition();
			playerBWorms[i].checkPosition();
		}

	}

	public void actionForActiveWorm(int keyCode) {
		if (playerAWorms[MapModel.getInstance().getActiveWormIndex()].getLife() > 0
				&& isA()) {
			switch (keyCode) {
			case KeyEvent.VK_LEFT:
				playerAWorms[MapModel.getInstance().getActiveWormIndex()]
						.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				playerAWorms[MapModel.getInstance().getActiveWormIndex()]
						.moveRight();
				break;
			case KeyEvent.VK_BACK_SPACE:
				playerAWorms[MapModel.getInstance().getActiveWormIndex()]
						.startJump();
				break;
			case KeyEvent.VK_ENTER:
				playerAWorms[MapModel.getInstance().getActiveWormIndex()]
						.startJumpingEnter();
				break;

			default:
				break;
			}

		} else if (playerBWorms[getActiveWormIndex()].getLife() > 0 && !isA()) {
			switch (keyCode) {
			case KeyEvent.VK_LEFT:
				playerBWorms[activeWormIndex].moveLeft();

				break;
			case KeyEvent.VK_RIGHT:
				playerBWorms[activeWormIndex].moveRight();
				break;
			case KeyEvent.VK_BACK_SPACE:
				playerBWorms[activeWormIndex].startJump();
				break;
			case KeyEvent.VK_ENTER:
				playerBWorms[MapModel.getInstance().getActiveWormIndex()]
						.startJumpingEnter();
				break;

			default:
				break;

			}
		}
	}

	public void setYWater(int yWater) {
		this.yWater = yWater;

	}

	public int getYWater() {
		// TODO Auto-generated method stub
		return yWater;
	}
}

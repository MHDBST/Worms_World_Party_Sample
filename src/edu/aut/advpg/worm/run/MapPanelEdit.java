package edu.aut.advpg.worm.run;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.util.Random;

import javax.swing.JPanel;

public class MapPanelEdit extends JPanel implements Runnable {

	private static final int BRUSH = 1;
	private static final int FILL = 2;
	private static final int TUNNEL = 3;

	private final int width = 400;
	private final int height = 140;

	int[][] map = new int[width][height];
	private int brushSize;
	private int clickType;
	private int scale;
	private BufferedImage bufferedImage;

	public MapPanelEdit() {
		bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		brushSize = 2;
		scale = 4;
		setSize(width, height);
		setBackground(Color.black);
		clear();
		new Thread(this).start();

		addMouseListener(new MouseListener() {

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
				switch (clickType) {
				case FILL:
					porkon(e.getX(), e.getY());
					updateBufferImage(2);
					break;
				case BRUSH:
					bekesh(e.getX(), e.getY());
					break;
				case TUNNEL:
					betunnel(e.getX(), e.getY());
					// updateBufferImage(2);
					break;
				}
			}
		});

		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				switch (clickType) {
				case FILL:
					porkon(e.getX(), e.getY());
					updateBufferImage(2);
					break;
				case BRUSH:
					bekesh(e.getX(), e.getY());
					break;
				case TUNNEL:
					betunnel(e.getX(), e.getY());
					// updateBufferImage(2);
					break;
				}
			}
		});
	}

	protected void betunnel(int x, int y) {
		for (int i = 0; i < scale; i++) {
			for (int j = 0; j < scale; j++) {
				map[x + i][y + j] = 1;
				map[x + 1 * scale + i][y + j] = 1;
				map[x + 2 * scale + i][y + j] = 1;
				map[x + 3 * scale + i][y + j] = 1;
				map[x + 3 * scale + i][y + 1 * scale + j] = 1;
				map[x + 3 * scale + i][y + 2 * scale + j] = 1;
				map[x + 3 * scale + i][y = 3 * scale + j] = 1;
				map[x + 4 * scale + i][y + j] = 1;
				map[x + 5 * scale + i][y + j] = 1;
				map[x + 6 * scale + i][y + j] = 1;
				map[x + 6 * scale + i][y + 1 * scale + j] = 1;
				map[x + 6 * scale + i][y + 2 * scale + j] = 1;
				map[x + 6 * scale + i][y + 3 * scale + j] = 1;
				map[x + 6 * scale + i][y + 4 * scale + j] = 1;
				map[x + 5 * scale + i][y + 4 * scale + j] = 1;
				map[x + 4 * scale + i][y + 4 * scale + j] = 1;
				map[x + 7 * scale + i][y + j] = 1;
				map[x + 8 * scale + i][y + j] = 1;
				map[x + 9 * scale + i][y + j] = 1;
				map[x + 10 * scale + i][y + j] = 1;
				map[x + 10 * scale + i][y + 1 * scale + j] = 1;
				map[x + 10 * scale + i][y + 2 * scale + j] = 1;
				map[x + 10 * scale + i][y + 3 * scale + j] = 1;
				map[x + 9 * scale + i][y + 3 * scale + j] = 1;
				map[x + 8 * scale + i][y + 3 * scale + j] = 1;
				map[x + 7 * scale + i][y + 3 * scale + j] = 1;
			}
		}
		Graphics g = bufferedImage.getGraphics();
		g.fillOval(x, y, scale, scale);
		g.fillOval(x + 1, y, scale, scale);
		g.fillOval(x + 2, y, scale, scale);
		g.fillOval(x + 3, y, scale, scale);
		g.fillOval(x + 4, y, scale, scale);
		g.fillOval(x + 5, y, scale, scale);
		g.fillOval(x + 6, y, scale, scale);
		g.fillOval(x + 7, y, scale, scale);
		g.fillOval(x + 8, y, scale, scale);
		g.fillOval(x + 9, y, scale, scale);
		g.fillOval(x + 10, y, scale, scale);

	}

	protected void porkon(int x, int y) {
		map[x][y] = 1;
		if (x - 1 > width || y - 1 > height)
			return;
		if (x + 1 < 400)
			if (map[x + 1][y] == 0)
				porkon(x + 1, y);
		if (x > 0)
			if (map[x - 1][y] == 0)
				porkon(x - 1, y);
		if (y + 1 < 140)
			if (map[x][y + 1] == 0)
				porkon(x, y + 1);
		if (y > 0)
			if (map[x][y - 1] == 0)
				porkon(x, y - 1);
	}

	protected void bekesh(int x, int y) {
		Graphics g = bufferedImage.getGraphics();
		g.setColor(Color.WHITE);
		for (int i = 0; i < brushSize * scale; i++) {
			for (int j = 0; j < brushSize * scale; j++) {
				if (y + j >= 0 && x + i >= 0)
					map[x + i][y + j] = 1;
			}
		}
		g.fillOval(x, y, brushSize * scale, brushSize * scale);
	}

	private void updateBufferImage(int s) {
		Graphics g = bufferedImage.getGraphics();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (map[i][j] == 1)
					g.setColor(Color.WHITE);
				else
					g.setColor(Color.BLACK);
				g.fillOval(i, j, s, s);
			}
		}
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		graphics.drawImage(bufferedImage, 0, 0, width, height, null);
	}

	public void load(Image mapImage) {
		PixelGrabber pg = new PixelGrabber(mapImage, 0, 0, -1, -1, false);
		try {
			pg.grabPixels();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				map[i][j] = recognizePixelAt((int[]) pg.getPixels(), i, j);
			}
		}
		updateBufferImage(2);
	}

	private int recognizePixelAt(int[] pixelBuffer, int i, int j) {
		Color c = new Color(pixelBuffer[j * width + i]);
		if (c.equals(Color.white))
			return 1;
		return 0;
	}

	public void reverse() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				map[i][j] = 1 - map[i][j];
			}
		}
		updateBufferImage(2);
	}

	public void brush(int f) {
		clickType = BRUSH;
		brushSize = f;
	}

	public void fill() {
		clickType = FILL;
	}

	public void circle() {
		Random rnd = new Random();
		for (int m = 0; m < 4; m++) {
			int radius = rnd.nextInt(5) + 2;
			int x = rnd.nextInt(width);
			int y = rnd.nextInt(height);
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if ((i - x) * (i - x) + (j - y) * (j - y) < radius * radius)
						map[i][j] = 1;
				}
			}
		}
		updateBufferImage(2);
	}

	public void clear() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				map[i][j] = 0;
			}
		}
		updateBufferImage(2);
	}

	public void rotate() {

	}

	public void undo() {
		// TODO Auto-generated method stub

	}

	public void tunnel() {
		clickType = TUNNEL;
	}

	public void border() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

}

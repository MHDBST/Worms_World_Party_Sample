package edu.aut.advpg.worm.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class MapPanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	private JLabel rightScroll;
	private JLabel leftScroll;
	private JLabel water;

	public Shot shot;
	private int xS, yS;
	public int clientNumber = 0;
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	private String c;
	boolean dic[] = new boolean[3];
	private String timer = new String("45");
	private String life[] = new String[3];
	private int xShotOther, xHadafOther;
	private int yShotOther, yHadafOther;
	protected int yWater = 650;
	private int numOther;
	private int[] xWormOther;
	private int[] yWormOther;
	private int[] lif;
	private int mod;
	public int tmr;
	private int num1 = 0;
	private Map gameMap;
	protected boolean leftScrollActivate;
	protected boolean rightScrollActivate;
	private final boolean showGrid;
	private ImageIcon[] wat = new ImageIcon[10];
	private ImageIcon hadaf = new ImageIcon("hadaf.png");
	protected ArrayList<Integer> xDestroy;
	protected ArrayList<Integer> yDestroy;
	protected ArrayList<Integer> radius;
	protected ArrayList<Integer> xDestroyGun;
	protected ArrayList<Integer> yDestroyGun;

	public MapPanel(int width, int height, boolean showGrid, int mod,
			Map gameMap) {
		this.showGrid = showGrid;
		this.gameMap = gameMap;
		setSize(1366, 768);
		setBackground(Color.BLUE);
		setLayout(null);
		for (int i = 1; i < 11; i++) {
			wat[i - 1] = new ImageIcon("water/wat" + i + ".png");
		}

		tmr = 45;

		xWormOther = new int[3];
		yWormOther = new int[3];
		lif = new int[3];
		this.mod = mod;

		xDestroy = new ArrayList<Integer>();
		yDestroy = new ArrayList<Integer>();
		radius = new ArrayList<Integer>();
		xDestroyGun = new ArrayList<Integer>();
		yDestroyGun = new ArrayList<Integer>();

		water = new JLabel();
		water.setLocation(0, yWater);
		water.setSize(2000, 100);
		water.setLayout(null);
		water.setOpaque(true);
		water.setBackground(Color.BLUE);
		add(water);

		MapModel.getInstance().setYWater(yWater);

		int scrollWidth = 100;
		leftScroll = new JLabel();
		leftScroll.setSize(scrollWidth, height);
		leftScroll.setLocation(0, 0);
		leftScroll.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftScrollActivate = false;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				leftScrollActivate = true;
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		add(leftScroll);
		rightScroll = new JLabel();
		rightScroll.setSize(scrollWidth, height);
		rightScroll.setLocation(width - scrollWidth, 0);
		rightScroll.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightScrollActivate = false;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				rightScrollActivate = true;
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		add(rightScroll);

		shot = new Shot(this);

		if (mod == 2) {
			c = new String();
			try {
				client = new Socket("192.168.131.242", 7777);
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				clientNumber = dis.readInt();
				setClientNumber(clientNumber);
				MapModel.setA(clientNumber == 0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		new Thread(this).start();

	}

	public String send() {
		if (MapModel.getInstance().isA()) {
			// String sendX = "khar", sendY = "khar", rad = "khar";
			// if (xDestroy.size() == 0)
			// sendX = "khar";
			// else
			// sendX = "" + xDestroy.get(xDestroy.size() - 1);
			// if (yDestroy.size() == 0)
			// sendY = "khar";
			// else
			// sendY = "" + yDestroy.get(yDestroy.size() - 1);
			// if (radius.size() == 0)
			// rad = "khar";
			// else
			// rad = "" + radius.get(radius.size() - 1);
			// System.out.println(sendX);
			return (xS + "," + yS
					+ MapModel.getInstance().playerAWorms[0].isDirection()
					+ "," + MapModel.getInstance().playerAWorms[0].getX() + ","
					+ MapModel.getInstance().playerAWorms[0].getY() + ","
					+ MapModel.getInstance().playerAWorms[1].isDirection()
					+ "," + MapModel.getInstance().playerAWorms[1].getX() + ","
					+ MapModel.getInstance().playerAWorms[1].getY() + ","
					+ MapModel.getInstance().playerAWorms[2].isDirection()
					+ "," + MapModel.getInstance().playerAWorms[2].getX() + ","
					+ MapModel.getInstance().playerAWorms[2].getY() + ","
					+ MapModel.getInstance().playerAWorms[0].getLife() + ","
					+ MapModel.getInstance().playerAWorms[1].getLife() + ","
					+ MapModel.getInstance().playerAWorms[2].getLife() + ";");
		}
		// String sendX, sendY, rad;
		// if (xDestroy.size() == 0)
		// sendX = null;
		// else
		// sendX = xDestroy.get(xDestroy.size() - 1) + ",";
		// if (yDestroy.size() == 0)
		// sendY = null;
		// else
		// sendY = yDestroy.get(yDestroy.size() - 1) + ",";
		// if (radius.size() == 0)
		// rad = null;
		// else
		// rad = radius.get(radius.size() - 1) + ",";
		return (xS + "," + yS + ","
				+ MapModel.getInstance().playerBWorms[0].isDirection() + ","
				+ MapModel.getInstance().playerBWorms[0].getX() + ","
				+ MapModel.getInstance().playerBWorms[0].getY() + ","
				+ MapModel.getInstance().playerBWorms[1].isDirection() + ","
				+ MapModel.getInstance().playerBWorms[1].getX() + ","
				+ MapModel.getInstance().playerBWorms[1].getY() + ","
				+ MapModel.getInstance().playerBWorms[2].isDirection() + ","
				+ MapModel.getInstance().playerBWorms[2].getX() + ","
				+ MapModel.getInstance().playerBWorms[2].getY() + ","
				+ MapModel.getInstance().playerBWorms[0].getLife() + ","
				+ MapModel.getInstance().playerBWorms[1].getLife() + ","
				+ MapModel.getInstance().playerBWorms[2].getLife() + ";");
	}

	public int getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		super.paint(g);
		g.drawImage(MapModel.getInstance().getMapImage(), 0, 0, getWidth(),
				getHeight(), MapModel.getInstance().getStartXPosition(), 0,
				MapModel.getInstance().getStartXPosition() + getWidth(),
				getHeight(), null);

		g.setColor(Color.black);
		for (int i = 0; i < yDestroy.size(); i++) {
			g.fillOval((xDestroy.get(i) - MapModel.getInstance().getStartX())
					* MapModel.getInstance().gridsize, yDestroy.get(i)
					* MapModel.getInstance().gridsize, radius.get(i) / 10,
					radius.get(i) / 10);
		}
		for (int i = 0; i < yDestroyGun.size(); i++) {
			g.fillRect((xDestroyGun.get(i) + 9 - MapModel.getInstance()
					.getStartX())
					* MapModel.getInstance().gridsize, (yDestroyGun.get(i) + 9)
					* MapModel.getInstance().gridsize, 50, 10);
		}
		for (int z = yWater; z < 800; z += 10)
			for (int k = 0; k < 1300; k += 256) {
				for (int r = num1; r < num1 + 10; r++)
					g.drawImage(wat[(num1) % 10].getImage(), k + 128
							* (r - num1), z, null);
			}
		if (showGrid)
			showGrid(g);
		if (!showGrid) {

			for (int i = 0; i < MapModel.noOfWorms; i++) {
				g.setColor(Color.ORANGE);
				if (MapModel.getInstance().playerAWorms[i].getX() > MapModel
						.getInstance().getStartX()
						&& MapModel.getInstance().playerAWorms[i].getX() < MapModel
								.getInstance().getStartX()
								+ getWidth() / MapModel.gridsize
						&& MapModel.getInstance().isA()) {
					g
							.drawImage(MapModel.getInstance().playerAWorms[i]
									.getImage(),
									(MapModel.getInstance().playerAWorms[i]
											.getX() - MapModel.getInstance()
											.getStartX())
											* MapModel.gridsize, MapModel
											.getInstance().playerAWorms[i]
											.getY()
											* MapModel.gridsize, 40, 40, null);
					if (MapModel.getInstance().playerAWorms[i].getLife() > 0) {

						g
								.drawString(
										""
												+ MapModel.getInstance().playerAWorms[i]
														.getLife(), (MapModel
												.getInstance().playerAWorms[i]
												.getX() - MapModel
												.getInstance().getStartX())
												* MapModel.gridsize, MapModel
												.getInstance().playerAWorms[i]
												.getY()
												* MapModel.gridsize - 20);

						g.fillRect((MapModel.getInstance().playerAWorms[i]
								.getX() - MapModel.getInstance().getStartX())
								* MapModel.gridsize,
								MapModel.getInstance().playerAWorms[i].getY()
										* MapModel.gridsize - 20, MapModel
										.getInstance().playerAWorms[i]
										.getLife() / 2, 15);
					}

				}
			}
			g.setColor(Color.blue);
			if (mod == 2) {
				g.drawImage(shot.getImage().getImage(), (xShotOther - MapModel
						.getInstance().getStartX())
						* MapModel.gridsize, yShotOther * MapModel.gridsize,
						null);
				g.drawImage(hadaf.getImage(), (xHadafOther - MapModel
						.getInstance().getStartX())
						* MapModel.gridsize, yHadafOther * MapModel.gridsize,
						null);
				for (int i = 0; i < MapModel.getInstance().noOfWorms; i++) {
					if (MapModel.getInstance().playerBWorms[i].getX() > MapModel
							.getInstance().getStartX()
							&& MapModel.getInstance().playerBWorms[i].getX() < MapModel
									.getInstance().getStartX()
									+ getWidth() / MapModel.gridsize
							&& !MapModel.getInstance().isA()) {

						g
								.drawImage(
										MapModel.getInstance().playerBWorms[i]
												.getImage(), (MapModel
												.getInstance().playerBWorms[i]
												.getX() - MapModel
												.getInstance().getStartX())
												* MapModel.gridsize, MapModel
												.getInstance().playerBWorms[i]
												.getY()
												* MapModel.gridsize, 40, 40,
										null);
						g
								.drawString(
										""
												+ MapModel.getInstance().playerBWorms[i]
														.getLife(), (MapModel
												.getInstance().playerBWorms[i]
												.getX() - MapModel
												.getInstance().getStartX())
												* MapModel.gridsize, MapModel
												.getInstance().playerBWorms[i]
												.getY()
												* MapModel.gridsize - 20);

						g.fillRect((MapModel.getInstance().playerBWorms[i]
								.getX() - MapModel.getInstance().getStartX())
								* MapModel.gridsize,
								MapModel.getInstance().playerBWorms[i].getY()
										* MapModel.gridsize - 20, MapModel
										.getInstance().playerBWorms[i]
										.getLife() / 2 + 5, 15);
					}
				}
				if (MapModel.getInstance().isA())
					for (int i = 0; i < MapModel.noOfWorms; i++) {
						MapModel.getInstance().playerBWorms[i]
								.setDirection(dic[i]);
						g.drawImage(MapModel.getInstance().playerBWorms[i]
								.getImage(), (xWormOther[i] - MapModel
								.getInstance().getStartX())
								* MapModel.gridsize, yWormOther[i]
								* MapModel.gridsize, 40, 40, null);
						g.drawString("" + lif[i], (xWormOther[i] - MapModel
								.getInstance().getStartX())
								* MapModel.gridsize, yWormOther[i]
								* MapModel.gridsize - 20);
						g.setColor(Color.blue);
						g.fillRect((xWormOther[i] - MapModel.getInstance()
								.getStartX())
								* MapModel.gridsize, yWormOther[i]
								* MapModel.gridsize - 20, MapModel
								.getInstance().playerAWorms[i].getLife() / 2,
								15);

					}
				else if (!MapModel.getInstance().isA()) {
					g.setColor(Color.orange);
					for (int i = 0; i < MapModel.noOfWorms; i++) {
						MapModel.getInstance().playerAWorms[i]
								.setDirection(dic[i]);
						g.drawImage(
								MapModel.getInstance().playerAWorms[numOther]
										.getImage(), (xWormOther[i] - MapModel
										.getInstance().getStartX())
										* MapModel.gridsize, yWormOther[i]
										* MapModel.gridsize, 40, 40, null);

						g.drawString("" + lif[i], (xWormOther[i] - MapModel
								.getInstance().getStartX())
								* MapModel.gridsize, yWormOther[i]
								* MapModel.gridsize - 20);

						g.fillRect((xWormOther[i] - MapModel.getInstance()
								.getStartX())
								* MapModel.gridsize, yWormOther[i]
								* MapModel.gridsize - 20, MapModel
								.getInstance().playerAWorms[i].getLife() / 2,
								15);
					}
					g.setColor(Color.black);
				}
			}

		}
		num1++;
		if (num1 == 10)
			num1 = 0;

		// khode tir
		g.drawImage(shot.getImage().getImage(), (xS - MapModel.getInstance()
				.getStartX())
				* MapModel.gridsize, yS * MapModel.gridsize, null);
		// hadafe
		g.drawImage(hadaf.getImage(), (shot.xH - MapModel.getInstance()
				.getStartX())
				* MapModel.gridsize, shot.yH * MapModel.gridsize, null);
		// esme tir
		g.setFont(new Font("Comic Sans MS", 1, 25));
		g.setColor(Color.red);
		g.drawString(shot.getName(), 700, 20);
//		new Sound(shot.getName() + "Explode.WAV").playSound();

		g.setFont(new Font("Comic Sans MS", 1, 18));
		g.setColor(Color.green);
		g.drawString("You are player no " + clientNumber, 10, 20);

		// timer
		g.fillRect(0, 600, 50, 50);
		g.setColor(Color.black);
		g.setFont(new Font("Comic Sans MS", 2, 25));
		g.drawString(timer, 10, 635);

	}

	private void showGrid(Graphics g) {
		g.setColor(Color.white);
		for (int i = 0; i < MapModel.mapWidth; i++) {
			g.drawLine(i * MapModel.gridsize, 0, i * MapModel.gridsize,
					getHeight());
		}
		for (int i = 0; i < MapModel.mapHeight; i++) {
			g.drawLine(0, i * MapModel.gridsize, getWidth(), i
					* MapModel.gridsize);
		}
	}

	@Override
	public void run() {
		int num = 0;
		while (true) {

			if (mod == 2) {
				byte b[] = new byte[100];
				String message = null;
				try {
						dos.write(send().getBytes());
					
					dos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					dis.read(b);
					message = (new String(b)).trim();
				} catch (IOException e) {
					e.printStackTrace();
				}

				StringTokenizer wholeMsgTokenizer = new StringTokenizer(
						message, ";");

				while (wholeMsgTokenizer.hasMoreTokens()) {
					String xyc = wholeMsgTokenizer.nextToken();
					StringTokenizer st = new StringTokenizer(xyc, ",");
					if (st.countTokens() >= 14) {
						// String desX = st.nextToken();
						// String desY = st.nextToken();
						// String rad = st.nextToken();
						String xShot = st.nextToken();
						String yShot = st.nextToken();
						String dic1 = st.nextToken();
						String x1 = st.nextToken();
						String y1 = st.nextToken();
						String dic2 = st.nextToken();
						String x2 = st.nextToken();
						String y2 = st.nextToken();
						String dic3 = st.nextToken();
						String x3 = st.nextToken();
						String y3 = st.nextToken();
						life[0] = st.nextToken();
						life[1] = st.nextToken();
						life[2] = st.nextToken();
						if ("true".equals(dic1))
							dic[0] = true;
						else
							dic[0] = false;
						if ("true".equals(dic2))
							dic[1] = true;
						else
							dic[1] = false;
						if ("true".equals(dic3))
							dic[2] = true;
						else
							dic[2] = false;
						xShotOther = Integer.parseInt(xShot);
						yShotOther = Integer.parseInt(yShot);
						// xHadafOther = Integer.parseInt(xHad);
						// yHadafOther = Integer.parseInt(yHad);
						// if ("true".equals(dic1))
						// dic[0] = true;
						// else
						// dic[0] = false;
						xWormOther[0] = Integer.parseInt(x1);
						xWormOther[1] = Integer.parseInt(x2);
						xWormOther[2] = Integer.parseInt(x3);
						yWormOther[0] = Integer.parseInt(y1);
						yWormOther[1] = Integer.parseInt(y2);
						yWormOther[2] = Integer.parseInt(y3);
						lif[0] = Integer.parseInt(life[0]);
						lif[1] = Integer.parseInt(life[1]);
						lif[2] = Integer.parseInt(life[2]);
					}
				}
			}
			if (shot.sReleased) {
				xS = shot.getX();
				yS = shot.getY();
			} else {
				if (MapModel.getInstance().isA()) {
					xS = (MapModel.getInstance().playerAWorms[MapModel
							.getInstance().getActiveWormIndex()].getX());
					yS = (MapModel.getInstance().playerAWorms[MapModel
							.getInstance().getActiveWormIndex()].getY());
				} else {
					xS = (MapModel.getInstance().playerBWorms[MapModel
							.getInstance().getActiveWormIndex()].getX());
					yS = (MapModel.getInstance().playerBWorms[MapModel
							.getInstance().getActiveWormIndex()].getY());
				}
			}

			num++;
			if (num == 10) {

				num = 0;
				tmr = Integer.parseInt(timer);
				tmr--;
				if ((tmr < 0)
						|| (shot.ds && shot.sReleased)
						|| (MapModel.getInstance().isA() && (MapModel
								.getInstance().playerAWorms[MapModel
								.getInstance().getActiveWormIndex()].getLife() == 0))
						|| (!MapModel.getInstance().isA() && (MapModel
								.getInstance().playerBWorms[MapModel
								.getInstance().getActiveWormIndex()].getLife() == 0))) {
					tmr = 45;
					shot = new Shot(this);
					gameMap.addKeyListener(gameMap.map.shot);
					repaint();
				}
				timer = "" + tmr;

			}

			if (MapModel.getInstance().getMapImage() != null) {
				if (leftScrollActivate
						&& MapModel.getInstance().getStartXPosition() > 0) {
					MapModel.getInstance().ScrollRight();
				}
				if (rightScrollActivate
						&& MapModel.getInstance().getStartXPosition()
								+ getWidth() < MapModel.getInstance()
								.getMapImage().getWidth(null)) {
					MapModel.getInstance().ScrollLeft();
				}
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();

		}
	}

}

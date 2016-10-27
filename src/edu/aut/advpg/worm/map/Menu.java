package edu.aut.advpg.worm.map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.org.apache.xpath.internal.operations.Bool;

import edu.aut.advpg.worm.run.GameRunner;
import edu.aut.advpg.worm.run.MapEdit;
import edu.aut.advpg.worm.run.Server;

public class Menu extends JPanel implements Runnable {
	private ImageIcon[] start = new ImageIcon[6];
	private ImageIcon[] world = new ImageIcon[20];
	private ImageIcon[] cloud = new ImageIcon[4];
	private ImageIcon[] up = new ImageIcon[4];
	private ImageIcon[] cancel = new ImageIcon[4];
	protected int num, num2, xC1, xC2, xC3, xC4, yC1, yC2, yC3, yC4;
	private Dimension d;
	private ImageIcon backG;
	private MenuFrame panel;
	private JButton[] mnu = new JButton[4];
	private JButton cncl = new JButton();

	public Menu(final MenuFrame panel) {
		setLayout(null);
		this.panel = panel;
		num = 2;
		num2 = 0;
		xC1 = 1300;
		yC1 = 50;
		xC2 = 1400;
		yC2 = 250;
		xC3 = 1500;
		yC3 = 400;
		xC4 = 1000;
		yC4 = 550;

		d = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(d.width+" "+d.height);
		setSize(d.width, d.height);
		setLocation(0, 0);

		up[0] = new ImageIcon("1up.png");
		up[1] = new ImageIcon("2up.png");
		up[2] = new ImageIcon("3up.png");
		up[3] = new ImageIcon("4up.png");

		mnu[0] = new JButton();// up[0]);
		mnu[0].setSize(up[0].getIconWidth(), up[0].getIconHeight());
		mnu[0].setLocation(d.width / 2 - 50 - up[0].getIconWidth(), d.height
				/ 2 - up[0].getIconHeight());
		mnu[0].setToolTipText("Play with the computer");
		mnu[0].addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel.dispose();
				new GameRunner(1);

			}
		});

		mnu[1] = new JButton(up[1]);
		mnu[1].setSize(up[1].getIconWidth(), up[1].getIconHeight());
		mnu[1].setLocation(d.width / 2 + 50, d.height / 2
				- up[1].getIconHeight());
		mnu[1].setToolTipText("Haminjury :D");

		mnu[2] = new JButton(up[2]);
		mnu[2].setSize(up[2].getIconWidth(), up[2].getIconHeight());
		mnu[2].setLocation(d.width / 2 - 50 - up[2].getIconWidth(),
				d.height / 2 + 50);
		mnu[2].setToolTipText("Play on the network");
		mnu[2].addMouseListener(new MouseListener() {

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
				panel.dispose();
				new Server();
				new GameRunner(2);

			}
		});

		mnu[3] = new JButton(up[3]);
		mnu[3].setSize(up[3].getIconWidth(), up[3].getIconHeight());
		mnu[3].setLocation(d.width / 2 + 50, d.height / 2 + 50);
		mnu[3].setToolTipText("Option menu");
		mnu[3].addMouseListener(new MouseListener() {

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
				// TODO Auto-generated method stub
				panel.dispose();
				new MapEdit();

			}
		});

		start[0] = new ImageIcon("start1.png");
		start[1] = new ImageIcon("start2.png");
		start[2] = new ImageIcon("start3.png");
		start[3] = new ImageIcon("start4.png");
		start[4] = new ImageIcon("start5.png");

		world[0] = new ImageIcon("world1.png");
		world[1] = new ImageIcon("world2.png");
		world[2] = new ImageIcon("world3.png");
		world[3] = new ImageIcon("world4.png");
		world[4] = new ImageIcon("world5.png");
		world[5] = new ImageIcon("world6.png");
		world[6] = new ImageIcon("world7.png");
		world[7] = new ImageIcon("world8.png");
		world[8] = new ImageIcon("world9.png");
		world[9] = new ImageIcon("world10.png");
		world[10] = new ImageIcon("world11.png");
		world[11] = new ImageIcon("world12.png");
		world[12] = new ImageIcon("world13.png");
		world[13] = new ImageIcon("world14.png");
		world[14] = new ImageIcon("world15.png");
		world[15] = new ImageIcon("world16.png");
		world[16] = new ImageIcon("world17.png");
		world[17] = new ImageIcon("world18.png");
		world[18] = new ImageIcon("world19.png");
		world[19] = new ImageIcon("world20.png");

		cloud[0] = new ImageIcon("cloud1.png");
		cloud[1] = new ImageIcon("cloud2.png");
		cloud[2] = new ImageIcon("cloud3.png");
		cloud[3] = new ImageIcon("cloud4.png");

		cancel[0] = new ImageIcon("cancel0.png");
		cancel[1] = new ImageIcon("cancel1.png");
		cancel[2] = new ImageIcon("cancel2.png");
		cancel[3] = new ImageIcon("cancle3.png");

		cncl = new JButton(cancel[3]);
		cncl.setSize(cancel[3].getIconWidth(), cancel[3].getIconHeight());
		cncl.setLocation(25, d.height - cancel[3].getIconWidth() - 25);
		cncl.setBorderPainted(false);
		cncl.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);

			}
		});

		setVisible(true);

	}

	public void paint(Graphics g) {
		super.paint(g);

		if (num == 0)
			g.drawImage(start[0].getImage(), 0, 0, null);

		else if (num == 1)
			g.drawImage(start[1].getImage(), 0, 0, null);

		else if (num == 2) {

			g.drawImage(start[4].getImage(), 0, 0, null);
			g.drawImage(cloud[0].getImage(), xC1, yC1, null);
			g.drawImage(cloud[1].getImage(), xC2, yC2, null);
			g.drawImage(cloud[2].getImage(), xC3, yC3, null);
			g.drawImage(cloud[3].getImage(), xC4, yC4, null);
			g.drawImage(start[2].getImage(), d.width / 2
					- (start[2].getIconWidth()) / 2, d.height / 2
					- start[2].getIconHeight() / 2, null);
			g.drawImage(start[3].getImage(), d.width / 2
					- (start[3].getIconWidth()) / 2, 50, null);

			switch (num2) {
			case 1:
				g.drawImage(world[0].getImage(), d.width / 2
						- (world[0].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 2:
				g.drawImage(world[1].getImage(), d.width / 2
						- (world[1].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 3:
				g.drawImage(world[2].getImage(), d.width / 2
						- (world[2].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 4:
				g.drawImage(world[3].getImage(), d.width / 2
						- (world[3].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 5:
				g.drawImage(world[4].getImage(), d.width / 2
						- (world[4].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 6:
				g.drawImage(world[5].getImage(), d.width / 2
						- (world[5].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 7:
				g.drawImage(world[6].getImage(), d.width / 2
						- (world[6].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 8:
				g.drawImage(world[7].getImage(), d.width / 2
						- (world[7].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 9:
				g.drawImage(world[8].getImage(), d.width / 2
						- (world[8].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 10:
				g.drawImage(world[9].getImage(), d.width / 2
						- (world[9].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 11:
				g.drawImage(world[10].getImage(), d.width / 2
						- (world[10].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 12:
				g.drawImage(world[11].getImage(), d.width / 2
						- (world[11].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 13:
				g.drawImage(world[12].getImage(), d.width / 2
						- (world[12].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 14:
				g.drawImage(world[13].getImage(), d.width / 2
						- (world[13].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 15:
				g.drawImage(world[14].getImage(), d.width / 2
						- (world[14].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 16:
				g.drawImage(world[15].getImage(), d.width / 2
						- (world[15].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 17:
				g.drawImage(world[16].getImage(), d.width / 2
						- (world[16].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 18:
				g.drawImage(world[17].getImage(), d.width / 2
						- (world[17].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 19:
				g.drawImage(world[18].getImage(), d.width / 2
						- (world[18].getIconWidth()) / 2 - 35, 55, null);
				break;
			case 20:
				g.drawImage(world[19].getImage(), d.width / 2
						- (world[19].getIconWidth()) / 2 - 35, 55, null);
				break;

			}

		} else if (num == 3) {
			g.drawImage(start[4].getImage(), 0, 0, null);

			g.drawImage(cloud[0].getImage(), xC1, yC1, null);
			g.drawImage(cloud[1].getImage(), xC2, yC2, null);
			g.drawImage(cloud[2].getImage(), xC3, yC3, null);
			g.drawImage(cloud[3].getImage(), xC4, yC4, null);
			g.drawImage(start[3].getImage(), d.width / 2
					- (start[3].getIconWidth()) / 2, 50, null);

			g.drawImage(up[0].getImage(), d.width / 2 - 50
					- up[0].getIconWidth(), d.height / 2
					- up[0].getIconHeight(), null);
			g.drawImage(up[1].getImage(), d.width / 2 + 50, d.height / 2
					- up[1].getIconHeight(), null);
			g.drawImage(up[2].getImage(), d.width / 2 - 50
					- up[2].getIconWidth(), d.height / 2 + 50, null);
			g.drawImage(up[3].getImage(), d.width / 2 + 50, d.height / 2 + 50,
					null);

			// g.drawImage(check[0].getImage(), 25, d.height
			// - check[0].getIconWidth() - 25, null);

			g.drawImage(cancel[3].getImage(), 25, d.height
					- cancel[3].getIconWidth() - 25, null);

		}

	}

	@Override
	public void run() {
		ArrayList<Boolean> aa = new ArrayList<Boolean>();
		num = 0;
		while (num < 4) {
			if (num < 2) {

				try {
					Thread.sleep(2000);
					repaint();
					num++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (num == 2) {
				repaint();
				try {
					Thread.sleep(10);
					num2++;
					num2 = num2 % 20;
					xC1 -= 10;
					xC2 -= 10;
					xC3 -= 10;
					xC4 -= 10;
					if (xC1 < -500)
						xC1 = 1400;
					if (xC2 < -500)
						xC2 = 1400;
					if (xC3 < -500)
						xC3 = 1400;
					if (xC4 < -500)
						xC4 = 1400;

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (num == 3) {
				repaint();
				try {
					Thread.sleep(5);
					xC1 -= 10;
					xC2 -= 10;
					xC3 -= 10;
					xC4 -= 10;
					if (xC1 < -500)
						xC1 = 1400;
					if (xC2 < -500)
						xC2 = 1400;
					if (xC3 < -500)
						xC3 = 1400;
					if (xC4 < -500)
						xC4 = 1400;

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				add(cncl);

				for (int i = 0; i < 4; i++) {
					add(mnu[i]);
				}

			}
		}

	}

}

package edu.aut.advpg.worm.run;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MapEdit extends JFrame implements Runnable {

	private int f = 1, y = 0,z=0, p = 0, j = 0, l = 0, n = 0, o = 0, g = 0, v = 0,
			m = 0;
	private int x1 = 20, y1 = 125;
	private int x2 = 20, y2 = 200;
	private boolean con;
	private int x3 = 20, y3 = 275;
	private int x4 = 20, y4 = 350;
	private int x5 = 70, y5 = 125;
	private int x6 = 70, y6 = 200;
	private int x7 = 70, y7 = 275;
	private int x8 = 70, y8 = 350;
	private int x9 = 120, y9 = 125;
	private int x10 = 120, y10 = 200;
	private int x11 = 20, y11 = 425;
	private int x12 = 70, y12 = 425;
	private int x13 = 120, y13 = 425;
	private int x14 = 20, y14 = 490;
	private Thread wat;
	private final JLabel wtr;
	private MapPanelEdit panel;
	private final JLabel icon13;

	public MapEdit() {
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Dimension d = this.getToolkit().getScreenSize();
		setSize(d.width, d.height);
		setLocation(d.width - getSize().width, d.height - getSize().height);
		
		setUndecorated(true);

		panel = new MapPanelEdit();
		panel.setLocation(400, 200);
		getContentPane().add(panel);

		JButton mapE0 = new JButton("map 0", new ImageIcon(
				"mapedit/map0-icon.png"));
		mapE0.setSize(190, 73);
		mapE0.setLocation(300, 30);
		mapE0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.load(new ImageIcon("mapedit/map0.png").getImage());
			}
		});
		add(mapE0);

		JButton mapE1 = new JButton("map 2", new ImageIcon(
				"mapedit/map2-icon.png"));
		mapE1.setSize(190, 73);
		mapE1.setLocation(600, 30);
		mapE1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.load(new ImageIcon("mapedit/map2.png").getImage());
			}
		});
		add(mapE1);

		final JButton icon1 = new JButton("", new ImageIcon("change/b1.png"));
		icon1.setSize(32, 32);
		icon1.setLocation(x1 - 3, y1 - 25);
		icon1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.reverse();
				
				
						Thread t = new Thread() {
							@Override
							public void run() {
								while (z < 12) {
									try {
										Thread.sleep(50);
										z++;
										ImageIcon img = new ImageIcon("change/b" + z
												+ ".png");
										icon1.setIcon(img);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								z = 1;
							}

						};
						t.start();
					}
				});

		
		
		
		
		
		
		add(icon1);

		final JButton icon2 = new JButton("", new ImageIcon("brush/a1.png"));
		icon2.setSize(32, 32);
		icon2.setLocation(x2 - 3, y2 - 25);
		icon2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				con = true;
				f++;
				if (f == 9)
					f = 2;
				panel.brush(f);
				ImageIcon img = new ImageIcon("brush/a" + f + ".png");
				icon2.setIcon(img);

			}
		});
		add(icon2);

		final JButton icon3 = new JButton("", new ImageIcon("fill/f1.png"));
		icon3.setSize(32, 32);
		icon3.setLocation(x3 - 3, y3 - 25);
		icon3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				con = false;
				panel.fill();
				Thread t = new Thread() {
					@Override
					public void run() {
						while (g < 15) {
							try {
								Thread.sleep(50);
								g++;
								ImageIcon img = new ImageIcon("fill/f" + g
										+ ".png");
								icon3.setIcon(img);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						g = 1;
					}

				};
				t.start();
			}
		});
		add(icon3);

		final JButton icon4 = new JButton("", new ImageIcon("circle/d1.png"));
		icon4.setSize(32, 32);
		icon4.setLocation(x4 - 3, y4 - 25);
		icon4.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				y++;
				if (y == 5)
					y = 1;
				panel.circle();
				ImageIcon img = new ImageIcon("circle/d" + y + ".png");
				icon4.setIcon(img);

			}
		});
		add(icon4);

		final JButton icon5 = new JButton("", new ImageIcon("recycle/e1.png"));
		icon5.setSize(32, 32);
		icon5.setLocation(x5 - 3, y5 - 25);
		icon5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.clear();
				Thread t = new Thread() {
					@Override
					public void run() {
						while (p < 19) {
							try {
								Thread.sleep(50);
								p++;
								ImageIcon img = new ImageIcon("recycle/e" + p
										+ ".png");
								icon5.setIcon(img);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						p = 1;
					}

				};
				t.start();
			}
		});
		add(icon5);

		final JButton icon6 = new JButton("", new ImageIcon("bridge/j1.png"));
		icon6.setSize(32, 32);
		icon6.setLocation(x6 - 3, y6 - 25);
		icon6.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				j++;
				if (j == 11)
					j = 1;
				ImageIcon img = new ImageIcon("bridge/j" + j + ".png");
				icon6.setIcon(img);

			}
		});
		add(icon6);

		final JButton icon7 = new JButton("", new ImageIcon("rotate/l1.png"));
		icon7.setSize(32, 32);
		icon7.setLocation(x7 - 3, y7 - 25);
		icon7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.rotate();
				Thread t = new Thread() {
					@Override
					public void run() {
						while (l < 21) {
							try {
								Thread.sleep(50);
								l++;
								ImageIcon img = new ImageIcon("rotate/l" + l
										+ ".png");
								icon7.setIcon(img);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						l = 1;
					}

				};
				t.start();
			}
		});

		add(icon7);

		final JButton icon8 = new JButton("", new ImageIcon("undo/u1.png"));
		icon8.setSize(32, 32);
		icon8.setLocation(x8 - 3, y8 - 25);
		icon8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.undo();
				Thread t = new Thread() {
					@Override
					public void run() {
						while (n < 19) {
							try {
								Thread.sleep(50);
								n++;
								ImageIcon img = new ImageIcon("undo/u" + n
										+ ".png");
								icon8.setIcon(img);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						n = 1;
					}

				};
				t.start();
			}
		});

		add(icon8);

		final JButton icon9 = new JButton("", new ImageIcon("tunnel/t1.png"));
		icon9.setSize(32, 32);
		icon9.setLocation(x9 - 3, y9 - 25);
		icon9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.tunnel();
				Thread t = new Thread() {
					@Override
					public void run() {
						while (n < 20) {
							try {
								Thread.sleep(50);
								n++;

								ImageIcon img = new ImageIcon("tunnel/t" + n
										+ ".png");
								icon9.setIcon(img);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						n = 1;
					}

				};
				t.start();
			}
		});

		add(icon9);

		final JButton icon10 = new JButton("", new ImageIcon("border/w1.png"));
		icon10.setSize(32, 32);
		icon10.setLocation(x10 - 3, y10 - 25);
		icon10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.border();
				Thread t = new Thread() {
					@Override
					public void run() {
						while (n < 10) {
							try {
								Thread.sleep(50);
								n++;

								ImageIcon img = new ImageIcon("border/w" + n
										+ ".png");
								icon10.setIcon(img);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						n = 1;
					}

				};
				t.start();
			}
		});
		add(icon10);

		final JButton icon11 = new JButton("Start", new ImageIcon("no.png"));
		icon11.setSize(82, 32);
		icon11.setLocation(x11 - 3, y11 - 25);
		add(icon11);
		icon11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new GameRunner(1, panel.map,
						new ImageIcon("bg/bg" + m + ".png").getImage());
			}
		});

		final JButton icon12 = new JButton("", new ImageIcon("object/o1.png"));
		icon12.setSize(32, 32);
		icon12.setLocation(x12 - 3, y12 - 25);
		icon12.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				con = false;
				o++;
				if (o == 11)
					o = 1;
				ImageIcon img = new ImageIcon("object/o" + o + ".png");
				icon12.setIcon(img);
			}
		});

		add(icon12);

		// butt[12]=new MButton("water",parent);
		// butt[12].setSize(50, 190);
		// butt[12].setBackground(Color.WHITE);
		// butt[12].setIcon(waterborder);
		// butt[12].setLayout(null);
		// butt[12].setLocation(160, 240);
		// waterlabel=new JLabel();
		// waterlabel.setIcon(smallwater[0]);
		// waterlabel.setSize(43,25);
		// waterlabel.setLocation(3, 160);
		// butt[12].add(waterlabel);
		// this.add(butt[12]);

		icon13 = new JLabel();
		icon13.setSize(32, 170);
		icon13.setLocation(x13 - 3, y13 - 165);
		icon13.setLayout(null);
		icon13.setOpaque(true);
		icon13.setBackground(Color.black);
		wtr = new JLabel(new ImageIcon("water/bw1.png"));
		wtr.setIcon(new ImageIcon("water/bw1.png"));
		wtr.setSize(32, 20);
		icon13.add(wtr);
		repaint();
		wtr.setLocation(0, 50);
		wtr.setOpaque(true);

		getContentPane().add(icon13);
		add(icon13);

		icon13.addMouseListener(new MouseListener() {

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
			public void mouseClicked(MouseEvent e) {
				wtr.setLocation(0, e.getY());
			}
		});
		add(icon13);

		final JButton icon14 = new JButton("", new ImageIcon("bg/bg1.png"));
		icon14.setSize(150, 150);
		icon14.setLocation(x14 - 3, y14 - 25);
		icon14.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				con = false;
				m++;
				if (m == 6)
					m = 1;
				// panel.backG();
				ImageIcon img = new ImageIcon("bg/bg" + m + ".png");
				icon14.setIcon(img);

			}
		});
		add(icon14);
		(new Thread(this)).start();
		setVisible(true);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				v++;
				if (v == 11)
					v = 1;
				Icon img = icon13.getIcon();
//				img.//new ImageIcon("water/bw" + v + ".png");
//				icon13.setIcon(img);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

//	public static void main(String[] args) {
//		new MapEdit();
//
//	}

}

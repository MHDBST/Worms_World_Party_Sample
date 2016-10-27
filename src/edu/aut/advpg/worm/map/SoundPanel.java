package edu.aut.advpg.worm.map;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SoundPanel extends JPanel {
	static boolean playSound = true;
	static JLabel label = new JLabel("Sound On");

	public SoundPanel(int x, int y, int width, int height) {
		super();
		setBounds(x, y, width, height);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		label.setBounds(10, 50, 100, 40);
		this.add(label);

		SoundButton soundButton = new SoundButton();
		soundButton.setBounds(10, 0, 50, 50);
		this.add(soundButton);
	}

	class SoundButton extends JButton {
		SoundButton() {
			setIcon(new ImageIcon(".\\images\\startPage\\soundIcons\\"
					+ SoundPanel.playSound + ".jpg"));
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setIcon(new ImageIcon(".\\images\\startPage\\soundIcons\\"
							+ SoundPanel.playSound + ".jpg"));
					if (SoundPanel.playSound) {
						SoundPanel.label.setText("Sound On");
					} else {
						SoundPanel.label.setText("Sound Off");
					}
					SoundPanel.playSound = !SoundPanel.playSound;
					SoundPanel.this.repaint();
				}

			});
		}
	}
}

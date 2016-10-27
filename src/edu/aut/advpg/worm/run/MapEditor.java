
package edu.aut.advpg.worm.run;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.aut.advpg.worm.map.Map;
import edu.aut.advpg.worm.map.MapModel;

public class MapEditor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map gameMap;

	public MapEditor() {
		setSize(1366, 700);
		setTitle("Map Editor");
		setVisible(true);
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		MenuItem openMenuItem = new MenuItem("Open Map Image");
		openMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openFileDialog();
			}
		});

		MenuItem saveMenuItem = new MenuItem("Save Map");
		saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					saveMap();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exitForm();
			}
		});

		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);

		Menu mapMenu = new Menu("Map");
		MenuItem generateMapMenuItem = new MenuItem("Generate Map");
		generateMapMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				autoGenerateMap();
			}
		});
		mapMenu.add(generateMapMenuItem);
		menuBar.add(mapMenu);
		setMenuBar(menuBar);

		// gameMap = new Map(getWidth(), getHeight(), true, 1);
		this.getContentPane().add(gameMap);
	}

	protected void autoGenerateMap() {
		MapModel.getInstance().autoGenerateMap();
		JOptionPane.showConfirmDialog(this,
				"Autogenerate Compeleted Successfully");
	}

	protected void saveMap() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		int showOpenDialog = fileChooser.showSaveDialog(this);
		if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
			FileWriter fileWriter = new FileWriter(fileChooser
					.getSelectedFile());
			for (int j = 0; j < MapModel.mapHeight; j++) {
				for (int i = 0; i < MapModel.mapWidth; i++) {
					int x = MapModel.getInstance().gamePlan[i][j];
					fileWriter.write(String.valueOf(x));
				}
				fileWriter.write('\r');
				fileWriter.write('\n');
			}
			fileWriter.flush();
			fileWriter.close();
		}
	}

	protected void exitForm() {
		this.dispose();
	}

	protected void openFileDialog() {
		JFileChooser fileChooser = new JFileChooser();
		int showOpenDialog = fileChooser.showOpenDialog(this);
		if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
			ImageIcon map = new ImageIcon(fileChooser.getSelectedFile()
					.getPath());
			getGraphics().drawImage(map.getImage(), 0, 0, null);
			MapModel.getInstance().setMapImage(map.getImage());
		}
	}

	public static void main(String[] args) {
		new MapEditor();
	}

}

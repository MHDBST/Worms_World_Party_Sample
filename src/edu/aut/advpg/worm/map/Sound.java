package edu.aut.advpg.worm.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

public class Sound{
	private File soundFile;
	private Clip clip;
	private AudioInputStream soundIn;
	private AudioFormat format;
	private DataLine.Info info;
	private Timer soundTimer;

	public Sound(String fileName) {
//		if (Constants.sound)
		{
			soundFile = new File("sounds/" + fileName);
			try {
				format = AudioSystem.getAudioInputStream(soundFile).getFormat();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			info = new DataLine.Info(Clip.class, format);
			soundTimer = new Timer(0, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					playSound();
				}
			});
		}
	}

	public void playSound() {
//		if (Constants.sound)
		{
			synchronized (this) {

				try {
					if ((clip == null) || (!clip.isActive())) {
						soundIn = AudioSystem.getAudioInputStream(soundFile);
						clip = (Clip) AudioSystem.getLine(info);
						clip.open(soundIn);
						clip.start();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("There was an error!");
				}
			}
		}
	}

	public void start() {
		
		soundTimer.start();

	}

	public void stop() {
		if (clip != null) {
			clip.stop();
			clip.close();
		}
		soundTimer.stop();
	}
}

package Task2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.sound.sampled.*;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

/* This player will only accept .wav file */
public class MusicPlayer extends Thread{

	private File soundFile;
	private JDialog playingDialog;
	private Clip clip;
	private Scanner input;
	private static Object lockA = new Object();
	private static Object lockB = new Object();
	private static Object lockC = new Object();

	public void playMusic() {

		/* Thread to select Music */
		Thread thSelectMusic = new Thread(new Runnable() {
			@Override
			public void run() {
				selectMusic();
			}
		});

		/* Thread to display information about this Music Player */
		Thread thInfo = new Thread(new Runnable() {

			@Override
			public void run() {
				info();	
			}

		});
		/* Thread to display all wav files */
		Thread thDisplayWavFiles = new Thread(new Runnable() {

			@Override
			public void run() {
				displayWavFiles();	
			}

		});

		thSelectMusic.start();
		thInfo.start();
		thDisplayWavFiles.start();
		try {
			thSelectMusic.join();
			thInfo.join();
			thDisplayWavFiles.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void displayWavFiles() {
		synchronized(lockC) {
			try {
				Thread.sleep(1000);

				System.out.println("\nListed below are all the .wav files available:");
				Files.newDirectoryStream(Paths.get("C:\\Users\\Kristel\\Music"),
						path -> path.toString().endsWith(".wav"))
				.forEach(System.out::println);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void info() {
		synchronized(lockB) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		System.out.println("This Music Player will continuously play the selected .wav file\n unless you instruct it to stop"
				+ " by typing - STOP");
		input = new Scanner(System.in);
		String ans = input.nextLine();

		if(ans.equalsIgnoreCase("STOP")) {
			clip.stop();

			System.out.println("What would you like the player to do?"
					+ "\nType the corresponding letter to execute commands." 
					+ "\n\tA. Play Music Again?"
					+ "\n\tB. List all available WAV files");
			input = new Scanner(System.in);
			ans = input.nextLine();
			if(ans.equalsIgnoreCase("A")) {
				System.out.flush(); 
				playMusic();
			}else if(ans.equalsIgnoreCase("B")) {
				displayWavFiles();
			}
		}		
	}

	private void selectMusic() {
		synchronized(lockA) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		soundFile = chooser.getSelectedFile();

		System.out.println("\nPlaying " + soundFile.getName());

		try {
			Line.Info linfo = new Line.Info(Clip.class);
			Line line = AudioSystem.getLine(linfo);
			clip = (Clip) line;	
			clip.addLineListener(event -> {
				if(event.getType().equals(LineEvent.Type.OPEN)) {
					System.out.println("OPEN");
					playingDialog.setVisible(true);
				}else if (event.getType().equals(LineEvent.Type.START)) {
					System.out.println("START");
					playingDialog.setVisible(true); 				        	
				}else if(event.getType().equals(LineEvent.Type.CLOSE)) {
					System.out.println("CLOSE");
					System.exit(0);
				}else if(event.getType().equals(LineEvent.Type.STOP)) {
					System.out.println("STOP");
					playingDialog.setVisible(false);
					clip.close();
				}
			});
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			clip.open(ais);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
}

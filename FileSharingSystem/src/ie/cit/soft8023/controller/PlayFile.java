package ie.cit.soft8023.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import ie.cit.soft8023.observer.FileSubject;
import ie.cit.soft8023.observer.Subject;
import ie.cit.soft8023.view.Local;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayFile implements Runnable {
	
	final int startTime = 2;

	/**
	 * Play File method
	 * @param listOfFiles
	 * @param index
	 */
	public void playFile(File[] listOfFiles, int index){
		try {
			FileInputStream fileInputStream = new FileInputStream(listOfFiles[index]);
			Player player = new Player(fileInputStream);
			System.out.println("Song is playing");
			player.play();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try{
			// Sleep for 2 seconds
			Thread.sleep(startTime * 1000);
			
			playFile(Local.listOfFiles, Local.index);

		}
		catch(InterruptedException e)
		{}		
	}

}
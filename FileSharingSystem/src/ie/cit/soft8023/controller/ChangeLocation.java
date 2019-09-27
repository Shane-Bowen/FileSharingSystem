package ie.cit.soft8023.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import ie.cit.soft8023.view.Local;

public class ChangeLocation {
	
	/**
	 * Change Location Method
	 * @param oldLocation
	 * @param newLocation
	 * 
	 */
	public static void change(String oldLocation, String newLocation){
		
		try {
			Files.move(Paths.get(oldLocation), Paths.get(newLocation), StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			System.out.println("Location Does not exist");
		}	
	}
}
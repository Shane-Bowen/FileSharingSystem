package ie.cit.soft8023.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class TransferFile {
	
	/**
	 * Transfer files method
	 */
	public static void transfer()
	{
		//transfering all files from shared to local
		File shared = new File("C:/Users/Shane Bowen/Downloads/Shared_Folder");
		File local = new File("C:/Users/Shane Bowen/Downloads/Local_Folder");
		
		try {
			FileUtils.copyDirectory(shared, local);
			System.out.println("Transfer complete");
		} 
		
		catch (IOException e) {
		}
	}
}
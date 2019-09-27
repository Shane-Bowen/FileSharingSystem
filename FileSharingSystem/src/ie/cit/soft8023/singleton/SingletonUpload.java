package ie.cit.soft8023.singleton;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ie.cit.soft8023.view.AlertBox;

public class SingletonUpload {

	private static SingletonUpload singletonInstance = null;
	static boolean firstThread = true;
    
	private SingletonUpload() { }

	/**
	 * Get Instance method 
	 * @return singletonInstance
	 */
	public static SingletonUpload getInstance() {
		if(singletonInstance == null) {
			if(firstThread){
				firstThread = false;
				try {
					Thread.currentThread();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			synchronized(SingletonUpload.class){ 
				if(singletonInstance == null) {
					singletonInstance = new SingletonUpload();
				}
			}
		}

		return singletonInstance;
	}
		
	/**
	 * Sending the file from the local folder to the shared folder
	 * @param listOfFiles
	 */
	public void uploadFile(File listOfFiles)
	{
		Path localFolder = Paths.get(listOfFiles + "");
		String newLocation = listOfFiles + "";
		newLocation = newLocation.replace("Local_Folder", "Shared_Folder");
		Path sharedFolder = Paths.get(newLocation);
		
		try {
			Files.copy(localFolder, sharedFolder);
			AlertBox.display("Upload", "File has been Uploaded to the Shared Folder!");
			System.out.println("Transfer complete");
		} 
		
		catch (IOException e) {
			AlertBox.display("Upload", "File already exists in the Shared Folder!");
			System.out.println("File already exists in folder");
		}
	}
	
}
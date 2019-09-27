package ie.cit.soft8023.observer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileObserver implements Observer {
	
	Path shared = Paths.get("C:/Users/Shane Bowen/Downloads/Shared_Folder");
	
	private File[] listOfFiles;
	private static int observerIDTracker = 0;
	private int observerID;
	private Subject FileSubject;
	
	/**
	 * Create File Subject Instance
	 * @param FileSubj
	 */
	public FileObserver(Subject FileSubj){
				
		this.FileSubject = FileSubj;
		this.observerID = ++observerIDTracker;
		System.out.println("New Observer " + this.observerID);
		FileSubject.register(this);
		
	}
	
	/**
	 * Update list of files method
	 * @param listOfFiles
	 */
	public void update(File[] listOfFiles) {
		
		System.out.println("File Updated");
		this.listOfFiles = listOfFiles;
	}
	
}
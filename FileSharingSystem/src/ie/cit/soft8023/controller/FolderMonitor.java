package ie.cit.soft8023.controller;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.apache.commons.io.FileUtils;

import ie.cit.soft8023.observer.FileSubject;
import ie.cit.soft8023.observer.Subject;

public class FolderMonitor implements Runnable {

	File shared = new File("C:/Users/Shane Bowen/Downloads/Shared_Folder");

	private Subject fileSubject;
	final int startTime = 2;

	/**
	 * Create Instance of File Subject
	 * @param fileSubj
	 */
	public FolderMonitor(FileSubject fileSubj) {

		this.fileSubject = fileSubj;
	}

	/**
	 * Watch Directory method
	 * @param shared
	 */
	public static void watchDirectoryPath(Path shared) {
		// Check if path is a folder
		try {
			Boolean isFolder = (Boolean) Files.getAttribute(shared, "basic:isDirectory", NOFOLLOW_LINKS);

			if (!isFolder) {
				throw new IllegalArgumentException("Path: " + shared + " is not a folder");
			}

		} catch (IOException ioe) {
			// Folder does not exists
			ioe.printStackTrace();
		}

		System.out.println("Watching path: " + shared);

		// We obtain the file system of the Path
		FileSystem fileSystem = shared.getFileSystem();

		// We create the new WatchService using the new try() block
		try (WatchService service = fileSystem.newWatchService()) {

			// We register the path to the service
			shared.register(service, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

			// Start the infinite loop
			WatchKey key = null;

			while (true) {
				key = service.take();

				// Dequeueing events
				Kind<?> kind = null;

				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					
					// Get the type of the event
					kind = watchEvent.kind();

					// Continue Loop
					if (OVERFLOW == kind) {
						continue;
					}

					//If we add a new file to shared folder then we add it to local folder
					else if (ENTRY_CREATE == kind) { 
						
						// A new Path was created
						@SuppressWarnings("unchecked")
						Path newPath = ((WatchEvent<Path>) watchEvent).context();

						System.out.println("New path created: " + newPath);
						
						//transfering all files from shared to local
						File sharedFolder = new File("C:/Users/Shane Bowen/Downloads/Shared_Folder");
						File localFolder = new File("C:/Users/Shane Bowen/Downloads/Local_Folder");
						
						try {
							FileUtils.copyDirectory(sharedFolder, localFolder);
							System.out.println("Transfer complete");
						} catch (IOException e) {
						}

					} 

					//If we delete a file from shared folder
					else if (ENTRY_DELETE == kind) {
						
						// deleted
						@SuppressWarnings("unchecked")
						Path newPath = ((WatchEvent<Path>) watchEvent)
								.context();

						System.out.println("Path was deleted: " + newPath);

						//transfering all files from shared to local
						File sharedFolder = new File("C:/Users/Shane Bowen/Downloads/Shared_Folder");
						File localFolder = new File("C:/Users/Shane Bowen/Downloads/Local_Folder");
						try {
							FileUtils.copyDirectory(sharedFolder, localFolder);
							System.out.println("Transfer complete");
						} catch (IOException e) {
						}
					}

					//If we modify a file from shared folder
					else if (ENTRY_MODIFY == kind) {
						
						// modified
						@SuppressWarnings("unchecked")
						Path newPath = ((WatchEvent<Path>) watchEvent)
								.context();

						System.out.println("New path modified: " + newPath);

						//transfering all files from shared to local
						File sharedFolder = new File("C:/Users/Shane Bowen/Downloads/Shared_Folder");
						File localFolder = new File("C:/Users/Shane Bowen/Downloads/Local_Folder");
						try {
							FileUtils.copyDirectory(sharedFolder, localFolder);
							//System.out.println("Transfer complete");
						} catch (IOException e) {
						}
					}
				}

				if (!key.reset()) {
					break; // loop
				}
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();

		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

	}

	@Override
	public void run() {

			try{
				// Sleep for 2 seconds
				Thread.sleep(startTime * 1000);
				
				File dir = new File("C:/Users/Shane Bowen/Downloads/Shared_Folder");
				watchDirectoryPath(dir.toPath());

			}
			catch(InterruptedException e)
			{}

		}
}
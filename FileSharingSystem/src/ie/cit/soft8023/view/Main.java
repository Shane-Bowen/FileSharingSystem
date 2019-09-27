/*
 * Name: Shane Bowen
 * Class: SD3-A
 * ID: R00149085
 */

package ie.cit.soft8023.view;

import ie.cit.soft8023.controller.FolderMonitor;
import ie.cit.soft8023.observer.FileSubject;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage window;
    public static Home home = new Home();
	public static Shared shared = new Shared(); // Subject
	public static Local local = new Local(); // Observer

	/**
	 * Main method
	 * @param args
	 */
    public static void main(String[] args) {

    	FileSubject fileSubject = new FileSubject();    	
    	fileSubject.setListOfFiles(Shared.listOfFiles);
    	
        Runnable getList = new FolderMonitor(fileSubject);
        new Thread(getList).start();
    	
        launch(args);
    }

    /**
     * Start method
     */
    public void start(Stage primaryStage) throws Exception {		
    	window = primaryStage;
        window.show();
        window.setScene(home.scene);
    }
    
    /**
     * Rest Local list of files
     */
	public static void resetLocal(){
		local = new Local();
		Main.window.setScene(local.scene);
	}
	
	/**
	 * Reset Shared List of files
	 */
	public static void resetShared(){
		shared = new Shared();
		Main.window.setScene(shared.scene);
	}
}
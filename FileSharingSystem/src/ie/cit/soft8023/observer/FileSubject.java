package ie.cit.soft8023.observer;

import java.io.File;
import java.util.ArrayList;

public class FileSubject implements Subject{

	private static ArrayList<Observer> observers;
	private static File[] listOfFiles;

	/**
	 * File Subject method
	 */
	public FileSubject(){
		observers = new ArrayList<Observer>();
	}

	/**
	 * Register method
	 * @param newObserver
	 */
	public void register(Observer newObserver) {
		observers.add(newObserver);
	}

	/**
	 * Unregister Method
	 * @param deleteObserver
	 */
	public void unregister(Observer deleteObserver) {
		int observerIndex = observers.indexOf(deleteObserver);	
		System.out.println("Observer " + (observerIndex+1) + " deleted");
		observers.remove(observerIndex);
	}
	
	/**
	 * Notify Observer method
	 */
	public void notifyObserver() {
		for(Observer observer : observers){
			System.out.println("Notify Observer");
			observer.update(listOfFiles);

		}
	}

	/**
	 * Set List of Files method
	 * @param newListOfFiles
	 */
	public void setListOfFiles(File[] newListOfFiles){
		listOfFiles = newListOfFiles;
		notifyObserver();
	}

}
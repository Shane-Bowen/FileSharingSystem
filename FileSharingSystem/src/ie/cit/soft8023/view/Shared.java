package ie.cit.soft8023.view;

import java.io.File;

import org.apache.commons.io.FileUtils;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Shared{	

	//**********************************************************
	//	Declaring the global variables
	//**********************************************************

	public static File[] listOfFiles;
	public Scene scene;

	private BorderPane border = new BorderPane();
	
	Home home = new Home();
	Stage stage = new Stage();

	//**********************************************************
	// Class Constructor	
	//**********************************************************

	public Shared () {	
		
		//**********************************************************
		// Creating the layout BorderPane and setting the title 
		// of the stage.
		//**********************************************************
		
		Label title = new Label("Shared Folder");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		//**********************************************************
		// 	Creating the layout 
		//**********************************************************

		VBox vbox = new VBox();
		Label songsList = null;

		File folder = new File("C:/Users/Shane Bowen/Downloads/Shared_Folder");
		listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				songsList = new Label();
				songsList.setText(listOfFiles[i].getName());
				songsList.setFont(new Font(14));
				vbox.getChildren().add(songsList);
			} 
		}

		vbox.setAlignment(Pos.CENTER);
		border.setCenter(vbox);

		Button backBtn = new Button("Back");
		Button changeBtn = new Button("Change");
		Button resetBtn = new Button("Reset");

		HBox hbox = new HBox(5);
		hbox.getChildren().addAll(backBtn, changeBtn, resetBtn);
		BorderPane.setMargin(hbox, new Insets(10));
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		border.setBottom(hbox);

		//**********************************************************
		// When the guess button is pressed it will cause an event
		// to happen
		//**********************************************************

		backBtn.setOnAction((ActionEvent event) ->{
			Main.window.setScene(home.scene);
		});
		
		//Not working properly
		  changeBtn.setOnAction((ActionEvent event) ->{
			
			String remoteLocation = FileUtils.getFile(folder).toString();
			Change.display("Change Location", remoteLocation);
		});

		resetBtn.setOnAction((ActionEvent event) ->{
			Main.resetShared();
		});		

		scene = new Scene(border, 800, 500);
	}
}
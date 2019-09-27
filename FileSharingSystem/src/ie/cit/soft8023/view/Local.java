package ie.cit.soft8023.view;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import ie.cit.soft8023.controller.PlayFile;
import ie.cit.soft8023.singleton.SingletonUpload;
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

public class Local {	

	//**********************************************************
	//	Declaring the global variables
	//**********************************************************

	public static File[] listOfFiles;
	
	private BorderPane border = new BorderPane();
	private ArrayList<Button> array = new ArrayList<Button>();

	Stage stage = new Stage();
	Home home = new Home();
	
	public Scene scene;	
	public static int index = 0;
 
	//**********************************************************
	// Class Constructor	
	//**********************************************************

	public Local() {

		//**********************************************************
		// Creating the layout BorderPane and setting the title 
		// of the stage.
		//**********************************************************

		Progress progress = new Progress();

		SingletonUpload uploadInstance = SingletonUpload.getInstance();

		Label title = new Label("Local Folder");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		//**********************************************************
		// 	Creating the layout
		//**********************************************************

		Label songsList = null;
		VBox vbox = new VBox();

		File folder = new File("C:/Users/Shane Bowen/Downloads/Local_Folder");
		listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				HBox hb = new HBox();
				songsList = new Label();
				songsList.setText(listOfFiles[i].getName());
				songsList.setFont(new Font(14));

				Button playBtn = new Button();
				playBtn.setText("Play");
				playBtn.setId(i + "");
				array.add(playBtn);
				playBtn.setDisable(true);

				playBtn.setOnAction((ActionEvent event) ->{
					System.out.println(playBtn.getId());
					System.out.println(listOfFiles[Integer.parseInt(playBtn.getId())]);
					index = Integer.parseInt(playBtn.getId());
			        Runnable getList = new PlayFile();
			        new Thread(getList).start();
					
				});

				Button downloadBtn = new Button();
				downloadBtn.setText("Download");
				downloadBtn.setId(i + "");

				downloadBtn.setOnAction((ActionEvent event) ->{

					array.get(Integer.parseInt(downloadBtn.getId())).setDisable(false);
					
					progress.display();
				});

				Button uploadBtn = new Button();
				uploadBtn.setText("Upload");
				uploadBtn.setId(i + "");
				uploadBtn.setOnAction((ActionEvent event) ->{
					System.out.println(listOfFiles[Integer.parseInt(playBtn.getId())]);
				    System.out.println("Upload Instance ID: " + System.identityHashCode(uploadInstance));
					uploadInstance.uploadFile(listOfFiles[Integer.parseInt(playBtn.getId())]);
				});

				HBox.setMargin(playBtn, new Insets(0, 10, 0, 10));
				HBox.setMargin(uploadBtn, new Insets(0, 0, 0, 10));
				hb.setPadding(new Insets(5, 5, 5, 5));

				hb.setAlignment(Pos.CENTER);
				hb.getChildren().addAll(songsList, playBtn, downloadBtn, uploadBtn);

				vbox .getChildren().add(hb);

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
		// When the buttons is pressed it will cause an event
		// to happen
		//**********************************************************

		backBtn.setOnAction((ActionEvent event) ->{
			Main.window.setScene(home.scene);
		});
		
		//Not working properly
		  changeBtn.setOnAction((ActionEvent event) ->{
			
			String oldLocation = FileUtils.getFile(folder).toString();
			Change.display("Change Location", oldLocation);
		});

		resetBtn.setOnAction((ActionEvent event) ->{
			Main.resetLocal();
		});

		scene = new Scene(border, 800, 500);
	}

}
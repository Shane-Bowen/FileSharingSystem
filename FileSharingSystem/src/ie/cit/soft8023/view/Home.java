package ie.cit.soft8023.view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Home{	
	
	//**********************************************************
	//	Declaring the global variables
	//**********************************************************

	private BorderPane border = new BorderPane();
	
	public Stage stage = new Stage();
    public Stage window;
    public Scene scene;
    
	public static Shared shared = new Shared();
	public static Local local = new Local();

	//**********************************************************
	// Class Constructor	
	//**********************************************************

	public Home () {

		//**********************************************************
		// Creating the layout BorderPane and setting the title 
		// of the stage.
		//**********************************************************

		Label title = new Label("File Sharing System");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		//**********************************************************
		// 	Creating the layout
		//**********************************************************

		Button localBtn = new Button("Local");
		Button sharedBtn = new Button("Shared");
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(localBtn, sharedBtn);
		hbox.setPadding(new Insets(10, 10, 10, 10));
		HBox.setMargin(localBtn, new Insets(10));
		hbox.setAlignment(Pos.CENTER);

		border.setCenter(hbox);
		
		//**********************************************************
		// When the buttons is pressed it will cause an event
		// to happen
		//**********************************************************

		localBtn.setOnAction((ActionEvent event) ->{
			local = new Local();
			Main.window.setScene(local.scene);
		});

		sharedBtn.setOnAction((ActionEvent event) ->{
			shared = new Shared();
			Main.window.setScene(shared.scene);
		});
		
		scene = new Scene(border, 800, 500);
	}

}
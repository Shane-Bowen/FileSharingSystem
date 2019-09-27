package ie.cit.soft8023.view;

import ie.cit.soft8023.controller.ChangeLocation;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Change {
	
	/**
	 * Display method
	 * @param title
	 * @param oldLocation
	 */
	public static void display(String title, String oldLocation) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(100);

        TextField text = new TextField();
        text.setText(oldLocation + "");
        
        Button okBtn = new Button("Ok");
        okBtn.setOnAction((ActionEvent event) ->{
        	
        	String newLocation = text.getText();
        	ChangeLocation.change(oldLocation, newLocation);
        });
        
        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> window.close());
        
        HBox hb = new HBox(10);
        hb.getChildren().addAll(okBtn, exitBtn);
        hb.setAlignment(Pos.CENTER);

        VBox vb = new VBox(10);
        vb.getChildren().addAll(text, hb);
        vb.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(vb);
        window.setScene(scene);
        window.showAndWait();
    }
}
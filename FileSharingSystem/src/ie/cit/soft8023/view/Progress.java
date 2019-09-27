package ie.cit.soft8023.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Progress {
	Task<?> copyWorker;
	public static boolean done = false;
	final int startTime = 2;
	
	/**
	 * Display method
	 */
	public void display(){
		
		Stage window = new Stage();

		BorderPane border = new BorderPane();
		Label title = new Label("File Transfer");
		title.setTextFill(Color.BLUE);
		title.setFont(new Font("Arial", 14));

		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		ProgressBar progressBar = new ProgressBar(0);
		Button startButton = new Button("Start");
		Button cancelButton = new Button("Cancel");
		Button exitButton = new Button("Exit");

		HBox hb = new HBox();
		hb.setSpacing(5);
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(startButton, cancelButton, exitButton);

		VBox vb = new VBox();
		vb.setSpacing(5);
		vb.getChildren().addAll(progressBar, hb);
		vb.setAlignment(Pos.CENTER);
		border.setCenter(vb);

		Scene scene = new Scene(border, 300, 150);

		exitButton.setOnAction(e -> window.close());

		startButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				startButton.setDisable(true);
				progressBar.setProgress(0);
				cancelButton.setDisable(false);
				copyWorker = createWorker();

				progressBar.progressProperty().unbind();
				progressBar.progressProperty().bind(copyWorker.progressProperty());

				copyWorker.messageProperty().addListener(new ChangeListener<String>() {
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						System.out.println(newValue);
					}
				});

				new Thread(copyWorker).start();

			}
		});
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startButton.setDisable(false);
				cancelButton.setDisable(true);
				copyWorker.cancel(true);
				progressBar.progressProperty().unbind();
				progressBar.setProgress(0);
				System.out.println("cancelled.");
			}
		});
		window.setScene(scene);
		window.show();
	}

	@SuppressWarnings("rawtypes")
	public Task createWorker() {

		return new Task() {
			@Override
			protected Object call() throws Exception {
				for (int i = 0; i < 10; i++) {
					Thread.sleep(1000);
					updateProgress(i + 1, 10);
				}
				System.out.println("finished");
				
				done = true;
				return true;         
			}
		};
	}
}
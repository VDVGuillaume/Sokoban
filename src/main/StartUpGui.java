package main;

import gui.WelcomeScreenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGui extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			WelcomeScreenController root = new WelcomeScreenController();
			Scene scene = new Scene(root, 1000, 500);

			primaryStage.setTitle("WelcomeScreen");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

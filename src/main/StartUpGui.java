package main;

import domein.DomainController;
import gui.MenuScreenController;
import gui.WelcomeScreenController;
import gui.NewGameScreenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGui extends Application {
	private DomainController domainController;
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			domainController = new DomainController();
			domainController.setLanguage(2);

			WelcomeScreenController root = new WelcomeScreenController(domainController);

			Scene scene = new Scene(root, 1000, 500);

			primaryStage.setTitle("Sokoban");
			primaryStage.setResizable(false);
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

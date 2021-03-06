package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import domein.DomainController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Hyperlink;

public class MenuScreenController extends BaseScreenController {

	@FXML
	private Label lblWelcomeUsername;
	@FXML
	private Hyperlink linkPlay;
	@FXML
	private Hyperlink linkQuit;
	@FXML
	private Hyperlink linkCreateNewGame;
	@FXML
	private Hyperlink linkEditGame;
	@FXML
	private Hyperlink linkLogOut;

	public MenuScreenController(DomainController domainController) {
		super(domainController, "MenuScreen.fxml");
	}

	@Override
	protected void loadData() {
		String[] userInfo = domainController.getInfoUser();
		String username = userInfo[0];
		boolean isAdmin = userInfo[1].equals("True");

		// set translations
		lblWelcomeUsername.setText(domainController.translate("WelcomeUser").replace("$username", username));
		linkPlay.setText(domainController.translate("Menu_PlayGame"));
		linkQuit.setText(domainController.translate("Menu_Quit"));
		linkLogOut.setText(domainController.translate("Log_Out"));
		
		if (isAdmin) {
			linkCreateNewGame.setDisable(false);
			linkCreateNewGame.setVisible(true);
			linkEditGame.setDisable(false);
			linkEditGame.setVisible(true);

			linkCreateNewGame.setText(domainController.translate("Menu_CreateNewGame"));
			linkEditGame.setText(domainController.translate("Menu_EditGame"));
		} else {
			linkCreateNewGame.setDisable(true);
			linkCreateNewGame.setVisible(false);
			linkEditGame.setDisable(true);
			linkEditGame.setVisible(false);
			GridPane.setRowIndex(linkLogOut, 4);
			GridPane.setRowIndex(linkQuit, 5);
		}
	}

	// Event Listener on Hyperlink[#linkPlay].onAction
	@FXML
	public void linkPlayOnAction(ActionEvent event) {
		Stage stage = (Stage) linkPlay.getScene().getWindow();
		GamesListScreenController root = new GamesListScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}

	// Event Listener on Hyperlink[#linkQuit].onAction
	@FXML
	public void linkQuitOnAction(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}

	// Event Listener on Hyperlink[#linkCreateNewGame].onAction
	@FXML
	public void linkCreateNewGameOnAction(ActionEvent event) {
		Stage stage = (Stage) linkCreateNewGame.getScene().getWindow();
		NewGameScreenController root = new NewGameScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}

	// Event Listener on Hyperlink[#linkEditGame].onAction
	@FXML
	public void linkEditGameOnAction(ActionEvent event) {
		Stage stage = (Stage) linkEditGame.getScene().getWindow();
		EditGamesListScreenController root = new EditGamesListScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}
	// Event Listener on Hyperlink[#linkLogOut].onAction
	@FXML
	public void linkLogOutOnAction(ActionEvent event) {
		setWelcomeScreen(this.getScene());
	}
}

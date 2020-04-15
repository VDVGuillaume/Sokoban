package gui;

import javafx.fxml.FXML;
import domein.DomainController;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Hyperlink;

public class GameScreenController extends BaseScreenController {
	protected GameScreenController(DomainController domainController) {
		super(domainController, "GameScreen.fxml");
	}

	@FXML
	private Label lblPlayingGame;
	@FXML
	private Hyperlink linkPlayNextGameBoard;
	@FXML
	private Hyperlink linkQuit;
	@FXML
	private Label lblTotalGameBoardsCount;
	@FXML
	private Label lblCompletedGameBoardsCount;

	@Override
	protected void loadData() {

	}

	// Event Listener on Hyperlink[#linkPlayNextGameBoard].onAction
	@FXML
	public void linkPlayNextGameBoardOnAction(ActionEvent event) {
		if (domainController.isGameCompleted()) {
			// TODO add error handling
		} else {
			// start next game board
			domainController.playNextGameBoard();
			// open window
			Stage stage = (Stage) linkPlayNextGameBoard.getScene().getWindow();
			GameBoardScreenController root = new GameBoardScreenController(domainController);
			Scene scene = new Scene(root, 1000, 500);
			scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> root.handleKeyEvent(key));
			stage.setScene(scene);
		}
	}

	// Event Listener on Hyperlink[#linkQuit].onAction
	@FXML
	public void linkQuitOnAction(ActionEvent event) {
		// redirecting to previous screen
		Stage stage = (Stage) linkQuit.getScene().getWindow();
		GamesListScreenController root = new GamesListScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}
}
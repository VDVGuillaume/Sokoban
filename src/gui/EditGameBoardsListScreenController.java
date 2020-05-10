package gui;

import javafx.fxml.FXML;

import java.util.List;

import domein.DomainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditGameBoardsListScreenController extends BaseScreenController {
	protected EditGameBoardsListScreenController(DomainController domainController) {
		super(domainController, "EditGameBoardsListScreen.fxml");
	}

	@FXML
	private Label lblChooseGameboard;
	@FXML
	private ListView lstGameboards;
	@FXML
	private Hyperlink linkReturn;
	@FXML
	private Hyperlink linkCreateGameBoard;

	@Override
	protected void loadData() {
		// set games
		List<Integer> gameBoardIds = domainController.getGameBoardIdsFromGame();
		ObservableList<Integer> gameBoardIdsObservLst = FXCollections.observableArrayList(gameBoardIds);
		lstGameboards.setItems(gameBoardIdsObservLst);
		linkCreateGameBoard.setText(domainController.translate("CreateGameboard"));
		linkReturn.setText(domainController.translate("Return"));
		lblChooseGameboard.setText(domainController.translate("ChooseGameboardToEdit"));
	}

	@FXML
	public void linkReturnOnAction(ActionEvent event) {
		// redirecting to previous screen
		Stage stage = (Stage) linkReturn.getScene().getWindow();
		MenuScreenController root = new MenuScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}

	@FXML
	public void linkCreateGameBoard(ActionEvent event) {
		domainController.createGameBoard();
		
		// open window
		Stage stage = (Stage) lstGameboards.getScene().getWindow();
		EditGameBoardScreenController root = new EditGameBoardScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}

	// Event Listener on ListView[#lstGames].onMouseClicked
	@FXML
	public void lstGameboardsChooseGameBoard(MouseEvent event) {

		if (lstGameboards.getSelectionModel().getSelectedItem() == null) {
			return;
		}

		// select game
		int selectedItem = (Integer) lstGameboards.getSelectionModel().getSelectedItem();

		if (selectedItem == 0) {
			return;
		}

		try {
			domainController.chooseGameBoardFromGame(selectedItem);

			// open window
			Stage stage = (Stage) lstGameboards.getScene().getWindow();
			EditGameBoardScreenController root = new EditGameBoardScreenController(domainController);
			Scene scene = new Scene(root, 1000, 500);
			stage.setScene(scene);

		} catch (Exception e) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText(e.getMessage());
			errorAlert.showAndWait();
			e.printStackTrace();
		}
	}
}

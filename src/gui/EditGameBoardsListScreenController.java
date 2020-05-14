package gui;

import javafx.fxml.FXML;

import java.util.List;

import domein.DomainController;
import exceptions.GameException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditGameBoardsListScreenController extends BaseScreenController {

	private enum ActionModes {
		update, delete
	}

	protected EditGameBoardsListScreenController(DomainController domainController) {
		super(domainController, "EditGameBoardsListScreen.fxml");
		actionMode = ActionModes.update;
	}

	@FXML
	private Hyperlink linkChooseGameboard;
	@FXML
	private ListView lstGameboards;
	@FXML
	private Hyperlink linkReturn;
	@FXML
	private Hyperlink linkCreateGameBoard;
	@FXML
	private Label lblSaveError;
	private ActionModes actionMode;
	@FXML
	private Button btnExit;

	@Override
	protected void loadData() {
		if(actionMode == null) {
			actionMode = ActionModes.update;
		}
		lblSaveError.setText("");
		btnExit.setText("Menu");

		List<Integer> gameBoardIds = domainController.getGameBoardIdsFromGame();
		ObservableList<Integer> gameBoardIdsObservLst = FXCollections.observableArrayList(gameBoardIds);
		lstGameboards.setItems(gameBoardIdsObservLst);
		linkCreateGameBoard.setText(domainController.translate("CreateGameboard"));
		linkReturn.setText(domainController.translate("Return"));

		if (actionMode.equals(ActionModes.update)) {
			linkChooseGameboard.setText(domainController.translate("ChooseGameboardToEdit"));
		} else if (actionMode.equals(ActionModes.delete)) {
			linkChooseGameboard.setText(domainController.translate("ChooseGameboardToDelete"));
		}
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

	/**
	 * Change action mode. 2 options: update/delete
	 */
	@FXML
	public void linkChooseGameboard(ActionEvent event) {
		switch (actionMode) {
		case update:
			// switch to delete
			actionMode = ActionModes.delete;
			break;
		case delete:
			// switch to update
			actionMode = ActionModes.update;
			break;
		}

		loadData();
		event.consume();
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

		// update or delete?
		switch (actionMode) {
		case update:
			updateGameBoard(selectedItem);
			break;
		case delete:
			deleteGameBoard(selectedItem);
			break;
		}
	}

	private void updateGameBoard(int gameBoardId) {
		try {
			domainController.chooseGameBoardFromGame(gameBoardId);

			// open window
			Stage stage = (Stage) lstGameboards.getScene().getWindow();
			EditGameBoardScreenController root = new EditGameBoardScreenController(domainController);
			Scene scene = new Scene(root, 1000, 500);
			stage.setScene(scene);
		} catch (Exception e) {
			lblSaveError.setText(e.getMessage());
		}
	}

	private void deleteGameBoard(int gameBoardId) {
		try {
			domainController.chooseGameBoardFromGame(gameBoardId);
			domainController.deleteSelectedGameBoard();
			lstGameboards.getSelectionModel().clearSelection();
			loadData();
		} catch (Exception e) {
			lblSaveError.setText(e.getMessage());
		}		
	}
	
	@FXML
	private void btnExitOnAction(ActionEvent event) {
		Stage stage = (Stage) btnExit.getScene().getWindow();
		
		MenuScreenController root = new MenuScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	
	}
}

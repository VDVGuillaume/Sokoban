package gui;

import javafx.fxml.FXML;

import java.util.List;

import domein.DomainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditGamesListScreenController extends BaseScreenController {
	protected EditGamesListScreenController(DomainController domainController) {
		super(domainController, "EditGamesListScreen.fxml");
	}

	@FXML
	private Label lblChooseGame;
	@FXML
	private ListView lstGames;
	@FXML
	private Hyperlink linkReturn;
	@FXML
	private Button btnExit;

	@Override
	protected void loadData() {
		// set games
		List<String> gameNames = domainController.getGamesListCreatedByUser();
		ObservableList<String> gameNamesObservLst = FXCollections.observableArrayList(gameNames);
		lstGames.setItems(gameNamesObservLst);

		linkReturn.setText(domainController.translate("Return"));
		lblChooseGame.setText(domainController.translate("ChooseGameToEdit"));
		btnExit.setText("Menu");
	}

	@FXML
	public void linkReturnOnAction(ActionEvent event) {
		// redirecting to previous screen
		Stage stage = (Stage) linkReturn.getScene().getWindow();
		MenuScreenController root = new MenuScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}

	// Event Listener on ListView[#lstGames].onMouseClicked
	@FXML
	public void lstGamesChooseGame(MouseEvent event) {

		// select game
		String selectedItem = (String) lstGames.getSelectionModel().getSelectedItem();

		if (selectedItem == null) {
			return;
		}

		try {
			domainController.chooseGame(selectedItem);
			// open window
			Stage stage = (Stage) lstGames.getScene().getWindow();
			EditGameBoardsListScreenController root = new EditGameBoardsListScreenController(domainController);
			Scene scene = new Scene(root, 1000, 500);
			stage.setScene(scene);
		} catch (Exception e) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText(e.getMessage());
			errorAlert.showAndWait();
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

package gui;

import java.io.IOException;

import domein.DomainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewGameScreenController extends BaseScreenController{
	@FXML
	private Label lblCreateGame;
	@FXML
	private Label lblGameName;
	
	@FXML
	private TextField txtGameNameCreateNewGame;

	@FXML
	private Button btnSave;


	public NewGameScreenController(DomainController domainController) {
		super(domainController, "NewGameScreen.fxml");
	}

	@Override
	protected void loadData() {
		// set translations
		lblCreateGame.setText(domainController.translate("CreateGame"));
		lblGameName.setText(domainController.translate("NameGame"));
		btnSave.setText(domainController.translate("Save"));
	}

	private void saveNewGame() {
		Stage stage = (Stage) btnSave.getScene().getWindow();
		MenuScreenController root = new MenuScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}

	@FXML
	private void btnSaveOnAction(ActionEvent event) {

		var gamename = txtGameNameCreateNewGame.getText();

		try {
			//domainController.(gamename);
			saveNewGame();
		} catch (Exception e) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText(e.getMessage());
			errorAlert.showAndWait();
		}
	}

	
}

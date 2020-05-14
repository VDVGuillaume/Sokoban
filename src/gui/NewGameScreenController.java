package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import domein.DomainController;
import exceptions.GameException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewGameScreenController extends BaseScreenController{
	@FXML
	private Label lblCreateGame;
	@FXML
	private Label lblGameName;
	@FXML
	private Label lblGameException;
	@FXML
	private Button btnReturn;
	
	
	@FXML
	private TextField txtGameNameCreateNewGame;

	@FXML
	private Button btnSave;
	private static String imageFilePathDisk = "src\\resources\\images\\disk.png";
	
	@FXML
	private Button btnExit;


	public NewGameScreenController(DomainController domainController) {
		super(domainController, "NewGameScreen.fxml");
	}

	@Override
	protected void loadData() throws FileNotFoundException {
		// set translations
		FileInputStream isDisk = new FileInputStream(imageFilePathDisk);
		Image disk = new Image(isDisk,15,15,false, false);
		lblCreateGame.setText(domainController.translate("CreateGame"));
		lblGameName.setText(domainController.translate("NameGame"));
		btnSave.setText(domainController.translate("Save"));
		btnExit.setText("Menu");
		btnSave.setGraphic(new ImageView(disk));

	}

	private void saveNewGame() {
		Stage stage = (Stage) btnSave.getScene().getWindow();
		
		EditGameBoardScreenController root = new EditGameBoardScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
		
	}

	@FXML
	private void btnSaveOnAction(ActionEvent event) {

		var gameName = txtGameNameCreateNewGame.getText();

		try {
			domainController.createGame(gameName);
			domainController.saveGame();
			domainController.createGameBoard();
			saveNewGame();
		} catch (GameException e) {
			lblGameException.setText(e.getMessage());
		}
	}
	
	
	@FXML
	private void btnExitOnAction(ActionEvent event) {
		setMenuScreen(this.getScene());
	
	}

	
}

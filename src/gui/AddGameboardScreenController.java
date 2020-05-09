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


public class AddGameboardScreenController extends BaseScreenController{
	protected AddGameboardScreenController(DomainController domainController) {
		super(domainController, "AddGameboardScreen.fxml");
	}
	
	@Override
	protected void loadData() {
		// set games
		List<Integer> gameboardIds = domainController.getSelectedGameGameBoardIds();
		ObservableList<Integer> gameboardIdsObservLst = FXCollections.observableList(gameboardIds);
		lstGameboards.setItems(gameboardIdsObservLst); 
		linkReturn.setText("Exit");
		linkAddAnother.setText("Add Another Gameboard");
	}

	
	


	private ListView<Integer> lstGameboards;
	@FXML
	private Hyperlink linkReturn;
	@FXML
	private Hyperlink linkAddAnother;
	
	@FXML
	public void linkAddAnotherOnAction(ActionEvent event) {
		Stage stage = (Stage) linkAddAnother.getScene().getWindow();
		domainController.createGameBoard();
		EditGameBoardScreenController root = new EditGameBoardScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
		System.out.print("test");
		
		
	}
	

	// Event Listener on Hyperlink[#linkReturn].onAction
	@FXML
	public void linkReturnOnAction(ActionEvent event) {
		Stage stage = (Stage) linkReturn.getScene().getWindow();
		MenuScreenController root = new MenuScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}
	
	public void lstGameBoards (ActionEvent event) {
		
		
	}
}

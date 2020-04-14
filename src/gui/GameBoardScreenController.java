package gui;

import javafx.fxml.FXML;
import domein.DomainController;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.layout.GridPane;

public class GameBoardScreenController extends BaseScreenController {

	protected GameBoardScreenController(DomainController domainController) {
		super(domainController, "GameBoardScreen.fxml");
		
		gridPaneGameBoard = new GridPaneGameBoard();
	}

	@FXML
	private GridPane gridPaneGameBoard;
	
	@FXML
	protected void loadData() 
	{
		String[][] gameBoardState = domainController.getSelectedGameBoardState();
		refreshGameBoard(gameBoardState);
	}
	
	private void refreshGameBoard(String[][] tileGrid) 
	{
		var gameBoard = (GridPaneGameBoard) gridPaneGameBoard;
		gameBoard.refreshGameBoard(tileGrid);
	}
}

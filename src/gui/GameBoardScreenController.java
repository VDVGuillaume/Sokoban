package gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import domein.DomainController;

public class GameBoardScreenController extends BaseGameBoardScreenController {

	protected GameBoardScreenController(DomainController domainController) {
		super(domainController, "GameBoardScreen.fxml");
		initializeGameBoard(gridPaneGameBoard);
		loadData();
	}
	
	public void handleKeyEvent(KeyEvent key) 
	{
		switch(key.getCode()) 
		{
		case Q:
			domainController.move("Left");
			break;
		case Z:
			domainController.move("Up");
			break;
		case S:
			domainController.move("Down");
			break;
		case D:
			domainController.move("Right");
			break;
		case R:
			domainController.resetSelectedGameBoard();
			break;
		case T:
			this.getScene().getWindow().hide();
			return;
		default:
			break;
		}
		
		refreshGameBoard(domainController.getSelectedGameBoardState());
	}
	

	@FXML
	private GridPane gridPaneGameBoard;

	@Override
	protected void loadData() {
		String[][] gameBoardState = domainController.getSelectedGameBoardState();
		refreshGameBoard(gameBoardState);
	}
}
package gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
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
			quitGameBoard();
		default:
			break;
		}
		
		refreshGameBoard(domainController.getSelectedGameBoardState());
		lblMovesMade.setText(domainController.translate("GameBoardMovesMade") + domainController.getSelectedGameBoardMoves());

		if(domainController.getSelectedGameBoardCompleted()) 
		{
			// show message stating that the game is won.
			Alert informationAlert = new Alert(AlertType.INFORMATION);
			informationAlert.setHeaderText(domainController.translate("GameBoardCompleted").replace("$param1", String.valueOf(domainController.getSelectedGameBoardMoves())));
			informationAlert.showAndWait();
			
			quitGameBoard();
		}
	}
	
	private void quitGameBoard() 
	{
		// redirecting to previous screen
		Stage stage = (Stage) gridPaneGameBoard.getScene().getWindow();
		GameScreenController root = new GameScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}

	@FXML
	private GridPane gridPaneGameBoard;
	@FXML
	private Label lblPossibleMoves;
	@FXML
	private Label lblMovesMade;

	@Override
	protected void loadData() {
		String[][] gameBoardState = domainController.getSelectedGameBoardState();
		refreshGameBoard(gameBoardState);
		int moves = domainController.getSelectedGameBoardMoves();
		
		lblPossibleMoves.setText(domainController.translate("GameBoardPossibleMoves")
				.replace(',', '\n')
				.replace(":", ":\n"));
		lblMovesMade.setText(domainController.translate("GameBoardMovesMade") + moves);
	}
}
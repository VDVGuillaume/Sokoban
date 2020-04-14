package gui;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import domein.DomainController;

public class GameBoardScreenController extends BaseGameBoardScreenController {

	protected GameBoardScreenController(DomainController domainController) {
		super(domainController, "GameBoardScreen.fxml");
		initializeGameBoard(gridPaneGameBoard);
	}

	@FXML
	private GridPane gridPaneGameBoard;

	@Override
	protected void loadData() {
		String[][] gameBoardState = domainController.getSelectedGameBoardState();
		refreshGameBoard(gameBoardState);
	}
}

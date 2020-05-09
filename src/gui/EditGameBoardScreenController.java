package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import domein.DomainController;
import gui.BaseGameBoardScreenController.ImageViewSokoban;
import java.util.List;

public class EditGameBoardScreenController extends BaseGameBoardScreenController {
	@FXML
	private GridPane gridPaneGameBoard;
	@FXML
	private Label lblPossibleMoves;

	@FXML
	private Button btnSaveAndQuit;

	private ImageViewSokoban[][] imageViews;
	private boolean gameBoardIsInitialized;
	private double itemWidth;
	private double itemHeight;
	private int multipleGameBoards = 0;

	private String[] action = { "clear", "wall", "pawn", "goal", "box" };
	private int actionIndex = 0;
	private int[] currentIndices = { 0, 0 };

	protected EditGameBoardScreenController(DomainController domainController) {
		super(domainController, "EditGameBoardScreen.fxml");

		initializeGameBoard(gridPaneGameBoard);
		loadData();
	}

	private void quitGameBoard() {
		// redirecting to previous screen
		Stage stage = (Stage) gridPaneGameBoard.getScene().getWindow();
		GameScreenController root = new GameScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}

	private void saveAndQuit() {

	}

	@Override
	protected void initializeGameBoard(GridPane gridPaneGameBoard) {

		if (gameBoardIsInitialized) {
			return;
		}

		// #region set image views for gridPanel use
		imageViews = new ImageViewSokoban[10][10];

		for (int i = 0; i < 10; i++) {
			ColumnConstraints column = new ColumnConstraints();
			RowConstraints row = new RowConstraints();

			this.getColumnConstraints().add(column);
			this.getRowConstraints().add(row);
		}

		// initialize ImageView objects in imageviews
		for (int rowIndex = 0; rowIndex < 10; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 10; columnIndex++) {
				imageViews[rowIndex][columnIndex] = new ImageViewSokoban();
				ImageViewSokoban imgView = imageViews[rowIndex][columnIndex];
				imgView.minWidth(itemWidth);
				imgView.maxWidth(itemWidth);
				imgView.minHeight(itemHeight);
				imgView.maxHeight(itemHeight);

				imgView.setTileType("WallBorder");
				imgView.setImage(getImage("WallBorder"));
				imgView.setColumnIndex(columnIndex);
				imgView.setRowIndex(rowIndex);
				imgView.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
					int yCoordinate = imgView.getColumnIndex();
					int xCoordinate = imgView.getRowIndex();
					if (xCoordinate != currentIndices[0] || yCoordinate != currentIndices[1]) {
						actionIndex = 0;
					}
					domainController.setPositionAction(xCoordinate, yCoordinate, action[actionIndex]);
					actionIndex++;
					currentIndices[0] = xCoordinate;
					currentIndices[1] = yCoordinate;
					if (actionIndex == 5) {
						actionIndex = 0;
					}
					refreshGameBoard(domainController.getSelectedGameBoardState());
					event.consume();
				});

				// add ImageView object to GameBoard
				gridPaneGameBoard.add(imgView, columnIndex, rowIndex);
			}
		}

		gameBoardIsInitialized = true;
	}

	@Override
	protected void refreshGameBoard(String[][] tilesGrid) {

		if (imageViews == null) {
			return;
		}

		int rowIndex = 0;
		for (String[] tilesrow : tilesGrid) {
			int columnIndex = 0;
			for (String tile : tilesrow) {
				ImageViewSokoban imgView = imageViews[rowIndex][columnIndex];

				// if (imgView.tileHasChanged(tile)) {
				imgView.setImage(getImage(tile));
				// }

				columnIndex++;
			}

			rowIndex++;
		}
	}

	@Override
	protected void loadData() {
		String[][] gameBoardState = domainController.getSelectedGameBoardState();
		refreshGameBoard(gameBoardState);

	}

	@FXML
	private void btnSaveAndQuitOnAction(ActionEvent event) {

		try {
			domainController.saveTiles();
			List<Integer> gameBoardIds = domainController.getSelectedGameGameBoardIds();
			if (gameBoardIds.isEmpty()) {
				domainController.saveGame();
			}
			else {
				domainController.addGameboard();
			}
			
			Stage stage = (Stage) btnSaveAndQuit.getScene().getWindow();

			EditGameBoardsListScreenController root = new EditGameBoardsListScreenController(domainController);
			Scene scene = new Scene(root, 1000, 500);
			stage.setScene(scene);
		} catch (Exception e) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText(e.getMessage());
			errorAlert.showAndWait();
		}
	}
}

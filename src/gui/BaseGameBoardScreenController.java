package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import domein.DomainController;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public abstract class BaseGameBoardScreenController extends BaseScreenController {

	private ImageViewSokoban[][] imageViews;
	private Image imageWallNoBorder;
	private Image imageWallBorder;
	private Image imageGoal;
	private Image imageBox;
	private Image imageNone;
	private Image imagePawn;
	private boolean gameBoardIsInitialized;
	private double itemWidth;
	private double itemHeight;

	// create custom ImageView for gameboard UI
	protected class ImageViewSokoban extends ImageView {
		private String tileType;
		private int columnIndex;
		private int rowIndex;
		
		protected void setColumnIndex(int columnIndex) {
			this.columnIndex = columnIndex;
			
		}
		
		protected void setRowIndex(int rowIndex) {
			this.rowIndex = rowIndex;
			
		}
		
		protected int getColumnIndex() {
			return columnIndex;
		}
		
		protected int getRowIndex() {
			return rowIndex;
		}

		public String getTileType() {
			return tileType;
		};

		public void setTileType(String tileType) {
			this.tileType = tileType;
		}

		public boolean tileHasChanged(String newTileType) {
			return !tileType.equals(newTileType);
		}
	}

	protected BaseGameBoardScreenController(DomainController domainController, String resource) {
		super(domainController, resource);

		// #region set images
		// 4 items in width - width 192px
		// 8 items in height - height 384px
		int fileImageWidth = 192;
		int fileImageHeight = 384;
		itemWidth = fileImageWidth / 4;
		itemHeight = fileImageHeight / 8;
		BufferedImage bigImg;
		try {
			bigImg = ImageIO.read(new File("src/resources/images/sokoban.png"));

			BufferedImage biNone = bigImg.getSubimage((int) calculatePos(0, itemWidth),
					(int) calculatePos(0, itemHeight), (int) itemWidth, (int) itemHeight);
			BufferedImage biPawn = bigImg.getSubimage((int) calculatePos(1, itemWidth),
					(int) calculatePos(0, itemHeight), (int) itemWidth, (int) itemHeight);
			BufferedImage biGoal = bigImg.getSubimage((int) calculatePos(0, itemWidth),
					(int) calculatePos(1, itemHeight), (int) itemWidth, (int) itemHeight);
			BufferedImage biWallBorder = bigImg.getSubimage((int) calculatePos(0, itemWidth),
					(int) calculatePos(3, itemHeight), (int) itemWidth, (int) itemHeight);
			BufferedImage biWallNoBorder = bigImg.getSubimage((int) calculatePos(3, itemWidth),
					(int) calculatePos(2, itemHeight), (int) itemWidth, (int) itemHeight);
			BufferedImage biBox = bigImg.getSubimage((int) calculatePos(2, itemWidth),
					(int) calculatePos(0, itemHeight), (int) itemWidth, (int) itemHeight);

			imageWallNoBorder = SwingFXUtils.toFXImage(biWallNoBorder, null);
			imageWallBorder = SwingFXUtils.toFXImage(biWallBorder, null);
			imageGoal = SwingFXUtils.toFXImage(biGoal, null);
			imageBox = SwingFXUtils.toFXImage(biBox, null);
			imageNone = SwingFXUtils.toFXImage(biNone, null);
			imagePawn = SwingFXUtils.toFXImage(biPawn, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// #endregion set images
	}

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

				// add ImageView object to GameBoard
				gridPaneGameBoard.add(imgView, columnIndex, rowIndex);
			}
		}

		gameBoardIsInitialized = true;
	}

	private double calculatePos(int itemIndex, double distancePerItem) {
		return distancePerItem * itemIndex;
	}

	protected Image getImage(String tile) {
		switch (tile) {
		case "Wall":
			return imageWallNoBorder;
		case "WallBorder":
			return imageWallBorder;
		case "Goal":
			return imageGoal;
		case "Box":
			return imageBox;
		case "Pawn":
			return imagePawn;
		case "None":
		default:
			return imageNone;
		}
	}

	protected void refreshGameBoard(String[][] tilesGrid) {

		if (imageViews == null) {
			return;
		}

		int rowIndex = 0;
		for (String[] tilesrow : tilesGrid) {
			int columnIndex = 0;
			for (String tile : tilesrow) {
				ImageViewSokoban imgView = imageViews[rowIndex][columnIndex];

				if (imgView.tileHasChanged(tile)) {
					imgView.setImage(getImage(tile));
				}

				columnIndex++;
			}

			rowIndex++;
		}
	}
}

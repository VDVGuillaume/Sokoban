package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.GameException;

public class GameBoard {
	private Tile[][] originalTiles;
	private Tile[][] tiles;
	private boolean completed;
	private Pawn pawn;
	private int moves;
	private int id;

	/** UC3 constructor Gameboard w/ 2D array of tiles */
	public GameBoard(int id, Tile[][] tiles) {
		this.id = id;
		originalTiles = tiles;
		moves = 0;
		setTiles(cloneTiles(originalTiles));
	}

	/* UC5 constructor for creating a new gameboard */
	public GameBoard() {
		this.id = id;
		TileTypes wall = TileTypes.Wall;
		TileTypes none = TileTypes.None;

		Tile[][] initialTiles = new Tile[10][10];
		for (int row = 0; row < initialTiles.length; row++) {
			for (int col = 0; col < initialTiles[row].length; col++) {
				if (col == 5 && row == 5) {
					initialTiles[row][col] = new Tile(none, false, col, row);
				} else {
					initialTiles[row][col] = new Tile(wall, false, col, row);
				}

			}

		}
		tiles = initialTiles;
	}

	/**
	 * setPawn(int rowIndex, int columnIndex) creation of a pawn on
	 * (rowindex,columnindex) coordinates
	 */
	private void setPawn(int rowIndex, int columnIndex) {
		this.pawn = new Pawn(rowIndex, columnIndex);
	}

	/**
	 * validate if tile groups are connected to each other in nested function
	 */
	private boolean validateTileGroupsNested(ArrayList<Tile> tilesConnected, ArrayList<ArrayList<Tile>> tileGroupsNotYetConnected, Tile[][] tiles) {
		ArrayList<ArrayList<Tile>> tileGroupsStillNotConnected = new ArrayList<ArrayList<Tile>>();
		boolean tilesConnectedHasChanged = false;
		
		for (ArrayList<Tile> tileGroup : tileGroupsNotYetConnected) {

			// copy first tile group
			if (tilesConnected.size() == 0) {
				tilesConnected.addAll(tileGroup);
				tilesConnectedHasChanged = true;
				continue;
			}

			// control for connectivity
			boolean tileGroupIsConnected = false;
			for (Tile tile : tilesConnected) {
				int rowIndex = tile.getRowIndex();
				int columnIndex = tile.getColumnIndex();
				Tile tileLeft, tileRight, tileDown, tileUp;
				tileLeft = getTile(rowIndex - 1, columnIndex, tiles);
				tileRight = getTile(rowIndex + 1, columnIndex, tiles);
				tileDown = getTile(rowIndex, columnIndex + 1, tiles);
				tileUp = getTile(rowIndex, columnIndex - 1, tiles);

				if (tileGroup.contains(tileLeft) || tileGroup.contains(tileRight)
						|| tileGroup.contains(tileUp) || tileGroup.contains(tileDown)) {
					tileGroupIsConnected = true;
					break;
				}
			}

			// if connected, add tiles to tilesConnectedList
			if (tileGroupIsConnected) {
				tilesConnected.addAll(tileGroup);
				tilesConnectedHasChanged = true;
				continue;
			}
			
			// if not connected, add tileGroup to list of groups not yet connected
			tileGroupsStillNotConnected.add(tileGroup);
		}
		
		if(tileGroupsStillNotConnected.size() == 0) {
			// if no groups are left unconnected
			return true;
		}else if(tilesConnectedHasChanged && tileGroupsStillNotConnected.size() != 0) {
			// if connectedTiles has changed and there are still tile groups left to be connected
			return validateTileGroupsNested(tilesConnected, tileGroupsStillNotConnected, tiles);
		}else {
			// there remainder tile groups can't be connected
			return false;
		}
	}

	/**
	 * Validate if tile groups are connected to each other in some way
	 */
	private boolean validateTileGroups(ArrayList<ArrayList<Tile>> tileGroups, Tile[][] tiles) {
		ArrayList<Tile> tilesConnected = new ArrayList<Tile>();
		return validateTileGroupsNested(tilesConnected, tileGroups, tiles);
	}

	/**
	 * UC3 setTiles set the Tiletypes of the gameboard and perform checks such as
	 * there should be one and only one pawn, the number of boxes should be equal to
	 * the number of goals
	 */
	private void setTiles(Tile[][] tiles) throws GameException {
		if (tiles.length != 10 || tiles[0].length != 10) {
			throw new GameException("ErrorGameBoardTilesDimension");
		}

		int pawnCount = 0;
		int goalCount = 0;
		int boxCount = 0;
		int rowIndex = 0;

		int tileGroupsCount = 0;
		ArrayList<ArrayList<Tile>> tileGroups = new ArrayList<ArrayList<Tile>>();
		tileGroups.add(new ArrayList<Tile>());

		for (Tile[] tileRow : tiles) {
			int columnIndex = 0;
			for (Tile tile : tileRow) {
				TileTypes tileType = tile.getTileType();

				if (tileType != TileTypes.Wall) {
					Tile tileLeft, tileRight, tileDown, tileUp;
					tileLeft = getTile(rowIndex - 1, columnIndex, tiles);
					tileRight = getTile(rowIndex + 1, columnIndex, tiles);
					tileDown = getTile(rowIndex, columnIndex + 1, tiles);
					tileUp = getTile(rowIndex, columnIndex - 1, tiles);

					if (tileLeft == null || tileRight == null || tileDown == null || tileUp == null) {
						throw new GameException("ErrorGameBoardWallNotClosed");
					}

					// group tiles based on connectivity to each other
					boolean tileConnectedToGroup = false;
					for (ArrayList<Tile> tileGroup : tileGroups) {
						if (tileGroup.contains(tileLeft) || tileGroup.contains(tileRight) || tileGroup.contains(tileUp)
								|| tileGroup.contains(tileDown)) {
							tileConnectedToGroup = true;
							tileGroup.add(tile);
							break;
						}
					}

					if (!tileConnectedToGroup) {
						ArrayList<Tile> tileGroup = new ArrayList<Tile>();
						tileGroup.add(tile);
						tileGroups.add(tileGroup);
					}
				}

				switch (tileType) {
				case None:
					if (tile.getContainsPlayer()) {
						setPawn(rowIndex, columnIndex);
						pawnCount++;
					}
					break;
				case Goal:
					goalCount++;
					break;
				case Box:
					boxCount++;
					break;
				case Wall:
					break;
				}

				columnIndex++;
			}
			rowIndex++;
		}

		if (pawnCount != 1) {
			throw new GameException("ErrorGameBoardPawnLimit");
		}

		if (goalCount != boxCount) {
			throw new GameException("ErrorGameBoardGoalBoxCount");
		}
		
		if(!validateTileGroups(tileGroups, tiles)) {
			throw new GameException("ErrorGameBoardInaccesibleSpace");
		}

		this.tiles = tiles;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setCompleted() {
		for (Tile[] tileRow : tiles) {
			for (Tile tile : tileRow) {
				if (tile.getTileType() == TileTypes.Goal) {
					this.completed = false;
					return;
				}
			}
		}
		this.completed = true;
	}

	/**
	 * UC3 method getCompleted returns true if complete attribute of gameboard is
	 * true; else false;
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * UC3 getCurrentState returns the 2D array representation of the current
	 * gameboard
	 */
	public String[][] getCurrentState() {
		String[][] gameBoardState = new String[10][10];
		int rowIndex = 0;

		for (Tile[] tileRow : tiles) {
			int columnIndex = 0;
			for (Tile tile : tileRow) {
				TileTypes tileType = tile.getTileType();

				if (tile.getContainsPlayer()) {
					gameBoardState[rowIndex][columnIndex] = "Pawn";
				} else if (tileType == TileTypes.Wall && checkWallBorder(rowIndex, columnIndex)) {
					gameBoardState[rowIndex][columnIndex] = "WallBorder";
				} else {
					gameBoardState[rowIndex][columnIndex] = tile.getTileType().toString();
				}

				columnIndex++;
			}

			rowIndex++;
		}

		return gameBoardState;
	}

	/**
	 * UC3 checkWallBorder returns true if tile or surrounding tiles are of type
	 * wall
	 */
	private boolean checkWallBorder(int rowIndex, int columnIndex) {
		for (int i = rowIndex - 1; i <= rowIndex + 1; i++) {
			for (int j = columnIndex - 1; j <= columnIndex + 1; j++) {
				// skip not accessible spaces
				if (i < 0 || j < 0 || i > 9 || j > 9) {
					continue;
				}

				Tile tile = tiles[i][j];
				if (tile.getTileType() != TileTypes.Wall) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * UC4 move(String direction) checks whether direction are valid & calling
	 * move(GameBoardMoves move)
	 */
	public void move(String direction) {
		GameBoardMoves gameBoardMove;
		try {
			gameBoardMove = GameBoardMoves.valueOf(direction);
		} catch (IllegalArgumentException ex) {
			return;
		} catch (NullPointerException ex) {
			return;
		}

		move(gameBoardMove);
	}

	/**
	 * UC4 move(GameBoardMoves move) move pawn whilst checking whether move in that
	 * direction is valid
	 */
	private void move(GameBoardMoves move) {
		int rowIndexPawn = pawn.getRowIndex();
		int columnIndexPawn = pawn.getColumnIndex();

		int rowIndexMoveLocation = rowIndexPawn;
		int columnIndexMoveLocation = columnIndexPawn;
		int rowIndexMoveLocationExtended = rowIndexPawn;
		int columnIndexMoveLocationExtended = columnIndexPawn;

		switch (move) {
		case Left:
			columnIndexMoveLocation--;
			columnIndexMoveLocationExtended -= 2;
			break;
		case Right:
			columnIndexMoveLocation++;
			columnIndexMoveLocationExtended += 2;
			break;
		case Down:
			rowIndexMoveLocation++;
			rowIndexMoveLocationExtended += 2;
			break;
		case Up:
			rowIndexMoveLocation--;
			rowIndexMoveLocationExtended -= 2;
			break;
		}

		Tile tilePawn = getTile(rowIndexPawn, columnIndexPawn);
		Tile tileMoveLocation = getTile(rowIndexMoveLocation, columnIndexMoveLocation);
		Tile tileMoveLocationExtended = getTile(rowIndexMoveLocationExtended, columnIndexMoveLocationExtended);

		// if move is valid
		if (validateMove(tileMoveLocation, tileMoveLocationExtended)) {
			// if box exists in move location, move box
			if (tileMoveLocation.getTileType() == TileTypes.Box) {
				tileMoveLocationExtended.setTileType(tileMoveLocation.getTileType());
			}

			// move player to location
			tileMoveLocation.movePlayerToTile();
			pawn.movePawn(rowIndexMoveLocation, columnIndexMoveLocation);

			// remove player from original location
			tilePawn.setContainsPlayer(false);

			// check if gameboard is completed
			setCompleted();

			// update number of moves
			setMoves(getMoves() + 1);
		}
	}

	private Tile getTile(int rowIndex, int columnIndex) {
		return getTile(rowIndex, columnIndex, this.tiles);
	}

	private Tile getTile(int rowIndex, int columnIndex, Tile[][] tiles) {
		if (rowIndex < 0 || columnIndex < 0 || rowIndex > 9 || columnIndex > 9) {
			return null;
		}

		return tiles[rowIndex][columnIndex];
	}

	/**
	 * UC4 validateMove returns true if pawn can be moved in that direction (no
	 * wall, etc); else return false
	 */
	private boolean validateMove(Tile moveLocation, Tile moveLocationExtended) {
		if (moveLocation == null || moveLocation.getTileType() == TileTypes.Wall) {
			return false;
		}

		if (moveLocation.getTileType() == TileTypes.Box
				&& (moveLocationExtended == null || moveLocationExtended.getTileType() == TileTypes.Wall
						|| moveLocationExtended.getTileType() == TileTypes.Box)) {
			return false;
		}

		return true;
	}

	/** UC4 cloneTiles(Tile[][] tiles): clone the 2D array tiles */
	private Tile[][] cloneTiles(Tile[][] tiles) {
		Tile[][] clonedTiles = new Tile[10][10];

		int rowIndex = 0;
		for (Tile[] tileRow : tiles) {
			int columnIndex = 0;
			for (Tile tile : tileRow) {
				clonedTiles[rowIndex][columnIndex] = tile.clone();
				columnIndex++;
			}
			rowIndex++;
		}

		return clonedTiles;
	}

	/**
	 * UC4 resetGameBoard set the nr of moves to 0, set the tiles back to the
	 * original tiles, setCompleted to false
	 */
	public void resetGameBoard() {
		setMoves(0);
		setTiles(cloneTiles(originalTiles));
		setCompleted();
	}

	private void setMoves(int moves) {
		this.moves = moves;
	}

	public int getMoves() {
		return this.moves;
	}

	/**
	 * UC6 tile at (x,y) coordinates is updated to contain wall, goal, box, pawn or
	 * nothing
	 **/
	public void setPositionAction(int xCoord, int yCoord, String action) {

		if (xCoord > 9 || xCoord < 0 || yCoord > 9 || yCoord < 0) {

			throw new GameException("ErrorSelectionOutOfBounds");
		}

		action = action.toLowerCase();

		switch (action) {
		case "wall":
			TileTypes wall = TileTypes.Wall;
			tiles[xCoord][yCoord] = new Tile(wall, false, xCoord, yCoord);

			break;
		case "goal":
			TileTypes goal = TileTypes.Goal;
			tiles[xCoord][yCoord] = new Tile(goal, false, xCoord, yCoord);
			break;
		case "box":
			TileTypes box = TileTypes.Box;
			tiles[xCoord][yCoord] = new Tile(box, false, xCoord, yCoord);
			break;
		case "pawn":
			TileTypes pawn = TileTypes.None;
			tiles[xCoord][yCoord] = new Tile(pawn, true, xCoord, yCoord);
			break;
		case "clear":
			TileTypes none = TileTypes.None;
			tiles[xCoord][yCoord] = new Tile(none, false, xCoord, yCoord);
			break;
		default:
			throw new GameException("IncorrectAction");

		}

	}

	/** save gameboard by saving the changes to the tiles of the gameboard **/
	public void changeGameboard() {
		this.setTiles(tiles);
	}

	/** UC7 set Id of the gameboard */
	public void setId(int id) {
		this.id = id;
	}

	/** UC7 return int Id of the gameboard */
	public int getId() {
		return id;
	}

}

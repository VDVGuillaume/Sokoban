package domein;

import java.util.ArrayList;

import exceptions.GameException;

public class GameBoard 
{
	private Tile[][] originalTiles;
	private Tile[][] tiles;
	private boolean completed;
	private Pawn pawn;
	private int moves;
	private ArrayList<Integer> selector;

	/** UC3 constructor Gameboard w/ 2D array of tiles*/
	public GameBoard(Tile[][] tiles) 
	{
		originalTiles = tiles;
		moves = 0;
		setTiles(cloneTiles(originalTiles));
	}
	
	public GameBoard() {
		//empty constructor for testing UC5
		//expanded constructor functionality for UC6. Initialises an empty starter gameboard for customization
		
		TileTypes wall = TileTypes.Wall;
		TileTypes selector = TileTypes.Selector;
		this.selector = new ArrayList<>(4);
		this.selector.add(5);
		this.selector.add(5);
		this.selector.add(0);
		this.selector.add(0);
		
		Tile[][] initialTiles = new Tile[10][10];
        for (int row = 0; row < initialTiles.length; row++) {
            for (int col = 0; col < initialTiles[row].length; col++) {
            	if (!(col == 0 || col == 9 || row == 0 || row == 9)) {
            		if (col == 5 && row ==5) {
            			initialTiles[row][col] = new Tile (selector,false);
                  		}
            		else {
            			initialTiles[row][col] = new Tile(wall, false);
            		}
            		
                	
                
            	}

            	else
            	{
            		initialTiles[row][col] = new Tile(wall, false);
            	}
            }
        }
        

        
        
        this.tiles = initialTiles;
     
      



		
	}
	
	/**setPawn(int rowIndex, int columnIndex) creation of a pawn on (rowindex,columnindex) coordinates*/
	private void setPawn(int rowIndex, int columnIndex) 
	{
		this.pawn = new Pawn(rowIndex, columnIndex);
	}
	
	/** UC3 setTiles set the Tiletypes of the gameboard and perform checks such as there should be one and only one pawn, the number of boxes should be equal to the number of goals */
	private void setTiles(Tile[][] tiles) throws GameException 
	{
		if(tiles.length != 10 || tiles[0].length != 10) 
		{
			throw new GameException("ErrorGameBoardTilesDimension");
		}
		
		int pawnCount = 0;
		int goalCount = 0;
		int boxCount = 0;
		
		int rowIndex = 0;
		
		for(Tile[] tileRow : tiles) 
		{
			int columnIndex = 0;
			for(Tile tile : tileRow) 
			{
				TileTypes tileType = tile.getTileType();
				
				switch(tileType) 
				{
				case None:
					if(tile.getContainsPlayer()) 
					{
						setPawn(rowIndex, columnIndex);
						pawnCount++;	
					}
					break;
				case Goal:
					goalCount ++;
					break;
				case Box:
					boxCount++;
					break;
				}
				columnIndex++;
			}
			rowIndex++;
		}
		
		if(pawnCount != 1) 
		{
			throw new GameException("ErrorGameBoardPawnLimit");
		}
		
		if(goalCount != boxCount) 
		{
			throw new GameException("ErrorGameBoardGoalBoxCount");
		}
		
		//TODO VALIDATE IF ALL FIELDS ARE ACCESSIBLE IN CELLS  
		
		this.tiles = tiles;
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}
	
	public void setCompleted() {
		for(Tile[] tileRow : tiles) 
		{
			for(Tile tile : tileRow) 
			{
				if(tile.getTileType() == TileTypes.Goal) 
				{
					this.completed = false;
					return;
				}
			}
		}
		this.completed = true;
		}
	
	/**UC3 method getCompleted returns true if complete attribute of gameboard is true; else false;*/
	public boolean isCompleted() {
		return completed;
	}
	
	/**UC3 getCurrentState returns the 2D array representation of the current gameboard*/
	public String[][] getCurrentState()
	{
		String[][] gameBoardState = new String[10][10];
		int rowIndex = 0;
		
		for(Tile[] tileRow : tiles) 
		{
			int columnIndex = 0;
			for(Tile tile : tileRow) 
			{
				TileTypes tileType = tile.getTileType();
				
				if(tile.getContainsPlayer()) 
				{
					gameBoardState[rowIndex][columnIndex] = "Pawn";
				}else if(tileType == TileTypes.Wall && checkWallBorder(rowIndex, columnIndex)) 
				{
					gameBoardState[rowIndex][columnIndex] = "WallBorder";
				}else 
				{
					gameBoardState[rowIndex][columnIndex] = tile.getTileType().toString();
				}
				
				columnIndex++;
			}
			
			rowIndex++;
		}
		
		return gameBoardState;
	}
	
	/**UC3 checkWallBorder returns true if tile or surrounding tiles are of type wall*/
	private boolean checkWallBorder(int rowIndex, int columnIndex) 
	{
		for(int i = rowIndex -1; i <= rowIndex + 1; i++) 
		{
			for(int j = columnIndex - 1; j <= columnIndex + 1;j++) 
			{
				//skip not accessible spaces
				if(i < 0 || j < 0 || i > 9 || j > 9) 
				{
					continue;
				}
				
				Tile tile = tiles[i][j];
				if(tile.getTileType() != TileTypes.Wall) 
				{
					return true;
				}	
			}
		}
		return false;
	}
	
	/**UC4 move(String direction) checks whether direction are valid & calling move(GameBoardMoves move) */
	public void move(String direction) 
	{
		GameBoardMoves gameBoardMove;
		try 
		{
			gameBoardMove = GameBoardMoves.valueOf(direction);
		}catch(IllegalArgumentException ex) 
		{
			return;
		}catch(NullPointerException ex) 
		{
			return;
		}
		
		move(gameBoardMove);
	}
	
	/**UC4 move(GameBoardMoves move) move pawn whilst checking whether move in that direction is valid*/
	private void move(GameBoardMoves move) 
	{
		int rowIndexPawn = pawn.getRowIndex();
		int columnIndexPawn = pawn.getColumnIndex();
		
		int rowIndexMoveLocation = rowIndexPawn;
		int columnIndexMoveLocation = columnIndexPawn;
		int rowIndexMoveLocationExtended = rowIndexPawn;
		int columnIndexMoveLocationExtended = columnIndexPawn;	
				
		switch(move) 
		{
			case Left:
				columnIndexMoveLocation --;
				columnIndexMoveLocationExtended -= 2;
				break;
			case Right:
				columnIndexMoveLocation ++;
				columnIndexMoveLocationExtended += 2;
				break;
			case Down:
				rowIndexMoveLocation ++;
				rowIndexMoveLocationExtended += 2;
				break;
			case Up:
				rowIndexMoveLocation --;
				rowIndexMoveLocationExtended -= 2;
				break;
		}
		
		Tile tilePawn = getTile(rowIndexPawn, columnIndexPawn);
		Tile tileMoveLocation = getTile(rowIndexMoveLocation, columnIndexMoveLocation);
		Tile tileMoveLocationExtended = getTile(rowIndexMoveLocationExtended, columnIndexMoveLocationExtended);
		
		// if move is valid
		if(validateMove(tileMoveLocation, tileMoveLocationExtended)) 
		{
			// if box exists in move location, move box
			if(tileMoveLocation.getTileType() == TileTypes.Box) 
			{
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
		
	private Tile getTile(int rowIndex, int columnIndex) 
	{
		if(rowIndex < 0 || columnIndex < 0 || rowIndex > 9 || columnIndex > 9) 
		{
			return null;
		}
		
		return tiles[rowIndex][columnIndex];
	}
	
	/**UC4 validateMove returns true if pawn can be moved in that direction (no wall, etc); else return false*/
	private boolean validateMove(Tile moveLocation, Tile moveLocationExtended) 
	{
		if(moveLocation == null || moveLocation.getTileType() == TileTypes.Wall) 
		{
			return false;
		}
		
		if(moveLocation.getTileType() == TileTypes.Box &&
					(
						moveLocationExtended == null || 
						moveLocationExtended.getTileType() == TileTypes.Wall || 
						moveLocationExtended.getTileType() == TileTypes.Box
					)
				) 
		{
			return false;
		}
		
		return true;
	}
	
	/**UC4 cloneTiles(Tile[][] tiles): clone the 2D array tiles*/
	private Tile[][] cloneTiles(Tile[][] tiles) 
	{
		Tile[][] clonedTiles = new Tile[10][10];
		
		int rowIndex = 0;
		for(Tile[] tileRow : tiles) 
		{
			int columnIndex = 0;
			for(Tile tile : tileRow) 
			{
				clonedTiles[rowIndex][columnIndex] = tile.clone();
				columnIndex++;
			}
			rowIndex++;
		}
		
		return clonedTiles;
	}
	
	/**UC4 resetGameBoard set the nr of moves to 0, set the tiles back to the original tiles, setCompleted to false*/
	public void resetGameBoard() 
	{
		setMoves(0);
		setTiles(cloneTiles(originalTiles));
		setCompleted();
	}
	
	private void setMoves(int moves) 
	{
		this.moves = moves;
	}
	
	public int getMoves() 
	{
		return this.moves;
	}
	
	public void setPositionAction(int xCoord, int yCoord, String action) {
	
		if (xCoord > 9 || xCoord < 0 || yCoord > 9 || yCoord < 0) {
			
				throw new GameException("ErrorSelectionOutOfBounds");
			}
			
			action = action.toLowerCase();
		
			switch(action) 
			{
			case "wall":
				TileTypes wall = TileTypes.Wall;
				this.tiles[xCoord][yCoord] = new Tile(wall,false);
				
				break;
			case "goal":
				TileTypes goal = TileTypes.Wall;
				this.tiles[xCoord][yCoord] = new Tile(goal,false);
				break;
			case "box":
				TileTypes box = TileTypes.Box;
				this.tiles[xCoord][yCoord] = new Tile(box,false);
				break;
			case "pawn":
				TileTypes pawn = TileTypes.None;
				this.tiles[xCoord][yCoord] = new Tile(pawn,true);
				break;
			case "clear":
				TileTypes none = TileTypes.None;
				this.tiles[xCoord][yCoord] = new Tile(none,false);
			default:
				throw new GameException("IncorrectAction");
				
			}
		
		
	}
	
	public void moveSelector(String direction) 
	{
		GameBoardMoves gameBoardMove;
		try 
		{
			gameBoardMove = GameBoardMoves.valueOf(direction);
		}catch(IllegalArgumentException ex) 
		{
			return;
		}catch(NullPointerException ex) 
		{
			return;
		}
		
		moveSelector(gameBoardMove);
	}
	
	private void moveSelector(GameBoardMoves move) 
	{
		int rowIndexSelector = this.selector.get(0);
		int columnIndexSelector = this.selector.get(1);
		int selectorTileType = this.selector.get(2);
	
		
		int rowIndexMoveLocation = rowIndexSelector;
		int columnIndexMoveLocation = columnIndexSelector;
		
		
		Tile selectorLocation = getTile(rowIndexSelector, columnIndexSelector);
		
		switch(selectorTileType)
		{
			case 0:
				selectorLocation.setTileType(TileTypes.None);
				break;
			case 1:
				selectorLocation.setTileType(TileTypes.Box);
				break;
			case 2:
				selectorLocation.setTileType(TileTypes.Wall);
				break;
			case 3:
				selectorLocation.setTileType(TileTypes.None);
				selectorLocation.setContainsPlayer(true);
				break;
		}
				
		switch(move) 
		{
			case Left:
				columnIndexMoveLocation --;
			
				break;
			case Right:
				columnIndexMoveLocation ++;
	
				break;
			case Down:
				rowIndexMoveLocation ++;
	
				break;
			case Up:
				rowIndexMoveLocation --;
	
				break;
		}

		
		
		
		Tile tileMoveLocation = getTile(rowIndexMoveLocation, columnIndexMoveLocation);
		
		
	
		
		TileTypes tileType = tileMoveLocation.getTileType();
		
		
		switch(tileType)
		{
			case None:
				if(tileMoveLocation.getContainsPlayer())
				{
					this.selector.set(2, 3);
				}
				else {
				this.selector.set(2, 0);
				}
				
				
				
				break;
			case Box:
				this.selector.set(2, 1);
			
				break;
			case Wall:
				this.selector.set(2, 2);
		
				break;
		default:
			break;
		
		}
		
			
			// move player to location
			tileMoveLocation.setTileType(TileTypes.Selector);
			tileMoveLocation.setContainsPlayer(false);
			this.selector.set(0, rowIndexMoveLocation);
			this.selector.set(1, columnIndexMoveLocation);
			this.selector.set(3, 0);
			
		
	}
	
	public void Toggle(){
		int rowIndexSelector = this.selector.get(0);
		int columnIndexSelector = this.selector.get(1);
		int toggleType = this.selector.get(3);
		int rowIndexLocation = rowIndexSelector;
		int columnIndexLocation = columnIndexSelector;
		Tile tileLocation = getTile(rowIndexLocation, columnIndexLocation);
		if (toggleType > 3) {
			toggleType = 0;
		}
		switch(toggleType)
		{
			case 0:
				tileLocation.setTileType(TileTypes.None);
				tileLocation.setContainsPlayer(false);
				this.selector.set(2, 0);
				break;
			case 1:
				tileLocation.setTileType(TileTypes.Box);
				tileLocation.setContainsPlayer(false);
				this.selector.set(2, 1);
				break;
			case 2:
				tileLocation.setTileType(TileTypes.Wall);
				tileLocation.setContainsPlayer(false);
				this.selector.set(2, 2);
				break;
			case 3:
				tileLocation.setTileType(TileTypes.None);
				tileLocation.setContainsPlayer(true);
				this.selector.set(2, 3);
				break;
		}
		toggleType++;
		this.selector.set(3, toggleType);
		
		
	}
	
}

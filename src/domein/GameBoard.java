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

	public GameBoard(Tile[][] tiles) 
	{
		originalTiles = tiles;
		moves = 0;
		setTiles(cloneTiles(originalTiles));
	}
	
	public GameBoard() {
		//empty constructor for testing UC5
	}
	
	private void setPawn(int rowIndex, int columnIndex) 
	{
		this.pawn = new Pawn(rowIndex, columnIndex);
	}
	
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
	
	public boolean getCompleted() {
		return completed;
	}
	
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
			tileMoveLocation.setTileType(TileTypes.None);
			tileMoveLocation.setContainsPlayer(true);
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
	
	public void resetGameBoard() 
	{
		setMoves(0);
		setTiles(cloneTiles(originalTiles));
		setCompleted();
	}
	
	private void setMoves(int Moves) 
	{
		this.Moves = Moves;
	}
	
	public int getMoves() 
	{
		return this.Moves;
	}
}

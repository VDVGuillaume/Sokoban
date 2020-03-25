package domein;

import java.util.ArrayList;

import exceptions.GameException;

public class GameBoard 
{
	private Tile[][] tiles;
	private boolean completed;
	private Pawn pawn;

	public GameBoard(Tile[][] tiles) 
	{
		setTiles(tiles);
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
				// TODO REMOVE THIS JUNK CODE
				/*if(tile == null) 
				{
					
					boolean stopHere = true;
				}*/
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
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
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
				if(tile.getContainsPlayer()) 
				{
					gameBoardState[rowIndex][columnIndex] = "Player";
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
	
	private void move(GameBoardMoves move) 
	{
		int rowIndexPawn = pawn.getRowIndex();
		int columnIndexPawn = pawn.getColumnIndex();
		
		switch(move) 
		{
			case Left:
				break;
			case Right:
				break;
			case Down:
				break;
			case Up:
				break;
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
}

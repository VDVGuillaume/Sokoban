package ui;

import domein.GameBoard;
import domein.Tile;
import domein.TileTypes;

public class UiGameBoard 
{
	private GameBoard gameBoard;
	
	private String wallNoCollision;
	private String wallCollision;
	private String none;
	private String box;
	private String goal;
	private String pawn;
	
	public UiGameBoard(GameBoard gameBoard) 
	{
		wallNoCollision = "/";
		wallCollision = "X";
		goal = ".";
		box = "B";
		none = " ";
		pawn = "P";
		
		this.gameBoard = gameBoard;
	}
	
	public void drawConsole() 
	{
		int rowIndex = 0;
		for(Tile[] tileRow : gameBoard.getTiles()) 
		{
			int columnIndex = 0;
			for(Tile tile : tileRow) 
			{
				TileTypes tileType = tile.getTileType();
				String outputString;
				switch(tileType) 
				{
				case Wall:
					boolean collision = checkWallCollision(gameBoard.getTiles(), rowIndex, columnIndex);
					if(collision) 
					{
						outputString = wallCollision;
					}
					else 
					{
						outputString = wallNoCollision;
					}
					break;
				case Goal:
					outputString = goal;
					break;
				case Pawn:
					outputString = pawn;
					break;
				case Box:
					outputString = box;
					break;
				case None:
				default:
					outputString = none;
					break;
				}
				
				System.out.print(String.format("%c[%d;%df",outputString,rowIndex,columnIndex));
				columnIndex++;
			}
			rowIndex++;
		}
	}
	
	private boolean checkWallCollision(Tile[][] tiles, int rowIndex, int columnIndex) 
	{
		
		for(int i = rowIndex -1; i <= rowIndex + 1; rowIndex++) 
		{
			for(int j = columnIndex - 1; i <= columnIndex + 1;columnIndex++) 
			{
				//skip not accessible spaces
				if(rowIndex < 0 || columnIndex < 0 || rowIndex > 9 || columnIndex > 9) 
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
}
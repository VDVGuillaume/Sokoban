package ui;

import domein.GameBoard;
import domein.Tile;
import domein.TileTypes;

public class UiGameBoard 
{
	private GameBoard gameBoard;
	
	private String wallNoBorder;
	private String wallBorder;
	private String none;
	private String box;
	private String goal;
	private String pawn;
	
	public UiGameBoard(GameBoard gameBoard) 
	{
		wallNoBorder = "/ ";
		wallBorder = "X ";
		goal = ". ";
		box = "B ";
		none = "  ";
		pawn = "P ";
		
		this.gameBoard = gameBoard;
	}
	
	public void drawConsole() 
	{
		int rowIndex = 0;
		
		StringBuilder sb = new StringBuilder();
		
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
						outputString = wallBorder;
					}
					else 
					{
						outputString = wallNoBorder;
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
				
				sb.append(outputString);
				//TODO investigate how to create a fixed canvas on a cmd window
				//System.out.print(String.format("%s[%d;%df",outputString,rowIndex,columnIndex));
				columnIndex++;
			}
			sb.append("\n");
			rowIndex++;
		}
		
		System.out.println(sb.toString());
	}
	
	private boolean checkWallCollision(Tile[][] tiles, int rowIndex, int columnIndex) 
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
}
package ui;

import domein.GameBoard;
import domein.Tile;
import domein.TileTypes;

public class UiGameBoardConsole 
{
	private String wallNoBorder;
	private String wallBorder;
	private String none;
	private String box;
	private String goal;
	private String pawn;
	private String selector;
	
	public UiGameBoardConsole() 
	{
		wallNoBorder = "/ ";
		wallBorder = "X ";
		goal = ". ";
		box = "B ";
		none = "  ";
		pawn = "P ";
		selector = "* ";
		}
	
	
	public void drawConsole(String[][] tiles) 
	{		
		StringBuilder sb = new StringBuilder();
		
		for(String[] tileRow : tiles) 
		{
			for(String tile : tileRow) 
			{
				String outputString;
				switch(tile) 
				{
				case "Wall":
					outputString = wallNoBorder;
					break;
				case "WallBorder":
					outputString = wallBorder;
					break;
				case "Goal":
					outputString = goal;
					break;
				case "Box":
					outputString = box;
					break;
				case "Pawn":
					outputString = pawn;
					break;
				case "Selector":
					outputString = selector;
					break;
				case "None":
				default:
					outputString = none;
					break;
				}
				
				sb.append(outputString);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
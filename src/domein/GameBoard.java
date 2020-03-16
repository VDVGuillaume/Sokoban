package domein;

import java.util.ArrayList;

public class GameBoard 
{
	private Tile[][] tiles;
	private boolean completed;
	//private ArrayList<Wall> walls;// represented by 'X'
	//private ArrayList<Goal> goals;// represented by '.' (dot)
	//private ArrayList<Box> boxs;// represented by 'B'
	//private ArrayList<Field> fields;// empty field represented by ' ' (space)
	//private Pawn pawn;//represented by P
	private int gameBoardId;
	

	private String level
	="XXXXXXXXXX\n"
	+"XXXXX.XXXX\n"
	+"XXXXX XXXX\n"
	+"XXXXXBPB.X\n"
	+"X.   B XXX\n"
	+"XXXXXXBXXX\n"
	+"XXXXXX XXX\n"
	+"XXXXXX XXX\n"
	+"XXXXXX.XXX\n"
	+ "XXXXXXXXXX\n";
	
	public GameBoard(Tile[][] tiles) 
	{
		try {
			setTiles(tiles);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//TODO EDIT EXCEPTION TYPE TO CUSTOM EXCEPTION
	private void setTiles(Tile[][] tiles) throws Exception 
	{
		if(tiles.length != 10 || tiles[0].length != 10) 
		{
			throw new Exception("Dimensions of gameboard has to be 10x10");
		}
		
		int pawnCount = 0;
		int goalCount = 0;
		int boxCount = 0;
		
		for(Tile[] tileRow : tiles) 
		{
			for(Tile tile : tileRow) 
			{
				TileTypes tileType = tile.getTileType();
				switch(tileType) 
				{
				case Pawn:
					pawnCount++;
					break;
				case Goal:
					goalCount ++;
					break;
				case Box:
					boxCount++;
					break;
				}
			}
		}
		
		if(pawnCount != 1) 
		{
			throw new Exception("Only one character is allowed on a gameboard");
		}
		
		if(goalCount != boxCount) 
		{
			throw new Exception("amount of goals has to be equal to amount of chests");
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
	
	public boolean getCompleted(boolean completed) {
		return completed;
	}
	
	private void setgameBoardId(int gameBoardId) {
		this.gameBoardId=gameBoardId;
	}
	
	public int getgameBoardId() {
		return gameBoardId;
	}
}

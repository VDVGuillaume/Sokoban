package domein;

import java.util.ArrayList;

public class GameBoard 
{
	private Tile[][] tiles;
	private boolean completed;
	private ArrayList<Wall> walls;// represented by 'X'
	private ArrayList<Goal> goals;// represented by '.' (dot)
	private ArrayList<Box> boxs;// represented by 'B'
	private ArrayList<Field> fields;// empty field represented by ' ' (space)
	private Pawn pawn;//represented by P
	private int levelNr;

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
	
	//TODO EDIT EXCEPTION TYPE TO CUSTOM EXCEPTION
	private void setTiles(Tile[][] tiles) throws Exception {
		if(tiles.length != 10 || tiles[0].length != 10) 
		{
			throw new Exception("Dimensions of gameboard has to be 10x10");
		}
		
		//TODO VALIDATE IF THERE IS ONLY ONE CHARACTER IN CELLS
		
		//TODO VALIDATE IF ALL FIELDS ARE ACCESSIBLE IN CELLS
		
		//TODO VALIDATE IF AMOUNT OF GOALS == AMOUNT OF CHESTS IN CELLS  
		
		this.tiles = tiles;
	}
	
	private Tile[][] getTiles(){
		return tiles;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public boolean getCompleted(boolean completed) {
		return completed;
	}
}

package domein;

public class Pawn 
{
	private Tile tile;
	private int rowIndex;
	private int columnIndex;
	
	public Pawn(Tile tile, int rowIndex, int columnIndex) 
	{
		this.tile = tile;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}
	
	public void movePawn(int newRowIndex, int newColumnIndex) 
	{
		this.rowIndex = newRowIndex;
		this.columnIndex = newColumnIndex;
	}
	
	public int getRowIndex() 
	{
		return rowIndex;
	}
	
	public int getColumnIndex() 
	{
		return columnIndex;
	}
}

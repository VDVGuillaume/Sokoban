package domein;

public class Pawn 
{
	private int rowIndex;
	private int columnIndex;
	
	public Pawn(int rowIndex, int columnIndex) 
	{
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

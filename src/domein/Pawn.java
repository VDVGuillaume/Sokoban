package domein;

public class Pawn 
{
	private int rowIndex;
	private int columnIndex;
	
	/**UC3 Constructor for Pawn*/
	public Pawn(int rowIndex, int columnIndex) 
	{
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}
	
	/**UC3 movePawn method moves the pawn to certain (rowindex, columnindex)*/
	public void movePawn(int newRowIndex, int newColumnIndex) 
	{
		this.rowIndex = newRowIndex;
		this.columnIndex = newColumnIndex;
	}
	
	/**UC3 returns rowindex (int)*/
	public int getRowIndex() 
	{
		return rowIndex;
	}
	
	/**UC3 returns columnindex (int)*/
	public int getColumnIndex() 
	{
		return columnIndex;
	}
}

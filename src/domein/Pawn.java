package domein;

import exceptions.FieldException;

public class Pawn 
{
	private int rowIndex;
	private int columnIndex;
	
	/**UC3 Constructor for Pawn*/
	public Pawn(int rowIndex, int columnIndex) 
	{
		setRowIndex(rowIndex);
		setColumnIndex(columnIndex);
	}
	
	/**UC3 movePawn method moves the pawn to certain (rowindex, columnindex)*/
	public void movePawn(int newRowIndex, int newColumnIndex) 
	{
		setRowIndex(rowIndex);
		setColumnIndex(columnIndex);
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
	
	/**UC3 to set rowindex (int)*/
	private void setRowIndex(int rowIndex) 
	{
		if(rowIndex <1 || rowIndex >10) {
			throw new FieldException("IndexRange");	
		}
		this.rowIndex= rowIndex;
	}
	
	/**UC3 to set columnindex (int)*/
	private void setColumnIndex(int columnIndex)  
	{
		if(columnIndex <1 || columnIndex >10) {
			throw new FieldException("IndexRange");	
		}
		this.columnIndex= columnIndex;
	}
	
	
}
	

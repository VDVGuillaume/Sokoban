package domein;

public class Cell 
{
	private CellTypes cellType;
	private CellContents cellContent;
	
	
	public Cell(CellTypes cellType, CellContents cellContent) {
		//TODO
	}
	
	private void setCellType(CellTypes cellType) {
		this.cellType = cellType;
	}
	
	private CellTypes getCellType() {
		return cellType;
	}
	
	private void setCellContent(CellContents cellContent) {
		this.cellContent = cellContent;
	}		
}

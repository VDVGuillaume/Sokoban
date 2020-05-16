package domein;

public class Tile 
{
	private TileTypes tileType;
	private boolean containsPlayer;
	private boolean isGoal;
	private int columnIndex;
	private int rowIndex;
	
	/**UC3 constructor Tile w/ tileType & containsplayer attributes*/
	public Tile(TileTypes tileType, boolean containsPlayer, int columnIndex, int rowIndex) 
	{
		if(tileType == TileTypes.Goal) {
			isGoal = true;
		}
		setTileType(tileType);
		setContainsPlayer(containsPlayer);
		this.columnIndex = columnIndex;
		this.rowIndex = rowIndex;
	}
	
	/**UC3 determines tileType of certain tile*/
	public final void setTileType(TileTypes tileType) 
	{
		this.tileType = tileType;
	}
	
	/**movePlayerToTile moves player to tile by using the setContainsPlayer and updating tiletype**/
	public void movePlayerToTile() 
	{
		setContainsPlayer(true);
		if(isGoal) {
			setTileType(TileTypes.Goal);
		}else {
			setTileType(TileTypes.None);
		}
	}
	
	/**UC3 determines whether certain tile contains the player*/
	public final void setContainsPlayer(boolean containsPlayer) 
	{
		this.containsPlayer = containsPlayer;
	}
	
	/**UC3 returns true in case the pawn is present on the tile*/
	public boolean getContainsPlayer() 
	{
		return this.containsPlayer;
	}	
	
	/**UC3 returns the tileType of a given tile*/
	public TileTypes getTileType() {
		return tileType;
	}
	
	public Tile clone() 
	{
		return new Tile(tileType, containsPlayer, columnIndex, rowIndex);
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}
}

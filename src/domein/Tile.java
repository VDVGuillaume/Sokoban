package domein;

public class Tile 
{
	private TileTypes tileType;
	private boolean containsPlayer;
	private boolean isGoal;
	
	/**UC3 constructor Tile w/ tileType & containsplayer attributes*/
	public Tile(TileTypes tileType, boolean containsPlayer) 
	{
		if(tileType == TileTypes.Goal) {
			isGoal = true;
		}
		setTileType(tileType);
		setContainsPlayer(containsPlayer);
	}
	
	/**UC3 determines tileType of certain tile*/
	public void setTileType(TileTypes tileType) 
	{
		this.tileType = tileType;
	}
	
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
	public void setContainsPlayer(boolean containsPlayer) 
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
		return new Tile(tileType, containsPlayer);
	}
}

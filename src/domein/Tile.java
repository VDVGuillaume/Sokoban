package domein;

public class Tile 
{
	private TileTypes tileType;
	private boolean containsPlayer;
	private boolean isGoal;
	
	public Tile(TileTypes tileType, boolean containsPlayer) 
	{
		if(tileType == TileTypes.Goal) {
			isGoal = true;
		}
		setTileType(tileType);
		setContainsPlayer(containsPlayer);
	}
	
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
	
	public void setContainsPlayer(boolean containsPlayer) 
	{
		this.containsPlayer = containsPlayer;
	}
	
	public boolean getContainsPlayer() 
	{
		return this.containsPlayer;
	}	
	
	public TileTypes getTileType() {
		return tileType;
	}
	
	public Tile clone() 
	{
		return new Tile(tileType, containsPlayer);
	}
}

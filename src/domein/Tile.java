package domein;

public class Tile 
{
	private TileTypes tileType;
	private boolean containsPlayer;
	
	public Tile(TileTypes tileType, boolean containsPlayer) 
	{
		setTileType(tileType);
		setContainsPlayer(containsPlayer);
	}
	
	public void setTileType(TileTypes tileType) 
	{
		this.tileType = tileType;
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
}

package domein;

public class Tile 
{
	private TileTypes tileType;
	
	public Tile(TileTypes tileType) {
		setTileType(tileType);
	}
	
	private void setTileType(TileTypes tileType) {
		this.tileType = tileType;
	}
	
	public TileTypes getTileType() {
		return tileType;
	}
}

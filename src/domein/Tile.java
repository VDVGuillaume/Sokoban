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
	
	private TileTypes getTileType() {
		return tileType;
	}
}

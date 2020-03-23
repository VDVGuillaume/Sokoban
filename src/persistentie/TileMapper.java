package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domein.Tile;
import domein.TileTypes;

public class TileMapper extends BaseMapper 
{
	public Tile[][] getTiles(int gameBoardId)
	{
		Tile[][] tiles = new Tile[10][10];
		
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "select row_index, column_index, type, contains_player from TILE where gameboard_id = ? order by row_index, column_index";
		
		try 
		{
			conn = createConnection(); 
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, gameBoardId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) 
			{	
				var rowIndex = rs.getInt(1);
				var columnIndex = rs.getInt(2);
				TileTypes type = TileTypes.valueOf(rs.getString(3));
				var containsPlayer = rs.getBoolean(4);
				
				tiles[rowIndex][columnIndex] = new Tile(type, containsPlayer);
			}		
		} catch (SQLException e) 
		{
			// java...
			e.printStackTrace();
		}finally 
		{
			try 
			{
				if(stmt != null) stmt.close();
			}catch(SQLException e) 
			{
				// java...
				e.printStackTrace();
			}
			try 
			{
				if(conn != null) conn.close();
			}
			catch(SQLException e) 
			{
				// java...
				e.printStackTrace();
			}
		}
		
		return tiles;
	}
}

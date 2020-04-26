package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Game;
import domein.GameBoard;
import domein.User;

public class GameBoardMapper extends BaseMapper {
	
	private TileMapper tileMapper;
	
	/*constructor GameBoardMapper*/
	public GameBoardMapper() 
	{
		tileMapper = new TileMapper();
	}
	
	/* method getGameBoards(String gameName) return list of Gameboards related to a given gamename*/ 
	public List<GameBoard> getGameBoards(String gameName)
	{
		List<GameBoard> gameBoards = new ArrayList<GameBoard>();
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "select id from GAMEBOARD where GameName = ?";
		
		try 
		{
			conn = createConnection(); 
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, gameName);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) 
			{	
				var gameBoardId = rs.getInt(1);
				var tiles = tileMapper.getTiles(gameBoardId);
				gameBoards.add(new GameBoard(tiles));
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
		
		return gameBoards;	
	}		
	
	/*method saveGameBoard(Game game) to save GameObject in DB*/
	public void saveGameBoard(Game game) {
		

		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "INSERT INTO GAMEBOARD (GameName) VALUES (?)";
		
		try 
		{
			conn = createConnection(); 
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, game.getName());
			
			stmt.executeUpdate();
		
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
	
	
	}
	
	
}

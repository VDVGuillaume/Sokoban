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

public class GameMapper extends BaseMapper 
{
	private GameBoardMapper gameBoardMapper;
	
	public GameMapper() 
	{
		gameBoardMapper = new GameBoardMapper();
	}
	
	public List<Game> getGames()
	{
		List<Game> games = new ArrayList<Game>();
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "select id, name from GAME order by id";
		
		try 
		{
			conn = createConnection(); 
			stmt = conn.prepareStatement(sql);
						
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) 
			{	
				var gameId = rs.getInt(1);
				var gameName = rs.getString(2);
				var gameBoards = gameBoardMapper.getGameBoards(gameId);
				games.add(new Game(gameName, gameBoards));
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
		
		return games;	
	}
	
	public void createGame(Game game) 
	{
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "INSERT INTO GAME(name) VALUES (?)";
		
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

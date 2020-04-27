package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domein.Game;
import domein.GameBoard;
import domein.User;

public class GameBoardMapper extends BaseMapper {
	
	private TileMapper tileMapper;
	
	/**constructor GameBoardMapper*/
	public GameBoardMapper() 
	{
		tileMapper = new TileMapper();
	}
	
	/** method getGameBoards(String gameName) return list of Gameboards related to a given gamename*/ 
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
				gameBoards.add(new GameBoard(gameBoardId, tiles));
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
	
	/** method saveGameBoard(Game game) to save GameObject in DB*/
	public int saveGameBoard(Game game) {
		
		int generatedKey=-1;
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "INSERT INTO GAMEBOARD (GameName) VALUES (?)";
		
		try 
		{
			conn = createConnection(); 
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, game.getName());
			
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			generatedKey = rs.getInt(1);
			
			
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
			return generatedKey;
		}
	
	
	}
	
public void addGameBoard(Game game, GameBoard gameBoard) {
		
	
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		Connection conn = null;
		Connection conn2 = null;
		final String sql = "INSERT INTO GAMEBOARD (GameName) VALUES (?)";
		final String sql2 =  "select id from GAMEBOARD where GameName = ?";
	
		
		try 
		{
			conn = createConnection();
			conn2 = createConnection();
			stmt1 = conn.prepareStatement(sql);
			stmt2 = conn2.prepareStatement(sql2);
			
		
			stmt1.setString(1, game.getName());
			stmt2.setString(1, game.getName());
			
			stmt1.executeUpdate();
			
			ResultSet rs = stmt2.executeQuery();
			while(rs.next()) 
			{	
				var gameBoardId = rs.getInt(1);
				tileMapper.insertTiles(gameBoard, gameBoardId);
				
	
			}
			
		
			
			
		} catch (SQLException e) 
		{
			// java...
			e.printStackTrace();
		}finally 
		{
			try 
			{
				if(stmt1 != null) stmt1.close();
				if(stmt2 != null) stmt2.close();
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

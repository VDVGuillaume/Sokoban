package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import domein.Game;
import domein.GameBoard;

public class GameBoardMapper extends BaseMapper {
	
		public List<GameBoard> getGameBoards()
		{
			return null;
			//TODO
		}
		
		/*public void createGameBoard(GameBoard gameBoard) 
		{
			PreparedStatement stmt = null;
			Connection conn = null;
			final String sql = "INSERT INTO GAMEBOARD(name) VALUES (?)";
			
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
		
		public Game getGame(String name) 
		{
			PreparedStatement stmt = null;
			Connection conn = null;
			final String sql = "SELECT name FROM GAME WHERE name = ?";
			
			try 
			{
				conn = createConnection(); 
				stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, name);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()) 
				{	
					return new Game(name); //updated in UC2 to match added attributes in user
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
			
			return null;
		}	*/

}

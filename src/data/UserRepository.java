package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserRepository extends BaseRepository 
{	
	public void createUser(DbUser user) 
	{
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "INSERT INTO USER(username, password_hashed, salt, is_admin) VALUES (?, ?, ?, ?)";
		
		try 
		{
		conn = createConnection(); 
		stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPasswordHashed());
		stmt.setString(3, user.getSalt());
		stmt.setBoolean(4, user.getIsAdmin());
		
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
	
	public DbUser getUser(String username) 
	{
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "SELECT password_hashed, salt, is_admin FROM USER WHERE username = ?";
		
		try 
		{
			conn = createConnection(); 
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) 
			{
				return new DbUser(username, rs.getString(1), rs.getString(2), rs.getBoolean(3));
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
	}	
}

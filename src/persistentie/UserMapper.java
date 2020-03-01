package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserMapper extends BaseMapper 
{	
	public void createUser(DbUser user) 
	{
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "INSERT INTO USER(username, password_hashed, salt, is_admin, name, firstname) VALUES (?, ?, ?, ?, ?, ?)"; // UC2: updated with new attributes
		
		try 
		{
			conn = createConnection(); 
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPasswordHashed());
			stmt.setString(3, user.getSalt());
			stmt.setBoolean(4, user.getIsAdmin());
			stmt.setString(5, user.getName()); //UC2 : Added new attribute
			stmt.setString(6, user.getFirstName()); //UC2 : Added new attribute
			
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
		final String sql = "SELECT password_hashed, salt, is_admin, name, firstname FROM USER WHERE username = ?";
		
		try 
		{
			conn = createConnection(); 
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) 
			{
				return new DbUser(username, rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5)); //updated in UC2 to match added attributes in user
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

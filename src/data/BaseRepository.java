package data;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class BaseRepository 
{	
	protected String connString = "jdbc:mysql://localhost:3306/sokoban";
	protected String username = "sokoban_user";
	protected String password = "sokoban";
	
	protected Connection createConnection()
	{
		try 
		{
			Connection conn = DriverManager.getConnection(connString, username, password);
			return conn;
		}catch(Exception e) 
		{
			// java...
			e.printStackTrace();
		}
		
		return null;
	}
}

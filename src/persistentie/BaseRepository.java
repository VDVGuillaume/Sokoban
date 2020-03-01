package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class BaseRepository 
{	
	protected String connString = "jdbc:mysql://ID222177_g88.db.webhosting.be:3306/ID222177_g88?serverTimezone=UTC&useLegacyDatetimeCode=false&user=ID222177_g88&password=RisEnu9s";

	protected Connection createConnection()
	{
		try 
		{
			Connection conn = DriverManager.getConnection(connString);
			return conn;
		}catch(Exception e) 
		{
			// java...
			e.printStackTrace();
		}
		
		return null;
	}
}

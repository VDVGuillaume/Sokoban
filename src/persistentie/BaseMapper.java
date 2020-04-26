package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class BaseMapper 
{	
	protected String connString = "jdbc:mysql://ID222177_g88.db.webhosting.be:3306/ID222177_g88?serverTimezone=UTC&useLegacyDatetimeCode=false&user=ID222177_g88&password=RisEnu9s";

	/** UC1 creation of the connection to the mysql DB*/
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

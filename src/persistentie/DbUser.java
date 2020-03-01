package persistentie;

public class DbUser 
{
	private String username;
	private String passwordHashed;
	private String salt;
	private boolean isAdmin;
	private String name; // added in UC2
	private String firstName; // added in UC2
	
	public DbUser(String username, String passwordHashed, String salt, boolean isAdmin, String name, String firstName) // added name, firstName in UC2
	{
		this.username = username;
		this.passwordHashed = passwordHashed;
		this.salt = salt;
		this.isAdmin = isAdmin;
		this.name = name; // added in UC2
		this.firstName = firstName; // added in UC2
		
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public String getPasswordHashed() 
	{
		return passwordHashed;
	}
	
	public String getSalt() 
	{
		return salt;
	}
	
	public boolean getIsAdmin() 
	{
		return isAdmin;
	}
	
	/* UC2 */
	public String getName() 
	{
		return name;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
}

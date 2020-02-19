package data;

public class DbUser 
{
	private String username;
	private String passwordHashed;
	private String salt;
	private boolean isAdmin;
	
	public DbUser(String username, String passwordHashed, String salt, boolean isAdmin) 
	{
		this.username = username;
		this.passwordHashed = passwordHashed;
		this.salt = salt;
		this.isAdmin = isAdmin;
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
	
}

package data;

import domein.User;

public class UserCatalog 
{
	private UserRepository userRepo = new UserRepository(); 
	
	public void createUser(String username, String password, boolean is_admin) 
	{
		String salt = Security.getNextSalt();
		String passwordHashed = Security.hash(password, salt.getBytes());
		
		DbUser dbUser = new DbUser(username, passwordHashed, salt, is_admin);
		
		userRepo.createUser(dbUser);
	}
	
	public User login(String username, String password) 
	{
		//get user in db
		DbUser dbUser = userRepo.getUser(username);
		
		if(dbUser == null) 
		{
			return null;
		}
		
		String salt = dbUser.getSalt();
		String passwordHashed = Security.hash(password, salt.getBytes());
		
		if(passwordHashed.equals(dbUser.getPasswordHashed())) 
		{
			return new User(username, passwordHashed, dbUser.getIsAdmin());
		}
		
		return null;
	}
}

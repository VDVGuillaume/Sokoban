package domein;

import exceptions.PasswordException;
import persistentie.DbUser;
import persistentie.Security;

public class UserMapper 
{
	private UserRepository userRepo = new UserRepository();
	
	public boolean userExists(String username) {
		DbUser dbUser = userRepo.getUser(username);
		if (dbUser == null)
		{
			return false;
		}
		else
		{
			return true;
		}
		
		
	}
	
	public void createUser(String username, String password, boolean is_admin, String name, String firstName) // added name, firstName in UC2
	{	
		String passwordRequirement = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
		if(password.matches(passwordRequirement)) {


		}else {
			throw new PasswordException("Password doesn't meet the complexity requirements");
		}

		String salt = Security.getNextSalt();
		String passwordHashed = Security.hash(password, salt.getBytes());
		
		
		DbUser dbUser = new DbUser(username, passwordHashed, salt, is_admin, name, firstName); 
		
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
			return new User(username, passwordHashed, dbUser.getIsAdmin(), dbUser.getName(), dbUser.getFirstName());
		}
		
		return null;
	}
}

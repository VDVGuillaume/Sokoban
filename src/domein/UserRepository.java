package domein;

import exceptions.PasswordException;
import persistentie.DbUser;
import persistentie.Security;
import persistentie.UserMapper;

public class UserRepository 
{
	private UserMapper userMapper = new UserMapper();
	
	public boolean userExists(String username) {
		DbUser dbUser = userMapper.getUser(username);
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
		
		userMapper.createUser(dbUser);
	}
	
	public User login(String username, String password) 
	{
		//get user in db
		DbUser dbUser = userMapper.getUser(username);
		
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

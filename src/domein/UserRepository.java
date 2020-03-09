package domein;

import exceptions.PasswordException;
import persistentie.DbUser;
import persistentie.UserMapper;
import util.Security;

public class UserRepository 
{
	private UserMapper userMapper;
	
	public UserRepository(){
		this.userMapper = new UserMapper();
	}
	
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
	
	public void createUser(User user) // added name, firstName in UC2
	{	
		String salt = Security.getNextSalt();
		String passwordHashed = Security.hash(user.getPassword(), salt.getBytes());
		
		DbUser dbUser = new DbUser(user.getUsername(), passwordHashed, salt, user.getAdmin(), user.getName(), user.getFirstName()); 
		
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
			return new User(username, password, dbUser.getIsAdmin(), dbUser.getName(), dbUser.getFirstName());
		}
		
		return null;
	}
}

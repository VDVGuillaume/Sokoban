package domein;

import persistentie.UserMapper;
import util.Security;

public class UserRepository 
{
	private UserMapper userMapper;
	private User selectedUser;
	
	/*UC1 constructor UserRepository*/
	public UserRepository(){
		this.userMapper = new UserMapper();
	}
	
	public boolean userExists(String username) {
		User user = userMapper.getUser(username);
		if (user == null)
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
		userMapper.createUser(user);
	}
	
	/*UC1 method checkUser(String username, String password): get the salt related to the username, hashing of the password with the salt, comparison of the newly hashed password w/ hashed password in the DB; if matches -> login, if not a match -> exception */
	public boolean checkUser(String username, String password) 
	{
		//get user in db
		User user = userMapper.getUser(username);
		
		if(user == null) 
		{
			return false;
		}
		
		String salt = user.getSalt();
		String passwordHashed = Security.hash(password, salt.getBytes());
		
		if(passwordHashed.equals(user.getPasswordHashed())) 
		{
			return true;
			
			
			
			
		}
		
		return false;
	}
	
	/*UC1 User getUser(String username): get Userobject based on username string*/
	public User getUser(String username)
	{
		//get user in db
		User user = userMapper.getUser(username);
		
			return user;
		
			
		
	}
}

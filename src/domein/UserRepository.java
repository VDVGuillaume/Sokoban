package domein;

import java.util.*;

public class UserRepository {

	private UserMapper userMapper;
	private Collection<User> users;

	public boolean validatePassword(String username, String password) {
		return userMapper.search(username).validatePassword(password);
	
			
	}

	public void retrieveUser(String username) {
		
		/* GVDV Not so sure about this one, shouldn't it give a User object? */ 
		
		userMapper.search(username);
	}
}

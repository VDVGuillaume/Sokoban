package domein;

import java.util.*;

public class UserRepository {

	private UserMapper userMapper;
	private Collection<User> users;

	public boolean validatePassword(String username, String password) {
		userMapper = new UserMapper();
		User user = userMapper.search(username);
		return user.validatePassword(password);
	
			
	}

	public void retrieveUser(String username) {
		
		/* GVDV Not so sure about this one, shouldn't it give a User object? */ 
		
		userMapper.search(username);
	}
}

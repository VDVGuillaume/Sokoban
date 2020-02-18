package domein;

import java.util.*;

public class UserRepository {

	private UserMapper UserMapper;
	private Collection<User> users;

	public boolean validatePassword(String username, String password) {
		throw new UnsupportedOperationException();
	}

	public void retrieveUser(String username) {
		throw new UnsupportedOperationException();
	}
}

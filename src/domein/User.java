package domein;

public class User {

	private boolean admin;
	private String username;
	private String password;

	public String getUsername() {
		return this.username;
	}

	public boolean validatePassword(String password) {
		throw new UnsupportedOperationException();
	}

	public boolean isAdmin() {
		return this.admin;
	}

	public User(String username, String password, boolean admin) {
		
	}
}

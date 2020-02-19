package domein;

import exceptions.PasswordException;

public class User {

	private boolean admin;
	private String username;
	private String password;

	public String getUsername() {
		return this.username;
	}

	public boolean validatePassword(String password) {
		/*GVDV probably needs to throw an exception*/
		
		 if(this.password.equals(password)) {
			 return true;
		 }else {
			 throw new PasswordException("Still needs an appropriate error message");
		 }		 
	}

	public boolean isAdmin() {
		return this.admin;
	}

	public User(String username, String password, boolean admin) {
		this.username = username;
		this.password = password;
		this.admin = admin;
		
	}
}

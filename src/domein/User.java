package domein;

import exceptions.PasswordException;

public class User {

	private boolean admin;
	private String username;
	private String password;
	private String name;
	private String firstName;

	public boolean getAdmin() {
		return admin;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFirstName() {
		return firstName;
	}	
	
	private void setPassword(String password) 
	{
		String passwordRequirement = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
		if(password.matches(passwordRequirement)) {
			this.password = password;
		}else {
			throw new PasswordException("Password doesn't meet the complexity requirements");
		}
	}

	public boolean validatePassword(String password) 
	{
		/*GVDV probably needs to throw an exception*/
		 if(this.password.equals(password)) 
		 {
			 return true;
		 }else 
		 {
			 throw new PasswordException("Still needs an appropriate error message");
		 }		 
	}

	public boolean isAdmin() {
		return this.admin;
	}
	
	/*
	public User(String username, String password, boolean admin) {
		this.username = username;
		this.password = password;
		this.admin = admin;
		
	} /*
	
	/* UC2 */
	public User(String username, String password, boolean admin, String name, String firstName) 
	{
		this.username = username;
		setPassword(password);
		this.admin = admin;
		this.name = name;
		this.firstName = firstName;		
	}
}

package domein;

import exceptions.PasswordException;
import exceptions.UsernameException;

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
	
	private void setUsername(String username){
		if(username.length() < 8) {
			throw new UsernameException("Username needs to be at least 8 characters");
		}
		
		this.username = username;
	}
	
	private void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	private void setFirstName(String firstName) {
		this.firstName = firstName;
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
	
	/* UC2 */
	public User(String username, String password, boolean admin, String name, String firstName) 
	{
		setUsername(username);
		setPassword(password);
		setAdmin(admin);
		setName(name);
		setFirstName(firstName);		
	}
}

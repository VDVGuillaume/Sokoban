package domein;

import java.util.ResourceBundle;

import exceptions.PasswordException;
import exceptions.UsernameException;
import util.Language;

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
			throw new PasswordException(ResourceBundle.getBundle("resources/MessagesBundle", Language.getLanguage()).getString("PasswordNotComplexityRequirements"));
		}
	}
	
	private void setUsername(String username){
		if(username.length() < 8) {
			throw new UsernameException(ResourceBundle.getBundle("resources/MessagesBundle", Language.getLanguage()).getString("PasswordValidationFailed"));
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

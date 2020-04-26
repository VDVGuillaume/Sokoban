package domein;

import java.util.ResourceBundle;

import exceptions.PasswordException;
import exceptions.UsernameException;
import util.Language;
import util.Security;

public class User {

	private boolean admin;
	private String username;
	private String password;
	private String name;
	private String firstName;

	private String passwordHashed;
	private String salt; 

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
		boolean hasLengthRequirement = password.length() >= 8;
		boolean hasUppercase = !password.equals(password.toLowerCase());
		boolean hasLowercase = !password.equals(password.toUpperCase());
		boolean containsDigit = password.matches(".*\\d.*");
		
		if(!hasLengthRequirement) 
		{
			throw new PasswordException("ErrorPasswordLength");
		}
		
		if(!hasUppercase) 
		{
			throw new PasswordException("ErrorPasswordAtLeast1UpperChar");
		}
		
		if(!hasLowercase) 
		{
			throw new PasswordException("ErrorPasswordAtLeast1LowerChar");
		}
		
		if(!containsDigit) 
		{
			throw new PasswordException("ErrorPasswordAtLeast1Number");
		}
		
		this.password = password;
	}
	
	private void setUsername(String username){
		if(username.length() < 8) {
			throw new UsernameException("ErrorUserNameLength");
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
	
	private void setSalt(String salt) 
	{
		this.salt = salt;
	}
	
	private void setPasswordHashed(String passwordHashed) 
	{
		this.passwordHashed = passwordHashed;
	}
	
	public String getSalt() 
	{
		return this.salt;
	}
	
	public String getPasswordHashed() 
	{
		return this.passwordHashed;
	}
	
	/* UC1 constructor User w/ username, password, admin, name & firstname*/
	public User(String username, String password, boolean admin, String name, String firstName) 
	{
		setUsername(username);
		setPassword(password);
		setAdmin(admin);
		setName(name);	
		setFirstName(firstName);
		
		String salt = Security.getNextSalt();
		String passwordHashed = Security.hash(password, salt);
		
		setSalt(salt);
		setPasswordHashed(passwordHashed);
	}
	
	/* UC2 User(String username, boolean admin, String name, String firstName, String passwordHashed, String salt): constructor for user based on username, admin, name, firstname, passwordHashed & salt*/
	public User(String username, boolean admin, String name, String firstName, String passwordHashed, String salt) 
	{
		setUsername(username);
		setAdmin(admin);
		setName(name);
		setFirstName(firstName);
		
		setSalt(salt);
		setPasswordHashed(passwordHashed);
	}
}

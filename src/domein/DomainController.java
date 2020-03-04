package domein;

import exceptions.PasswordException;
import persistentie.UserMapper;
import ui.Menu;

public class DomainController {
	
	private UserRepository userRepository;
	private User selectedUser;
	
	/** UC1 Constructor*/
	
	public DomainController() {
				this.userRepository = new UserRepository();
	}
	

	/** UC1 logIn 
login checks the entered data against those in the database. 
If correct, it displays the menu. */
	
	public void logIn(String username, String password) throws Exception {
		/*GVDV LogIn method has to use the validatePasword method to retrieve the user
		 * Not sure but i assume that we use the method in the UserRepository
		 * Something to look at, do we need to throw exceptions for this one?
		 * Because i did not know what to do after successful login i now just for the example show a message*/
		
		User user = userRepository.login(username, password);
		if(user != null) {
			Menu.menu(user);
		}else {
			throw new PasswordException("Login failed");
		}
	}
	
	/* UC2 */
	public void register(String name, String firstName, String username, String password) throws Exception 
	{	
		boolean admin = false; //default value until we know how attribute can be defined with system interaction
		User user = new User(username, password, admin, username, firstName);
		
		if (userRepository.userExists(username)) 
		{
			throw new PasswordException("Username already exists");
		}else 
		{
				userRepository.createUser(user);
		}		
	}
}
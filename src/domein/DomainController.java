package domein;

import exceptions.PasswordException;
import ui.Menu;

public class DomainController {
	
	private UserMapper userMapper;
	private User selectedUser;
	private UserRepository userRepo;
	
	/** UC1 Constructor*/
	
	public DomainController() {
		
		this.userRepo = new UserRepository();
		this.userMapper = new UserMapper();
	}
	
	/** UC1  */
	public void startLogIn() {
		
	}

	/** UC1 logIn 
login checks the entered data against those in the database. 
If correct, it displays the menu. */
	
	public void logIn(String username, String password) throws Exception {
		/*GVDV LogIn method has to use the validatePasword method to retrieve the user
		 * Not sure but i assume that we use the method in the UserRepository
		 * Something to look at, do we need to throw exceptions for this one?
		 * Because i did not know what to do after successful login i now just for the example show a message*/
		
		User user = userMapper.login(username, password);
		if(user != null) {
			Menu.menu(user);
		}else {
			throw new PasswordException("Login failed");
		}
	}
	
	/* UC2 */
	public void register(String name, String firstName, String username, String password) {
		if ((userRepo.getUser(username)) != null) {
			throw new PasswordException("Username already exists");
		}else {
			String passwordRequirement = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$"; // minimum 8 characters, minimum 1 number, 1 lower case letter, 1 upper case letter
			if(password.matches(passwordRequirement)) {
				boolean is_admin = false; //default value until we know how attribute can be defined with system interaction
				userMapper.createUser(username, password, is_admin, name, firstName);

			}else {
				throw new PasswordException("Password doesn't meet the complexity requirements");
			}

		}
		
		
	}
}
package domein;

import data.UserCatalog;
import exceptions.PasswordException;
import ui.Menu;

public class DomainController {

	private UserCatalog userCatalog = new UserCatalog();
	private User selectedUser;

	/* UC1 */
	public void startLogIn() {
		
	}

	/* UC1 */
	public void logIn(String username, String password) throws Exception {
		/*GVDV LogIn method has to use the validatePasword method to retrieve the user
		 * Not sure but i assume that we use the method in the UserRepository
		 * Something to look at, do we need to throw exceptions for this one?
		 * Because i did not know what to do after successful login i now just for the example show a message*/
		
		User user = userCatalog.login(username, password);
		if(user != null) {
			Menu.menu(user);
		}else {
			throw new PasswordException("Login failed");
		}
	}
}
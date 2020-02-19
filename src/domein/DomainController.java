package domein;

public class DomainController {

	private UserRepository userRepository;
	private User selectedUser;

	/* UC1 */
	public void startLogIn() {
		
	}

	
	/* UC1 */
	public void logIn(String username, String password) {
		/*GVDV LogIn method has to use the validatePasword method to retrieve the user
		 * Not sure but i assume that we use the method in the UserRepository
		 * Something to look at, do we need to throw exceptions for this one?
		 * Because i did not know what to do after successful login i now just for the example show a message*/
		
	userRepository = new UserRepository();
		
		if(userRepository.validatePassword(username, password)) {
			userRepository.retrieveUser(username);
			System.out.print("Successful login");
		}else {
			System.out.print("Login failed");
		}
	
	}
}

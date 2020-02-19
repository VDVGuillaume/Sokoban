package domein;



public class UserMapper {
	
	/*GVDV Database isn't linked yet so i've made a dummy user in the mapper   */

	public User search(String username) {		
		
		 User gebruiker = new User(username,"wachtwoord",false);
		 return gebruiker;
	}
}

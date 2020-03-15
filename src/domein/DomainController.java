package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.PasswordException;
import util.Language;
import java.util.ResourceBundle;
import java.util.Locale;
import persistentie.UserMapper;
import ui.Menu;

public class DomainController {
	
	private UserRepository userRepository;
	private GameRepository gameRepository;
	private List<Game> gamesList;
	private User selectedUser;

	private ResourceBundle messages;
	/** UC1 Constructor*/
	

	public DomainController() {
				
				messages = ResourceBundle.getBundle("resources/MessagesBundle", Locale.getDefault(Locale.Category.DISPLAY));
				this.userRepository = new UserRepository();
				this.gameRepository = new GameRepository();
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
			Menu.menu(user.getAdmin());
		}else {
			throw new PasswordException(ResourceBundle.getBundle("resources/MessagesBundle", Language.getLanguage()).getString("LoginFailed"));
		}
	}
	
	/* UC2 */
	public void register(String name, String firstName, String username, String password) throws Exception 
	{	
		boolean admin = false; //default value until we know how attribute can be defined with system interaction
		User user = new User(username, password, admin, username, firstName);
		
		if (userRepository.userExists(username)) 
		{
			throw new PasswordException(ResourceBundle.getBundle("resources/MessagesBundle", Language.getLanguage()).getString("UsernameAlreadyExists"));
		}else 
		{
				userRepository.createUser(user);
		}		
	}

	public List<String> getGamesList()
	{
		this.gamesList = gameRepository.getGames();
		var names = new ArrayList<String>();
		
		for(Game game : gamesList){
			names.add(game.getName());
		}
		
		return names;
	}
	
	public Game chooseGame(String gameName) throws Exception 
	{
		for(Game game: gamesList) 
		{
			if(game.getName() == gameName) 
			{
				return game;
			}
		}
		
		throw new Exception("game not found");
	}		
}
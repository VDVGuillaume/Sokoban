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
				
				this.gamesList = gameRepository.getGames();
	}

	/** UC1 logIn 
login checks the entered data against those in the database. 
If correct, it displays the menu. */
	
	public void logIn(String username, String password) throws Exception {
		/*GVDV LogIn method has to use the validatePasword method to retrieve the user
		 * Not sure but i assume that we use the method in the UserRepository
		 * Something to look at, do we need to throw exceptions for this one?
		 * Because i did not know what to do after successful login i now just for the example show a message*/
		
		selectedUser = userRepository.login(username, password);
		if(selectedUser == null) {
			throw new PasswordException(ResourceBundle.getBundle("resources/MessagesBundle", Language.getLanguage()).getString("LoginFailed"));
			
		/*	
		 * int userSelectionMenu;
		 	userSelectionMenu= Menu.menu(user.getAdmin());
			if(Integer.compare(userSelectionMenu,1)==0 &&  Boolean.compare(user.getAdmin(),false)==0) {
				System.out.println("Choose one of the following games:");
				getGamesList();}
			*/					
		}else {
			
		}
	}
	
	/** UC1 getInfoUser string representation of admin and username value */
	
	public String[] getInfoUser() {
		
		String[] userInfo = new String[2];
		
		userInfo[0] = selectedUser.getUsername();
		if(selectedUser.getAdmin()) {
			userInfo[1] = "True";
		}else {
			userInfo[1] = "False";
		}
		return userInfo;
		
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
			if(game.getName().equals(gameName)) 
			{
				return game;
			}
		}
		
		throw new Exception("game not found");
	}		
}
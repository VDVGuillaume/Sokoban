package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.GameException;
import exceptions.PasswordException;
import exceptions.UsernameException;
import util.Language;
import java.util.ResourceBundle;
import java.util.Locale;
import persistentie.UserMapper;

public class DomainController {

	private UserRepository userRepository;
	private GameRepository gameRepository;
	private User selectedUser;
	private Game selectedGame;
	private Language language;
	private ResourceBundle messages;

	/** UC1 Constructor */

	public DomainController() {
		language = new Language();
		this.userRepository = new UserRepository();
		this.gameRepository = new GameRepository();
	}

	/**
	 * UC1 logIn login checks the entered data against those in the database. If
	 * correct, it displays the menu.
	 */

	public void logIn(String username, String password) throws Exception {
		/*
		 * GVDV LogIn method has to use the validatePasword method to retrieve the user
		 * Not sure but i assume that we use the method in the UserRepository Something
		 * to look at, do we need to throw exceptions for this one? Because i did not
		 * know what to do after successful login i now just for the example show a
		 * message
		 */

		selectedUser = userRepository.login(username, password);
		if (selectedUser == null) {
			throw new PasswordException(translate("LoginFailed"));

			/*
			 * int userSelectionMenu; userSelectionMenu= Menu.menu(user.getAdmin());
			 * if(Integer.compare(userSelectionMenu,1)==0 &&
			 * Boolean.compare(user.getAdmin(),false)==0) {
			 * System.out.println("Choose one of the following games:"); getGamesList();}
			 */
		} else {

		}
	}

	/** UC1 getInfoUser string representation of admin and username value */

	public String[] getInfoUser() {

		String[] userInfo = new String[2];

		userInfo[0] = selectedUser.getUsername();
		if (selectedUser.getAdmin()) {
			userInfo[1] = "True";
		} else {
			userInfo[1] = "False";
		}
		return userInfo;

	}

	/* UC2 */
	public void register(String name, String firstName, String username, String password) throws Exception {
		boolean admin = false; // default value until we know how attribute can be defined with system
								// interaction
		User user;
		try {
			user = new User(username, password, admin, username, firstName);
		} catch (PasswordException ex) {
			throw new PasswordException(language.translate(ex.getMessage()));
		} catch (UsernameException ex) {
			throw new UsernameException(language.translate(ex.getMessage()));
		}

		if (userRepository.userExists(username))

		{
			throw new PasswordException(translate("UsernameAlreadyExists"));
		} else {
			userRepository.createUser(user);
			selectedUser = user;
		}
	}

	/* UC3 */
	public List<String> getGamesList() {
		return gameRepository.getGames();
	}

	public void chooseGame(String gameName) throws Exception {
		Game game = gameRepository.getGame(gameName);

		if (game == null) {
			throw new GameException(language.translate("ErrorGameNotFound"));
		}

		selectedGame = game;
	}

	public int[] getSelectedGameInfo() {
		if (selectedGame == null) {
			throw new GameException(language.translate("ErrorGameNotFound"));
		}

		return new int[] { selectedGame.getAmountBoardsTotal(), selectedGame.getAmountBoardsCompleted() };
	}

	public void playNextGameBoard() 
	{
		if(selectedGame == null) 
		{
			throw new GameException(language.translate("ErrorGameNotFound"));
		}
		
		try 
		{
			selectedGame.playNextGameBoard();
		}catch(GameException ex) 
		{
			throw new GameException(language.translate(ex.getMessage()));
		}
	}
	
	public boolean getSelectedGameBoardComplete() {
		if (selectedGame == null) {
			throw new GameException(language.translate("ErrorGameNotFound"));
		}

		try 
		{
			return selectedGame.getSelectedGameBoardComplete();
		}catch(GameException ex) 
		{
			throw new GameException(language.translate(ex.getMessage()));
		}
	}
	
	public boolean getGameIsComplete() 
	{
		if(selectedGame == null) 
		{
			throw new GameException(language.translate("ErrorGameNotFound"));
		}
		
		try 
		{
			return selectedGame.getComplete();
		}catch(GameException ex) 
		{
			throw new GameException(language.translate(ex.getMessage()));
		}
	}
	
	public String[][] getCurrentGameBoardState()
	{
		if(selectedGame == null) 
		{
			throw new GameException(language.translate("ErrorGameNotFound"));
		}
		
		try 
		{
			return selectedGame.getCurrentGameBoardState();
		}catch(GameException ex) 
		{
			throw new GameException(language.translate(ex.getMessage()));
		}
	}	

	public void setLanguage(int languageSelection) {
		language.setLanguage(languageSelection);
	}

	public String translate(String translationKey) {
		return language.translate(translationKey);
	}
	
	/* UC4 */
	public void move(String direction) 
	{
		if(selectedGame == null) 
		{
			throw new GameException(language.translate("ErrorGameNotFound"));
		}
		
		try 
		{
			selectedGame.move(direction);
		}catch(GameException ex) 
		{
			throw new GameException(language.translate(ex.getMessage()));
		}	
	}
	
	public void resetSelectedGameBoard() 
	{
		if(selectedGame == null) 
		{
			throw new GameException(language.translate("ErrorGameNotFound"));
		}
		
		try 
		{
			selectedGame.resetCurrentGameBoard();
		}catch(GameException ex) 
		{
			throw new GameException(language.translate(ex.getMessage()));
		}	
	}
}
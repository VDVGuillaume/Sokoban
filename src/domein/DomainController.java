package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.GameException;
import exceptions.PasswordException;
import exceptions.UsernameException;
import util.Language;
import java.util.ResourceBundle;
import java.util.Locale;


public class DomainController {

	private UserRepository userRepository;
	private GameRepository gameRepository;
	private GameBoardRepository gameBoardRepository;
	private User selectedUser;
	private Game selectedGame;
	private Language language;

	public DomainController() {
		language = new Language();
		this.userRepository = new UserRepository();
		this.gameRepository = new GameRepository();
		this.gameBoardRepository = new GameBoardRepository();
	}

	/**
	 * UC1 logIn login checks the entered data against those in the database. If
	 * correct, it displays the menu.
	 */

	public void login(String username, String password) throws Exception {
		boolean userCheck = userRepository.checkUser(username, password);
		
		if (userCheck == false) {
			throw new PasswordException(translate("LoginFailed"));
		} else {
			
			selectedUser = userRepository.getUser(username);

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

	/**
	 * UC2 register checks whether a user with a certain username already exists in the database, if not a new user is added to the database
	 */

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

	/**
	 * UC3 getGamesList shows the names of games available in the database
	 */

	public List<String> getGamesList() {
		return gameRepository.getGames();
	}
	
	/**
	 * UC3 chooseGame enables user to select game to play based on gamename
	 */
	public void chooseGame(String gameName) throws Exception {
		Game game = gameRepository.getGame(gameName,selectedUser);

		if (game == null) {
			throw new GameException(language.translate("ErrorGameNotFound"));
		}

		selectedGame = game;
	}

	/**Method getSelectedGameInfo() returns a string representation of the total number of boards, completed boards, and the name of the game*/
	public String[] getSelectedGameInfo() {
		if (selectedGame == null) {
			throw new GameException(language.translate("ErrorGameNotFound"));
		}

		return new String[] { selectedGame.getNumberBoardsTotal(), selectedGame.getNumberBoardsCompleted(),selectedGame.getCreatedByUser().getUsername()};
	}
	
	/**
	 * playNextGameBoard calls method playNextGameBoard for a selectedGame, which returns the first non-completed gameboard for the selected game
	 */
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
	
	/**
	 * getSelectedGameBoardCompleted: boolean is returned to indicate whether gameboard is completed or not
	 */
	public boolean getSelectedGameBoardCompleted() {
		if (selectedGame == null) {
			throw new GameException(language.translate("ErrorGameNotFound"));
		}

		try 
		{
			return selectedGame.getSelectedGameBoardCompleted();
		}catch(GameException ex) 
		{
			throw new GameException(language.translate(ex.getMessage()));
		}
	}
	
	/**
	 * isGameCompleted calls method getComplete for a selectedGame, which returns a boolean to indicate whether a game is completed
	 */
	public boolean isGameCompleted() 
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
	
	/**
	 * getSelectedGameName returns the name of the selected game
	 */
	public String getSelectedGameName() 
	{
		if(selectedGame == null) 
		{
			throw new GameException(language.translate("ErrorGameNotFound"));
		}
		
		try 
		{
			return selectedGame.getName();
		}catch(GameException ex) 
		{
			throw new GameException(language.translate(ex.getMessage()));
		}
	} 

	/**
	 * getSelectedGameBoardState returns the string arrays representing the current state of the gameboard 
	 */
	public String[][] getSelectedGameBoardState()
	{
		if(selectedGame == null) 
		{
			throw new GameException(language.translate("ErrorGameNotFound"));
		}
		
		try 
		{
			return selectedGame.getSelectedGameBoardState();
		}catch(GameException ex) 
		{
			throw new GameException(language.translate(ex.getMessage()));
		}
	}	
	
	/**
	 * setLanguage is used to set the language in which the game should be displayed
	 */
	public void setLanguage(int languageSelection) {
		language.setLanguage(languageSelection);
	}

	/**
	 * getLanguage returns the number related to the selected language 
	 * possible returnvalues: { 1(english), 2(dutch), 3(french) }
	 */
	public int getLanguage() 
	{
		return language.getLanguage();
	}
	
	/**
	 * translate method used to translate message to the chosen language
	 */
	public String translate(String translationKey) {
		return language.translate(translationKey);
	}
	
	/**
	 * move is used to move the pawn across the board
	 */
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
	
	/**
	 * method resetSelectedGameBoard is used to reset the gameboard to the initial state, before movements on the board were made
	 */
	public void resetSelectedGameBoard() 
	{
		if(selectedGame == null) 
		{
			throw new GameException(language.translate("ErrorGameNotFound"));
		}
		
		try 
		{
			selectedGame.resetSelectedGameBoard();
		}catch(GameException ex) 
		{
			throw new GameException(language.translate(ex.getMessage()));
		}	
	}
	/**
	 * getSelectedGameBoardMoves
	 * Returns moves made on selected game board
	 * 
	 * */
	public int getSelectedGameBoardMoves() 
	{
		if(selectedGame == null) 
		{
			throw new GameException(language.translate("ErrorGameNotFound"));
		}
		
		try 
		{
			return selectedGame.getSelectedGameBoardMoves();
		}catch(GameException ex) 
		{
			throw new GameException(language.translate(ex.getMessage()));
		}	
	}



	/**UC5 Create game :  create's a new game object linked to the current user. 
	 * The object is send through the repository to the mapper */

	public void createGame(String gameName) {
		
		if(gameRepository.getGame(gameName,selectedUser) == null) {		
		
		try {
		List<GameBoard> gameboards = new ArrayList<GameBoard>();	
		selectedGame = new Game(gameName,gameboards,selectedUser);
		gameRepository.createGame(selectedGame);
		}catch(GameException ex)
		{
			System.err.print(ex.getMessage());
			throw new GameException(language.translate(ex.getMessage()));
			
		}
		}else {
			throw new GameException(language.translate("ErrorGameNameAlreadyUsed"));
		}
	}

	/** method addGameboard calls addGameboard in game class to add a new gameboard */
	public void addGameboard() {
		
		selectedGame.addGameBoard();
	 gameBoardRepository.addGameBoard(selectedGame);
	}

	
}
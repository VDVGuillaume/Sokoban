package domein;

import java.util.List;

import persistentie.GameMapper;

public class GameRepository 
{
	private GameMapper mapper;
	
	/** UC3 constructor GameRepository*/
	public GameRepository() 
	{
		mapper = new GameMapper();
	}
	
	/** UC3 getGames method that returns list of string gamenames of the games in the DB*/
	public List<String> getGames()
	{
		return mapper.getGames();
	}
	
	/** UC7 getGames method that returns list of string gamenames of the games that where created by the currently logged in user*/
	public List<String> getGames(String username)
	{
		return mapper.getGames(username);
	}
	
	/** UC3 getGame method that returns game based on gamename from DB*/
	public Game getGame(String gameName) 
	{
		return mapper.getGame(gameName);
	}
	
	/** UC5 SaveGame saves game (name) & username of user that created it**/
	public void saveGame(Game game, String username) {
		mapper.insertGame(game,username);
	}
	
	
	public void updateGame(Game game) {
		mapper.updateGame(game);
	}
}
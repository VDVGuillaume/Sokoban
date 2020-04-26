package domein;

import java.util.List;

import persistentie.GameMapper;

public class GameRepository 
{
	private GameMapper mapper;
	
	public GameRepository() 
	{
		mapper = new GameMapper();
	}
	
	public List<String> getGames()
	{
		return mapper.getGames();
	}
	
	public List<String> getGames(String username)
	{
		return mapper.getGames(username);
	}
	
	public Game getGame(String gameName) 
	{
		return mapper.getGame(gameName);
	}
	
	public void saveGame(Game game, String username) {
		mapper.saveGame(game,username);
	}
}
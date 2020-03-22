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
	
	public Game getGame(String gameName) 
	{
		return mapper.getGame(gameName);
	}
}
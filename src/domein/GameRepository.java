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
	
	public List<Game> getGames()
	{
		return mapper.getGames();
	}
}
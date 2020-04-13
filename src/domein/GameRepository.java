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
	
	public Game getGame(String gameName,User user) 
	{
		return mapper.getGame(gameName,user);
	}
	
	public void createGame(Game game) {
		mapper.createGame(game);
	}
}
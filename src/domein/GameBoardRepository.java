package domein;
import java.util.List;
import persistentie.GameBoardMapper;
import persistentie.TileMapper;

public class GameBoardRepository {
	private GameBoardMapper mapper;
	
	public GameBoardRepository() 
	{
		mapper = new GameBoardMapper();
	}
	
	public List<GameBoard> getGameBoards()
	{
		//TODO still necessary?
		return null;
		//return mapper.getGameBoards();
	}
	
	public int saveGameBoard(Game game){
		//edited so mapper.saveGameBoard is executed once
		int gameboardID = mapper.saveGameBoard(game);
		System.out.println("saveGameBoard GBRepo:" + gameboardID);
		return gameboardID;
		
	}
	
	public void insertGame(Game game) {
		//TODO actually saveGame but renamed
	}
	
	public void updateGame(Game game) {
		//TODO
	}
	

	

	
}
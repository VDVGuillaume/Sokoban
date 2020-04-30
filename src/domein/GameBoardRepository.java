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
		System.out.println("saveGameBoard GBRepo:" + mapper.saveGameBoard(game));
		return mapper.saveGameBoard(game);
		
	}
	
	public void insertGame(Game game) {
		//TODO actually saveGame but renamed
	}
	
	public void updateGame(Game game) {
		//TODO
	}
	

	

	
}
package domein;
import java.util.List;
import persistentie.GameBoardMapper;

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
	
	public void addGameBoard(Game game){
		mapper.addGameBoard(game);
	}
	
}
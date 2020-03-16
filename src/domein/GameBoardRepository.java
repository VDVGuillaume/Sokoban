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
		return mapper.getGameBoards();
	}
}
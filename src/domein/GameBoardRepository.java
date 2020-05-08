package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.GameBoardMapper;
import persistentie.TileMapper;

public class GameBoardRepository {
	private GameBoardMapper mapper;

	public GameBoardRepository() {
		mapper = new GameBoardMapper();
	}

	public List<GameBoard> getGameBoards() {
		// TODO still necessary? 
		return null;
		// return mapper.getGameBoards();
	}

	public int saveGameBoard(Game game) {
		// TODO still necessary?
		return -1;
	}

	public void insertGame(Game game) {
		// TODO actually saveGame but renamed
	}

	public void updateGame(Game game) {
		// TODO
	}
	
	public List<Integer> getGameBoardIds(String gameName) {
		// TODO still necessary? Used in UC6 GUI
		List<GameBoard> gameBoards = mapper.getGameBoards(gameName);
		List<Integer> gameBoardIds = new ArrayList<>();
		for(GameBoard gameboard : gameBoards) 
		{
			gameBoardIds.add(gameboard.getId());
		}
		return gameBoardIds ;
		
	}
}
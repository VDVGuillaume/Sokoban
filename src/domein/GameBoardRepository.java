package domein;

import java.util.List;
import persistentie.GameBoardMapper;
import persistentie.TileMapper;

public class GameBoardRepository {
	private GameBoardMapper mapper;

	public GameBoardRepository() {
		mapper = new GameBoardMapper();
	}

	public List<GameBoard> getGameBoards() {
		// TODO still necessary? Used in UC6 GUI
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
}
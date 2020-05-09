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

	public int saveGameBoard(String gameName, GameBoard gameBoard) {
		// TODO still necessary?
		int gameboardID = mapper.saveGameBoard(gameBoard, gameName);
		System.out.println("saveGameBoard GBRepo:" + gameboardID);
		return gameboardID;
	}

	public void insertGame(Game game) {
		// TODO actually saveGame but renamed
	}

	public void updateGame(Game game) {
		// TODO
	}
	
	public List<Integer> getGameBoardIds(String gameName) {
		//  Used in UC6 GUI
		List<GameBoard> gameBoards = mapper.getGameBoards(gameName);
		List<Integer> gameBoardIds = new ArrayList<>();
		for(GameBoard gameboard : gameBoards) 
		{
			gameBoardIds.add(gameboard.getId());
		}
		return gameBoardIds ;
	}
	
	public void insertGameBoard(GameBoard gameBoard, String gameName) {
		int id = mapper.insertGameBoard(gameBoard, gameName);
		gameBoard.setId(id);
	}
	
	public void updateGameBoard(GameBoard gameBoard) {
		mapper.updateGameBoard(gameBoard);
	}
}
package domein;

import java.util.List;

public class Game {
	private List<GameBoard> gameBoards;
	
	public Game(/*List<GameBoard> gameBoards*/) {
		//TODO get gameBoards from constructor as parameter -> domainController gets gameBoards from Repository
	}
	
	private void setGameBoards(List<GameBoard> gameBoards) {
		this.gameBoards = gameBoards;
	}
	
	public List<GameBoard> getGameBoards() {
		return gameBoards;
	}	
	
	public int getAmountBoardsCompleted() {
		//TODO
		return -1;
	}
	
	public int getAmountBoardsTotal() {
		//TODO
		return -1;
	}
	
	public GameBoard getNextGameBoard() {
		//TODO
		return null;
	}
	
	public void completeGameBoard(GameBoard gameBoard) {
		gameBoard.setCompleted(true);
	}
}

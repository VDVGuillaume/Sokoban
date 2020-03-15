package domein;

import java.util.List;

public class Game {
	private List<GameBoard> gameBoards;
	private String name;
	
	public Game(List<GameBoard> gameBoards, String name) {
		setGameBoards(gameBoards);
	}
	
	private void setGameBoards(List<GameBoard> gameBoards) {
		this.gameBoards = gameBoards;
	}
	
	private void setName(String name) 
	{
		this.name = name;
	}
	
	public String getName() {
		return this.name;
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

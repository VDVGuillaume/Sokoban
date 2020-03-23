package domein;

import java.util.List;

public class Game {
	private List<GameBoard> gameBoards;
	private String name;
	
	public Game(String name, List<GameBoard> gameBoards) 
	{
		setName(name);
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
		int amountBoardsCompleted=0;
		for(GameBoard gameBoard : gameBoards) 
		{
			if(gameBoard.getCompleted()) 
			{
				amountBoardsCompleted++;
			}
		}
		return amountBoardsCompleted;
	}
	
	public int getAmountBoardsTotal() {
		return gameBoards.size();
	}
	
	public boolean allBoardsGameCompleted() {
		if(getAmountBoardsCompleted()<getAmountBoardsTotal())
		{
			return false;
		}else {
			return true;
		}
		
	}
	
	public GameBoard getNextGameBoard() 
	{
		for(GameBoard gameBoard : gameBoards) 
		{
			if(gameBoard.getCompleted()) 
			{
				continue;
			}else 
			{
				return gameBoard;
			}
		}
		
		return null;
	}
	
	public void completeGameBoard(GameBoard gameBoard) 
	{
		gameBoard.setCompleted(true);
	}
}

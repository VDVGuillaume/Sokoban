package domein;

import java.util.List;

import exceptions.GameException;

public class Game {
	private List<GameBoard> gameBoards;
	private GameBoard selectedGameBoard;
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
	
	private GameBoard getNextGameBoard() 
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
	
	public boolean getSelectedGameBoardCompleted() 
	{
		if(selectedGameBoard == null) 
		{
			throw new GameException("ErrorGameBoardNotFound");
		}
		
		return selectedGameBoard.getCompleted();
	}
	
	public void playNextGameBoard()
	{
		selectedGameBoard = getNextGameBoard();
		
		if(selectedGameBoard == null) 
		{
			throw new GameException("ErrorGameBoardNotFound");
		}
	}
	
	public boolean getComplete() 
	{
		return getAmountBoardsCompleted() == getAmountBoardsTotal();
	}
	
	public String[][] getSelectedGameBoardState()
	{
		if(selectedGameBoard == null) 
		{
			throw new GameException("ErrorGameBoardNotFound");
		}
		
		return selectedGameBoard.getCurrentState();
	}
	
	public void move(String direction) 
	{
		if(selectedGameBoard == null) 
		{
			throw new GameException("ErrorGameBoardNotFound");
		}
		
		selectedGameBoard.move(direction);
	}
	
	public void resetSelectedGameBoard() 
	{
		if(selectedGameBoard == null) 
		{
			throw new GameException("ErrorGameBoardNotFound");
		}
		
		selectedGameBoard.resetGameBoard();	
	}
	
	public int getSelectedGameBoardMoves() 
	{
		if(selectedGameBoard == null) 
		{
			throw new GameException("ErrorGameBoardNotFound");
		}
		
		return selectedGameBoard.getMoves();	
	}
}

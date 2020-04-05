package domein;

import java.util.List;

import exceptions.GameException;

public class Game {
	private List<GameBoard> gameBoards;
	private GameBoard selectedGameBoard;
	private String name;
	private String createdByUser;
	
	public Game(String name, List<GameBoard> gameBoards) 
	{
		setName(name);
		setGameBoards(gameBoards);
	}
	
	public Game(String name, String user) {
		
		setName(name);
		setCreatedByUser(user);
		
	}
	
	private void setGameBoards(List<GameBoard> gameBoards) {
		this.gameBoards = gameBoards;
	}
	
	private void setCreatedByUser(String user) {
		this.createdByUser = user;
	}
	
	private String getCreatedByUser() {
		return this.createdByUser;
	}
	
	private void setName(String name) 
	{
		if(!name.contains(" ")) {
		this.name = name;}else {
			throw new GameException("ErrorSpacesInGameName");
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<GameBoard> getGameBoards() {
		return gameBoards;
	}	
	
	public int getNumberBoardsCompleted() {
		int numberBoardsCompleted=0;
		for(GameBoard gameBoard : gameBoards) 
		{
			if(gameBoard.getCompleted()) 
			{
				numberBoardsCompleted++;
			}
		}
		return numberBoardsCompleted;
	}
	
	public int getNumberBoardsTotal() {
		return gameBoards.size();
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
		GameBoard tempGameBoard = null;
		for(GameBoard gameBoard : gameBoards) 
		{
			if(gameBoard.getCompleted()) 
			{
				continue;
			}
			
			tempGameBoard = gameBoard;
			break;
		}
		
		selectedGameBoard = tempGameBoard;
		
		if(selectedGameBoard == null) 
		{
			throw new GameException("ErrorGameBoardNotFound");
		}
	}
	
	public boolean getComplete() 
	{
		return getNumberBoardsCompleted() == getNumberBoardsTotal();
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

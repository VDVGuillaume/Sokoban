package domein;

import java.util.List;

import exceptions.GameException;

public class Game {
	private List<GameBoard> gameBoards;
	private GameBoard selectedGameBoard;
	
	private String name;
	

	
	/**
	 * UC3 Constructor
	 */
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
	
	public String getNumberBoardsCompleted() {
		int numberBoardsCompleted=0;
		for(GameBoard gameBoard : gameBoards) 
		{
			if(gameBoard.isCompleted()) 
			{
				numberBoardsCompleted++;
			}
		}
		return  String.format("%d",numberBoardsCompleted);
	}
	
	public String getNumberBoardsTotal() {
		return String.format("%d",gameBoards.size());		
	}
	
	public boolean isSelectedGameBoardCompleted() 
	{
		if(selectedGameBoard == null) 
		{
			throw new GameException("ErrorGameBoardNotFound");
		}
		
		return selectedGameBoard.isCompleted();
	}
	
	public void playNextGameBoard()
	{
		GameBoard tempGameBoard = null;
		for(GameBoard gameBoard : gameBoards) 
		{
			if(gameBoard.isCompleted()) 
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
	
	public boolean isComplete() 
	{
		return getNumberBoardsCompleted().equals(getNumberBoardsTotal());
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
	
	public void addGameBoard(GameBoard gameBoard) {
		this.selectedGameBoard = gameBoard;
		
		gameBoards.add(selectedGameBoard);
		
	}
	
	public void setSelectedGameBoard(GameBoard gameBoard) {
		this.selectedGameBoard = gameBoard;
	}
	
	public void moveSelector(String direction) {
		if(selectedGameBoard == null) {
			throw new GameException("ErrorGameBoardNotFound");
		}
	selectedGameBoard.moveSelector(direction);
	}
	
	public void toggle() {
		selectedGameBoard.Toggle();
	}
	
}

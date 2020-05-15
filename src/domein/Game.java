package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.GameException;

public class Game {
	private List<GameBoard> gameBoards;
	private GameBoard selectedGameBoard;
	
	private String name;
	

	
	/**
	 * UC3 Constructor Game w/ name & gameboards list
	 */
	public Game(String name, List<GameBoard> gameBoards) 
	{
		setName(name);
		setGameBoards(gameBoards);
	}
	
	/**UC3 setGameboards for a game based on a list of gameboards*/	
	private void setGameBoards(List<GameBoard> gameBoards) {
		this.gameBoards = gameBoards;
	}
	
	/**UC3 setName of the Game*/	
	private void setName(String name) 
	{
		if(!name.contains(" ")) {
		this.name = name;}else {
			throw new GameException("ErrorSpacesInGameName");
		}
	}
	
	/**UC3 getName returns the String name of the Game*/	
	public String getName() {
		return this.name;
	}
	
	public List<GameBoard> getGameBoards() {
		return gameBoards;
	}	
	
	/**UC3 getNumberBoardsCompleted returns the number of completed boards in String format*/
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
	
	/**UC3 getNumberBoardsTotal returns the total number of boards in String format*/
	public String getNumberBoardsTotal() {
		return String.format("%d",gameBoards.size());		
	}
	
	/**UC3 isSelectedGameBoardCompleted returns true if gameboard is true; else false*/
	public boolean isSelectedGameBoardCompleted() 
	{
		if(selectedGameBoard == null) 
		{
			throw new GameException("ErrorGameBoardNotFound");
		}
		
		return selectedGameBoard.isCompleted();
	}
	
	/**UC3 playNextGameBoard() sets getSelectedGameBoard to the first non-completed gameboard of the selectedGame*/
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
	
	/**UC3 method isComplete returns true if all gameboards for the game have been completed, which is the case if the number of boards completed is equal to the overall number of boards; otherwise return false*/
	public boolean isComplete() 
	{
		return getNumberBoardsCompleted().equals(getNumberBoardsTotal());
	}
	
	/**UC3 method isComplete returns true if all gameboards for the game have been completed, which is the case if the number of boards completed is equal to the overall number of boards; otherwise return false*/
	public String[][] getSelectedGameBoardState()
	{
		if(selectedGameBoard == null) 
		{
			throw new GameException("ErrorGameBoardNotFound");
		}
		
		return selectedGameBoard.getCurrentState();
	}
	
	/**UC4 move the pawn in certain direction using Gameboard move methods*/
	public void move(String direction) 
	{
		if(selectedGameBoard == null) 
		{
			throw new GameException("ErrorGameBoardNotFound");
		}
		
		selectedGameBoard.move(direction);
	}
	
	/**UC4 resetSelectedGameBoard reset the gameboard using the resetSelectedGameBoard methods for GameBoard*/
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
	
	/**UC5 addGameBoard(GameBoard gameBoard): adding selected gameboard to the gameboards*/
	public void addGameBoard(GameBoard gameBoard) {
		selectedGameBoard = gameBoard;
		
		gameBoards.add(selectedGameBoard);
		
	}
	
	public void setSelectedGameBoard(GameBoard gameBoard) {
		this.selectedGameBoard = gameBoard;
	}
	
	/**UC6 method changeGameboard saves the changes to the tiles of a gameboard**/
	public void changeGameboard() {
		selectedGameBoard.changeGameboard();
	}
	
	/**UC6 Get selected gameBoard **/
	public GameBoard getSelectedGameBoard() {
		return selectedGameBoard;
	}
	
	/**UC7 getGameBoardsIds returns the ids of the gameboards related to selectedgame**/
	public List<Integer> getGameBoardsIds() {
		List<GameBoard> gameboards = new ArrayList<>();
		gameboards = this.getGameBoards();
		List<Integer> gameboardIds = new ArrayList<>();
		for(GameBoard gameboard: gameboards) {
			gameboardIds.add(gameboard.getId());
		}
		return gameboardIds;
	}	
	
	
	
	
	
	/**UC7 chooseGameBoard(int gameBoardId) */
	public void chooseGameBoard(int gameBoardId) {
		List<GameBoard> gameBoards= getGameBoards();
		for(GameBoard gameboard: gameBoards) {
			if(gameboard.getId()==gameBoardId){
				selectedGameBoard = gameboard;
			}
		}	
	}
	
	/**method deleteSelectedGameBoard deletes selected gameboard**/
	public void deleteSelectedGameBoard() {
		if(!validateGame()) {
			throw new GameException("ErrorDeleteGameBoardLimitReached");
		}
		
		gameBoards.remove(selectedGameBoard);	
		setSelectedGameBoard(null);
	}
	
	/**ValidateGame checks whether there is at least 1 gameboard**/
	private boolean validateGame() {
		return gameBoards != null && gameBoards.size() > 1;
	}	
}

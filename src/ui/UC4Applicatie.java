package ui;

import domein.DomainController;
import domein.GameBoard;

public class UC4Applicatie 
{
	private DomainController domainController;
	
	public UC4Applicatie(DomainController domainController) {
		this.domainController = domainController;
		

	}
	
	public void UI() 
	{
		try 
		{
			GameBoard gameBoard = domainController.chooseGame("test_dbol").getNextGameBoard();
			
			UiGameBoard uiGameBoard = new UiGameBoard(gameBoard);
			uiGameBoard.drawConsole();
		}
		 catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
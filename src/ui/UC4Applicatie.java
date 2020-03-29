package ui;

import java.util.List;
import java.util.Scanner;

import domein.DomainController;
import domein.GameBoard;
import domein.GameBoardMoves;

public class UC4Applicatie 
{
	private DomainController controller;
	
	public UC4Applicatie(DomainController controller) {
		this.controller = controller;
		

	}
	
	private void playNextGameBoard(Scanner input) 
	{
		UiGameBoardConsole gameBoardConsole = new UiGameBoardConsole();
		int gameBoardMoves; 
		
		controller.playNextGameBoard();
		
		while(!controller.getSelectedGameBoardCompleted()) 
		{
			gameBoardMoves = controller.getSelectedGameBoardAmountMoves();
			gameBoardConsole.drawConsole(controller.getSelectedGameBoardState());
			System.out.println(controller.translate("GameBoardMovesMade") + gameBoardMoves);
			System.out.println(controller.translate("GameBoardPossibleMoves"));
			char c = input.next().charAt(0);
			
			switch(c) 
			{
			case 'Q':
			case 'q':
				controller.move("Left");
				break;
			case 'Z':
			case 'z':
				controller.move("Up");
				break;
			case 'S':
			case 's':
				controller.move("Down");
				break;
			case 'D':
			case 'd':
				controller.move("Right");
				break;
			case 'R':
			case 'r':
				controller.resetSelectedGameBoard();
				break;
			case 'T':
			case 't':
				return;
			default:
				break;
			}
		}
		gameBoardMoves = controller.getSelectedGameBoardAmountMoves();
		gameBoardConsole.drawConsole(controller.getSelectedGameBoardState());
		System.out.println(controller.translate("GameBoardCompleted").replace("$param1", Integer.toString(gameBoardMoves)));
	}
	
	public void UI(Scanner input) {
		int gameNumber;
		int gameboardActionNumber;
		String gameName;
		boolean gameboardCompleted;

		try {
			List<String> names = controller.getGamesList();
			System.out.println(controller.translate("GiveGameName"));
			for (int i = 0; i < names.size(); i++) {
				String gamename = (i + 1) + ". " + names.get(i);
				System.out.println(gamename);
			}

			gameNumber = input.nextInt();
			gameName = names.get(gameNumber - 1);
			controller.chooseGame(gameName);

			while (true) {
				int[] gameInfo = controller.getSelectedGameInfo();
				int totalAmountGameBoards = gameInfo[0];
				int completedAmountGameBoards = gameInfo[1];

				System.out.println(completedAmountGameBoards + " "
						+ controller.translate("NumberGameboardsCompletedOutofTotal") + " " + totalAmountGameBoards);
				
				if(controller.getGameIsComplete()) 
				{
					break;
				}
				
				System.out.println(controller.translate("GiveGameboardAction"));
				String option1 = "1." + controller.translate("PlayNextGameboard");
				System.out.println(option1);
				String option2 = "2." + controller.translate("QuitGame");
				System.out.println(option2);
				gameboardActionNumber = input.nextInt();

				if (gameboardActionNumber == 1) {
					playNextGameBoard(input);
				} else if (gameboardActionNumber == 2) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			UI(input);
		} finally {
		}

	}
}
package ui;


import java.util.Scanner;

import domein.DomainController;

public class UC6Applicatie {
	
	private DomainController controller;
	private UiGameBoardConsole console;

	
	public UC6Applicatie(DomainController controller) {
		this.controller = controller;
		this.console = new UiGameBoardConsole();
		
	}
	
	
	
	

	public void UI(Scanner input) {
		
		int[] selector = {5,5,0};		

		int selectorXCoordinate = selector[0];
		int selectorYCoordinate = selector[1];
				
		try {
		System.out.println(controller.translate("GiveGameName"));
		String gameName = input.nextLine();		
		controller.createGame(gameName);
		int answer = 0;
		String[] action = {"clear","wall","pawn","goal","box"};
		int actionIndex = 0;
		int multipleGameBoards = 0;
		String userInput = new String();
		
		do{
			controller.createGameBoard();
			
			String gameboardInfo[][] = controller.getSelectedGameBoardState();
			String[][] gameboardInfoWithSelector = gameboardInfo;
			gameboardInfoWithSelector[selectorYCoordinate][selectorXCoordinate] = "Selector";
			console.drawConsole(gameboardInfoWithSelector);
			
			
			 
			do{
				
				System.out.println(controller.translate("GameBoardCreationPossibleMoves"));
				char c = input.next().charAt(0);
				
				switch(c) 
				{
				case 'Q':
				case 'q':
					selectorYCoordinate --;

					selector[2] = 0;
					break;
				case 'Z':
				case 'z':
					selectorXCoordinate --;

					selector[2] = 0;
					break;
				case 'S':
				case 's':
					selectorXCoordinate ++;
					selector[2] = 0;
					break;
				case 'D':
				case 'd':
					selectorYCoordinate ++;

					selector[2] = 0;
					break;
				case 'E':
				case 'e':
					String piece = new String();
					piece = action[actionIndex];
					controller.setPositionAction(selectorXCoordinate, selectorYCoordinate, piece);
					actionIndex++;
					if (actionIndex > 4) {
						actionIndex = 0;
					}
					selector[2] = 1;
					
					break;
				case 'A':
				case 'a':
					userInput = "save";
					controller.saveTiles();
					break;
				case 'T':
				case 't':
					return;
				default:
					break;
				}
				
				if (selectorYCoordinate > 9 || selectorYCoordinate < 0 || selectorXCoordinate > 9 || selectorXCoordinate < 0) {
					selectorYCoordinate = selector[1];
					selectorXCoordinate = selector[0];
					continue;
				}
				else {
					selector[0] = selectorXCoordinate;
					selector[1] = selectorYCoordinate;
				}
				
				gameboardInfo = controller.getSelectedGameBoardState();
				
				if (selector[2] == 0) {
					gameboardInfoWithSelector = gameboardInfo;
					gameboardInfoWithSelector[selectorXCoordinate][selectorYCoordinate] = "Selector";
					console.drawConsole(gameboardInfoWithSelector);
				}
				else {
				    
				    
					console.drawConsole(gameboardInfo);
				}
				
		
			
			
			}while(!userInput.equals("save"));
			if (multipleGameBoards == 0) {
				controller.saveGame();
				multipleGameBoards ++;
			}
			else {
				controller.addGameboard();
			}
			
			
			
			System.out.println(controller.translate("addGameBoard"));
			
			
			
			
			
			
			
			answer = input.nextInt();
		}while(answer == 1);
		
		String gameInfo[] = controller.getSelectedGameInfo();
		System.out.printf("%s %s%n",gameInfo[2],gameInfo[0]);
		}
		catch(Exception e) {
			
			e.printStackTrace();
			UI(input);		
		
	}
	
	
	
}}

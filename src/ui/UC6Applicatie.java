package ui;

import java.util.ArrayList;
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
		
				
		try {
		System.out.println(controller.translate("GiveGameName"));
		String gameName = input.nextLine();		
		controller.createGame(gameName);
		int answer = 0;
		String action = new String();
		do{
			controller.createGameBoard();
			System.out.println(controller.translate("addGameBoard"));
			String gameboardInfo[][] = controller.getSelectedGameBoardState();
			console.drawConsole(gameboardInfo);
			
			
			 
			do{
				/** System.out.println(controller.translate("GameBoardCreationPossibleActions"));
				action = input.nextLine();
				action = action.toLowerCase();
				
				System.out.println(controller.translate("GameBoardCreationSelectedCoordinates"));
				String coordinates = input.nextLine();
				String[] coordinatesArray = coordinates.split(",");
				ArrayList<Integer> coordinatesIntArrayList = new ArrayList<Integer>();
			    for (String a : coordinatesArray)
			    {
			    	int i = Integer.parseInt(a.trim());
			    	coordinatesIntArrayList.add(i);
		            
			    } 
			    int xCoordinate = coordinatesIntArrayList.get(0);
			    int yCoordinate = coordinatesIntArrayList.get(1);
			    controller.setPositionAction(xCoordinate,yCoordinate,action );
			    gameboardInfo = controller.getSelectedGameBoardState();
				console.drawConsole(gameboardInfo); */
				
				char c = input.next().charAt(0);
				
				switch(c) 
				{
				case 'Q':
				case 'q':
					controller.moveSelector("Left");
					break;
				case 'Z':
				case 'z':
					controller.moveSelector("Up");
					break;
				case 'S':
				case 's':
					controller.moveSelector("Down");
					break;
				case 'D':
				case 'd':
					controller.moveSelector("Right");
					break;
				case 'E':
				case 'e':
					controller.toggle();
					break;
				case 'A':
				case 'a':
					action = "save";
					break;
				case 'T':
				case 't':
					return;
				default:
					break;
				}
				
				
				
			    // controller.setPositionAction(xCoordinate,yCoordinate,action );
			    gameboardInfo = controller.getSelectedGameBoardState();
				console.drawConsole(gameboardInfo);
			    
			}while(!action.equals("save"));
			controller.addGameboard();
			
			
			
			
			
			
			
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

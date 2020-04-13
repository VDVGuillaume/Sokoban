package ui;

import java.util.Scanner;

import domein.DomainController;

public class UC5Applicatie {
	
	private DomainController controller;
	
	
	public UC5Applicatie(DomainController controller) {
		this.controller = controller;
		
	}

	public void UI(Scanner input) {	
		
				
		try {
		System.out.println(controller.translate("GiveGameName"));
		String gameName = input.nextLine();		
		controller.createGame(gameName);
		int answer = 0;
		do{
			controller.addGameboard();
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

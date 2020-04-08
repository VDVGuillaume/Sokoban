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
		
		System.out.printf("", controller.getGameInfo())
		}catch(Exception e) {
			System.out.println(e.getMessage());
			UI(input);		
		
	}
	
	
	
}}

package ui;

import java.util.Scanner;

import domein.DomainController;

public class UC5Applicatie {
	
	private DomainController controller;
	
	
	public UC5Applicatie(DomainController controller) {
		this.controller = controller;
		
	}

	public void UI(Scanner input) {
		
		
		
		System.out.println(controller.translate("GiveGameName"));
		String gameName = input.next();
		controller.createGame(gameName);
		
	}
	
	
	
}
